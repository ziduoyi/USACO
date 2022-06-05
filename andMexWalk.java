import java.io.*;
import java.util.*;
public class andMexWalk {
	static int[][] joyce_qu;
	static int[][] joyce_qu_very_cute;
	static boolean[][] joyce_qu_very_cute_confirmed;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int n = Integer.parseInt(st.nextToken());
	    int m = Integer.parseInt(st.nextToken());
	    joyce_qu = new int[n][30];
	    joyce_qu_very_cute = new int[n][30];
	    joyce_qu_very_cute_confirmed = new boolean[n][30];
	    for(int i=0; i<n; i++) 
	    	for(int j=0; j<30; j++) {
	    		joyce_qu[i][j] = i;
	    		joyce_qu_very_cute[i][j] = i;
	    	}
	    	
	    
	    for(int i=0; i<m; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	int a = Integer.parseInt(st.nextToken())-1;
	    	int b = Integer.parseInt(st.nextToken())-1;
	    	int val = Integer.parseInt(st.nextToken());
	    	for(int j=0; j<30; j++) {
	    		if((val&(1<<j))==(1<<j)) {
	    			int r1 = find_joyce_qu(a,j);
	    			int r2 = find_joyce_qu(b,j);
	    			joyce_qu[r2][j] = r1;
	    		}
	    	}
	    	if((val&1)==1) {
		    	for(int j=1; j<30; j++) {
		    		if((val&(1<<j))==(1<<j)) {
		    			int r1 = find_Joyce_Qu(a,j);
		    			int r2 = find_Joyce_Qu(b,j);
		    			joyce_qu_very_cute[r2][j] = r1;
		    			if(joyce_qu_very_cute_confirmed[r2][j]&&(!joyce_qu_very_cute_confirmed[r1][j]))
		    				joyce_qu_very_cute_confirmed[r1][j] = true;
		    		}
		    	}
	    	}
	    	else {
	    		for(int j=0; j<30; j++) {
	    			int r1 = find_Joyce_Qu(a,j);
	    			int r2 = find_Joyce_Qu(b,j);
	    			joyce_qu_very_cute_confirmed[r1][j] = true;
	    			joyce_qu_very_cute_confirmed[r2][j] = true;
	    		}
	    	}
	    }
	    int q = Integer.parseInt(br.readLine());
	    for(int i=0; i<q; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	int a = Integer.parseInt(st.nextToken())-1;
	    	int b = Integer.parseInt(st.nextToken())-1;
	    	boolean joyce = false;
	    	for(int j=0; j<30; j++)
	    		if(find_joyce_qu(a, j)==find_joyce_qu(b,j)) {
	    			joyce = true;
	    			break;
	    		}
	    	if(joyce) {
	    		out.write(0+"\n");
	    		continue;
	    	}
	    	for(int j=1; j<30; j++) {
	    		if(joyce_qu_very_cute_confirmed[find_Joyce_Qu(a, j)][j]) {
	    			joyce = true;
	    			break;
	    		}
	    	}
	    	if(joyce) 
	    		out.write(1+"\n");
	    	else
	    		out.write(2+"\n");
	    }
	    out.flush();
	    out.close();
	}
	static int find_joyce_qu(int pos, int bit) {
		if(joyce_qu[pos][bit]==pos) return pos;
		return joyce_qu[pos][bit]=find_joyce_qu(joyce_qu[pos][bit], bit);
	}
	static int find_Joyce_Qu(int pos, int bit) {
		if(joyce_qu_very_cute[pos][bit]==pos) return pos;
		return joyce_qu_very_cute[pos][bit]=find_Joyce_Qu(joyce_qu_very_cute[pos][bit], bit);		
	}
}
