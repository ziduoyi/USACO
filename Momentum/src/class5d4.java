import java.util.*;
import java.io.*;
public class class5d4 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("teleport.in"));
		PrintWriter out = new PrintWriter(new FileWriter("teleport.out"));
		int start=scanner.nextInt();
		int end=scanner.nextInt();
		int tele1=scanner.nextInt();
		int tele2=scanner.nextInt();
		int dis=0;
		int min=Math.abs(end-start);
		int a1=Math.abs(end-tele2);
		int a2=Math.abs(end-tele1);
		if((Math.abs(start-tele1)+a1)<(Math.abs(start-tele2)+a2)) {
			dis=Math.abs(start-tele1)+a1;
			if(dis<min) {
				min=dis;
			}
		}
		else {
			dis=Math.abs(start-tele2)+a2;
			if(dis<min) {
				min=dis;
			}
		}
		out.println(min);
		out.close();
	}

}
