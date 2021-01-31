import java.util.*;
import java.io.*;
public class barnpainting {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("barnpainting.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barnpainting.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] list = new ArrayList[N];
		for(int i=0; i<N; i++)
			list[i] = new ArrayList<>();
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			list[x].add(y);
			list[y].add(x);
		}
		long[][] nodes = new long[N][3];
		int[] color = new int[N];
		Arrays.fill(color, -1);
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int a =Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			color[a]=b;
		}
		long[] ans = recursion(list,nodes, new boolean[N], 0,color);
		out.println((ans[0]+ans[1]+ans[2])%1000000007);
		out.close();
	}
	static long[] recursion(ArrayList<Integer>[] edges, long[][] nodes, boolean[] visited, int curr, int[] color) {
		visited[curr] = true;
		ArrayList<Integer> al = edges[curr];
		boolean b =false;
		nodes[curr][0] =1;
		nodes[curr][1] =1;
		nodes[curr][2] =1;
		if(color[curr]!=-1) {
			for(int j=0; j<3; j++) {
				if(j != color[curr]) {
					nodes[curr][j]=0;
				}
			}
		}
		for(int i=0; i<al.size(); i++) {
			int node = al.get(i);
			if(visited[node]==true)
				continue;
			b = true;
			visited[node] = true;
			long[] adds = recursion(edges, nodes, visited, node,color);
			if(color[node]!=-1) {
				nodes[curr][color[node]] = 0;
			}
			for(int j=0; j<3; j++) {
				long sum=0;
				for(int k =0; k<3; k++) {
					if(j==k)
						continue;
					sum +=adds[k];
					sum %=1000000007;
				}
				nodes[curr][j] *= sum;
				nodes[curr][j] %= 1000000007;
			}
		}
		if(b==false&&color[curr]==-1) {
			return new long[] {1,1,1};
		}
		if(b==false) {
			long[] ans = new long[3];
			Arrays.fill(nodes[curr], 0);
			ans[color[curr]]=1;
			nodes[curr][color[curr]]=1;
			return ans;
		}
		return nodes[curr];
			
	}
}
