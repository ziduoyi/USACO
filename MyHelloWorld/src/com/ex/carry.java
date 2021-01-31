package com.ex;

public class carry {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String s1="hello"
		
		int num1=4523, num2=5386;
		
		// return if there is a carray for num1 + num2
		while(num1 >0 && num2>0){
			int a = num1 % 10;
			int b = num2 % 10;
			
			if(a+b>=10) {
				System.out.println("there is a carry");
				return;
			}
			
			num1 = num1 /10 ;
			num2 = num2 /10;
		}
		System.out.println("there is No carry");
	}

}
