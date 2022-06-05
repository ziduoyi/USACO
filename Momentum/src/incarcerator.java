import java.io.*;
import java.util.*;
public class incarcerator {
	static HashMap<Integer, Integer> map;
	static HashSet<Integer>[] nodes;
	static int[][] store;
	static int[] size;
    public static void main(String[] Args)throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	        int n = Integer.parseInt(st.nextToken());
	        int e = Integer.parseInt(st.nextToken());
	        Graph g = new Graph(n);
	        map = new HashMap<>();
	        store = new int[e][2];
	        size = new int[e];
	        for (int j = 0; j < e; j++) {
	        	st = new StringTokenizer(br.readLine());
	            int a = Integer.parseInt(st.nextToken()) - 1;
	            int b = Integer.parseInt(st.nextToken()) - 1;
	            g.add(a, b);
	            store[j][0] = a;
	            store[j][1] = b;
	        }
	        nodes = new HashSet[e];
	        for(int k=0; k<e; k++)nodes[k] = new HashSet<>();
            g.run();
            g.components();
            ArrayList<Integer>[] edges = new ArrayList[g.comp_count-1];
            for(int k=0; k<g.comp_count-1; k++) {
            	edges[k] = new ArrayList<>();
            }
            for(int k=0; k<g.comp_count; k++) {
            	
            }
            for (int k = 0; k < g.comp_count; k++) {
                System.out.println("Component " + (k + 1));
                for (Edge e2 : g.e_list) {
                    if (g.edge_to_comp[e2.ind] == k)
                        System.out.print(e2.ind + " ");
                }
                System.out.println();
                System.out.println();
            }
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
                                size[comp_count]++;
                                nodes[comp_count].add(store[cur][0]);
                                nodes[comp_count].add(store[cur][1]);
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
        void components() {
        	HashSet<Integer> store = new HashSet<>();
        	for(int j=0; j<e; j++)store.add(j);
        	for(int j=0; j<comp_count; j++) {
        		if(size[j]>=2) {
        			Edge e = e_list.get(j);
        			if(store.contains(e.a)) {
        				
        			}
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
