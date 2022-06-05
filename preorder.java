import java.io.*;
import java.util.*;
public class preorder {
	static int[] arr;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int n = Integer.parseInt(br.readLine());
	    n = (1<<n)-1;
	    arr = new int[n];
	    String str = br.readLine();
	    for(int i=0; i<n; i++) {
	    	if(str.charAt(i)=='B')arr[i] = 1;
	    	else arr[i] = 0;
	    }
	    System.out.println(dfs(1));
	}
	static long dfs(int i) {
		if (2*i > arr.length) return 1;
		int l = 2 * i;
		int r = l + 1;
		long cntl = dfs(l);
		long cntr = dfs(r);
		
		if (isEqual(l, r)) return cntl * cntr % 998244353;
		else return 2 * cntl * cntr % 998244353;
	}
 
	static boolean isEqual(int l, int r) {
		if (arr[l-1] != arr[r-1]) return false;
		
		if (2 * l > arr.length) return true;
		
		return (isEqual(2 * l, 2 * r) && isEqual(2 * l + 1, 2 * r + 1)) ||
			   (isEqual(2 * l, 2 * r + 1) && isEqual(2 * l + 1, 2 * r));
	}
}
