import java.io.*;
import java.util.*;
public class pairedup {
	static int N;
	static int K;
	static long[][] dp;
	static int[][] arr;
	static long[] summary;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int T = Integer.parseInt(st.nextToken());
	    N = Integer.parseInt(st.nextToken());
	    K = Integer.parseInt(st.nextToken());
	    arr = new int[N][2];
	    summary = new long[N+1];
	    int sum = 0;
	    for(int i=0; i<N; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	arr[i][0] = Integer.parseInt(st.nextToken());
	    	arr[i][1] = Integer.parseInt(st.nextToken());
	    	sum+=arr[i][1];
	    }
	    Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				return arg0[0]-arg1[0];
			}
	    });
	    for(int i=0; i<N; i++)
	    	summary[i+1] = summary[i] + arr[i][1];
	    long ans = -1;
	    dp = new long[N][2];
	    if(T==1) { // may need to deal with N=1 case
	    	if(arr[1][0]-arr[0][0]<=K)
	    		dp[1][1] = arr[0][1] + arr[1][1];
	    	for(int i=2; i<N; i++) {
	    		dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
	    		if(arr[i][0]-arr[i-1][0]<=K)
	    			dp[i][1] = arr[i][1]+arr[i-1][1]+dp[i-1][0];
	    		if(arr[i][0]-arr[i-2][0]<=K)
	    			dp[i][1] = Math.max(dp[i][1], arr[i][1]+arr[i-2][1]+dp[i-2][0]);
	    	}
	    	ans = (sum-Math.max(dp[N-1][0], dp[N-1][1]));
	    }
	    else {
	    	for(int i=0; i<N; i++) {
	    		dp[i][0] = 1000000000000000000L;
	    		dp[i][1] = 1000000000000000000L;
	    	}
	    	long a = recursion(0,0);
	    	long b = recursion(0,1);
	    	ans = (sum-(int) Math.min(a,b));
	    }
	    out.write(ans+"\n");
	    out.flush();
	    out.close();
	}
	static long recursion(int i, int j) {
		if(i>=N) return 0;
		if(dp[i][j] != 1000000000000000000L)
			return dp[i][j];
		if(i==N-1) {
			if(j==0)return 0;
			else return 100000000000000000L;
		}
		if(j==0) {
			int last =i;
			int val = arr[last][0] + K;
			int pos = i+1;
			while(pos<N&&val>=arr[pos][0]) 
				pos++;
			int cnt = pos-i-1;
			if(cnt%2==0) {
				dp[i][j] = Math.min(recursion(pos, 0), recursion(pos,1)) + summary[pos]-summary[i+1];
				return dp[i][j];
			}
			else {
				dp[i][j] = Math.min(dp[i][j],recursion(pos-1, 1) + summary[pos-1]-summary[i+1]);
				return dp[i][j];
			}
		}
		if(arr[i+1][0]-arr[i][0]<=K) {
			dp[i][j] = Math.min(recursion(i+2,0), recursion(i+2,1))+summary[i+2]-summary[i];
			if(i<=N-3&&arr[i+2][0]-arr[i][0]<=K) {
				int last = i+1;
				int val = arr[last][0] + K;
				int pos = i+3;
				if(pos==N) {
					dp[i][j] = Math.min(dp[i][j], (long)arr[i][1]+arr[i+2][1]);
					return dp[i][j];
				}
				while(pos<N&&val>=arr[pos][0]) 
					pos++;
				int cnt = pos-i-3;
				if(cnt%2==0) {
					dp[i][j] = Math.min(dp[i][j],(long)arr[i][1] + Math.min(recursion(pos, 0), recursion(pos,1)) + summary[pos]-summary[i+2]);
					return dp[i][j];
				}
				else {
					dp[i][j] = Math.min(dp[i][j], (long)arr[i][1] + recursion(pos-1, 1) + summary[pos-1]-summary[i+2]);
					return dp[i][j];
				}
			}
			return dp[i][j];
		}
		dp[i][j] = 100000000000000000L;
		return dp[i][j];	
	}
}
