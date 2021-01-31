import java.io.*;
import java.util.*;
public class cowrun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		int[] presum = new int[N+1];
		for(int i=1; i<N+1; i++) {
			presum[i] =presum[i-1]+ scanner.nextInt();
		}
		int[] dp = new int[N+1];
		for(int i=2; i<N+1; i++) {
			dp[i] = dp[i-1];
			for(int j=1; j<=M; j++) {
				if(i-(2*j)<0)
					break;
				dp[i] = Math.max(dp[i], dp[i-(2*j)]+presum[i-j]-presum[i-2*j]);
			}
		}
		System.out.println(dp[N]);
	}

}
