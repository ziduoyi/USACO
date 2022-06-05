import java.util.*;
import java.io.*;
public class job {
	static int[][] root;
	static int[] ans;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int n = Integer.parseInt(br.readLine());
	    long[][] sums = new long[n+1][n+1];
	    for(int i=1; i<=n; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	for(int j=1; j<=n; j++) {
	    		sums[i][j] = Integer.parseInt(st.nextToken())+sums[i-1][j]+sums[i][j-1]-sums[i-1][j-1];
	    	}
	    }
	    long[][] joyce_qu = new long[n+1][n+1];
	    root = new int[n+1][n+1];
	    ans = new int[n+1];
		for(int k=1;k<=n;k++) {
			for(int i=0;i+k<=n;i++){
				joyce_qu[i][i+k]=1000000000000000000L;
				for(int l=i;l<i+k;l++)
				{
					long now=joyce_qu[i][l]+(sums[l][n]-sums[i][n])-(sums[l][l]-sums[i][l]-sums[l][i]+sums[i][i])+
							joyce_qu[l+1][i+k]+(sums[i+k][n]-sums[l+1][n])-(sums[i+k][i+k]-sums[l+1][i+k]-sums[i+k][l+1]+sums[l+1][l+1]);
					if(joyce_qu[i][i+k]>now)
					{
						joyce_qu[i][i+k]=now;
						root[i][i+k]=l;
					}
				}
			}
		}
		dfs(0,n,-1);
		for(int i=0; i<n; i++)out.write(ans[i]+" ");
		out.write("\n");
		out.flush();
		out.close();
	}
	static void dfs(int l,int r,int p)
	{
		if(l==r)return;
		int v=root[l][r];
		ans[v]=p+1;
		dfs(l,v,v);
		dfs(v+1,r,v);
	}
}
