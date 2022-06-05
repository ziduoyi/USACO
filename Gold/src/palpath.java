import java.io.*;
import java.util.*;

public class palpath {
	public static void main(String[] args)throws IOException  {
		// TODO Auto-generated method stub
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palpath.out")));
		BufferedReader br = new BufferedReader(new FileReader("palpath.in"));
		int N = Integer.parseInt(br.readLine());
		char[][] arr = new char[N][N];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++)
				arr[i][j] = str.charAt(j);	
		}
		long mod = 1000000007;
		long[][] dp = new long[N][N];
		for(int i=0; i<N; i++) 
			dp[i][i] = 1;
		for(int i=N-1; i>=1; i--) {
			long[][] next = new long[N][N];
			for(int j=0; j<N; j++) {
				int row1 = j;
				int col1 = (i-1-j);
				if(col1<0)continue;
				for(int k=0; k<N; k++) {
					int row2 = k;
					int col2 = 2*N-i-row2-1;
					if(col2>=N)continue;
					if(arr[row1][col1]!=arr[row2][col2]) continue;
					next[row1][row2] += dp[row1][row2];
			        if(row1+1 < N) next[row1][row2] += dp[row1+1][row2];
			        if(row2-1 >= 0) next[row1][row2] += dp[row1][row2-1];
			        if(row1+1 < N && row2-1 >= 0) next[row1][row2] += dp[row1+1][row2-1];
			        next[row1][row2] %= mod;
				}
			}
			dp = next;
		}
		out.println(dp[0][N-1]);
		out.close();
	}
}
