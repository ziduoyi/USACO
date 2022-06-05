import java.util.*;
import java.io.*;

public class cbarn2 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("cbarn2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn2.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][][] dp = new int[N][N][K];
		int[] arr = new int[N];
		int ans = 1000000000;
		for(int i=0; i<N; i++)arr[i] = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++)
				for(int k=0; k<N; k++)
					Arrays.fill(dp[j][k], 1000000000);
			dp[0][0][0] = 0;
			for(int j=0; j<N-1; j++) {
				for(int k=0; k<=j; k++) {
					for(int l=0; l<K; l++) {
						if(dp[j][k][l]==1000000000)continue;
						dp[j+1][k][l] = Math.min(dp[j+1][k][l], dp[j][k][l]+arr[j+1]*(j+1-k));
						if(l!=K-1)
							dp[j+1][j+1][l+1] = Math.min(dp[j+1][j+1][l+1], dp[j][k][l]);
					}
				}
			}
			for(int j=0; j<N; j++)
				ans = Math.min(ans, dp[N-1][j][K-1]); 
			int save = arr[N-1];
			for(int j=N-2; j>-1; j--) 
				arr[j+1] = arr[j]; 
			arr[0] = save;
		}
		out.println(ans);
		out.close();
	}

}
