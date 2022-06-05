import java.io.*;
import java.util.*;
public class hopscotch {
	static int[][] bitree;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("hopscotch.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hopscotch.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long mod = 1000000007;
		int[][] arr = new int[R][C];
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		bitree = new int[R][C+1];
		update(C,1,R-1);
		int[][] dp = new int[R][C];
		dp[R-1][C-1] = 1;
		for(int i=R-2; i>-1; i--) {
			for(int j=C-2; j>-1; j--) {
				
			}
		}
	}
	static void update(int pos, int val, int node) {
		for(int i=pos; i<bitree[0].length; i+=(-i&i))
			bitree[node][i] += val;
	}
    public int sum(int j, int node) {
    	int s=0;
        for(;j>0; j-=(-j&j))
        	s+=bitree[node][j];
        return s;
    }
}
