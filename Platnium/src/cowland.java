import java.io.*;
import java.util.*;
public class cowland {
	static ArrayList<Integer>[] edges;
	static int[] start;
	static int[] end;
	static int[] order;
	static int[] par;
	static int[] bitree;
	static int[] dep;
	static int c;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("cowland.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowland.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		bitree = new int[2*N+1];
		int[] dist = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
			dist[i] = Integer.parseInt(st.nextToken());
		start = new int[N];
		end = new int[N];
		edges = new ArrayList[N];
		for(int i=0; i<N; i++) edges[i] = new ArrayList<>();
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			edges[a].add(b);
			edges[b].add(a);
		}
		order = new int[2*N];
		par = new int[N];
		dep = new int[N];
		c = 0;
		recursion(0,0);
		for(int i=0; i<N; i++) {
			update(start[i]+1, dist[i]);
			update(end[i]+1, dist[i]);
		}
		int[][] sparse = new int[2*N][(int)Math.ceil(Math.log10(2*N)/Math.log10(2))+1];
		for(int i=0; i<2*N; i++) sparse[i][0] = par[order[i]];
		for(int i=1; i<(int)Math.ceil(Math.log10(2*N)/Math.log10(2))+1; i++) {
			for(int j=0; j<2*N; j++) {
				int next = j+(1<<i);
				if(next>=2*N) {
					sparse[j][i] = -1;
					break;
				}
				if(dep[sparse[j][i-1]]<dep[sparse[j+(1<<(i-1))][i-1]])
					sparse[j][i] = sparse[j][i-1];
				else
					sparse[j][i] = sparse[j+(1<<(i-1))][i-1];
			}
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			if(a==1) {
				int pos = Integer.parseInt(st.nextToken())-1;
				int val = Integer.parseInt(st.nextToken());
				update(start[pos]+1, dist[pos]);
				update(end[pos]+1, dist[pos]);
				dist[pos] = val;
				update(start[pos]+1, dist[pos]);
				update(end[pos]+1, dist[pos]);
			}
			else {
				int node1 = Integer.parseInt(st.nextToken())-1;
				int node2 = Integer.parseInt(st.nextToken())-1;
				if(node1==node2) {
					out.println(dist[node1]);
					continue;
				}
				if(end[node1]>end[node2]) {
					int save = node1;
					node1 = node2;
					node2 = save;
				}
				boolean b = false;
				int next = start[node2];
				if(next<end[node1]) {
					b = true;
					next = end[node2];
				}
				int ans = Math.max(0, query(next+1)^query(end[node1]));
				if(!b) {
					int val = N;
					int max = 0;
					int pos = end[node1];
					while(pos<next+1) {
						int dif = next+1-pos;
						int power = (int)Math.floor(Math.log10(dif)/Math.log10(2));
						if(dep[sparse[pos][power]]<val) {
							val = dep[sparse[pos][power]];
							max = sparse[pos][power];
						}
						pos+=(1<<power);
					}
					ans^=dist[max];
				}
				out.println(ans);
			}
		}
		out.close();
	}
	static void recursion(int node, int depth) {
		start[node] = c;
		dep[node] = depth;
		order[c++] = node;
		ArrayList<Integer> al = edges[node];
		int s = al.size();
		for(int i=0; i<s; i++) {
			int tar = al.get(i);
			if(tar == par[node]) continue;
			par[tar] = node;
			recursion(tar, depth+1);
		}
		end[node] = c;
		order[c++] = node;
	}
	static void update(int pos, int val) {
		for(; pos<bitree.length; pos+=((-pos)&pos))
			bitree[pos] ^= val;
	}
	static int query(int pos) {
		int ans = 0;
		for(; pos>0; pos-=((-pos)&pos))
			ans ^= bitree[pos];
		return ans;
	}
}
