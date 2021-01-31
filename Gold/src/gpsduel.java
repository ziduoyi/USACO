import java.util.*;
import java.io.*;
public class gpsduel {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("gpsduel.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gpsduel.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int num=Integer.parseInt(st.nextToken());
		int paths=Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] edge1=new ArrayList[num];
		ArrayList<Integer>[] edge2=new ArrayList[num];
		for(int i=0; i<num; i++) {
			edge1[i]=new ArrayList<>();
			edge2[i]=new ArrayList<>();
		}
		for(int i=0 ; i<paths; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			edge1[b].add(a);
			edge2[b].add(a);
			edge1[b].add(c);
			edge2[b].add(d);
		}
		int[] time1=new int[num];
		Arrays.fill(time1, 100000000);
		LinkedList<Integer> list=new LinkedList<>();
		time1[num-1]=0;
		list.add(num-1);
		while(!list.isEmpty()) {
			int node=list.removeFirst();
			ArrayList<Integer> al=edge1[node];
			int s=al.size();
			for(int i=0; i<s; i+=2) {
				int tar=al.get(i);
				int time=al.get(i+1);
				if(time1[node]+time<time1[tar]) {
					time1[tar]=time+time1[node];
					list.add(tar);
				}
			}
		}
		int[]time2=new int[num];
		Arrays.fill(time2, 100000000);
		time2[num-1]=0;
		list.add(num-1);
		while(!list.isEmpty()) {
			int node=list.removeFirst();
			ArrayList<Integer> al=edge2[node];
			int s=al.size();
			for(int i=0; i<s; i+=2) {
				int tar=al.get(i);
				int time=al.get(i+1);
				if(time2[node]+time<time2[tar]) {
					time2[tar]=time+time2[node];
					list.add(tar);
				}
			}
		}
		ArrayList<Integer>[] use=new ArrayList[num];
		for(int i=0; i<num; i++) {
			use[i]=new ArrayList<>();
		}
		for(int i=0; i<num; i++) {
			ArrayList<Integer> al1=edge1[i];
			ArrayList<Integer> al2=edge2[i];
			int s=al1.size();
			for(int j=0; j<s; j+=2) {
				int count =0;
				use[i].add(al1.get(j));
				if(al1.get(j+1)!=Math.abs((time1[i]-time1[al1.get(j)])))						
					count++;
				if(al2.get(j+1)!=Math.abs((time2[i]-time2[al1.get(j)])))
					count++;
				use[i].add(count);
			}
		}
		int[] error=new int[num];
		Arrays.fill(error, 100000);
		list.add(num-1);
		error[num-1]=0;
		while(!list.isEmpty()) {
			int node=list.removeFirst();
			ArrayList<Integer> al=use[node];
			int s=al.size();
			for(int i=0; i<s; i+=2) {
				int tar=al.get(i);
				int time=al.get(i+1);
				if(error[node]+time<error[tar]) {
					error[tar]=time+error[node];
					list.add(tar);
				}
			}
		}
		out.println(error[0]);
		out.close();
	}

}
