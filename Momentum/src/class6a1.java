import java.io.*;
import java.util.*;
public class class6a1 {
	public static void main(String[] args) throws IOException {
		int[] arr=new int[] {100,2,4,10,12,4,5,3,8};

		selectionSort(arr);
		
		//Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));

	}
	
	static void selectionSort(int[] arr) {
		for(int i=0; i<arr.length; i++) {
			int min=i;
			for(int j=i+1; j<arr.length; j++) {
				if(arr[min]>arr[j])
					min=j;
			}
			int save=arr[min];
			arr[min]=arr[i];
			arr[i]=save;
		}
		//return;
	}
	
	
	
	//n!= n*(n-1)*(n-2)*...*1
	static int factoral_recursive(int n) {
		if(n<=1) return 1;
		return n*factoral_recursive(n-1); 
	}
	
	//n! = 1 * 2 * ... *n
	static int factoral_bottom_up(int n) {
		int product=1;
		for(int i=1;i<=n;i++)
			product *= i;
		return product;
	}
	
	
	//f(0)=0, f(1)=1
	//f(10)=f(9)+f(8)=...
	static int fib(int n) {
		if(n==1)
			return 1;
		if(n==0)
			return 0;
		return fib(n-1)+fib(n-2);
	}
	
}