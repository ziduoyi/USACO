import java.io.*;
import java.util.*;
public class spaint {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("spainting.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spainting.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M =Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long [] s = new long[N+1];
		long a =1;
		s[0]=1;
		long mod = 1000000007;
		long[] dp = new long[N];
		dp[0] = M;
		for(int i=1; i<N; i++) {
			for(int j=1; j<=Math.min(i, K-1); j++) {
				if(i<K-1&&j==2)
					break;
				dp[i] = (dp[i] + dp[i-j])%mod;
			}
			if(i<K-1)
				dp[i]= (dp[i] * (M))%mod;
			else
				dp[i]= (dp[i] * (M-1))%mod;
		}
		long ans = M;
		for(int i=1; i<N; i++) {
			ans = (ans * M) % mod;
		}
		if(ans<dp[N-1]) ans+=mod;
		out.println(ans - dp[N-1]);
		/*
		for(int i=1; i<K; i++) {
			a = (a*M)%mod;
			s[i] = (s[i] + s[i-1] +a) % mod;
		}
		for(int i=K; i<=N; i++) {
			s[i] = (M * s[i-1]) % mod - ((M-1) * s[i-K]) % mod;
		}
		ans = M;
		for(int i=1; i<N; i++) {
			ans = (ans * M) % mod;
		}
		if(ans > s[N] - s[N-1])
			ans = (ans - (s[N] - s[N-1])) % mod;
		else {
			ans = (ans + mod - s[N] + s[N-1]) % mod;
		}
		if(ans<0)
			ans +=mod;
		long[] comp = new long[N];
		for(int i=0; i<N; i++) {
			comp[i] = s[i+1] -s[i];
			if(comp[i]<0)
				comp[i]+=mod;
		}
		out.println(ans);
		*/
		out.close();
		
	}

}
