import java.io.*;
import java.util.*;
public class exercise {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("exercise.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("exercise.out")));
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[] composite = new boolean[N+1];
		for(int i=2; i<=N; i++) 
			if(composite[i] == false)
				for(int j = 2*i; j<=N; j+=i) 
					composite[j] = true;
		long[] dp = new long[N+1];
		dp[0]= 1;
		for(int i=2; i<=N; i++) {
			if(composite[i])
				continue;
			for(int k=N-i; k>=0; k--) {
				for(int j = i; j<=N; j*=i) {
					if(k+j>N)
						continue;
					if(dp[k]==0)
						continue;
					dp[k+j] += dp[k] * j;
					dp[k+j] %= M;
				}
			}
		}
		long sum =0;
		for(int i=0; i<=N; i++) {
			sum += dp[i];
			sum%= M;
		}
		out.println(sum);
		out.close();
	}

}
