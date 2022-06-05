import java.io.*;
import java.util.*;
public class frames {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int m = Integer.parseInt(st.nextToken());
	    	int[][] grid = new int[n][m];
	    	for(int j=0; j<n; j++) {
	    		String str = br.readLine();
	    		for(int k=0; k<m; k++) 
	    			grid[j][k] = str.charAt(k)-'a';
	    	}
	    	boolean[][][] work1 = new boolean[n][m][Math.min(n, m)];
	    	for(int j=0; j<n; j++) {
	    		for(int k=0; k<m; k++) {
	    			for(int l=0; l<Math.min(n, m); l++) {
	    				if(j+l>=n||k+l>=m)break;
	    				if(grid[j+l][k]!=grid[j][k+l]) break;
	    				if(grid[j+l][k]!=grid[j][k])break;
	    				work1[j][k][l] = true;
	    			}
	    		}
	    	}
	    	boolean[][][] work2 = new boolean[n][m][Math.min(n, m)];
	    	for(int j=0; j<n; j++) {
	    		for(int k=0; k<m; k++) {
	    			for(int l=0; l<Math.min(n, m); l++) {
	    				if(j-l<0||k-l<0)break;
	    				if(grid[j-l][k]!=grid[j][k-l]) break;
	    				if(grid[j-l][k]!=grid[j][k])break;
	    				work2[j][k][l] = true;
	    			}
	    		}
	    	}
	    	int count = 0;
	    	for(int j=0; j<n; j++) {
	    		for(int k=0; k<m; k++) {
	    			for(int l=0; l<Math.min(n, m); l++) {
	    				if(j+l>=n||k+l>=m)break;
	    				if(grid[j+l][k]!=grid[j][k+l]) break;
	    				if(grid[j+l][k]!=grid[j][k])break;
	    				if(work1[j][k][l]&&work2[j+l][k+l][l])
	    					count++;
	    			}
	    		}
	    	}
	    	out.write(count+"\n");
	    }
	    out.flush();
	    out.close();
	}

}
