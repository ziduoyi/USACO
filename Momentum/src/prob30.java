import java.io.*;
import java.util.*;
public class prob30 {
	static int[][] ans;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner br = new Scanner(System.in);
		int n =  br.nextInt();
		int[][] grid = new int[n][n];
		for(int i=0; i<n; i++) {
			String str = br.next();
			for(int j=0; j<n; j++) {
				char c = str.charAt(j);
				if(c=='.')
					grid[i][j] = -1;
				if(c=='C')
					grid[i][j] = 0;
				if(c=='v')
					grid[i][j] = 1;
			}
		}
		ans = new int[n][n];
		for(int i=0; i<n*n; i++) {
			twoConsecutive(grid);
			twoGap(grid);
			count(grid);
		}
		recursion(grid, 0, 0);
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(grid[i][j]==0)System.out.print("C");
				else System.out.print("v");
			}
			System.out.println();
		}
	}
	static void recursion(int[][] grid, int x, int y) {
		twoConsecutive(grid);
		twoGap(grid);
		count(grid);
		boolean change = false;
		for(int i=x; i<grid.length; i++) {
			int s = 0;
			if(i==x) s = y;
			for(int j=s; j<grid.length; j++) {
				if(grid[i][j]==-1) {
					grid[i][j] = 0;
					//recursion(next, i, j);
					int[][] next2 = new int[grid.length][grid.length];
					for(int k=0; k<grid.length; k++) next2[k] = grid[k].clone(); 
					next2[i][j] = 1;
					recursion(next2, i,j);
					change = true;
					break;
				}
			}
			if(change)break;
		}
		if(!change) {
			if(isValid(grid)) {
				for(int i=0; i<grid.length; i++)ans[i] = grid[i].clone();
				for(int i=0; i<grid.length; i++) {
					for(int j=0; j<grid.length; j++) {
						if(grid[i][j]==0)System.out.print("C");
						else System.out.print("v");
					}
					System.out.println();
				}
				System.exit(0);
			}
		}
		return;
	}
	
	
	
	static boolean isValid(int[][] arr) {
		for(int i=0; i<arr.length; i++) 
			for(int j=0; j<arr.length-2;j++) 
				if(arr[i][j] == arr[i][j+1]&&arr[i][j]==arr[i][j+2]&&arr[i][j+1]!=-1) 
					return false;
		for(int j=0; j<arr.length; j++) 
			for(int i=0; i<arr.length-2;i++) 
				if(arr[i][j] == arr[i+1][j]&&arr[i][j]==arr[i+2][j]&&arr[i][j]!=-1)return false; 
		
		for(int i=0; i<arr.length; i++) {
			int zero = 0;
			int one = 0;
			for(int j=0; j<arr.length; j++) {
				if(arr[i][j]==0) zero++;
				if(arr[i][j]==1) one++;
			}
			if(zero!=one) return false;
		}
		
		for(int i=0; i<arr.length; i++) {
			int zero = 0;
			int one = 0;
			for(int j=0; j<arr.length; j++) {
				if(arr[j][i]==0) zero++;
				if(arr[j][i]==1) one++;
			}
			if(zero!=one) return false;
		}
		
			
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	static boolean allFilled(int[][] arr) {
		for(int i=0; i<arr.length; i++) for(int j=0; j<arr.length; j++) if(arr[i][j]==-1) return false;
		return true;
	}
	static void twoConsecutive(int[][] arr) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr.length-1;j++) {
				if(arr[i][j] == arr[i][j+1]&&arr[i][j]!=-1) {
					if(j>0&&arr[i][j-1]==-1)arr[i][j-1] = 1- arr[i][j];
					if(j<arr.length-2&&arr[i][j+2]==-1)arr[i][j+2] = 1- arr[i][j];
				}
			}
		}
		for(int j=0; j<arr.length; j++) {
			for(int i=0; i<arr.length-1;i++) {
				if(arr[i][j] == arr[i+1][j]&&arr[i][j]!=-1) {
					if(i>0&&arr[i-1][j]==-1)arr[i-1][j] = 1- arr[i][j];
					if(i<arr.length-2&&arr[i+2][j]==-1)arr[i+2][j] = 1- arr[i][j];
				}
			}
		}
	}
	static void twoGap(int[][] arr) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr.length-2;j++) {
				if(arr[i][j] == arr[i][j+2]&&arr[i][j]!=-1) {
					if(arr[i][j+1]==-1) arr[i][j+1] = 1 - arr[i][j];
				}
			}
		}
		for(int j=0; j<arr.length; j++) {
			for(int i=0; i<arr.length-2;i++) {
				if(arr[i][j] == arr[i+2][j]&&arr[i][j]!=-1) {
					if(arr[i+1][j]==-1) arr[i+1][j] = 1 - arr[i][j];
				}
			}
		}		
	}
	static void count(int[][] arr) {
		for(int i=0; i<arr.length; i++) {
			int zero = 0;
			int one = 0;
			for(int j=0; j<arr.length; j++) {
				if(arr[i][j]==0) zero++;
				if(arr[i][j]==1) one++;
			}
			if(zero==arr.length/2) for(int j=0; j<arr.length; j++) if(arr[i][j]==-1) arr[i][j] = 1;
			if(one==arr.length/2) for(int j=0; j<arr.length; j++) if(arr[i][j]==-1) arr[i][j] = 0;
		}
		
		for(int i=0; i<arr.length; i++) {
			int zero = 0;
			int one = 0;
			for(int j=0; j<arr.length; j++) {
				if(arr[j][i]==0) zero++;
				if(arr[j][i]==1) one++;
			}
			if(zero==arr.length/2) for(int j=0; j<arr.length; j++) if(arr[j][i]==-1) arr[j][i] = 1;
			if(one==arr.length/2) for(int j=0; j<arr.length; j++) if(arr[j][i]==-1) arr[j][i] = 0;
		}
	}
}
