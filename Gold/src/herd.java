import java.io.*;
import java.util.*;
public class herd {
	static int[] dp;
	static int[][] dist;
	static int[] times;
	static int dif;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int N = str.length();
		HashSet<Character> set = new HashSet<>();
		for(int i=0; i<N; i++)
			set.add(str.charAt(i));
		HashMap<Character, Integer> map = new HashMap<>();
		dif = set.size();
		int a = 0;
		for(char c: set) 
			map.put(c, a++);
		int[] arr = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = map.get(str.charAt(i));
		HashSet<Integer>[] save = new HashSet[dif];
		for(int i=0; i<dif; i++)save[i] = new HashSet<>();
		for(int i=0; i<N; i++)
			save[arr[i]].add(i);
		dist = new int[dif][dif];
		for(int i=0; i<dif; i++) {
			for(int j=0; j<dif; j++) {
				int curr = 0;
				for(int num: save[i])
					if(save[j].contains(num+1))
						curr++;
				dist[i][j] = curr;
			}
		}
		times = new int[dif+1];
		times [0] = 1;
		for(int i=1; i<=dif; i++)times[i] = times[i-1]*2;
		dp = new int[times[dif]];
		Arrays.fill(dp, 1000000000);
		dp[0] = 1;
		recursion(times[dif]-1);
		System.out.println(dp[times[dif]-1]);
	}
	static void recursion(int node) {
		for(int i=0; i<dif; i++) {
			if((node^times[i])<node) {
				int count = 0;
				for(int j=0; j<dif; j++) {
					if((node^times[j])<node) {
						count+=dist[i][j];
					}
				}
				int now = node^times[i];
				if(dp[now]==1000000000)
					recursion(now);
				dp[node] = Math.min(dp[node], dp[now]+count);
			}
		}
	}
}
