import java.util.*;
import java.io.*;
public class atlarge {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("atlarge.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("atlarge.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int nodes=Integer.parseInt(st.nextToken());
		int start=Integer.parseInt(st.nextToken())-1;//sub one is used
		ArrayList<Integer>[] edges=new ArrayList[nodes];
		for(int i=0; i<nodes; i++) {
			edges[i]=new ArrayList<>();
		}
		for(int i=0; i<nodes-1; i++) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken())-1;
			int y=Integer.parseInt(st.nextToken())-1;
			edges[x].add(y);
			edges[y].add(x);
		}
		boolean[] visit=new boolean[nodes];
		visit[start]=true;
		int steps=0;
		int need=0;
		LinkedList<Integer> list=new LinkedList<>();
		list.add(start);
		while(!list.isEmpty()) {
			int z=list.size();
			for(int j=0; j<z; j++) {
			int n=list.removeFirst();
			ArrayList<Integer> al=edges[n];
			LinkedList<Integer> use=new LinkedList<>();
			int t=al.size();
			for(int i=0; i<t; i++) {
				if(!visit[al.get(i)]) {
					use.add(al.get(i));
				}
			}
				int s=use.size(); 
				if(s==0) {
					need++;
					continue;
				}
				if(s==1) {
					int a=use.removeFirst();
					visit[a]=true;
					list.add(a);
					continue;
				}
				if(s>1) {
					boolean[] vis=visit.clone();
					LinkedList<Integer> tars=new LinkedList<>();
					tars.add(n);
					int take=0;
					boolean b=false;
					while(!tars.isEmpty()) {
						int p=tars.size();
						for(int k=0; k<p; k++) {
							int a=tars.removeFirst();
							ArrayList<Integer> f=edges[a];
							int o=0;
							ArrayList<Integer> l=new ArrayList<>();
							int d=f.size();
							for(int h=0; h<d; h++) {
								if(!vis[f.get(h)])
									l.add(f.get(h));
							}
							int m=l.size();
							if(l.size()==0) {
								b=true;
								break;
							}
							for(int i=0; i<m; i++) {
								int c=l.get(i);
								if(!vis[c]) {
									tars.add(c);
									vis[c]=true;
								}
							}
						}
						if(b)
							break;
						take++;
					}
					if(take>steps) {
						for(int i=0; i<s; i++) {
							visit[use.get(i)]=true;
						}
						list.addAll(use);
					}
					else {
						need++;
					}
				}
			}
			steps++;
		}
		out.println(need);
		out.close();
	}

}
