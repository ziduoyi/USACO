import java.io.*;
import java.util.*;
public class cowngress {
	static ArrayList<Integer>[] edges;
	static LinkedList<Integer> list;
	static Stack<Integer> stack;//default
	static int[] scc; //default
	static int[] in; // filled with -1
	static int[] low; // filled with -1
  	static boolean[] instack; //filled with false
  	static boolean[] visit;
	static int time; //start at 1
	static int num; //default
	static int N; //input
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		edges = new ArrayList[2*N];
		for(int i=0; i<2*N; i++) 
			edges[i] = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a1 = Integer.parseInt(st.nextToken())-1;
			String a2 = st.nextToken();
			int b1 = Integer.parseInt(st.nextToken())-1;
			String b2 = st.nextToken();
			if(a2.equals("Y")) {
				if(b2.equals("Y")) {
					edges[a1+N].add(b1);
					edges[b1+N].add(a1);
				}
				else {
					edges[a1+N].add(b1+N);
					edges[b1].add(a1);
				}
			}
			else {
				if(b2.equals("Y")) {
					edges[a1].add(b1);
					edges[b1+N].add(a1+N);
				}
				else {
					edges[a1].add(b1+N);
					edges[b1].add(a1+N);

				}
			}
		}
		stack = new Stack<>();
		scc = new int[2*N];
		in = new int[2*N];
		low = new int[2*N];
		instack = new boolean[2*N];
		time = 1;
		num = 0;
		Arrays.fill(in, -1);
		Arrays.fill(low, -1);
		tarjan();
		list = new LinkedList<>();
		visit = new boolean[2*N];
		String ans = "";
		for(int i=0; i<N; i++) {
			if(scc[i]==scc[i+N]) {
				ans = "IMPOSSIBLE";
				break;
			}
			boolean b1 = ispath(i,i+N);
			boolean b2 = ispath(i+N, i);
			if(b1)
				ans += "N";
			if(b2)
				ans +="Y";
			if(!b1&&!b2)
				ans += "?";
		}
		System.out.println(ans);
	}
	static boolean ispath(int start, int end) {
		list.add(start);
		visit[start] = true;
		while(!list.isEmpty()) {
			int node = list.removeFirst();
			ArrayList<Integer> al = edges[node];
			int s = al.size();
			for(int i=0; i<s; i++) {
				int tar = al.get(i);
				if(visit[tar]==false) {
					visit[tar] = true;
					list.add(tar);
					if(tar==end) {
						Arrays.fill(visit, false);
						list.clear();
						return true;
					}
				}
			}
		}
		Arrays.fill(visit, false);
		return false;
	}
	static void tarjan() {
		for(int i=0; i<2*N; i++)
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

}
