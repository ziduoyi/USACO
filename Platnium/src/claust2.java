import java.util.*;
import java.io.*;
public class claust2 {
	static int[][] arrx;
	static double[] ans;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arrx = new int[N][3];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arrx[i][0] = Integer.parseInt(st.nextToken());
			arrx[i][1] = Integer.parseInt(st.nextToken());
			arrx[i][2] =i;
		}
		Arrays.sort(arrx, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[0] - o2[0];
			}
		});
		ans = new double[3]; // x, y, value
		ans[2] = 1000000000;
		recursion(0, N-1);
		System.out.println(((int)ans[0]+1) + " " +((int)ans[1]+1));
	}
	static int recursion(int l, int r) {
		if(l==r) 
			return 20000;
		int m = (l+r)/2;
		int d = Math.min(recursion(l,m), recursion(m+1, r));
		int s =m;
		int e =m;
		for(int i=m; i<=r; i++) {
			if(arrx[i][0]<=arrx[m][0]+d) {
				e =i;
			}
			else
				break;
		}
		for(int i=m; i>=l; i--) {
			if(arrx[i][0]>=arrx[m][0]-d) {
				s =i;
			}
			else
				break;
		}
		int[][] stuff = new int[e-s+1][3];
		for(int i=s; i<=e ; i++) {
			stuff[i-s][0] = arrx[i][0];
			stuff[i-s][1] = arrx[i][1];
			stuff[i-s][2] =arrx[i][2];
		}
		Arrays.sort(stuff, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[1] - o2[1];
			}
		});
		for(int i=0; i<e-s+1; i++) {
			int a =i;
			for(int j=i+1; j<Math.min(a+16, e-s+1); j++) {
				double num = Math.sqrt((stuff[j][0]-stuff[i][0])*(stuff[j][0]-stuff[i][0])+(stuff[j][1]-stuff[i][1])*(stuff[j][1]-stuff[i][1]));
				if(num<ans[2]) {
					ans[2] = num;
					ans[0] = Math.min(stuff[j][2],stuff[i][2]);
					ans[1] = Math.max(stuff[j][2],stuff[i][2]);
				}
				else if(num==ans[2]) {
					if(Math.min(stuff[j][2],stuff[i][2])<ans[0]) {
						ans[0] = Math.min(stuff[j][2],stuff[i][2]);
						ans[1] = Math.max(stuff[j][2],stuff[i][2]);	
					}
					else if(Math.min(stuff[j][2],stuff[i][2])==ans[0]) {
						if(Math.max(stuff[j][2],stuff[i][2])<ans[1]) {
							ans[1] = Math.max(stuff[j][2],stuff[i][2]);
						}
					}
				}
			}
		}
		return (int)ans[2];
		
	}
}
