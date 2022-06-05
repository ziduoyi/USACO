import java.io.*;
import java.util.*;
public class excellence {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	int[] arr = new int[n];
	    	for(int j=0; j<n; j++)arr[j] = Integer.parseInt(br.readLine());
	    	Arrays.sort(arr);
	    	int min = 1000000000;
	    	for(int j=0; j<n/2; j++)min = Math.min(min, arr[j]+arr[n-j-1]);
	    	out.write(min+"\n");
	    }
	    out.flush();
	    out.close();
	}

}
