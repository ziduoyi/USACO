import java.io.*;
import java.util.*;
public class protest {
	static int N;
	static int[] bitree;
	static long mod;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		bitree = new int[N+1];
		int[] arr = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		mod = 1000000009;
		long[] dp = new long[N+1];
		dp[0] =1;
		int[] sum = new int[N];
		sum[0] = arr[0];
		for(int i=1; i<N; i++)
			sum[i] = sum[i-1] + arr[i];
		TreeSet<Integer> check = new TreeSet<>();
		for(int i=0; i<N; i++) 
			check.add(arr[i]-sum[i]);
		Map<Integer, Integer> map = new HashMap<>();
		int z =check.size();
		for(int num: check) 
			map.put(num, z--);
		modify(map.get(arr[0]-sum[0]), 1);
		for(int i=1; i<N+1; i++) {
			if(check.ceiling(-1*sum[i-1])==null)
				continue;
			dp[i]+= sum(map.get(check.ceiling(-1*sum[i-1])));
			dp[i]%=mod;
			if(i!=N)
				modify(map.get(arr[i]-sum[i]), (int) dp[i]);
		}
		System.out.println(dp[N]);
		
	}
    static void modify(int j, int delta) {
        for(;j<=N;j+=(-j&j)) {		//j=j+lowbit(j)
        	bitree[j] += delta;
        	bitree[j]%=mod;
        }
    }
    static int sum(int j) {
    	int s=0;
        for(;j>0; j-=(-j&j)) {	////j=j-lowbit(j)
        	s+=bitree[j];
        	s%=mod;
        }
        return s;
    }

}
