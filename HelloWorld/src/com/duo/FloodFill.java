package com.duo;

public class FloodFill {
	
	
	public static void main(String[] args) {
		FloodFill floodFill = new FloodFill();
		
		int[][] data = new int[8][9];
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<9;j++) {
				int t = (i+j) / 3 ;
				data[i][j] = t % 2 ;
				System.out.print(data[i][j]+"  ");
			}
			System.out.println();
		}
		floodFill.fill(data,3,2,1,5);
		
		System.out.println();
		for(int i=0;i<8;i++) {
			for(int j=0;j<9;j++) {
				System.out.print(data[i][j]+"  ");
			}
			System.out.println();
		}
	}
	
	
	
	void fill(int[][] m, int x, int y, int oldValue, int newValue) {
		int row=m.length-1;
		int col=m[0].length-1;
		
		if(x<0 || y<0 || x>row || y>col)
			return;
			
			
		if(m[x][y]==oldValue) {
			m[x][y]=newValue;
		
			fill(m,x-1,y,oldValue, newValue);
			fill(m,x+1,y,oldValue, newValue);
			fill(m,x,y-1,oldValue, newValue);
			fill(m,x,y+1,oldValue, newValue);
		}
		
		return;
	}
	
	//recursive
	int factoral(int n) {
		if (n==1) return 1;
		if(n<1) return 0;
		
		int production=0;
		
		production = n * factoral(n-1);
		
//		for(int i=1;i<=n;i++) {
//			production *= i;
//		}
		
		return production;
	}
}
