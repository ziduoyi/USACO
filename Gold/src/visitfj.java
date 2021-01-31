import java.io.*;
import java.util.*;
public class visitfj {
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("visitfj.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("visitfj.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int T=Integer.parseInt(st.nextToken());
		int[][] arr=new int[N][N];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		long[][][] dist=new long[N][N][3];
		for(int i=0; i<N; i++) 
			for(int j=0; j<N; j++)
				Arrays.fill(dist[i][j], Long.MAX_VALUE);
		dist[0][0][0]=0;
		int[][] direct=new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
		LinkedList<int[]>list=new LinkedList<>();//x,y,trial #
		int[] z=new int[3];
		list.add(z);
		while(!list.isEmpty()) {//simpify
			int[] use=list.removeFirst();
			if(use[0]==N-1&&use[1]==N-1)
				continue;
			for(int i=0; i<4; i++) {
				int x=use[0]+direct[i][0];
				int y=use[1]+direct[i][1];
				if(x>-1&&x<N&&y>-1&&y<N) {
					int c=use[2];
					int e=0;
					if(c==3) {
						c=0;
					}
					if(c==2) {
						e=arr[x][y];
					}
					int d=0;
					if(use[2]==0) 
						d=1;
					if(use[2]==1) 
						d=2;
					if(use[2]==2) 
						d=0;
					if(use[2]==3)
						d=1;
					if(dist[use[0]][use[1]][c]+T+e<dist[x][y][d]) {
						dist[x][y][d]=dist[use[0]][use[1]][c]+T+e;
						int[] temp=new int[3];
						if(use[2]==3) 
							temp[2]=1;
						else
							temp[2]=use[2]+1;
						temp[0]=x;
						temp[1]=y;
						list.add(temp);
					}
				}
			}
		}
		long min=Long.MAX_VALUE;
		for(int i=0; i<3; i++) {
			min=Math.min(min, dist[N-1][N-1][i]);
		}
		out.println(min);
		out.close();
	}

}
