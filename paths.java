import java.util.*;
import java.io.*;
public class paths {
	static ArrayList<Integer>[] edges;
	static ArrayList<Integer>[] items;
	static int[] visit; 
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	br.readLine();
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int m = Integer.parseInt(st.nextToken());
	    	edges = new ArrayList[n];
	    	for(int j=0; j<n; j++)edges[j] = new ArrayList<>();
	    	for(int j=0; j<m; j++) {
	    		st = new StringTokenizer(br.readLine());
	    		int a = Integer.parseInt(st.nextToken())-1;
	    		int b = Integer.parseInt(st.nextToken())-1;
	    		edges[a].add(b);
	    	}
	    	visit = new int[n];
	    	items = new ArrayList[2];
	    	items[0] = new ArrayList<>();
	    	items[1] = new ArrayList<>();
	    	recursion(0);
	    	for(int j=0; j<2; j++) {
	    		int ini = 2;
	    		if(j==1) ini = -1;
	    		for(int val: items[j])
	    			dfs(val, ini);
	    	}
	    	for(int val: visit)
	    		out.write(val+" ");
	    	out.write("\n");
	    }
    	out.flush();
    	out.close();
	}
	static void dfs(int node, int fill) {
		visit[node] = fill;
		for(int tar: edges[node]) {
			if(visit[tar]!=fill) {
				dfs(tar, fill);
			}
		}
	}
	static void recursion(int node) {
		visit[node] = 4;
		for(int tar: edges[node]) {
			if(visit[tar] ==0 )
				recursion(tar);
			else if(visit[tar] ==4) {
				visit[node] = 3;
				items[1].add(node);
				return;
			}
			else if(visit[tar]==1) {
				visit[tar] =5;
				items[0].add(tar);
			}
		}
		visit[node] = 1;
	}


}
