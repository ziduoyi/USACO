import java.io.*;
import java.util.*;
public class soda {
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<int[]> que = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[0] - o2[0];
			}
		});
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			que.add(new int[] {Integer.parseInt(st.nextToken()), 1});
			que.add(new int[] {Integer.parseInt(st.nextToken())+1, -1});
		}
		int max =0;
		int curr=0;
		while(!que.isEmpty()) {
			int[] use = que.poll();
			curr+=use[1];
			if(que.size()==0) {
				max = Math.max(max, curr);
				break;
			}
			while(que.peek()[0]==use[0]) {
				curr+=que.poll()[1];
				if(que.size()==0) {
					max = Math.max(max, curr);
					break;
				}
			}
			max = Math.max(max, curr);
		}
		System.out.println(max);
	}
}
