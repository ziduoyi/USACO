import java.io.*;
import java.util.*;
public class chipMove {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	int[][] arr = new int[n][2];
	    	for(int k=0; k<2; k ++) {
	    		String str = br.readLine();
		    	for(int j=0; j<n; j++) 
		    		if(str.charAt(j)=='*')arr[j][k] = 1;
	    	}
	    	int[][] dp = new int[n+1][2];
	    	boolean b = false;
	    	for(int j=0; j<n; j++) {
	    		if(!b&&(arr[j][0]==1||arr[j][1]==1)) {
	    			b = true;
	    			if(arr[j][0]==1&&arr[j][1]==1) {
	    				dp[j+1][0] =1;
	    				dp[j+1][1] =1;
	    			}
	    			else {
	    				if(arr[j][0]==1)dp[j+1][1] =1;
	    				else dp[j+1][0] =1;
	    			}
	    			continue;
	    		}
	    		if(!b)continue;
	    		if(arr[j][0]==0&&arr[j][1]==0) {
	    			dp[j+1][0] = Math.min(dp[j][0], dp[j][1]+1)+1;
	    			dp[j+1][1] = Math.min(dp[j][0]+1, dp[j][1])+1;
	    		}
	    		if(arr[j][0]==0&&arr[j][1]==1) {
	    			dp[j+1][0] = Math.min(dp[j][0]+1, dp[j][1]+1)+1;
	    			dp[j+1][1] = Math.min(dp[j][0]+1, dp[j][1])+1;
	    		}
	    		if(arr[j][0]==1&&arr[j][1]==0) {
	    			dp[j+1][0] = Math.min(dp[j][0], dp[j][1]+1)+1;
	    			dp[j+1][1] = Math.min(dp[j][0]+1, dp[j][1]+1)+1;
	    		}
	    		if(arr[j][0]==1&&arr[j][1]==1) {
	    			dp[j+1][0] = Math.min(dp[j][0]+1, dp[j][1]+1)+1;
	    			dp[j+1][1] = Math.min(dp[j][0]+1, dp[j][1]+1)+1;
	    		}
	    	}
	    	for(int j=n; j>-1; j--) {
	    		if(arr[j-1][0]==1||arr[j-1][1]==1) {
	    			out.write(Math.min(dp[j][0], dp[j][1])+"\n");
	    			break;
	    		}
	    	}
	    }
	    out.flush();
	    out.close();
	    
	}

}
