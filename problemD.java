import java.io.*;
import java.util.*;
public class problemD {
	
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int k = Integer.parseInt(st.nextToken());
	    	st = new StringTokenizer(br.readLine());
	    	int[] arr = new int[n];
	    	for(int j=0; j<n; j++)arr[j] = Integer.parseInt(st.nextToken());
	    	if(k>=n) {
	    		long ans = 0;
	    		for(int j=0; j<n; j++)ans += arr[j];
	    		ans += (long)n*(long)k;
	    		ans-= (long)n*(long)(n+1)/2;
	    		out.write(ans +"\n");
	    	}
	    	else {
	    		long curr = 0;
	    		long max =0;
	    		for(int j=0; j<k; j++)curr += arr[j];
	    		max = Math.max(curr, max);
	    		for(int j=0; j+k<n; j++) {
	    			curr-=arr[j];
	    			curr+=arr[j+k];
	    			max = Math.max(max, curr);
	    		}
	    		max += (long)(k-1)*(long)(k)/2;
	    		out.write(max+"\n");
	    	}
	    }
	    out.flush();
	    out.close();
	}
}
