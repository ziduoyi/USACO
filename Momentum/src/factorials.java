import java.io.*;
import java.util.*;
public class factorials {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    long[] facts = new long[15];
	    facts[3] = 6;
	    for(int i=4; i<15; i++) facts[i] = facts[i-1]*i;
	    for(int i=0; i<t; i++) {
	    	long n = Long.parseLong(br.readLine());
	    	int ans = 1000;
	    	for(int j=0; j<(1<<15); j+=8) {
	    		long sub = 0;
	    		for(int k=0; k<15; k++) 
	    			if((j&(1<<k))==(1<<k))sub+=facts[k];
	    		if(n-sub>=0) 
	    			ans = Math.min(ans, Integer.bitCount(j)+Long.bitCount(n-sub));
	    		
	    	}
	    	out.write(ans+"\n");
	    }
	    out.flush();
	    out.close();
	}

}
