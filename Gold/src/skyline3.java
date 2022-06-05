import java.io.*;
import java.util.*;
public class skyline3 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("skyline3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skyline3.out")));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<int[]> que = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if(o1[0]!=o2[0])
					return o1[0]-o2[0];
				return o1[2]-o2[2];
			}
		});
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			que.add(new int[] {a, c, 0});
			que.add(new int[] {b, c, 1});
		}
		TreeSet<Integer> set = new TreeSet<>();
		set.add(0);
		Map<Integer, Integer> map = new HashMap<>();
		int ans = 0;
		int last = 0;
		while(!que.isEmpty()) {
			int node = que.peek()[0];
			int num = set.pollLast();
			if(num!=0&&map.get(num)<=1) 
				ans += (node-last)*(num-set.floor(num));
			set.add(num);
			while(!que.isEmpty()&&node==que.peek()[0]&&que.peek()[2]==0) {
				int[] arr = que.poll();
				if(set.contains(arr[1]))
					map.put(arr[1], map.get(arr[1])+1);
				else {
					set.add(arr[1]);
					map.put(arr[1], 1);
				}
			}
			while(!que.isEmpty()&&node==que.peek()[0]) {
				int[] arr = que.poll();
				if(map.get(arr[1])>1)
					map.put(arr[1], map.get(arr[1])-1);
				else {
					set.remove(arr[1]);
					map.remove(arr[1]);
				}
			}
			last = node;
		}
		out.println(ans);
		out.close();
	}

}
