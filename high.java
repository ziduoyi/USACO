import java.io.*;
import java.util.*;
public class high {
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int[] arr = new int[n];
	    	for(int j=0; j<n; j++)
	    		arr[j] = Integer.parseInt(st.nextToken());
	    	int x = Integer.parseInt(br.readLine());
	    	ArrayList<Integer> list = new ArrayList<>();
	    	list.add(-1);
	    	for(int j=0; j<n; j++)
	    		if(arr[j]>=x)
	    			list.add(arr[j]);
	    	list.add(n);
	    	int[] max = new int[list.size()-1];
	    	Arrays.fill(max, -1000000000);
	    	for(int j=0; j<list.size()-1; j++) 
	    		for(int k= list.get(j)+1; k<=list.get(j+1)-1; k++)
	    			max[j] = Math.max(max[j], arr[k]);
	    	
	    }
	}
}
