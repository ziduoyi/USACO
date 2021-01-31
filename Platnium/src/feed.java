import java.io.*;
import java.util.*;
public class feed {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N+1][3];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		arr[N][0] = E;
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[0] - o2[0];
			}
		});
		long[][] dp = new long[2][K+1];
		Arrays.fill(dp[0], -1);
		dp[0][0] = 0;
		for(int i=1; i<N+1; i++) {
			Deque<Long> D = new LinkedList<>();
			Queue<Long> Q = new LinkedList<>();
			long sub = 0;
			for(int j=0; j<K+1; j++) {
				long add = j *j * (arr[i][0] - arr[i-1][0]);
				if(dp[0][j] != -1) {
					long ele = dp[1][j] -sub;
					if(Q.isEmpty()) {
						Q.add(ele);
						D.addLast(ele);
					}
					else {
						Q.add(ele);
						while(!D.isEmpty()&&D.peekLast() > ele)
							D.pollLast();
						D.addLast(ele);
					}
					
				}
				if(sub > (long)arr[i-1][2]*arr[i-1][1]) {
					if(dp[0][j-arr[i-1][1]-1]!=-1) {
						if(D.peekFirst().equals(Q.peek())) {
							Q.poll();
							D.pollFirst();
						}
						else
							Q.poll();
						
					}
				}
				if(!D.isEmpty()) {
					long num = D.peekFirst();
					dp[1][j] = num + sub + add;
				}
				else
					dp[1][j] = -1;
				sub += arr[i-1][2];
			}
			dp[0] = dp[1].clone();
		}
		System.out.println(dp[N%2][K]);
	}

}
