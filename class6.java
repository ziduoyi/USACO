import java.util.*;
import java.io.*;

public class class6 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(new File("bear.in"));
		PrintWriter out = new PrintWriter(new FileWriter("bear.out"));
		int times = scanner.nextInt();
		char[][] arr = new char[10][10];
		for (int i= 0; i < times; i++) {
			for (int j = 0; j < 10; j++) {
				String s = scanner.next();
				for (int k = 0; k < 10; k++) {
					arr[j][k] = s.charAt(k);
				}
			}
			for (int j = 0; j < 10; j++) {
				int bear = 0;
				for (int k = 0; k < 10; k++) {
					if (arr[j][k] == 'B') {
						bear++;
					}
				}
				out.print(bear);
				out.print(" ");
			}
			out.println();
			for (int j = 0; j < 10; j++) {
				int bear = 0;
				for (int k = 0; k < 10; k++) {
					if (arr[k][j] == 'B') {
						bear++;
					}
				}
				out.print(bear);
				out.print(" ");
			}
			out.println();
		}
		out.close();
/*		int a=0;
		for (int i = 1; i < times + 1; i++) {
			for (int j = 0; j < 10; j++) {
				String s = scanner.next();
				for (int k = 0; k < 10; k++) {
					arr[a +j][k] = s.charAt(k);
				}
			}
			a+=10;
		}
		for (int i = 0; i < times; i++) {
			for (int j = i * 10; j < (i + 1) * 10; j++) {
				int bear = 0;
				for (int k = 0; k < 10; k++) {
					if (arr[j][k] == 'B') {
						bear++;
					}
				}
				System.out.print(bear);
				System.out.print(" ");
			}
			System.out.println();
			for (int j = 0; j < 10; j++) {
				int bear = 0;
				for (int k = i*10; k < (i+1)*10; k++) {
					if (arr[k][j] == 'B') {
						bear++;
					}
				}
				System.out.print(bear);
				System.out.print(" ");
			}
			System.out.println();
		}
		*/

	}

}
