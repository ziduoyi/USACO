
import java.util.*;

public class bbc {
    public static void main(String[] Args) {
        Scanner sc = new Scanner(System.in);
        int t= sc.nextInt();
        int n = sc.nextInt();
        int e = sc.nextInt();
        Graph g = new Graph(n);
        for (int i = 0; i < e; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            g.add(a, b);
        }
        g.run();
        for (int i = 0; i < g.comp_count; i++) {
            System.out.println("Component " + (i + 1));
            for (Edge e2 : g.e_list) {
                if (g.edge_to_comp[e2.ind] == i)
                    System.out.print(e2.ind + " ");
            }
            System.out.println();
            System.out.println();
        }
    }
    public static class Graph {
        ArrayList<Edge>[] adj;
        ArrayList<Edge> e_list;
        int n;
        Graph(int nn) {
            e_list = new ArrayList<Edge>();
            adj = new ArrayList[n = nn];
            for (int i = 0; i < n; i++) 
                adj[i] = new ArrayList<Edge>();
        }
        void add(int a, int b) {
            int id = e_list.size();
            Edge fwd = new Edge(a, b, id);
            Edge rev = new Edge(b, a, id);
            adj[a].add(fwd);
            adj[b].add(rev);
            e_list.add(fwd);
        }

        int pre_order_index;
        int[] pre_order;
        int[] low_link;
        int[] edge_stack;
        boolean[] edge_used;
        boolean[] node_used;
        int comp_count;
        int[] edge_to_comp;
        boolean[] is_ap;

        int e;
        void run() {
            e = e_list.size();
            pre_order_index = comp_count = 0;
            pre_order = new int[n];
            low_link = new int[n];
            Arrays.fill(pre_order, 1000000000);
            Arrays.fill(low_link, 1000000000);
            edge_stack = new int[e + 1];
            edge_used = new boolean[e];
            node_used = new boolean[n];
            edge_to_comp = new int[e];
            is_ap = new boolean[n];
            for (int i = 0; i < n; i++)
                if (!node_used[i])
                    dfs(i);
        }

        void dfs(int u) {
            node_used[u] = true;
            pre_order[u] = low_link[u] = pre_order_index++;
            for (Edge e: adj[u]) {
                if (!edge_used[e.ind]) {
                    edge_used[e.ind] = true;
                    edge_stack[++edge_stack[0]] = e.ind;
                    int v = e.b;
                    if (!node_used[v]) {
                        dfs(v);
                        if (low_link[v] >= pre_order[u]) {
                            is_ap[u] = true;
                            // special case if leaf
                            int cur = -1;
                            while (cur != e.ind) {
                                cur = edge_stack[edge_stack[0]--];
                                edge_to_comp[cur] = comp_count;
                            }
                            comp_count++;
                        }
                    }
                    // These two lines were in the above code block
                    // :(
                    if (low_link[v] < low_link[u])
                        low_link[u] = low_link[v];
                }
            }
        }   
    }
    public static class Edge {
        int a, b, ind;
        Edge(int aa, int bb, int ii) {
            a = aa; b = bb; ind = ii;
        }
    }
}
