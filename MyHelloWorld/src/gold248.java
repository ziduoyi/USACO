import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

//USACO 2016 US Open Contest, Gold Problem 3. 248
public class gold248 {

	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("262144.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("262144.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());

		int[] arr=new int[N];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		int res=0;
		int[][] dp = new int[N][N];
		for(int i=0;i<N;i++)
			dp[i][i]=arr[i];
		for(int len=2;len<=N;len++){
			for(int i=0;i<N;i++){
				int j=Math.min(i+len-1,N-1);
				for(int k=i;k<j;k++){
					if(dp[i][k]==dp[k+1][j] && dp[i][k]>0)
						dp[i][j]=Math.max(dp[i][j], dp[i][k]+1);
				}
			}
		}
		
		for(int[] a: dp)
			for(int x:a)
				res=Math.max(res, x);
		
		out.println(res);			
		out.close();
	}

}
