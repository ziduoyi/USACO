import java.io.*;
import java.util.*;
public class lilypad {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] arr = new int[M][N];
		int[] start = new int[2];
		int[] end = new int[2];
		int a = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] ==3) {
					start[0] = i;
					start[1] = j;
				}
				if(arr[i][j] ==4) {
					end[0] = i;
					end[1] = j;
				}
				if(arr[i][j] == 1||arr[i][j] ==3) 
					map.put(100*i+j, a++);
			}
		}
		int[][] direct = new int[][] {{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}};
		int[][] time = new int[M][N];
		for(int i=0; i<M; i++) 
			Arrays.fill(time[i], 10000000);
		time[start[0]][start[1]] = 0;
		int min =Integer.MAX_VALUE;
		int count =0;
		PriorityQueue<int[]> list = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return time[o1[0]][o1[1]]-time[o2[0]][o2[1]];
			}
		});
		list.add(new int[] {start[0], start[1], 0});
		Map<Integer, HashSet<Integer>> store = new HashMap<>();
		HashSet<Integer> z = new HashSet<>();
		z.add(map.get(start[0]*100+start[1]));
		store.put(0, z);
		int b =1;
		while(!list.isEmpty()) {
			int[] node = list.poll();
			for(int i=0; i<8; i++) {
				int x = node[0] + direct[i][0];
				int y = node[1] + direct[i][1];
				if(x<0||x>=M||y<0||y>=N)
					continue;
				if(arr[x][y]==3) 
					continue;
				if(arr[x][y] !=2) {
					int val = time[node[0]][node[1]];
					if(arr[x][y] == 0)
						val++;
					if(x == end[0] && y == end[1]) {
						if(val<=time[x][y]) {
							if(val<time[x][y]) {
								min = val;
								time[x][y] = val;
								count =1;
							}
							else
								count++;
						}
					}
					else {
						@SuppressWarnings("unchecked")
						HashSet<Integer> s = (HashSet<Integer>) store.get(node[2]).clone();
						if(arr[x][y]==1) {
							if(s.contains(map.get(100*x+y)))
								continue;
						}
						if(val<=time[x][y]) {
							if(arr[x][y]==1) 
								s.add(map.get(100*x+y));
							store.put(b,  s);
							list.add(new int[] {x,y,b++});
							time[x][y] = val;
						}
					}
				}
			}
				
		}
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		System.out.println(min);
		System.out.println(count);
	}

}
