import java.util.*;
import java.io.*;
public class charrec {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			int times = 1;
			for(int j = 19; j>-1; j--) {
				arr[i] += times*(str.charAt(j)-'0');
				times*=2;
			}
		}
		int[] dict = new int[540];
		for(int i=0; i<540; i++) {
			String str = br.readLine();
			int times = 1;
			for(int j = 19; j>-1; j--) {
				dict[i] += times*(str.charAt(j)-'0');
				times*=2;
			}
		}
		int[] dp = new int[N+1];
		int[] prev = new int[N+1];
		char[] letter = new char[N+1];
		Arrays.fill(prev, -1);
		Arrays.fill(dp, 1000000000);
		dp[0] = 0;
		prev[0] = 0;
		letter[0] = ' ';
		for(int i=0; i<=N; i++) {
			if(dp[i] != 1000000000) {
				int start = i;
				if(i+20<=N) {
					int min = 1000000000;
					int save = -1;
					int end = i+20;
					for(int j=0; j<540; j+=20) {
						int count = 0;
						for(int k=start; k<end; k++) 
							count+=Integer.bitCount(arr[k]^dict[j+k-start]);
						if(count<min)
							save = j/20;
						min = Math.min(count, min);
					}
					if(dp[i]+min<dp[i+20]) {
						prev[i+20] = i;
						if(save ==0)
							letter[i+20] = ' ';
						else
							letter[i+20] = (char) ('a'-1+save); 
					}
					dp[i+20] = Math.min(dp[i+20], dp[i]+min);
				}
				if(i+19<=N) {
					int min = 1000000000;
					int save = -1;
					int end = i+19;
					for(int j=0; j<540; j+=20) {
						int count = 0;
						for(int k=start; k<end; k++) 
							count+=Integer.bitCount(arr[k]^dict[j+k-start]);
						if(count<min)
							save = j/20;
						min = Math.min(count, min);
						for(int k=j+17; k>j-1; k--) {
							count+=Integer.bitCount(arr[k-j+start+1]^dict[k]);
							count-=Integer.bitCount(arr[k-j+start]^dict[k]);
							if(count<min)
								save = j/20;
							min = Math.min(min, count);
						}
					}
					if(dp[i]+min<dp[i+19]) {
						prev[i+19] = i;
						if(save==0)
							letter[i+19] = ' ';
						else
							letter[i+19] = (char) ('a'-1+save);
					}
					dp[i+19] = Math.min(dp[i+19], dp[i]+min);
				}
				if(i+21<=N) {
					int min = 1000000000;
					int save = -1;
					int end = i+21;
					for(int j=0; j<540; j+=20) {
						int count = 0;
						for(int k = j; k<j+20; k++)
							count+=Integer.bitCount(arr[k-j+start]^dict[k]);
							if(count<min) 
								save = j/20;
							min = Math.min(count, min);
						for(int k=end-2; k>start-1; k--) {
							count+=Integer.bitCount(arr[k+1]^dict[k-start+j]);
							count-=Integer.bitCount(arr[k]^dict[k-start+j]);
								if(count<min)
									save = j/20;
								min = Math.min(min, count);
						}
					}
					if(dp[i]+min<dp[i+21]) {
						prev[i+21] = i;
						if(save==0)
							letter[i+21] = ' ';
						else
							letter[i+21] = (char) ('a'-1+save);
					}
					dp[i+21] = Math.min(dp[i+21], dp[i] + min);
				}
			}
		}
		int last = dp[N];
		int pos = N;
		String str = "";
		while(prev[pos]!=pos) {
			if(last-dp[pos]<=120)
				str+=letter[pos];
			else
				str+='?';
			last = dp[pos];
			pos = prev[pos];
		}
		String ans = "";
		for(int i=str.length()-1; i>-1; i--)
			ans+=str.charAt(i);
		System.out.println(ans);
	}
	
}
