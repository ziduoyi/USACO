package com.ex;

import java.util.Scanner;

public class Reverse {
	public static void main(String[] args) {
		// TODO Auto-generated method stub]

			
		System.out.println("\nplease input anumber");
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
	while( num!= 0) {
		num = scanner.nextInt();
		System.out.println("reversed number: " + reverseInt(num));
	}

	}
	static int reverseInt(int num) {
		int answer=0;
		while(num!=0) {
			answer=answer*10+num%10;
			num=num/10;
		}
		return answer;
	}
}