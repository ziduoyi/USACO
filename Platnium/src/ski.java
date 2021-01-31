import java.util.*;
public class ski {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		int S = scanner.nextInt();
		int N = scanner.nextInt();
		int[][] classes = new int[S+1][3];
		for(int i=1; i<=S; i++) {
			classes[i][0] = scanner.nextInt();
			classes[i][1] = scanner.nextInt();
			classes[i][2] = scanner.nextInt()-1;
		}
		Arrays.sort(classes,(a,b)->{if(a[0]!=b[0]) 
			return a[0]-b[0];
			else return a[1]-b[1];
			});
		int[] slopes = new int[100];
		Arrays.fill(slopes, 1000000000);
		for(int i=0; i<N; i++) {
			int a = scanner.nextInt()-1;
			int b = scanner.nextInt();
			slopes[a] = Math.min(slopes[a],b);
		}
		for(int i = 1; i<100; i++) {
			slopes[i] = Math.min(slopes[i], slopes[i-1]);
		}
		int[] dp = new int[S+1];
		int ans =0;
		for(int i=0; i<S+1; i++) {
			for(int j =i+1; j<S+1; j++) {
				if(classes[i][0]+classes[i][1]>classes[j][0]) {
					continue;
				}
				if(slopes[classes[i][2]]!=1000000000) {
					dp[j] =Math.max(dp[j], dp[i] + (classes[j][0]-classes[i][0]-classes[i][1])/slopes[classes[i][2]]);
				}
			}
			if(slopes[classes[i][2]]!=1000000000) {
				ans = Math.max(ans, dp[i] + (T-classes[i][0]-classes[i][1])/slopes[classes[i][2]]);
			}
		}
		System.out.println(ans);
		
 	}

}
