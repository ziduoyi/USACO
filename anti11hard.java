import java.io.*;
import java.util.*;
public class anti11hard {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(br.readLine());
		for(int i=0; i<t; i++) {
			int n = Integer.parseInt(br.readLine());
			long ans = 0;
			long[] zero = new long[n];
			long[] one = new long[n];
			zero[0] = 1;
			one[0] = 1;
			for(int j=1; j<n; j++) {
				zero[j] = (zero[j-1]+ one[j-1])%1000000007;
				one[j] = zero[j-1];
			}
			ans = (one[n-1]+zero[n-1])%1000000007;
			out.write(ans+"\n");
		}
		out.flush();
		out.close();
	}

}
