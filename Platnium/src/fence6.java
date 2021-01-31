import java.io.*;
import java.util.*;
public class fence6 {
	static HashSet<Integer>[][] edges;
	static int ans;
	static int[][] dist;
	static int[] costs;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		edges = new HashSet[N][2];
		costs = new int[N];
		for(int i=0; i<N; i++) {
			edges[i][0] = new HashSet<>();
			edges[i][1] = new HashSet<>();
		}
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			costs[a] = b;
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<c; j++) 
				edges[a][0].add(Integer.parseInt(st.nextToken())-1);
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<d; j++)
				edges[a][1].add(Integer.parseInt(st.nextToken())-1);
		}
		int print = 1000000;
		for(int i=0; i<N; i++) {
			ans = 1000000;
			dist = new int[N][2];
			for(int j=0; j<N; j++) {
				dist[j][0] = 1000000;
				dist[j][1] = 1000000;
			}
			dist[i][0] = 0;
			dist[i][1] = 0;
			recursion(i, 0, -1);
			for(int j=0; j<N; j++) {
				dist[j][0] = 1000000;
				dist[j][1] = 1000000;
			}
			dist[i][0] = 0;
			dist[i][1] = 0;
			recursion(i, 1, -1);
			print = Math.min(print, ans);
		}
		System.out.println(print);
	}
	static void recursion(int node, int side, int last) {
		HashSet<Integer> set = edges[node][side];
		for(int tar: set) {
			if(tar==last)
				continue;
			if(dist[tar][0] ==0) 
				ans = Math.min(ans, dist[node][side] + costs[tar]);
			else {
				HashSet<Integer> comp = edges[tar][0];
				if(comp.contains(node)) {
					if(dist[tar][1] == 1000000) {
						dist[tar][1] = dist[node][side] + costs[tar];
						recursion(tar, 1, node);
						dist[tar][1] = 0;
					}
				}
				else {
					if(dist[tar][0] == 1000000) {
						dist[tar][0] = dist[node][side] + costs[tar];
						recursion(tar, 0, node);
						dist[tar][0] = 1000000;
					}
				}
			}
		}
	}
}
