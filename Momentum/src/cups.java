import java.io.*;
import java.util.*;

public class cups {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    double[] sum = new double[50001];
	    sum[1] = 3600;
	    for(int i=2; i<50001; i++)sum[i] = sum[i-1] + (double)3600/(double)i;
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	double time = 0;
	    	if(n%2==1) 
	    		time += (double)3600/Math.max((n+1),1);
	    	time+= sum[n/2];
	    	out.write((long)Math.round(time)+"\n");
	    }
	    out.flush();
	    out.close();
	}

}
