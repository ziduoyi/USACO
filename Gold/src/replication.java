import java.util.*;
import java.io.*;
public class replication {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int N = Integer.parseInt(st.nextToken());
	    int D = Integer.parseInt(st.nextToken());
	    LinkedList<int[]> list = new LinkedList<>();
	    int[][] arr = new int[N][N]; // 0 = empty   1 = visited -1 = rock 
	    boolean[][] visit = new boolean[N][N];
	    int ans = 0;
	    for(int i=0; i<N; i++) {
	    	String str = br.readLine();
	    	for(int j=0; j<N; j++) {
	    		char c = str.charAt(j);
	    		if(c=='S') {
	    			arr[i][j] = 1;
	    			ans++;
	    			list.add(new int[] {i,j});
	    			visit[i][j] = true;
	    		}
	    		if(c=='#')
	    			arr[i][j] = -1;
	    	}
	    }
	    int[][] direct = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
	    int steps = 1;
	    int times = 0;
	    boolean ismult = false;
	    while(!list.isEmpty()) {
	    	if(steps%D==0) {
	    		times++;
	    		ismult = true;
	    	}
	    	int s = list.size();
	    	for(int i=0; i<s; i++) {
		    	int[] node = list.removeFirst();
		    	for(int j=0; j<4; j++) {
		    		int x = node[0] + direct[j][0];
		    		int y = node[1] + direct[j][1];
		    		if(visit[x][y])
		    			continue;
		    		visit[x][y]= true;	
		    		if(ismult)times--;
	    			boolean b = false;
	    			for(int k=-times; k <=times; k++) {
	    				int b1 = x-k;
	    				int b2 = y+times-Math.abs(k);
	    				if(b1<=0||b1>=N-1||b2<=0||b2>=N-1) {
	    					b = true;
	    					break;
	    				}
	    				if(arr[b1][b2] == -1) {
	    					b = true;
	    					break;
	    				}
	    				b2 = y-times+Math.abs(k);
	    				if(b1<=0||b1>=N-1||b2<=0||b2>=N-1) {
	    					b = true;
	    					break;
	    				}
	    				if(arr[b1][b2] == -1) {
	    					b = true;
	    					break;
	    				}
	    			}
	    			if(ismult)times++;
		    		if(b)
		    			continue;
		    		if(1>0) {
		    			int curr = 0;
		    			if(ismult)times--;
		    			for(int k=-times; k <=times; k++) {
		    				int b1 = x-k;
		    				int b2 = y+times-Math.abs(k);
		    				if(arr[b1][b2] == 0) {
		    					curr++;	
		    					arr[b1][b2] = 1;
		    				}
		    				b2 = y-times+Math.abs(k);
		    				if(arr[b1][b2] == 0) {
		    					curr++;
		    					arr[b1][b2] = 1;
		    				}
		    			}
		    			if(ismult)times++;
		    			ans+=curr;
		    			if(arr[x][y] ==0) {
		    				arr[x][y] = 1;
		    				ans+=1;
		    			}
		    			curr = 0;
		    			if(ismult) {
			    			b = false;
			    			for(int k=-times; k <=times; k++) {
			    				int b1 = x-k;
			    				int b2 = y+times-Math.abs(k);
			    				if(b1<=0||b1>=N-1||b2<=0||b2>=N-1) {
			    					b = true;
			    					break;
			    				}
			    				if(arr[b1][b2] == -1) {
			    					b = true;
			    					break;
			    				}
			    				b2 = y-times+Math.abs(k);
			    				if(b1<=0||b1>=N-1||b2<=0||b2>=N-1) {
			    					b = true;
			    					break;
			    				}
			    				if(arr[b1][b2] == -1) {
			    					b = true;
			    					break;
			    				}
			    			}
				    		if(b)
				    			continue;
			    			for(int k=-times; k <=times; k++) {
			    				int b1 = x-k;
			    				int b2 = y+times-Math.abs(k);
			    				if(arr[b1][b2] == 0) {
			    					curr++;	
			    					arr[b1][b2] = 1;
			    				}
			    				b2 = y-times+Math.abs(k);
			    				if(arr[b1][b2] == 0) {
			    					curr++;
			    					arr[b1][b2] = 1;
			    				}
			    			}
			    			ans+=curr;
		    			}
		    			list.add(new int[] {x,y});
		    			
		    		}
		    	}
	    	}
	    	steps++;
	    	ismult = false;
	    }
	    out.write(ans+"\n");
	    out.flush();
	    out.close();
	}

}
