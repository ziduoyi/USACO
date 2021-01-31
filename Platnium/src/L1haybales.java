import java.io.*;
import java.util.*;
public class L1haybales {
	// December p3 2015-2016
    static long[][] segs;
    static long[] lazys;
    static int[] nums;
    static int N;
    public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("haybales.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int Q=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		int[] arr=new int[(int) (n+1)];
		for(int i=1; i<n+1; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		init(n,arr);
		//buildTreeM(1,1,N);
		buildTreeS(1,1,N);
		for(long i=0; i<Q; i++) {
			st=new StringTokenizer(br.readLine());
			String s=st.nextToken();
			if(s.equals("M")) {
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				out.println(queryM(1,a, b));
				continue;
			}
			if(s.equals("S")) {
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				out.println(queryS(1,a,b));
				continue;
			}
			if(s.equals("P")) {
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				//rangeUpdateM(1,c,a,b);
				rangeUpdateS(1,c,a,b);
				continue;
			}
		}
		out.close();
	}
    static void init(int n, int[] arr) {
    	N=n;
    	segs=new long[(int) (4*N)][4];
    	lazys=new long[(int) (4*N)];
    	for(int i=1; i<4*N; i++) segs[i][3]=Integer.MAX_VALUE;
    	nums=arr;
    }
   
	static void pushup(int x, int s1, int s2) {
		segs[x][0] = segs[s1][0] + lazys[s1]*(segs[s1][2]-segs[s1][1]+1) + segs[s2][0] + lazys[s2]*(segs[s2][2]-segs[s2][1]+1);
		segs[x][3] = Math.min(segs[s1][3] + lazys[s1], segs[s2][3] + lazys[s2]);
	}

    static void buildTreeS(int x, int l, int r) {
    	segs[x][1]=l;segs[x][2]=r;
    	if(l==r) { 
    		segs[x][0]=nums[l];
    		segs[x][3]=nums[l];
    	}
    	else {
    		int mid=l+(r-l)/2;
    		int s1=x*2,s2=s1+1;
    		buildTreeS(s1,l,mid);
    		buildTreeS(s2,mid+1,r);
    		segs[x][0]=segs[s1][0]+segs[s2][0];	//push up sum
    		segs[x][3] = Math.min(segs[s1][3], segs[s2][3]);	//push up min
    	}
    }
    static void rangeUpdateS(int x, long delta, int a, int b) {
    	int l = (int) segs[x][1], r=(int) segs[x][2];
    	int s1=x*2,s2=s1+1;
    	if(a<=l && r<=b)
    		lazys[x]+=delta;
    	else {
    		int mid = (l+r)/2;
    		a=Math.max(a,l); b=Math.min(r, b);
    		if(a<=mid) 
    			rangeUpdateS(s1,delta,a,Math.min(b,mid));	//l, mid
    		if(mid<b)
    			rangeUpdateS(s2,delta,Math.max(a,mid+1),b);
    		pushup(x,s1,s2);
    	}
    }
    
    static long queryS(int x, int a, int b) {
    	long res = 0;
    	int l = (int) segs[x][1], r=(int) segs[x][2];
    	int s1=x*2,s2=s1+1;

    	if(a<=l && r<=b)
    		return  segs[x][0] + (lazys[x])*(r-l+1) ;
    	else {
    		int mid = (l+r)/2;
    		a=Math.max(a,l); b=Math.min(r, b);
    		res += lazys[x] * (b-a+1);					// --- range summary
    		if(a<=mid)
    			res += queryS(s1,a,Math.min(b,mid));	//l, mid
    		if(mid<b)
    			res += queryS(s2,Math.max(a,mid+1),b);
    	}
    	return res;
    }
  static long queryM(int x, int a, int b) {
	long res = Integer.MAX_VALUE;
	int l = (int) segs[x][1], r=(int) segs[x][2];
	int s1=x*2,s2=s1+1;
	if(a<=l && r<=b) {
		return  segs[x][3] + lazys[x] ;
	}else {
		int mid = (int) ((l+r)/2);
		a=Math.max(a,l); b=Math.min(r, b);
		if(a<=mid)
			res =Math.min(res,queryM(s1,a,Math.min(b,mid)));
		if(mid<b)
			res = Math.min(res,queryM(s2,Math.max(a,mid+1),b));
		res += lazys[x];					// --- range min
	}
	return res;
  } 
}
