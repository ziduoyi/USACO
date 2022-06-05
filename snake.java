import java.io.*;
import java.util.*;
public class snake {
	static int[][] seg;
	static int[] lazy;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
    	seg = new int[4*3*100000][3];
    	lazy = new int[4*3*100000];
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int N = Integer.parseInt(st.nextToken());
	    	int Q = Integer.parseInt(st.nextToken());
	    	buildTree(1,1,N);
	    	Arrays.fill(lazy, 0);
	    	for(int j=0; j<Q; j++) {
	    		st = new StringTokenizer(br.readLine());
		    	rangeUpdate(1, 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		    	int com = query(1, 1, N);
		    	if(com<Integer.parseInt(st.nextToken())) {
		    		out.write("hit\n");
		    	}
		    	else
		    		out.write("blocked\n");
	    	}
	    }
	    out.flush();
	    out.close();
	}
    static void buildTree(int x, int l, int r) {
    	seg[x][1]=l;seg[x][2]=r;
    	seg[x][0] = 0;
    	if(l==r) 
    		seg[x][0]=0;
    	else {
    		int mid=l+(r-l)/2;
    		int s1=x*2,s2=s1+1;
    		buildTree(s1,l,mid);
    		buildTree(s2,mid+1,r);
    	}
    }
    static void rangeUpdate(int x, long delta, int a, int b) {
    	int l = (int) seg[x][1], r=(int) seg[x][2];
    	int s1=x*2,s2=s1+1;
    	if(a<=l && r<=b)
    		lazy[x]+=delta;
    	else {
    		int mid = (l+r)/2;
    		a=Math.max(a,l); b=Math.min(r, b);
    		if(a<=mid) 
    			rangeUpdate(s1,delta,a,Math.min(b,mid));	
    		if(mid<b)
    			rangeUpdate(s2,delta,Math.max(a,mid+1),b);
    		seg[x][0] = Math.max(seg[s1][0] + lazy[s1] , seg[s2][0] + lazy[s2]);
    	}
    }
    static int query(int x, int a, int b) {
    	int res = 0;
    	int l = seg[x][1], r=seg[x][2];
    	int s1=x*2,s2=s1+1;
    	
    	if(a<=l && r<=b) {
    		return  seg[x][0] + (lazy[x]) ;
    	}else {
    		int mid = (l+r)/2;
    		a=Math.max(a,l); b=Math.min(r, b);
    		res += lazy[x];		
    		if(a<=mid)	
    			res += query(s1,a,Math.min(b,mid));	
    		if(mid<b)
    			res += query(s2,Math.max(a,mid+1),b);
    	}

    	return res;
    }
}
