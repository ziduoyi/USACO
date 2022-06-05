import java.io.*;
import java.util.*;
public class permutation {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int N = Integer.parseInt(br.readLine());
	    int[][] store = new int[N][2];
	    for(int i=0; i<N; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	store[i][0] = Integer.parseInt(st.nextToken());
	    	store[i][1] = Integer.parseInt(st.nextToken());
	    }	   
	    boolean[][][][] inside = new boolean[N][N][N][N]; 
	    for(int i=0; i<N; i++) {
	    	for(int j=0; j<N; j++) {
	    		for(int k=0; k<N; k++) {
	    			if(i==j||i==k||j==k)continue;
	    			for(int l=0; l<N; l++) {
	    				double area = area(store[i][0], store[i][1], store[j][0], store[j][1], store[l][0], store[l][1]);
	    				area += area(store[i][0], store[i][1], store[k][0], store[k][1], store[l][0], store[l][1]);
	    				area += area(store[j][0], store[j][1], store[k][0], store[k][1], store[l][0], store[l][1]);
	    				if(area == area(store[i][0], store[i][1], store[j][0], store[j][1], store[k][0], store[k][1])) 
	    					inside[i][j][k][l] = true;
	    			}
	    		}
	    	}
	    }
	    long[][][][] dp = new long[N][N][N][N];
	    LinkedList<Integer> outside = new LinkedList<>();
	    for(int i=0; i<N; i++) {
	    	for(int j=0; j<N; j++) {
	    		for(int k=0; k<N; k++) {
	    			if(i==j||i==k||j==k)continue;
	    			dp[i][j][k][0] = 1;
	    			int count = 0;
	    			for(int l=0; l<N; l++) {
	    				if(inside[i][j][k][l])
	    					count++;
	    				else 
	    					outside.add(l);
	    			}
	    			count -=3;
	    			for(int l=1; l<=count; l++) 
	    				dp[i][j][k][l] = (dp[i][j][k][l]+dp[i][j][k][l-1]*(count-l+1))%1000000007;
	    			while(!outside.isEmpty()) {
	    				int num = outside.removeFirst();
	    				for(int l=1; l<N; l++) {
		    				if(inside[i][j][num][k])
		    					dp[i][j][num][l] = (dp[i][j][num][l]+dp[i][j][k][l-1])%1000000007;
		    				if(inside[i][k][num][j])
		    					dp[i][k][num][l] += (dp[i][k][num][l]+dp[i][j][k][l-1])%1000000007;
		    				if(inside[j][k][num][i])
		    					dp[j][k][num][l] += (dp[j][k][num][l]+dp[i][j][k][l-1])%1000000007;
	    				}
	    			}
	    		}
	    	}
	    }
	    long ans = 0;
	    for(int i=0; i<N; i++) {
	    	for(int j=0; j<N; j++) {
	    		for(int k=0; k<N; k++) {
	    			if(i==j||i==k||j==k)continue;
	    			ans = (ans + dp[i][j][k][N-3])%1000000007;
	    		}
	    	}
	    }
	    out.write(ans+"\n");
	    out.close();
	}
	static double area(int x1, int y1, int x2,int y2,int x3,int y3) {
		return Math.abs(x1*y2+x2*y3+x3*y1-x2*y1-x3*y2-x1*y3)/(double)2;
	}
}
