import java.io.*;
import java.util.*;
public class xoinc {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		int[][] dp = new int[N+1][N];
		for(int i=0; i<N; i++)
			dp[N-1][i] = arr[N-1];
		
		int[] sum = new int[N];
		sum[N-1] = arr[N-1];
		for(int i=N-2; i>-1; i--) 
			sum[i]= sum[i+1]+ arr[i];
		for(int i=0; i<N; i++) 
			dp[N-2][i] = sum[N-2];
		
		for(int i=N-3; i>-1; i--){
			for(int j=0; j<N; j++) {
				int a = i+2*j+1;
				int b = i+2*j +2;
				if(j==0) {
					if(a<=N) {
						if(b<=N) 
							dp[i][j] =  sum[i] - Math.min(dp[a][a-i-1], dp[b][b-i-1]);
						else
							dp[i][j] =  sum[i] -dp[a][a-i-1];
					}
					continue;
				}
				if(a<=N) {
					if(b<=N) 
						dp[i][j] = Math.max(dp[i][j-1], sum[i] - Math.min(dp[a][a-i-1], dp[b][b-i-1]));
					else
						dp[i][j] = Math.max(dp[i][j-1], sum[i] -dp[a][a-i-1]);
				}
				else
					dp[i][j] = dp[i][j-1];
			}
		}
		System.out.println(dp[0][0]);
	}

}
