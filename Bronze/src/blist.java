import java.io.*;
import java.util.*;
public class blist {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("blist.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("blist.out")));
		int[] arr=new int[1001];
		int N=scanner.nextInt();
		for(int i=0; i<N; i++){
			int s=scanner.nextInt();
			int t=scanner.nextInt();
			int b=scanner.nextInt();
			for(int j=s; j<=t; j++) {
				arr[j]+=b;
			}
		}
		int max=0;
		for(int i=1; i< 1001; i++)
			max=Math.max(max, arr[i]);
		
		out.println(max);
		out.close();
 	}

}
