import java.io.*;
import java.util.*;
public class mootube {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		BufferedReader br=new BufferedReader(new FileReader("mootube.in"));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int Q=Integer.parseInt(st.nextToken());
		int[][] edges=new int[N-1][3];
		for(int i=0; i<N-1; i++) {
			st=new StringTokenizer(br.readLine());
			edges[i][0]=Integer.parseInt(st.nextToken())-1;
			edges[i][1]=Integer.parseInt(st.nextToken())-1;
			edges[i][2]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(edges, (a,b)->b[2]-a[2]);
		int[] root = new int [N];
		int[] size = new int [N];
		int[] ans = new int [Q];
		int[][] que=new int[Q][3];
		for(int i=0; i<Q; i++) {
			st=new StringTokenizer(br.readLine());
			que[i][0]=Integer.parseInt(st.nextToken());
			que[i][1]=Integer.parseInt(st.nextToken())-1;
			que[i][2]=i;
		}
		Arrays.sort(que, (a,b)->b[0]-a[0]);
		for(int i=0; i<N; i++) {
			root[i]=i;
		}
		int pos=0;
		int old=0;
		for(int i=0; i<N-1; i++) {
			if(pos<Q) {
				if(edges[i][2]<que[pos][0]) {
					int par=find(root, que[pos][1]);
					ans[que[pos][2]]=size[par];
					i=old;
					pos++;
					continue;
				}
			}
			int r1=find(root, edges[i][0]);
			int r2=find(root, edges[i][1]);
			size[r1]=Math.max(size[r1], 1);
			size[r2]=Math.max(size[r2], 1);
			int keep=union(root, r1, r2, size);
			size[r2]=keep;
			root[r1]=r2;
			old=i;
		}
		for(int i=pos; i<Q; i++) {
			ans[que[i][2]]=N;
		}
		for(int i=0; i<Q; i++) {
			if(ans[i]!=0)
				out.println(ans[i]-1);
			else
				out.println(0);
		
		}
		out.close();
	}
	static int find(int[] root, int x) {
		int n=x;
		while(n!=root[n]) 
			n=root[n];
		root[x]=n;
		return n;
	}
	static int union(int[] root, int a, int b, int[] size) {
		size[b]=size[b]+size[a];
		root[a]=b;
		return size[b];
	}
}
