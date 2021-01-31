import java.io.*;
import java.util.*;
public class snakes {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("snakes.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snakes.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken())+1;
		int[][][] dp = new int[N][K][N];
		int[][] find = new int[N][K];
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		for(int i=0;i<N; i++) {
			for(int j=0; j<K; j++) {
				int min =Integer.MAX_VALUE;
				for(int k =0; k<N; k++) {
					int dif = arr[k] - arr[i];
					if(dif<0){
						dp[i][j][k] = Integer.MAX_VALUE;
					}
					else {
						if(i==0&&j==0) {
							dp[i][j][k] = dif;
							min = Math.min(min, dp[i][j][k]);
							continue;
						}
						if(i==0) {
							dp[i][j][k] = find[i][j-1] +dif;
							min = Math.min(min, dp[i][j][k]);
							continue;
						}
						if(j==0) {
							dp[i][j][k] = dp[i-1][j][k] +dif;
							if(dp[i][j][k] <0)
								dp[i][j][k] = Integer.MAX_VALUE;
							min = Math.min(min, dp[i][j][k]);
							continue;
						}
						dp[i][j][k] = Math.min(find[i-1][j-1] +dif, dp[i-1][j][k] + dif);
						if(dp[i][j][k] <0)
							dp[i][j][k] = find[i-1][j-1] +dif;
						min = Math.min(min, dp[i][j][k]);
					}
				}
				find[i][j] = min;
			}
		}
		int ans =Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			ans = Math.min(ans, dp[N-1][K-1][i]);
		}
		out.println(ans);
		out.close();
	}

}
