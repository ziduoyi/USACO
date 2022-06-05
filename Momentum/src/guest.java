import java.io.*;
import java.util.*;
public class guest {
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++)arr[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int g = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int[][]dp = new int[m][g];
		for(int i=0; i<m; i++)Arrays.fill(dp[i], 1000000000);
		dp[0][0] = 0;
		LinkedList<Integer> list = new LinkedList<>();
		list.add(0);
		list.add(0);
		while(!list.isEmpty()) {
			int num = list.removeFirst();
			int time = list.removeFirst();
			if(num>0&&arr[num]-arr[num-1]<=g-time) {
				int next = (time + arr[num]-arr[num-1])%g;
				if(next==0&&dp[num][time]+arr[num]-arr[num-1]+r<dp[num-1][next]) {
					dp[num-1][next] = dp[num][time]+arr[num]-arr[num-1]+r;
					list.add(num-1);
					list.add(next);
				}
				else if(dp[num][time]+arr[num]-arr[num-1]<dp[num-1][next]){
					dp[num-1][next] = dp[num][time]+arr[num]-arr[num-1];
					list.add(num-1);
					list.add(next);
				}
			}
			if(num<m-1&&arr[num+1]-arr[num]<=g-time) {
				int next = (time + arr[num+1]-arr[num])%g;
				if(next==0&&dp[num][time]+arr[num+1]-arr[num]+r<dp[num+1][next]) {
					dp[num+1][next] = dp[num][time]+arr[num+1]-arr[num]+r;
					if(num+1!=m-1) {
						list.add(num+1);
						list.add(next);
					}
				}
				else if(dp[num][time]+arr[num+1]-arr[num]<dp[num+1][next]){
					dp[num+1][next] = dp[num][time]+arr[num+1]-arr[num];
					if(num+1!=m-1) {
						list.add(num+1);
						list.add(next);
					}
				}
			}
		}
		int ans = dp[m-1][0];
		if(ans!=1000000000)ans-=r;
		for(int i=1; i<g; i++)
			ans = Math.min(ans, dp[m-1][i]);
		if(ans==1000000000)
			out.write(-1+"\n");
		else
			out.write(ans+"\n");
		out.flush();
		out.close();
	}
}
