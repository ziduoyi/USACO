import java.io.*;
import java.util.*;
public class trapezoid {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] read = new int[N][4];
		HashSet<Integer> set = new HashSet<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			read[i][0] = Integer.parseInt(st.nextToken());
			read[i][1] = Integer.parseInt(st.nextToken());
			read[i][2] = Integer.parseInt(st.nextToken());
			read[i][3] = Integer.parseInt(st.nextToken());
			set.add(read[i][0]);
			set.add(read[i][1]);
			set.add(read[i][2]);
			set.add(read[i][3]);
		}
		HashMap<Integer, Integer> conv = new HashMap<>();
		int[][] arr = new int[N][4];
		int p = 0;
		for(int num: set)
			conv.put(p++, num);
		for(int i=0; i<N; i++) 
			for(int j=0; j<4; j++)
				arr[i][j] = conv.get(read[i][j]);
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return Math.min(o1[0], o1[2])-Math.min(o2[0], o2[2]);
			}
		});
		LinkedList<int[]> list = new LinkedList<>();
		for(int i=0; i<N; i++)
			for(int j=0; j<4; j++)
				list.add(new int[] {arr[i][j],i,j});
		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[0]-o2[0];
			}
		});
		int[] dp = new int[N];
		while(!list.isEmpty()) {
			int[] use = list.removeFirst();
		}
	}

}
