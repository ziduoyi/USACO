import java.io.*;
import java.util.*;

public class lighting {
	static ArrayList<Integer>[] edges;//default
	static int[] stack;//default use stack array trick
	static int[] scc; //default
	static int[] in; // filled with -1
	static int[] low; // filled with -1
  	static boolean[] instack; //filled with false
	static int time; //start at 1
	static int num; //default
	static int N; //input
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int t = Integer.parseInt(br.readLine());
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int m = Integer.parseInt(st.nextToken());
	    	edges = new ArrayList[n];
	    	stack = new int[20*n];
	    	scc = new int[n];
	    	in = new int[n];
	    	low = new int[n];
	    	Arrays.fill(in, -1);
	    	Arrays.fill(low, -1);
	    	instack = new boolean[n];
	    	time = 1;
	    	num = 0;
	    	N = n;
	    	for(int j=0; j<n; j++)edges[j] = new ArrayList<>();
	    	for(int j=0; j<m; j++) {
	    		st = new StringTokenizer(br.readLine());
	    		int a = Integer.parseInt(st.nextToken())-1;
	    		int b = Integer.parseInt(st.nextToken())-1;
	    		edges[a].add(b);
	    	}
			for(int j=0; j<N; j++)
				if(in[j] == -1)
					dfs(j);
			HashSet<Integer>[] paths = new HashSet[num];
	    	HashSet<Integer>[] nodes = new HashSet[num];
	    	HashSet<Integer> set = new HashSet<>();
	    	for(int j=0; j<num; j++) {
	    		paths[j] = new HashSet<>();
	    		nodes[j] = new HashSet<>();
	    		set.add(j);
	    	}
	    	for(int j=0; j<N; j++) 
	    		nodes[scc[j]].add(j);
	    	for(int j=0; j<num; j++) {
	    		for(int node: nodes[j]) {
	    			for(int tar: edges[node]) {
	    				if(!nodes[j].contains(tar)) {
	    					paths[j].add(scc[tar]);
	    					if(set.contains(scc[tar]))set.remove(scc[tar]);
	    				}
	    			}
	    		}
	    	}
	    	out.write("Case "+(i+1)+": "+set.size()+"\n");
	    }
	    out.flush();
	    out.close();
	}
	static void dfs(int node) {
		low[node] = in[node] = time++;
		stack[++stack[0]]=node;
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
				int u = stack[stack[0]--];
				scc[u] = num;
				instack[u] = false;
				if(u==node)break;
			}
			num++;
		}
	}
}
