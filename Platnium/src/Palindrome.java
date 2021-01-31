import java.util.*;
public class Palindrome {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		char[] str = scanner.next().toCharArray();
		int[] dp1 = new int[N];
		int[] dp2 = new int[N];
		for(int i=0; i<N-1; i++) {
			if(str[i] !=str[i+1])
				dp2[i] =1;
		}
		for(int len =3; len<=N; len++) {
			int[] dp3 = new int[N];
			for(int i=0; i+len-1<N; i++) {
				int j = i+len-1;
				if(str[i] == str[j])
					dp3[i] = dp1[i+1];
				else
					dp3[i] = Math.min(dp2[i]+1, dp2[i+1]+1);
			}
			dp1 = dp2.clone();
			dp2 = dp3.clone();
		}
		System.out.println(dp2[0]);
	}

}
