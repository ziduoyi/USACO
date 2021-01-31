import java.io.*;
import java.util.*;
public class mowlawn {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		long[] sum = new long[N+1];
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		for(int i=1; i<N+1; i++)
			sum[i] = sum[i-1] + arr[i-1];
		long[] dp = new long[N];
		TreeSet<Long> set = new TreeSet<>();
		HashMap<Long, Long> map = new HashMap<>();
		LinkedList<Long> que = new LinkedList<>();
		dp[0] = arr[0];
		if(K==1)
			dp[1] = Math.max(arr[0], arr[1]);
		else
			dp[1] = arr[0] + arr[1];
		long curr = arr[0]+ arr[1];
		for(int i=2; i<N; i++) {
			long stuff = dp[i-2]-curr;
			set.add(stuff);
			if(map.get(stuff)==null) 
				map.put(stuff, (long) 1);
			else
				map.put(stuff, map.get(stuff)+1);
			que.add(stuff);
			if(i>=Math.min(K+2,N)) {
				long rev = que.removeFirst();
				if(map.get(rev)==1) {
					map.remove(rev);
					set.remove(rev);	
				}
				else 
					map.put(rev, map.get(rev)-1);
			}
			curr+=arr[i];
			long node = set.pollLast();
			set.add(node);
			if(i<K)
				dp[i] = sum[i+1];
			else
				dp[i] = node + curr;
		}
		System.out.println(dp[N-1]);
	}

}
