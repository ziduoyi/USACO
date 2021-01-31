import java.io.*;
import java.util.*;


public class superboot {

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("snowboots.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int B=Integer.parseInt(st.nextToken());

		int[] ds=new int[N];
		Integer[] inds=new Integer[N];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++){
			ds[i]=Integer.parseInt(st.nextToken());
			inds[i]=i;
		}
		
		int[] db = new int[B];
		int[] steps = new int[B];
		Integer[] indb=new Integer[B];
		for(int i=0; i<B; i++) {
			st=new StringTokenizer(br.readLine());
			db[i]=Integer.parseInt(st.nextToken());
			steps[i]=Integer.parseInt(st.nextToken());
			indb[i]=i;
		}
		
		Arrays.sort(inds,(a,b)->ds[a]-ds[b]);
		Arrays.sort(indb, (a,b)->db[a]-db[b]);
		
/*		//Option 1: next & prev
		int[] next = new int[N];
		int[] prev = new int[N];
		for(int i=0;i<N;i++){
			next[i] = i+1; 
			prev[i] = i-1;
		}
		
		int[] ans = new int[B];
		int j = N-1;
		int maxStep = 1;
		for(int i=B-1;i>=0;i--){
			int boot = indb[i];
			while(j >= 0 && ds[inds[j]] > db[boot]){
				int cur = inds[j];
				next[prev[cur]] = next[cur];
				prev[next[cur]] = prev[cur];
				maxStep = Math.max(maxStep, next[cur] - prev[cur]);
				j--;
			}
			if(maxStep <= steps[boot])
				ans[boot]=1;
		}
*/
		//Option 2: union find
		int[] ans = new int[B];
		int[] root = new int[N];
		int[] size = new int[N];
		boolean[] black = new boolean[N];

		Arrays.fill(size,1);
		for(int i=0;i<N;i++)
			root[i]=i;

		int maxSize = 0;
		int j=N-1;
		for(int i=B-1;i>=0;i--){
			int x=inds[j],y = indb[i];
			while(j>=0 && ds[x] > db[y]){
				black[x]=true;
				maxSize=Math.max(maxSize,1);
				if(x>0 && black[x-1])
					maxSize=Math.max(maxSize, union(root,size,x,find(root,x-1)));
				if(x<N-1 && black[x+1])
					maxSize=Math.max(maxSize,union(root,size,find(root,x),find(root,x+1)));
				x=inds[--j];
			}
			if(steps[y]>maxSize)
				ans[y]=1;
		}

		
		//print answers
		for(int i=0;i<B;i++)
			out.println(ans[i]);

		out.close();
	}
	static int find(int[] root, int x){
		int n=x;
		while(n!=root[n])
			n=root[n];
		root[x]=n;
		return n;
	}
	static int union(int[] root, int[] size, int x, int y){
		root[y]=x;
		size[x] += size[y];
		return size[x];
	}
}
