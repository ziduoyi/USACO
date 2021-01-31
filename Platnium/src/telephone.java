import java.io.*;
import java.util.*;
public class telephone {
	static ArrayList<Integer>[] edges;
	static boolean[] visit;
	static int K;
	static int ans;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		edges = new ArrayList[N];
		for(int i=0; i<N; i++)
			edges[i] = new ArrayList<>();
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			edges[a].add(b);
			edges[b].add(a);
		}
		visit = new boolean[N];
		visit[0] = true;
		ans = 0;
		dfs(0);
		System.out.println(ans);
	}
	static int dfs(int node) {
		int total = 0;
		if(edges[node].size()==1)
			total = 1;
		ArrayList<Integer> al = edges[node];
		int s = al.size();
		for(int i=0; i<s; i++) {
			int tar = al.get(i);
			if(visit[tar] ==false) {
				visit[tar] = true;
				total += dfs(tar);
			}
		}
		if(total<=2*K) {
			ans += total/2;
			return total%2;
		}
		ans += K;
		return 0;
	}

}
