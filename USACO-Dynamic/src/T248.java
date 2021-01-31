import java.util.*;
import java.io.*;
public class T248 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("248.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("248.out")));
		int N=Integer.parseInt(br.readLine());
		int[] arr=new int[N];
		for(int i=0; i<N; i++)
			arr[i]=Integer.parseInt(br.readLine());
		int[][] dp=new int[N][N];
		int max=0;
		for(int len =0; len<N; len++) {
			for(int i=0; i+len<N; i++) {
				int j=i+len;
				if(i==j) {
					dp[i][j]=arr[i];
					max=Math.max(dp[i][j], max);
					continue;
				}
				int curr=0;
				for(int k=i; k<j; k++) {
					if(dp[i][k]==dp[k+1][j]&&dp[i][k]!=-1)
						curr=Math.max(curr, dp[i][k]+1);
				}
				dp[i][j]=curr;
				max=Math.max(curr, max);
			}
		}
		out.println(max);
		out.close();
	}

}
