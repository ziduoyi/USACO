import java.io.*;
import java.util.*;
public class pyramids {

	@SuppressWarnings("unchecked")
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
	    	int[][] dp = new int[n][n];
	    	LinkedList<int[]> list = new LinkedList<>();
	    	for(int j=0; j<n; j++)
	    		for(int k=0; k<n; k++)
	    			if(j!=k&&arr[j]==arr[k]) {
	    				dp[j][k] = 2;
	    				list.add(new int[] {j,k});
	    			}
	    	TreeSet<Integer>[] find = new TreeSet[n+1];
	    	for(int j=0; j<n+1; j++)find[j] = new TreeSet<>();
	    	for(int j=0; j<n; j++) {
	    		find[arr[j]].add(j);
	    	}
	    	long ans = 0;
	    	while(!list.isEmpty()) {
	    		int[] node =  list.removeFirst();
	    		for(int j=node[0]+1; j<node[1]; j++) {
	    			if(arr[j]>arr[node[0]]) {
	    				ans = Math.max(ans,dp[node[0]][node[1]]+1);
	    				if(find[arr[j]].lower(node[1])!=null) {
	    					int next = find[arr[j]].lower(node[1]);
	    					if(next>j) {
	    						if(dp[node[0]][node[1]]+2>dp[j][next]) {
	    							dp[j][next] = dp[node[0]][node[1]]+2;
	    							list.add(new int[]{j, next});
	    						}
	    					}
	    				}
	    			}
	    		}
	    		/*
	    		if(biggerR[0][node[0]]!=-1) {
	    			if(find[biggerR[0][node[0]]].higher(biggerR[1][node[0]])!=null) {
	    				int next = find[biggerR[0][node[0]]].higher(biggerR[1][node[0]]);
	    				if(next<node[1]){
	    					if(dp[node[0]][node[1]]+2>dp[biggerR[1][node[0]]][next]) {
	    						dp[biggerR[1][node[0]]][next] = dp[node[0]][node[1]]+2;
	    						list.add(new int[] {biggerR[1][node[0]],next});
	    					}
	    				}
	    			}
	    		}
	    		if(biggerL[0][node[1]]!=-1) {
	    			if(find[biggerL[0][node[1]]].lower(biggerL[1][node[1]])!=null) {
	    				int next = find[biggerL[0][node[1]]].lower(biggerL[1][node[1]]);
	    				if(next>node[0]){
	    					if(dp[node[0]][node[1]]+2>dp[biggerL[1][node[1]]][next]) {
	    						dp[biggerL[1][node[1]]][next] = dp[node[0]][node[1]]+2;
	    						list.add(new int[] {biggerL[1][node[1]],next});
	    					}
	    				}
	    			}
	    		}
	    		*/
	    		
	    	}
	    	out.write(ans+"\n");
	    }
	    out.flush();
	    out.close();
	}

}
