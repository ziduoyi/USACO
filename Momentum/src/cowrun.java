import java.io.*;
import java.util.*;
public class cowrun {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("cowrun.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowrun.out")));
		int N = Integer.parseInt(br.readLine());
		int[][][] dp = new int[N+1][N+1][2];
		int[] arr = new int[N+1];
		for(int i=1; i<=N; i++) 
			arr[i] = Integer.parseInt(br.readLine());
		Arrays.sort(arr);
		int start = -1;
		for(int i=0; i<=N; i++)
			if(arr[i]==0)
				start = i;
		for(int i=0; i<=N; i++)
			for(int j=0; j<=N; j++)
				for(int k=0; k<2; k++)
					dp[i][j][k] = 2000000000;
		dp[start][start][0] = 0;
		dp[start][start][1] = 0;
		int ans = 2000000000;
		LinkedList<int[]> list = new LinkedList<>();
		list.add(new int[] {start, start, 0});
		list.add(new int[] {start, start, 1});
		while(!list.isEmpty()) {
			int[] info = list.removeFirst();
			if(info[0]==0&&info[1]==N) 
				continue;
			int pos = info[info[2]];
			if(info[0]>0) {
				int next = info[0]-1;
				if(dp[next][info[1]][0]>dp[info[0]][info[1]][info[2]]+(N-(info[1]-info[0]))*Math.abs(arr[pos]-arr[next])) {
					dp[next][info[1]][0]=dp[info[0]][info[1]][info[2]]+(N-(info[1]-info[0]))*Math.abs(arr[pos]-arr[next]);
					list.add(new int[] {next, info[1],0});
				}
			}
			if(info[1]<N) {
				int next = info[1]+1;
				if(dp[info[0]][next][1]>dp[info[0]][info[1]][info[2]]+(N-(info[1]-info[0]))*Math.abs(arr[pos]-arr[next])) {
					dp[info[0]][next][1]=dp[info[0]][info[1]][info[2]]+(N-(info[1]-info[0]))*Math.abs(arr[pos]-arr[next]);
					list.add(new int[] {info[0], next,1});
				}
			}
		}
		ans = Math.min(dp[0][N][0], dp[0][N][1]);
		out.write(ans+"\n");
		out.close();
	}

}
