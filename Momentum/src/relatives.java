import java.io.*;
import java.util.*;
public class relatives {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	double[][] arr = new double[n][n];
	    	for(int j=0; j<n; j++) {
	    		StringTokenizer st = new StringTokenizer(br.readLine());
	    		for(int k=0; k<n; k++)
	    			arr[j][k] = Double.parseDouble(st.nextToken());
	    	}
	    	double[][] dp = new double[1<<n][n];
	    	for(int j=0; j<(1<<n); j++)
	    		Arrays.fill(dp[j], 1000000);
	    	dp[0][0] = 0;
	    	LinkedList<int[]> list = new LinkedList<>();
	    	list.add(new int[] {0,0});
	    	while(!list.isEmpty()) {
	    		int[] use = list.removeFirst();
	    		for(int j=1; j<n; j++) {
	    			if((use[0]^(1<<j))>use[0]) {
	    				int next = (use[0]^(1<<j));
	    				if(dp[use[0]][use[1]]+arr[use[1]][j]<dp[next][j]) {
	    					dp[next][j] = dp[use[0]][use[1]]+arr[use[1]][j];
	    					list.add(new int[] {next, j});
	    				}
	    			}
	    		}
	    		if(use[0]==((1<<n)-2)) {
	    			int j = 0;
	    			int next = (use[0]^(1<<j));
    				if(dp[use[0]][use[1]]+arr[use[1]][j]<dp[next][j]) 
    					dp[next][j] = dp[use[0]][use[1]]+arr[use[1]][j];
	    		}
	    	}
	    	out.write(String.format("%.2f", dp[(1<<n)-1][0]));
	    	out.write("\n");
	    }
	    out.flush();
	    out.close();
	}

}
