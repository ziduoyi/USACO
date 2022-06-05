import java.io.*;
import java.util.*;
public class weird {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int n = Integer.parseInt(st.nextToken());
	    int m = Integer.parseInt(st.nextToken());
	    long[][] arr = new long[n+1][m+1];
	    for(int i=1; i<=n; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	for(int j=1; j<=m; j++) {
	    		arr[i][j] = Integer.parseInt(st.nextToken());
	    	}
	    }
	    long[][] row = new long[100001][4];
	    long[][] col = new long[100001][4];
	    for(int i=1; i<=n; i++) {
	    	for(int j=1; j<=m; j++) {
	    		int num = (int)arr[i][j];
	    		if(row[num][0]==0) {
	    			row[num][0] = i;
	    			row[num][2]++;
	    		}
	    		else {
	    			row[num][1] += (i - row[num][0])*row[num][2];
	    			row[num][0] = i;
	    			row[num][3] += row[num][1];
	    			row[num][2]++;
	    		}
	    	}
	    }
	    for(int j=1; j<=m; j++) {
	    	for(int i=1; i<=n; i++) {
	    		int num = (int)arr[i][j];
	    		if(col[num][0]==0) {
	    			col[num][0] = j;
	    			col[num][2]++;
	    		}
	    		else {
	    			col[num][1] += (j - col[num][0])*col[num][2];
	    			col[num][0] = j;
	    			col[num][3] += col[num][1];
	    			col[num][2]++;
	    		}
	    	}
	    }
	    long ans = 0;
	    for(int i=1; i<=100000; i++) ans += row[i][3] + col[i][3];
	    out.write(ans+"\n");
	    out.flush();
	    out.close();
	}

}
