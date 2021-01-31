import java.io.*;
import java.util.*;
public class milkvisits {
	/*
	static ArrayList<int[]> ending;
	static ArrayList<int[]> needs;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("milkvisits.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] edges = new ArrayList[N];
		for(int i=0; i<N; i++)
			edges[i] = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			int a =Integer.parseInt(st.nextToken())-1;
			arr[i] =a;
		}
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			edges[a].add(b);
			edges[b].add(a);
		}
		ending = new ArrayList<>();
		needs = new ArrayList<>();
		int[][] que = new int[2*M][4];
		int[] starts = new int[2*M];
		for(int i=0; i<2*M; i+=2) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken());
			que[i][0] = a;
			que[i][1] = b;
			que[i][2] = c;
			que[i][3] = i;
			que[i+1][0] =b;
			que[i+1][1] =a;
			que[i+1][2] =c;
			que[i+1][3] =i;
			starts[i] =a;
			starts[i+1] =b;
		}
		Arrays.sort(que, (a,b) -> a[0]-b[0]);
		Arrays.sort(starts);
		
	}
	static void calAns(ArrayList<Integer>[] edges, int curr, boolean[] visited, int[] starts, int[][] que) {
		visited[curr] =true;
		ArrayList<Integer> al = edges[curr];
		int n =al.size();
		for(int i=0; i<n; i++) {
			calAns(edges, al.get(i), visited, starts, que);
		}
	}
	static int binarySearh(int find, ArrayList<int[]> al) {
		int l=0;
		int r = al.size();
		while(l<=r) {
			int m=l+(r-l)/2;
			int[] a = al.get(m);
			int b = a[0];
			if(b==find)
				return m;
			else if(b>find)
				r=m-1;
			else
				l=m+1;
		}
		return -1*l -1;
	}*/
	
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("milkvisits.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashSet<Integer>[] edges = new HashSet[N+1];
		ArrayList<Integer>[] qlist = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			edges[i] = new HashSet<>();
			qlist[i] = new ArrayList<>();
		}
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N+1];
		for(int i=1; i<=N; i++) {
			int a =Integer.parseInt(st.nextToken());
			arr[i] =a;
		}
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges[a].add(b);
			edges[b].add(a);
		}
		int[][] que = new int[M+1][3];
		boolean[] ans = new boolean[M+1];
		boolean[] vis = new boolean[N+1];

		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			que[i][0] = a;
			que[i][1] = b;
			que[i][2] = c;
			qlist[a].add(i);
			//qlist[b].add(i);
		}
		
		for(int i=1;i<N;i++) {
			int x = i;
			while(!vis[x] && edges[x].size()==1) {
				int y=0;
				for(int z:edges[x])
					y=z;
				for(int q : qlist[x]) {
					if(arr[x] == que[q][2]) {
						ans[q] = true;
					}else if(x!=que[q][1]){
						qlist[y].add(q);
					}
				}
				qlist[x]=null;
				edges[x]=null;
				vis[x]=true;
				edges[y].remove(x);
				x=y;
			}
		}
		StringBuilder res = new StringBuilder();
		for(int i=1;i<=M;i++) {
			if(ans[i])
				res.append("1");
			else
				res.append("0");
		}
		out.println(res);
		out.close();
	}
	
}
