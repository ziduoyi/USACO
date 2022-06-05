import java.io.*;
import java.util.*;
public class fact {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	long ans = 1;
	    	for(int j=1;  j<=n; j++) ans *=j;
	    	out.write(ans+"\n");
	    }
	    out.flush();
	}

}
