import java.io.*;
import java.util.*;
public class seating {
	static int N;
	static int[][] stree;
	static int[] lazy;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		stree = new int[4*N][3]; // max prefix, max suffix, max value
		lazy = new int[4*N];
		int M = Integer.parseInt(st.nextToken());
		buildTree(1, 0, N-1);
		int ans =0;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			if(st.nextToken().equals("A")) {
				int len = Integer.parseInt(st.nextToken());
				int pos = query(1,len, 0, N-1);
				if(pos ==0)
					ans++;
				if(pos!=0)
					rangeUpdate(1, 0, pos-1, len+pos-2, 0, N-1,-1);
			}
			else {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				rangeUpdate(1,1, start-1, end-1, 0, N-1,-1);
			}
		}
		System.out.println(ans);
	}
	static int query(int x,int comp, int l, int r) {
		int s1=x*2,s2=s1+1;
    	if(stree[x][2]<comp)
    		return 0;
    	int mid = (l+r)/2;
    //	if(l==r)
    //		return Math.max(mid-stree[s1][1]+2,0);
    	if(stree[x][0]>=comp) return l+1;
    	
    	if(stree[s1][2]>=comp) 
    		return query(s1,comp, l, mid);

		if(stree[s1][1]+stree[s2][0]>=comp)
			return mid-stree[s1][1]+2;
		return query(s2,comp, mid+1, r);
	}
    static void rangeUpdate(int x, int delta, int a, int b, int l, int r, int lazy) { //lazy -1: no lazy; 0: used; 1: cleared
    	int s1=x*2,s2=s1+1;
    	int range = r-l+1;

    	if(lazy<0) {
    		if(stree[x][2]==range)
    			lazy=1;
    		if(stree[x][2]==0)
    			lazy=0;
    	}else {
    		stree[x][0]=stree[x][1]=stree[x][2]= lazy==0?0:range;
    	}
    	if(r<a || b<l || l>r) return;
    	if(a<=l && r<=b) {
    		stree[x][0]=stree[x][1]=stree[x][2]= delta==0?0:range;
    		return;
    	}

    	int mid = (l+r)/2;
    	rangeUpdate(s1,delta,a,b, l,mid,lazy);	//l, mid
	    rangeUpdate(s2,delta,a,b, mid+1, r,lazy);

	    stree[x][0] =  stree[s1][0];
    	if(stree[s1][0]==mid-l+1)
    		stree[x][0] += stree[s2][0];

	    stree[x][1] =  stree[s2][1];
    	if(stree[s2][1]==r-mid)
    		stree[x][1] += stree[s1][1];

    	stree[x][2] = Math.max(stree[s2][2],Math.max(stree[s1][2],stree[s1][1] + stree[s2][0]));
    }
    static void buildTree(int x, int l, int r) {
    	if(l==r) {
    		stree[x][0]=stree[x][1]=stree[x][2]=1;
    	}
    	else {
    		int mid=l+(r-l)/2;
    		int s1=x*2,s2=s1+1;
    		buildTree(s1,l,mid);
    		buildTree(s2,mid+1,r);
    		stree[x][0]=stree[x][1]=stree[x][2]= r-l+1;
    	}
    }
    
}
