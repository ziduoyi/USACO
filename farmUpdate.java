import java.io.*;
import java.util.*;
public class farmUpdate {
	static int[] root;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int N = Integer.parseInt(st.nextToken());
	    int Q = Integer.parseInt(st.nextToken());
	    int[] ans = new int[N];
	    Arrays.fill(ans, -1);
	    boolean[] activeEnd = new boolean[N];
	    Arrays.fill(activeEnd, true);
	    int[][] edge = new int[Q][2];
	    boolean[] onEnd = new boolean[Q];
	    LinkedList<int[]> list = new LinkedList<>();
	    int M = 0;
	    for(int i=0; i<Q; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	String str = st.nextToken();
	    	if(str.equals("A")) {
	    		edge[M][0] = Integer.parseInt(st.nextToken())-1;
	    		edge[M][1] = Integer.parseInt(st.nextToken())-1;
	    		onEnd[M++] = true;
	    	}
	    	if(str.equals("D")) {
	    		int p = Integer.parseInt(st.nextToken())-1;
	    		activeEnd[p] = false;
	    		list.add(new int[] {i, 1, p});
	    	}
	    	if(str.equals("R")) {
	    		int q= Integer.parseInt(st.nextToken())-1;
	    		onEnd[q] = false;
	    		list.add(new int[] {i, 0, q});
	    	}
	    }
	    LinkedList<Integer>[] sets = new LinkedList[N];
	    for(int i=0; i<N; i++) {
	    	sets[i] = new LinkedList<>();
	    	sets[i].add(i);
	    }
	    boolean[] active = new boolean[N];
	    for(int i=0; i<M; i++) if(onEnd[i]) list.add(new int[] {Q, 0, i});
	    for(int i=0; i<N; i++) if(activeEnd[i]) list.add(new int[] {Q, 1, i});
	    root = new int[N];
	    for(int i=0; i<N; i++) root[i] = i;
	    while(!list.isEmpty()) {
	    	int[] use = list.removeLast();
	    	int time = use[0];
	    	int type = use[1];
	    	int pos = use[2];
	    	if(type==1) {
	    		int r = getRoot(pos);
	    		if(!active[r]) {
	    			for(int num: sets[r]) {
	    				active[num] = true;
	    				ans[num] = time;
	    			}
	    			sets[r].clear();
	    		}
	    	}
	    	else {
	    		int r1 = getRoot(edge[pos][0]);
	    		int r2 = getRoot(edge[pos][1]);
	    		if(active[r1]&&active[r2])continue;
	    		if(active[r1]) {
	    			for(int num: sets[r2]) {
	    				active[num] = true;
	    				ans[num] = time;
	    			}
	    			sets[r2].clear();
	    			root[r1] = r2;
	    		}
	    		else if(active[r2]) {
	    			for(int num: sets[r1]) {
	    				active[num] = true;
	    				ans[num] = time;
	    			}
	    			sets[r1].clear();	  
	    			root[r1] = r2;
	    		}
	    		else {
	    			if(sets[r1].size()>sets[r2].size()) {
	    				sets[r1].addAll(sets[r2]);
	    				sets[r2].clear();
	    				root[r2] = r1;
	    			}
	    			else {
	    				sets[r2].addAll(sets[r1]);
	    				sets[r1].clear();	 
	    				root[r1] = r2;
	    			}
	    		}
	    	}
	    }
	    for(int i=0; i <N; i++)out.write(ans[i]+"\n");
	    out.flush();
	}
	
	static int getRoot(int pos) {
		if(root[pos]==pos)return pos;
		return root[pos] = getRoot(root[pos]);
	}
}
