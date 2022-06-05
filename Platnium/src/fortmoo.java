import java.io.*;
import java.util.*;
public class fortmoo {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("fortmoo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fortmoo.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] grid = new int[N+1][M+1];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<str.length(); j++) {
				grid[i+1][j+1] = grid[i+1][j]+grid[i][j+1]-grid[i][j];
				if(str.charAt(j)=='X')
					grid[i+1][j+1]++;
			}
		}
		int ans = 0;
		for(int j=0; j<M; j++) {
			for(int k=j; k<M; k++) {
				int last = -1;
				for(int i=0; i<N; i++) {
					boolean isRow = false; //work
					if(grid[i+1][k+1]-grid[i][k+1]-grid[i+1][j]+grid[i][j]==0)isRow = true;
					if(isRow)
						ans = Math.max(ans, k-j+1);
					if(isRow&&last!=-1)
						ans = Math.max(ans, (k-j+1)*(i-last+1));
					if(grid[i+1][k+1]-grid[i+1][k]-grid[i][k+1]+grid[i][k]!=0||grid[i+1][j+1]-grid[i+1][j]-grid[i][j+1]+grid[i][j]!=0)
						last=-1;
					if(isRow&&last==-1)
						last = i;
				}
			}
		}
		out.println(ans);
		out.close();
	}

}
