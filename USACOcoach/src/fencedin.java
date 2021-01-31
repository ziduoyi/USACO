import java.util.*;
import java.io.*;
public class fencedin {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("fencedin.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fencedin.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int len = Integer.parseInt(st.nextToken());
		int wid = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] ver = new int[N+2];
		int[] hor = new int[M+2];
		for(int i=1; i<N+1; i++)
			ver[i] = Integer.parseInt(br.readLine());
		ver[N+1]= wid;
		for(int i=1; i<M+1; i++)
			hor[i]= Integer.parseInt(br.readLine());
		hor[M+1] =len;
		Arrays.sort(ver);
		Arrays.sort(hor);
		boolean[][] visit = new boolean[N+1][M+1];
		int[][] in = new int[N+1][M+1];
		PriorityQueue<int[]> list = new PriorityQueue<>((a,b) -> a[2]-b[2]);
		visit[0][0] = true;
		int[] a1 = new int[] {0,1, ver[1]};
		int[] a2 = new int[] {1,0, hor[1]};
		list.add(a1);
		list.add(a2);
		int[][] direct = new int[][] {{1,0}, {0,1}, {-1,0}, {0,-1}};
		long ans=0;
		while(!list.isEmpty()) {
			int[] arr = list.poll();
			if(visit[arr[0]][arr[1]]==true)
				continue;
			ans +=arr[2];
			visit[arr[0]][arr[1]] = true;
			for(int i=0; i<4; i++) {
				int x = arr[0] + direct[i][0];
				int y = arr[1] + direct[i][1];
				if(x >=0 && x <N+1 && y>=0 && y<M+1) {
					if(visit[x][y] ==false) {
						int z ;
						if(x == arr[0]) 
							z = ver[x+1]-ver[x];
						else 
							z = hor[y+1]-hor[y];
						if(z<in[x][y] ||in[x][y]==0) {
							int[] temp = new int[] {x,y, z};
							list.add(temp);
							in[x][y] = z;
						}
					}
				}
			}
		}
		out.println(ans);
		out.close();
	}

}
