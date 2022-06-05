import java.io.*;
import java.util.*;
public class youwin {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	   while(1>0) {
	    	String str = br.readLine();
	    	if(str.length()==1&&str.equals("0"))
	    		break;
	    	int n = str.length();
	    	int[][] dp = new int[1<<n][n];
	    	for(int j=0; j<(1<<n); j++) 
	    		Arrays.fill(dp[j], 1000000);
	    	dp[0][0] = 0;
	    	LinkedList<int[]> list1 = new LinkedList<>();
	    	LinkedList<int[]> list2 = new LinkedList<>();
	    	list1.add(new int[] {0, 0});
	    	int[] a = new int[n+1];
	    	list2.add(a);
	    	while(!list1.isEmpty()) {
	    		int[] use = list1.removeFirst();
	    		int[] arr = list2.removeFirst();
	    		for(int j=0; j<n; j++) {
	    			if((use[0]^(1<<j))>use[0]) {
	    				int next = (use[0]^(1<<j));
	    				int add = 1;
	    				int dist = Math.abs(str.charAt(j)-str.charAt(use[1]));
	    				dist = Math.min(dist, 25-dist);
	    				if(j>use[1]) { //use a bitree instead
	    			        for(int k=j+1; k>0; k-=(-k&k))
	    			        	add+=arr[k];
	    			        for(int k=use[1]+1; k>0; k-=(-k&k))
	    			        	add-=arr[k];
	    				}
	    				else {
	    			        for(int k=use[1]+1; k>0; k-=(-k&k))
	    			        	add+=arr[k];
	    			        for(int k=j-1+1; k>0; k-=(-k&k))
	    			        	add-=arr[k];
	    				}
	    				if(dp[use[0]][use[1]]+dist+add<dp[next][j]) {
	    					dp[next][j] = dp[use[0]][use[1]]+dist+add;
	    					int[] temp = arr.clone();
	    					for(int k = j+1; k<temp.length; k+=(-k&k))
	    						temp[k] +=1;
	    					list1.add(new int[] {next, j});
	    					list2.add(temp);
	    				}
	    			}
	    		}
	    	}
	    	int ans = 1000000;
	    	for(int j=0; j<n; j++)
	    		ans = Math.min(ans, dp[(1<<n)-1][j]);
	    	out.write(ans+"\n");
	    }
	    out.flush();
	    out.close();
	}

}
