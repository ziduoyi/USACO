import java.util.*;
import java.io.*;
public class Class11 {
	
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		if(n%4==0)
			out.write("YES\n");
		else
			out.write("NO\n");
		out.flush();
		out.close();
	}
}

