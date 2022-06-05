import java.io.*;
import java.util.*;

public class energy {
	static int[] dp;
	static ArrayList<Integer>[] edges;
	static int L;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		edges = new ArrayList[L];
		for(int i=0; i<L; i++)edges[i] = new ArrayList<>();
		dp = new int[L+1];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[a].add(b);
			edges[a].add(c);
		}
		Arrays.fill(dp, 1000000000);
		dp[0] =0;
		recursion(0);
		if(dp[L]!=1000000000)
			System.out.println(dp[L]);
		else
			System.out.println("Impossible");
	}
	static void recursion(int node) {
		ArrayList<Integer> al = edges[node];
		int s = al.size();
		for(int i=0; i<s; i+=2) {
			int tar = al.get(i);
			int cost = al.get(i+1);
			if(dp[node]+cost<dp[tar]) {
				dp[tar] = dp[node]+cost;
				if(tar!=L)
					recursion(tar);
			}
		}
		if(node!=0)
			if(dp[node]<dp[node-1]) {
				dp[node-1] = dp[node];
				recursion(node-1);
			}
	}
}
