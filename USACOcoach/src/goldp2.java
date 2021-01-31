import java.io.*;
import java.util.*;
public class goldp2 {

	
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("threesum.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("threesum.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		long[][] ans = new long[N][N];
		long[][] sum = new long[N][N];
		int[] count = new int[2000001];
		for(int i=0; i<N-2; i++) {
			for(int j=i+2; j<N; j++) {
				count[arr[j-1]+1000000]++;
				if((-1*(arr[i]+arr[j]))+1000000<=2000000&&(-1*(arr[i]+arr[j]))+1000000>=0)
					sum[i][j] = count[(-1*(arr[i]+arr[j]))+1000000];
			}
			for(int j=i+2; j<N; j++)
				count[arr[j-1]+1000000]--;
		}
		for(int len =3; len<=N; len++) {
			for(int i=0; i+len-1<N; i++) {
				int j = i+len-1;
				ans[i][j] = ans[i+1][j] +ans[i][j-1]-ans[i+1][j-1] + sum[i][j];
			}
		}
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			out.println(ans[a][b]);
		}
		out.close();
	}
}
