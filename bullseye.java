import java.io.*;
import java.util.*;
public class bullseye {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	char[] colors = st.nextToken().toCharArray();
	    	double x = Double.parseDouble(st.nextToken());
	    	double y = Double.parseDouble(st.nextToken());
	    	double dist = Math.sqrt(x*x+y*y);
	    	int n = colors.length;
	    	dist%=n;
	    	int pos = (int) Math.floor(dist);
	    	out.write(colors[pos]+"\n");
	    }
	    out.flush();
	    out.close();
	}

}
