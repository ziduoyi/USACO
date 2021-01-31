import java.util.*;
import java.io.*;
public class nocross {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("nocross.in"));
		PrintWriter out=new PrintWriter(new FileWriter("nocross.out"));
		int N=scanner.nextInt();
		int[]arr1=new int[N];
		int [] arr2 = new int [N] ;
		for(int i=0; i<N; i++) {
			arr1[i]=scanner.nextInt()-1;
		}
		for(int i=0; i<N; i++) {
			arr2[i]=scanner.nextInt()-1;
		}
		int[][] dp=new int[N][N];
		for(int i=0; i<N; i++) {
			dp[i][0]=1;
			dp[0][i]=1;
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr1[i]-arr2[j]>4||arr2[j]-arr1[i]>4) {
					dp[i][j]=-1;
				}
			}
		}
		for(int i=1; i<N; i++) {
			dp[i][0]=Math.max(dp[i-1][0], dp[i][0]);
			dp[0][i]=Math.max(dp[0][i-1], dp[0][i]);
		}
		for(int i=1; i<N; i++) {
			for(int j=1; j<N; j++) {
				if(dp[i][j]==-1) {
					dp[i][j]=Math.max(Math.max(dp[i-1][j],0),Math.max(dp[i][j-1],0));
				}
				else {
					dp[i][j]=Math.max(Math.max(dp[i-1][j-1],0)+1,Math.max(Math.max(dp[i-1][j],0),Math.max(dp[i][j-1],0)));
				}
			}
		}
		int m=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				m=Math.max(m, dp[i][j]);
			}
		}
		out.println(m);
		out.close();
		
	}

}
