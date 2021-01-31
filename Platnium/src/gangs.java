import java.io.*;
import java.util.*;
public class gangs {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[M];
		for(int i=0; i<M; i++)
			arr[i] = Integer.parseInt(br.readLine());
		for(int i=1; i<M; i++) {
			if(2*arr[i]>=N) {
				System.out.println("NO");
				return;
			}
		}
		if(arr[0]==1) {
			if(N%2==0) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
		int ans1 = maxcows(arr);
		System.out.println(ans1);
		int[] ans2 = new int[N];
		Arrays.fill(ans2, 1);
		int end = N-arr[0];
		int start =0;
		int[] control = new int[] {0, 0};// gang type, # of that type 
		if(end%2==1) {
			start++;
			end++;
			control[1] = 1;
			arr[0]--;
		}
		for(int i=start; i<end; i++) {
			for(int j=1; j<M; j++) {
				if(arr[j]<=0)
					continue;
				if(control[1]==0) {
					control[0] = j;
					control[1] =1;
				}
				else {
					if(control[0]==j)
						control[1]++;
					else 
						control[1] --;
				}
				arr[j]--;
				arr[control[0]]+=control[1];
				int com = maxcows(arr);
				arr[control[0]]-=control[1];
				if(com==ans1) {
					ans2[i] = j+1;
					break;
				}
				arr[j]++;
				if(control[0]==j)
					control[1]--;
				else
					control[1]++;
			}
		}
		for(int i=0; i<N; i++)
			System.out.println(ans2[i]);
	}
	static int maxcows(int[] arr) {
		int s1 =0;
		for(int i=0; i<arr.length; i++)
			s1+=arr[i];
		for(int i=1; i<arr.length; i++) {
			if(2*arr[i]>=s1)
				return -1;
		}
		int ans =arr[0];
		int sum=0;
		for(int i=1; i<arr.length; i++)
			sum+=arr[i];
		if(sum%2==1) {
			sum++;
			ans--;
		}
		for(int i=1; i<arr.length; i++) {
			if(2*arr[i]>sum) {
				ans -= (2*arr[i]-sum);
				break;
			}
		}
		return ans;
	}

}
