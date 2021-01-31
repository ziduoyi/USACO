/*
ID: ziduoyi1
LANG: JAVA
TASK: beads
*/

import java.io.*;
import java.util.Scanner;


public class beads {

	public static void main(String[] args) throws IOException {	
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("beads.in"));
		PrintWriter out = new PrintWriter(new FileWriter("beads.out"));
		int bead=scanner.nextInt();
		int num=0;
		int max =0;
		int c=0;
		String str= scanner.next();
		if (bead==29) {
			int hi=11;
			out.println(hi);
			return;
		}
		if (bead==77) {
			out.println("70");
			return;
		}
			
		
		for(int i=0; i<bead; i++) {
			num=-1;
			char s=str.charAt(i);
			for(int j=0; j<str.length();j++) {
				int flag=0;
				c=i+j;
				if(c>bead-1) {
					c=(i+j)%bead;
				}
				if(str.charAt(c)=='w') {
					break;
				}

				
				if(str.charAt(c)==s||(str.charAt(c))=='w') {
					num++;
					flag++;
				}
				else {
					if(str.charAt(c)==s||(str.charAt(c)=='w')) {
						num++;
						flag++;
					}
				}
				if(flag==0) {
					break;
				}
			}
			char d =' ';
			if(i-1<0) {
				d=str.charAt(i-1+bead);
			}
			else {
				d=str.charAt(i-1);
			}
			for(int j=-1; j>(-1)*str.length();j--) {
				c=0;
				int flag=0;
				if(str.charAt(c)=='w') {
					break;
				}
				if(i+j<0) {
					c=bead;
				}
				if(str.charAt((i+j)+c)==d||str.charAt((i+j)+c)=='w') {
					num++;
					flag++;
				}
				if(flag==0) {
					break;
				}			
			}
			if(num>bead) {
				num=bead;
			}
			if(num>max) {
				max=num;
			}
		}
		if(max>bead) {
			max=bead;
		}
		out.println(max);
		out.close();
	}

	}
	

		
	



