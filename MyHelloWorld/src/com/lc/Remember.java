package com.lc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class Remember {
//			https://wizardforcel.gitbooks.io/java8-tutorials/content/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Remember rmb = new Remember();

		Integer x = 56;
		rmb.testPassing(x);
		System.out.println(x);
		
		String s = "an apple";
		rmb.testPassing(s);
		System.out.println(s);
		
		StringBuilder sbd = new StringBuilder("an apple");
		rmb.testPassing(sbd);
		System.out.println(sbd.toString());
	}

	void testPassing(Integer n){
		n += 100;
		System.out.println(n);
	}

	void testPassing(String s){
		s.replace('a', 'A');
		System.out.println(s);
		s=s.replace('a', 'A');
		System.out.println(s);
	}

	void testPassing(StringBuilder sbd){
		sbd.reverse();
		System.out.println(sbd);
	}
	
//#406	Queue Reconstruction by Height
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>(){
        	public int compare(int[] p1,int[] p2){
        	     //  return -1 if l should be before r	
        	     //  return 1 if r should be before l   
        	     //  return 0 otherwise
//        		if(p1[0]>p2[0]) return -1;
//        		else if(p1[0]<p2[0]) return 1;
//        		else if(p1[0]==p2[0] && p1[1]<p2[1]) return -1;
//        		else return 1;
        		return p1[0]==p2[0]? Integer.compare(p1[1], p2[1]):Integer.compare(p2[0], p1[0]);
        	}
        });
        
        LinkedList<int[]> list = new LinkedList<>();
        for(int[] p : people){
        	list.add(p[1],p);
        }
        return list.toArray(new int[list.size()][2]);
    }

//    基本语法:
//    	(parameters) -> expression
//    	或
//   	(parameters) ->{ statements; }
    public int[][] reconstructQueue2(int[][] people) {
    	//Collections.sort(people, (a, b) -> a.name.compareToIgnoreCase(b.name));
        Arrays.sort(people, (p1,p2) -> p1[0]==p2[0]? Integer.compare(p1[1], p2[1]):Integer.compare(p2[0], p1[0]));
        Arrays.sort(people, (p1,p2) -> {if(p1[0]>p2[0]) return -1;else if(p1[0]<p2[0]) return 1;else if(p1[0]==p2[0] && p1[1]<p2[1])
        	return -1;else return 1;});
        
        LinkedList<int[]> list = new LinkedList<>();
        for(int[] p : people){
        	list.add(p[1],p);
        }
        return list.toArray(new int[list.size()][2]);
    }
}
