import java.io.*;
import java.util.*;
public class cbs {
	static int[] seg;
	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		HashSet<Integer> set = new HashSet<>();
		seg = new int[4*N];
		Arrays.fill(seg, N+1);
		for(int i=0; i<K; i++) {
			String str = br.readLine();
			int sum =0;
			HashSet<Long> curr = new HashSet<>();
			Map<Integer, LinkedList<Integer>> store = new HashMap<>();
			if(str.charAt(0)=='(') {
				sum = 1;
				LinkedList<Integer> b = new LinkedList<>();
				b.add(-1);
				store.put(0, b);
			}
			else
				sum = -1;
			if(str.charAt(1)=='(') {
				LinkedList<Integer> a = new LinkedList<>();
				a.add(0);
				store.put(sum, a);
			}
			update(sum,0, 1, 0, N-1);
			for(int j =1; j<N; j++) {
				if(str.charAt(j)=='(')
					sum += 1;
				else 
					sum += -1;
				update(sum,j, 1, 0, N-1);
				if(str.charAt(j)=='(') {
					if(j!=N-1&&str.charAt(j+1)=='(') {
						LinkedList<Integer> temp = new LinkedList<>();
						if(store.containsKey(sum))
							temp = store.get(sum);
						temp.addFirst(j);
						store.put(sum, temp);
					}
				}
				else {
					if(store.containsKey(sum)) {
						LinkedList<Integer> list = store.get(sum);
						int z =0;
						for(int num: list) {
							if(query(1,num+1, j, 0, list.size()-1)<sum)
								break;
							z++;
						}
						int s = list.size();
						for(int k=0; k<s-z; k++)
							list.removeLast();
						for(int num: list)
							curr.add(((long)num+1)*(N+1)+j);
						if(j!=N-1&&str.charAt(j+1)=='(') {
							LinkedList<Integer> temp = store.get(sum);
							temp.addFirst(j);
							store.put(sum, temp);
						}
					}
					else if(j!=N-1&&str.charAt(j+1)=='('){
						LinkedList<Integer> temp = new LinkedList<>();
						temp.addFirst(j);
						store.put(sum, temp);
					}
				}	
			}
			HashSet<Long> rev = new HashSet<>();
			for(long num: curr) {
				if(!set.contains(num))
					rev.add(num);
			}
			if(i==0)
				rev.clear();
			curr.removeAll(rev);
			set = (HashSet<Integer>) curr.clone();
		}
		System.out.println(set.size());
	}
	static void update(int delta,int pos, int x, int l, int r) {
		int s1 =2*x, s2 = 2*x+1;
		if(l==r)
			seg[x] = delta;
		else {
			int mid = (l+r)/2;
			if(mid>=pos)
				update(delta,pos, s1, l, mid);
			else
				update(delta,pos, s2, mid+1, r);
			seg[x] = Math.min(seg[s1], seg[s2]);
		}
	}
	static int query(int x, int a, int b, int l, int r) {
		int s1 = 2*x, s2 = 2*x +1;
		if(r<a||l>b||l>r)
			return 1000000;
		if(l>=a&&r<=b)
			return seg[x];
		int mid = (l+r)/2;
		int ans = query(s1, a, b, l, mid);
		ans = Math.min(ans, query(s2, a, b, mid+1, r));
		return ans;
	}

}
