import java.io.*;
import java.util.*;
public class codes {
	static ArrayList<Integer>[] starts;
	static ArrayList<Integer>[] edges;
	static HashMap<Integer, Integer> map;
	static ArrayList<Integer> order;
	static int num;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] arr = new String[N];
		for(int i=0; i<N; i++)
			arr[i] = br.readLine();
		String string = br.readLine();
		starts = new ArrayList[58];
		for(int i=0; i<58; i++)
			starts[i] = new ArrayList<>();
		for(int i=0; i<string.length(); i++) 
			starts[string.charAt(i)-'A'].add(i);
		edges = new ArrayList[10000];
		for(int i=0; i<10000; i++) 
			edges[i] =new ArrayList<>();
		map = new HashMap<>();
		order = new ArrayList<>();
		num = 0;
		for(int i=0; i<N; i++)
			find_sequence(arr[i]);
		Collections.sort(order);
		int[] dist = new int[num];
		int ans = 0;
		Arrays.fill(dist, -1);
		dist[map.get(order.get(0))] = 0;
		LinkedList<Integer> list = new LinkedList<>();
		list.add(order.get(0));
		while(!list.isEmpty()) {
			int node = list.removeFirst();
			ArrayList<Integer> al = edges[map.get(node)];
			int s = al.size();
			for(int i=0; i<s; i+=2) {
				int tar = Collections.binarySearch(order,al.get(i));
				if(tar<0) {
					tar++;
					tar*=-1;
				}
				int cost = al.get(i+1);
				if(tar ==order.size()) {
					ans = Math.max(ans, dist[map.get(node)]+cost);
					continue;
				}
				tar = order.get(tar);
				if(dist[map.get(node)] + cost > dist[map.get(tar)]) {
					dist[map.get(tar)] = dist[map.get(node)] + cost;
					list.add(tar);
				}
			}
			int next = Collections.binarySearch(order, node+1);
			if(next<0) {
				next++;
				next*=-1;
			}
			if(next ==order.size()) {
				ans = Math.max(ans, dist[map.get(node)]);
				continue;
			}
			if(dist[map.get(node)]> dist[map.get(order.get(next))]) {
				dist[map.get(order.get(next))] = dist[map.get(node)];
				list.add(order.get(next));
			}
		}
		
		System.out.println(ans);
	}
	static void find_sequence(String s) {
		for(int start: starts[s.charAt(0)-'A']) {
			int idx = start;
			for(int i= 1; i<s.length(); i++) {
				ArrayList<Integer> al = starts[s.charAt(i)-'A'];
				int pos = Collections.binarySearch(al, idx+1);
				if(pos<0) {
					pos++;
					pos*=-1;
				}
				if(pos == al.size()) 
					return;
				idx = al.get(pos);
			}
			if(idx!=-1) {
				if(idx-start+1<=1000) {
					if(!map.containsKey(start)) {
						map.put(start, num++);
						order.add(start);
					}
					edges[map.get(start)].add(idx+1);
					edges[map.get(start)].add(s.length());
				}
			}
		}
	}
}
