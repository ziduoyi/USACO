import java.io.*;
import java.util.*;
public class drought {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int N = Integer.parseInt(br.readLine());
	    int[] arr = new int[N];
	    int min = 1000000000;
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    for(int i=0; i<N; i++) {
	    	arr[i] = Integer.parseInt(st.nextToken());
	    	min = Math.min(min, arr[i]);
	    }
	    long mod = 1000000007;
	    long ans = 0;
	    for(int i=0; i<=min; i++) { 
	    	long[][] dp = new long[N][1002-i];
	    	for(int j=0; j<=arr[0]-i; j++)dp[0][j] = 1;
	    	for(int j=0; j<N-1; j++) {
	    		for(int k=0; k<1002-i; k++) {
	    			if(dp[j][k]>0) {
	    				if(arr[j+1]-i-k>=0) {
	    					dp[j+1][0]= dp[j+1][0] + dp[j][k];
	    					dp[j+1][arr[j+1]-i-k+1]= (dp[j+1][arr[j+1]-i-k+1]-dp[j][k]);
	    				}
	    			}
	    		}
	    		for(int k=1; k<1002-i; k++) 
	    			dp[j+1][k] += dp[j+1][k-1];
	    		for(int k=1; k<1002-i; k++) 
	    			dp[j+1][k]%=mod;
	    	}
	    	ans = (ans + dp[N-1][0])%mod;
	    	if(N%2==0)break;
	    }
	    out.write(ans+"\n");
	    out.flush();
	    out.close();
	}

}
