import java.io.*;
import java.util.*;
public class scc {
	static ArrayList<Integer>[] edges;
	static Stack<Integer> stack;
	static int[] scc;
	static int[] in;
	static int[] low;
	static boolean[] instack;
	static int time;
	static int num;
	static int N;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		edges = new ArrayList[N];
		for(int i=0; i<N; i++)
			edges[i] = new ArrayList<>();
		for(int i=0; i<N; i++) {
			int x = Integer.parseInt(br.readLine())-1;
			edges[i].add(x);
		}
		stack = new Stack<>();
		scc = new int[N];
		in = new int[N];
		low = new int[N];
		Arrays.fill(in, -1);
		Arrays.fill(low, -1);
		instack = new boolean[N];
		time = 1;
		num =0;
		tarjen();
		Set<Integer> set = new HashSet<>();
		for(int i=0; i<N; i++)
			set.add(scc[i]);
		System.out.println(set.size());
	}
	static void tarjen() {
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
				low[node] = Math.min(low[node], in[tar]);	
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

}
