import java.io.*;
import java.util.*;
public class binarystrings {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int[] mult = new int[30];
	    mult[0] = 1;
	    for(int i=1; i<30; i++)mult[i] = mult[i-1]*2;
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int k = Integer.parseInt(st.nextToken());
	    	st = new StringTokenizer(br.readLine());
	    	long ans = 1;
	    	for(int j=0; j<n; j++) {
	    		int last = -1000000000;
	    		int num = Integer.parseInt(st.nextToken());
	    		int[] arr = new int[k];
	    		for(int l = k-1; l>-1; l--) {
	    			if(num>mult[l]) {
	    				num-=mult[l];
	    				arr[l] = 1;
	    				last = l;
	    			}
	    		}
		    	ArrayList<Integer> gaps = new ArrayList<>();
		    	int count = 0;
		    	for(int l=last+1; l<k; l++) {
		    		if(arr[j]==0) 
		    			count++;
		    		else if(count>0){
		    			gaps.add(count);
		    			count = 0;
		    		}
		    	}
		    	if(last+count>0)
		    		gaps.add(last+count);
		    	if(gaps.isEmpty())continue;
		    	Map<Integer, Integer> map = new HashMap<>();
		    	for(int l=0; l<gaps.size(); l++) {
		    		if(map.containsKey(gaps.get(l)))
		    			map.put(gaps.get(l), map.get(gaps.get(l))+1);
		    		else
		    			map.put(gaps.get(l), 1);
		    	}boolean a = false;
		    	int gcd = -1;
		    	for(int val: map.values()) {
		    		if(!a)
		    			gcd = val;
		    		else {
		    			while(gcd>0&&val>0) {
		    				if(gcd>val)
		    					gcd -= ((gcd/val)*val);
		    				else
		    					val -= ((val/gcd)*gcd);
		    			}
		    			if(gcd==0) {
		    				int save = gcd;
		    				gcd = val;
		    				val = save;
		    			}
		    		}
		    	}if(gcd==1)break;
		    	int len = gaps.size()/gcd;
		    	int[] comp = new int[len];
		    	for(int l=0; l<len; l++) {
		    		comp[l] = gaps.get(l);
		    	}
		    	boolean work = true;
		    	for(int l = len; l<n; l++) {
		    		if(gaps.get(l)!=comp[l%len])
		    			work = false;
		    	}
		    	if(work) {
		    		ans*=k/gcd;
		    	}
		    	else {
		    		ans*=k;
		    	}
	    	}
	    	out.write(ans+"\n");
	    }
	    out.flush();
	    out.close();
	}

}
