import java.io.*;
import java.util.*;
public class stonks {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	TreeSet<Integer> set = new TreeSet<>();
	    	Map<Integer, Integer> map = new HashMap<>();
	    	int n = Integer.parseInt(br.readLine());
	    	long ans = 0;
	    	for(int j=0; j<n; j++) {
	    		StringTokenizer st = new StringTokenizer(br.readLine());
	    		String str = st.nextToken();
	    		int s = Integer.parseInt(st.nextToken());
	    		int p = Integer.parseInt(st.nextToken());
	    		if(str.equals("BUY")) {
	    			if(set.contains(p)) 
	    				map.put(p, map.get(p)+s);
	    			else {
	    				map.put(p, s);
	    				set.add(p);
	    			}
	    		}
	    		else {
	    			while(s>0) {
	    				int val = set.pollLast();
	    				int num = map.get(val);
	    				if(s>=num) {
	    					s-=num;
	    					map.remove(val);
	    					ans += ((long)num) * (p-val);
	    				}
	    				else {
	    					map.put(val, num-s);
	    					ans += ((long)s) * (p-val); 
	    					set.add(val);
	    					s = 0;
	    				}
	    			}
	    		}
	    	}
	    	out.write(ans+"\n");
	    }
	    out.flush();
	    out.close();
	}

}
