import java.util.*;
import java.io.*;

public class artgallery3 {
	static int[] bitree;
	static Map<Integer, Integer> map;
	@SuppressWarnings("resource")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("artgallery3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("artgallery3.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		TreeSet<Integer> set = new TreeSet<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2-o1;
			}
		});
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		int add = 0;
		for(int i=0; i<N; i++) {
			add+=arr[i];
			set.add(arr[i]-add);
		}
		bitree = new int[set.size()+1];
		map= new HashMap<>();
		int a =1;
		for(int num: set)
			map.put(num, a++);
		map.put(1000000000, 0);
		map.put(-1000000000, set.size());
		set.add(-1000000000);
		set.add(1000000000);
		add = 0;
		long ans = 0;
		LinkedList<Integer> list = new LinkedList<>();
		for(int i=0; i<2*N-2; i++) {
			add+= arr[i%N];
			if(i<N) {
				update(map.get(arr[i%N]-add), 1);
				list.add(arr[i%N]-add);
			}
			else {
				if(i==N) 
					update(map.get(list.removeFirst()),-1);
				update(map.get(list.removeFirst()),-1);
			}
			ans += cal(map.get((int) set.floor(K-add)));
		}
		out.println(ans);
		out.close();
	}
	static void update(int node, int val) {
		for(int i=node; i<bitree.length-1; i+=(-i&i))
			bitree[i]+=val;
	}
	static int cal(int node) {
		int sum = 0;
		for(int i=node; i>0; i-=(-i&i))
			sum += bitree[i];
		return sum;
	}
}
