import java.io.*;
import java.util.*;
public class talent {

	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("talent.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("talent.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int W=Integer.parseInt(st.nextToken());
		double[][] arr=new double[N][3];
		
		//double[] g = new double[lines];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
			arr[i][2]=arr[i][1]/arr[i][0];
		}
		Arrays.sort(arr, (a,b)->((Double)b[2]).compareTo(a[2]));
		//Arrays.sort(arr, (a,b)->(a[2]>b[2]?1:(a[2]<b[2]?-1:0)));
		//Arrays.sort(arr, (a,b)->{if(a[2]>b[2])return 1;
									//else if(a[2]<b[2]) return -1;
									//else return 0;});

		double res=0.0;

		
		if(arr[0][0]>=W) res=Math.max(res, arr[0][2]);

		double[] dp = new double[W+1];
		Arrays.sort(arr, (a,b)->((Double)a[0]).compareTo(b[0]));
		Arrays.fill(dp, -1);
		dp[0]=0.0;
		
		for(int i=0;i<N;i++){
			for(int j=W;j>=0;j--){
				if(dp[j]>=0){
					int x=(int) (j+arr[i][0]);
					if(x>=W){
						dp[W]=Math.max(dp[W], (dp[j]+arr[i][1])/x);
					}else
						dp[x]=Math.max(dp[x], dp[j]+arr[i][1]);
				}
			}
		}
		
		res=Math.max(res, dp[W]);
		res*=1000;
		out.println((int)res);
		out.close();
	}
}