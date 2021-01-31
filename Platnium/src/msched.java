import java.io.*;
import java.util.*;
public class msched {
	static ArrayList<Integer>[] edges;
	static int[] dist;
	static int[] cost;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		cost = new int[N];
		for(int i=0; i<N; i++)
			cost[i] = Integer.parseInt(br.readLine());
		edges = new ArrayList[N];
		for(int i=0; i<N; i++)
			edges[i] = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			edges[b].add(a);
		}
		dist = new int[N];
		Arrays.fill(dist, 1000000000);
		for(int i=0; i<N; i++) {
			if(dist[i]==1000000000)
				recursion(i);
		}
		int ans = 0;
		for(int i=0; i<N; i++)
			ans = Math.max(ans, dist[i]);
		System.out.println(ans);
	}
	static void recursion(int node) {
		ArrayList<Integer> al = edges[node];
		int s = al.size();
		int max = 0;
		for(int i=0; i<s; i++) {
			int tar = al.get(i);
			if(dist[tar]==1000000000)
				recursion(tar);
			max = Math.max(max, dist[tar]);
		}
		dist[node] = cost[node] + max;
	}
	
}
