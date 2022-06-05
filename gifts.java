import java.io.*;
import java.util.*;
public class gifts {
	static Map<Integer, Integer>[] edges;
	static int[][] arr;
	static int n; 
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    n = Integer.parseInt(br.readLine());
	    arr = new int[n][n];
	    edges = new HashMap[n];
	    ArrayList<Integer>[] can = new ArrayList[n];
	    for(int i=0; i<n; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	edges[i] = new HashMap<>();
	    	can[i] = new ArrayList<>();
	    	for(int j=0; j<n; j++) {
	    		arr[i][j] = Integer.parseInt(st.nextToken())-1;
	    		can[i].add(arr[i][j]);
	    		edges[i].put(arr[i][j], j);
	    		if(i==arr[i][j])break;
	    		
	    	}
	    }
	    int[] dp = new int[(1<<n)];
	    LinkedList<Integer> list = new LinkedList<>();
	    for(int i=0; i<n; i++) {
	    	dp[(1<<i)] = 1;
	    	list.add((1<<i));
	    }
	    while(!list.isEmpty()) {
	    	int node = list.removeFirst();
	    	boolean[] has = new boolean[n];
	    	for(int i=0; i<n; i++) if((node&(1<<i))==(1<<i))has[i] = true;
	    	for(int i=0; i<n; i++) {
	    		if((node&(1<<i))!=(1<<i)) {
	    			double cnt = 1;
	    			for(int j =0; j<n; j++) {
	    				if(has[j]&&j!=i&&edges[i].containsKey(j)) {
	    					if(edges[j].containsKey(i)) {
	    						cnt += ((double)can[j].size()-edges[j].get(i))/((double)can[j].size())*((double)can[i].size()-edges[i].get(j))/((double)can[i].size());
	    					}
	    				}
	    			}
	    			list.add((node|(1<<i)));
	    			dp[(node|(1<<i))] += cnt * dp[node];
	    		}
	    	}
	    }
	    int q = Integer.parseInt(br.readLine());
	    for(int i=0; i<q; i++) {
	    	String str = br.readLine();
	    	boolean[] thing = new boolean[n];
	    	for(int j=0; j<n; j++)if(str.charAt(j)=='H')thing[j] = true;
	    	int store = 0;
	    	for(int j=0;j<n; j++) 
	    		if(thing[j]) store += (1<<j);
	    	int ans = dp[store];
	    	for(int j=0; j<n; j++) {
	    		if(thing[j]) thing[j] = false;
	    		else thing[j] = true;
	    	}
	    	store = 0;
	    	for(int j=0;j<n; j++) 
	    		if(thing[j]) store += (1<<j);
	    	if(ans==0) ans = 1;
	    	if(dp[store]!=0)
	    		ans *= dp[store];
	    	out.write(ans+"\n");
	    }
	    out.flush();
	    out.close();
	}
}
