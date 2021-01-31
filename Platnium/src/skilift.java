import java.io.*;
import java.util.*;
public class skilift {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int K = scanner.nextInt();
		int[] arr = new int[N];//1,000,000,000 * 1,000,000,000 = long
		for(int i=0; i<N; i++)
			arr[i] = scanner.nextInt();
		int[] dp = new int[N];
		dp[0] =1;
		for(int i=1; i<N; i++) {
			dp[i] = dp[i-1]+1;
			int pre =i-1;
			for(int j= i-2; j>=Math.max(i-K,0); j--) {
				if(ccw(j, arr[j], pre, arr[pre], i, arr[i])) {
					pre = j;
					dp[i] = Math.min(dp[i], dp[j]+1);
				}
			}
		}
		System.out.println(dp[N-1]);
	}
	static boolean ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
		if((long)((long)((x2-x1)*(y3-y1))-(long)((y2-y1)*(x3-x1)))>=0) {
			return true;
		}
		return false;
	}

}
