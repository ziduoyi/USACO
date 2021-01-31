/*
 * Given positive integers a, b, and n, find the sum of all integers in the pyramid.
 * For example, if a = 21, b = 2, n = 5, the pyramid is like this:
 *             21   2
 *           21  11   2
 *         21  32  13  2
 *       21  53  22  15  2
 *     21  74  75   37  17  2
 *
 * The output is 464.
 * 
 * Add the ideas here!
 */
import java.util.*;
import java.io.*;
public class class5 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("pyramid.in"));
		PrintWriter out = new PrintWriter(new File("pyramid.out"));
		
		int a = in.nextInt();
		int b = in.nextInt();
		int n = in.nextInt();
		in.close();
		
		// find the rows of the pyramid and get the sum
		int sum = a+b;

		int[] A = new int[2];
		A[0] = a;
		A[1] = b;

		for (int k = 1; k < n; k++) {
			int[] B = new int[A.length + 1];

			B[0] = A[0]+1;
			for (int j = 1; j < A.length; j++)
				B[j] = A[j - 1] + A[j];
			
			B[A.length] = A[A.length-1]+1;

			if (A.length % 2 == 0) {
				B[A.length / 2] /= 2;
			}
			A = B;

			for (int j = 0; j < B.length; j++)
				sum += B[j];
		}
		out.println(sum);
		int m=9999999;
		out.println(m);
		out.close();
	}
}

