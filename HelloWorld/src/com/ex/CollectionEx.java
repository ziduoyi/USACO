package com.ex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class CollectionEx {
	public static void main(String args[]) throws FileNotFoundException {
		
		Scanner scanner = new Scanner(new File("./vector.txt"));
		Vector<String> v= new Vector<String>();
		while(scanner.hasNextLine()) {
			String tmp = scanner.nextLine();
			
			v.add(tmp);
		}
		System.out.println("Before sorted");
			for (int i=0; i<v.size();i++) {
				System.out.println("vector["+ i + "]=" + v.get(i));
			}
		v.sort(null);
		System.out.println("after sorted");
		for (int i=0; i<v.size();i++) {
			System.out.println("vector["+ i + "]=" + v.get(i));
		}
	

/*		
		Vector<Integer> vr = new Vector<Integer>();	//new Vector(10);
		
		for (int i=0; i<15; i++) {
			if(i%2==0)
				vr.add((i+15)%7);
			else
				vr.add((i+7)*(i%5)+i);
		}
		vr.sort(null);
		
		System.out.println("the numbers are:");
		for (int i=0; i<vr.size();i++) {
			System.out.println("vector["+ i + "]=" + vr.get(i));
		}*/
	}
}	
