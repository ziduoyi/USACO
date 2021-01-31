import java.io.*;
import java.util.*;
public class shuffle {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
		Scanner scanner =new Scanner(new File("shuffle.in"));
		int cows=scanner.nextInt();
		int[] pos=new int[cows];
		int[] cow=new int[cows];
		int[] save=new int[cows];
		for(int i=0; i<cows; i++) {
			pos[i]= scanner.nextInt()-1;
		}
		for(int i=0; i<cows; i++) {
			cow[i]= scanner.nextInt();
		}
		for(int i=0; i<cows; i++) {
			for(int j=0; j<cows; j++) {
				if(pos[j]==i) {
					save[j]=cow[i];
				}
			}
		}
		for(int i=0; i<cows; i++) {
			for(int j=0; j<cows; j++) {
				if(pos[j]==i) {
					cow[j]=save[i];
				}
			}
		}
		for(int i=0; i<cows; i++) {
			for(int j=0; j<cows; j++) {
				if(pos[j]==i) {
					save[j]=cow[i];
				}
			}
		}
		for(int i=0; i<cows; i++) {
			out.println(save[i]);
		}
		out.close();
	}
}
