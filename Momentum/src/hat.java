import java.io.*;
import java.util.*;
public class hat {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int m = Integer.parseInt(st.nextToken());
	    	int k = Integer.parseInt(st.nextToken());
	    	int old = n;
	    	int size = 0;
	    	while(size<n) size+=m;
	    	size/=m;
	    	int timeb = n-(size-1)*m;
	    	int times = m*size-n;
	    	n=old;
	    	int pos = 1;
	    	for(int j=0; j<k; j++) {
	    		for(int l=0; l<timeb*size; l++) {
	    			if((l%size)==0) {
	    				out.write("\n");
	    				out.write(size + " ");
	    			}
	    			out.write((pos)+" ");
	    			pos++;
	    			if(pos==n+1) pos = 1;
	    		}
	    		int pos2 = pos;
	    		for(int l=0; l<times*(size-1); l++) {
	    			if(((l)%(size-1))==0) {
		    			out.write("\n");
		    			out.write(size-1 + " ");
	    			}
	    			out.write((pos2)+" ");
	    			pos2++;
	    			if(pos2==n+1) pos2 = 1;
	    		}
	    	}
	    	out.write("\n");
	    }
	    out.flush();
	    out.close();
	}

}
