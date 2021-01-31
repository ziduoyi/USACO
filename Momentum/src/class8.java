import java.io.File;
import java.io.*;
import java.io.IOException;
import java.util.*;
public class class8 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("grades.in"));
		int n=scanner.nextInt();
		int[] arr=new int[n];
		for(int i=0; i<n; i++) {
			arr[i]=scanner.nextInt();
		}
		int[] con=new int[4];
		int max=0;
		for(int i=0; i<n-3; i++) {
			int s=0;
			double ave=0;
			for(int j=i; j<i+4; j++) {
				con[s]=arr[j];
				s++;
			}
			Arrays.sort(con);
			for(int j=1; j<con.length; j++) {
				ave+=con[j];
			}
			ave/=3;
			ave+=0.5;
			if(ave>max)
				max=(int) ave;
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("grades.out")));
		out.print(max);
		out.close();
	}

}
