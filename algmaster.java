import java.io.*;
import java.util.*;
public class algmaster {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int r = Integer.parseInt(st.nextToken());
	    	ArrayList<Integer>[] edges = new ArrayList[1<<n];
	    	for(int j=0; j<(1<<n); j++)
	    		edges[j] = new ArrayList<>();
	    	for(int j=0; j<r; j++) {
	    		st = new StringTokenizer(br.readLine());
	    		int a = Integer.parseInt(st.nextToken());
	    		int b = Integer.parseInt(st.nextToken());
	    		int c = Integer.parseInt(st.nextToken());
	    		edges[a].add(b);
	    		edges[a].add(c);
	    	}
	    	int[] dp = new int[1<<n];
	    	Arrays.fill(dp, 1000000000);
	    	dp[0] = 0;
	    	LinkedList<Integer> list = new LinkedList<>();
	    	list.add(0);
	    	while(!list.isEmpty()) {
	    		int pos = list.removeFirst();
	    		ArrayList<Integer> al = edges[pos];
	    		int s = al.size();
	    		for(int j=0; j<s; j+=2) {
	    			int add = al.get(j);
	    			int cost = al.get(j+1);
	    			if((pos^(1<<add))>pos) {
	    				int next = (pos^(1<<add));
	    				if(dp[pos]+cost<dp[next]) {
	    					dp[next] = dp[pos] + cost;
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
