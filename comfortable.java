import java.io.*;
import java.util.*;
public class comfortable {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int n = Integer.parseInt(br.readLine());
	    int[][] list = new int[n][2]; //stores the input
	    for(int i=0; i<n; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	list[i][0] = Integer.parseInt(st.nextToken())+500; //coordinates are increased by 500 to prevent negative positions
	    	list[i][1] = Integer.parseInt(st.nextToken())+500;
	    }
	    boolean[][] cows = new boolean[2000][2000]; //2000 because cows positions can go from -499 to 1500
	    int[][] direct = new int[][] {{-1,0},{0,-1},{1,0},{0,1},{0,0}};
	    LinkedList<int[]> que = new LinkedList<>();
	    int num = 0;
	    for(int i=0; i<n; i++) {
	    	if(!cows[list[i][0]][list[i][1]]) {
	    		num +=1;
	    		cows[list[i][0]][list[i][1]] = true;
	    		que.add(new int[] {list[i][0], list[i][1]});
	    		while(!que.isEmpty()) {
	    			int[] curr = que.removeFirst();// get the current cow
	    			for(int j=0; j<5; j++) {
	                    int checkx = curr[0] + direct[j][0]; //the position of a cow we are checking
	                    int checky = curr[1] + direct[j][1]; //for comfortability
	                    int count = 0;
	                    int dir = -1;
	                    if( !cows[checkx][checky] ) //if the checking positions doesn't even have a cow, its useless to check
	                    	continue;
	                    for (int k=0; k<4; k++) {
	                    	int x = checkx + direct[k][0];
	                    	int y = checky + direct[k][1];
	                    	if (cows[x][y]) 
	                    		count += 1;
	                        else 
	                        	dir = k;
	                    }
                        if (count==3) { //if a cow is comfortable, add a cow so that it becomes surrounded
                        	int addx = checkx + direct[dir][0];
                        	int addy = checky + direct[dir][1];
                        	if (!cows[addx][addy]) { //check the cow itself and neighbors in future iterations
                            	que.add(new int[] {addx,addy});
                            	cows[addx][addy] = true;
                            	num +=1 ;
                            }
                        }
	    			}
	    		}
	    	}
	    	out.write((num-(i+1))+"\n"); //(# of cows we have with no comfortable) - (# of non-added cows) 
	    	// = (#of cows that need to be added)
	    }
	    out.flush();
	}

}
