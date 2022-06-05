import java.io.*;
import java.util.*;
public class dec {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("dec.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dec.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int[][] bonus = new int[B][3];
		for(int i=0; i<B; i++) {
			st = new StringTokenizer(br.readLine());
			bonus[i][0] = Integer.parseInt(st.nextToken())-1;
			bonus[i][1] = Integer.parseInt(st.nextToken());
			bonus[i][2] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(bonus, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				if(arg0[0] != arg1[0]) return arg0[0] - arg1[0];
				return arg0[1]-arg1[1];
			}
			
		});
		int[][] cows = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++)
				cows[i][j] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[(1<<N)];
		LinkedList<Integer> list = new LinkedList<>();
		list.add(0);
		list.add(0);
		int step = 0;
		int move = 0;
		while(!list.isEmpty()) {
			int s = list.size();
			int end = move-1;
			for(int i=move; i<B; i++) {
				if(bonus[i][0]!=step)break;
				end = i;
			}
			for(int i=0; i<s; i++) {
				int pos = list.removeFirst();
				for(int j=0; j<N; j++) {
					int next = pos^(1<<j);
					if(next>pos) {
						int val = dp[pos]+cows[j][step];
						for(int k=move; k<=end; k++) {
							if(val>=bonus[k][1])val+=bonus[k][2];
						}
						if(dp[next]<val) {
							dp[next]=val;
							list.add(next);
						}
					}
				}
			}
			move = end+1;
			step++;
		}
		out.println(dp[(1<<N)-1]);
		out.close();
	}

}
