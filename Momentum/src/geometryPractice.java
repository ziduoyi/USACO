import java.io.*;
import java.util.*;
public class geometryPractice {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		String[] st1 = new String[] {"when", "because", "provided that", "since", "after", "due to the fact", "in the event", "as a result", "on account of"};
		String[] st2 = new String[] {"only if", "so", "therefore", "thus", "implies", "in order to", "to have been", "caused", "before"};
		while(1>0) {
			long num = System.currentTimeMillis();
			if(num%2 ==0) {
				System.out.println(st1[(int) (num%st1.length)]);
				int a = scanner.nextInt();
				if(a==1)
					System.out.println("correct");
				else
					System.out.println("wrong");
			}
			else {
				System.out.println(st2[(int) (num%st2.length)]);
				int a = scanner.nextInt();
				if(a==1)
					System.out.println("wrong");
				else
					System.out.println("correct");
			}
		}
	}

}
