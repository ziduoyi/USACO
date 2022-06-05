import java.util.*;
import java.io.*;
public class class5c3 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("cowsignal.in"));
		PrintWriter out = new PrintWriter(new FileWriter("cowsignal.out"));
		int high=scanner.nextInt();
		int len=scanner.nextInt();
		int mult=scanner.nextInt();
		char[][] arr=new char[high][len];
		char[][] ans=new char[high*mult][len*mult];
		for(int i=0 ;i<high; i++) {
			String s=scanner.next();
			for(int j=0; j<len; j++) {
				arr[i][j]=s.charAt(j);
			}
		}
		for(int i=0; i<high*mult; i+=mult) {
			for(int j=0; j<len*mult; j+=mult) {
				char c=arr[i][j];
				for(int k=i; k<high+i; k++) {
					for(int l=j; l<len+j; l++) {
						ans[k][l]=c;
					}
				}
			}
		}
		for(int i=0; i<high*mult; i++) {
			for(int j=0; j<len*mult; j++) {
				out.print(ans[i][j]);
			}
			out.println();
		}
		out.close();
	}

}
