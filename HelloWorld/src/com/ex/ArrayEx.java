package com.ex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ArrayEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] values={1,5,9,67,4,8,2,88,23};
		ArrayList<Integer> al = new ArrayList<Integer>(Arrays.asList(values));
		Integer[] nums={1};
		ArrayList<Integer> clone = new ArrayList<Integer>(Arrays.asList(values));
		//al.add(0,3);
		//int pos= al.get(2);
		//al.remove(5);
		//al.add(5,10);
		//al.remove(2);
		//int pos = al.indexOf(2);
		//Collections.sort(al);
		//Arrays.sort(al.toArray());
		clone=al;
		System.out.println(al);
		System.out.println(clone);
		String num=Integer.toBinaryString(11);
		String num2=Integer.toString(89765,5);
		System.out.println(num2);
	}

}
