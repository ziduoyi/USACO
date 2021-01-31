import java.util.*;
import java.io.*;
public class snowboots {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("snowboots.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int B=Integer.parseInt(st.nextToken());
		int[] snow=new int[N];
		Integer[] ps=new Integer[N];
		st=new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
 			snow[i]=Integer.parseInt(st.nextToken());
			ps[i]=i;
		}
		Arrays.sort(ps, (a,b)->snow[a]-snow[b]);
		int[] boot=new int[B];
		Integer[] pb=new Integer[B];
		int[] step=new int[B];
		for(int i=0; i<B; i++) {
			st=new StringTokenizer(br.readLine());
			boot[i]=Integer.parseInt(st.nextToken());
			pb[i]=i;
			step[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(pb, (a,b)->boot[a]-boot[b]);
		int[] ans =new int[B];
		int[] root=new int[N];
		for(int i=0; i<N; i++) {
			root[i]=i;
		}
		int[] size =new int[N];
		int j=N-1;
		int maxs=0;
		boolean[] black=new boolean[N];
		for(int i=B-1; i>=0; i--) {
			int x=ps[j];
			int y=pb[i];
			while(j>=0&&snow[x]>boot[y]) {
				black[x]=true;
				maxs=Math.max(maxs, 1);
				size[x]=1;
				if(x>0&&black[x-1]) {
					int a1=find(root, x-1);
					maxs=Math.max(union(root, a1, x, size),maxs);
				}
				if(x<N-1&&black[x+1]) {
					int a1=find(root, x+1);
					int a2=find(root, x);
					maxs=Math.max(union(root, a1, a2, size),maxs);
				}
				j--;
				x=ps[j];
			}
			if(step[y]>maxs)
				ans[y]=1;
		}
		for(int i=0; i<B; i++) {
			out.println(ans[i]);
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
