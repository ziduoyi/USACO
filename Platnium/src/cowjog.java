import java.io.*;
import java.util.*;
public class cowjog {
	static ArrayList<Integer>[] edges;
	static int[][] dist;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		edges = new ArrayList[N];
		for(int i=0; i<N; i++)
			edges[i] = new ArrayList<>();
		for(int i=0; i<M; i++) { // a -> b path
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			edges[b].add(a);
			edges[b].add(c);
		}
		dist = new int[N][K];
		for(int i=0; i<N; i++)
			Arrays.fill(dist[i], 1000000000);
		dist[N-1][0] = 0;
		recursion(0);
		for(int i=0; i<K; i++) {
			if(dist[0][i] == 1000000000)
				System.out.println(-1);
			else
				System.out.println(dist[0][i]);
		}
	}
	static void recursion(int node) {
		ArrayList<Integer> al = edges[node];
		int s = al.size();
		ArrayList<Integer> arr = new ArrayList<>();
		for(int i=0; i<s; i+=2) {
			int tar = al.get(i);
			int cost = al.get(i+1);
			if(dist[tar][0] == 1000000000)
				recursion(tar);
			for(int j=0; j<dist[0].length; j++) {
				if(dist[tar][j] == 1000000000)
					break;
				arr.add(dist[tar][j] + cost);
			}
		}
		Collections.sort(arr);
		for(int i=0; i<Math.min(dist[0].length, arr.size()); i++)
			dist[node][i] = arr.get(i);
	}
}
