import java.util.*;
import java.io.*;
public class hps {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		BufferedReader br=new BufferedReader(new FileReader("hps.in"));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());
		String[] move=new String[N];
		for(int i=0; i<N; i++) {
			move[i]=br.readLine();
		}
		int[][][] dp=new int[N][K+1][3];//0=h 1=p 2=s
		for(int i=0; i<N; i++) {
			String win=getWin(move[i]);
			for(int j=0; j<K+1; j++) {
				for(int k=0; k<3; k++) {
					if(j==0) {
						boolean b=seeWin(k, win);
						if(b&&i>0)
							dp[i][j][k]=dp[i-1][j][k]+1;
						else {
							if(b&&i==0)
								dp[i][j][k]=1;
							else if(i>0)
								dp[i][j][k]=dp[i-1][j][k];
						}
					}
					else {
						if(i==0)
							break;
						else {
							int max=0;
							for(int l=0; l<3; l++) {
								if(l==k)continue;
								max=Math.max(dp[i-1][j-1][l], max);
							}
							boolean b=seeWin(k, win);
							if(b)
								dp[i][j][k]=Math.max(dp[i-1][j][k]+1, max+1);
							else
								dp[i][j][k]=Math.max(dp[i-1][j][k], max);
						}
					}
				}
			}
		}
		int ans=0;
		for(int i=0; i<3;i++) {
			for(int j=0; j<K+1; j++)
				ans=Math.max(ans, dp[N-1][j][i]);
		}
		out.println(ans);
		out.close();
	}
	static boolean seeWin(int i,String win) {
		if(i==0&& win =="H")
			return true;
		if(i==1&&win== "P")
			return true;
		if(i==2&&win =="S")
			return true;
		return false;
	}
	static String getWin(String str) {
		if(str.equals("H"))
			return "P";
		if(str.equals("P"))
			return "S";
		else
			return "H";
	}
}
