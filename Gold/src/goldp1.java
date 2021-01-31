import java.util.*;
import java.io.*;
public class goldp1 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br= new BufferedReader(new FileReader("timeline.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("timeline.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] mins = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
			mins [i] = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] starts = new ArrayList[N];
		ArrayList<Integer>[] ends = new ArrayList[N];
		for(int i=0; i<N; i++) {
			starts[i] = new ArrayList<>();
			ends[i] = new ArrayList<>();
		}
		for(int i=0; i<C; i++) {
			st = new StringTokenizer(br.readLine());
			int a =Integer.parseInt(st.nextToken())-1;
			int b =Integer.parseInt(st.nextToken())-1;
			int c =Integer.parseInt(st.nextToken());
			starts[a] .add(b);
			starts[a].add(c);
			ends[b].add(a);
			ends[b].add(c);
		}
		int[] dist = new int[N];
		for(int i=0; i<N; i++)
			dist[i] = mins[i];
		LinkedList<Integer> list = new LinkedList<>();
		int count =0;
		boolean[] visit = new boolean[N];
		for(int j=0; j<N; j++) {
			if(count==N)
				break;
			if(visit[j] ==true)
				continue;
			list.add(j);
			visit[j] =true;
			count++;
			while(!list.isEmpty()) {
				int num = list.removeFirst();
				if(visit[num]==false) {
					count ++;
					visit[num] = true;
				}
				ArrayList<Integer> start = starts[num];
				ArrayList<Integer> end = ends[num];
				int s1 = start.size();
				int s2 = end.size();
				for(int i=0; i<s1; i+=2) {
					int tar = start.get(i);
					int cost = start.get(i+1);
					if(dist[num] + cost>dist[tar]) {
						dist[tar] =dist[num] + cost;
						list.add(tar);
					}
				}
				
				for(int i=0; i<s2; i+=2) {
					int tar = end.get(i);
					int cost = end.get(i+1);
					if(dist[tar] + cost>dist[num]) {
						dist[num] = dist[tar] + cost;
						list.add(num);
					}
				}
				
			}
		}
		for(int i=0; i<N; i++)
			out.println(dist[i]);
		out.close();
	}

}