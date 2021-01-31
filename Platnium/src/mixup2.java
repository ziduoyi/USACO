import java.util.*;
import java.io.*;
public class mixup2 {
	static long[][] dp;
	static int[] arr;
	static int N;
	static int K;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		Arrays.sort(arr);
		int len = 1<<N;
		dp = new long[len][N];
		for(int i=0; i<len; i++)
			Arrays.fill(dp[i], -1);
		for(int i=0; i<N; i++) 
			dp[1<<i][i] = 1;
		
		long ans = 0;
		for(int i=0; i<N; i++)
			recursion(len-1,i);
		for(int i=0; i<N; i++)
			ans += dp[(1<<N)-1][i];
		System.out.println(ans);
	}
	static void recursion(int curr, int last) {
		dp[curr][last] = 0;
		for(int i=0; i<N; i++) {
			if(i==last)continue;
			if(last==-1|| (arr[i]<arr[last]&&arr[i]+K<arr[last])||(arr[i]>arr[last]&&arr[i]-K>arr[last])) {
				if((curr^(1<<i))<curr) {
					if(dp[curr^(1<<last)][i] == -1)
						recursion(curr^(1<<last),i);
					dp[curr][last] += dp[curr^(1<<last)][i];
				}
			}
		}
	}
}
