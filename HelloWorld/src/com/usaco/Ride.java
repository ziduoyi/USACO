/*
ID: ziduoyi1
LANG: JAVA
TASK: ride
*/
package com.usaco;

import java.io.*;
import java.util.*;


public class Ride {

	public static void main(String[] args) throws IOException { 
		int pro=1;
		// TODO Auto-generated method stub
		Scanner scanner= new Scanner(new File("ride.in"));
		String str=scanner.next();
		for(int i=0; i<str.length(); i++) {
			int num=str.charAt(i)-'A'+1;
			pro=pro*num;
		}
		int pro2=1;
		str=scanner.next();
		for(int i=0; i<str.length(); i++) {
			int num=str.charAt(i)-'A'+1;
			pro2=pro2*num;
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
		
		if(pro%47==pro2%47) {
			out.println("GO");
		}
		else {
			out.println("STAY");
		}
		out.close();
		
		
	}
}
