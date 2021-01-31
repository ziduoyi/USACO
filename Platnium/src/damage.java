import java.io.*;
import java.util.*;
public class damage {
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int P = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] edges = new ArrayList[P];
		for(int i=0; i<P; i++)
			edges[i] = new ArrayList<>();
		for(int i=0; i<C; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			edges[a].add(b);
			edges[b].add(a);
		}
		boolean[] isblock = new boolean[P];
		for(int i=0; i<N; i++) {
			int x = Integer.parseInt(br.readLine())-1;
			ArrayList<Integer> al = edges[x];
			int s = al.size();
			for(int j=0; j<s; j++)
				isblock[al.get(j)] = true;
		}
		boolean[] visit = new boolean[P];
		LinkedList<Integer> list = new LinkedList<>();
		visit[0] = true;
		list.add(0);
		int ans =1;
		while(!list.isEmpty()) {
			int node= list.removeFirst();
			ArrayList<Integer> al = edges[node];
			int s = al.size();
			for(int i=0; i<s; i++) {
				int tar = al.get(i);
				if(visit[tar]==false&&isblock[tar]==false) {
					visit[tar] = true;
					list.add(tar);
					ans++;
				}
			}
		
		}
		System.out.println(P-ans);
	}

}
