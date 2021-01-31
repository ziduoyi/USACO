import java.io.*;
import java.util.*;
public class vacationgold {

	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int[][] dist1 = new int[N][K];
		int[][] dist2 = new int[N][K];
		ArrayList<Integer>[] edges1 = new ArrayList[N];
		ArrayList<Integer>[] edges2 = new ArrayList[N];
		for(int i=0; i<N; i++) {
			edges1[i] = new ArrayList<>();
			edges2[i] = new ArrayList<>();
			Arrays.fill(dist1[i], 1000000000);
			Arrays.fill(dist2[i], 1000000000);
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			edges1[a].add(b);
			edges1[a].add(c);
			edges2[b].add(a);
			edges2[b].add(c);
		}
		HashMap<Integer, Integer> map = new HashMap<>();
		HashMap<Integer, Integer> back = new HashMap<>();
		boolean[] check = new boolean[N];
		for(int i=0; i<K; i++) {
			int a = Integer.parseInt(br.readLine())-1;
			map.put(a, i);
			back.put(i, a);
			dist1[a][i] = 0;
			dist2[a][i] = 0;
			check[a] = true;
		}
		for(int i=0; i<K; i++) {
			LinkedList<Integer> list = new LinkedList<>();
			list.add(back.get(i));
			while(!list.isEmpty()) {
				int node = list.removeFirst();
				ArrayList<Integer> al = edges1[node];
				int s = al.size();
				for(int j=0; j<s; j+=2) {
					int tar = al.get(j);
					int cost = al.get(j+1);
					if(dist1[node][i] + cost <dist1[tar][i]) {
						dist1[tar][i] = dist1[node][i] + cost;
						list.add(tar);
					}
				}
			}
		}
		for(int i=0; i<K; i++) {
			LinkedList<Integer> list = new LinkedList<>();
			list.add(back.get(i));
			while(!list.isEmpty()) {
				int node = list.removeFirst();
				ArrayList<Integer> al = edges2[node];
				int s = al.size();
				for(int j=0; j<s; j+=2) {
					int tar = al.get(j);
					int cost = al.get(j+1);
					if(dist2[node][i] + cost <dist2[tar][i]) {
						dist2[tar][i] = dist2[node][i] + cost;
						list.add(tar);
					}
				}
			}
		}
		int count = 0;
		long sum = 0;
		for(int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			if(check[a]==false&& check[b]==false) {
				int min = 1000000000;
				for(int j=0; j<K; j++) 
					min = Math.min(min, dist2[a][j] + dist1[b][j]);
				if(min!=1000000000) {
					count++;
					sum+=min;
				}
			}
			else {
				if(check[a]==false) {
					if(dist2[a][map.get(b)]!=1000000000) {
						count++;
						sum += dist2[a][map.get(b)];
					}
				}
				else {
					if(dist1[b][map.get(a)]!=1000000000) {
						count++;
						sum += dist1[b][map.get(a)];
					}
				}
			}
		}
		System.out.println(count);
		System.out.println(sum);
	}

}
