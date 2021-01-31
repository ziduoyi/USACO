package com.ex;

public class Ex3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[]arr= new int[] {1,1,2};
		int median= arr[arr.length/2-1];
		int moves=0;
		for(int i=0; i<arr.length; i++) {
			moves = moves +Math.abs(median-arr[i]);
		}
		System.out.println("The number of moves are "+moves);
	}

}
