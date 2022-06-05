import java.io.*;
import java.util.*;
public class coin {
	
	static int mod=1000000007 ;
	static int n,m;
	static long k;
	static int arr[];
	static int vst[];
	static int dp[];
	static boolean cc;
	static List<Integer> adj[];
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		PrintWriter out=new PrintWriter(System.out);
	    FastScanner fs=new FastScanner();
//	    DecimalFormat formatter= new DecimalFormat("#0.000000");
//	    int t=fs.nextInt();
	   int t=1;
	    outer:for(int time=1;time<=t;time++) {
	    	n=fs.nextInt() ; 
	    	m=fs.nextInt();
	    	k=fs.nextLong();
	    	arr=new int[n+1];
	    	for(int i=1;i<=n;i++) arr[i]=fs.nextInt();
	    	if(k==1) {
	    		int min=Integer.MAX_VALUE;
	    		for(int i=1;i<=n;i++) min=Math.min(min, arr[i]);
	    		out.println(min);
	    		continue outer;
	    	}
	    	adj=new ArrayList[n+1];
	    	for(int i=0;i<=n;i++) adj[i]=new ArrayList<>();
	    	for(int i=0;i<m;i++) {
	    		int u=fs.nextInt(), v=fs.nextInt();
	    		adj[u].add(v);
	    	}
	    	int l=1,r= 1000000000;
	    	if(!find(r,k-1)) {
	    		out.println(-1);
	    		continue outer;
	    	}
	    	
	    	while(l<r) {
	    		int mid = (l+r)/2;
	    		if(find(mid,k-1)) {
	    			r=mid;
	    		}
	    		else l=mid+1;
	    	}
	    	out.println(l);
	    }
	    
	    out.close();
	}
	static boolean find(int x,long k) {
		cc=false;
		vst=new int[n+1];
		dp=new int[n+1];
		Arrays.fill(dp, -1);
//		Arrays.fill(vst, 0);
		for(int i=1;i<=n;i++) {
			if(vst[i]==0&&arr[i]<=x) {
				cycle(i,x);
				if(cc) return true;
			}
		}
		long max=0;
		for(int i=1;i<=n;i++) {
			if(arr[i-1]>x) continue;
			max=Math.max(dp[i],max);
		}
		
		return max>=k;
		
	}
	
	static void cycle(int cur,int x) {
		
//		if(vst[cur]==2) {
//			cycle=true;
//			return ;
//		}
		
		if(vst[cur]==2) {
			return ;
		}
		vst[cur]=1;
		int ans=0;
		for(int c:adj[cur]) {
			if(arr[c]>x) continue;
			if(vst[c]==1) {
				cc=true;
				return ;
			}
			if(vst[c]==0) {
				cycle(c,x) ;
			}
			ans= Math.max(ans, dp[c]+1);
		}
		dp[cur]=ans;
		vst[cur]=2;
		
		return ;
	}
	static long pow(long a,long b) {
		if(b<0) return 1;
		long res=1;
		while(b!=0) {
			if((b&1)!=0) {
				res*=a;
				res%=mod;
			}
			a*=a;
			a%=mod;
			b=b>>1;
		}
		return res;
	}
	static long gcd(long  a,long  b) {
		if(b==0) return a;
		return gcd(b,a%b);
	}
	static long nck(int n,int k) {
		if(k>n) return 0;
		long res=1;
		res*=fact(n);
		res%=mod;
		res*=modInv(fact(k));
		res%=mod;
		res*=modInv(fact(n-k)); 
		res%=mod;
		return res;
	}
	static long fact(long n) {
		long res=1;
		for(int i=2;i<=n;i++) {
			res*=i;
			res%=mod;
		}
		return res;
	}
	
	static long modInv(long n) {
		return pow(n,mod-2);
	}
	
	static void sort(int[] a) {
		//suffle
		int n=a.length;
		Random r=new Random();
		for (int i=0; i<a.length; i++) {
			int oi=r.nextInt(n);
			int temp=a[i];
			a[i]=a[oi];
			a[oi]=temp;
		}
		
		//then sort
		Arrays.sort(a);
	}
	
	// Use this to input code since it is faster than a Scanner
	static class FastScanner {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer("");
		String next() {
			while (!st.hasMoreTokens())
				try {
					st=new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		long[] lreadArray(int n) {
			long a[]=new long[n];
			for(int i=0;i<n;i++) a[i]=nextLong();
			return a;
		}
		int[] readArray(int n) {
			int[] a=new int[n];
			for (int i=0; i<n; i++) a[i]=nextInt();
			return a;
		}
		long nextLong() {
			return Long.parseLong(next());
		}
	}
 
}