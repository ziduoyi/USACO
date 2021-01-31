import java.util.*;
public class vidgame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int V = scanner.nextInt();
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] list = new ArrayList[N];
		int[] costs = new int[N];
		for(int i=0; i<N; i++)
			list[i] = new ArrayList<>();
		for(int i=0; i<N; i++) {
			costs[i] = scanner.nextInt();
			int n = scanner.nextInt();
			for(int j=0; j<n; j++) {
				list[i].add(scanner.nextInt());
				list[i].add(scanner.nextInt());
			}
		}
		long[] dp = new long[V+1];
		for(int i=0; i< N; i++) {
			long[] curr = dp.clone();
			for(int j=0; j<list[i].size(); j+=2) {
				for(int k = V; k>=0; k--) {
					int pos = k-list[i].get(j);
					if(pos<0)
						break;
					curr[k] = Math.max(curr[k], curr[pos]+list[i].get(j+1));
				}
			}
			for(int j=V; j>=costs[i]; j--) {
				dp[j] = Math.max(dp[j], curr[j-costs[i]]);
			}
		}
		long ans = 0;
		for(int i=0; i<=V; i++)
			ans = Math.max(ans, dp[i]);
		System.out.println(ans);
	}

}
