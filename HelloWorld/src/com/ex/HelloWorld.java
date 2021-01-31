package com.ex;

import java.io.*;
import java.util.Scanner;

public class HelloWorld {

	//private static final boolean False = 0;
	private static int i;
	private static int va[] = new int[100];
	private static int va2[]={100,99,98,97};

	public static void main(String[] args) throws IOException {
//		Geeks gk = new Geeks();
//		gk.testCase2();
//		gk.testCase3();
//
//		Leet lt = new Leet();
//		lt.testStringCharType();
//		lt.getPalindromicSubstring(null);
	
		
	
//		Bicycle b = new Bicycle(5,6);
//		System.out.println(b);
//		b.brake(1);
//		System.out.println(b);
//		
//		MountainBike m=new MountainBike(2,8);
//		System.out.println(m);
//		m.brake(1);
//		System.out.println(m);
		
		int data[] = {5,22,1,9,56,4,2,7,10};
		//sort data array
		//data.length;
		//selectSort(data);
		
		//Scanner scanner = new Scanner(System.in);
		//int num=scanner.nextInt();
		
	
		
		
		int data2[] = {5,22,1,100,15,9,56,4,2,7,10,23,18,28,35,27};
		/*insertSort(data2);
		
		*/int toSearch1=10;
		int toSearch2=50;
		int toSearch3=100;
		calPi( toSearch1);	
		pairFind(data2);	
/*
		bisearch(data2, toSearch1);	
		bisearch(data2,toSearch2);
		bisearch(data2, toSearch3);*/
		bubbleSort(data);
		bubbleSort(data2);
		//commanNum(data,data2);
		//evenNum(data2);
		//hayBales();
		
		

	}
	static void evenNum(int[] data2) {
		for(int i=0;i<data2.length-1; i++) {
			if(data2[i]%2==0) {
				System.out.println(data2[i]+" is a even number");
			}
			
		}
	}
	
	static void commanNum(int[] data, int[] data2) {	
		for(int i=0;i<data.length-1;i++) {
			for(int x =0; x<data2.length; x++) {
				if(data[i]==data2[x]) {
					System.out.println(data[i]+" is a number in both arrays");
				}
			}
		}
	}
	
	static void calPi(int number) {
		System.out.println("\nplease input anumber");
		Scanner scanner = new Scanner(System.in);
		int num=scanner.nextInt();
		double answer =0;
		double x = 1;
		for(i=1; i<num+1; i++) {
			if(i%2!=0) {
				answer=answer+1/x;
				x=x+2;
				}
			else {
				answer=answer-1/x;
				x=x+2;
			}
		}
		answer=answer*4;
		System.out.println("Your asked digits of pi are "+ answer);
	}
	

	static void bisearch(int[] data, int toSearch) {
		System.out.println("---> Binary search results:");
		int x = 0;
		int y= data.length-1;
		
		while( x<=y ) {
			int i = (x+y)/2;

			if(data[i]==toSearch) {
				System.out.println("The position of "+ toSearch+" is "+ i);
				return;
			}
			if(data[i]<toSearch) {
				x=i+1;
			}else{
				y=i-1;
			}
		}
		System.out.println(toSearch+" is not in the data list");
	}
		
	static void pairFind(int[] data2) {
		for(int x=0; x<data2.length-1; x++) {
			for(int y=x+1; y<data2.length-1; y++) {
				if(data2[x]+data2[y]==50) {
					System.out.println(data2[x]+" and "+data2[y]+" are pairs that add up to fifty");
				}
			}
		}
	}
	
	static void search(int[] data, int toSearch) {
		System.out.println("---> Normal search results:");
		
		for(int i= 0; i<data.length; i++) {
			if(data[i]==toSearch) {
				System.out.println("The position of "+ toSearch+" is "+ i);
				return;
			}
		}

		System.out.println(toSearch+" is not in the data list");

	}
	
	
	static void insertSort(int[] data) {
		for(int i=1; i<data.length; i++) {
			int insertpos = -1;
			int save = data[i];
			for(int j=0; j<i; j++) {
				if (data[j]>data[i]) {
					insertpos=j;
					break;
				}
			}
			if(insertpos == -1)
				continue;
			for(int k=i; k>insertpos; k--) {
				data[k]=data[k-1];
			}	

			data[insertpos]=save;
		}
		System.out.println();
		System.out.println("Insert Sorted:");
		for(i=0; i<data.length;i++)
			System.out.print( data[i] + ",");
	}
	
	static void bubbleSort(int[] data2) {//{5,22,1,100,15,9,56,4,2,7,10,23,18,28,35,27};
		int num=0;
		int save=0;
		for(int i=0; i<data2.length-1; i++) {
			num=0;
			for(int x=0; x<data2.length-1-i; x++) {
				if(data2[x]>data2[x+1]) {
					num++;
					save=data2[x];
					data2[x]=data2[x+1];
					data2[x+1]=save;
				}
			}

			if(num==0) {
				System.out.println("Swaped:");
				for(i=0; i<data2.length;i++)
					System.out.print( data2[i] + ",");
				return;
			}
			
		}
		System.out.println("Swaped:");
		for(i=0; i<data2.length;i++)
			System.out.print( data2[i] + ",");
	}

