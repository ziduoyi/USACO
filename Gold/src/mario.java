import java.io.*;
import java.util.*;


public class mario {

	@SuppressWarnings({ "resource", "unchecked" })
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("mario.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mario.out")));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		TreeSet<Integer>[] next = new TreeSet[100000];
		for(int i=0; i<100000; i++)
			next[i] = new TreeSet<>();
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken())-1;
			next[arr[i]].add(i);
		}
		int[] dp = new int[N];
		for(int i=0; i<N-1; i++) {
			if(next[arr[i]].ceiling(i+1)!=null) {
				int num = next[arr[i]].ceiling(i+1);
				dp[num] = Math.max(dp[num], dp[i]+1);
			}
			dp[i+1] = Math.max(dp[i+1], dp[i]);
			
		}
		out.println(N-dp[N-1]-1);
		out.close();
	}

}
