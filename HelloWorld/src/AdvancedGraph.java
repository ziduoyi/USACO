import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class AdvancedGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	// ------------------------------------------------------------------Tarjan algorithm
	static ArrayList<Integer>[] edges;//default
	static Stack<Integer> stack;//default
	static int[] scc; //default
	static int[] in; // filled with -1
	static int[] low; // filled with -1
  	static boolean[] instack; //filled with false
	static int time; //start at 1
	static int num; //default
	static int N; //input
	
	static void countscc() {
		Set<Integer> set = new HashSet<>();
		for(int i=0; i<N; i++)
			set.add(scc[i]); //node i belongs in group scc[i]
		System.out.println(set.size());
	}
	static void tarjan() {
		for(int i=0; i<N; i++)
			if(in[i] == -1)
				dfs(i);
	}
	static void dfs(int node) {
		low[node] = in[node] = time++;
		stack.push(node);
		instack[node] = true;
		ArrayList<Integer> al = edges[node];
		int s = al.size();
		for(int i=0; i<s; i++) {
			int tar = al.get(i);
			if(in[tar] == -1) {
				dfs(tar);
				low[node] = Math.min(low[node], low[tar]);
			}
			else if(instack[tar]) 
				low[node] = Math.min(low[node], in[tar]);// Math.min(low[node], low[tar]) does not works	
		}
		if(low[node] == in[node]) {
			while(true) {
				int u = stack.pop();
				scc[u] = num;
				instack[u] = false;
				if(u==node)break;
			}
			num++;
		}
	}
	//---------------------------------------------------------------------------end of tarjan algorithm
	//variables cancelled out so that no errors would occur with code above
	
	//static ArrayList<Integer>[] edges;
	//static int[] in;
	//static int[] low;
	static boolean[] visit;
	//static int time;
	//static int num;
	//static int N;
	static void Articulation()throws IOException {
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
	//--------------------- check if a point is below, above, or in a linear line formed by two other points
	static boolean ccw(int x1, int y1, int x2, int y2, int x3, int y3) { // on line gives value 0, above gives -, below gives +
		if((long)((long)((x2-x1)*(y3-y1))-(long)((y2-y1)*(x3-x1)))>=0) {
			return true;
		}
		return false;
	}
}
