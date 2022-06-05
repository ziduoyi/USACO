import java.io.*;
import java.util.*;

public class hungry {
	static ArrayList<Integer>[] edges;
	static int[][] dp;
	static int[] values;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br= new BufferedReader(new FileReader("hungry.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("hungry.out")));
		int N = Integer.parseInt(br.readLine());
		values = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
			values[i] = Integer.parseInt(st.nextToken());
		edges = new ArrayList[N];
		for(int i=0; i<N; i++)
			edges[i] = new ArrayList<>();
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			edges[a].add(b);
			edges[b].add(a);
		}
		dp = new int[N][2]; //0 no   1 yes
		compute(0, -1);
		out.println(Math.max(dp[0][0], dp[0][1]));
		out.close();
	}
	static void compute(int node, int par) {
		ArrayList<Integer> al = edges[node];
		int  s = al.size();
		if(s==1&&node!=0) {
			dp[node][1] = values[node];
			return;
		}
		dp[node][1] = values[node];
		for(int i=0; i<s; i++) {
			int tar = al.get(i);
			if(tar==par)continue;
			compute(tar, node);
			dp[node][1] += dp[tar][0];
			dp[node][0] += Math.max(dp[tar][0], dp[tar][1]);
		}
	}
}
