package com.ex;

public class Ex2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]arr= new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
		int moves = 0;
		int min=arr[0];
		for(int i=1; i<arr.length; i++) {
			if(arr[i]<min) {
				min=arr[i];
			}
		}
			for(int i= 0; i<arr.length; i++) {
				moves=moves+arr[i]-min;

			}
					

		
		System.out.println("The number of moves are " +moves);
	}
}
