import java.io.*;
import java.util.*;
public class jump {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	int[] arr = new int[n];
	    	long ans = 0;
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	for(int j=0; j<n; j++)arr[j] = Integer.parseInt(st.nextToken());
	    	for(int j=0; j<n; j++) {
	    		for(int k=0; k<n; k++) {
	    			ans = Math.max(ans, (long)(arr[k]-arr[j])*(arr[k]-arr[j])+(long)(k-j)*(k-j));
	    		}
	    	}
	    	out.write(ans+"\n");
	    }
	    out.flush();
	}

}
