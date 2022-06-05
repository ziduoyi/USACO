import java.io.*;
import java.util.*;
public class progression {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int x = Integer.parseInt(st.nextToken());
	    	int[] arr = new int[n];
	    	st = new StringTokenizer(br.readLine());
	    	for(int j=0; j<n; j++) arr[j] = Integer.parseInt(st.nextToken());
	    	int max = 0;
	    	int min = 1000000000;
	    	long ans = 0;
	    	for(int j=0; j<n; j++) {
	    		max = Math.max(max, arr[j]);
	    		min = Math.min(min, arr[j]);
	    		if(j!=n-1)ans += Math.abs(arr[j]-arr[j+1]);
	    	}
	    	int val = 1000000000;
	    	min--;
	    	max++;
	    	if(min>0) {
	    		val = Math.min(val, Math.max(Math.abs(arr[0]-min), Math.abs(arr[0]-1)));
	    		val = Math.min(val, Math.max(Math.abs(arr[n-1]-min), Math.abs(arr[n-1]-1)));
	    		for(int j=0; j<n-1; j++) {
	    			val = Math.min(val,2*Math.min(Math.max(Math.abs(arr[j]-1),Math.abs(arr[j]-min)), Math.max(Math.abs(arr[j+1]-1), Math.abs(arr[j+1]-min))));
	    		}
	    	}
	    	if(val!=1000000000)
	    		ans += val;
	    	val = 1000000000;
	    	if(max<=x) {
	    		val = Math.min(val, Math.max(Math.abs(arr[0]-max), Math.abs(arr[0]-x)));
	    		val = Math.min(val, Math.max(Math.abs(arr[n-1]-max), Math.abs(arr[n-1]-x)));
	    		for(int j=0; j<n-1; j++) {
	    			val = Math.min(val,2*Math.min(Math.max(Math.abs(arr[j]-x),Math.abs(arr[j]-max)), Math.max(Math.abs(arr[j+1]-x), Math.abs(arr[j+1]-max))));
	    		}
	    	}
	    	if(val!=1000000000)
	    		ans+=val;
	    	out.write(ans +"\n");
	    }
	    out.flush();
	    out.close();
	}

}
