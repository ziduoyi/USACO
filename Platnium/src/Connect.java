import java.io.*;
import java.util.*;
public class Connect {
	static int[][][] dp;
	static char[][] arr;
	static String[][] states;
	static int N;
	static int M;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken())/2;
		M = Integer.parseInt(st.nextToken())/2;
		arr = new char[2*N+1][2*M+1];
		for(int i=0; i<2*N+1; i++) {
			String str = br.readLine();
			for(int j=0; j<2*M+1; j++)
				arr[i][j] = str.charAt(j);
		}
		dp = new int[N][M][1<<(N+1)];
		states = new String[][] {
			{ "+ +", "+ +", "+.+", "+ +", "+ +", "+ +", "+.+", "+.+", "+.+", "+ +", "+ +" },
			{ "   ", ".X ", " X ", " X.", " X ", "...", " . ", ".. ", " ..", " ..", ".. " },
			{ "+ +", "+ +", "+ +", "+ +", "+.+", "+ +", "+.+", "+ +", "+ +", "+.+", "+.+" },
		};
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		
	}
	static void recursion(int row, int col, int mask) {
		
	}
}
