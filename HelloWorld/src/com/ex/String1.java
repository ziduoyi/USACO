package com.ex;

public class String1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = "school";
		String str2 = "ool";
		/*int num3 =str1.length()-str2.length();
		str1= str1.substring(num3);
		Boolean same = str1.equals(str2);
		if(same==true) {
			System.out.println("The same endings in both strings are "+str2);
		}
		else {
			System.out.println("The endings in both strings are different");
		}
			*/

/*	int num=str1.lastIndexOf(str2);
		boolean isendwith = str1.endsWith(str2);
		int output = str1.indexOf(str2);
		if(output+ str2.length()==str1.length()) {
			str1= str1.substring(output);
			System.out.println("The same endings in both strings are "+str2);
		}
		else {
			System.out.println("The endings in both strings are different");
		}
		*/
/*		Boolean equal=str1.equals(str2);
		if(equal == true) {
			System.out.println("The two strings are the same ");
		}
		else {
			System.out.println("The two strings are different");
		}
		*/
		int var3 = str1.compareToIgnoreCase(str2);
		if(var3 == 0) {
			System.out.println("The two strings are the same ");
		}
		else {
			System.out.println("The two strings have a diference");
		}
	}
}
	
		
