import java.util.*;
import java.io.*;
public class lasers {
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		//long t1=System.currentTimeMillis();
		BufferedReader br=new BufferedReader(new FileReader("lasers.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("lasers.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int s1=Integer.parseInt(st.nextToken());
		int s2=Integer.parseInt(st.nextToken());
		int e1=Integer.parseInt(st.nextToken());
		int e2=Integer.parseInt(st.nextToken());
		int[][] store=new int[N][2];
		Map<Integer, Integer> map1=new HashMap<>();
		Map<Integer, Integer> map2=new HashMap<>();
		int v=0;
		int w=0;
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			store[i][0]=Integer.parseInt(st.nextToken());
			if(!map1.containsKey(store[i][0])) {
				map1.put(store[i][0], w);
				w++;
			}
			store[i][1]=Integer.parseInt(st.nextToken());
			if(!map2.containsKey(store[i][1])) {
				map2.put(store[i][1], v);
				v++;
			}
		}
		ArrayList<Integer>[] storex=new ArrayList[N];//verticle find
		ArrayList<Integer>[] storey=new ArrayList[N];//horizantal find
		for(int i=0; i<N; i++) {
			storex[i]=new ArrayList<>();
			storey[i]=new ArrayList<>();
		}
		for(int i=0; i<N; i++) {
			int a=store[i][0];
			int b=store[i][1];
			storex[map1.get(a)].add(b);
			storex[map1.get(a)].add(i+1);
			storey[map2.get(b)].add(a);
			storey[map2.get(b)].add(i+1);
		}
		int[][] dist=new int[N+2][2];
		for(int i=1; i<N+2; i++) {
			Arrays.fill(dist[i], 100000000);
		}
		
		int[] list = new int[4*N+8];
		int h=0,t=0;
		//LinkedList<int[]> list=new LinkedList<>();
		list[t++]=s1;
		list[t++]=s2;
		list[t++]=0;
		list[t++]=-1;
		while(h<t) {
			int x=list[h++];
			int y=list[h++];
			int curr=list[h++];
			int side =list[h++];
			if(side==-1&&map1.get(x)!=null||side==0&&map1.get(x)!=null) {
				ArrayList<Integer> al1=storex[map1.get(x)];
				int n0=al1.size();
				for(int i=0; i<n0; i+=2) {
					int next=al1.get(i);
					int n=al1.get(i+1);
					if(x==e1) {
						dist[N+1][0]=Math.min(dist[N+1][0], curr+1);
						break;
					}
					if(curr+1<dist[n][1]) {
						dist[n][1]=curr+1;
						list[t++]=x;
						list[t++]=next;
						list[t++]=curr+1;
						list[t++]=1;
					}
				}
				map1.remove(x);
			}
			if(side==-1&&map2.get(y)!=null||side==1&&map2.get(y)!=null) {
				ArrayList<Integer> al2=storey[map2.get(y)];
				int n1=al2.size();
				for(int i=0; i<n1; i+=2) {
					int next=al2.get(i);
					int n=al2.get(i+1);
					if(y==e2) {
						dist[N+1][0]=Math.min(dist[N+1][0], curr+1);
						break;
					}
					if(curr+1<dist[n][0]) {
						dist[n][0]=curr+1;
						list[t++]=next;
						list[t++]=y;
						list[t++]=curr+1;
						list[t++]=0;
					}
				}
				map2.remove(y);
			}
		}
		
		if(dist[N+1][0]!=100000000)
			out.println(dist[N+1][0]-1);
		else
			out.println(-1);
		out.close();
	}

}
