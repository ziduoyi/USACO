import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class usacop3 {

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
			int v=Integer.parseInt(st.nextToken());
			edges[r].add(new Edge(c,v));
			edges[c].add(new Edge(r,v));
		}

		for(int j=0; j<q; j++) {
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
				for(Edge edge : edges[x]) {
					int n = edge.n;
					int min=edge.r;
					if(min>=a && !visited[n]) {
						count++;
						visited[n]=true;
						list.add(n);
					}
				}
			}
			out.println(count);
		}
		out.close();
		System.out.println((System.currentTimeMillis()-t1));
	}
	
	static class Edge{
		int n; // destination node
		int r; // relevance
		public Edge(int dest, int relev) {
			this.n=dest;
			this.r=relev;
		}
	}


}

