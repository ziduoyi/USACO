import java.io.*;
import java.util.*;
public class walking {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    int[][] dict = new int[][] {{0,2},{2,1},{2,-1},{0,-2},{-2,-1},{-2,1}};
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	long x = 0;
	    	long y = 0;
	    	for(int j=0; j<n; j++) {
	    		StringTokenizer st = new StringTokenizer(br.readLine());
		    	int o = Integer.parseInt(st.nextToken())-1;
		    	int d = Integer.parseInt(st.nextToken());
		    	x += (long)d*dict[o][0];
		    	y += (long)d*dict[o][1];
	    	}
	    	x = Math.abs(x);
	    	y = Math.abs(y);
	    	long step = x/2;
	    	long ans = 0;
	    	if(step>=y) 
	    		ans = step;
	    	else {
	    		ans = step+((y-step)/2);
	    	}
	    	out.write(ans+"\n");
	    }
	    out.flush();
	    out.close();
	}

}
