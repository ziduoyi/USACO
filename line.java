import java.io.*;
import java.util.*;
public class line {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	long n = Long.parseLong(st.nextToken());
	    	long k = Long.parseLong(st.nextToken());
	    	int r = Integer.parseInt(st.nextToken());
	    	ArrayList<Long> list = new ArrayList<>();
	    	for(int j=0; j<r; j++)
	    		list.add(Long.parseLong(br.readLine()));
	    	list.add(n);
	    	list.add((long) 0);
	    	Collections.sort(list);
	    	long count = 0;
	    	TreeSet<Long> set = new TreeSet<>();
	    	HashMap<Long, Long> map = new HashMap<>();
	    	HashMap<Long, Integer> find = new HashMap<>();
	    	for(int j=0; j<list.size()-1; j++) {
	    		count += (list.get(j+1)-list.get(j))/k;
	    		long num = (list.get(j+1)-list.get(j))%k;
	    		if(set.contains(num)) 
	    			map.put(num, map.get(num)+1);
	    		else {
	    			set.add(num);
	    			map.put(num, (long) 1);
	    		}
	    	}
	    	long[] summary = new long[set.size()];
	    	int a = 0;
	    	for(long use: set) {
	    		find.put(use, a);
	    		if(a==0) summary[a] = map.get(use);
	    		else summary[a] = map.get(use) + summary[a-1];
	    		a++;
	    	}
	    	int q = Integer.parseInt(br.readLine());
	    	for(int j=0; j<q; j++) {
	    		long pos = Long.parseLong(br.readLine());
	    		long ans = 0;
	    		ans+= count;
	    		ans+=summary[set.size()-1];
	    		if(set.lower(pos)!=null)
	    			ans -= summary[find.get(set.lower(pos))];
	    		out.write(ans+"\n");
	    	}
	   }
	    out.flush();
	    out.close();
	}

}
