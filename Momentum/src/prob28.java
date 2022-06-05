import java.io.*;
import java.util.*;
public class prob28 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner br = new Scanner(System.in);
		int m = br.nextInt();
		int n = br.nextInt();
		LinkedList<Integer> list = new LinkedList<>();
		boolean[][] visit = new boolean[n][m];
		int[][] grid = new int[n][m];
		for(int i=0; i<n; i++) {
			String str = br.next();
			for(int j=0; j<m; j++) {
				if(str.charAt(j)=='T') {
					visit[i][j] = true;
					grid[i][j] = 2;
					list.add(i);
					list.add(j);
				}
				if(str.charAt(j)=='-') 
					grid[i][j] = 1;
				
				if(str.charAt(j)=='D') 
					grid[i][j] = 3;
				if(str.charAt(j)=='W')
					grid[i][j] = 0;
			}
		}
		int[][] direct = new int[][] {{0,-1},{-1,0},{1,0},{0,1}};
		int ans = 0;
		boolean b = false;
		while(!list.isEmpty()) {
			ans++;
			int s = list.size();
			for(int i=0; i<s/2; i++) {
				int x = list.removeFirst();
				int y = list.removeFirst();
				for(int j=0; j<4; j++) {
					int x1 = x + direct[j][0];
					int y1 = y + direct[j][1];
					if(x1<0||x1>=n||y1<0||y1>=m)continue;
					if(!visit[x1][y1]&&grid[x1][y1]>0) {
						visit[x1][y1] = true;
						list.add(x1);
						list.add(y1);
						if(grid[x1][y1]==3) {
							b = true;
							break;
						}
					}
				}
				if(b)break;
			}
			if(b)break;
		}
		System.out.println(ans);
	}

}
