import java.io.*;
import java.util.*;
public class problemF1 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	int[][] joyce_qu = new int[n][2];
	    	int max = 0;
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	HashMap<Integer,Integer> map = new HashMap<>();
	    	for(int j=0; j<n; j++) {
	    		joyce_qu[j][0] = Integer.parseInt(st.nextToken());
	    		joyce_qu[j][1] = j;
	    		if(map.containsKey(joyce_qu[j][0]))map.put(joyce_qu[j][0],map.get(joyce_qu[j][0])+1);
	    		else map.put(joyce_qu[j][0], 1);
	    		max = Math.max(max, map.get(joyce_qu[j][0]));
	    	}
	    	Arrays.sort(joyce_qu, (a,b)->a[0]-b[0]);
	    	int[] next = new int[n];
	    	
	    	int[] ans = new int[n];
	    	for(int j=0; j<n; j++)
	    		next[j] = joyce_qu[(j+max)%n][0];
	    	for(int j=0; j<n; j++)
	    		ans[joyce_qu[j][1]] = next[j]; 
	    	for(int j=0; j<n; j++)
	    		out.write(ans[j] +" ");
	    	out.write("\n");
	    	/*
	    	LinkedList<Integer>[] joyce_qu = new LinkedList[n+1];
	    	for(int j=1; j<=n; j++)joyce_qu[j] = new LinkedList<>();
	    	for(int j=0; j<n; j++) {
	    		arr[j] = Integer.parseInt(st.nextToken());
	    		joyce_qu[arr[j]].add(j);
	    	}
	    	int[] next = new int[n+1];
	    	for(int j=1; j<=n; j++) {
	    		if(joyce_qu[j].isEmpty())next[i] = -1;
	    		else next[j] = joyce_qu[j].removeFirst();
	    	}
	    	*/
	    }
	    out.flush();
	    out.close();
	}

}
