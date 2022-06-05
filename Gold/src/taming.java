import java.util.*;
import java.io.*;
public class taming {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("taming.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));
		int N=Integer.parseInt(br.readLine());
		int[] arr=new int[N];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
			arr[i]=Integer.parseInt(st.nextToken());
		int[][][] dp = new int[N][N][N+1];
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				Arrays.fill(dp[i][j], 1000000000);
		if(arr[0]!=0)dp[0][0][1] = 1;
		else dp[0][0][1] = 0;	
		for(int i=1; i<N; i++) {
			for(int j=0; j<=i; j++) {
				for(int k=1; k<=i+1; k++) {
					if(j<i)
						dp[i][j][k] = dp[i-1][j][k];
					else
						for(int l=0; l<i; l++)
							dp[i][j][k] = Math.min(dp[i][j][k], dp[i-1][l][k-1]);
					if(arr[i]!=i-j)
						dp[i][j][k]++;
				}
			}
		}
		for(int i=1; i<=N; i++) {
			int ans = 1000000000;
			for(int j=0; j<N; j++)
				ans = Math.min(ans, dp[N-1][j][i]);
			out.println(ans);
		}
		out.close();
	}

}
