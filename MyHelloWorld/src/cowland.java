import java.util.*;
import java.io.*;

public class cowland {

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
	static int[]  sgt;
	static boolean[] visited;
	static int len = 0;

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
		
		len=N;

		//seg = new int[N*2][3];	//seg[][0]: entainment value, seg[][1]:seg inclusive left bound, seg[][2]:seg inclusive right bound
		sgt = new int[N*2];
		buildTree();
		for(int i=0;i<Q;i++){
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken()), a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			if(o==1)
				update(a,b);
			else{
				int v = lca(a,b);
				//int w = lcacs(a,b);
				out.println(v);	//System.out.println(v + ", " + w);
			}
		}		
		
/*		
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
*/
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
	
//	-------------------
	static void buildTree(){
        for(int i=1,j=len;i<=len;i++){
        	sgt[j++]=ev[idx[i]];		//relabeling
        }
        for(int i=len-1;i>0;i--){
        	sgt[i]=sgt[2*i] ^ sgt[2*i+1];
        }
    }

    static void update(int i, int val) {	//new label
        ev[i] = val;
        int x = id[i] + len - 1;	//update new label
        sgt[x]=val;
        while(x>0){
            int left=x;
            int right=x;
            if((x&1)==0)	//x%2==0
                right++;
            else
                left--;
            x>>=1;
		sgt[x] = sgt[left] ^ sgt[right];
        }
    }

    static int sumRange(int i, int j) {
        int l=id[i]+len-1, r=id[j]+len-1;	
        int sum=0;
        while(l<=r){
            if((l&1)==1)
                sum ^= sgt[l++];
            if((r&1)==0)
                sum ^= sgt[r--];
            l /= 2;
            r /= 2;
        }
        return sum;
    }

    static int lca(int x, int y){
    	int sum=0;
		while(x!=y){
			int tx = top[x], ty=top[y];
			if(tx==ty){
				if(depth[x]>depth[y])
					sum ^= sumRange(y,x);
				else
					sum ^= sumRange(x,y);
				return sum;
			}
			if(depth[tx]<depth[ty]){		// go upper per depth of top
				x ^= y ^ (y=x);	//swap x,y
				tx ^= ty ^ (ty=tx);
			}
			
			if(tx==x){
				sum ^= ev[x];//sumRange(top[x],x);
				x=p[x];
			}else{
				sum ^= sumRange(tx,x) ^ ev[tx];	//^ ev[tx]: get rid of the upper bound, it will be calculated later
				x=tx;
			}
		}
		return sum^ev[x];
	}
    
    static int lcacs(int x, int y){
    	int sum=0;
		while(x!=y){
			if(depth[x]>depth[y]){
				sum ^= ev[x];
				x=p[x];
			}else{
				sum ^= ev[y];
				y=p[y];
			}
		}
		return sum^ev[x];
	}
// -------------------------


	static void pushup(int x){
		 seg[x][0] = seg[2*x][0] ^ seg[2*x+1][0];
	}

	static void buildTree(int x, int l, int r){
		seg[x][1]=l; seg[x][2]=r;
		if(l==r){
			seg[x][0]=ev[idx[l]];	//??
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
				x ^= y ^ (y = x);	//swap(x, y)		or		x += (y - (y = x));
				fx ^= fy ^ (fy = fx);	//swap(fx, fy) ;
			}
			ans ^= query(1, id[fx], id[x]);
			x = p[fx] ; fx = top[x];
		}
		if (depth[x] > depth[y])
			x ^= y ^ (y = x);	//swap(x, y) ;
		return ans ^ query(1, id[x], id[y]) ;
	}
}	

/*	static int lca(int x, int y){
		while(x!=y){
			if(top[x]==top[y]){
				if(depth[x]>depth[y])
					return y;
				else
					return x;
			}
			if(depth[x]>depth[y]){
				if(top[x]==x) 
					x=p[x];
				else
					x=top[x];
			}else{
				if(top[y]==y) 
					y=p[y];
				else
					y=top[y];
			}
		}
		return x;
	}*/	
	

