import java.io.*;
import java.util.*;
public class crisis {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		HashMap<Integer,Integer> cows = new HashMap<>();
		int[][] arrc = new int[N][2];
		HashMap<Integer,Integer> hays = new HashMap<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arrc[i][0] = Integer.parseInt(st.nextToken());
			arrc[i][1] = Integer.parseInt(st.nextToken());
			cows.put(arrc[i][0]*10000+arrc[i][1], i);
		}
		int[][] arrh = new int[M][2];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			arrh[i][0] = Integer.parseInt(st.nextToken());
			arrh[i][1] = Integer.parseInt(st.nextToken());
			hays.put(arrh[i][0]*10000+arrh[i][1], i);
		}
		int[][][] dp = new int[K+1][2*K+1][2*K+1];
		int[][][] prev = new int[K+1][2*K+1][2*K+1];
		for(int i=0; i<K+1; i++)
			for(int j=0; j<2*K+1; j++)
				Arrays.fill(dp[i][j], -1000000);
		dp[0][K][K] = 0;
		int[][] dist = new int[][] {{0,1},{0,-1},{1,0},{-1,0}}; //North  South   East  West
		for(int i=1; i<K+1; i++) {
			for(int j=-i; j<=i; j++) {  // x changes
				for(int k=-i; k<=i; k++) { //y changes
					if(Math.abs(j)+Math.abs(k)>i)continue;
					for(int l=0; l<4; l++) {
						int curr = 0;
						int incx = j+dist[l][0];
						int incy = k+dist[l][1];
						for(int m = 0; m<N; m++) {
							int x = arrc[m][0] + j;
							int y = arrc[m][1] + k;
							if(hays.containsKey(x*10000+y)) 
								curr++;
						}
						if(incx+K<2*K+1&&incy+K<2*K+1&&incx+K>-1&&incy+K>-1) {
							int side = l;
							if(side==0||side==1) {
								if(side==0)
									side = 1;
								else
									side = 2;
							}
							else {
								if(side == 2)
									side = 0;
								else
									side = 3;
							}
							if(dp[i-1][incx+K][incy+K]+curr>dp[i][j+K][k+K]) {
								dp[i][j+K][k+K] = Math.max(dp[i][j+K][k+K], dp[i-1][incx+K][incy+K]+curr);
								prev[i][j+K][k+K] = side;
							}
							if((dp[i-1][incx+K][incy+K]+curr==dp[i][j+K][k+K]&&prev[i][j+K][k+K]<side))
								prev[i][j+K][k+K] = side;
						}
					}
				}
			}
		}
		int ans = 0;
		LinkedList<int[]> list = new LinkedList<>();
		for(int i=0; i<2*K+1; i++) {
			for(int j=0; j<2*K+1; j++) {
				if(dp[K][i][j]>=ans) {
					if(dp[K][i][j] == ans) 
						list.add(new int[] {i,j});
					else {
						list.clear();
						list.add(new int[] {i,j});
						ans = dp[K][i][j];
					}
				}
			}
		}
		int[][] arr = new int[list.size()][K];
		int i=0;
		while(!list.isEmpty()) {
			int[] curr = list.removeFirst();
			for(int j=K; j>0; j--) {
				int side = prev[j][curr[0]][curr[1]];
				if(side==0)
					arr[i][j-1] = 3;
				if(side==1)
					arr[i][j-1] = 2;
				if(side==2)
					arr[i][j-1] = 1;
				if(side==3)
					arr[i][j-1] = 0;
				side = arr[i][j-1];
				curr[0] += dist[side][0];
				curr[1] += dist[side][1];
			}
			i++;
		}
		String str = "";
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				for(int i=0; i<K; i++) {
					if(o1[i]<o2[i])
						return -1;
					if(o1[i]>o2[i])
						return 1;
				}
				return 0;
			}
		});
		
		for(i=0; i<K; i++) {
			if(arr[0][i] == 1) 
				str+='E';
			if(arr[0][i] == 2)
				str+='N';
			if(arr[0][i] == 3)
				str+='S';
			if(arr[0][i] == 4)
				str+='W';
		}
		System.out.println(ans);
		System.out.println(str);
	}

}
