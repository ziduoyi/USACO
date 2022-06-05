import java.io.*;
import java.util.*;
public class average {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	int[] arr = new int[n];
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	Map<Integer, Integer> map = new HashMap<>();
	    	int a = 0;
	    	for(int j=0; j<n; j++) {
	    		arr[j] = Integer.parseInt(st.nextToken());
	    		if(!map.containsKey(arr[j]))
	    			map.put(arr[j], a++);
	    	}
	    	int q = Integer.parseInt(br.readLine());
	    	int[][] queries = new int[q][3];
	    	for(int j=0; j<q; j++) {
	    		st = new StringTokenizer(br.readLine());
	    		queries[j][0] = Integer.parseInt(st.nextToken())-1;
	    		queries[j][1] = Integer.parseInt(st.nextToken())-1;
	    		queries[j][2] = j;
	    	}
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
	    	double[] ans = new double[q];
	    	int pos = 0;
	    	for(int j=1; j<=(int)Math.ceil((double)n/inter); j++) {
	    		if(pos<q&&queries[pos][0]<j*inter) {
	    			int left = queries[pos][0];
	    			int right = queries[pos][0]-1;
	    			long sum = 0;
	    			long count = 0;
	    			Arrays.fill(freq, 0);
	    			while(pos<q&&queries[pos][0]<j*inter) {
	    				for(int k=right+1; k<=queries[pos][1]; k++) {
	    					if(freq[map.get(arr[k])]==0) {
	    						sum+=arr[k];
	    						count++;
	    					}
	    					freq[map.get(arr[k])]++;
	    				}
	    				right = queries[pos][1];
	    				if(queries[pos][0]>=left)
		    				for(int k=left; k<queries[pos][0]; k++) {
		    					if(freq[map.get(arr[k])]==1) {
		    						sum-=arr[k];
		    						count--;
		    					}
		    					freq[map.get(arr[k])]--;
		    				}
	    				else
		    				for(int k=queries[pos][0]; k<left; k++) {
		    					if(freq[map.get(arr[k])]==0) {
		    						sum+=arr[k];
		    						count++;
		    					}
		    					freq[map.get(arr[k])]++;
		    				}
	    				left = queries[pos][0];
	    				ans[queries[pos][2]] = ((double)sum)/(count);
	    				pos++;
	    			}
	    		}
	    		
	    	}
	    	//DecimalFormat df2 = new DecimalFormat("#.######");
	    	//out.write("Case "+(i+1)+":\n");
	    	for(int j=0; j<q; j++) {   		
	    		/*
	    		String show = df2.format(ans[j]);
	    		double thing = Double.parseDouble(show);
	    		if(thing%1==0) {
	    			out.write(thing+"00000\n");
	    			continue;
	    		}
	    		thing%=1;
	    		show = thing+"";
	    		out.write(df2.format(ans[j]));
	    		for(int k = 0; k<6-(show.length()-2); k++) {
	    			out.write(0+"");
	    		}
	    		out.write("\n");
	    		*/
	    		out.write(String.format("%.6f", ans[j]));
	    		out.write("\n");
	    	}
	    }
	    out.flush();
	    out.close();
	}

}
