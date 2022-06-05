import java.io.*;
import java.util.*;
public class lost {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br= new BufferedReader(new FileReader("lost.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("lost.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr =  new int[M][3];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken())-1;
			arr[i][1] = Integer.parseInt(st.nextToken())-1;
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		long[] dist = new long[N];
		Arrays.fill(dist, 1000000000000000000L);
		dist[0]=0;
		for(int i=0; i<N-1; i++) {
			for(int j=0; j<M; j++) {
				int u = arr[j][0];
				int v = arr[j][1];
				int d = arr[j][2];
				if(dist[u]!=1000000000000000000L&&dist[v]>dist[u]+d)
					dist[v]=dist[u]+d;
			}
		}
		TreeSet<Integer> safe = new TreeSet<>();
		for(int i=0; i<N; i++)
			if(dist[i]!=1000000000000000000L)
				safe.add(i);
		for(int j=0; j<N/4; j++) {
			for(int i=0; i<M; i++) {
				int u = arr[i][0];
				int v = arr[i][1];
				int d = arr[i][2];
				if(dist[u]!=1000000000000000000L&&dist[u]+d<dist[v]) {
					if(safe.contains(v))
						safe.remove(v);
					dist[v] = dist[u]+d;
				}
			}
		}
		if(safe.isEmpty())
			out.print("None");
		else {
			while(!safe.isEmpty())
				out.print(safe.pollFirst()+1+" ");
		}
		out.println();
		out.close();
	}

}
