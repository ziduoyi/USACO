import java.io.*;
import java.util.*;
public class story {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int n = Integer.parseInt(br.readLine());
	    int[] arr = new int[n+1];
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    for(int i=1; i<n; i++)
	    	arr[i] = Integer.parseInt(st.nextToken());
	    long[] dp = new long[n+1];
	    
	}

}
