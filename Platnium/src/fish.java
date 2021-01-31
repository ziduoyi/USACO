import java.io.*;
import java.util.*;
public class fish {
	static long seg[];
	static int F;
	static int M;
	static int K;
	static int cool;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		F = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		int[][] fishes = new int[F][2]; // length, gem type
		for(int i=0; i<F; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			fishes[i][0] = Integer.parseInt(st.nextToken());
			fishes[i][1] = (Integer.parseInt(st.nextToken())-1);
		}
		Arrays.sort(fishes,new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				if(arg0[0]!=arg1[0]) return arg0[0] - arg1[0];
				return arg0[1] - arg0[1];
			}
		});
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] store = new ArrayList[K];
		for(int i=0; i<K; i++)
			store[i] = new ArrayList<>();
		for(int i=0; i<F; i++)
			store[fishes[i][1]].add(fishes[i][0]);
		ArrayList<int[]> order = new ArrayList<>();
		for(int i=0; i<K; i++) {
			if(!store[i].isEmpty()) 
				order.add(new int[] {store[i].get(store[i].size()-1), i});
		}
		int[] E =  new int[order.size()];
		seg = new long[order.size()*2];
		Arrays.fill(seg, 1);
		Collections.sort(order, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if(o1[0]!=o2[0]) return o1[0] - o2[0];
				return o1[1] - o2[1];
			}
		});
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<order.size(); i++)
			map.put(order.get(i)[1], i);
		cool = order.size();
		int[] need = new int[K];
		for(int i=0; i<K; i++) {
			if(!store[i].isEmpty()) {
				need[i] = store[i].get(store[i].size()-1);
				int comp = need[i];
				for(int j= store[i].size()-1; j>-1; j--) {
					if(store[i].get(j)*2>comp) 
						need[i] = store[i].get(j);
					else {
						E[i] = j+1;
						break;
					}
				}
			}
		}
		PriorityQueue<int[]> que = new PriorityQueue<>(new Comparator<int[]>(){
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if(o1[0]!=o2[0]) return o1[0] - o2[0];
				return o1[1] - o2[1];
		}});
		int last =0;
		long[] ans = new long[order.size()];
		for(int i=0; i<order.size(); i++) {
			int[] curr = order.get(i);
			for(; last<order.size(); last++) {
				if(need[curr[1]]*2>store[order.get(last)[1]].get(store[order.get(last)[1]].size()-1)) {
					for(int j=0; j<store[order.get(last)[1]].size(); j++)
						que.add(new int[] {store[order.get(last)[1]].get(j),order.get(last)[1]});
				}
				else
					break;
			}
			boolean b = false;
			while(b==false) {
				b = true;
				int[] arr = que.peek();
				if(curr[0]>=arr[0]*2) {
					que.poll();
					update(map.get(arr[1]));
					b = false;
				}
			}
			ans[i] =(E[curr[1]]*query(0, i-1)+(query(0, i-1)*query(i+1, order.size()-1)));
			ans[i] %= M;
		}
		long print = 0;
		int z = order.get(order.size()-1)[0];
		for(int i= order.size()-1; i>-1; i--) {
			if(order.get(i)[0]*2>-1/*z*/) {
				print += ans[i];
				print%=M;
			}
			else
				break;
		}
		System.out.println(print);
		
	}
	static void update(int pos) {
		int x = pos+ cool;
		seg[x] += 1;
		while(x/2>0) {
			int l=x;
			int r=x;
			if(x%2==0) 
				r++;
			else
				l--;
			seg[x/2] = seg[l]*seg[r];
			seg[x/2]%=M;
			x/=2;
		}
	}
	static int query(int i, int j) {
		if(i>j)
			return 1;
		int l = i+cool;
		int r = j+cool;
		long sum =1;
		while(l<=r) {
			if(l%2==1) 
				sum*=seg[l++];
			if(r%2==0) 
				sum*=seg[r--];
			l/=2;
			r/=2;
			sum%=M;
		}
		return (int) sum;
	}

}
