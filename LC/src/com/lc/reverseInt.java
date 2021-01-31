package com.lc;

import java.util.Scanner;

public class reverseInt {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("please input an integer:");
		int value = sc.nextInt();
		
		String str = Integer.toString(value);
		String reverse = "";
		if(str.startsWith("-")) {
			str=str.substring(1);
			reverse="-";
		}
		int len = str.length();
		for(int i=len-1; i>=0; i--) {
			char c = str.charAt(i);
			reverse = reverse + c;
		}
		
		long reverseInt = Long.getLong(reverse);
		System.out.println();
		System.out.println(reverseInt);
	}
	
}
