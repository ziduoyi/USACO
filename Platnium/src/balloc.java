import java.io.*;
import java.util.*;
public class balloc {
	static int[] stree;
	static int[] lazy;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		stree = new int[4*N];// min value
		lazy = new int[4*N];
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		buildTree(arr, 0, N-1, 1);
		int[][] input = new int[M][2];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken())-1;
			input[i][1] = Integer.parseInt(st.nextToken())-1;
		}
		Arrays.sort(input, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if(o1[1]!=o2[1])return o1[1]-o2[1];
				return  o2[0]-o1[0];
			}
		});
		
		int ans =0;
		for(int i=0; i<M; i++) {
			if(query(1, input[i][0], input[i][1], 0, N-1)==true) {
				rangeUpdate(1, input[i][0], input[i][1], 0, N-1);
				ans++;
			}
		}
		System.out.println(ans);
	}
	static boolean query(int x, int a, int b, int l, int r) {
		int s1 = 2*x, s2 = 2*x+1;
		if(lazy[x]!=0) {
			stree[x] +=lazy[x];
			if(l!=r) {
				lazy[s1] += lazy[x];
				lazy[s2] += lazy[x];
			}
			lazy[x] =0;
		}
		if(r<a||b<l||l>r)
			return true;
		if(a<=l&&b>=r) {
			if(l!=r) {
				if(Math.min(stree[s1]+lazy[s1], stree[s2]+lazy[s2])>0)
					return true;
			}
			else if(stree[x]>0)
				return true;
			return false;
		}
		int mid = (l+r)/2;
		if(query(s1, a, b, l, mid)==true&&query(s2, a, b, mid+1, r)==true) 
			return true;
		
		return false;
	}
	static void rangeUpdate(int x, int a, int b, int l, int r) {// delta is always -1
		int s1 = 2*x, s2 = 2*x+1;
		if(lazy[x]!=0) {
			stree[x] +=lazy[x];
			if(l!=r) {
				lazy[s1] += lazy[x];
				lazy[s2] += lazy[x];
			}
			lazy[x] =0;
		}
		if(r<a||b<l||l>r)
			return;
		if(a<=l&&b>=r) {
			stree[x] --;
			if(l!=r) {
				lazy[s1]--;
				lazy[s2]--;
			}
		}
		else {
			int mid = (l+r)/2;
			if(l!=r) {
				rangeUpdate(s1, a, b, l, mid);
				rangeUpdate(s2, a, b, mid+1, r);
			}
			stree[x] = Math.min(stree[s1], stree[s2]);
		}
	}
	static void buildTree(int[] data, int l, int r, int x) {
		if(l>r)
			return;
		int s1 = 2*x, s2 = 2*x+1;
		if(l==r)
			stree[x] = data[l];
		else {
			int mid = (l+r)/2;
			buildTree(data, l, mid, s1);
			buildTree(data, mid+1, r, s2);
			stree[x] = Math.min(stree[s1], stree[s2]);
		}
	}
}