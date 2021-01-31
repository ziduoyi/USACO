import java.util.*;
import java.io.*;
public class sort {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("sort.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
		int num=Integer.parseInt(br.readLine());
		int[] A=new int[num];
		for(int i=0; i<num; i++) {
			A[i]=Integer.parseInt(br.readLine());
		}
		int moo=0;
		boolean sorted = false;
		while (! sorted) {
			sorted = true;
			moo++;
			for(int  i = 0 ;i< num-2; i++) {
				if( A[i+1] < A[i]) {
					int save=A[i+1];
					A[i+1]=A[i];
					A[i]=save;
				}
			}
			for(int  i = num-2 ;i>=0; i--) {
				if( A[i+1] < A[i]) {
					int save=A[i+1];
					A[i+1]=A[i];
					A[i]=save;
				}
			}
			for(int  i = 0 ;i< num-2; i++) {
				if( A[i+1] < A[i]) {
					sorted=false;
				}
			}
		}
		out.println(moo);
		out.close();
	}

}
