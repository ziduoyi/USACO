import java.io.*;
import java.util.*;
public class mooriokart {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("mooriokart.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mooriokart.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] edges = new ArrayList[N];
		for(int i=0; i<N; i++) edges[i] = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			edges[a].add(b);
			edges[a].add(c);
			edges[b].add(a);
			edges[b].add(c);
		}
		int K = 0;
		LinkedList<Integer> list = new LinkedList<>();
		LinkedList<Integer> fill = new LinkedList<>();
		boolean[] visit = new boolean[N];
		for(int i=0; i<N; i++) {
			if(!visit[i]) {
				visit[i] = true;
				fill.add(i);
				while(!fill.isEmpty()) {
					int ele = fill.removeFirst();
					ArrayList<Integer> al = edges[ele];
					int s = al.size();
					if(s==2)list.add(ele);
					for(int j=0; j<s; j+=2) {
						int tar = al.get(j);
						if(!visit[tar]) {
							fill.add(tar);
							visit[tar] = true;
							
						}
					}
				}
				list.removeLast();
				K++;
			}
		}
		long[] dp = new long[Y+2];
		long[] next = new long[Y+2];
		for(int i=0; i<K; i++) {
			int pos = list.removeFirst();
			fill.add(pos);
			visit[i] = false;
			while(!fill.isEmpty()) {
				int ele = fill.removeFirst();
				ArrayList<Integer> al = edges[ele];
				int s = al.size();
				for(int j=0; j<s; j+=2) {
					int tar = al.get(j);
					if(visit[tar]) {
						fill.add(tar);
						int cost = al.get(j+1);
						for(int k = Y+1; k>-1; k--) { //may cause %mod to screw things up
							if(dp[k]>0) {
								int stuff = k+(int)cost;
								dp[Math.min(Y+1, stuff)] = (dp[Math.min(Y+1, stuff)]+dp[k]*2)%1000000007;
							}
						}
						if(i==0)
							if(cost<Y+2)
								dp[(int)cost]+=2;
						visit[tar] = false;			
					}
				}
			}
			for(int j=0; j<Y+2; j++)
				dp[j] = next[j];
		}
		long ans = 0;
		for(int i = Math.max(0, Y-K*X); i<Y+2; i++) 
			ans += dp[i]*i;
		ans%=1000000007;
		out.println(ans);
		out.close();
	}

}
