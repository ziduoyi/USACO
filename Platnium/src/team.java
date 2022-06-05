import java.io.*;
import java.util.*;
public class team {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("team.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("team.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int mod = 1000000009;
		int[] john = new int[N];
		int[] paul = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			john[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++)
			paul[i] = Integer.parseInt(st.nextToken());
		int[][][] dp = new int[N][M][K];
		int[][] sum = new int[N][M];
		Arrays.sort(john);
		Arrays.sort(paul);
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(john[i]>paul[j]) {
					dp[i][j][0] = 1;
					sum[i][j] = 1;
				}
			}
		}
		for(int i=0; i<N; i++)
			for(int j=0; j<M-1; j++)
				sum[i][j+1]+=sum[i][j];
		for(int i=0; i<M; i++)
			for(int j=0; j<N-1; j++)
				sum[j+1][i]+=sum[j][i];
		for(int k= 1; k<K; k++) {
			for(int i=k; i<N; i++) 
				for(int j=k; j<M; j++) 
					if(john[i]>paul[j])
						dp[i][j][k] = sum[i-1][j-1];
			for(int i=0; i<N; i++) 
				for(int j=0; j<M; j++) 
					sum[i][j] = 0;	
			for(int i=k; i<N; i++) 
				for(int j=k; j<M; j++) 
					sum[i][j] = dp[i][j][k];	
			
			for(int i=0; i<N; i++)
				for(int j=0; j<M-1; j++) {
					sum[i][j+1]+=sum[i][j];
					sum[i][j+1]%=mod;
				}
			for(int i=0; i<M; i++)
				for(int j=0; j<N-1; j++) {
					sum[j+1][i]+=sum[j][i];
					sum[j+1][i]%=mod;
				}
			
		}
		int ans = 0;
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				if(john[i]>paul[j]) {
					ans += dp[i][j][K-1];
					ans%=mod;
				}
		out.println(ans%mod);
		out.close();
	}

}
