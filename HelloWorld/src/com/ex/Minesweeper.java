package com.ex;

import java.io.File;
import java.util.Scanner;

public class Minesweeper {
	
	@SuppressWarnings("resource")
	public static void main(String[] args){
		
		try{
			int fieldNum = 0;
			Scanner scanner = new Scanner(new File("./input_minesweeper.txt"));
			
			while (true){
				int numRows=scanner.nextInt();
				int numCols=scanner.nextInt();

				if(numRows == 0 && numCols == 0 )
					return;
				
				if(numRows < 1 || numRows > 100 || numCols < 1 || numCols>100){
					System.out.println("Wrong input");
					continue;
				}

				char[][] data = new char[numRows][numCols];
				scanner.nextLine();
				for(int i=0; i<numRows; i++){
					String line = scanner.nextLine();
					for(int j=0; j<numCols; j++){
						data[i][j]=line.charAt(j);
					}
				}
				
				short[][] result = new short[numRows][numCols];
				for(int i=0; i<numRows; i++){
					for(int j=0; j<numCols; j++){
						if(data[i][j]=='*')
								result[i][j]=9;
						if(data[i][j]=='.')
							result[i][j]=0;
								
					}
				}
				
				//using 9 to represents *, as 8 the biggest number with all * neighbors
				for(int i=0; i<numRows; i++){
					for(int j=0; j<numCols; j++){

						if(result[i][j]==9){
							int i1 = i>0?i-1:0;
							int i2 = i<numRows-1?i+1:i;
							
							int j1 = j>0?j-1:0;
							int j2 = j<numCols-1?j+1:j;

							for(int x=i1; x<=i2; x++){
								for(int y=j1; y<=j2; y++){
									if(result[x][y]!=9)
										result[x][y]++;
								}
							}
						}
					}
				}

				System.out.println("Field #" + (++fieldNum) + ":");
				for(int i=0; i<numRows; i++){
					for(int j=0; j<numCols; j++){
						if(result[i][j]==9)
							System.out.print("*");
						else
							System.out.print(result[i][j]);
					}
					System.out.println("");
				}
				System.out.println("");
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
