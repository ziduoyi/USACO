import java.io.*;
import java.util.*;
public class test {
	static ArrayList<Integer>[] edges;
	static long[] ans;
	static int[] depth;
	static int max;
	static int N;
	static int cnt;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    N = Integer.parseInt(br.readLine());
	    edges = new ArrayList[N+1];
	    depth = new int[N+1];
	    for(int i=0; i<N+1; i++) edges[i] = new ArrayList<>();
	    int[] arr = new int[N];
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    for(int i=0; i<N; i++) {
	    	arr[i] = Integer.parseInt(st.nextToken());
	    	edges[arr[i]].add(i);
	    }
	    max = 0;
	    cnt = N+1;
	    dfs(N, -1);
	    for(int i=0; i<=N; i++) depth[i] = max - depth[i];
	    ans = new long[N+1];
	    solve(N, -1);
	    out.write(N+"\n");
	    for(int i=0; i<N; i++) out.write(ans[i]+"\n");
	    out.flush();
	    out.close();
	}
	static void solve(int curr, int par) {
		ans[curr] = (long)depth[curr]*(N+1)+cnt--;
		ArrayList<Integer> al = edges[curr];
		int s = al.size();
		for(int i=s-1; i>-1; i--) {
			int next = al.get(i);
			if(next!=par) 
				solve(next, curr);
		}
	}
	static void dfs(int curr, int par) {
		ArrayList<Integer> al = edges[curr];
		int s = al.size();
		for(int i=0; i<s; i++) {
			int next = al.get(i);
			if(next!=par) {
				depth[next] = depth[curr]+1;
				max = Math.max(depth[next], max);
				dfs(next, curr);
			}
		}
	}
}
