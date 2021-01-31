import java.io.*;
import java.util.*;

public class relocate {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("relocate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("relocate.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int num=Integer.parseInt(st.nextToken());
		int path=Integer.parseInt(st.nextToken());
		int farm=Integer.parseInt(st.nextToken());
		int[] farms=new int[farm];
		for(int i=0; i<farm; i++) {
			farms[i]=Integer.parseInt(br.readLine())-1;
		}
		ArrayList<Integer>[] edges=new ArrayList[num];
		for(int i=0;i<num; i++) {
			edges[i]=new ArrayList<>();
		}
		for(int i=0; i<path; i++) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken())-1;
			int y=Integer.parseInt(st.nextToken())-1;
			int z=Integer.parseInt(st.nextToken());
			edges[x].add(y);
			edges[x].add(z);
			edges[y].add(x);
			edges[y].add(z);
		}
		int[][] times=new int[farm][num];
		for(int i=0; i<farm; i++) {
			times[i]=findLength(edges, farms[i], num).clone();
		}
		Set<Integer> set=new HashSet<>();
		for(int i=0; i<farm; i++) {
			set.add(farms[i]);
		}
		long min=Long.MAX_VALUE;
		for(int i=0; i<num; i++) {
			if(set.contains(i))continue;
			int total=0;
			int start=Integer.MAX_VALUE;
			int pos=-1;
			boolean[] visited=new boolean[farm];
			for(int j=0; j<farm; j++) {
				if(times[farms[j]][i]<start) {
					pos=j;
					start=times[j][i];
				}
			}
			visited[pos]=true;
			total+=start;
			for(int j=0; j<farm-1; j++) {
				start=Integer.MAX_VALUE;
				 int sub=-1;
				for(int k=0; k<farm; k++) {
					if(visited[k]==true)continue;
					if(times[pos][farms[k]]<start) {
						sub=j;
						start=times[k][j];
					}
				}
				pos=sub;
				total+=start;
				visited[pos]=true;
			}
			total+=times[pos][i];
			min=Math.min(total, min);
		}
		out.println(min);
		out.close();
	}
	static int[] findLength(ArrayList<Integer>[] edges, int start, int num) {
		int[] time=new int[num];
		Arrays.fill(time, 1000000000);
		time[start]=0;
		LinkedList<Integer> list=new LinkedList<>();
		list.add(start);
		while(!list.isEmpty()) {
			int node=list.removeFirst();
			ArrayList<Integer> al=edges[node];
			int s=al.size();
			for(int i=0; i<s; i+=2) {
				int tar=al.get(i);
				int dist=al.get(i+1);
				if(time[node]+dist<time[tar]) {
					time[tar]=time[node]+dist;
					list.add(tar);
				}
			}
		}
		return time;
		
	}
}
