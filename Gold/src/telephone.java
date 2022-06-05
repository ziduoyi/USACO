import java.io.*;
import java.util.*;
public class telephone {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int N = Integer.parseInt(st.nextToken());
	    int K = Integer.parseInt(st.nextToken());
	    int[] type = new int[N];
	    Set<Integer>[] edges = new HashSet[K];
	    Set<Integer>[] map = new HashSet[K];
	    st = new StringTokenizer(br.readLine());
	    for(int i=0; i<K; i++)
	    	map[i]= new HashSet<>();
	    for(int i=0; i<K; i++)
	    	edges[i] = new HashSet<>();
	    for(int i=0; i<N; i++) {
	    	type[i] = Integer.parseInt(st.nextToken())-1;
	    	map[type[i]].add(i);
	    }
	    for(int i=0; i<K; i++) {
	    	String str = br.readLine();
	    	for(int j=0; j<K; j++) {
	    		if(str.charAt(j)=='1') 
	    			edges[i].add(j);    		
	    	}
	    }
	    int[][] dp = new int[N][K];
	    for(int i=0; i<N; i++)
	    	Arrays.fill(dp[i], 1000000000);
	    dp[0][type[0]] = 0;
	    LinkedList<int[]> list = new LinkedList<>();
	    list.add(new int[] {0,type[0]});
	    while(!list.isEmpty()) {
	    	int[] node = list.removeFirst();
	    	if(node[0]+1<N&&dp[node[0]][node[1]]+1<dp[node[0]+1][node[1]]) {
	    		dp[node[0]+1][node[1]] = dp[node[0]][node[1]]+1;
	    		list.add(new int[] {node[0]+1,node[1]});
	    	}
	    	if(node[0]-1>-1&&dp[node[0]][node[1]]+1<dp[node[0]-1][node[1]]) {
	    		dp[node[0]-1][node[1]] = dp[node[0]][node[1]]+1;
	    		list.add(new int[] {node[0]-1,node[1]});
	    	}
	    	if(edges[node[1]].contains(type[node[0]])) {
	    		if(dp[node[0]][node[1]]<dp[node[0]][type[node[0]]]) {
	    			dp[node[0]][type[node[0]]] = dp[node[0]][node[1]];
	    			list.add(new int[] {node[0], type[node[0]]});
	    		}
	    	}
	    }
	    int ans = 1000000000;
	    for(int i=0; i<K; i++)
	    	if(edges[i].contains(type[N-1]))
	    		ans = Math.min(ans, dp[N-1][i]);
	    if(ans !=1000000000)
	    	System.out.println(ans);
	    else
	    	System.out.println(-1);
	}

}
