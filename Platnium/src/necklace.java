import java.io.*;
import java.util.*;
public class necklace {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String bessie = br.readLine();
		String other = br.readLine();
		int N = bessie.length();
		int M = other.length();
		int[] next = new int[M];
		kmpNext(other, next);
		//int[][] dp = new int[N+1][M];
		int[][] dp = new int[3][M];
		/*
		for(int i=0; i<N+1; i++)
			Arrays.fill(dp[i], 100000);
		*/
		for(int i=0; i<3; i++)
			Arrays.fill(dp[i], 100000);
		dp[0][0] = 0;
		for(int i=0; i<N; i++) {
			Arrays.fill(dp[(i+1)%3], 100000000);
			for(int j=0; j<M; j++) {
				int k = j;
				while(k!=-1&&bessie.charAt(i)!=other.charAt(k)) 
					k = next[k];
				if(k+1<M)
					dp[(i+1)%3][k+1] = Math.min(dp[(i+1)%3][k+1], dp[i%3][j]);
				dp[(i+1)%3][j] = Math.min(dp[(i+1)%3][j], dp[i%3][j]+1);
			}
		}
		int ans = 100000000;
		for(int i=0; i<M; i++)
			ans = Math.min(ans, dp[N%3][i]);
		System.out.println(ans);
	}
	
	static void kmpNext(String s, int[] next) {
		next[0]=-1;
		int i=0, j=-1;

		while(i<s.length()-1) {
			if(j==-1 || s.charAt(i)==s.charAt(j)) {
				i++;
				j++;
				next[i]=j;
			}else {
				j=next[j];
			}
		}
	}
}
