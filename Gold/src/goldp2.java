import java.util.*;
import java.io.*;
public class goldp2 {
	static int[] root;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] cost = new int[N][2];
		int[][] value = new int[N][4];
		int[][][] next = new int[N][4][4];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = i;
			for(int j=0; j<4; j++)
				value[i][j] = Integer.parseInt(st.nextToken());
		}
		HashMap<Integer, int[]> map = new HashMap<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<4; j++) {
				if(!map.containsKey(value[i][j]))
					map.put(value[i][j], new int[] {i,j});
				else {
					int[] arr = map.get(value[i][j]);
					next[i][j][0] = arr[0];
					next[i][j][1] = arr[1];
					next[i][j][2] = i;
					if(j==0) 
						next[i][j][3] = 1;
					if(j==1) 
						next[i][j][3] = 0;
					if(j==2) 
						next[i][j][3] = 3;
					if(j==3) 
						next[i][j][3] = 2;
					next[arr[0]][arr[1]][0] = i;
					next[arr[0]][arr[1]][1] = j;
					next[arr[0]][arr[1]][2] = arr[0];
					if(arr[1]==0)
						next[arr[0]][arr[1]][3] = 1;
					if(arr[1]==1)
						next[arr[0]][arr[1]][3] = 0;
					if(arr[1]==2)
						next[arr[0]][arr[1]][3] = 3;
					if(arr[1]==3)
						next[arr[0]][arr[1]][3] = 2;
				}
			}
		}
		boolean[][] visit = new boolean[N][4];
		LinkedList<int[]> list = new LinkedList<>();
		LinkedList<HashSet<Integer>> parts = new LinkedList<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<4; j++) {
				if(visit[i][j])continue;
				list.add(new int[] {i,j});
				visit[i][j] = true;
				HashSet<Integer> curr = new HashSet<>();
				curr.add(i);
				while(!list.isEmpty()) {
					int[] node = list.removeFirst();
					int[] edges = next[node[0]][node[1]];
					for(int k=0; k<4; k+=2) {
						int x = edges[k];
						int y = edges[k+1];
						if(!visit[x][y]) {
							visit[x][y] = true;
							curr.add(x);
							list.add(new int[] {x,y});
						}
					}
				}
				parts.add(curr);
			}
		}
		int M = parts.size();
		root = new int[M];
		for(int i=0; i<M; i++)root[i] = i;
		int count = 1;
		int ans = 0;
		int[][] par = new int[N][2];
		for(int i=0; i<N; i++)Arrays.fill(par[i], -1);
		int a = 0;
		for(HashSet<Integer> use: parts) {
			for(int node: use) {
				if(par[node][0]!=-1)
					par[node][1] = a;
				else
					par[node][0] = a;
			}
			a++;
		}
		Arrays.sort(cost, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				return arg0[0]-arg1[0];
			}
		});
		for(int i=0; i<N; i++) {
			if(count == M)break;
			if(par[cost[i][1]][1]==-1)continue;
			int r1 = root(par[cost[i][1]][0]);
			int r2 = root(par[cost[i][1]][1]);
			if(r1==r2)continue;
			root[r1] = r2;
			count++;
			ans += cost[i][0];
		}
		System.out.println(ans);
	}
	static int root(int node) {
		int curr = node;
		while(root[node]!=node) 
			node = root[node];
		while(curr!=node) {
			int old = root[curr];
			root[curr] = node;
			curr = old;
		}
		return node;
	}

}
