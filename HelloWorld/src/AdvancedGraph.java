import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class AdvancedGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	// ------------------------------------------------------------------Tarjan algorithm (directed graph)
	static ArrayList<Integer>[] edges;//default
	static Stack<Integer> stack;//default use stack array trick
	static int[] scc; //default
	static int[] in; // filled with -1
	static int[] low; // filled with -1
  	static boolean[] instack; //filled with false
	static int time; //start at 1
	static int num; //default
	static int N; //input
	
	static void countscc() {
		Set<Integer> set = new HashSet<>();
		for(int i=0; i<N; i++)
			set.add(scc[i]); //node i belongs in group scc[i]
		System.out.println(set.size());
	}
	static void tarjan() {
		for(int i=0; i<N; i++)
			if(in[i] == -1)
				dfs(i);
	}
	static void dfs(int node) {
		low[node] = in[node] = time++;
		stack.push(node);
		instack[node] = true;
		ArrayList<Integer> al = edges[node];
		int s = al.size();
		for(int i=0; i<s; i++) {
			int tar = al.get(i);
			if(in[tar] == -1) {
				dfs(tar);
				low[node] = Math.min(low[node], low[tar]);
			}
			else if(instack[tar]) 
				low[node] = Math.min(low[node], in[tar]);// Math.min(low[node], low[tar]) does not works	
		}
		if(low[node] == in[node]) {
			while(true) {
				int u = stack.pop();
				scc[u] = num;
				instack[u] = false;
				if(u==node)break;
			}
			num++;
		}
	}
	//---------------------------------------------------------------------------end of tarjan algorithm
	//variables cancelled out so that no errors would occur with code above
	
	//bbc algorithm
	/*
	 * import java.util.*;

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
End of bbc*/
	//static ArrayList<Integer>[] edges;
	//static int[] in;
	//static int[] low;
	static boolean[] visit;
	//static int time;
	//static int num;
	//static int N;
	static void Articulation()throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		edges = new ArrayList[N];
		for(int i=0; i<N; i++)
			edges[i] = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			edges[a].add(b);
			edges[a].add(i);
			edges[b].add(a);
			edges[b].add(i);
		}
		in = new int[N];
		Arrays.fill(in, -1);
		low = new int[N];
		Arrays.fill(low, -1);
		visit = new boolean[N];
		time = 1;
		num = 0;
		for(int i=0; i<N; i++) {
			if(visit[i]==false)
				dfs(i,-1);
		}
		System.out.println(num);
	}
	static void dfs(int node, int last) {// last = previous id
		low[node] = in[node] = time++;
		visit[node] = true;
		ArrayList<Integer> al = edges[node];
		int count = 0;
		boolean check = false;
		int s = al.size();
		for(int i=0; i<s; i+=2) {
			int tar = al.get(i);
			int curr = al.get(i+1);
			if(curr==last)
				continue;
			if(visit[tar] ==false) {
				count++;
				dfs(tar, curr);
				if(low[tar]>=in[node])
					check = true;
				low[node] = Math.min(low[node], low[tar]);
			}
			else
				low[node] = Math.min(low[node], in[tar]);
		}
		if(last==-1) 
			check = (count>1);
		if(check == true)
			num ++;
	}
	//--------------------- check if a point is below, above, or in a linear line formed by two other points
	static boolean ccw(int x1, int y1, int x2, int y2, int x3, int y3) { // on line gives value 0, above gives -, below gives +
		if((long)((long)((x2-x1)*(y3-y1))-(long)((y2-y1)*(x3-x1)))>=0) {
			return true;
		}
		return false;
	}
}
