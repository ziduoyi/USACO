import java.io.*;
import java.util.*;
public class zerosum {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int Q = Integer.parseInt(br.readLine());
	    	for(int j=0; j<Q; j++) {
	    		StringTokenizer st = new StringTokenizer(br.readLine());
	    		int L = Integer.parseInt(st.nextToken())-1;
	    		int R = Integer.parseInt(st.nextToken());
	    		int ans = 0;
	    		if(L%4==0) ans = L;
	    		if(L%4==1)ans = 1;
	    		if(L%4==2)ans = L+1;
	    		if(L%4==3)ans = 0;
	    		if(R%4==0)ans^=R;
	    		if(R%4==1) ans^=1;
	    		if(R%4==2) ans^=R+1;
	    		if(R%4==3) ans^=0;
	    		if(ans==0)out.write("NO\n");
	    		else out.write("YES\n");
	    	}
	    }
	    out.flush();
	    out.close();
	}

}
