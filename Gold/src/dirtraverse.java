import java.util.*;
import java.io.*;
public class dirtraverse {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("dirtraverse.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dirtraverse.out")));
		int N = Integer.parseInt(br.readLine());
		int[] cost = new int[N];
		ArrayList<Integer>[] edges = new ArrayList[N];
		for(int i=0; i<N; i++)
			edges[i]= new ArrayList<>();
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			int num = Integer.parseInt(st.nextToken());
			cost[i]=str.length()+1;
			for(int j=0; j<num; j++) {
				edges[i].add(Integer.parseInt(st.nextToken())-1);
			}
		}
		ArrayList<Integer> child = new ArrayList<>();
		int[] count =new int[N];
		for(int i=0; i<N; i++) {
			if(edges[i].size()==0) { 
				count [i] = 1;
				child.add(i);
			}
		}
		tranverse(edges, 0, count);
		int[] prev = new int[N];
		int[] time = new int[N];
		LinkedList<Integer> list = new LinkedList<>();
		list.add(0);
		while(!list.isEmpty()) {
			int node = list.removeFirst();
			int n = edges[node].size();
			ArrayList<Integer> al = edges[node];
			for(int i=0; i<n; i++) {
				int tar = al.get(i);
				time[tar]= time[node] + cost[tar];
				list.add(tar);
				prev[tar] = node;
			}
		}
		long[] total = new long[N];
		for(int i=0; i<child.size(); i++)
			total[0] += time[child.get(i)];
		list.add(0);
		while(!list.isEmpty()) {
			int node = list.removeFirst();
			int n = edges[node].size();
			if(node!=0) {
				total[node]= total[prev[node]];
				int sub = count[node];
				total[node] -= cost[node]*sub;
				total[node] += 3*(child.size()-sub);
			}
			ArrayList<Integer> al = edges[node];
			for(int i=0; i<n; i++) {
				int tar = al.get(i);
				list.add(tar);
			}
		}
		long min= Long.MAX_VALUE;
		for(int i=0; i<N; i++)
			min = Math.min(min, total[i]);
		out.println(min-child.size());
		out.close();
	}
	static int tranverse( ArrayList<Integer>[] edges,int curr, int[] count) {
		if(edges[curr].size() == 0)
			return 1;
		int n = edges[curr].size();
		for(int i=0; i<n; i++) 
			count[curr] +=tranverse(edges, edges[curr].get(i), count);
		return count[curr];
	}
}
