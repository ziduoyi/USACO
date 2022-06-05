import java.util.*;
import java.io.*;
public class gift {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int x = Integer.parseInt(st.nextToken());
	    	int y = Integer.parseInt(st.nextToken());
	    	int a = Integer.parseInt(st.nextToken());
	    	int b = Integer.parseInt(st.nextToken());
	    	int com = Math.min(a, b);
	    	int dif = Math.max(a, b) - com;
	    	long l = 0;
	    	long r = Math.max(x, y)/com;
	    	long ans = -1;
	    	if(dif==0) {
	    		out.write(Math.min(x, y)/a +"\n");
	    		continue;
	    	}
	    	while(l<=r) {
	    		long mid = l + (r-l)/2;
	    		if((((x-com*mid)>=0)&&((y-com*mid)>=0)&&((x-com*mid)/dif+(y-com*mid)/dif>=mid))&&((x-com*(mid+1))<0
	    				||(y-com*(mid+1))<0||(x-com*(mid+1))/dif+(y-com*(mid+1))/dif<(mid+1))) {
	    			ans = mid;
	    			break;
	    		}
	    		else if((x-com*(mid))<0||(y-com*(mid))<0||(x-com*(mid))/dif+(y-com*(mid))/dif<(mid))
	    			r = mid-1;
	    		else
	    			l = mid+1;
	    	}
	    	out.write(ans+"\n");
	    }
	    out.flush();
	    out.close();
	}
}
