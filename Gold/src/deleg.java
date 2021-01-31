import java.io.*;
import java.util.*;
public class deleg {
	static ArrayList<Integer>[] edges;
	static ArrayList<Integer>[] paths;
	static int K;
	static int N;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("deleg.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("deleg.out")));
		N = Integer.parseInt(br.readLine());
		K = 2;
		edges = new ArrayList[N];
		for(int i=0; i<N; i++)
			edges[i] = new ArrayList<>();
		paths = new ArrayList[N];
		for(int i=0; i<N; i++)
			paths[i] = new ArrayList<>();
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			edges[a].add(b);
			edges[b].add(a);
		}
		count(0,-1);
		boolean[] ans = new boolean[N];
		ans[1] = true;
		for(int i=N-1; i>1; i--) {
			K = i;
			if((i*2<N&&ans[i*2])||(i*3<N&&ans[i*3])) {
				ans[i] = true;
				continue;
			}
			if((double)(N-1)/K==(N-1)/K) {
				if(recursion(0)!=-1)
					ans[i] = true;
				else
					ans[i] = false;
			}
			else
				ans[i] = false;
		}
		for(int i=1; i<N; i++) {
			if(ans[i])
				out.print(1);
			else
				out.print(0);
		}
		out.println();
		out.close();
	}
	static void count(int node, int par) {
		ArrayList<Integer> list = edges[node];
		int s = list.size();
		if(s==1&&par!=-1) {
			paths[par].add(node);
			return;
		}
		for(int i=0; i<s; i++) {
			int tar = list.get(i);
			if(tar!=par) 
				count(tar,node);
		}
		if(par!=-1)
			paths[par].add(node);
	}
	static int recursion(int node) {
		ArrayList<Integer> list = paths[node];
		int s = list.size();
		if(s==0)
			return 1;
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<s; i++) {
			int tar = list.get(i);
			int curr = recursion(tar);
			if(curr==-1)
				return -1;
			if(curr!=0) {
				if(map.containsKey(K-curr)) {
					if(map.get(K-curr)==1) 
						map.remove(K-curr);
					else
						map.put(K-curr, map.get(K-curr)-1);
				}
				else {
					if(map.containsKey(curr))
						map.put(curr, map.get(curr)+1);
					else
						map.put(curr, 1);
				}
			}
		}
		if(map.size()>1)
			return -1;
		for(int num: map.values()) {
			if(num>1)
				return -1;
		}
		for(int num: map.keySet()) {
			return (num+1)%K;
		}
		return 1;
	}
}