	static void selectSort(int[] data) {
		for(int i=0; i<data.length-1; i++){	//{1,22,5,9,56,4,2,7,10};
			int minPos = i;
			for(int j=i+1; j<data.length;  j++ ){
				if (data[j]<data[minPos]){
					minPos = j;
				}
			}
			int save=data[i];
			data[i]=data[minPos];
			data[minPos]=save;
		}
		System.out.println();
		System.out.println("Sorted:");
		for(i=0; i<data.length;i++)
			System.out.print( data[i] + ",");
	}



	static void hayBales() throws FileNotFoundException {
		////////-----------		
		Scanner scanner = new Scanner(new File("./input_data.txt"));
		//Scanner scanner1 = new Scanner(new FileInputStream("./input_data.txt"));
		//Scanner scanner2 = new Scanner(System.in);
		int average=0;
		int total=0;
		int answer=0;
		int numPiles=scanner.nextInt();
		if(numPiles>0){
			int[] piles = new int[numPiles];
			for(int x=0;x<numPiles;x++){
				piles[x]=scanner.nextInt();
			}
			
			for(int x=0;x<numPiles;x++){
				total = total + piles[x];
			}

			average=total/numPiles;
			System.out.println("the average is: "+average);
			
			
			for(int x=0; x<numPiles; x++){
				if(piles[x]>average ){
					answer+=piles[x]-average;
				}
			}	
			}
		System.out.println("The number of hay piles you need to move is "+answer+" piles");
	}
		
		
		
		////////------------------------------
/*		int result = 0;
		
		result = sumeven(1,100);
		result = sumodd(10000,100);
		result = sumeven(100,10);

		//result = sumeven(1,100);
		
		HelloWorld hw = new HelloWorld();
		
		hw.isPrime(73,11);
		
		String ss="this is walker Stattion";
		String included = ss.substring(10);

		result = shop(6);
		System.out.println("the result is: " + result);//Integer.toString(result)
		result = shop(18);
		System.out.println("the result is: " + result);
		result = shop(25);
		System.out.println("the result is: " + result);
		result = shop(100);
		System.out.println("the result is: " + result);
		
		//return;
	*/	

	
	 boolean isPrime(int num,int factor ){ // 11
		//int flag = 0;
		for(int x = 2; x <=num /2; x++){
			if(num%x==0){
				System.out.println(num+" is a composite number");
				return false ;
			}
		}

		System.out.println(num+" is a prime number");
		return true;
	}

		
	static int shop(int item){	
		int price=10;
		int result= 1;
		switch(item/10){
		case 0 :
			price = 10;
			result = item * price;
			break;
		case 1 :
			price = 8;
			result = item * price;
			break;
		case 2 :
			price = 6;
			result = item * price;
			break;
		case 3 :
			price = 4;
			result = item * price;
			break;
		default:
			price=3;
			result=item*price;
			break;
		}
		
		
		return result;
	}
	
	static int sumodd(int begin, int end){	//except 3 & 3 multiples
		int sum= 0;
		if(begin>end)
			System.out.println("You are stupid");
		else{
			for(int i=begin; i<=end; i++)
				if(i%3==0)
					continue;
				if(i%2!=0)
					sum+= i;
		}
		System.out.println("The sum of all the odd numbers are: " + sum);
		return sum;
	}
	
	
	static int sumeven(int begin, int end){
		

		
		//System.out.println("Hello World!");  /* ... */
		/*
		System.out.println("Welcome to my java programming world");
		
		int myScore = 8;
		myScore -= 1;	//myScore--
		
		double y = 2.3;
		
		String myStr = "Hello world,";
		String myStr2; 
		
		myStr2 = "ziduo";
		
		System.out.println(myStr +" "  +myStr2);
		
		
		int price=1;
		int discount=0;
		int finalprice=0;
		
		if(price > 50){
			discount=10;
		}else
			discount=5;
		finalprice= price-discount;
		
		System.out.println("discount is "+ discount);
		System.out.println("Your final price is " +finalprice);		
		*/
		int sum = 0;
		for(int i=begin;i<=end;i++){
			if(i%2>0){
				sum=sum;
			}else{
				sum += i;
				System.out.println(sum);
			}
		}
		System.out.println(sum);
		
		return sum;
	}
	
	
	/*		
	  int x=0,y=3,z=3;
		if(x==0){
			if(y>z)
				//System.out.println("s 1");
				if(z==y)
						System.out.println("S 2");
				else
					System.out.println("S 3");

		}else
			System.out.println("S 4");
			*/
}
