package com.lc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Ex1 {
	public static void main(String[] args) throws FileNotFoundException{
		
		Ex1 ex1 = new Ex1();
		ex1.secretCowCodeNR();
		
//		Scanner scanner = new Scanner(new File("test.txt"));
//		
//		int len = scanner.nextInt();
//		int[] nums = new int[len];
//		for(int i=0; i<nums.length;i++){
//			nums[i] = scanner.nextInt();
//		}
//		
//		for(int i=1; i<nums.length-1;i++){
//			int lsum = 0;
//			int rsum = 0;
//					
//			for(int j=0;j<i;j++){
//				lsum += nums[j];
//			}
//			for(int j=nums.length-1;j>i;j--){
//				rsum += nums[j];
//			}
//			if(lsum==rsum){
//				System.out.println("privot found: "+i);
//			}
//		}
		
		
//		for(int i=0;i<nums.length;i++){
//			System.out.print(nums[i]+",");
//		}
		//Arrays.sort(nums);
		
		//check if there is pivot index or not
		// 1 2 3 .. 100 2 5050		
//		System.out.println();
//		for(int i=0;i<nums.length;i++){
//			System.out.print(nums[i]+",");
//		}
		
		
		

		// check if a carry exists when adding num1 + num2
		

		
		
		
		
//		System.out.println(num);
//		// reverse the digits of num.
//		int rev=0;
//		while(num>0){
//			rev = rev *10 + (num % 10);
//			num /= 10;
//		}
//
//		System.out.println(rev);
	}
	
	
	//USACO 2017 January Contest, Silver	Problem 3. Secret Cow Code
	void secretCowCode(){
		String cow="cow";
		int N = 8;
		
		char c = parse(cow,10);
		
		int len = cow.length();
		int r = N%len;
		int d = N/len%len;
		
		//COW -> COWWCO
		String scc=cow;
		for(int i=0;i<d;i++){
			cow = cow.charAt(len-1) + cow.substring(0, len-1);
			scc = scc + cow;
		}
		System.out.print(scc.charAt(r));

	}
	
	public static char parse(String s, long index) {
		if(index < s.length()) {
			return s.charAt((int)index);
		}
		long length = s.length();
		while(2*length <= index) {
			length *= 2;
		}
		if(length == index) {
			return parse(s, length-1);
		}
		return parse(s, index - length - 1);
	}
	
	void secretCowCodeNR(){
		String cow="cow";
		int N = 8;

		long len = cow.length();

		while(len<N){
			len += len;
		}

		while(N>cow.length()){
			while(len>=N)
				len/=2;
			if(N==len+1)
				N=(int) len;
			else
				N=(int) (N-len-1);
		}

		System.out.println(cow.charAt(N-1));
	}
	
	
//USACO 2015 December Contest, Silver
//Problem 1. Switching on the Lights
	void lightsOn(){
		int N=3,M=6;
		
		
	}
	
}
