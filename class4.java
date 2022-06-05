import java.io.*;
import java.util.*;
public class class4 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		PrintWriter out = new PrintWriter(new FileWriter("square.out"));
		Scanner scanner=new Scanner(new File("square.in"));
		int[][] arr = new int[4][2];
		int[] arr2= new int[4];
		for(int i=0; i<4; i++) {
			for(int j=0;j<2; j++) {
				arr[i][j]=scanner.nextInt();
			}
		}
		for(int i=0; i<2; i++) {
			if(arr[0][i]<arr[2][i]) {
				arr2[i]=arr[0][i];
			}
			else {
				arr2[i]=arr[2][i];
			}
		}
		for(int i=0; i<2; i++) {
			if(arr[1][i]>arr[3][i]) {
				arr2[i+2]=arr[1][i];
			}
			else {
				arr2[i+2]=arr[3][i];
			}
		}
		int len=0;
		int maxl=0;
		len= arr2[3]-arr2[1];
		if(len>maxl) {
			maxl=len;
		}
		len= arr2[2]-arr2[0];
		if(len>maxl) {
			maxl=len;
		}

		maxl*=maxl;
		out.println(maxl);
		out.close();
	}

}
