import java.io.*;
import java.util.*;
public class island {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("island.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("island.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] grid = new int[R][C];
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				if(str.charAt(j)=='X') 
					grid[i][j] = -2;
				if(str.charAt(j)=='.')
					grid[i][j] = -1;
				if(str.charAt(j)=='S')
					grid[i][j] = 0;
			}
		}
		LinkedList<Integer> list = new LinkedList<>();
		int[][] direct = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
 		int N = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(grid[i][j]==-2) {
					grid[i][j] = ++N; 
					list.add(i);
					list.add(j);
					while(!list.isEmpty()) {
						int x = list.removeFirst();
						int y = list.removeFirst();
						for(int k=0; k<4; k++) {
							int x2 = x + direct[k][0];
							int y2 = y+ direct[k][1];
							if(x2<0||x2>=R||y2<0||y2>=C)continue;
							if(grid[x2][y2]==-2) {
								grid[x2][y2] = N;
								list.add(x2);
								list.add(y2);
							}
						}
					}
				}
			}
		}
		ArrayList<Integer>[] edges = new ArrayList[N];
		for(int i=0; i<N; i++) edges[i] = new ArrayList<>();
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(grid[i][j]>0) {
					int start = grid[i][j];
					boolean[][] visit = new boolean[R][C];
					list.add(i);
					list.add(j);
					visit[i][j] = true;
					int step = 0;
					while(!list.isEmpty()) {
						int x = list.removeFirst();
						int y = list.removeFirst();
						for(int k=0; k<4; k++) {
							
						}
					}
				}
			}
		}
	}

}
