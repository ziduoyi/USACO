import java.util.*;
import java.io.*;
public class boring {
	static int n, m;
	static int[] seg;
	static int[] lazy;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
	    seg = new int[4*m];
	    lazy = new int[4*m];
	    int[][] arr = new int[n][3];
	    for(int i=0; i<n; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	for(int j=0; j<3; j++)
	    		arr[i][j] = Integer.parseInt(st.nextToken());
	    }
	    Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				return arg0[2]-arg1[2];
			}
	    });
	    int ans = 1000000000;
	    for(int i=0; i<n; i++) {
	    	update(1, arr[i][2], 1, m-1, arr[i][0], arr[i][1]-1);
	    	if(seg[1]>0) ans = Math.min(ans, arr[i][2]-seg[1]);
	    }
	    out.write(ans+"\n");
	    out.flush();
	    out.close();
	}
	static void update(int x, int delta, int l, int r, int a, int b) {
		int s1 = x*2, s2 = x*2+1;
		if(a<=l && b>=r) {
			seg[x] = Math.max(seg[x], delta);
			lazy[x] = Math.max(lazy[x], delta);
		}
		else if (a<=r && b>=l) {
			int mid = (l+r)/2;
			seg[s1] = Math.max(seg[s1], lazy[x]);
			lazy[s1] = Math.max(lazy[s1], lazy[x]);
			seg[s2] = Math.max(seg[s2], lazy[x]);
			lazy[s2] = Math.max(lazy[s2], lazy[x]);
			lazy[x] = 0;
			update(s1, delta, l, mid, a, b);
			update(s2, delta, mid+1, r, a, b);
			seg[x] = Math.min(seg[s1], seg[s2]);
		}
	}
}
