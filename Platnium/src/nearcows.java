import java.io.*;
import java.util.*;
public class nearcows {
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] edges = new ArrayList[N];
		for(int i=0; i<N; i++)
			edges[i] = new ArrayList<>();
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			edges[a].add(b);
			edges[b].add(a);
		}
		int[][] dp = new int[N][3];
		for(int i=0; i<N; i++) 
			dp[i][0] = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			ArrayList<Integer> al = edges[i];
			int s = al.size();
			dp[i][1] = dp[i][0];
			for(int j=0; j<s; j++) {
				int tar = al.get(j);
				dp[i][1] +=dp[tar][0];
			}
		}
		for(int i=2; i<=K; i++) {
			for(int j=0; j<N; j++) {
				ArrayList<Integer> al = edges[j];
				int s = al.size();
				dp[j][i%3] = -1*(s-1)*dp[j][(i-2)%3];
				for(int k=0; k<s; k++) {
					int tar = al.get(k);
					dp[j][i%3] +=dp[tar][(i-1)%3];
				}
			}
		}
		for(int i=0; i<N; i++)
			System.out.println(dp[i][K%3]);

	}
}
