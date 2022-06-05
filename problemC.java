import java.io.*;
import java.util.*;
public class problemC {
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	StringTokenizer st =new StringTokenizer(br.readLine());
	    	int[] temp = new int[n];
	    	for(int j=0; j<n; j++)temp[j] = Integer.parseInt(st.nextToken());
	    	if(n%2==1) {
	    		out.write("NO\n");
	    		continue;
	    	}
	    	Arrays.sort(temp);
	    	int[] arr = new int[n];
	    	for(int j=0; j<n/2; j++) {
	    		arr[2*j] = temp[j];
	    		arr[2*j+1] = temp[n/2+j];
	    	}
	    	boolean b = false;
	    	for(int j=0; j<n; j++) {
	    		if(!((arr[j]<arr[(j+1)%n]&&arr[j]<arr[(j-1+n)%n])||(arr[j]>arr[(j+1)%n]&&arr[j]>arr[(j-1+n)%n]))){
	    			b =true;
	    			break;
	    		}
	    	}
	    	if(!b) {
	    		out.write("YES\n");
	    		for(int j=0; j<n; j++)out.write(arr[j]+" ");
	    		out.write("\n");
	    	}
	    	else out.write("NO\n");
	    }
	    
	    out.flush();
	    out.close();
	}
    
}
