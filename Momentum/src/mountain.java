import java.io.*;
import java.util.*;
public class mountain {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		long[] dp = new long[n+1];
		for(int i=Math.max(n-h,0); i<n+1; i++) 
			dp[i] = 1;
		
		for(int i=1; i<w; i++) {
			long sum = 0;
			for(int j=0; j<Math.min(n+1, h+1); j++)
				sum = (sum+dp[j])%1000000007;
			for(int j=0; j<n+1; j++) {
				long save = sum;
				sum-=dp[j];
				if(j+h+1<n+1)
					sum+=dp[j+h+1];
				sum = (sum+1000000007)%1000000007;
				dp[j] = save;
			}
		}
		long ans = 0;
		for(int i=0; i<n+1; i++)
			ans = (ans + dp[i])%1000000007;
		int dumb = 0;
		for(int i=0; i<=h; i++) {
			if(i*w<=n)dumb++;
			else break;
		}
		out.write(((ans-dumb+1000000007)%1000000007)+"\n");
		out.close();
	}

}
