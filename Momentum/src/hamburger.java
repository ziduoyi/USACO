import java.io.*;
import java.util.*;
public class hamburger {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	int[][] arr = new int[n][2];
	    	for(int j=0; j<n; j++) {
	    		 StringTokenizer st = new StringTokenizer(br.readLine());
	    		 arr[j][0] = Integer.parseInt(st.nextToken());
	    		 arr[j][1] = Integer.parseInt(st.nextToken());
	    	}
	    	int[][] dp = new int[1<<n][1000];
	    	for(int j=0; j<1<<n; j++)
	    		Arrays.fill(dp[j], 1000000);
	    	dp[0][0] = 0;
	    	LinkedList<int[]> list = new LinkedList<>();
	    	list.add(new int[] {0,0});
	    	while(!list.isEmpty()) {
	    		int[] curr = list.removeFirst();
	    		for(int j=0; j<n; j++) {
	    			if((curr[0]^(1<<j))>curr[0]) {
	    				int next = (curr[0]^(1<<j));
	    				int amount = curr[1] - arr[j][0];
	    				int add = 0;
	    				if(amount <0) {
	    					add = -1*amount;
	    					amount = 0;
	    				}
	    				amount += arr[j][1];
	    				if(dp[curr[0]][curr[1]]+arr[j][0]<dp[next][amount]) {
	    					dp[next][amount] = dp[curr[0]][curr[1]]+arr[j][0];
	    					list.add(new int[] {next, amount});
	    				}
	    			}
	    		}
	    	}
	    	int ans = 1000000;
	    	for(int j=0; j<1000; j++) {
	    		ans = Math.min(ans, dp[(1<<n)-1][j]+j);
	    		
	    	}
	    	out.write(ans+"\n");
	    }
	    out.flush();
	    out.close();
	}

}
