import java.io.*;
import java.util.*;
public class bintree1 {
	static boolean[][] tree;
	static HashMap<Integer, Integer>[] ele;
	static boolean[] completed;
	static int curr;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("bintree1.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("bintree1.out"))); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		if(N==1||N==2) {
			for(int i=0; i<M+1; i++)
				out.println(0);
			out.close();
			return;
		}
		curr = 0;
		tree = new boolean[N+1][3];
		ele = new HashMap[N+1];
		for(int i=0; i<N+1; i++)
			ele[i] = new HashMap<>();
		completed = new boolean[N+1];
		for(int i=1; i<=N; i++) {
			if(2*i>N) 
				completed[i] = true;
			else
				curr++;
		}
		out.println(curr);
		for(int i=1; i<=N; i++) {
			if(2*i>=N) 
				continue;
			ele[i].put( 2*i,0);
			ele[i].put( 2*i+1,1);
			
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a!=b&&a*2!=b&&a*2+1!=b&&b*2!=a&&b*2+1!=a)
				checkPar(a,b, N);
			out.println(curr);
		}
		out.close();
		
	}
	static void checkPar(int node1, int node2, int N) {
		while(node1!=node2) {
			if(node1<node2) {
				int next = node2;
				if(next%2==0)
					next/=2;
				else
					next = (node2-1)/2;
				if(next==node1)break;
				if(next==1||next*2>=N) {
					if(!completed[next]) {
						completed[next] = true;
						curr--;
					}
				}
				else {
					int start = -1;
					int end = -1;
					if(ele[next].containsKey(node1))
						start = ele[next].get(node1);
					else
						start = 2;
					if(ele[next].containsKey(node2))
						end = ele[next].get(node2);
					else
						end = 2;
					tree[next][start] = true;
					tree[next][end] =true;
					if(tree[next][0]&&tree[next][1]&&tree[next][2]) {
						if(!completed[next]) {
							completed[next] = true;
							curr--;
						}
					}
				}
				node2 = next;
			}
			else {
				int next = node1;
				if(next%2==0)
					next/=2;
				else
					next = (node1-1)/2;
				if(next==node2)break;
				if(next==1||next*2>=N) {
					if(!completed[next]) {
						completed[next] = true;
						curr--;
					}
				}
				else {
					int start = -1;
					int end = -1;
					if(ele[next].containsKey(node1))
						start = ele[next].get(node1);
					else
						start = 2;
					if(ele[next].containsKey(node2))
						end = ele[next].get(node2);
					else
						end = 2;
					tree[next][start] = true;
					tree[next][end] =true;
					if(tree[next][0]&&tree[next][1]&&tree[next][2]) {
						if(!completed[next]) {
							completed[next] = true;
							curr--;
						}
					}
				}
				node1 = next;
			}
		}
	}
}
