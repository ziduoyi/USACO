import java.io.*;
import java.util.*;
public class milk4 {
	static int[] last;
	static int[] nlast;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Q = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		Integer[] values = new Integer[P];
		for(int i=0; i<P; i++)
			values[i] = Integer.parseInt(br.readLine());
		Arrays.sort(values, new Comparator<Integer>(){
			@Override
			public int compare(Integer arg0, Integer arg1) {
				// TODO Auto-generated method stub
				return arg1-arg0;
			}	
		});
		int[] dp = new int[Q+1];
		last = new int[Q+1];
		nlast = new int[Q+1];
		Arrays.fill(nlast, -1);
		Arrays.fill(last, -1);
		Arrays.fill(dp, 1000000);
		dp[0] = 0;
		for (int i = 0; i < P; i++) {
			int[] dp2 = new int[Q+1];
			int[] last2 = new int[Q+1];
			int[] nlast2 = new int[Q+1];
			for (int j = 0; j <= Q; j++) {
			 dp2[j] = dp[j];
			 last2[j] = last[j];
			 nlast2[j] = nlast[j];
			}
			for (int j = values[i]; j <= Q; j++) {
				int prev = j - values[i];
				if (dp2[prev] < 1000000) {
					if (last2[prev] == values[i]) {
						last2[j] = values[i];
						dp2[j] = dp2[prev];
						nlast2[j] = nlast2[prev] + 1;
					}
					if (last2[prev] != values[i]) {
						last2[j] = values[i];
						dp2[j] = dp2[prev] + 1;
						nlast2[j] = 1;
					}
					if (dp[prev] < 1000000 && (dp[prev] + 1 < dp2[j] || (dp[prev] + 1 == dp2[j] && (check (prev, j - nlast2[j] * values[i]))!=0))) {//
							last2[j] = values[i];
							dp2[j] = dp[prev] + 1;
							nlast2[j] = 1;
					}
				}
			}
			for (int j = values[i]; j <= Q; j++) {
				if (dp2[j] <= dp[j]) {
					dp[j] = dp2[j];
					last[j] = last2[j];
					nlast[j] = nlast2[j];
				}
			}
		}
		int[] ans = new int[dp[Q]];
		int curr = Q;
		for(int i=0; i<ans.length; i++) {
			ans[i] = last[curr];
			curr -= last[curr]*nlast[curr];
		}
		System.out.print(ans.length);
		for(int i=0; i<ans.length; i++) 
			System.out.print(" "+ ans[i]);
		System.out.println();
		
	}
	static int check (int a, int b){
		while (a!=0 && b!=0) {//
			if (last[a] < last[b])
				return 1;
			if (last[a] > last[b])
				return 0;
			a -= nlast[a] * last[a];
			b -= nlast[b] * last[b];
		}
		if (a!=0) //
			return 0;
		return 1;
	}
}
