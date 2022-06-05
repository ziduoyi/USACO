import java.io.*;
import java.util.*;
public class green {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[][] store = new int[N][N]; //stores the grid/grass greenness
		for(int i = 0; i<N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) 
				store[i][j] = Integer.parseInt(st.nextToken());
		}
		int[][] sumsAdd = new int[N+1][N];//# of cows from position 0 to i-1 which have more than 99 greenness at height j 
		int[][] sumsSub = new int[N+1][N];//# of cows from position 0 to i-1 which have more than 100 greenness at height j 
		for(int j=0; j<N; j++) {
			for(int i=1; i<=N; i++) {
				if(store[i-1][j]<100) {
				     sumsAdd[i][j] = 1;
				     sumsSub[i][j] = 1;
				}
				else if (store[i-1][j] == 100) 
						sumsAdd[i][j] = 1;
			}
			for(int i=1; i<=N; i++) {//self summary
				sumsAdd[i][j] += sumsAdd[i-1][j];
				sumsSub[i][j] += sumsSub[i-1][j];
			}
		}
		long ans = 0;
		for(int i=1; i<=N; i++) {
			for(int j=i; j<=N; j++) {
				int y1 = -1;
				int y2 = -1;
				for(int k=0; k<N; k++) {
					while (y1 < N && (y1 < k || (sumsAdd[j][y1] - sumsAdd[i-1][y1] == 0)))//note that these while loops
		                y1+=1; //if the rectangle here works, or our starting has been moved too far forward, move the end
		            while (y2 < N && (y2 < k || (sumsSub[j][y2] - sumsSub[i-1][y2] == 0)))//are technically O(1) inside the loop
		                y2+=1;
		            ans += y2-y1;
				}
				           
			}
		}
		out.write(ans+"\n");
		out.flush();
		out.close();
	}

}
