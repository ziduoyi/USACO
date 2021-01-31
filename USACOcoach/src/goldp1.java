import java.io.*;
import java.util.*;
public class goldp1 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("time.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("time.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] money = new int[N];
		st = new StringTokenizer(br.readLine());
	    for(int i=0; i<N; i++)
	    	money[i] = Integer.parseInt(st.nextToken());
	    int[][] dist = new int[N][1001];
	    ArrayList<Integer>[] edges = new ArrayList[N];
	    for(int i=0; i<N; i++)
	    	edges[i] = new ArrayList<>();
	    for(int i=0; i<M; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	int a = Integer.parseInt(st.nextToken())-1;
	    	int b = Integer.parseInt(st.nextToken())-1;
	    	edges[a].add(b);
	    }
	    LinkedList<int[]> list = new LinkedList<>();// curr, steps
	    int[] temp = new int[] {0,0};
	    list.add(temp);
	    while(!list.isEmpty()) {
	    	int[] arr = list.removeFirst();
	    	int curr = arr[0];
	    	int steps = arr[1];
	    	if(steps==1000)
	    		break;
	    	ArrayList<Integer> al = edges[curr];
	    	int n = al.size();
	    	for(int i=0; i<n; i++) {
	    		if(dist[curr][steps] + money[al.get(i)]>dist[al.get(i)][steps+1]) {
	    			dist[al.get(i)][steps+1] =dist[curr][steps] + money[al.get(i)];
	    			int[] add = new int[] {al.get(i),steps+1};
	    			list.add(add);
	    		}
	    	}
	    }
	    int ans = 0;
	    for(int i=0; i<1001; i++) {
	    	ans = Math.max(ans, dist[0][i]-C*i*i);
	    }
	    out.println(ans);
	    out.close();
	}

}
