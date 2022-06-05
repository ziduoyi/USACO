import java.io.*;
import java.util.*;
public class room {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n  = Integer.parseInt(st.nextToken());
	    	int m = Integer.parseInt(st.nextToken());
	    	int[][] grid = new int[n][m];
	    	for(int j=0; j<n; j++) {
	    		String str = br.readLine();
	    		for(int k=0; k<m;k ++) {
	    			if(str.charAt(k)=='#')
	    				grid[j][k] = 1;
	    			else
	    				grid[j][k] = 0;
	    		}
	    	}
	    	int[][] direct = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
	    	int count = 0;
	    	LinkedList<int[]> list = new LinkedList<>();
	    	int[][] arr = new int[n][m];
	    	for(int j=0; j<n; j++) Arrays.fill(arr[j], -1);
	    	boolean[][] visit = new boolean[n][m];
	    	ArrayList<Integer> sizes = new ArrayList<>();
	    	for(int j=0; j<n; j++) {
	    		for(int k=0; k<m; k++) {
	    			if(!visit[j][k]&&grid[j][k]==0) {
	    				list.add(new int[] {j,k});
	    				visit[j][k] = true;
	    				arr[j][k] = count;
	    				int size = 1;
	    				while(!list.isEmpty()) {
	    					int[] curr = list.removeFirst();
	    					for(int l=0; l<4; l++) {
	    						int x = curr[0] + direct[l][0];
	    						int y = curr[1] + direct[l][1];
	    						if(x>-1&&x<n&&y>-1&&y<m) {
	    							if(!visit[x][y]&&grid[x][y]==0) {
	    								visit[x][y] = true;
	    								arr[x][y] = count;
	    								size++;
	    								list.add(new int[] {x,y});
	    							}
	    						}
	    					}
	    				}
	    				sizes.add(size);
	    				count++;
	    			}
	    		}
	    	}
	    	int ans = 1;
	    	if(sizes.size()>0)
	    		ans = sizes.get(0);
	    	HashSet<Integer> set = new HashSet<>();
	    	for(int j=0; j<n; j++) {
	    		for(int k=0; k<m; k++) {
	    			if(grid[j][k]==1) {
	    				for(int l=0; l<4; l++) {
	    					int x = direct[l][0]+j;
	    					int y = direct[l][1]+k;
	    					if(x>-1&&x<n&&y>-1&&y<m) {
	    						if(grid[x][y]==0) 
	    							set.add(arr[x][y]);
	    					}
	    				}
	    				int here = 0;
	    				for(int num: set) 
	    					here += sizes.get(num);
	    				ans = Math.max(ans, here+1);
	    				set.clear();
	    			}
	    		}
	    	}
	    	out.write(ans+"\n");
	    }
	    out.flush();
	    out.close();
	}

}
