import java.io.*;
import java.util.*;
public class partition {
	static long[] bitree;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	bitree = new long[n+1];
	    	Arrays.fill(bitree, -1000000000);
	    	int[] arr = new int[n+1];
	    	TreeSet<Long>joyce_qu = new TreeSet<>();
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	long sum = 0;
	    	for(int j=1; j<=n; j++) {
	    		arr[j] = Integer.parseInt(st.nextToken());
	    		joyce_qu.add(sum);
	    		sum+=arr[j];
	    	}
	    	Map<Long, Integer> map = new HashMap<>();
	    	int a = 1;
	    	map.put(null, 0);
	    	for(long num: joyce_qu)map.put(num, a++);
	    	update(map.get(0L),0);
	    	sum = 0;
	    	long last = 0;
	    	for(int j=1; j<=n; j++) {
	    		sum += arr[j];
	    		int p = map.get(joyce_qu.lower(sum));
	    		if(arr[j]>0)last ++;
	    		if(arr[j]<0)last --;
	    		if(p!=0) {
	    			long thing = query(p);
	    			if(thing!=-1000000000) {
		    			last = Math.max(last, thing + j);
		    			
	    			}
	    		}
	    		if(j!=n)
	    			update(map.get(joyce_qu.floor(sum)), last-j);
	    	}
	    	out.write(last+"\n");
	    }
	    out.flush();
	    out.close();
	}
	static void update(int pos, long val) {
		for(int j=pos; j<bitree.length; j+= (-j&j))
			bitree[j] = Math.max(bitree[j], val);
	}
	static long query(int pos) {
		long ans = -1000000000;
		for(int j=pos; j>0; j-= (-j&j))
			ans = Math.max(bitree[j], ans);
		return ans;
	}
}
