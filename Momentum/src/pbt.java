import java.util.*;
import java.io.*;
public class pbt {
	static long[] dp;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		long l1=System.currentTimeMillis();
		int N=1000000000;//20 seconds around, max case
		//input
		dp=new long[250000001];
		dp[2]=1;
		dp[3]=2;
		dp[4]=3;
		dp[5]=4;
		long ans=getVal(N, N);
		System.out.println(ans);
		System.out.println(System.currentTimeMillis()-l1);
	}
	static int[] getUse(int num) {
		int stop=num/3;
		int add=num-stop;
		int[] temp=new int[] {stop, add};
		return temp;
	}
	static int[] getLen(int curr, int num) {
		int div=num/curr;
		div++;
		int next=num/div;
		int[] temp=new int[]{next+1, curr};
		return temp;
	}
	static long getVal(int curr, int num) {
		if(curr<=250000000) {
			if(dp[curr]>0)
				return dp[curr];
		}
		long val=0;
		int[] use=getUse(curr);
		val+=use[1];
		int i=use[0];
		while(i>1) {
			int[] get=getLen(i, num);
			long store=getVal((curr/i), num);
			val+=(store*((get[1]-get[0])+1));
			if(get[1]<=250000000)
				Arrays.fill(dp, get[0], get[1], store);
			i=get[0]-1;
		}
		if(curr<=250000000)
			dp[curr]=val;
		return val;
	}
}
