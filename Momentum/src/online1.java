
public class online1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=1;
		int b=3;
		int n=1000000;
		int[] arr=new int[n];
		arr[0]=1;
		arr[1]=3;
		for(int i=2; i<n; i++) {
			long sum=arr[i-1]+arr[i-2];
			sum%=1000000007;
			arr[i]=(int) sum;
		}
		System.out.println(arr[n-1]);
		int s=3;
		int[] nums=new int[] {1,3,4};
		int sum=5;
		int[] dp=new int[1000001];
		dp[0]=1;
		for(int i=0; i<dp.length; i++) {
			for(int j=0; j<nums.length; j++) {
				int x=i+nums[j];
				if(x<dp.length) {
					dp[x]+=dp[i];
					dp[x]%=1000000007;
				}
			}
		}
		System.out.println(dp[dp.length-1]);
	}

}
