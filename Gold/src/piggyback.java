import java.util.*;
import java.io.*;
public class piggyback {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("piggyback.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("piggyback.out")));
		int B=scanner.nextInt();
		int E=scanner.nextInt();
		int P=scanner.nextInt();
		int N=scanner.nextInt();
		int M=scanner.nextInt();
		ArrayList<Integer>[] edges=new ArrayList[N];
		for(int i=0; i<N; i++) {
			edges[i]=new ArrayList<>();
		}
		for(int i=0; i<M; i++) {
			int s=scanner.nextInt()-1;
			int e=scanner.nextInt()-1;
			edges[s].add(e);
			edges[e].add(s);
		}
		int[] timeT= new int[N];
		int[] timeB= new int[N];
		int[] timeE= new int[N];
		Arrays.fill(timeT, Integer.MAX_VALUE);
		Arrays.fill(timeB, Integer.MAX_VALUE);
		Arrays.fill(timeE, Integer.MAX_VALUE);
		timeT[N-1]=0;
		timeB[0]=0;
		timeE[1]=0;
		Dj(edges, timeT, N-1, 1);
		Dj(edges, timeB, 0, B);
		Dj(edges, timeE, 1, E);
		int min =Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			min=Math.min(min, timeB[i]+timeE[i]+(timeT[i]*P));
		}
		out.println(min);
		out.close();
	}
	static int[] Dj(ArrayList<Integer>[] edges, int[] time, int s, int mult) {
		LinkedList<Integer> list=new LinkedList<>();
		list.add(s);
		while(!list.isEmpty()) {
			int node=list.removeFirst();
			ArrayList<Integer> al=edges[node];
			int n=al.size();
			for(int i=0; i<n; i++) {
				int tar=al.get(i);
				if(time[node]+mult<time[tar]) {
					time[tar]=time[node]+mult;
					list.add(tar);
				}
			}
		}
		return time;
	}
}
