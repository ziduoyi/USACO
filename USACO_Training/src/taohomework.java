import java.util.*;

public class taohomework {

	public static void main(String[] args) {
		//  Auto-generated method stub
		factorial (10);
	}
	public void fibo (int n)
	{
		int[] arr=new int[n];
		arr[1]=1;
		for (int c=2; c<n;c++ ) {
			int sum=arr[c-1]+arr[c-2];
			arr[c]=sum;
		}
		System.out.println(Arrays.toString(arr));
	}
	public void sumOdd (int n, int a)
	{
		for(int i=0; i<n; i++) {
			System.out.println(a);
			a+=2;
		}
	}

	public static int factorial (int n)
	{
		int T=1;
		for(int t=1;t<=n;t++) {
			T=T*t;
		}
		return T;
	}
}
