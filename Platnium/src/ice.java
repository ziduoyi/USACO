import java.io.*;
import java.util.*;
public class ice {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] start = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		int[] end = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		int[][] dist = new int[N][4];
		for(int i=0; i<N; i++)
			Arrays.fill(dist[i], 1000000);
		int[][] direct = new int[][] {{1,0},{0,1}, {-1,0}, {0,-1}};
		Map<Integer, Integer> map  = new HashMap<>();
		map.put(0, 2);
		map.put(1, 3);
		map.put(3, 1);
		map.put(2, 0);
		TreeSet<Integer> setx = new TreeSet<>();
		TreeSet<Integer> sety = new TreeSet<>();
		HashMap<Integer, Integer> convx = new HashMap<>();
		HashMap<Integer, Integer> convy = new HashMap<>();
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] edgesx = new ArrayList[N+1];
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] edgesy = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			edgesx[i] = new ArrayList<>();
			edgesy[i] = new ArrayList<>();
		}
		int[][] store = new int[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			setx.add(a);
			sety.add(b);
			store[i][0] = a;
			store[i][1] = b;
		}
		int a =0;
		for(int num: setx) 
			convx.put(num, a++);
		a = 0;
		for(int num: sety)
			convy.put(num, a++);
		HashMap<Long, Integer> convc = new HashMap<>();
		for(int i=0; i<N; i++) {
			long hash = (long)(2000000001) * (store[i][0]) + store[i][1];
			convc.put(hash, i);
			edgesx[convx.get(store[i][0])].add(store[i][1]);
			edgesy[convy.get(store[i][1])].add(store[i][0]);
		}
		LinkedList<Integer> list = new LinkedList<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<4; j++) {
				int x = store[i][0] + direct[j][0];
				int y = store[i][1] + direct[j][1];
				if(x==start[0] && y == start[1]) {
					list.add(i);
					list.add(j);
					dist[i][j] = 0;
				}
			}
		}
		for(int i=0; i<N; i++) {
			Collections.sort(edgesx[i]);
			Collections.sort(edgesy[i]);
		}
		while(!list.isEmpty()) {
			int node = list.removeFirst();
			int face = list.removeFirst();
			int x = store[node][0] + direct[face][0];
			int y = store[node][1] + direct[face][1];
			int p1 = N;
			int p2 = N;
			if(convx.get(x)!=null)
				p1 = convx.get(x);
			if(convy.get(y)!=null)
				p2 = convy.get(y);
			ArrayList<Integer> alx = edgesy[p2];
			ArrayList<Integer> aly = edgesx[p1];
			int pos1 = Collections.binarySearch(alx, x);
			int pos2 = Collections.binarySearch(aly, y);
			if(pos1<0) {
				pos1++;
				pos1*=-1;
			}
			
			if(pos2<0) {
				pos2++;
				pos2*=-1;
			}
			
			if(pos1-1>=0&&face!=0) {
				long hash = (long)(2000000001) * (alx.get(pos1-1)) + y; 
				if(dist[node][face] + 1 <dist[convc.get(hash)][0]) {
					dist[convc.get(hash)][0] = dist[node][face] +1;
					list.add(convc.get(hash));
					list.add(0);
				}
			}
			if(pos1<alx.size()&&pos1>=0&&face!=2) {
				long hash = (long)(2000000001) * (alx.get(pos1)) + y; 
				if(dist[node][face] + 1 <dist[convc.get(hash)][2]) {
					dist[convc.get(hash)][2] = dist[node][face] +1;
					list.add(convc.get(hash));
					list.add(2);
				}
			}
			if(pos2-1>=0&&face!=1) {
				long hash = (long)(2000000001) * x + aly.get(pos2-1); 
				if(dist[node][face] + 1 <dist[convc.get(hash)][1]) {
					dist[convc.get(hash)][1] = dist[node][face] +1;
					list.add(convc.get(hash));
					list.add(1);
				}
			}
			if(pos2<aly.size()&&pos2>=0&&face!=3) {
				long hash = (long)(2000000001) * x + aly.get(pos2); 
				if(dist[node][face] + 1 <dist[convc.get(hash)][3]) {
					dist[convc.get(hash)][3] = dist[node][face] +1;
					list.add(convc.get(hash));
					list.add(3);
				}
			}
		}
		int ans = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			for(int j=0; j<4; j++) {
				int x = store[i][0] + direct[j][0];
				int y = store[i][1] + direct[j][1];
				if(x==end[0] && y == end[1]) 
					ans = Math.min(ans, dist[i][j]);
			}
		}
		System.out.println(ans);
 	}
}
