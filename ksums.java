import java.io.*;
import java.util.*;
public class ksums {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int k = Integer.parseInt(st.nextToken());
		    HashMap<Integer,Long> map = new HashMap<>();
		    int[] arr = new int[n];
	    	Integer[] sort = new Integer[n-k+1];
	    	st = new StringTokenizer(br.readLine());
	    	for(int j=0; j<n; j++)arr[j] = Integer.parseInt(st.nextToken());
	    	for(int j=0; j<n-k+1; j++)sort[j] = j+1;
	    	long curr = 0;
	    	for(int j=0; j<k; j++) 
	    		curr+=arr[j];
	    	map.put(sort[0], curr);
	    	for(int j=1; j<n-k+1; j++) {
	    		curr-=arr[j-1];
	    		curr+=arr[k+j-1];
	    		map.put(sort[j], curr);
	    	}
	    	Arrays.sort(sort, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					long a = map.get(o1);
					long b = map.get(o2);
					if(a>b)return -1;
					if(a<b)return 1;
					return o1-o2;
				}	
	    	});
	    	for(int j=0; j<n-k+1; j++) {
	    		if(j==n-k) 
	    			out.write(sort[j] + "\n");
	    		else
	    			out.write(sort[j]+" ");
	    	}
	    	map.clear();
	    	Arrays.fill(sort, 0);
	    	Arrays.fill(arr, 0);
	    }
	    out.flush();
	    out.close();
	}

}
