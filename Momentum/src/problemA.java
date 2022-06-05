import java.io.*;
import java.util.*;
public class problemA {
	public static  void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int x = Integer.parseInt(br.readLine());
	    	int ans = ((-x)&x);
	    	x-=((-x)&x);
	    	if(x==0) {
	    		if(ans==1)ans+=2;
	    		else ans +=1;
	    	}
	    	out.write(ans+"\n");
	    }
	    out.flush();
	    out.close();
	}
}
