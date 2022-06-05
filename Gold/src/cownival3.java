import java.io.*;
import java.util.*;
public class cownival3 {
	static int[][][] dp;
	static int[] arr;
	static ArrayList<Integer>[] edges;
	@SuppressWarnings({ "unchecked", "resource" })
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownival3.out")));
		BufferedReader br = new BufferedReader(new FileReader("cownival3.in"));
		int N = Integer.parseInt(br.readLine());
		edges = new ArrayList[N];
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;  i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			edges[i] = new ArrayList<>();
		}
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			edges[a].add(b);
			edges[b].add(a);
		}
		dp = new int[N][2][2]; // off on, no set or set
		for(int i=0; i<N; i++)
			for(int j=0; j<2; j++)
				Arrays.fill(dp[i][j], 1000000000);
		recursion(0,-1);
		int ans = Math.min(dp[0][0][0], dp[0][0][1]);
		if(ans == 1000000000)
			out.println("Impossible");
		else
			out.println(ans);
		out.close();
	}
	static void recursion(int node, int last) {
		ArrayList<Integer> al = edges[node];
		int s = al.size();
		if(s==1) {
			dp[node][arr[node]][0] = 0;
			dp[node][1-arr[node]][1] = 1;
			return;
		}
		int tar1 = -1;
		int tar2 = -1;
		boolean b = false;
		for(int i=0; i<s; i++) {
			int tar = al.get(i);
			if(tar==last)continue;
			recursion(tar, node);
			if(!b) {
				tar1 = tar;
				b = true;
			}
			else
				tar2 = tar;
		}
		if(tar2==-1) {
			dp[node][arr[node]][0] = dp[tar1][0][0];
			dp[node][arr[node]][1] = Math.min(1000000000, dp[tar1][1][1]+1);
			dp[node][1-arr[node]][0] = dp[tar1][0][1];
			dp[node][1-arr[node]][1] = Math.min(1000000000, dp[tar1][1][0]+1);
		}
		else {
			dp[node][arr[node]][0] = Math.min(1000000000, Math.min(dp[tar1][0][0] + dp[tar2][0][0],dp[tar1][0][1]+dp[tar2][0][1]));
			dp[node][arr[node]][1] = Math.min(1000000000, Math.min(dp[tar1][1][0]+dp[tar2][1][1]+1,dp[tar1][1][1]+dp[tar2][1][0]+1));
			dp[node][1-arr[node]][0] = Math.min(1000000000, Math.min(dp[tar1][0][0]+dp[tar2][0][1],dp[tar1][0][1]+dp[tar2][0][0]));
			dp[node][1-arr[node]][1] = Math.min(1000000000, Math.min(dp[tar1][1][0]+dp[tar2][1][0]+1,dp[tar1][1][1]+dp[tar2][1][1]+1));
		}
	}
}
