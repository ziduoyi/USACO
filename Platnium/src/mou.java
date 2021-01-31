import java.io.*;
import java.util.*;
public class mou {
    static long[][] seg;
    static int[] lazy;
    static ArrayList<String> types;
    static ArrayList<int[]> list;
    static ArrayList<int[]> base;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		types = new ArrayList<>();
		list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		String c = st.nextToken();
		TreeSet<Integer> start = new TreeSet<>();
		TreeSet<Integer> end = new TreeSet<>();
		while(!c.equals("E")) {
			types.add(c);
			if(c.equals("Q")) {
				int a = Integer.parseInt(st.nextToken());
				list.add(new int[] {a});
			}
			else {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				list.add(new int[] {a,b,d});
				start.add(a);
				end.add(b);
			}
			st = new StringTokenizer(br.readLine());
			c = st.nextToken();
		}
		base = new ArrayList<>();
		int curr  =0;
		while(start.ceiling(curr)!=null) {
			curr = start.ceiling(curr);
			if(start.ceiling(curr+1)==null) {
				base.add(new int[] {curr, end.ceiling(curr)});
				curr = end.ceiling(curr);
			}
			
			else {
				int store = curr+1;
				while(start.ceiling(store)>end.ceiling(curr)) {
					base.add(new int[] {curr, end.ceiling(curr)});
					curr = end.ceiling(curr)+1;
				}
				if(curr!=start.ceiling(store)) {
					base.add(new int[] {curr, start.ceiling(store)-1});
					curr = start.ceiling(store)-1;
				}
				else
					curr--;
			}
			curr++;
		}
		while(end.ceiling(curr)!=null) {
			base.add(new int[] {curr, end.ceiling(curr)});
			curr = end.ceiling(curr)+1;
		}
		Map<Integer, Integer> maps = new HashMap<>();
		Map<Integer, Integer> mape = new HashMap<>();
		for(int i=0; i< base.size(); i++) {
			maps.put(base.get(i)[0], i);
			mape.put(base.get(i)[1], i);
		}
		seg = new long[4*base.size()][2];//0: max           1:max prefix        2: l            3: r 
		lazy = new int[4*base.size()];
		Arrays.fill(lazy, 1000000001);
		for(int i=0; i<types.size(); i++) {
			if(types.get(i).equals("Q")) {
				if(seg[1][1]<=list.get(i)[0])
					System.out.println(N);
				else {
					int store = query(1, 0, base.size()-1, list.get(i)[0],0);
					System.out.println(store);
				}
			}
			else {
				rangeUpdate(1, list.get(i)[2], maps.get(list.get(i)[0]), mape.get(list.get(i)[1]), 0, base.size()-1);
			}
		}
	}
    static void rangeUpdate(int x, int delta, int a, int b, int l, int r) {
    	int s1=x*2,s2=s1+1;
    	int range = base.get(r)[1]-base.get(l)[0]+1;
    	if(lazy[x]!=1000000001) {
    		seg[x][0] = (long)lazy[x]*(long)range;
    		seg[x][1] =0;
    		if(lazy[x]>0)
    			seg[x][1] = seg[x][0];
    		if(l!=r) {
    			lazy[s1] =lazy[x];
    			lazy[s2]= lazy[x];
    		}
    		lazy[x] =1000000001;
    	}
    	if(l>r|| r<a|| b<l)
    		return; 
    	if(a<=l&&b>=r) { 
    		seg[x][0] = (long)delta*(long)range;
    		seg[x][1] = Math.max((long)delta*(long)range, 0);
    		if(l!=r) {
    			lazy[s1] = delta;
    			lazy[s2] = delta;
    		}
    	}
    	else {
    		int mid = (l+r)/2;
    		if(l!=r) {
    			rangeUpdate(s1, delta, a, b, l, mid);
    			rangeUpdate(s2, delta, a, b, mid+1, r);
    		}
    		seg[x][0] = seg[s1][0] + seg[s2][0];
    		seg[x][1] = Math.max(seg[s1][1], seg[s1][0]+seg[s2][1]);
    	}
    }
    static int query(int x, int l, int r, int comp, long sum) {
    	int s1=x*2,s2=s1+1;
    	int range = base.get(r)[1] - base.get(l)[0] +1;
    	if(lazy[x]!=1000000001) {
    		seg[x][0] = (long)lazy[x]*((long)range);
    		seg[x][1] =0;
    		if(lazy[x]>0)
    			seg[x][1] = seg[x][0];
    		if(l!=r) {
    			lazy[s1] =lazy[x];
    			lazy[s2] = lazy[x];
    		}
    		lazy[x] =1000000001;
    	}
    	int mid = (l+r)/2;
    	if(l==r) {
    		long extra =  Math.max(sum+seg[x][1]-comp,0);
    		int mult = (int)( (seg[x][0]/(base.get(l)[1]-base.get(l)[0]+1)));
    		return (int) (base.get(l)[1]- Math.ceil((double)extra/mult));
    	}
    	if(lazy[s1]==1000000001) {
        	if(sum+seg[s1][1]>comp) {
        		int ans = query(s1, l, mid,comp, sum);
        		return ans;
        	}
        	return query(s2, mid+1, r,comp, sum+seg[s1][0]);
    	}
    	if(sum+lazy[s1]*(base.get(mid)[1]-base.get(l)[0]+1)>comp) {
    		int ans = query(s1, l, mid,comp,sum);
    		return ans;
    	}
    	return query(s2, mid+1, r,comp, sum+lazy[s1]*(base.get(mid)[1]-base.get(l)[0]+1));
    }
}
