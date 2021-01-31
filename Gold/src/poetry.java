import java.io.*;
import java.util.*;
public class poetry {
	static int mod=1000000007;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("poetry.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("poetry.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long[] dp = new long[K+1];
		int[][] arr= new int [N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken())-1;
		}
		Map<Integer, Integer> convert = new HashMap<>();
		int b =0;
		for(int i=0; i<N; i++) {
			if(convert.get(arr[i][1])==null) {
				convert.put(arr[i][1], b++);
			}
		}
		int z=0;
		Map<String, Integer> use = new HashMap<>();
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<M; i++) {
			String str = br.readLine();
			if(use.get(str)==null) {
				use.put(str, z);
				map.put(z++, 1);
			}
			else {
				int a = map.remove(use.get(str));
				a++;
				map.put(use.get(str), a);
			}
		}
		int[] ways = new int[convert.size()];
		dp[0]=1;
		for(int i = 0; i<K+1; i++) {
			if(dp[i]==0)continue;
			for(int j=0; j<N; j++) {
				int pos = i + arr[j][0];
				if(pos>=K) continue;
				dp[pos] = (dp[pos]+ dp[i]) % mod;
			}
		}
		for(int i=0; i<convert.size(); i++) {
			for(int j=0; j<N; j++) {
				if(i==convert.get(arr[j][1])) {
					ways[i] += dp[K-arr[j][0]];
					ways[i]%=mod;
				}
			}
		}
		long ans =1;
		for(int i=0; i<map.size(); i++) {
			long curr=0;
			int times = map.get(i);
			for(int j=0; j<convert.size(); j++) {
				long now = expnp(ways[j],times)%mod;
				curr+=now;
				curr%=mod;
			}
			ans*=curr;
			ans%=mod;
		}
		out.println(ans);
		out.close();
	}
	public static long exp(long n, int e) {
		if(e==1 || n==1) return n;
		long res = 1;
		res = exp((n*n)%mod, e/2);
		if(e%2!=0)
			res = (res*n) % mod;
		return res;
	}
	
	public static long expnp(long n, int e) {
		if(e==1 || n==1) return n;
		long res = 1;
		
		while(e>1) {
			if(e%2!=0)
				res = res * n % mod;
			n = n * n % mod;
			e /= 2;
		}
		res = res * n % mod;
		return res;
	}
	
	public static long power(int num, int times) {
		if(times==1)
			return num;
		long mult =1;
		while(mult<times) {
			mult*=2;
		}
		mult/=2;
		int a = (int) (times - mult);
		long curr=num;
		while(mult!=1) {
			curr*=curr;
			curr%=mod;
			mult/=2;
		}
		return (power(num, a)%mod) * curr;
	}
}
