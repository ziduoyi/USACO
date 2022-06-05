import java.io.*;
import java.util.*;
public class cowtree {
	static ArrayList<Integer>[] edges;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("cowtree.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtree.out")));
		int N = Integer.parseInt(br.readLine());
		int[] weights = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
			weights[i] = Integer.parseInt(st.nextToken());
		int min =Integer.MAX_VALUE;
		edges = new ArrayList[N];
		for(int i=0; i<N; i++)
			edges[i] = new ArrayList<>();
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			edges[a].add(b);
			edges[b].add(a);
		}
		for(int i=0; i<N; i++) {
			ArrayList<Integer> al = edges[i];
			if(al.size()!=2)
				continue;
			boolean[] b = new boolean[N];
			b[i]=true;
			int s1 =count(al.get(0), b, weights);
			boolean[] c = new boolean[N];
			c[i]=true;
			int s2 =count(al.get(1), c, weights);
			min = Math.min(min, Math.abs(s1-s2));
		}
		out.println(min);
		out.close();
		
	}
	static int count(int curr, boolean[] visit, int[] weights) {
		visit[curr] =true;
		ArrayList<Integer> al = edges[curr];
		int s = al.size();
		int tol=weights[curr];
		for(int i=0; i<s; i++) {
			int tar = al.get(i);
			if(visit[tar]!=true) {
				visit[tar] = true;
				tol+=count(tar, visit,  weights);
			}
		}
		return tol;
	}
}
