import java.util.*;
public class bdsum {
	static int[] ans;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int total = scanner.nextInt();
		ans = new int[N];
		permutation(N, total, 0, new boolean[N], new int[N]);
		for(int i=0; i<N; i++)
			System.out.print(ans[i] +" ");
	}
	static void permutation(int N, int total, int curr, boolean[] seen, int[] arr) {
		for(int i=1; i<=N; i++) {
			if(seen[i-1]==true)
				continue;
			arr[curr] =i;
			seen[i-1] = true;
			if(curr!=N-1) {
				permutation(N, total, curr+1, seen.clone(), arr);
				seen[i-1] = false;
			}
			else {
				int[] dp = new int[N];
				dp[0] = 1;
				for(int j=1; j<N; j++) {
					dp[j] = dp[j-1] * (N-j);
					dp[j] /= j;
				}
				int sum = 0;
				for(int j=0; j<N; j++)
					sum+=dp[j]*arr[j];
				if(sum==total) {
					if(ans[0] ==0)
						ans = arr.clone();
					else {
						for(int j=0; j<N; j++) {
							if(ans[j] > arr[j]) {
								ans = arr.clone();
							}
							else if(ans[j]<arr[j]){
								break;
							}
						}
					}
				}
			}
		}
	}

}
