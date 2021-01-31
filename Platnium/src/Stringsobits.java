import java.io.*;
import java.util.*;
public class Stringsobits {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int L = scanner.nextInt();
		long I = scanner.nextLong();
		long tol =I;
		char[] stuff = new char[N];
		int ans =0;
		int used =L;
		for(int i=0; i<N; i++) {
			ans =0;
			long curr = count(N-i-1, used+1);
			if(curr<tol) {
				used--;
				ans +=1;
				tol-=curr;
			}
			stuff[i] =(char) ('0'+(ans%10));
		}
		String print = new String(stuff);
		System.out.println(print);
	}
	static long count(int left, int ones) {
		long[][] dp = new long[left][ones];
		for(int i=0; i<left; i++) {
			for(int j = i; j<ones; j++) {
				if(i==0) {
					dp[i][j]=1;
					continue;
				}
				if(j==0) {
					dp[i][j]=1;
					continue;
				}
				dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
			}
		}
		long sum =0;
		for(int i=ones; i>0; i--) {
			if(left-i>0)
				sum += dp[left-i-1][i-1];
		}
		return sum;
	}
}
