import java.util.*;
import java.io.*;
public class goldp1 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		HashMap<Character, Integer> map = new HashMap<>();
		int a = 0;
		Set<Character> set = new HashSet<>();
		for(int i=0; i<str.length(); i++) {
			set.add(str.charAt(i));
			if(!map.containsKey(str.charAt(i))) 
				map.put(str.charAt(i), a++);
		}
		int N = set.size();
		int[][] counts = new int[N][N];
		for(int i=0; i<str.length()-1; i++)
			counts[map.get(str.charAt(i))][map.get(str.charAt(i+1))]++;
		int[] dp = new int[(1<<N)];
		Arrays.fill(dp, 1000000000);
		dp[0] = 0;
		LinkedList<Integer> list = new LinkedList<>();
		boolean[] visit = new boolean[(1<<N)];
		visit[0] = true;
		for(int i=0; i<N; i++) {
			dp[(1<<i)] = counts[i][i];
			list.add((1<<i));
			visit[(1<<i)] = true;
		}
		while(!list.isEmpty()) {
			int node = list.removeFirst();
			for(int i=0; i<N; i++) {
				if((node^(1<<i))>node) {
					int add = 0;
					int next = (node^(1<<i));
					for(int j=0; j<N; j++) {
						if((next^(1<<j))<next) 
							add+= counts[i][j];
					}
					if(dp[next]>dp[node]+add) {
						dp[next] = dp[node]+add;
						if(!visit[next]) {
							list.add(next);
							visit[next] = true;
						}
					}
				}
			}
		}
		out.write(dp[(1<<N)-1]+1+"\n");
		out.flush();
		out.close();
	}

}