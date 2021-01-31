import java.io.*;
import java.util.*;
public class land {
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] arr = new int[M];
		int[][] dp = new int[M][C+1];
		int[] t = new int[C+1];
		int ans = 0;
		for(int i=0; i<N; i++) { 
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int h = Integer.parseInt(st.nextToken());
				for(int k=0; k<=C; k++) {
					if(i==0) 
						t[k] = 1;
					else {
						int d = arr[j] - h + k;
						if(d>=0 && d<=C)
							t[k] = dp[j][d] +1;
						else
							t[k] = 1;
					}
				}
				for(int k=0; k<=C; k++)
					dp[j][k] = t[k];
				arr[j] = h;
				for(int k=0; k<=C; k++) {
					int maxh = dp[j][k];
					for(int a = j; a>= 0 && j-a<100; a--) {
						int d = arr[a] - arr[j] + k;
						if(d>=0 &&d<=C) {
							if(dp[a][d] < maxh)
								maxh = dp[a][d];
							if(ans < maxh*(j - a +1)) {
								ans = maxh*(j-a+1);
							}
						}
						else
							break;
					}
				}
			}
		}
		System.out.println(ans);
	}
}
