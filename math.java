import java.io.*;
import java.util.*;
public class math {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    int[] count = new int[1<<10];
	    for(int i=0; i<(1<<10); i++)
	    	count[i] = Integer.bitCount(i);
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int m = Integer.parseInt(st.nextToken());
	    	int[] arr = new int[n];
	    	st = new StringTokenizer(br.readLine());
	    	for(int j=0; j<n; j++)
	    		arr[j] = Integer.parseInt(st.nextToken());
	    	int[][] grid = new int[n][m];
	    	for(int j=0; j<n; j++) {
	    		String str = br.readLine();
	    		for(int k=0; k<m; k++) 
	    			grid[j][k] = str.charAt(k)-'0';
	    	}
	    	int[] problems = new int[m];
	    	for(int j=0; j<m; j++) 
	    		for(int k=0; k<n; k++) 
	    			problems[j] +=  (grid[k][j]<<k);
	    	long max = 0;
	    	int[] ans = new int[m];
	    	for(int j=0; j<(1<<n); j++) {
	    		LinkedList<int[]> list = new LinkedList<>();
	    		for(int k=0; k<m; k++) {
	    			int add = count[j&problems[k]];
	    			int sub = count[problems[k]] - add;
	    			list.add(new int[] {add-sub,k});
	    		}
	    		Collections.sort(list, new Comparator<int[]>() {
					@Override
					public int compare(int[] arg0, int[] arg1) {
						// TODO Auto-generated method stub
						return arg0[0]-arg1[0];
					}
	    		});
	    		int[] values = new int[m];
	    		for(int k=0; k<m; k++)
	    			values[list.removeFirst()[1]] = k+1;
	    		long curr = 0;
	    		for(int k =0; k<n; k++) {
	    			long val = 0;
	    			for(int l=0; l<m; l++)
	    				val += grid[k][l]*values[l];
	    			val -= arr[k];
	    			curr += Math.abs(val);
	    		}
	    		if(max<curr) {
	    			max = curr;
	    			ans = values;
	    		}
	    	}
	    	for(int a: ans)
	    		out.write(a+" ");
	    	out.write("\n");
	    	out.flush();
	    }
	}

}
