import java.io.*;
import java.util.*;
public class occurrences {
	static ArrayList<Integer>[] edges;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int n = Integer.parseInt(br.readLine());
	    edges = new ArrayList[n];
	    for(int i=0; i<n; i++) edges[i] = new ArrayList<>();
	    Set<Integer> bank = new HashSet<>();
	    for(int i=0; i<n-1; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int a = Integer.parseInt(st.nextToken())-1;
	    	int b = Integer.parseInt(st.nextToken())-1;
	    	int c = Integer.parseInt(st.nextToken())-1;
	    	edges[a].add(b);
	    	edges[a].add(c);
	    	edges[b].add(a);
	    	edges[b].add(c);
	    	bank.add(c);
	    }
	}
	
}
