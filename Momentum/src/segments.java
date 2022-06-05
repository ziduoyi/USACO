import java.util.*;
import java.io.*;
 
public class segments {
    public static void main(String args[]) throws Exception {
      BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
      
      int T = Integer.parseInt(s.readLine());
      
      for (int t = 0; t < T; ++t) {
          int n = Integer.parseInt(s.readLine());
          
          String[] ss = s.readLine().split(" ");
          int[] a = new int[n + 5];
          for (int i = 0; i < n; ++i) {
              a[i + 1] = Integer.parseInt(ss[i]);
          }
          
          long[] sums = new long[n + 5];
          sums[n + 1] = 0;
          for (int i = n; i >= 1; --i) {
              sums[i] = sums[i + 1] + a[i];
          }
          
          int kmax = 1;
          while (kmax * (kmax + 1) / 2 < n) {
              ++kmax;
          }
          
          long[][] dp = new long[kmax + 2][n + 5];
          dp[1][n] = a[n];
          for (int i = n - 1; i >= 1; --i) {
              dp[1][i] = Math.max(a[i], dp[1][i + 1]);
          }
          
          int res = 1;
          for (int k = 2; k <= kmax; ++k) {
              for (int i = n - k; i >= 1; --i) {
                  if (sums[i] - sums[i + k] < dp[k - 1][i + k]) {
                      dp[k][i] = Math.max(sums[i] - sums[i + k], dp[k][i + 1]);
                  } else {
                      dp[k][i] = dp[k][i + 1];
                  }
                  if (dp[k][i] > 0) {
                      res = k;
                  }
              } 
          }
          
          System.out.println(res);
      }
    }
}
