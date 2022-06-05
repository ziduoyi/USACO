import java.io.*;
import java.util.*;
public class icepath {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    int[][] direct = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int m = Integer.parseInt(st.nextToken());
	    	char[][] grid = new char[n][m];
	    	int[] start = new int[2];
	    	int[] end = new int[2];
	    	for(int j=0; j<n; j++) {
	    		String str = br.readLine();
	    		grid[j] = str.toCharArray().clone();
	    		for(int k=0; k<m; k++) {
	    			if(grid[j][k] == 'S') {
	    				start[0] = j;
	    				start[1] = k;
	    			}
	    			if(grid[j][k] == 'E') {
	    				end[0] = j;
	    				end[1] = k;
	    			}
	    		}
	    	}
	    	LinkedList<int[]> list = new LinkedList<>();
	    	boolean[][] visit = new boolean[n][m];
	    	visit[start[0]][start[1]] = true;
	    	list.add(new int[] {start[0], start[1]});
	    	int step = 0;
	    	boolean b = false;
	    	while(!list.isEmpty()) {
	    		step++;
	    		int s = list.size();
	    		for(int j=0; j<s; j++) {
	    			int[] pos = list.removeFirst();
	    			for(int k=0; k<4; k++) {
	    				int x = pos[0];
	    				int y = pos[1];
	    				while(x>=0&&x<n&&y>=0&&y<m) {
	    					x+= direct[k][0];
	    					y+= direct[k][1];
	    					if(x<0||x>=n||y<0||y>=m) {
		    					x-= direct[k][0];
		    					y-= direct[k][1];
		    					break;
	    					}
	    					if(grid[x][y] == 'X') {
		    					x-= direct[k][0];
		    					y-= direct[k][1];
		    					break;
	    					}
	    				}
	    				if(!visit[x][y]) {
	    					visit[x][y] = true;
	    					if(grid[x][y]=='E') {
	    						b = true;
	    						break;
	    					}
	    					list.add(new int[] {x,y});
	    				}
	    			}
	    			if(b)break;
	    		}
	    		if(b)break;
	    	}
	    	if(!b)out.write(-1+"\n");
	    	else out.write(step+"\n");
	    }
	    out.flush();
	    out.close();
	}

}
