import java.io.*;
import java.util.*;
public class skilevel {
	static int[] root;
	static int N;
	static int M;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("skilevel.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skilevel.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		PriorityQueue<int[]> que = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[0]-o2[0];
			}
		});
		root = new int[M*N];
		for(int i=0; i<M*N; i++)
			root[i] = i;
		int[][] arr = new int[M][N];
		int[][] direct   = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) 
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<2; k++) {
					int x = i+direct[k][0];
					int y = j+direct[k][1];
					if(x<0||x>=M||y<0||y>=N)continue;
					que.add(new int[] {Math.abs(arr[x][y]-arr[i][j]),i,j,x,y});
				}
			}
		}
		
		int[] size = new int[M*N];
		Arrays.fill(size, 1);
		int[] stuff = new int[M*N];
		Arrays.fill(stuff, 1000000000);
		long ans = 0;
		LinkedList<Integer> list = new LinkedList<>();
		while(!que.isEmpty()) {
			int[] use = que.poll();
			int r1 = find(use[1]*N+use[2]);
			int r2 = find(use[3]*N+use[4]);
			if(stuff[r1]!=1000000000&&stuff[r2]!=1000000000)continue;
			if(r1==r2)continue;
			root[r2] = r1;
			size[r1]+=size[r2];
			if(size[r1]>=T) {
				if(stuff[r1]==1000000000) {
					stuff[r1] = use[0];
					list.add(r1);
				}
				if(stuff[r2]==1000000000) {
					stuff[r2] = use[0];
					list.add(r2);
				}
				while(!list.isEmpty()) {
					int node = list.removeFirst();
					int x1 = node/N;
					int y1 = node%N;
					for(int i=0; i<4; i++) {
						int x = x1+ direct[i][0];
						int y = y1 + direct[i][1];
						if(x>=0&&x<M&&y>=0&&y<N) {
							if(Math.abs(arr[x1][y1]-arr[x][y])<=use[0]) {
								if(stuff[x*N+y]==1000000000) {
									stuff[x*N+y] = use[0];
									list.add(x*N+y);
								}
							}
						}
					}
				}
			}
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				if(Integer.parseInt(st.nextToken())==1)
					ans += stuff[i*N+j];
			}
		}
		out.println(ans);
		out.close();
	}
	static int find(int node) {
		if(node!=root[node]) 
			root[node] = find(root[node]);
		return root[node];
	}
}
