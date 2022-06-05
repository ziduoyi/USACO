import java.io.*;
import java.util.*;
public class prime {

	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    LinkedList<Integer>[] list = new LinkedList[1000001];
	    for(int i=2; i<1000001; i++)list[i] = new LinkedList<>();
	    for(int i=2; i<1000001; i++) {
	    	if(list[i].isEmpty()) {
	    		for(int j=i; j<1000001; j+=i) 
	    			list[j].add(i);
	    	}
	    }
    	Map<Integer, Integer> map = new HashMap<>();
    	int a = 0;
    	for(int i=2; i<1000001; i++)
    		if(list[i].size()==1)
    			map.put(i, a++);
    	int[] freq = new int[map.size()];
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int q = Integer.parseInt(st.nextToken());
	    	int[] arr = new int[n];
	    	st = new StringTokenizer(br.readLine());
	    	for(int j=0; j<n; j++) 
	    		arr[j] = Integer.parseInt(st.nextToken());
	    	int[][] queries = new int[q][3];
	    	for(int j=0; j<q; j++) {
	    		st = new StringTokenizer(br.readLine());
	    		queries[j][0] = Integer.parseInt(st.nextToken())-1;
	    		queries[j][1] = Integer.parseInt(st.nextToken())-1;
	    		queries[j][2] = j;
	    	}
	    	int inter = (int)Math.ceil(Math.sqrt(n));
	    	Arrays.sort(queries, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					if(o1[0]/inter != o2[0]/inter) return o1[0]/inter - o2[0]/inter;
					return o1[1]-o2[1];
				}	    		
	    	});
	    	int[] ans = new int[q];
	    	int pos = 0;
	    	for(int j=1; j<=(int)Math.ceil((double)n/inter); j++) {
	    		if(pos<q&&queries[pos][0]<j*inter) {
	    			int left = queries[pos][0];
	    			int right = queries[pos][0]-1;
	    			int count = 0;
	    			Arrays.fill(freq, 0);
	    			while(pos<q&&queries[pos][0]<j*inter) {
	    				for(int k=right+1; k<=queries[pos][1]; k++) {
		    					for(int num: list[arr[k]]) {
		    						num = map.get(num);
		    						if(freq[num]==0)
		    							count++;
		    						freq[num]++;
		    					}
	    				}
	    				right = queries[pos][1];
	    				if(queries[pos][0]>=left)
		    				for(int k=left; k<queries[pos][0]; k++) {
			    					for(int num: list[arr[k]]) {
			    						num = map.get(num);
			    						if(freq[num]==1)
			    							count--;
			    						freq[num]--;
			    					}
		    				}
	    				else
		    				for(int k=queries[pos][0]; k<left; k++) {
			    					for(int num: list[arr[k]]) {
			    						num = map.get(num);
			    						if(freq[num]==0)
			    							count++;
			    						freq[num]++;
			    					}
		    				}
	    				left = queries[pos][0];
	    				ans[queries[pos][2]] = count;
	    				pos++;
	    			}
	    		}
	    		
	    	}
	    	for(int j=0; j<q; j++)
	    		out.write(ans[j]+"\n");
	    }
	    out.flush();
	    out.close();
	}

}
