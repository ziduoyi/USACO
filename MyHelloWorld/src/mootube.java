import java.io.*;
import java.util.*;

public class mootube {
	//silver 
/*
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		long t1=System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num =Integer.parseInt(st.nextToken());
		int q=Integer.parseInt(st.nextToken());
		int[][] arr=new int[num+1][num+1];
		ArrayList<Edge>[] edges = new ArrayList[num+1];
		for(int i=1;i<num+1;i++)
			edges[i]=new ArrayList<Edge>();

		for(int i=0; i<num-1; i++) {
			st = new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			edges[r].add(new Edge(c,v));
			edges[c].add(new Edge(r,v));
			arr[r][0]++;
		}

		for(int m=0; m<q; m++) {
			st = new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int i=Integer.parseInt(st.nextToken());
			int count =0;

			LinkedList<Integer> list = new LinkedList<>();
			list.add(i);
			boolean[] visited = new boolean[num+1];
			visited[i]=true;
			while(!list.isEmpty()) {
				int x = list.removeFirst();
				for(Edge edge : edges[x]){
					int k=edge.n;
					if(!visited[k] && edge.r>=a){
						count++;
						list.add(k);
						visited[k]=true;
					}
				}
			}
			out.println(count);
		}
		out.close();
		System.out.println((System.currentTimeMillis()-t1) + "ms");
	}*/
	
	static class Edge{
		int n;	//destination node
		int r;	//relevance
		public Edge(int node, int rel){
			this.n=node;
			this.r=rel;
		}
	}
	
	//gold
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		long t1=System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num =Integer.parseInt(st.nextToken());
		int q=Integer.parseInt(st.nextToken());
		int[][] arr=new int[num+1][num+1];
		ArrayList<Edge>[] edges = new ArrayList[num+1];
		for(int i=1;i<num+1;i++)
			edges[i]=new ArrayList<Edge>();

		for(int i=0; i<num-1; i++) {
			st = new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			edges[r].add(new Edge(c,v));
			edges[c].add(new Edge(r,v));
			arr[r][0]++;
		}

		for(int m=0; m<q; m++) {
			st = new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int i=Integer.parseInt(st.nextToken());
			int count =0;

			LinkedList<Integer> list = new LinkedList<>();
			list.add(i);
			boolean[] visited = new boolean[num+1];
			visited[i]=true;
			while(!list.isEmpty()) {
				int x = list.removeFirst();
				for(Edge edge : edges[x]){
					int k=edge.n;
					if(!visited[k] && edge.r>=a){
						count++;
						list.add(k);
						visited[k]=true;
					}
				}
			}
			out.println(count);
		}
		out.close();
		System.out.println((System.currentTimeMillis()-t1) + "ms");
	}

}
