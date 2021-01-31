import  java.io.*;
import java.util.*;
public class AdvancedSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr=new int[] {100, 45, 75, 90, 1000, 24, 2, 66};
		//invoke merge sort
		//mergesort(arr);
		int maxx=1000;
		radixsort(arr,maxx);
		System.out.println(Arrays.toString(arr));
	}
	//radix sort------------
	public static void countsort(int[] arr,int exp) {
	   int output[] = new int[arr.length]; // output array  
    	int count[] = new int[10]; 
        for (int i = 0; i < arr.length; i++) 
            count[(arr[i]/exp)%10]++; 
        for (int i = 1; i < 10; i++) 
            count[i] += count[i - 1]; 
        for (int i = arr.length - 1; i >= 0; i--) { 
            output[count[ (arr[i]/exp)%10 ] - 1] = arr[i]; 
            count[ (arr[i]/exp)%10 ]--; 
        } 
        arr=output.clone();
	}
	public static void radixsort(int[] arr,int maxx){            //maxx is the maximum element in the array
	
        for (int exp = 1; maxx/exp > 0; exp *= 10) 
            countsort(arr, exp); 
    }
	//end of radix sort--------------------------------
	
	
	//merge sort
	static int[] res;
	public static void mergesort(int[] arr) {
		res = new int[arr.length];
		mergesort(arr,0,arr.length-1);
	}

	public static void mergesort(int[] arr, int l, int h) {
		if(l==h) return;
		
		int m = (l+h)/2;
		mergesort(arr, l, m);
		mergesort(arr,m+1,h);
		merge(arr, l,m,m+1,h);
	}
	private static void merge(int[] arr, int a1, int b1, int a2, int b2) {
		int i=a1, j=a2,k=a1;
		
		while(i<=b1 && j<=b2) {
			//res[k++]=arr[i]>arr[j]?arr[i++]:arr[j++];
			if(arr[i]<arr[j])
				res[k++]=arr[i++];
			else
				res[k++]=arr[j++];
		}
		if(i<=b1){
			while(i<=b1)
				res[k++]=arr[i++];
		}else
			while(j<=b2)
				res[k++]=arr[j++];
		for(i=a1;i<=b2;i++)
			arr[i]=res[i];
	}
	
}
