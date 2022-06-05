import java.io.*;
import java.util.*;
public class dance {
	static int[] next;
	static int[] prev;
	static Set<Integer>[] edges1;
	static Set<Integer>[] edges2;
	static boolean[] visit;
	static long cycles;
	static int[] ans;
	static HashSet<Integer> set;
	static HashMap<Integer, Integer> map;
	static int org;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int N = Integer.parseInt(st.nextToken());
	    int K = Integer.parseInt(st.nextToken());
	    long M = Long.parseLong(st.nextToken());
	    cycles = M/K;
	    long rem = M%K;
	    next = new int[N];
	    prev = new int[N];
	    for(int i=0; i<N; i++)prev[i] = i;
	    edges1 = new HashSet[N];
	    edges2 = new HashSet[N];
	    for(int i=0; i<N; i++) {
	    	edges1[i]= new HashSet<>();
	    	edges2[i] = new HashSet<>();
	    }
	    ans = new int[N];
	    visit = new boolean[N];
	    for(int i=0; i<K; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	int a = Integer.parseInt(st.nextToken())-1;
	    	int b = Integer.parseInt(st.nextToken())-1;
	    	edges1[prev[a]].add(b);
	    	edges1[prev[b]].add(a);
	    	if(i<rem) {
	    		edges2[prev[a]].add(b);
	    		edges2[prev[b]].add(a);
	    	}
	    	int save = prev[a];
	    	prev[a] = prev[b];
	    	prev[b] = save;
	    }
	    for(int i=0; i<N; i++)next[prev[i]] = i;	
	    for(int i=0; i<N; i++) {
	    	if(!visit[i]) {
		    	set = new HashSet<>();
		    	map = new HashMap<>();
		    	set.add(i);
		    	map.put(i, 1);
		    	int tar = i;
		    	for(int j=0; j<cycles; j++) {
		    		if(tar==i&&j!=0) {
		    			tar = -1;
		    			break;
		    		}
		    		for(int num: edges1[tar]) {
		    			if(set.contains(num))
		    				map.put(num, map.get(num)+1);
		    			else {
		    				map.put(num, 1);
		    				set.add(num);
		    			}
		    		}
		    		tar = next[tar];
		    	}
		    	if(tar!=-1) {
		    		for(int num: edges2[tar]) {
		    			if(set.contains(num))
		    				map.put(num, map.get(num)+1);
		    			else {
		    				map.put(num, 1);
		    				set.add(num);
		    			}
		    		}
		    	}
		    	visit[i] = true;
		    	ans[i] = set.size();
		    	org = i;
		    	dfs(i,tar);
	    	}
	    }
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    for(int i=0; i<N; i++)
	    	out.write(ans[i]+"\n");
	    out.flush();
	    
	}
	static void dfs(int node,int end) {
		if(next[node]==org)return;
		visit[next[node]] = true;
		if(end ==-1) {
			ans[next[node]] = set.size();
			dfs(next[node],-1);
			return;
		}
		if(map.get(node)>1)
			map.put(node, map.get(node)-1);
		else {
			map.remove(node);
			set.remove(node);
		}
		if(set.contains(next[node]))
			map.put(next[node], map.get(next[node])+1);
		else {
			map.put(next[node], 1);
			set.add(next[node]);
		}
		if(cycles>0)
			for(int num: edges1[node]) {
				if(map.get(num)>1)
					map.put(num, map.get(num)-1);
				else {
					map.remove(num);
					set.remove(num);
				}
			}
		if(end!=-1) {
			for(int num: edges2[end]) {
				if(map.get(num)>1)
					map.put(num, map.get(num)-1);
				else {
					map.remove(num);
					set.remove(num);
				}
			}
    		for(int num: edges2[next[end]]) {
    			if(set.contains(num))
    				map.put(num, map.get(num)+1);
    			else {
    				map.put(num, 1);
    				set.add(num);
    			}
    		}
    		if(cycles>0)
	    		for(int num: edges1[end]) {
	    			if(set.contains(num))
	    				map.put(num, map.get(num)+1);
	    			else {
	    				map.put(num, 1);
	    				set.add(num);
	    			}
	    		}
		}
		ans[next[node]] = set.size();
		dfs(next[node],next[end]);
	}
}