import java.util.*;
public class Treasure_Chest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int[] presum = new int[N+1];
		int[] dp = new int[N];
		int[] arr = new int[N];
		for(int i=1; i<N+1; i++) {
			dp[i-1] = scanner.nextInt();
			arr[i-1]= dp[i-1];
			presum[i] = presum[i-1] + dp[i-1];
		}
		for(int i=1; i<N; i++) {
			int[] save = new int[N];
			for(int j=0; j+i<N; j++) 
				save[j] = presum[j+i+1]-presum[j]-Math.min(dp[j], dp[j+1]);
			dp = save.clone();
		}
		System.out.println(dp[0]);
	}

}
