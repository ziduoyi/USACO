import java.io.*;
import java.util.*;

public class schlnet {
	static ArrayList<Integer>[] edges;//default
	static Stack<Integer> stack;//default
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
		for(int i=0; i<N; i++) {
			edges[i] = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			while(a!=-1) {
				edges[i].add(a);
				a = Integer.parseInt(st.nextToken())-1;
			}
		}
		stack = new Stack<>();
		scc = new int[N];
		in = new int[N];
		Arrays.fill(in, -1);
		low = new int[N];
		Arrays.fill(low, -1);
		instack = new boolean[N];
		time = 1;
		num = 1;
		tarjan();
		ArrayList<Integer>[] paths = new ArrayList[num];
		for(int i=0; i<num; i++)
			paths[i] = new ArrayList<>();
		for(int i=0; i<N; i++) {
			ArrayList<Integer> al = edges[i];
			int s = al.size();
			for(int j=0; j<s; j++) {
				int tar = al.get(j);
				if(scc[i]!=scc[tar])
					paths[scc[i]].add(scc[tar]);
			}
		}
		HashSet<Integer> set = new HashSet<>();
		for(int i=0; i<num; i++)
			set.addAll(paths[i]);
		System.out.println(num-1-set.size());
		int child = 0;
		for(int i=1; i<num; i++) {
			if(paths[i].size()==0&&set.contains(i))
				child++;
		}
		
		System.out.println(Math.max(num-1-set.size()==1?0:num-1-set.size(),child));
		
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
}
