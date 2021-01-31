import java.io.*;
import java.util.*;
public class cfair {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] times = new int[N];
		for(int i=0; i<N; i++)
			times[i] = Integer.parseInt(br.readLine());
		int[][] edges = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++)
				edges[i][j] = Integer.parseInt(br.readLine());
		}
		int[] max = new int[N];
		LinkedList<Integer> list = new LinkedList<>();
		for(int i=1; i<N; i++) {
			if(times[0] + edges[0][i] <= times[i]) 
				max[i] = 2;
			else
				max[i] =1;
			if(edges[0][i] <= times[i])
				list.add(i);
		}
		while(!list.isEmpty()) {
			int node = list.removeFirst();
			for(int i=0; i<N; i++) {
				if(node==i)continue;
				if(times[node] + edges[node][i]<= times[i]) {
					if(max[node] +1 > max[i]) {
						max[i] = max[node] + 1;
						list.add(i);
					}
				}
			}
		}
		int ans = 0;
		for(int i=0; i<N; i++)
			ans = Math.max(ans, max[i]);
		if(N==1) {
			System.out.println(1);
			return;
		}
		System.out.println(ans);
	}

}
