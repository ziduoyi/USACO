package com.ex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Example {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner scanner= new Scanner(new File("./new 2.txt"));
		int num= scanner.nextInt();
		int[] arr = new int[num];
		for(int x =0; x<num; x++) {
			arr[x]=scanner.nextInt();
		}
	    int a= 0;
	    int b= arr.length-1;
	    int sl=arr[a];
	    int sr=arr[b];
		while(a<b) {
			if(sl<sr) {
				a++;
				sl=sl+arr[a];
			}
			else {
				b--;
				sr=sr+arr[b];
			}
		}
		if(sl-sr==0) {
			System.out.println("Te first pivot is at "+a);
		}
		else {
			System.out.println("-1");
		}
/*		int num2 =scanner.nextInt();
		while(num<0 || num2<0) {
			int i=10;
			if(num%i+num2%i<10) {
				i=i*10;
				continue;
			}
			else {
				System.out.println("no");
				return;
			}
		}
		
		System.out.println("yes");
		*/
/*		for(int i=0; i>-1; i++) {
			int save= num%10;
		    num=num/10;
		    answer=answer*10;
		    answer=answer+save;
		    if (num!=0) {
		    	continue;
		    }
		    else {
		    	System.out.println(answer);
		    	return;
		    }
		}
		
		int rev=0;
		while(num<0) {
			rev=rev*10+(num%10);
			num /= 10;
		}
		System.out.println(rev);
		*/
		
	}

}

