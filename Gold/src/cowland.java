import java.io.*;
import java.util.*;
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

	static int count, N,Q;
	static int[]  seg;
	static boolean[] visited;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		long t1=System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new FileReader("cowland.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowland.out")));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = new Integer(st.nextToken()); Q = new Integer(st.nextToken());
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
		top = new int[N+1];			//heavy chain:  top = topest of the heavy chain; light chain: top = self
		p = new int[N+1];			//parent
		hc = new int[N+1];			//heavy child
		depth = new int[N+1];
		visited = new boolean[N+1];
		dfs1(1);
		dfs2(1,1);

		seg = new int[N*2];	//seg[][0]: entainment value, seg[][1]:seg inclusive left bound, seg[][2]:seg inclusive right bound
		buildTree();
		for(int i=0;i<Q;i++){
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken()), a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			
			if(o==1) {
				update(a,b);
				ev[a]=b;
			}
			else{
				int v = 0;
				if(a==b)
					v=ev[a];
				else
					v=lca(a,b);
				out.println(v);//System.out.println(v);
			}
		}
		out.close();
	}
	static void dfs1(int s) {
		visited[s]=true;
		size[s]=1;
		for(int x:edges[s]) {
			if(!visited[x]) {
				depth[x]=depth[s] + 1 ;
				dfs1(x);
				p[x]=s;
				size[s]+=size[x];
				if(size[x]>size[hc[s]])
					hc[s]=x;
			}
		}
		
	}
	static void dfs2(int s, int t) {
		visited[s]=false;
		id[s]=++count;
		idx[count]=s;
		top[s]=t;
		if(hc[s]>0) 
			dfs2(hc[s],t); //heavy chain first
		for(int x:edges[s]) {
			if(visited[x]) {
				dfs2(x,x);
			}
		}
		
	}
    static void buildTree(){
        for(int i=0;i<N;i++){
        	seg[i+N]=ev[idx[i+1]];
        }
        for(int i=N-1;i>0;i--){
        	seg[i]=seg[2*i] ^ seg[2*i+1];
        }
    }

    static void update(int i, int val) {
        int x = id[i] + N -1;
        seg[x]=val;
        while(x>0){
            int left=x;
            int right=x;
            if((x&1)==0)	//x%2==0
                right++;
            else
                left--;
            x>>=1;
            seg[x] = seg[left]^seg[right];
        }
    }

    static int sumRange(int i, int j) {
        int l=id[i]+N-1, r=id[j]+N-1;
        int sum=0;
        while(l<=r){
            if((l&1)==1)
                sum ^= seg[l++];
            if((r&1)==0)
                sum ^= seg[r--];
            l /= 2;
            r /= 2;
        }
        return sum;
    }
    
    static int lcaCom(int x, int y) {
    	int sum=0;
    	while(x != y) {
    		if(depth[x] > depth[y]) {
    			sum ^= ev[x];
    			x = p[x];
    		}else {
    			sum ^= ev[y];
    			y=p[y];
    		}
    	}
    	return sum^ev[x];
    }
    static int lca(int x, int y) {
    	int sum=0;
    	while(x!=y) {
    		int tx=top[x],ty=top[y];
    		if(depth[tx]>depth[ty]) {
    			x^=y^(y=x); 
    			tx^=ty^(ty=tx);
    		}
    		if(tx==ty) {
    			if(depth[x]>depth[y]) {
        			x^=y^(y=x); 
        			tx^=ty^(ty=tx);    				
    			}
    			sum^=sumRange(x,y);
    			return sum;
    		}
    		if(ty!=y) {
    			sum^=sumRange(ty,y)^ev[ty];
    			y=ty;
    		}else {
    			sum^=ev[y];
    			y=p[y];
    		}
    	}
    	return sum^ev[x];
    }
}
