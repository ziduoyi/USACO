import java.io.*;
import java.util.*;

public class goldcactus {
	static ArrayList<int[]>[] edges;//default
	static int[] stack;//default
	static int[] bcc; //default
	static int[] pre; // filled with -1
	static int[] low; // filled with -1
	static int[] size;
  	static boolean[] instack; //filled with false
  	static boolean[] visitnode;
  	static boolean[] visitedge;
	static int time; //start at 1
	static int num; //default
	static int N; //input
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	N = Integer.parseInt(st.nextToken());
	    	int M = Integer.parseInt(st.nextToken());
	    	edges = new ArrayList[N];
	    	for(int j=0; j<N; j++) edges[j] = new ArrayList<>();
	    	stack = new int[M+1];
	    	bcc = new int[M];
	    	pre = new int[N];
	    	low = new int[N];
	    	size = new int[M];
	    	instack = new boolean[N];
	    	visitnode = new boolean[N];
	    	visitedge = new boolean[M];
	    	time = 1;
	    	num = 0;
	    	for(int j=0; j<M; j++) {
	    		st = new StringTokenizer(br.readLine());
	    		int a = Integer.parseInt(st.nextToken())-1;
	    		int b = Integer.parseInt(st.nextToken())-1;
	    		edges[a].add(new int[] {b, j});
	    		edges[b].add(new int[] {a, j});
	    	}
	    	for(int j=0; j<N; j++)
	    		if(!visitnode[j])
	    			dfs(j);
	    	int ans = 1;
	    	for(int j=0; j<M; j++) {
	    		ans *= Math.max(1,size[j]);
	    		ans%=1007;
	    	}
	    	out.write("Case #"+(i+1)+": "+ans+"\n\n");
	    }
	    out.flush();
	    out.close();
	}
	static void dfs(int node) {
		visitnode[node] = true;
		low[node] = pre[node] = time++;
		ArrayList<int[]> al = edges[node];
		int s = al.size();
		for(int i=0; i<s; i++) {
			int[] arr = al.get(i);
			if(!visitedge[arr[1]]) {
				visitedge[arr[1]] = true;
				stack[++stack[0]] = arr[1];
				int tar = arr[0];
				if(!visitnode[tar]) {
					dfs(tar);
					if(low[tar] >= pre[node]) {
						int curr = -1;
						while(curr != arr[1]) {
							curr = stack[stack[0]--];
							bcc[curr] = num;
							size[num]++;
						}
						num++;
					}
				}
				if(low[arr[0]] < low[node])
					low[node] = low[arr[0]];
			}	
		}
	}
}
