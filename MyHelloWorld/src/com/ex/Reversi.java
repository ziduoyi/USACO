package com.ex;

import java.io.File;
import java.util.Scanner;

public class Reversi {
	static char[][] board = new char[8][8];

	@SuppressWarnings("resource")
	public static void main(String[] args){
		
		try{
			Scanner scanner = new Scanner(new File("./input_reversi.txt"));

			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					board[i][j] = scanner.next().charAt(0);
				}
			}

			char color = scanner.next().charAt(0);

			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					if(isLegalMove(i,j,color))
						board[i][j]='0';
				}
			}

			for(int i=0;i<8;i++){
				for(int j=0;j<8;j++){
					System.out.print(" " + board[i][j] +" ");
				}
				System.out.println("");
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public static boolean isLegalMove(int i, int j, char color){
		if(board[i][j]!='.')
			return false;	//not empty cell

		for(int x=-1;x<=1;x++){
			for(int y=-1;y<=1;y++){
				int m = i + x;
				int n = j + y;

				if (m<0 || m >7 || n<0 || n>7 || board[m][n]==color || board[m][n]=='.' || board[m][n]=='0')
					continue;	//out of bound, same color or empty, skip this direction

				while(true){
					m += x;
					n += y;
					
					if (m<0 || m >7 || n<0 || n>7 || board[m][n]=='.' || board[m][n]=='0')
						break;	//out of bound or empty, skip this direction
					
					if(board[m][n]==color)
						return true;
				}
			}
		}
		
		return false;
	}
}
