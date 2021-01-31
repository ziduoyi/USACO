/*
ID: ziduoyi1
LANG: JAVA
TASK: friday
*/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class friday {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("friday.in"));
		int years=scanner.nextInt();
		int numd=1;
		int[] mon= new int[] {31,28,31,30,31,30,31,31,30,31,30,31};
		int[] days= new int[7];
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));

		for(int i=0;i<years;i++) {
			mon[1]=28;
			if((1900+i)%100==0) {
				if((1900+i)%400==0) {
					mon[1]=29;
				}				
			}
			else {
				if(((1900+i)%4==0)) {
					mon[1]=29;
				}
			}
			for(int j=0;j<12;j++) {
				for(int k=0; k<mon[j];k++) {
					numd++;
					if(k+1==13) {
						int day=numd%7;
						days[day]++;
						
					}
				}	
			}
		}
		for(int i=0; i<7;i++) {
			out.print(days[i]);
			if(i!=6)	
				out.print(" ");
		}
		out.println();
		out.close();
		
	}

}
