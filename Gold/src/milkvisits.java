import java.io.*;
import java.util.*;
public class milkvisits {
	static ArrayList<Integer>[] edges;
	static Stack<int[]> stack;
	static Stack<int[]>[] types;
	static boolean[] visit;
	static ArrayList<Integer>[] que; 
	static int[][] store;
	static int[] pre;
	static int[] post;
	static int[] ans;
	static int count;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
		BufferedReader br=new BufferedReader(new FileReader("milkvisits.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		edges = new ArrayList[N];
		for(int i=0; i<N; i++)
			edges[i] = new ArrayList<>();
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)arr[i] = Integer.parseInt(st.nextToken());
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			edges[a].add(b);
			edges[b].add(a);
		}
		pre = new int[N];
		post = new int[N];
		visit = new boolean[N];
		count = 0;
		preorder(0);
		count = 0;
		Arrays.fill(visit, false);
		postorder(0);
		stack = new Stack<>();
		types = new Stack[N];
		for(int i=0; i<N; i++)types[i] = new Stack<>();
		Arrays.fill(visit, false);
		que = new ArrayList[N];
		store = new int[N][3];
		for(int i=0; i<N; i++)que[i] = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			que[a].add(i);
			que[b].add(i);
			store[i][0] = a;
			store[i][1] = b;
			store[i][2] = c;
		}
	}
	static void dfs(int node) {
		visit[node] = true;
		ArrayList<Integer> al = edges[node];
		int s = al.size();
		for(int i=0; i<s; i++) {
			int tar = al.get(i);
			
		}
	}
	static void preorder(int node) {
		visit[node] = true;
		pre[node] = count++;
		ArrayList<Integer> al = edges[node];
		int s = al.size();
		for(int i=0; i<s; i++) {
			int tar = al.size();
			if(!visit[tar])
				preorder(tar);
		}
	}
	static void postorder(int node) {
		visit[node] = true;
		ArrayList<Integer> al = edges[node];
		int s = al.size();
		for(int i=0; i<s; i++) {
			int tar = al.size();
			if(!visit[tar])
				preorder(tar);
		}
		post[node] = count++;
	}
}
