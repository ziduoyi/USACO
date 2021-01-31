import java.io.*;
import java.util.*;
public class rpaths {
	static ArrayList<Integer>[] edges;
	static Stack<Integer> stack;
	static Map<Integer, HashSet<Integer>> scc;
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
		stack = new Stack<>();
		scc = new HashMap<>();
		in = new int[N];
		low = new int[N];
		Arrays.fill(in, -1);
		Arrays.fill(low, -1);
		instack = new boolean[N];
		num = 0;
		time = 1;
		dfs(0,-1);
		int child = 0;
		for(int i=0; i<num; i++) {
			HashSet<Integer> set = scc.get(i);
			int ct = 0;
			for(int node: set) {
				ArrayList<Integer> al = edges[node];
				int s = al.size();
				for(int j=0; j<s; j+=2) {
					int tar = al.get(j);
					if(!set.contains(tar)) 
						ct++;					
				}
			}
			if(ct<=1)
				child++;
		}
		if(child==1)
			child--;
		System.out.println((int)((double)child/2+0.5));
		
	}
	static void dfs(int node, int last) {
		low[node] = in[node] = time++;
		instack[node] = true;
		stack.push(node);
		ArrayList<Integer> al = edges[node];
		int s = al.size();
		for(int i=0; i<s; i+=2) {
			int tar = al.get(i);
			int curr = al.get(i+1);
			if(curr==last)
				continue;
			if(in[tar] ==-1) {
				dfs(tar, curr);
				low[node] = Math.min(low[node], low[tar]);
			}
			else
				low[node] = Math.min(low[node], in[tar]);
		}
		if(low[node] == in[node]) {
			HashSet<Integer> a = new HashSet<>();
			while(true) {
				int u = stack.pop();
				a.add(u);
				instack[u] = false;
				if(u==node)break;
			}
			scc.put(num, a);
			num++;
		}
	}

}
