import java.io.*;
import java.util.*;
public class moocast {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("moocast.in"));
		int num=Integer.parseInt(br.readLine());
		int[][] save=new int[num][2];
		for(int i=0; i<num; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			save[i][0]=Integer.parseInt(st.nextToken());
			save[i][1]=Integer.parseInt(st.nextToken());
		}
		ArrayList<Integer>[] edges=new ArrayList[num];
		for(int i=0; i<num; i++) {
			edges[i]=new ArrayList<>();
		}
		for(int i=0; i<num-1; i++) {
			for(int j=i+1; j<num; j++) {
				int time=(save[i][0]-save[j][0])*((save[i][0]-save[j][0]))+((save[i][1]-save[j][1])*(save[i][1]-save[j][1]));
				edges[i].add(j);
				edges[i].add(time);
				edges[j].add(i);
				edges[j].add(time);
			}
		}
		int[] time=new int[num];
		Arrays.fill(time, Integer.MAX_VALUE);
		time[0]=0;
		LinkedList<Integer> list=new LinkedList<>();
		list.add(0);
		while(!list.isEmpty()) {
			int node=list.removeFirst();
			ArrayList<Integer> al=edges[node];
			int s=al.size();
			for(int i=0; i<s; i+=2) {
				int tar=al.get(i);
				int dis=al.get(i+1);
				if(Math.max(time[node], dis)<time[tar]) {
					time[tar]=Math.max(time[node], dis);
					list.addLast(tar);
				}
			}
		}
		int ans=0;
		for(int i=0; i<num; i++) {
			ans=Math.max(ans, time[i]);
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		out.println(ans);
		out.close();
	}

}
