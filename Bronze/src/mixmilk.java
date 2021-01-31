import java.io.*;
import java.util.*;

public class mixmilk {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("mixmilk.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("mixmilk.out")));
		
		int[] cap =new int[3];
		int[] curr=new int[3];
		for(int i=0; i<3; i++) {
			cap[i]=scanner.nextInt();
			curr[i]=scanner.nextInt();
		}
		for(int i=0; i<100; i++) {
			int s=i%3;
			int e=(i+1)%3;
			if(curr[s]+curr[e]<=cap[e]) {
				curr[e]+=curr[s];
				curr[s]=0;
			}else{
				//curr[s]-=(cap[e]-curr[e]);
				curr[s]=curr[s]+curr[e]-cap[e];
				curr[e]=cap[e];
			}
		}
		for(int i=0; i<3; i++) {
			out.println(curr[i]);
		}
		out.close();
	}

}
