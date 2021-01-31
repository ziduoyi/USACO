import java.io.*;
import java.util.*;
public class lites {
	static int[] seg;
	static int[] lazy;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		seg = new int[4*N];
		lazy = new int[4*N];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken())==0) 
				rangeUpdate(1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0, N-1);
			else
				System.out.println(query(1, Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()), 0, N-1));
		}
	}
	static void rangeUpdate(int x, int a, int b, int l, int r) {
		int s1 = 2*x,s2 = 2*x+1;
		int range = r-l+1;
		if(lazy[x] !=0) {
			lazy[x]%=2;
			if(lazy[x]==1) {
				seg[x] = range-seg[x];
				if(l!=r) {
					lazy[s1] +=lazy[x];
					lazy[s2] +=lazy[x];
				}
				lazy[x] =0;
			}
		}
    	if(l>r|| r<a|| b<l)
    		return; 
    	if(a<=l&&b>=r) {
    		seg[x] = range-seg[x];
    		if(l!=r) {
    			lazy[s1]++;
    			lazy[s2]++;
    		}
    		return;
    	}
		int mid = (l+r)/2;
		if(l!=r) {
			rangeUpdate(s1, a, b, l, mid);
			rangeUpdate(s2, a ,b ,mid+1, r);
		}
		seg[x] = seg[s1] + seg[s2];
	}
	static int query(int x, int a, int b, int l, int r) {
		int s1 = 2*x,s2 = 2*x+1;
		int range = r-l+1;
		if(lazy[x] !=0) {
			lazy[x]%=2;
			if(lazy[x]==1) {
				seg[x] = range-seg[x];
				if(l!=r) {
					lazy[s1] +=lazy[x];
					lazy[s2] +=lazy[x];
				}
				lazy[x] =0;
			}
		}
    	if(l>r|| r<a|| b<l)
    		return 0;
    	if(a<=l&&b>=r) 
    		return seg[x];
    	int mid = (l+r)/2;
    	return query(s1, a, b, l, mid) + query(s2, a, b, mid+1, r);
	}
}
