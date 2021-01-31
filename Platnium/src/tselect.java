import java.io.*;
import java.util.*;
public class tselect {
	static ArrayList<Integer>[] edges;
	static int[][][] dp;
	static int[] val;
	static int[] prev;
	static int N;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		edges = new ArrayList[N];
		for(int i=0; i<N; i++)
			edges[i] = new ArrayList<>();
		val = new int[N];
		prev = new int[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			val[i] = Integer.parseInt(st.nextToken());
			prev[i] = Integer.parseInt(st.nextToken())-1;
			if(prev[i]!=-1)
				edges[prev[i]].add(i);
		}
		dp = new int[N][N][2]; // 0 = not use   1 = use
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++)
				Arrays.fill(dp[i][j], -1000000000);
		}
		HashSet<Integer> set = new HashSet<>();
		for(int i=0; i<N; i++) {
			if(prev[i] ==-1)
				set.add(i);
		}
		for(int num: set)
			recursion(num);
		int[] sack = new int[N];
		
		Arrays.fill(sack, -1000000000);
		sack[0] = 0;
		boolean[] visit = new boolean[N];
		for(int i: set) {
			for(int l=N-1; l>-1; l--) {
				if(visit[l]||l==0) {
					int curr = sack[l];
					for(int j=0; j<N; j++) {
						int next = l+j;
						if(next>=N)continue;
						for(int k=0; k<2; k++) {
							if(dp[i][j][k]==-1000000000)continue;
							if(curr + dp[i][j][k] > sack[next]) {
								sack[next] = curr + dp[i][j][k];
								visit[next] = true;
							}
						}
					}
				}
			}
		}
		int ans = -1;
		for(int i=N-1; i>-1; i--) {
			if(sack[i]>=X) {
				ans = i;
				break;
			}
		}
		System.out.println(ans);
	}
	static void recursion(int node) {
		ArrayList<Integer> list = edges[node];
		int s = list.size();
		if(s==0) {
			dp[node][0][1] = val[node];
			return;
		}
		boolean[] visit = new boolean[N];
		int[] sack = new int[N];
		Arrays.fill(sack, -1000000000);
		sack[0] = 0;
		for(int i=0; i<s; i++) {
			int tar = list.get(i);
			recursion(tar);
		}
		for(int i=0; i<s; i++) {
			int tar = list.get(i);
			for(int l=N-1; l>-1; l--) {
				if(visit[l]||l==0) {
					int curr = sack[l];
					for(int j=0; j<N; j++) {
						int next = l+j;
						if(next>=N)continue;
						for(int k=0; k<2; k++) {
							if(dp[tar][j][k] == -1000000000)
								continue;
							if(curr + dp[tar][j][k] > sack[next]) {
								sack[next] = curr + dp[tar][j][k];
								visit[next] = true;
							}
						}
					}
				}
			}
		}
		for(int i=0; i<N; i++)
			dp[node][i][0] = sack[i];
		
		visit = new boolean[N];
		sack = new int[N];
		Arrays.fill(sack, -1000000000);
		sack[0] = val[node];
		for(int i=0; i<s; i++) {
			int tar = list.get(i);
			for(int l=N-1; l>-1; l--) {
				if(visit[l]||l==0) {
					int curr = sack[l];
					for(int j=0; j<N; j++) {
						int next = l+j;
						for(int k=0; k<2; k++) {
							if(k==1)next++;
							if(next>=N)continue;
							if(dp[tar][j][k] == -1000000000)
								continue;
							if(curr + dp[tar][j][k] > sack[next]) {
								sack[next] = curr + dp[tar][j][k];
								visit[next] = true;
							}
						}
					}
				}
			}		
			
		}
		for(int i=0; i<N; i++)
			dp[node][i][1] = sack[i];
	}
}
