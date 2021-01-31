import java.io.*;
import java.util.*;
public class photo {
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] starts = new ArrayList[N+1];
		int[] ends = new int[N+1];
		int[] sum = new int[N+2];
		for(int i=0; i<=N; i++)
			starts[i] = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			starts[a].add(b);
			ends[b]++;
			sum[a]++;
			sum[b+1]--;
		}
		for(int i=1; i<=N; i++) {
			sum[i] += sum[i-1];
			if(sum[i] == 0) {
				starts[i].add(i);
				ends[i]++;
			}
		}
		starts[0].add(0);
		ends[0]++;
		TreeSet<Integer> set = new TreeSet<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		TreeSet<Integer> que = new TreeSet<>();
		HashMap<Integer, Integer> check = new HashMap<>();
		int upper = 1000000;
		int[] dp = new int[N+2];
		dp[N+1] = 0;
		que.add(0);
		check.put(0, 1);
		int pos1 = N+1;
		int pos2 = N+2;
		int c = 190020;
		for(int i=N; i>-1; i--) {
			dp[i] = -1;
			if(ends[i]>0) {
				set.add(i);
				map.put(i, ends[i]);
			}
			if(i!=N) {
				ArrayList<Integer> al = starts[i+1];
				int s = al.size();
					for(int j=0; j<s; j++) {
						int tar = al.get(j);
						upper = Math.min(upper, tar);
						if(map.get(tar)==null)
							continue;
						if(map.get(tar)==1) {
							map.remove(tar);
							set.remove(tar);
						}
						else
							map.put(tar, map.get(tar)-1);
					}
			}
			int j = set.pollLast();
			set.add(j);
			if(j+1<=upper) {
				if(j+1<pos1) {
					for(int k = j+1; k<pos1; k++) {
						if(dp[k]==-1)
							continue;
						if(que.contains(dp[k])) 
							check.put(dp[k], check.get(dp[k])+1);
						else {
							check.put(dp[k], 1);
							que.add(dp[k]);
						}
					}
					pos1 = j+1;
				}
				if(upper<pos2) {
					for(int k = upper +1; k<pos2; k++) {
						if(dp[k]==-1)
							continue;
						if(check.get(dp[k])==1) {
							que.remove(dp[k]);
							check.remove(dp[k]);
						}
						else
							check.put(dp[k], check.get(dp[k])-1);
					}
					pos2 = upper;
				}
				if(!que.isEmpty()) {
					int val = que.pollLast();
					que.add(val);
					dp[i] = val +1;
				}
			}
		}
		if(dp[0] == -1|| c==N)
			System.out.println(-1);
		else
			System.out.println(dp[0]-1);
	}
}
