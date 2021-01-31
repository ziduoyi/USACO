import java.util.*;
public class baleshare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int[] arr = new int[N];
		int sum =0;
		for(int i=0; i<N; i++) {
			arr[i] = scanner.nextInt();
			sum +=arr[i];
		}
		boolean[][] dp = new boolean[sum+1][sum+1];
		dp[0][0] = true;
		int min =Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			boolean[][] save = new boolean[sum+1][sum+1];
			for(int j=0; j< sum/2+1+1; j++) {
				for(int k=0; k<= Math.min(sum/3+1+1,j); k++) {
					save[j][k] = dp[j][k];
					if(j-arr[i]>=0) {
						if(dp[j-arr[i]][k] ==true) {
							save[j][k] = true;
						}
					}
					if(k-arr[i]>=0) {
						if(dp[j][k-arr[i]] ==true) {
							save[j][k] = true;
						}
					}
				}
			}
			for(int j=0; j<sum+1; j++)
				dp[j]  = save[j].clone();
		}
		for(int i=0; i<sum+1; i++) {
			for(int j=0; j<sum+1; j++) {
				if(dp[i][j] ==true&&i>=j&&sum-i-j>=i)
					min = Math.min(min, sum-i-j);
			}
		}
		System.out.println(min);
	}

}
