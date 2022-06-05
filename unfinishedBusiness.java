import java.io.*;
import java.util.*;
public class unfinishedBusiness {
	static boolean[] need;
	static Stack<Integer> stack;
	static ArrayList<Integer>[] edges;
	static int ans;
	static int y;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	br.readLine();
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int k = Integer.parseInt(st.nextToken());
	    	st = new StringTokenizer(br.readLine());
	    	int x = Integer.parseInt(st.nextToken())-1;
	    	y = Integer.parseInt(st.nextToken())-1;
	    	edges = new ArrayList[n];
	    	for(int j=0; j<n; j++)edges[j] = new ArrayList<>();
	    	need=new boolean[n];
	    	st = new StringTokenizer(br.readLine());
	    	for(int j=0; j<k; j++) 
	    		need[Integer.parseInt(st.nextToken())-1]=true;
	    	need[x]=true;
	    	need[y] = true;
	    	for(int j=0; j<n-1; j++) {
	    		st = new StringTokenizer(br.readLine());
	    		int a = Integer.parseInt(st.nextToken())-1;
	    		int b = Integer.parseInt(st.nextToken())-1;
	    		edges[a].add(b);
	    		edges[b].add(a);
	    	}
	    	stack = new Stack<>();
	    	stack.push(x);
	    	ans = 0;
	    	dfs(x, -1,0);
	    	int sum = 0;
	    	for(int j=0; j<n; j++) {
	    		if(!need[j])edges[j].clear();
	    		for(int num: edges[j]) {
	    			if(need[num])sum++;
	    			
	    		}
	    	}
	    	out.write((sum-ans)+"\n");
	    }
	    out.flush();
	    out.close();
	}
	static void dfs(int node, int last, int depth) {
		if(need[node]) {
			while(!stack.isEmpty()) {
				int num = stack.pop();
				need[num] = true;
			}
		}
		if(node==y) ans = depth;
		ArrayList<Integer> al = edges[node];
		int s = al.size();
		for(int i=0; i<s; i++) {
			if(al.get(i)!=last) {
				stack.push(al.get(i));
				dfs(al.get(i),node, depth+1);
				if(!stack.isEmpty()&&stack.peek()==al.get(i))
					stack.pop();
			}
		}
	}
}
