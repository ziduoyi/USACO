import java.util.*;
import java.io.*;

public class island2 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				char c = str.charAt(j);
				if(c=='X')
					arr[i][j] = 1;
				if(c=='S')
					arr[i][j] = 0;
				if(c=='.')
					arr[i][j] = -1;
			}
		}
		int[][] direct = new int[][] {{1,0},{0,1}, {-1,0}, {0,-1}};
		int curr = 2;
		Map<Integer, int[]> map = new HashMap<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 1) {
					map.put(curr, new int[] {i,j});
					int type = curr++;
					arr[i][j] = type;
					LinkedList<int[]> list = new LinkedList<>();
					list.add(new int[] {i,j});
					while(!list.isEmpty()) {
						int[] node = list.removeFirst();
						for(int k=0; k<4; k++) {
							int x = node[0] + direct[k][0];
							int y = node[1] + direct[k][1];
							if(x>-1&&x<N&&y>-1&&y<M) {
								if(arr[x][y] == 1) {
									arr[x][y] = type;
									list.add(new int[] {x,y});
								}
							}
						}
					}
				}
			}
		}
		int[][] edges = new int[curr-2][curr-2];
		for(int i=0; i<curr-2; i++)
			Arrays.fill(edges[i], 1000000);
		for(int i=0; i<curr-2; i++)
			edges[i][i] = 0;
		for(int i=0; i<curr-2; i++) {
			LinkedList<int[]> list = new LinkedList<>();
			int[][] dist = new int[N][M];
			list.add(map.get(i+2));
			for(int j=0; j<N; j++)
				Arrays.fill(dist[j], 1000000);
			dist[map.get(i+2)[0]][map.get(i+2)[1]] = 0;
			while(!list.isEmpty()) {
				int[] node = list.removeFirst();
				for(int j=0; j<4; j++) {
					int x = node[0] + direct[j][0];
					int y = node[1] + direct[j][1];
					if(x>-1&&x<N&&y>-1&&y<M) {
						if(arr[x][y] != -1) {
							if(arr[x][y] ==0) {
								if(dist[node[0]][node[1]] + 1 <dist[x][y]) {
									dist[x][y] = dist[node[0]][node[1]] +1;
									list.add(new int[] {x,y});
								}
							}
							else {
								if(dist[node[0]][node[1]] <dist[x][y]) {
									dist[x][y] = dist[node[0]][node[1]];
									list.add(new int[] {x,y});
								}
							}
						}
					}
				}
			}
			for(int j=0; j<curr-2; j++) 
				edges[i][j] = dist[map.get(j+2)[0]][map.get(j+2)[1]];
		}
		N = curr-2;
		int[] times = new int[N+1];
		times[0] = 1;
		for(int i=1; i<=N; i++)
			times[i] = times[i-1] *2;
		int[][] dp = new int[times[N]][N];
		for(int i=1; i<times[N]; i++)
			Arrays.fill(dp[i], 1000000);
		for(int i=0; i<times[N]-1; i++) {
			for(int j=0; j<N; j++) {
				int next = times[j] + i;
				if(next>=times[N])
					break;
				int[] al = edges[j];
				for(int k=0; k<N; k++) {
					int tar = k;
					int cost = al[k];
					if((next|times[tar]) != next) 
						dp[next][tar] = Math.min(dp[next][tar], dp[i][j] + cost);
				}
			}
		}
		int ans = 1000000;
		for(int i=0; i<N; i++)
			ans = Math.min(ans, dp[times[N]-1-times[i]][i]);
		System.out.println(ans);
	}

}
