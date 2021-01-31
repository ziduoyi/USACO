import java.io.*;
import java.util.*;
public class robots {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] N = new int[2], M = new int[2];
		int[][][] arr = new int[2][][];
		int[][] start = new int[2][2];
		int[][] direct = new int[][] {{-1,0},{0,1},{1,0},{0,-1}};
		int[][][][] possible = new int[2][][][];
		for(int a = 0; a<2; a++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N[a] = Integer.parseInt(st.nextToken());
			M[a] = Integer.parseInt(st.nextToken());
			arr[a] = new int[N[a]][M[a]];
			for(int i=0; i<N[a]; i++) {
				String str = br.readLine();
				for(int j=0; j<M[a]; j++) {
					if(str.charAt(j) == '#')
						arr[a][i][j] = 1;
					else {
						if(str.charAt(j) == 'X') {
							start[a][0] = i;
							start[a][1] = j;
						}
						arr[a][i][j] = 0;
					}
				}
			}
			int num = Integer.parseInt(br.readLine());
			possible[a] = new int[N[a]][M[a]][12];
			for(int i=0; i<num; i++) {
				st = new StringTokenizer(br.readLine());
				int z = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				int c = Integer.parseInt(st.nextToken())-1;
				String s = st.nextToken();
				int type = -1;
				if(s.equals("N"))
					type = 4;
				if(s.equals("E"))
					type = 5;
				if(s.equals("S"))
					type = 6;
				if(s.equals("W"))
					type = 7;
				possible[a][z][b][0] = (type+2)%4 +4;
				for(int j=1; j<=c; j++) {
					z+=direct[type-4][0];
					b+=direct[type-4][1];
					possible[a][z][b][j] = type;
				}
				for(int j=c+1; j<=2*c; j++) {
					z-=direct[type-4][0];
					b-=direct[type-4][1];
					possible[a][z][b][j] = (type+2)%4+4;
				}
				int k = 2*c+1;
				while(k<12) {
					for(int j=1; j<=c; j++) {
						z+=direct[type-4][0];
						b+=direct[type-4][1];
						possible[a][z][b][k] = type;
						k++;
					}
					for(int j=c+1; j<=2*c; j++) {
						z-=direct[type-4][0];
						b-=direct[type-4][1];
						possible[a][z][b][k%12] = (type+2)%4+4;
						k++;
					}
				}
			}
		}
		int ans = 1000000000;
		int[][][][] half = new int[2][][][];
		half[0] = new int[N[0]][M[0]][12];
		half[1] = new int[N[1]][M[1]][12];
		for(int i=0; i<2; i++)
			for(int j=0; j<half[i].length; j++)
				for(int k=0; k<half[i][j].length; k++)
					Arrays.fill(half[i][j][k], 1000000000);
		int[][][][][] dist = new int[N[0]][M[0]][N[1]][M[1]][12];
		for(int i=0; i<N[0]; i++)
			for(int j=0; j<M[0]; j++)
				for(int k=0; k<N[1]; k++)
					for(int l=0; l<M[1]; l++)
						Arrays.fill(dist[i][j][k][l], 1000000000);
		dist[start[0][0]][start[0][1]][start[1][0]][start[1][1]][0] = 0;
		LinkedList<int[]> list = new LinkedList<>();
		list.add(new int[] {start[0][0], start[0][1], start[1][0], start[1][1], 0});
		while(!list.isEmpty()) {
			int[] node = list.removeFirst();
			if(node[0]==-2&&node[2]==-2)
				continue;
			if(node[0] ==-2||node[2]==-2) {
				if(node[0]==-2){
					for(int i=0; i<4; i++) {
						int x = node[2] + direct[i][0];
						int y = node[3] + direct[i][1];
						if(x<0||x>=N[1]||y<0||y>=M[1])
							x = -2;
						else {
							if(possible[1][x][y][(node[4]+1)%12]>0||(possible[1][node[2]][node[3]][(node[4]+1)%12]==(i+2)%4+4))
								continue;
							if(arr[1][x][y]==1) {
								x = node[2];
								y = node[3];
							}
						}
						if(x==-2) 
							ans = Math.min(ans, half[1][node[2]][node[3]][node[4]]+1);
						else if(half[1][node[2]][node[3]][node[4]]+1 < half[1][x][y][(node[4]+1)%12]) {
							half[1][x][y][(node[4]+1)%12] = half[1][node[2]][node[3]][node[4]]+1;
							list.add(new int[] {-2, -2, x, y, (node[4]+1)%12});
						}
					}
				}
				else {
					for(int i=0; i<4; i++) {
						int x = node[0] + direct[i][0];
						int y = node[1] + direct[i][1];
						if(x<0||x>=N[0]||y<0||y>=M[0])
							x = -2;
						else {
							if(possible[0][x][y][(node[4]+1)%12]>0||(possible[0][node[0]][node[1]][(node[4]+1)%12]==(i+2)%4+4))
								continue;
							if(arr[0][x][y]==1) {
								x = node[0];
								y = node[1];
							}
						}
						if(x==-2) 
							ans = Math.min(ans, half[0][node[0]][node[1]][node[4]]+1);
						else if(half[0][node[0]][node[1]][node[4]]+1 < half[0][x][y][(node[4]+1)%12]) {
							half[0][x][y][(node[4]+1)%12] = half[0][node[0]][node[1]][node[4]]+1;
							list.add(new int[] {x,y,-2,-2, (node[4]+1)%12});
						}
					}
				}
			}
			else {
				for(int i=0; i<4; i++) {
					int[] x = new int[] {node[0] + direct[i][0], node[2] + direct[i][0]};
					int[] y = new int[] {node[1] + direct[i][1], node[3] + direct[i][1]};
					boolean b = false;
					for(int j=0; j<2; j++) {
						if(x[j]<0||x[j]>=N[j]||y[j]<0||y[j]>=M[j]) 
							x[j] = -2;
						else {
							if(possible[j][x[j]][y[j]][(node[4]+1)%12]>0||(possible[j][node[2*j]][node[2*j+1]][(node[4]+1)%12]==((i+2)%4+4)))
								b = true;
							if(arr[j][x[j]][y[j]] == 1) {
								x[j] = node[2*j];
								y[j] = node[2*j+1];
							}
						}
					}
					if(!b) {
						if(x[0]==-2&&x[1]==-2) {
							ans = Math.min(ans, dist[node[0]][node[1]][node[2]][node[3]][node[4]]+1);
							continue;
						}
						if(x[0] ==-2||x[1]==-2) {
							if(x[0]==-2){
								if(dist[node[0]][node[1]][node[2]][node[3]][node[4]] +1 <half[1][x[1]][y[1]][(node[4]+1)%12]) {
									half[1][x[1]][y[1]][(node[4]+1)%12] = dist[node[0]][node[1]][node[2]][node[3]][node[4]] +1;
									list.add(new int[] {-2,-2,x[1],y[1],(node[4]+1)%12});
								}
							}
							else {
								if(dist[node[0]][node[1]][node[2]][node[3]][node[4]] +1 <half[0][x[0]][y[0]][(node[4]+1)%12]) {
									half[0][x[0]][y[0]][(node[4]+1)%12] = dist[node[0]][node[1]][node[2]][node[3]][node[4]] +1;
									list.add(new int[] {x[0],y[0],-2,-2,(node[4]+1)%12});
								}
							}
						}
						else {
							if(dist[node[0]][node[1]][node[2]][node[3]][node[4]] +1<dist[x[0]][y[0]][x[1]][y[1]][(node[4]+1)%12]) {
								dist[x[0]][y[0]][x[1]][y[1]][(node[4]+1)%12] = dist[node[0]][node[1]][node[2]][node[3]][node[4]] +1;
								list.add(new int[] {x[0], y[0], x[1], y[1], (node[4]+1)%12});
							}
						}
					}
				}
			}
		}
		if(ans == 1000000000)
			System.out.println(-1);
		else
			System.out.println(ans);
		
	}
}

