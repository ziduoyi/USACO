import java.io.*;
import java.util.*;
public class milkorder {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("milkorder.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] edges =new ArrayList[N];
		ArrayList<Integer>[] back =new ArrayList[N];
		for(int i=0; i<N; i++) {
			edges[i]=new ArrayList<>();
			back[i]=new ArrayList<>();
		}
		int[] numch=new int[N];
		int X=0;
		boolean[] notchild=new boolean[N];
		for(int i=0; i <M; i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());
			int a=Integer.parseInt(st.nextToken())-1;
			int start=a;
			int[] sub=new int[s];
			sub[0]=a;
			ArrayList<Integer> changed=new ArrayList<>();
			for(int j=0; j<s-1; j++) {
				int b=Integer.parseInt(st.nextToken())-1;
				sub[j+1]=b;
				edges[a].add(b);
				back[b].add(a);
				numch[b]++;
				if(notchild[b]!=true) {
					notchild[b]=true;
					changed.add(b);
				}
				a=b;
			}
			int[] gone=new int[N];
			LinkedList<Integer> list=new LinkedList<>();
			list.add(start);
			boolean b=false;
			while(!list.isEmpty()) {
				int node=list.removeFirst();
				ArrayList<Integer> al=edges[node];
				int l=al.size();
				for(int j=0; j<l; j++) {
					int dest=al.get(j);
					if(gone[dest]>numch[dest]) {
						b=true;
						break;
					}
					list.add(dest);
					gone[dest]++;
				}
				if(b==true)
					 break;
			}
			if(b==false)
				X++;
			else {
				for(int j=0; j<s-1; j++) {
					int z=edges[sub[j]].remove(edges[sub[j]].size()-1);
					back[z].remove(back[z].size()-1);
					numch[z]--;
				}
				for(int j=0; j<changed.size(); j++) {
					notchild[changed.get(j)]=false;
				}
				break;
			}
		}
		boolean[] been=new boolean[N];
		PriorityQueue<Integer> que=new PriorityQueue<>((a,b)->a-b);
		for(int i=0; i<N; i++) {
			if(notchild[i]==false) {
				que.add(i);
			}
		}
		int[] ans=new int[N];
		int c=0;
		while(!que.isEmpty()) {
			int node=que.poll();
			ArrayList<Integer> al=edges[node];
			int length=al.size();
			ans[c++]=node+1;
			been[node]=true;
			for(int i=0; i<length; i++) {
				int tar=al.get(i);
				ArrayList<Integer> check=back[tar];
				boolean b=false;
				for(int j=0; j<check.size(); j++) {
					if(been[check.get(j)]==false) {
						b=true;
						break;
					}
				}
				if(been[tar]==false&&b==false) {
					que.add(tar);
				}
			}
		}
		for(int i=0; i<N-1;i++)
			out.print(ans[i]+" ");
		out.print(ans[N-1]);
		out.close();
	}


}
