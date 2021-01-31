import java.io.*;
import java.util.*;
public class optmilk {
	static long[][] seg;
	static int N;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		seg = new long[4*N][4];//max val, max val no l, max val no r, max val no l and r
		int[] arr = new int[N];
		for(int i=0; i<N; i++) 
			arr[i] = Integer.parseInt(br.readLine());
		buildTree(arr,1,0,N-1);
		long ans =0;
		for(int i=0; i<D; i++) {
			st = new StringTokenizer(br.readLine());
			int pos = Integer.parseInt(st.nextToken())-1;
			int delta = Integer.parseInt(st.nextToken());
			update(pos, delta,0,N-1,1);
			ans+=seg[1][0];
		}
		System.out.println(ans);
		
	}
	static void buildTree(int[] data, int x,int l, int r) {
		if(l==r) 
			seg[x][0] = data[l]; 
		else {
			int mid = (l+r)/2;
			buildTree(data, 2*x, l, mid);
			buildTree(data, 2*x+1, mid+1, r);
			seg[x][0] = Math.max(seg[2*x][0]+seg[2*x+1][1], seg[2*x][2]+seg[2*x+1][0]);
			seg[x][1] = Math.max(seg[2*x][1]+seg[2*x+1][1], seg[2*x][3]+seg[2*x+1][0]);
			seg[x][2] = Math.max(seg[2*x][2]+seg[2*x+1][2], seg[2*x][0]+seg[2*x+1][3]);
			seg[x][3] = Math.max(seg[2*x][1]+seg[2*x+1][3], seg[2*x][3]+seg[2*x+1][2]);
		}
	}
	static void update(int i, int delta, int l, int r, int x) {
		int left= 2*x, right = 2*x+1;
		if(l!=r) {
			int mid = (l+r)/2;
			if(mid>=i) 
				update(i,delta,l, mid, left);
			else
				update(i, delta,mid+1, r, right);
			seg[x][0] = Math.max(seg[left][0]+seg[right][1], seg[left][2]+seg[right][0]);
			seg[x][1] = Math.max(seg[left][1]+seg[right][1], seg[left][3]+seg[right][0]);
			seg[x][2] = Math.max(seg[left][2]+seg[right][2], seg[left][0]+seg[right][3]);
			seg[x][3] = Math.max(seg[left][1]+seg[right][3], seg[left][3]+seg[right][2]);
		}
		else {
			seg[x][0]=delta;
		}
	}
}
