import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.StringTokenizer;

public class barnpainting {
	
	static ArrayList<Integer>[] edges;
	static int[] color;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("barnpainting.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barnpainting.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N =Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());

		edges = new ArrayList[N+1];
		for(int i=1;i<=N;i++)
			edges[i]=new ArrayList<>();
		
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			edges[r].add(c);
			edges[c].add(r);
		}
		
		color = new int[N+1];
		for(int i=0;i<K;i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			color[a]=b;
		}
	
		boolean[] visited = new boolean[N+1];
		int res = 0;
		dp=new int[N+1][3];
		for(int c=1;c<=3;c++){
			boolean[] v = visited.clone();
			dfs(v,1,c);
			//res = (res + dfs(v,1,1)) % 1000000007;
		}
		System.out.println(res);
		out.println(res);
		out.close();
	}

	static void dfs(boolean visited[], int r,int c){
		int res = 0;
		visited[r]=true;
		
		if(color[r]!=0 && color[r]!=c) return;
		for(int x : edges[r]){
			if(color[x]==c) return;
		}
		boolean isLeaf = true;
		for(int x : edges[r]){
			if(!visited[x]){
				isLeaf=false;
				break;
			}
		}
		if(isLeaf){
			dp[r][c]++;
		}
		
		for(int i=1;i<=3;i++){
			if(c==i) continue;
			int temp = 0;
			for(int x : edges[r]){
				if(!visited[x])
					dfs(visited,x,i);
			}
		}
		//for()
		
	}
}
