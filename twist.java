import java.io.*;
import java.util.*;
public class twist {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	int[] arr = new int[n];
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	for(int j=0; j<n; j++) arr[j] = Integer.parseInt(st.nextToken())-1;
	    	LinkedList<Integer> ans = new LinkedList<>();
	    	for(int j=n-1; j>-1; j--) {
	    		int pos = -1;
	    		for(int k=0; k<n; k++)
	    			if(arr[k]==j) {
	    				pos = k;
	    				break;
	    			}
	    		int steps = (pos-j+(j+1))%(j+1);
	    		ans.add(steps);
	    		int[] next = arr.clone();
	    		for(int k=0; k<=j; k++) next[k] = arr[(k+steps)%(j+1)];
	    		arr = next;
	    	}
	    	for(int j=0; j<ans.size(); j++)out.write(ans.get(ans.size()-j-1)+" ");
	    	out.write("\n");
	    }
	    out.flush();
	    out.close();
	}

}
