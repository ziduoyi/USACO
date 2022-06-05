import java.util.*;
import java.io.*;
public class flowers2 {
	static Set<Integer>[] bitree;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		BufferedReader br=new BufferedReader(new FileReader("flowers2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("flowers2.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		bitree = new HashSet[N+1];
		for(int i=0; i<=N; i++)
			bitree[i] = new HashSet<>();
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			for(int j=0; j<len; j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				update(a,b);
			}
			out.println(cal(Integer.parseInt(st.nextToken())));
		}
		out.close();
	}
	static void update(int node, int val) {
		for(int i=node; i<bitree.length-1; i+=(-i&i))
			bitree[i].add(val);
	}
	static int cal(int node) {
		Set<Integer> set = new HashSet<>();
		for(int i=node; i>0; i-=(-i&i))
			set.addAll( bitree[i]);
		return set.size();
	}
}
