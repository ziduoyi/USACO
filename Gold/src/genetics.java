import java.io.*;
import java.util.*;
public class genetics {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    String str = br.readLine();  
	    int N = str.length();
	    long mod = 1000000007;
	    long[][][][] dp = new long[N][4][4][4]; // 0: A   1: C   2: G   3: T
	    int[] arr = new int[N];
	    for(int i=0; i<N;  i++) {
	    	if(str.charAt(i)=='C')
	    		arr[i] = 1;
	    	if(str.charAt(i)=='G')
	    		arr[i] = 2;
	    	if(str.charAt(i)=='T')
	    		arr[i] = 3;
	    	if(str.charAt(i)=='?') 
	    		arr[i] = 4;
	    }
	    for(int i=0; i<4; i++) {
	    	for(int j=0; j<4; j++) {
	    		if(arr[0] == 4|| j == arr[0])
	    			dp[0][i][j][j] = 1;
	    	}
	    }
	    for(int i=1; i<N; i++) {
	    	for(int c = 0; c<4; c++) {
		    	if(arr[i] ==4 || arr[i] ==c) {
			    	for(int j=0; j<4; j++) {
			    		for(int k=0; k<4; k++) {
			    			for(int l=0; l<4; l++) {
			    				if(l!=c) {
			    					dp[i][j][k][c] += dp[i-1][j][k][l];
			    					dp[i][j][k][c] %= mod;
			    				}
			    				if(j==l) {
			    					dp[i][k][c][c] += dp[i-1][j][k][l];
			    					dp[i][k][c][c] %=mod;
			    				}
			    			}
			    		}
			    	}
		    	}
	    	}
	    }
	    long ans = 0;
	    for(int i=0; i<4; i++) {
	    	for(int j=0; j<4; j++)
	    		ans += dp[N-1][j][i][j];
	    	ans %=mod;
	    }
	    System.out.println(ans);
	}

}
