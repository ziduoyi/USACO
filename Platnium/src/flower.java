import java.io.*;
import java.util.*;
public class flower {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int F = scanner.nextInt();
		int V = scanner.nextInt();
		int[][] arr = new int[F][V];
		for(int i=0; i<F; i++) {
			for(int j=0; j<V; j++) {
				arr[i][j] = scanner.nextInt();
			}
		}
		int[][] dp = new int[F][V];
		dp[0][0] = arr[0][0];
		for(int i=1;i<V; i++) 
			dp[0][i] = Math.max(dp[0][i-1], arr[0][i]);
		for(int i =1; i<F; i++) {
			for(int j=0; j<i; j++) {
				dp[i][j] = -1000000000;
			}
		}
		for(int i=1; i<F; i++) {
			for(int j=i; j<V; j++) {
				dp[i][j] = dp[i][j-1];
				for(int k=j-1; k>=0; k--) {
					dp[i][j] = Math.max(dp[i][j], dp[i-1][k]+arr[i][j]);
				}
			}
		}
		System.out.println(dp[F-1][V-1]);
	}

}
