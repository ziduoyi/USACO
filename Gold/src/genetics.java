import java.io.*;
import java.util.*;
public class genetics {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    String str = br.readLine();
	    int N = str.length();
	    char[] bases = str.toCharArray();
	    HashMap<Character, Integer> map = new HashMap<>();
	    map.put('A', 0);
	    map.put('C', 1);
	    map.put('G', 2);
	    map.put('T', 3);
	    long[][][][] dp = new long[N][4][4][4];
	    for(int i=0; i<4; i++) {
	    	for(int j=0; j<4; j++) {
	    		if(bases[0]=='?'||map.get(bases[0])==j) {
	    			dp[0][i][j][j] = 1;
	    		}
	    	}
	    }
	    long mod = 1000000007;
	    for(int i=1; i<N; i++) {
	    	 for(int l=0; l<4; l++) {
	    		 if(bases[i]=='?'||map.get(bases[i])==l) {
		    		 for(int j=0; j<4; j++) {
		    			 for(int k = 0; k<4; k++) {
		    				 for(int m=0; m<4; m++) {
		    					 if(m!=l) {
		    						 dp[i][j][k][l] += dp[i-1][j][k][m];
		    						 dp[i][j][k][l] %= mod;
		    					 }
		    					 if(j==m) {
		    						 dp[i][k][l][l] += dp[i-1][j][k][m];
		    						 dp[i][k][l][l] %= mod;
		    					 }
		    				 }
		    			 }
		    		 }
	    		 }
	    	 }
	    }
	    long ans = 0;
	    for(int i=0; i<4; i++)
	    	for(int j=0; j<4; j++)
	    		ans += dp[N-1][j][i][j];
	    ans %= mod;
	    System.out.println(ans);
	}

}
