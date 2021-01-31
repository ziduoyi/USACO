import java.io.*;
import java.util.*;
public class boards {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("boards.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("boards.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		LinkedList<int[]> list = new LinkedList<>();
		int[][] arr = new int[P][4];
		for(int i=0; i<P; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			arr[i][0] = a;
			arr[i][1] = b;
			arr[i][2] = c;
			arr[i][3] = d;
			list.add(new int[] {a,b,0,i});
			list.add(new int[] {c,d,1,i});
		}
		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if(o1[0]!=o2[0])
					return o1[0]-o2[0];
				else
					return o1[1]-o2[1];
			}
		});
		int[] ans = new int[P];
		Arrays.fill(ans, 2100000000);
		TreeSet<Integer> values = new TreeSet<>();
		Map<Integer, Integer> map = new HashMap<>();
		values.add(0);
		map.put(0, 0);
		while(!list.isEmpty()) {
			int[] curr = list.removeFirst();
			int use = values.floor(curr[1]);
			ans[curr[3]] = Math.min(map.get(use) +curr[0] + curr[1], ans[curr[3]]);
			if(map.get(values.floor(curr[1]))>ans[curr[3]] - curr[0] - curr[1]) {
				while(values.ceiling(curr[1])!=null&&map.get(values.ceiling(curr[1]))>ans[curr[3]]-curr[0]-curr[1]) {
					map.remove(values.ceiling(curr[1]));
					values.remove(values.ceiling(curr[1]));
				}
				values.add(curr[1]);
				map.put(curr[1],ans[curr[3]] - curr[0] - curr[1]);
			}
		}
		int fin = 2100000000;
		for(int i=0; i<P; i++) 
			fin = Math.min(fin, ans[i]+N+N-arr[i][2]-arr[i][3]);
		out.println(fin);
		out.close();
	}

}
