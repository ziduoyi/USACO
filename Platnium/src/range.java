import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class range {
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		while(line==null||line.trim().length()==0)
			line = br.readLine();
		int N = Integer.parseInt(line);
		int[][] arr = new int[N][N]; //false=0, true=1
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) 
				arr[i][j] = s.charAt(j)-'0';
		}
		int[][] dp = new int[N+1][N+1];
		int[] totals = new int[N+1];
		for(int i=N-1; i>-1; i--) {
			for(int j=N-1; j>-1; j--) { 
				if(arr[i][j]==1) 
					dp[i][j] = 1+Math.min(dp[i+1][j], Math.min(dp[i][j+1], dp[i+1][j+1]));
				totals[dp[i][j]]++;
			}
		}
		int[] ans = new int[N+2];
		for(int i=N; i>1; i--) 
			ans[i] = ans[i+1]+totals[i];
		for(int i=2; i<=N; i++) {
			if(ans[i] ==0)
				break;
			System.out.print(i+" ");
			System.out.println(ans[i]);
		}
	}
}