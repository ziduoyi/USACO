import java.io.*;
import java.util.*;

public class marginal {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int c = Integer.parseInt(br.readLine());
	    	out.write(c-1+"\n");
	    }
	    out.flush();
	    out.close();
	}

}
