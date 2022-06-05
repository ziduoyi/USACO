import java.io.*;
import java.util.*;
public class problemB {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	StringTokenizer st =new StringTokenizer(br.readLine());
	    	int[] arr = new int[n];
	    	for(int j=0; j<n; j++)arr[j] = Integer.parseInt(st.nextToken());
	    	int val = 0;
	    	for(int j=0; j<n-1; j++) {
	    		if(arr[j]>arr[j+1]) {
	    			val++;
	    			j++;
	    		}
	    	}
	    	out.write(val +"\n");
	    }
	    out.flush();
	    out.close();
	}

}
