import java.io.*;
import java.util.*;
public class paintArray {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int n = Integer.parseInt(br.readLine());
	    int[] arr = new int[n];
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    for(int i=0; i<n; i++)
	    	arr[i] = Integer.parseInt(st.nextToken());
	    boolean[] use = new boolean[n];
	    int sum = 1;
	    use[0] = true;
	    for(int i=1; i<n; i++) {
	    	if(arr[i-1]!=arr[i]) {
	    		use[i] = true;
	    		sum+=1;
	    	}
	    }
	    int last = -1;
	    for(int i=1; i<n; i++)
	    	if(!use[i]) 
	    		if(last!=arr[i]) {
	    			last = arr[i];
	    			sum+=1;
	    		}
	    out.write(sum+"\n");
	    out.close();
	}

}
