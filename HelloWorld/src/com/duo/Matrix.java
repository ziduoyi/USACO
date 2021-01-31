package com.duo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Matrix {


	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(new File("matrix.txt"));
		int row=scanner.nextInt();
		int column = scanner.nextInt();
		int sum=0;
		int num= 0;
		int[][] data = new int[row][column];
		for(int i=0; i<row-1; i++) {
			int up=scanner.nextInt();
			data[i][0]=up;
			for(int x=1 ;x<column-1; x++) {
				int right=scanner.nextInt();
				data[i][x]=right;

			}
		}
		for(int i=0; i<row-1; i++) {
			sum=sum+data[i][0];
			num++;
			for(int x=1 ;x<column-1; x++) {
				sum=sum+data[i][x];
				num++;
			}
		}
		sum=sum/num;
		System.out.println(sum);
	}

}
