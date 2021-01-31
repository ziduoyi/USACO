 import java.util.*;
import java.io.*;

public class AdvancedCow {

	static ArrayList<Integer>[] edges;
	static int[] ev;
	
	static int[] size;
	static int[] id;	// old index --> new index
	static int[] idx;	// new index --> old index
	static int[] top;	//heavy child: top is top of the heavy path, light child: self
	static int[] p;	//parent
	static int[] hc;//heavy child
	static int[] depth;

	static int count;
	static int[][]  seg;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cowland.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowland.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = new Integer(st.nextToken()), Q = new Integer(st.nextToken());
		ev = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++)
			ev[i]=new Integer(st.nextToken());

		edges = new ArrayList[N+1];
		for(int i=1;i<=N;i++)
			edges[i]=new ArrayList<>();

		for(int i=0;i<N-1;i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			edges[a].add(b);
			edges[b].add(a);
		}

		size = new int[N+1];
		id = new int[N+1];			//new id
		idx = new int[N+1];
		top = new int[N+1];
		p = new int[N+1];			//parent
		hc = new int[N+1];			//heavy child
		depth = new int[N+1];
		visited = new boolean[N+1];

		dfs(1);
		dfs2(1,1);

		seg = new int[N*4][3];	//seg[][0]: entainment value, seg[][1]:seg inclusive left bound, seg[][2]:seg inclusive right bound
		buildTree(1,1,N);
		for(int i=0;i<Q;i++){
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken()), a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			if(o==1)
				modify(1,id[a],b);
			else{
				int v = Query(a,b);		//lca(a,b);
				out.println(v);//System.out.println(v);
			}
		}
		out.close();
	}

	static void dfs(int r){	//return number of nodes
		visited[r]=true;
		size[r]=1;
		for(int x : edges[r]){
			if(!visited[x]){
				depth[x]=depth[r]+1;
				p[x]=r;
				dfs(x);		//recursive
				size[r]+=size[x];
				if(size[x]>size[hc[r]])
					hc[r]=x;
			}
		}
	}

	static void dfs2(int r, int t){	//relabeling
		visited[r]=false;
		id[r]=++count;
		idx[count]=r;
		top[r]=t;
		if(hc[r]>0) dfs2(hc[r],t);	//heavy first
		for(int x : edges[r]){
			if(visited[x])
				dfs2(x,x);
		}
	}

	static void pushup(int x){  
		 seg[x][0] = seg[2*x][0] ^ seg[2*x+1][0];
	}

	static void buildTree(int x, int l, int r){
		seg[x][1]=l; seg[x][2]=r;
		if(l==r){
			seg[x][0]=ev[idx[l]];
		}else{
			int mid = (l+r)/2;
			buildTree(x*2,l,mid);
			buildTree(x*2+1,mid+1,r);
			pushup(x);
		}
	}

	static void modify(int x, int pos, int c) {
		if (seg[x][1] == seg[x][2]) {
			seg[x][0] = c;
		}else{
			int mid = (seg[x][1] + seg[x][2])/2 ;
			if (pos <= mid)
				modify(x*2, pos, c) ;
			else
				modify(x*2+1, pos, c) ;
			pushup(x) ;
		}
	}

	static int query(int x, int l, int r) {
		if (l <= seg[x][1] && seg[x][2] <= r) 
			return seg[x][0] ;
		int mid = (seg[x][1] + seg[x][2]) / 2, ans = 0 ;
		if (l <= mid) ans ^= query(2*x, l, r) ;
		if (mid < r) ans ^= query(2*x+1, l, r) ;
		return ans ;
	}

	static int Query(int x, int y) {
		int fx = top[x], fy = top[y], ans = 0 ;
		while (fx != fy) {
			if (depth[fx] < depth[fy]){	//keep depth(fx) >= depth(fy)
				x = x ^ y ^ (y = x);	//swap(x, y)		or		x += (y - (y = x));
				fx = fx ^ fy ^ (fy = fx);	//swap(fx, fy) ;
			}
			ans ^= query(1, id[fx], id[x]);
			x = p[fx] ; fx = top[x];
		}
		if (depth[x] > depth[y])
			x = x ^ y ^ (y = x);	//swap(x, y) ;
		return ans ^ query(1, id[x], id[y]) ;
	}
}
