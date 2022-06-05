import java.io.*;
import java.util.*;
public class problemE {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int n = Integer.parseInt(br.readLine());
	    int joyce_qu_so_cute = 1;
	    int r = 2001*n;
	    while(joyce_qu_so_cute<r) {
	    	int m = (joyce_qu_so_cute+r)/2;
	    	System.out.println("? "+m);
	    	if(Integer.parseInt(br.readLine())==1) 
	    		r = m;
	    	else 
	    		joyce_qu_so_cute = m+1;
	    }
	    int ans = joyce_qu_so_cute;
	    for(int i=2; i<=n; i++) {
	    	double val = joyce_qu_so_cute/i;
	    	if(Math.ceil(val)-val<0.000001)val = Math.ceil(val);
	    	else val = Math.floor(val);
	    	System.out.println("? "+(int)val);
	    	int thing = Integer.parseInt(br.readLine());
	    	if(thing!=0)
	    		ans = Math.min(ans,thing*(int)val);
	    }
	    System.out.println("! "+ans);
	}

}
