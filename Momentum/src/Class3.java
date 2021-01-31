import java.util.*;
import java.io.*;
public class Class3 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("solutions.in"));
		int n = in.nextInt();  
		
		int[][] A = new int[n][n];  
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				A[r][c] = in.nextInt();
			}
		}
		in.close();
		boolean isValid = true;

		for(int r=0; r<n; r++) {
			if( !allIntegersAppeared(A[r]) ) {
				isValid = false;
				System.out.println("Invalid -- rows");
				break;
			}
		}
		

		if( isValid ) {
			for(int c=0; c<n; c++) { 
				int[] B = new int[n];
				
				for(int r=0; r<n; r++) 
					B[r] = A[r][c];

				if( !allIntegersAppeared(B) ) {
					isValid = false;
					System.out.println("Invalid -- columns");
					break;
				}
			}
		}


		if( isValid ) {
			int m = (int) Math.sqrt(n);

			outer:
			for(int r=0; r<n; r=r+m) {
				for(int c=0; c<n; c=c+m) {
					int[] B = new int[n];
					
					for(int x=0; x<m; x++) {
						for(int y=0; y<m; y++) {
							B[x*m+y] = A[r+x][c+y];
						}
					}
					
					if( !allIntegersAppeared(B) ) {
						isValid = false;
						System.out.println("Invalid -- blocks");
						break outer;
					}
				}
			}			
		}
		
		if( isValid ) {
			System.out.println("Valid");
		}
	}
	
	public static boolean allIntegersAppeared(int[] B) {
		boolean[] appeared = new boolean[B.length+1];
		for(int k=0; k<B.length; k++)
			appeared[ B[k] ] = true;

		for(int k=1; k<=B.length; k++) {
			if( !appeared[k] )
				return false;
		}
		return true;
	}
}