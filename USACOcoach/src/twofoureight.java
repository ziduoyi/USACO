import java.io.*;
import java.util.*;
public class twofoureight {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader (new FileReader("248.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("248.out")));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		int[][] dp = new int[N][N];
		
		for(int i=0; i<N; i++) {
			dp[i][i] = arr[i];
		}
		for(int i=1; i<N; i++) {
			for(int j=0; j+i<N; j++) {
				boolean b =true;
				for(int k = i+j-1; k>-1; k--) {
					int curr= Math.max(dp[k][j], dp[i+j][k+1]);
					if(dp[k][j]==dp[i+j][k+1]) {
						curr++;
						b=false;
						dp[j+i][j] = Math.max(dp[j+i][j], curr);
					}
				}
				if(b==true)
					dp[j+i][j] =-1000000;
			}
		}
		int max=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				max = Math.max(max, dp[i][j]);
			}
		}
		out.println(max);
		out.close();
	}

}
