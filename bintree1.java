import java.io.*;
import java.util.*;
public class bintree1 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("bintree1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bintree1.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int dep = (int) ((Math.log10(N+1)/Math.log10(2)));
		int tol = (N+1)/2;
		tol += (N-(Math.pow(2, dep)-1))/2;
		tol = N-tol;
		if(N==2)
			tol =0;
		out.println(tol);
		out.close();
	}

}
