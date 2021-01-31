import java.util.*;
import java.io.*;
public class tsp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] edges = new int[N][N];
		for(int i=0; i<N; i++)
			Arrays.fill(edges[i], 1000000000);
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			edges[a][b] = Math.min(edges[a][b], c);
			edges[b][a] = Math.min(edges[a][b], c);
		}
		int[] times = new int[N+1];
		times[0] = 1;
		for(int i=1; i<=N; i++)
			times[i] = times[i-1] *2;
		int[][] dp = new int[times[N]][N];
		for(int i=1; i<times[N]; i++)
			Arrays.fill(dp[i], 1000000);
		for(int i=0; i<times[N]-1; i++) {
			for(int j=0; j<N; j++) {
				int next = times[j] + i;
				if(next>=times[N])
					break;
				int[] al = edges[j];
				for(int k=0; k<N; k++) {
					int tar = k;
					int cost = al[k];
					if((next|times[tar]) != next) 
						dp[next][tar] = Math.min(dp[next][tar], dp[i][j] + cost);
				}
			}
		}
		int ans = 1000000;
		for(int i=0; i<N; i++)
			ans = Math.min(ans, dp[times[N]-1-times[i]][i]);
		System.out.println(ans);
	}

}
