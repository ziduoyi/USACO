import java.util.*;
import java.io.*;
public class cowjog {
	static int[] bitree;
	static HashMap<Long, Integer> map;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("cowjog.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowjog.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][] store = new int[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			store[i][0] = Integer.parseInt(st.nextToken());
			store[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(store, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[0]-o2[0];
			}
		});
		bitree = new int[N+1];
		long[] arr = new long[N];
		for(int i=0; i<N; i++)
			arr[i] = store[i][0] + ((long)store[i][1])*T;
		long[] save = arr.clone();
		Arrays.sort(save);;
		map = new HashMap<>();
		for(int i=0; i<N; i++) 
			map.put(save[i], N-i-1);
		int[] dp = new int[N];
		int ans = 0;
		for(int i=0; i<N; i++) {
			dp[i] = query(map.get(arr[i])+1)+1;
			update(map.get(arr[i])+1, dp[i]);
			ans = Math.max(ans, dp[i]);
		}
		out.println(ans);
		out.close();
	}
	static void update(int node, int val) {
		for(int i=node; i<bitree.length; i+=(-i&i))
			bitree[i]= Math.max(bitree[i], val);
	}
	static int query(int node) {
		int sum = 0;
		for(int i=node; i>0; i-=(-i&i))
			sum= Math.max(sum, bitree[i]);
		return sum;
	}
}
