import java.io.*;
import java.util.*;
public class cowturn {
	static int[] arr;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		for(int i=0; i<N; i++) {
			if(br.readLine().equals("B"))
				arr[i] = -1;
			else
				arr[i] = 1;
		}
		int[] ans = new int[] {Integer.MAX_VALUE, -1};
		int[] save = arr.clone();
		for(int i=N; i>0; i--) {
			int val = calAns(i, 0);
			boolean b = false;
			for(int j=0; j<N; j++) {
				if(arr[j]==-1) {
					b = true;
					break;
				}
			}
			arr = save.clone();
			if(b==true)
				continue;
			if(val>=0) {
				if(val<=ans[0]) {
					ans[0] = val;
					ans[1] = i;
				}
			}
		}
		System.out.println(ans[1]+" "+ans[0]);
	}
	static int calAns(int k, int pos) {
		int n = arr.length;
		for(;pos<n; pos++) {
			if(arr[pos]==-1)
				break;
		}
		if(pos==n)return 0;
		if(pos+k>n)return -10000;
		int ans = 1;
		for(int i=pos; i<pos+k; i++) 
			arr[i] *=-1;
		ans +=calAns(k, pos);
		return ans;
	}
}
