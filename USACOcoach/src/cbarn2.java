import java.io.*;
import java.util.*;
public class cbarn2 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("cbarn2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn2.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		long[][][] dp = new long[N][N][K];
		long ans  =Integer.MAX_VALUE;
		long[][] min = new long[N][K];
		for(int i=0; i<N; i++) {
			for(int j=1; j<N; j++) {
				dp[j][0][0] = dp[j-1][0][0] + arr[j]*j;
				min[j][0] = dp[j][0][0];
			}
			for(int j=1; j<K; j++) {
				for(int k=j; k<N; k++) {
					long curr =Integer.MAX_VALUE;
					for(int l=0; l<=k; l++) {
						if(l==k) {
							dp[k][l][j] = min[k-1][j-1];
							curr = Math.min(curr, dp[k][l][j]);

						}
						else {
							dp[k][l][j] = dp[k-1][l][j] + arr[k]*(k-l);
							curr = Math.min(curr, dp[k][l][j]);
						}
					}
					min[k][j] = curr;
				}
			}
			ans= Math.min(ans, min[N-1][K-1]);
			int s1 = arr[0];
			for(int j=1; j<N; j++) {
				int s2 = arr[j];
				arr[j] = s1;
				s1 = s2;
			}
			arr[0] = s1;
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++) {
					for(int l =0; l<K; l++) {
						dp[j][k][l]=0;
					}
				}
			}
			for(int j=0; j<N; j++) {
				for(int k =0; k<K; k++) {
					min[j][k] =0;
				}
			}
		}
		out.println(ans);
		out.close();
	}
}
