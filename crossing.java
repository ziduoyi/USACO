import java.io.*;
import java.util.*;
public class crossing {
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	br.readLine();
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int m = Integer.parseInt(st.nextToken());
		    Set<Integer> cannot = new HashSet<>();
		    ArrayList<Integer> last = new ArrayList<>();
		    boolean ans = true;
	    	for(int j=0; j<m; j++) {
	    		st = new StringTokenizer(br.readLine());
	    		int s = Integer.parseInt(st.nextToken());
	    		int[] now = new int[s];
	    		for(int k=0; k<s; k++) {
	    			now[k] = Integer.parseInt(st.nextToken());
	    		}
	    		if(!ans) continue;
	    		Set<Integer> has = new HashSet<>();
	    		for(int num: now)	has.add(num);
	    		ArrayList<Integer> newLast = new ArrayList<>();
	    		for(int num: last) {
	    			if(has.contains(num)) newLast.add(num);
	    			else cannot.add(num);
	    		}
	    		if(!isSub(newLast, now)) ans = false;
	    		for(int num: now) if(cannot.contains(num)) ans = false;
	    		last.clear();
	    		for(int k=0; k<now.length; k++)
	    			last.add(now[k]);
	    	}
	    	if(ans) out.write("YES\n");
	    	else out.write("NO\n");
	    }
	    out.flush();
	    out.close();
	}
	static boolean isSub(ArrayList<Integer>a, int[]b) {
		if(a.size()>b.length)return false;
		int ind = 0, lastCheck = -1;
		for(int i=0; i<b.length; i++) {
			if(ind>=a.size())continue;
			if(b[i] == a.get(ind)) {
				ind++;
				if(!check(b, lastCheck+1, i)) return false;
				lastCheck = i;
			}
		}
		if(!check(b, lastCheck+1, b.length)) return false;
		return ind==a.size();
	}
	static boolean check(int[] arr, int l, int r) {
		if(l>=r)return true;
		if((r-l)%2==1)return false;
		int second = -1;
		for(int i=l+1; i<r; i++) {
			if(i<0||i>=arr.length) return false;
			if(arr[i] == arr[l]) {
				second = i;
				break;
			}
		}
		if(second<0)return false;
		return check(arr, l+1, second)&& check(arr, second+1, r);
	}
}
