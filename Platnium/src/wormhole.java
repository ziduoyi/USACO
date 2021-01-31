import java.io.*;
import java.util.*;
public class wormhole {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int F = Integer.parseInt(br.readLine());
		for(int i=0; i<F; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			@SuppressWarnings("unchecked")
			ArrayList<Integer>[] edges = new ArrayList[N];
			for(int j=0; j<N; j++)
				edges[j] = new ArrayList<>();
			for(int j =0; j<M; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				int c = Integer.parseInt(st.nextToken());
				edges[a].add(b);
				edges[a].add(c);
				edges[b].add(a);
				edges[b].add(c);
			}
			for(int j =0; j<W; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				int c = Integer.parseInt(st.nextToken());
				edges[a].add(b);
				edges[a].add(c*-1);
			}
			LinkedList<Integer> list = new LinkedList<>();
			list.add(0);
			int[] dist = new int[N];
			Arrays.fill(dist, 1000000000);
			dist[0]=0;
			boolean possible = false;
			while(!list.isEmpty()) {
				int node = list.removeFirst();
				ArrayList<Integer> al = edges[node];
				int s = al.size();
				for(int j=0; j<s; j+=2) {
					int tar = al.get(j);
					int cost = al.get(j+1);
					if(dist[node] + cost < dist[tar]) {
						if(tar==0) {
							possible = true;
							break;
						}
						dist[tar] = dist[node] + cost;
						list.add(tar);
					}
				}
				if(possible)
					break;
			}
			if(possible)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}

}
