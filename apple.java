import java.io.*;
import java.util.*;
public class apple {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int n = Integer.parseInt(br.readLine());
	    int[][] arr = new int[n][4];
	    for(int i=0; i<n; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	arr[i][0] = Integer.parseInt(st.nextToken());
	    	arr[i][1] = Integer.parseInt(st.nextToken());
	    	arr[i][2] = Integer.parseInt(st.nextToken());
	    	arr[i][3] = Integer.parseInt(st.nextToken());
	    }
	    Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				if(arg0[2]!=arg1[2])return arg0[2]-arg1[2];
				return arg0[1]-arg1[1];
			}
	    });
	    Set<Integer> set = new TreeSet<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				// TODO Auto-generated method stub
				if(arr[a][2]-arr[a][1]!=arr[b][2]-arr[b][1]) return (arr[a][2]-arr[a][1])-(arr[b][2]-arr[b][1]);
				return (arr[a][1]-arr[a][2])-(arr[b][1]-arr[b][2]);
			}	    	
	    });
	    int ans = 0;
	    for(int i=0; i<n; i++) if(arr[i][0]==1) set.add(i);
	    for(int i=0; i<n; i++) {
	    	if(arr[i][0]==2) {
	    		for(int pos: set) {
	    			if(arr[pos][1]<=arr[i][1]) {
	    				if((int)Math.abs(arr[pos][2]-arr[i][2])<=arr[i][1]-arr[pos][1]) {
	    					int add = Math.min(arr[i][3], arr[pos][3]);
	    					arr[i][3]-=add;
	    					arr[pos][3]-=add;
	    					ans +=add;
	    					if(arr[i][3]==0)break;
	    				}
	    			}
	    		}
	    		
	    	}
	    }
	    out.write(ans+"\n");
	    out.close();
	}

}
