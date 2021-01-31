import java.io.*;
import java.util.*;
public class delivery {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] store = new int[5*N][2];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			store[i][0] = Integer.parseInt(st.nextToken());
			store[i][1] = Integer.parseInt(st.nextToken());
		}
		int[][] direct = new int[][] {{1,0}, {0,1}, {-1,0}, {0,-1}};
		for(int i=0; i<N; i++) {
			for(int j=0; j<4; j++) { 
				store[N+(i*4)+(j)][0] = store[i][0] + direct[j][0];
				store[N+(i*4)+(j)][1] = store[i][1] + direct[j][1];
				boolean b = false;
				for(int k=0; k<N; k++) {
					if(store[N+(i*4)+(j)][0] == store[k][0]&&store[N+(i*4)+(j)][1] == store[k][1]) {
						b = true;
						break;
					}
				}
				if(b) {
					store[N+(i*4)+(j)][0] = 100000000;
					store[N+(i*4)+(j)][1] = 100000000;
				}
			}
		}
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] edges = new ArrayList[5*N];
		for(int i=0; i<5*N; i++)
			edges[i] = new ArrayList<>();
		int ans = 0;
		for(int i=0; i<5*N; i++) {
			for(int j=i+1; j<5*N; j++) {
				boolean a = false;
				boolean b = false;
				for(int k=0; k<N; k++) {
					if(i==k||j==k)continue;
					if(store[i][0] == store[k][0] || store[j][1] == store[k][1])
						if((long)(store[k][0]-store[j][0])*(store[k][0] - store[i][0])<0 || (long)(store[k][1]-store[j][1])*(store[k][1] - store[i][1])<0)
							a = true;
					
					if(store[i][1] == store[k][1] || store[j][0] == store[k][0]) 
						if((long)(store[k][0]-store[j][0])*(store[k][0] - store[i][0])<0 || (long)(store[k][1]-store[j][1])*(store[k][1] - store[i][1])<0)
							b = true;
					if(a&&b)
						break;
				}
				if(a==false||b==false) {
					edges[i].add(j);
					edges[j].add(i);
				}
			}
		}
		for(int i=0; i<N; i++) {
			int end = i+1;
			if(end==N)
				end = 0;
			int[] dist = new int[5*N];
			Arrays.fill(dist, 1000000000);
			dist[i] = 0;
			LinkedList<Integer> list = new LinkedList<>();
			list.add(i);
			while(!list.isEmpty()) {
				int node =list.removeFirst();
				ArrayList<Integer> al = edges[node];
				int s = al.size();
				for(int j=0; j<s; j++) {
					int tar = al.get(j);
					if(tar<N&&tar!=end)continue;
					int cost = Math.abs(store[tar][0] - store[node][0]) + Math.abs(store[tar][1] - store[node][1]);
					if(dist[node] + cost < dist[tar]) {
						dist[tar] = dist[node] + cost;
						list.add(tar);
					}
				}
			}
			ans += dist[end];
			if(dist[end]==1000000000) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(ans);
	}

}
