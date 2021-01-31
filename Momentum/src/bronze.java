import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
	public int x;
	public int y;
	@Override
	public int compareTo(Node arg0) {
		// TODO Auto-generated method stub
		return this.y-arg0.y;
	}
}
public class bronze {
	static ArrayList<Integer>[] edges;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub

		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner scanner = new Scanner(System.in);
		//StringTokenizer st = new StringTokenizer(br.readLine());
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		//System.out.println(3);
		//if(result==3)
		//    return;
		
		edges = new ArrayList[N];
		int[][] stuff = new int[M][3];
		for(int i=0; i<N; i++)
			edges[i] = new ArrayList<>();
		

		for(int i=0; i<M; i++) {
			//st= new StringTokenizer(br.readLine());
			int a = scanner.nextInt()-1;
			int b = scanner.nextInt()-1;
			int c = scanner.nextInt();
			edges[a].add(b);
			edges[a].add(c);
			edges[b].add(a);
			edges[b].add(c);
			stuff[i][0]=a;
			stuff[i][1]=b;
			stuff[i][2]=c;
		}
		int count=0;
		int comp = Prim(new boolean[N],0,0,0);
		for(int i=0; i<M; i++) {
			if(Prim(new boolean[N],stuff[i][0], stuff[i][1], stuff[i][2])==comp)
				count++;
		}
		System.out.println(count);
	}
	static int Prim(boolean[] visit, int a, int b, int start) {
		PriorityQueue<Node> list = new PriorityQueue<>();
//		PriorityQueue<Node> list = new PriorityQueue<>(new Comparator<Node>() {
//			@Override
//			public int compare(Node o1, Node o2) {
//				// TODO Auto-generated method stub
//				return o1.y-o2.y;
//			}
//		});
		int count =1;
		int dist =start;
		if(a==b) {
			visit[0]=true;
			ArrayList<Integer> al = edges[0];
			int s = al.size();
			for(int i=0; i<s; i+=2) {
				Node node = new Node();
				node.x =al.get(i);
				node.y = al.get(i+1);
				list.add(node);
			}
		}
		else {
			visit[a]=true;
			visit[b]=true;
			ArrayList<Integer> al1 = edges[a];
			int s = al1.size();
			for(int i=0; i<s; i+=2) {
				if(al1.get(i)==b)
					continue;
				Node node = new Node();
				node.x =al1.get(i);
				node.y = al1.get(i+1);
				list.add(node);
			}
			ArrayList<Integer> al2 = edges[b];
			s = al2.size();
			for(int i=0; i<s; i+=2) {
				if(al2.get(i)==a)
					continue;
				Node node = new Node();
				node.x =al2.get(i);
				node.y = al2.get(i+1);
				list.add(node);
			}
			count++;
		}
		while(count!=visit.length) {
			Node use = list.poll();
			if(visit[use.x]==true)
				continue;
			visit[use.x] =true;
			count++;
			dist+=use.y;
			ArrayList<Integer> items = edges[use.x];
			for(int i=0; i<items.size(); i+=2) {
				if(visit[items.get(i)]==true)
					continue;
				Node node = new Node();
				node.x =items.get(i);
				node.y = items.get(i+1);
				list.add(node);
			}
		}
		return dist;
		
	}

}
