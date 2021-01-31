import java.io.*;
import java.util.*;
public class help {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("help.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("help.out")));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		int[] times = new int[N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		times[0] = 1;
		for(int i=1; i<N; i++) {
			times[i] = times[i-1]*2;
			times[i] %=1000000007;
		}
		LinkedList<int[]> list = new LinkedList<>();
		for(int i=0; i<N; i++) 
			for(int j=0; j<2; j++)
				list.add(new int[] {arr[i][j], j});
		Collections.sort(list, (a,b) -> a[0]-b[0]);
		int curr = 0;
		long ans = 0;
		while(!list.isEmpty()) {
			int[] nums = list.removeFirst();
			if(nums[1]==0) {
				ans += times[N-1-curr];
				ans %= 1000000007L;
				curr++;
			}
			else
				curr--;
		}
		out.println(ans);
		out.close();
	}

}
