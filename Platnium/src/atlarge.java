import java.io.*;
import java.util.*;
public class atlarge {
	static ArrayList<Integer>[] edges;
	static int[] dist;
	static ArrayList<int[]>[] dp1;
	static ArrayList<int[]>[] dp2;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		edges = new ArrayList[N];
		for(int i=0; i<N; i++)
			edges[i] = new ArrayList<>();
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			edges[a].add(b);
			edges[b].add(a);
		}
		dist = new int[N];
		Arrays.fill(dist, N);
		LinkedList<Integer> list = new LinkedList<>();
		for(int i=0; i<N; i++) {
			if(edges[i].size()==1) {
				list.add(i);
				dist[i]=0;
			}
		}
		while(!list.isEmpty()) {
			int node = list.removeFirst();
			ArrayList<Integer> al = edges[node];
			int s = al.size();
			for(int i=0; i<s; i++) {
				int tar = al.get(i);
				if(dist[node]+1<dist[tar]) {
					dist[tar] = dist[node]+1;
					list.add(tar);
				}
			}
		}
		dp1 = new ArrayList[N];
		dp2 = new ArrayList[N];
		for(int i=0; i<N; i++) {
			dp1[i] = new ArrayList<>();
			dp2[i] = new ArrayList<>();
		}
		compute1(0, new boolean[N]);
	}
	static void compute1(int node, boolean[] visit) {
		visit[node] = true;
		if(dist[node]==0&&node!=0) {
			dp1[node].add(new int[] {0,1});
			return;
		}
		ArrayList<int[]> curr = new ArrayList<>();
		ArrayList<Integer> al = edges[node];
		int s = al.size();
		TreeSet<Integer> set = new TreeSet<>();
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<s; i++) {
			int tar = al.get(i);
			ArrayList<int[]> stuff = dp1[tar];
			int a = stuff.size();
			if(visit[tar] ==false) {
				compute1(tar, visit);
				for(int j=0; j<a; j++) {
					if(set.contains(stuff.get(j)[0])) 
						map.put(stuff.get(j)[0], map.get(stuff.get(j)[0])+stuff.get(j)[1]); 
					else {
						set.add(stuff.get(j)[0]);
						map.put(stuff.get(j)[0], stuff.get(j)[1]);
					}
				}
			}
		}
		int first = set.pollFirst();
		for(int num: set) {
			if(num>=dist[node]) {
				if(num!=first)
					curr.add(new int[] {num+curr.get(curr.size()-1)[0], 1});
				else
					curr.add(new int[] {num-first, 1});
				break;
			}
			if(num==first) 
				curr.add(new int[] {num-first, map.get(num)});
			else 
				curr.add(new int[] {num+curr.get(curr.size()-1)[0], map.get(num)+curr.get(curr.size()-1)[1]});	
		}
		if(dist[node]==0) {
			dp1[node].add(new int[] {0,1});
			return;
		}
		int last =-1;
		for(int i=0; i<curr.size(); i++) {
			if(curr.get(i)[0]-1==last)
				dp1[node].get(i)[1] +=curr.get(i)[1];
			else
				dp1[node].add(new int[] {curr.get(i)[0]-1, curr.get(i)[1]});
			last = curr.get(i)[0]-1;
		}
		
	}
	
	
	
	
	static void compute2(int node, boolean[] visit, int par) {
		visit[node] = true;
		if(dist[node]==0) {
			dp2[node].add(new int[] {0,1});
			if(node!=0)
				return;
		}
		ArrayList<int[]> curr = new ArrayList<>();
		TreeSet<Integer> set = new TreeSet<>();
		Map<Integer, Integer> map = new HashMap<>();
		if(par!=-1) {
			for(int i=0; i<dp2[par].size(); i++) {
				if(set.contains(Math.max(dp2[par].get(i)[0]-1,0))) 
					map.put(Math.max(dp2[par].get(i)[0]-1,0), map.get(Math.max(dp2[par].get(i)[0]-1,0))+dp2[par].get(i)[1]);
				else {
					set.add(Math.max(dp2[par].get(i)[0]-1,0));
					map.put(Math.max(dp2[par].get(i)[0]-1,0), dp2[par].get(i)[1]);
				}
			}
			for(int i=0; i<dp1[par].size(); i++) {
				if(set.contains(Math.max(dp1[par].get(i)[0]-1,0))) 
					map.put(Math.max(dp1[par].get(i)[0]-1,0), map.get(Math.max(dp1[par].get(i)[0]-1,0))+dp1[par].get(i)[1]);
				else {
					set.add(Math.max(dp1[par].get(i)[0]-1,0));
					map.put(Math.max(dp1[par].get(i)[0]-1,0), dp1[par].get(i)[1]);
				}
			}
			for(int i=0; i<dp1[node].size(); i++) {
				
			}
		}
		
	}

}
