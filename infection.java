import java.io.*;
import java.util.*;
public class infection {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	ArrayList<Integer>[] edges = new ArrayList[n];
	    	for(int j=0; j<n; j++)edges[j] = new ArrayList<>();
	    	for(int j=1; j<n; j++) edges[Integer.parseInt(st.nextToken())-1].add(j);
	    	LinkedList<Integer> list = new LinkedList<>();
	    	list.add(0);
	    	LinkedList<Integer> joyce = new LinkedList<>();
	    	joyce.add(1);
	    	while(!list.isEmpty()) {
	    		int node = list.removeFirst();
	    		ArrayList<Integer> al = edges[node];
	    		int s = al.size();
	    		if(s>0)
	    			joyce.add(s);
	    		for(int j=0; j<s; j++)list.add(al.get(j));
	    		
	    	}
	    	Collections.sort(joyce);
	    	int ans = joyce.size();
	    	int sub = joyce.size();
	    	for(int j=0; j<sub; j++) {
	    		int num = joyce.removeLast();
	    		num-=(sub-j);
	    		if(num>0)joyce.addFirst(num);
	    	}
	    	Collections.sort(joyce);
	    	ans += joyce.size();
	    	int s = joyce.size();
	    	for(int j=0; j<s; j++) {
	    		int num = joyce.removeFirst()-1;
	    		if(num>0)joyce.add(num);
	    	}
	    	while(!joyce.isEmpty()) {
	    		s = joyce.size();
	    		int thing = joyce.removeLast()-1;
	    		if(thing>0) {
	    			thing--;
	    			ans++;
	    			if(thing>0)joyce.addFirst(thing);
	    		}
	    		for(int j=0; j<s-1; j++) {
	    			int num = joyce.removeLast()-1;
	    			if(j==0)num--;
	    			if(num>0)joyce.addFirst(num);
	    		}
	    		Collections.sort(joyce);
	    	}
	    	out.write(ans+"\n");
	    }
	    out.flush();
	}

}
