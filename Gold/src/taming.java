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
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		int[][][] dp=new int[N][N][N];//breakouts of i number, prefix to i,last break at pos i
		int[][] none=new int[N][N];//inclusive
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				Arrays.fill(dp[i][j], Integer.MAX_VALUE);
			}
		}
		for(int i=0; i<N; i++) {
			int num=0;
			int count=0;
			for(int j=i; j<N; j++) {
				if(num!=arr[j])
					count++;
				num++;
				none[i][j]=count;
			}
		}
		for(int i=0; i<N; i++) {
			dp[0][i][0]=none[0][i];
		}
		for(int i=1;i<N; i++) {
			for(int j=i; j<N; j++) {
				for(int k=i; k<=j; k++) {
					if(k==j) {
						for(int l=0; l<j;l++) {
							dp[i][j][k]=Math.min(dp[i][j][k], dp[i-1][j-1][l]);
						}
					}
					if(k<j) {
						dp[i][j][k]=dp[i][j-1][k];
					}
					if(arr[j]!=j-k)
						dp[i][j][k]++;
				}
			}
		}
		for(int i=0; i<N; i++) {
			int min=Integer.MAX_VALUE;
			for(int j=0; j<N; j++) {
				if(dp[i][N-1][j]!=-1)
					min=Math.min(min, dp[i][N-1][j]);
			}
			out.println(min);
		}
		out.close();
	}

}
