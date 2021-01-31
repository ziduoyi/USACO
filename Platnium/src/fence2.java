import java.io.*;
import java.util.*;
public class fence2 {
	static int[][] dp;
	static int[][] f;
	static int[][] g;
	static int[][] arr;
	static int[] quad;
	static double[] slope;
	static int first;
	static int num;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		f = new int[N][N];
		g = new int[N][N];
		arr = new int[N][2];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j)continue;
				double max1 = 0;
				double min2 = 1000000000;
				f[i][j] = -1;
				g[i][j] = -1;
				for(int k=0; k<N; k++) {
					if(k==i||k==j)continue;
					if(ccw(arr[i][0], arr[i][1], arr[j][0], arr[j][1], arr[k][0], arr[k][1])) {
						double angle = angle(arr[i][0], arr[i][1], arr[j][0], arr[j][1], arr[k][0], arr[k][1]);
						if(angle>max1) {
							max1 = angle;
							f[i][j] = k;
						}
						angle = angle(arr[j][0], arr[j][1], arr[i][0], arr[i][1], arr[k][0], arr[k][1]);
						if(angle<min2) {
							min2 = angle;
							g[i][j] = k;
						}
					}
				}
			}
		}

		dp = new int[N][N];
		quad = new int[N];
		slope = new double[N];
		int ans = 0;
		for(int i=0; i<N; i++) {
			num = i;
			Integer[] order = new Integer[N];
			for(int j=0; j<N; j++) 
				order[j] = j;
			for(int j=0; j<N; j++) {
				if(i!=j) {
					if(arr[j][0] <=arr[num][0] && arr[j][1] >= arr[num][1]) 
						quad[j] = 0;
					if(arr[j][0] >arr[num][0] && arr[j][1] > arr[num][1]) 
						quad[j] = 1;
					if(arr[j][0] >=arr[num][0] && arr[j][1] <= arr[num][1]) 
						quad[j] = 2;
					if(arr[j][0] <arr[num][0] && arr[j][1] < arr[num][1]) 
						quad[j] = 3;
					slope[j] = -1000000;
					if(arr[j][0] != arr[num][0])
						slope[j] =(double) (arr[j][1] - arr[num][1]) / (arr[j][0] - arr[num][0]);
				}
				else
					quad[j] = -1;
			}
			Arrays.sort(order, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					// TODO Auto-generated method stub
					if(o1 == num)
						return 1;
					if(o2 == num)
						return -1;
					if(quad[o1] ==quad[o2]) {
						if(slope[o1]>slope[o2])
							return -1;
						return 1;
					}
					return quad[o1] - quad[o2];
				}	
			});
			for(int j=0; j<N; j++) 
				if(i!=j)
					dp[j][i] = 1;
			for(int j=N-2; j>-1; j--) {
				if(dp[i][order[j]] ==0 && arr[order[j]][1] <= arr[num][1]) {
					first = order[j];
					compute(i,order[j]);
				}
				else
					break;
			}
			for(int j=0; j<N; j++)
				if(i!=j) 
					ans = Math.max(ans, dp[i][j]);
			for(int j=0; j<N; j++)
				Arrays.fill(dp[j], 0);
		}
		System.out.println(ans);
	}
	
	static void compute(int x, int y) {
		if(f[x][y] !=-1) {
			boolean b = false;
			if(quad[f[x][y]] == quad[first])
				if(slope[f[x][y]] > slope[first]) 
					b = true;
			if(quad[f[x][y]] == (quad[first] +3)%4)
				b = true;
			if(quad[f[x][y]] == (quad[first]+2)%4)
				if(slope[f[x][y]] < slope[first])
					b = true;
			if((quad[y] == quad[f[x][y]]&&slope[y]>slope[f[x][y]])||(quad[y]+1)%4 == quad[f[x][y]]||((quad[y]+2)%4==quad[f[x][y]])&& quad[f[x][y]]==quad[first])
				b = false;
			if(b||f[x][y] == num) {
				if(dp[y][f[x][y]] == 0)
					compute(y, f[x][y]);
				if(dp[y][f[x][y]]!= 0)
					dp[x][y] = Math.max(dp[y][f[x][y]] +1, dp[x][y]);
			}
		}
		if(g[x][y] != -1) {
			boolean b = false;
			if(quad[g[x][y]] == quad[first])
				if(slope[f[x][y]] > slope[first]) 
					b = true;
			if(quad[g[x][y]] == (quad[first] +3)%4)
				b = true;
			if(quad[g[x][y]] == (quad[first]+2)%4)
				if(slope[g[x][y]] < slope[first])
					b = true;
			if((quad[y] == quad[f[x][y]]&&slope[y]>slope[f[x][y]])||(quad[y]+1)%4 == quad[f[x][y]]||((quad[y]+2)%4==quad[f[x][y]])&& quad[f[x][y]]==quad[first])
				b = false;
			if(b|| g[x][y] == num) {
				if(dp[y][g[x][y]] == 0)
					compute(y, g[x][y]);
				if(dp[y][g[x][y]]!= 0)
					dp[x][y] = Math.max(dp[y][g[x][y]], dp[x][y]);
			}
		}
	}
	static boolean ccw(int x1, int y1, int x2, int y2, int x3, int y3) { // on line gives value 0, above gives -, below gives +
		if((long)((long)((x2-x1)*(y3-y1))-(long)((y2-y1)*(x3-x1)))>0) 
			return true;
		return false;
	}
	static double angle(int x1, int y1, int x2, int y2, int x3, int y3) {
		double a1 = (x2-x1)*(x2-x1);
		double b1 = (x3-x1)*(x3-x1);
		double c1 = (x3-x2)*(x3-x2);
		double a2 = (y2-y1)*(y2-y1);
		double b2 = (y3-y1)*(y3-y1);
		double c2 = (y3-y2)*(y3-y2);
		return Math.acos((a1+a2+c1+c2-b1-b2)/(2*Math.sqrt((a1+a2)*(c1+c2))))*180/Math.PI;
	}
}
