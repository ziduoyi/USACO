import java.io.*;
import java.util.*;
public class teamwork {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("teamwork.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("teamwork.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr =new int[N];
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		int[] dp = new int[N];
		dp[0] = arr[0];
		for(int i=1; i<N; i++) {
			int max = arr[i];
			for(int j=i; j>-1; j--) {
				int dist = i + 1 - j;
				if(dist > K)
					continue;
				max = Math.max(max, arr[j]);
				if(j==0) {
					dp[i] = Math.max(dp[i], dist*max);
				}
				else {
					dp[i] = Math.max(dp[i], dp[j-1] + dist*max);
				}
			}
		}
		out.println(dp[N-1]);
		out.close();
	}

}
