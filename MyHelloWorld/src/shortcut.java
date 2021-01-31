import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;


class Edge{
	int n;
	int t;
	public Edge(int x, int y){
		n=x;
		t=y;
	}
}

//USACO 2019 January Contest, Gold
public class shortcut {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("shortcut.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N =Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		int T=Integer.parseInt(st.nextToken());
		int[] nums = new int[N+1];

		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++)
			nums[i]=Integer.parseInt(st.nextToken());

		ArrayList<Edge>[] edges = new ArrayList[N+1];
		for(int i=1;i<=N;i++)
			edges[i]=new ArrayList<Edge>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			edges[r].add(new Edge(c,t));
			edges[c].add(new Edge(r,t));
		}

		//Dijkstra with path remembered & tie break
		LinkedList<Integer> list = new LinkedList<>();
		int[] dist = new int[N+1];
		int[] prev = new int[N+1];					//set up the path
		Arrays.fill(dist, 260000000);
		dist[1]=0;
		list.add(1);
		while(!list.isEmpty()){
			int u = list.removeFirst();
			for(Edge e : edges[u]){
				int v = e.n, t = e.t;
				if(dist[v]>dist[u]+t || dist[v]==dist[u]+t && u<prev[v]){ //path tie break here
					dist[v]=dist[u]+t;
					list.add(v);
					prev[v]=u;					//path remembered
				}
			}
		}

		//find leaf node
//		boolean[] notLeaf = new boolean[N];
//		for(int x : prev)
//			notLeaf[x]=true;
		
		
		int[] copy = nums.clone();
		for(int i=2;i<=N;i++){	//how to optimize here???
			int x=prev[i];
			while(x>1){
				nums[x]+=copy[i];
				x=prev[x];
				if(dist[x]<=T)
					break;
			}
		}
		long res=0;
		for(int i=2;i<=N;i++){
			if(dist[i]>T)
				res=Math.max(res, ((long)(dist[i]-T))*nums[i]);
		}
		out.println(res);
		out.close();
	}

}
