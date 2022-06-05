import java.io.*;
import java.util.*;
public class paint {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int q = Integer.parseInt(st.nextToken());
	    	int[] arr = new int[n];
	    	st = new StringTokenizer(br.readLine());
	    	Map<Integer, Integer> map = new HashMap<>();
	    	int a = 0;
	    	for(int j=0; j<n; j++) {
	    		arr[j] = Integer.parseInt(st.nextToken());
	    		if(!map.containsKey(arr[j]))
	    			map.put(arr[j], a++);
	    	}
	    	int[][] queries = new int[q][4];
	    	for(int j=0; j<q; j++) {
	    		st = new StringTokenizer(br.readLine());
	    		queries[j][0] = Integer.parseInt(st.nextToken())-1;
	    		queries[j][1] = Integer.parseInt(st.nextToken())-1;
	    		queries[j][2] = Integer.parseInt(st.nextToken());
	    		queries[j][3] = j;
	    	}/*
	    	int inter = (int)Math.ceil(Math.sqrt(n));
	    	int[] freq = new int[map.size()];
	    	Arrays.sort(queries, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					if(o1[0]/inter != o2[0]/inter) return o1[0]/inter - o2[0]/inter;
					return o1[1]-o2[1];
				}	    		
	    	});
	    	long[] ans = new long[q];
	    	int stop = inter-1;
	    	int curr = 0;
	    	int pos = 0;
	    	for(int j=1; j<=(int)Math.ceil((double)n/inter); j++) {
	    		if(pos<q&&queries[pos][0]<j*inter) {
	    			int left = queries[pos][0];
	    			int right = queries[pos][0]-1;
	    			Arrays.fill(freq, 0);
	    			while(pos<q&&queries[pos][0]<j*inter) {
	    				for(int k=right+1; k<=queries[pos][1]; k++) {
	    					freq[map.get(arr[k])]++;
	    				}
	    				right = queries[pos][1];
	    				if(queries[pos][0]>=left)
		    				for(int k=left; k<queries[pos][0]; k++) {
		    					freq[map.get(arr[k])]--;
		    				}
	    				else
		    				for(int k=queries[pos][0]; k<left; k++) {
		    					freq[map.get(arr[k])]++;
		    				}
	    				left = queries[pos][0];
	    				if(map.containsKey(queries[pos][2]))
	    					ans[queries[pos][3]] = freq[map.get(queries[pos][2])];
	    				pos++;
	    			}
	    		}
	    		
	    	}
	    	for(int j=0; j<q; j++)
	    		out.write(ans[j]+"\n");
	    		*/
	    }
	    out.flush();
	    out.close();
	}

}
