import java.io.*;
import java.util.*;
public class fence {
	static ArrayList<int[]>[] edges;
	static int[] store;
	static boolean[] visit;
	static int count;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		edges = new ArrayList[500];
		for(int i=0; i<500; i++) 
			edges[i] = new ArrayList<>();
		for(int i=0; i<N; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			edges[a].add(new int[] {b,i});
			edges[b].add(new int[] {a,i});
		}
		for(int i=0; i<500; i++)
			Collections.sort(edges[i], new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					return o1[0]-o2[0];
				}
				
			});
		int pos = 0;
		for(int i=0; i<500; i++) {
			if(edges[i].size()%2!=0) {
				pos = i;
				break;
			}
		}
		visit = new boolean[N];
		store = new int[N+1];
		count = N;
		eulerian(pos);
		boolean[] v = new boolean[N];
		boolean b = false;
		for(int i=0; i<N; i++) {
			boolean check = false;
			ArrayList<int[]> al = edges[store[i]];
			int s = al.size();
			for(int j=0; j<s; j++) {
				int tar = al.get(j)[0];
				if(v[al.get(j)[1]] ==true)
					continue;
				if(tar==store[i+1]) {
					v[al.get(j)[1]] = true;
					check = true;
					break;
				}
			}
			if(check==false) {
				b = true;
				break;
			}
		}
		if(b == false) {
			for(int j=0; j<N+1; j++)
				System.out.println(store[j]+1);
			return;
		}
	}
	static void eulerian(int node) {
		ArrayList<int[]> al = edges[node];
		int s = al.size();
		for(int i=0; i<s; i++) {
			int[] next = al.get(i);
			if(visit[next[1]] == true)continue;
			visit[next[1]] = true;
			eulerian(next[0]);
		}
		store[count--] = node;
	}

}
