import java.io.*;
import java.util.*;
public class talent {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("talent.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("talent.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int lines=Integer.parseInt(st.nextToken());
		int min=Integer.parseInt(st.nextToken());
		int[][] arr=new int[lines][2];
		//double[] g = new double[lines];
		for(int i=0; i<lines; i++) {
			st=new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr,(a,b)->a[0]-b[0]);
		int[] dp=new int[min];
		double ans=0;
		dp[0]=0;
		for(int i=0; i<lines; i++) {
			for(int j=min-1; j>=0; j--) {
				if(dp[j]>0||j==0) {
					int x=arr[i][0]+j;
					if(x>=min) {
						ans=Math.max(ans, (double)(dp[j]+arr[i][1])/x);
					}
					else {
						dp[x]=Math.max(dp[x], dp[j]+arr[i][1]);
					}
				}
			}
		}
		ans*=1000;
		out.println((int)ans);
		out.close();
	}

}
