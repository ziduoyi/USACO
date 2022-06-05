import java.io.*;
import java.util.*;
public class fire {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] arr = new char[R][C];
		boolean[][] visitJ = new boolean[R][C];
		boolean[][] visitF = new boolean[R][C];
		LinkedList<int[]> joe = new LinkedList<>();
		LinkedList<int[]> fire = new LinkedList<>();
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				arr[i][j] = str.charAt(j);
				if(arr[i][j] == 'J') {
					visitJ[i][j] = true;
					joe.add(new int[] {i,j});
				}
				if(arr[i][j] == 'F') {
					visitF[i][j] = true;
					fire.add(new int[] {i,j});
				}
			}
		}
		int[][] direct = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
		int step = 1;
		boolean b = false;
		while(!joe.isEmpty()) {
			int s = fire.size();
			for(int i=0; i<s; i++) {
				int[] pos = fire.removeFirst();
				for(int j = 0; j<4; j++) {
					int x = pos[0] + direct[j][0];
					int y = pos[1] + direct[j][1];
					if(x>=R||x<0||y>=C||y<0)continue;
					if(!visitF[x][y]&&arr[x][y]!='#') {
						visitF[x][y] = true;
						fire.add(new int[] {x,y});
					}
				}
			}
			s = joe.size();
			for(int i=0; i<s; i++) {
				int[] pos = joe.removeFirst();
				for(int j = 0; j<4; j++) {
					int x = pos[0] + direct[j][0];
					int y = pos[1] + direct[j][1];
					if(x>=R||x<0||y>=C||y<0) {
						b = true;
						break;
					}
					if(!visitF[x][y]&&arr[x][y]!='#'&&!visitJ[x][y]) {
						visitJ[x][y] = true;
						joe.add(new int[] {x,y});
					}
				}
				if(b)break;
			}
			if(b)break;
			step++;
		}
		if(b)
			out.write(step+"\n");
		else
			out.write("IMPOSSIBLE\n");
		out.close();
	}

}
