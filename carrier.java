import java.util.*;
import java.io.*;
public class carrier {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int k = Integer.parseInt(st.nextToken());
	    	int[] arr = new int[n];
	    	st = new StringTokenizer(br.readLine());
	    	for(int j=0; j<n; j++) arr[j] = Integer.parseInt(st.nextToken());
	    	long l = 0;
	    	long r = 100000000000000L;
	    	while(l<=r) {
	    		long mid = l + (r-l)/2;
	    		int time = k;
	    		long curr = mid;
	    		boolean b = false;
	    		for(int j=0; j<n; j++) {
	    			if(curr-arr[j]>=0) 
	    				curr-=arr[j];
	    			else {
	    				time--;
	    				curr = mid-arr[j];
	    				if(curr<0||time==0) {
	    					b = true;
	    					break;
	    				}
	    			}
	    		}
	    		if(!b) 
	    			r=mid-1;
	    		else
	    			l = mid +1;
	    	}
    		int time = k;
    		long curr = l;
    		boolean b = false;
    		for(int j=0; j<n; j++) {
    			if(curr-arr[j]>=0) 
    				curr-=arr[j];
    			else {
    				time--;
    				curr = l-arr[j];
    				if(curr<0||time==0) {
    					b = true;
    					break;
    				}
    			}
    		}
    		long ans = -1;
    		if(!b) 
    			ans = l;
    		else
    			ans = l+1;
    		curr = ans;
    		int cnt = 0;
    		int fill = k-1;
    		for(int j=0; j<n; j++) {
    			if(curr-arr[j]>=0) {
    				curr-=arr[j];
    				cnt++;
    			}
    			else {
    				time--;
    				curr = ans-arr[j];
    				out.write(cnt+"\n");
    				fill--;
    				cnt = 1;
    			}
    		}
    		out.write(cnt+"\n");
    		for(int j=0; j<fill; j++)out.write(0+"\n");
	    }
	    out.flush();
	    out.close();
	}

}
