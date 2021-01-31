import java.io.*;
import java.util.*;
public class fewcoins {

	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[] coins = new int[N];
		int[] count = new int[N];
		int V = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			coins[i] = Integer.parseInt(st.nextToken());
			V = Math.max(V, coins[i]);
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			count[i] = Integer.parseInt(st.nextToken());
		int[] dpc = new int[V*V+1];
		Arrays.fill(dpc, 10000000);
		dpc[0] = 0;
		for(int i=0; i<N; i++) {
			int val = coins[i];
			if(val<=V*V)
				dpc[val] = 1;
			for(int j = val+1; j<=V*V; j++) 
				dpc[j] = Math.min(dpc[j], dpc[j-val]+1);
		}
		int[] dpf = new int[T+V*V+1];
		Arrays.fill(dpf, 10000000);
		dpf[0]=0;
		TreeSet<Integer>[] que = new TreeSet[V];
		Queue<Integer>[] stuff = new Queue[V];
		Map<Integer, Integer>[] set = new HashMap[V];
		for(int j=0; j<V; j++) {
			que[j] = new TreeSet<>();
			set[j] = new HashMap<>();
			stuff[j] = new LinkedList<>();
		}
		int[] size = new int[V];
		for(int i=0; i<N; i++) {
			int val = coins[i];
			que[0].add(0);
			set[0].put(0, 1);
			stuff[0].add(0);
			size[0] = 1;
			int add = 0;
			for(int j=1; j<T+V*V+1; j++) {
				int pos = j%val;
				if(pos==0)
					add++;
				if(dpf[j]!=10000000) {
					if(que[pos].contains(dpf[j]-add)) {
						set[pos].put(dpf[j]-add, set[pos].get(dpf[j]-add)+1);
						stuff[pos].add(dpf[j]-add);
					}
					else {
						que[pos].add(dpf[j]-add);
						set[pos].put(dpf[j]-add, 1);
						stuff[pos].add(dpf[j]-add);
					}
				}
				if(que[pos].size()!=0) {
					int min = que[pos].pollFirst();
					que[pos].add(min);
					int comp  = min +add;
					dpf[j] = Math.min(dpf[j], comp);
				}
				if(size[pos]>count[i]&&stuff[pos].size()!=0) {
					int rev =  stuff[pos].poll();
					if(set[pos].get(rev)==1) {
						set[pos].remove(rev);
						que[pos].remove(rev);
					}
					else 
						set[pos].put(rev, set[pos].get(rev)-1);
				}
				size[j%val] ++;
			}
			for(int j=0; j<val; j++) {
				que[j].clear();
				set[j].clear();
				stuff[j].clear();
				size[j] = 0;
			}
		}
		int ans = 10000000;
		for(int i= T; i<V*V+T+1; i++) 
			ans = Math.min(dpc[i-T]+dpf[i], ans);
		if(ans==10000000)
			System.out.println(-1);
		else
			System.out.println(ans);
			
	}

}