import java.util.*;
import java.io.*;
public class fcolor {
	static int[] root;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		long t1 = System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new FileReader("fcolor.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fcolor.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashSet<Integer>[] edges = new HashSet[N];
		for(int i=0; i<N; i++) 
			edges[i] = new HashSet<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			edges[a].add(b);
		}
		root = new int[N];
		for(int i=0; i<N; i++)
			root[i] = i;
		LinkedList<Integer> list = new LinkedList<>();
		for(int i=0; i<N; i++) {
			if(edges[i].size()>1)
				list.add(i);
		}
		while(!list.isEmpty()) {
			int node = list.removeFirst();
			if(edges[node].size()<=1)
				continue;
			HashSet<Integer> set = edges[node];
			HashSet<Integer> curr = new HashSet<>();
			int r1 = -1;
			for(int r: set) 
				curr.add(getRoot(r));
			set.clear();
			for(int r2: curr) {
				if(r1 ==-1) {
					r1 = r2;
					set.add(r1);
					continue;
				}
				if(edges[r1].size()>edges[r2].size()) {
					edges[r1].addAll(edges[r2]);
					edges[r2].clear();
					root[r2] = r1;
				}
				else {
					edges[r2].addAll(edges[r1]);
					edges[r1].clear();
					root[r1] = r2;
					r1 = r2;
				}
			}
			if(edges[node].size()>1)
				list.add(node);
			if(edges[r1].size()>1)
				list.add(r1);
		}
		Map<Integer, Integer> map = new HashMap<>();
		int a = 1;
		for(int i=0; i<N; i++) {
			int curr = getRoot(i);
			if(!map.containsKey(curr)) 
				map.put(curr, a++);
			out.println(map.get(curr));
		}
		out.close();
	}
	static int getRoot(int pos) {
		if(root[pos]!=pos) 
			root[pos]=getRoot(root[pos]);
		return root[pos];
	}

}
