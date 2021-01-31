import java.util.*;
import java.io.*;
public class checklist {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("checklist.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int H=Integer.parseInt(st.nextToken());
		int G=Integer.parseInt(st.nextToken());
		int[][] h=new int[H+1][2];
		int[][] g=new int[G+1][2];
		for(int i=1; i<=H; i++) {
			st=new StringTokenizer(br.readLine());
			h[i][0]=Integer.parseInt(st.nextToken());
			h[i][1]=Integer.parseInt(st.nextToken());
		}
		for(int i=1; i<=G; i++) {
			st=new StringTokenizer(br.readLine());
			g[i][0]=Integer.parseInt(st.nextToken());
			g[i][1]=Integer.parseInt(st.nextToken());
		}
		long[][] dp=new long[H+1][G+1];
		for(int i=1;i<H;i++)
			Arrays.fill(dp[i],20000000000l);

		long[][] dp2=new long[H+1][G+1];
		for(int i=1;i<H;i++)
			Arrays.fill(dp[i],20000000000l);

		//boolean lastH = true;
		for(int i=1;i<=H;i++) {
			dp[i][0]=0;
			dp2[i][0]=0;
			for(int j=1;j<=G;j++) {
				if(i>1) {
					long a = dp[i-1][j]+getDist(h[i],h[i-1]);
					long b = dp2[i-1][j]+getDist(h[i],g[j]);
					dp[i][j]=Math.min(a,b);
				}
				long a = dp[i][j-1]+getDist(h[i],g[j]);
				long b = j>1?dp2[i][j-1]+getDist(g[j],g[j-1]):20000000000l;
				dp2[i][j]=Math.min(a,b);
			}
		}
		long res = dp[H][G];//lastH?dp[H-1][G]+getDist(h[H-1],h[H]):dp[H-1][G]+getDist(g[G],h[H]);
		System.out.println(res);
		out.println(res);
		out.close();
	/*
		int[][] hol=new int[H][2];
		int[][] gue=new int[G+1][2];
		for(int i=0; i<H; i++) {
			st=new StringTokenizer(br.readLine());
			hol[i][0]=Integer.parseInt(st.nextToken());
			hol[i][1]=Integer.parseInt(st.nextToken());
		}
		for(int i=1; i<G+1; i++) {
			st=new StringTokenizer(br.readLine());
			gue[i][0]=Integer.parseInt(st.nextToken());
			gue[i][1]=Integer.parseInt(st.nextToken());
		}
		
		
		long[][][] dp=new long[H-1][G+1][2];
		long ans=0;
		for(int i=0; i<H-1; i++) {
			for(int j=0; j<G+1; j++) {
				for(int k=0; k<2; k++) {
					if(i==0&&j==0)
						continue;
					long com1=Integer.MAX_VALUE;
					long com2=Integer.MAX_VALUE;
					if(k==0) {
						if(i-1>=0) {
							com1=dp[i-1][j][0]+getDist(hol[i-1],hol[i]);
							if(j-1>=0) {
								com2=dp[i-1][j][1]+getDist(gue[j], hol[i]);
							}
						}
						dp[i][j][0]=Math.min(com1, com2);
					}
					else {
						if(j-2>=0) {
							com1=dp[i][j-1][1]+getDist(gue[j-1],gue[j]);
						}
						if(i>=0&&j>0) {
							com2=dp[i][j-1][0]+getDist(hol[i], gue[j]);
						}
						dp[i][j][1]=Math.min(com1, com2);
					}
				}
			}
		}
		ans=Math.min(dp[H-2][G][0]+getDist(hol[H-2], hol[H-1]), dp[H-2][G][1]+getDist(gue[G], hol[H-1]));
		out.println(ans);
		out.close();
		*/
	}
	static int getDist(int[] a, int[] b) {
		return (a[1]-b[1]) * (a[1]-b[1]) + (a[0]-b[0]) * (a[0]-b[0]);
	}
}


/*
	
int[][] h=new int[H+1][2];
int[][] g=new int[G+1][2];
for(int i=1; i<=H; i++) {
	st=new StringTokenizer(br.readLine());
	h[i][0]=Integer.parseInt(st.nextToken());
	h[i][1]=Integer.parseInt(st.nextToken());
}
for(int i=1; i<=G; i++) {
	st=new StringTokenizer(br.readLine());
	g[i][0]=Integer.parseInt(st.nextToken());
	g[i][1]=Integer.parseInt(st.nextToken());
}
long[][] dp=new long[H+1][G+1];
for(int i=1;i<H;i++)
	Arrays.fill(dp[i],20000000000l);

long[][] dp2=new long[H+1][G+1];
for(int i=1;i<H;i++)
	Arrays.fill(dp[i],20000000000l);

//boolean lastH = true;
for(int i=1;i<=H;i++) {
	dp[i][0]=0;
	dp2[i][0]=0;
	for(int j=1;j<=G;j++) {
		if(i>1) {
			long a = dp[i-1][j]+getDist(h[i],h[i-1]);
			long b = dp2[i-1][j]+getDist(h[i],g[j]);
			dp[i][j]=Math.min(a,b);
		}
		long a = dp[i][j-1]+getDist(h[i],g[j]);
		long b = j>1?dp2[i][j-1]+getDist(g[j],g[j-1]):20000000000l;
		dp2[i][j]=Math.min(a,b);
	}
}
long res = dp[H][G];//lastH?dp[H-1][G]+getDist(h[H-1],h[H]):dp[H-1][G]+getDist(g[G],h[H]);
System.out.println(res);
out.println(res);
out.close();
*/
