import java.io.*;
import java.util.*;
public class race3 {
	static ArrayList<Integer>[] edges;
	static boolean[] isavoid;
	static boolean[] issplit;
 	static int N;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] store = new int[101][2];
		N = 0;
		int c = 0;
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			while(a!=-1 && a!=-2) {
				store[c][0] = N;
				store[c++][1] = a;
				a = Integer.parseInt(st.nextToken());
			}
			if(a==-1) {
				store[c][0] = -1;
				store[c][1] = -1;
				break;
			}
			N++;
		}
		edges = new ArrayList[N];
		for(int i=0; i<N; i++)
			edges[i] = new ArrayList<>();
		for(int i=0; i<100; i++) {
			if(store[i][0] ==-1)break;
			edges[store[i][0]].add(store[i][1]);
		}
		isavoid = new boolean[N];
		issplit = new boolean[N];
		Arrays.fill(issplit, true);
		HashSet<Integer> z = new HashSet<>();
		z.add(0);
		recursion(z, 0);
		int ans1 = 0;
		for(int i=1; i<N-1; i++)
			if(!isavoid[i])
				ans1++;
		System.out.print(ans1);
		for(int i=1; i<N-1; i++)
			if(!isavoid[i])
				System.out.print(" "+i);
		System.out.println();
		int ans2 = 0;
		for(int i=1; i<N-1; i++)
			if(issplit[i]&&!isavoid[i])
				ans2++;
		System.out.print(ans2);
		for(int i=1; i<N-1; i++)
			if(issplit[i]&&!isavoid[i])
				System.out.print(" "+i);
		System.out.println();
		
	}
	static void recursion(HashSet<Integer> set, int node) {
		if(node ==N-1) {
			for(int i=0; i<N; i++) {
				if(!set.contains(i))
					isavoid[i] = true;
			}
			return;
		}
		ArrayList<Integer> al = edges[node];
		int s = al.size();
		for(int i=0; i<s; i++) {
			int tar = al.get(i);
			if(tar==node) //idk should be here
				continue;
			if(set.contains(tar)) {
				issplit[node] = false;
				calculate(tar);
				continue;
			}
			set.add(tar);
			recursion(set, tar);
			set.remove(tar);
		}
	}
	static void calculate(int node) {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(node);
		while(!list.isEmpty()) {
			int curr = list.removeFirst();
			ArrayList<Integer> al = edges[curr];
			int s= al.size();
			for(int i=0; i<s; i++) {
				int tar = al.get(i);
				if(issplit[tar] ==true) {
					issplit[tar] = false;
					list.add(tar);
				}
			}
		}
	}
}
