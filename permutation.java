import java.io.*;
import java.util.*;
public class permutation {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int N = Integer.parseInt(br.readLine());
	    int[][] arr = new int[N][2];
	    for(int i=0; i<N; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	arr[i][0] = Integer.parseInt(st.nextToken());
	    	arr[i][1] = Integer.parseInt(st.nextToken());
	    }
	    boolean[] choose = new boolean[N*N*N*N];
	    for(int i=0; i<N; i++) 
	    	for(int j=0; j<N; j++) 
	    		for(int k=0; k<N; k++) 
	    			for(int l =0; l<N; l++)
		    			if(i!=j&&j!=k&&i!=k&&l!=i&&l!=j&&l!=k) {
		    				int sum = area(arr[l][0],arr[l][1],arr[j][0],arr[j][1],arr[i][0],arr[i][1]);
		    				sum += area(arr[l][0],arr[l][1],arr[k][0],arr[k][1],arr[i][0],arr[i][1]);
		    				sum += area(arr[j][0],arr[j][1],arr[k][0],arr[k][1],arr[i][0],arr[i][1]);
		    				if(sum==area(arr[l][0],arr[l][1],arr[j][0],arr[j][1],arr[k][0],arr[k][1]))
		    					choose[(i*N*N*N)+(j*N*N)+(k*N)+l] = true;
		    			}
	    boolean[] inside = new boolean[N*N*N*N];
	    for(int i=0; i<N; i++) 
	    	for(int j=0; j<N; j++) 
	    		for(int k=0; k<N; k++) 
	    			for(int l =0; l<N; l++)
		    			if(i!=j&&j!=k&&i!=k&&l!=i&&l!=j&&l!=k) {
		    				int sum = area(arr[i][0],arr[i][1],arr[j][0],arr[j][1],arr[l][0],arr[l][1]);
		    				sum += area(arr[i][0],arr[i][1],arr[k][0],arr[k][1],arr[l][0],arr[l][1]);
		    				sum += area(arr[j][0],arr[j][1],arr[k][0],arr[k][1],arr[l][0],arr[l][1]);
		    				if(sum==area(arr[i][0],arr[i][1],arr[j][0],arr[j][1],arr[k][0],arr[k][1]))
		    					inside[(i*N*N*N)+(j*N*N)+(k*N)+l] = true;
		    			}
	    long[][][][] dp = new long[N][N][N][N+1];
	    LinkedList<int[]> list = new LinkedList<>(); 
	    boolean[][][][] visit = new boolean[N][N][N][N+1];
	    for(int i=0; i<N; i++) 
	    	for(int j=0; j<N; j++) 
	    		for(int k=0; k<N; k++) 
	    			if(i!=j&&j!=k&&i!=k) {
	    				dp[i][j][k][3] = 1;
	    				list.add(new int[] {i,j,k,3});
	    			}
	    while(!list.isEmpty()) {
	    	int s = list.size();
	    	for(int z=0; z<s; z++) {
		    	int[] pos = list.removeFirst();
		    	if(visit[pos[0]][pos[1]][pos[2]][pos[3]])
		    		continue;
		    	visit[pos[0]][pos[1]][pos[2]][pos[3]] = true;
		    	if(pos[3]==N)break;
		    	int cnt = 3;
		    	long val = dp[pos[0]][pos[1]][pos[2]][pos[3]];
		    	for(int i=0; i<N; i++)
		    		if(inside[(pos[0]*N*N*N)+(pos[1]*N*N)+(pos[2]*N)+i])
		    			cnt++;
		    	if(pos[3]<cnt) {
		    		dp[pos[0]][pos[1]][pos[2]][pos[3]+1] += (val*(cnt-pos[3]));
		    		dp[pos[0]][pos[1]][pos[2]][pos[3]+1] %= 1000000007;
		    		list.add(new int[] {pos[0],pos[1],pos[2],pos[3]+1});
		    	}
		    	for(int i=0; i<N; i++) {
		    		if(i==pos[0]||i==pos[1]||i==pos[2])continue;
		    		if(!inside[(pos[0]*N*N*N)+(pos[1]*N*N)+(pos[2]*N)+i]) {
		    			if(choose[(pos[0]*N*N*N)+(pos[1]*N*N)+(pos[2]*N)+i]) {
		    				dp[i][pos[1]][pos[2]][pos[3]+1] += val;
		    				list.add(new int[] {i, pos[1], pos[2],pos[3]+1});
		    				dp[i][pos[1]][pos[2]][pos[3]+1] %= 1000000007;
		    			}
		    			else if(choose[(pos[0]*N*N)+(pos[1]*N*N*N)+(pos[2]*N)+i]) {
		    				dp[pos[0]][i][pos[2]][pos[3]+1] += val;
		    				list.add(new int[] {pos[0], i, pos[2],pos[3]+1});
		    				dp[pos[0]][i][pos[2]][pos[3]+1] %= 1000000007;
		    			}
		    			else if(choose[(pos[0]*N)+(pos[1]*N*N)+(pos[2]*N*N*N)+i]) {
		    				dp[pos[0]][pos[1]][i][pos[3]+1] += val;
		    				list.add(new int[] {pos[0], pos[1], i,pos[3]+1});
		    				dp[pos[0]][pos[1]][i][pos[3]+1] %= 1000000007;
		    			}
		    		}
		    	}
	    	}
	    }
	    long ans = 0;
	    for(int i=0; i<N; i++)
	    	for(int j=0; j<N; j++)
	    		for(int k=0; k<N; k++)
	    			if(i!=j&&j!=k&&i!=k)
	    				ans += dp[i][j][k][N];
	    ans %=1000000007;
	    out.write(ans +"\n");
	    out.flush();
	    out.close();
	}
	static int area(int x1, int y1, int x2, int y2, int x3, int y3) {
		return Math.abs(((x1*y2)+(x2*y3)+(x3*y1))-((x2*y1)+(x3*y2)+(x1*y3)));	
	}
}
