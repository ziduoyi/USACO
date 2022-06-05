import java.io.*;
import java.util.*;
public class invest {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	int[][] arr = new int[n][n];
	    	for(int j=0; j<n; j++) {
	    		 StringTokenizer st = new StringTokenizer(br.readLine());
	    		 for(int k=0; k<n; k++)
	    			 arr[j][k] = Integer.parseInt(st.nextToken());
	    	}
	    	long[] dp = new long[1<<n];
	    	int[] count = new int[1<<n];
	    	for(int j=0; j<(1<<n); j++) {
	    		for(int k=0; k<n; k++) {
	    			if(((j^(1<<k)))<j) 
	    				count[j]++;
	    		}
	    	}
	    	LinkedList<Integer> list = new LinkedList<>();
	    	list.add(0);
	    	while(!list.isEmpty()) {
	    		int curr = list.removeFirst();
	    		for(int j=0; j<n; j++) {
	    			if((curr^(1<<j))>curr) {
	    				int next = (curr^(1<<j));
	    				if(dp[curr]+arr[j][count[curr]]>dp[next]) {
	    					dp[next] = dp[curr]+arr[j][count[curr]];
	    					list.add(next);
	    				}
	    			}
	    		}
	    	}
	    	out.write(dp[(1<<n)-1]+"\n");
	    }
	    out.flush();
	    out.close();
	}

}