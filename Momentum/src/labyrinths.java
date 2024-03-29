import java.io.*;
import java.util.*;
public class labyrinths {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	
	    }
	}
	class Tarjan {
		  public int ind, sPtr, dsPtr, sccCount, N;
		  public boolean[] onStk;
		  public int[] low, pre, sStk, sccId, dStk, c, p;
		  ArrayList<Integer>[] g;
		  public Tarjan(ArrayList<Integer>[] g) {
		    this.g = g; this.N = g.length;
		    onStk = new boolean[N];
		    low = new int[N];
		    sStk = new int[N];
		    dStk = new int[N];
		    c = new int[N];
		    Arrays.fill(p = new int[N], -1);
		    Arrays.fill(sccId = new int[N], -1);
		    Arrays.fill(pre = new int[N], -1);
		    for (int i = 0; i < N; i++) if (pre[i] < 0) dfs(i);
		  }
		  void makeScc(int v) {
		    for (int t=-1; t!=v; onStk[t]=false) sccId[t=sStk[--sPtr]]=sccCount;
		    sccCount++;
		  }
		  void addToStack(int v, int parent) {
		    onStk[dStk[dsPtr++] = sStk[sPtr++] = v] = true;
		    pre[v] = low[v] = ind++;
		    p[v] = parent;
		  }
		  public void dfs(int rNode) {
		    addToStack(rNode, -1);
		    while (dsPtr != 0) {
		      int at = dStk[dsPtr-1];
		      if (c[at] == g[at].size()) {
		        dsPtr--;
		        if(p[at] != -1 && low[at] < low[p[at]]) low[p[at]] = low[at];
		        if (low[at] == pre[at]) makeScc(at);
		      } else {
		        int next = g[at].get(c[at]++);
		        if (pre[next] < 0) addToStack(next, at);
		        else if (onStk[next] && low[next] < low[at]) low[at] = low[next];
		      }
		    }
		  }
		}

}
