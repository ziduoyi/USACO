import java.io.*;
import java.util.*;
public class prob29 {
	static int win;
	static HashSet<Integer> ways;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] board = new int[3][3];
		for(int i=0; i<3; i++) {
			String str = br.readLine();
			for(int j=0; j<3; j++) {
				char c = str.charAt(j);
				if(c=='O')
					board[i][j] = 1;
				if(c=='X')
					board[i][j] = 2;
			}
		}
		String str = br.readLine();
		int turn = 0;
		if(str.charAt(0)=='O')
			turn = 1;
		else
			turn = 2;
		win = -1;
		ways = new HashSet<>();
		recursion(board, 1, turn);
	}
	static void recursion(int[][] curr, int step, int turn) {
		if(step>4)return;
		if(checkWin(curr)) {
			win = 3-turn;
			win(curr);
			return;
		}
		HashSet<Integer> set = canWin(curr,turn);
		for(int node: set) {
			int[][] ex = new int[3][3];
			for(int j=0; j<3; j++)
				ex[j] = curr[j].clone();
			int x = node%3;
			int y = node/3;
			ex[x][y] = turn;
			recursion(ex, step+1, 3-turn);
			return;
		}
		HashSet<Integer> block = canWin(curr,3-turn);
		if(block.size()<=1) {
			int[][] ex = new int[3][3];
			for(int j=0; j<3; j++)
				ex[j] = curr[j].clone();
			if(block.size()==0) {
				for(int i=0; i<3; i++) {
					for(int j=0; j<3; j++) {
						if(ex[i][j]==0) {
							ex[i][j] = turn;
							recursion(ex, step+1, 3-turn);
							return;
						}
					}
				}
			}
			else {
				for(int node: block) {
					int x = node%3;
					int y = node/3;
					ex[x][y] = turn;
					recursion(ex, step+1, 3-turn);
				}
			}
		}
		
	}
	static void win(int[][] arr) {
		for(int i=0; i<3; i++) 
			if(arr[i][0]!=0&&arr[i][0]==arr[i][1]&&arr[i][1]==arr[i][2])
				ways.add((i)*81+(i+3)*9+(i+6));
		
		for(int i=0; i<3; i++) 
			if(arr[0][i]!=0&&arr[0][i]==arr[1][i]&&arr[1][i]==arr[2][i])
				ways.add((3*i)*81+(1+3*i)*9+(2+3*i));
		if(arr[0][0]!=0&&arr[0][0]==arr[1][1]&&arr[1][1]==arr[2][2])
			ways.add(0*81+(1+3*1)*9+(2+3*2));
		if(arr[2][0]!=0&&arr[2][0]==arr[1][1]&&arr[1][1]==arr[0][2])
			ways.add((2)*81+(1+3*1)*9+(0+3*2));
	}
	static boolean checkWin(int[][] arr) {
		for(int i=0; i<3; i++) 
			if(arr[i][0]!=0&&arr[i][0]==arr[i][1]&&arr[i][1]==arr[i][2])
				return true;
		
		for(int i=0; i<3; i++) 
			if(arr[0][i]!=0&&arr[0][i]==arr[1][i]&&arr[1][i]==arr[2][i])
				return true;
		if(arr[0][0]!=0&&arr[0][0]==arr[1][1]&&arr[1][1]==arr[2][2])
			return true;
		if(arr[2][0]!=0&&arr[2][0]==arr[1][1]&&arr[1][1]==arr[0][2])
			return true;
		return false;
	}
	static HashSet<Integer> canWin(int[][] arr, int side){
		HashSet<Integer> set = new HashSet<>();
		for(int i=0; i<3; i++) {
			int count = 0;
			for(int j=0; j<3; j++) 
				if(arr[i][j] == side)count++;
			
			if(count==2) {
				for(int j=0; j<3; j++)
					if(arr[i][j] !=side)set.add(i+3*j);
			}
		}
		for(int i=0; i<3; i++) {
			int count = 0;
			for(int j=0; j<3; j++) 
				if(arr[j][i] == side)count++;
			
			if(count==2) {
				for(int j=0; j<3; j++)
					if(arr[j][i] !=side)set.add(i+3*j);
			}
		}
		int count = 0;
		for(int j=0; j<3; j++) 
			if(arr[j][j] == side)count++;
		
		if(count==2) {
			for(int j=0; j<3; j++)
				if(arr[j][j] !=side)set.add(j+3*j);
		}
		count = 0;
		for(int j=0; j<3; j++) 
			if(arr[2-j][j] == side)count++;
		
		if(count==2) {
			for(int j=0; j<3; j++)
				if(arr[2-j][j] !=side)set.add(j+3*j);
		}
		return set;
	}
}
