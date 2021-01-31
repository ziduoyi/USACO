import java.io.*;
import java.util.*;
public class articulation {
	static ArrayList<Integer>[] edges;
	static int[] in;
	static int[] low;
	static boolean[] visit;
	static int time;
	static int num;
	static int N;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		edges = new ArrayList[N];
		for(int i=0; i<N; i++)
			edges[i] = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			edges[a].add(b);
			edges[a].add(i);
			edges[b].add(a);
			edges[b].add(i);
		}
		in = new int[N];
		Arrays.fill(in, -1);
		low = new int[N];
		Arrays.fill(low, -1);
		visit = new boolean[N];
		time = 1;
		num = 0;
		for(int i=0; i<N; i++) {
			if(visit[i]==false)
				dfs(i,-1);
		}
		System.out.println(num);
	}
	
	static void dfs(int node, int last) {// last = previous id
		low[node] = in[node] = time++;
		visit[node] = true;
		ArrayList<Integer> al = edges[node];
		int count = 0;
		boolean check = false;
		int s = al.size();
		for(int i=0; i<s; i+=2) {
			int tar = al.get(i);
			int curr = al.get(i+1);
			if(curr==last)
				continue;
			if(visit[tar] ==false) {
				count++;
				dfs(tar, curr);
				if(low[tar]>=in[node])
					check = true;
				low[node] = Math.min(low[node], low[tar]);
			}
			else
				low[node] = Math.min(low[node], in[tar]);
		}
		if(last==-1) 
			check = (count>1);
		if(check == true)
			num ++;
	}
}
