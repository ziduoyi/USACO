import java.io.*;
import java.util.*;
public class balanced {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	long[] arr = new long[n];
	    	for(int j=0; j<n; j++) arr[j] = Integer.parseInt(st.nextToken());
	    	int l = 0;
	    	int r = 1000000000;
	    	while(l<=r) {
	    		int m = (l+r)/2;
	    		if((!work(arr.clone(), m+1))&&(work(arr.clone(), m))) {
	    			l = m;
	    			break;
	    		}
	    		if(!work(arr.clone(), m)) r = m-1;
	    		else l = m+1;
	    	}
	    	out.write(l+"\n");
	    }
	    out.flush();
	    out.close();
	}
	static boolean work(long[] arr, int val) {
		for(int i=arr.length-1; i>1; i--) {
			if(arr[i]<val)return false;
			long add = (long)Math.floor((double)(arr[i]-val)/3);
			arr[i]-=add*3;
			arr[i-1]+=add;
			arr[i-2]+=add*2;
		}
		if(arr[0]<val||arr[1]<val) return false;
		return true;
	}
}
