import java.io.*;
import java.util.*;
public class lemonade {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("lemonade.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lemonade.out")));
		int N=scanner.nextInt();
		int[] arr=new int[N];
		int sub=0;
		for(int i=0; i<N; i++) {
			arr[i]=scanner.nextInt();
			if(arr[i]>=N-1)
				sub++;
		}
		Arrays.sort(arr);
		int min=sub;
		for(int i=N-1-sub; i>-1; i--) {
			if(arr[i]-sub>=0) {
				min++;
				sub++;
			}
			else
				break;
		}
		out.println(min);
		out.close();
	}

}
