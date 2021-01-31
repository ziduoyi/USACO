package com.ex;

public class Pivot {
	public static void main(String[] args) {
	int[] arr = new int[]{1,2,3,2,1 };
	    int i= 0;
	    int j= arr.length-1;
	/*for(int i=0; i<arr.length-1;i++) {
		int num1 = 0;
		int num2 = 0;
		for(int x=0; x<i; x++) {
			num1= num1+arr[x];
		}
		for(int y=i+1; y<arr.length;y++) {
			num2 = num2+arr[y];
		}
		if(num1==num2) {
			System.out.println("The first pivot is at " + i);
			return;
		}
	}
	System.out.println(-1);*/
    int sl=arr[i];
    int sr=arr[j];
	while(i<j) {
		if(sl<sr) {
			i++;
			sl=sl+arr[i];
		}
		else {
			j--;
			sr=sr+arr[j];
		}
	}
	if(sl-sr==0) {
		System.out.println("Te first pivot is at "+i);
	}
	else {
		System.out.println("-1");
	}
}
}