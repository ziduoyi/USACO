import java.io.*;
import java.util.*;
public class fancy {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	int[] arr = new int[n];
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	for(int j=0; j<n; j++)arr[j] = Integer.parseInt(st.nextToken());
	    	long[][] dp = new long[n+1][n+1];
	    	dp[0][0] = 1;
	    	for(int j=0; j<n/2; j++) {
	    		int save = arr[j];
	    		arr[j] = arr[n-j-1];
	    		arr[n-j-1] = save;
	    	}
	    	for(int j=0; j<n; j++) {
	    		for(int k=0; k<n; k++) {
	    			
	    			if(dp[j][k]==0)continue;
	    			dp[j][k]%=998244353;
	    			if(j+1<n&&arr[j]>arr[j+1]) 
	    				dp[j+1][k+1] += dp[j][k];
	    			int add = 0;
	    			if(k==n/2)add++;
	    			dp[j+1][k] += dp[j][k]+Math.max(k-1, 0)+add-(j-k);
	    		}
	    	}
	    	long ans = dp[n][n/2]%998244353;
	    	out.write(ans+"\n");
	    }
	    out.flush();
	    out.close();
	}

}
