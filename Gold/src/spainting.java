import java.io.*;
import java.util.*;
public class spainting {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("spainting.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spainting.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long mod = 1000000007;
		long[] dp = new long[N];
		dp[0] = M;
		long sum = dp[0];
		for(int i=1; i<N; i++) {
			if(i>=K-1)
				dp[i] = (sum*(M-1))%mod;
			else
				dp[i] = (sum*(M))%mod;
			if(i>=K-1) 
				sum -= dp[i-K+1];
			sum+=dp[i];
			sum+= mod*mod;
			sum%=mod;
		}
		long curr = M;
		int count = 1;
		ArrayList<Integer> list = new ArrayList<Integer>();
		HashMap<Integer, Long> map = new HashMap<>();
		list.add(1);
		map.put(1, curr);
		while(count<=N) {
			curr*=curr;
			curr%=mod;
			count*=2;
			list.add(count);
			map.put(count, curr);
		}
		int now = N;
		long comp = 1;
		while(now>0) {
			int pos = Collections.binarySearch(list, now);
			if(pos<0) {
				pos++;
				pos*=-1;
				pos--;
			}
			now-=list.get(pos);
			comp*=map.get(list.get(pos));
			comp %= mod;
		}
		long ans = comp - dp[N-1];
		ans += mod*mod;
		ans %=mod;
		out.println(ans);
		out.close();
	}

}
