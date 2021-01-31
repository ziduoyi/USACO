import java.io.*;
import java.util.*;

public class mixmilk {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("mixmilk.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mixmilk.out")));
		int[] cap=new int[3];
		int[] o=new int[3];
		for(int i=0; i<3; i++) {
			cap[i]=scanner.nextInt();
			o[i]=scanner.nextInt();
		}
		int cur=0;
		for(int i=0; i<100; i++) {
			if(cur==0) {
				int am=o[0];
				int need=cap[1]-o[1];
				if(am>need) {
					int dif=am-need;
					o[1]=cap[1];
					o[0]=dif;
				}
				else {
					o[0]=0;
					o[1]+=am;
				}
			}
			if(cur==1) {
				int am=o[1];
				int need=cap[2]-o[2];
				if(am>need) {
					int dif=am-need;
					o[2]=cap[2];
					o[1]=dif;
				}
				else {
					o[1]=0;
					o[2]+=am;
				}
			}
			if(cur==2) {
				int am=o[2];
				int need=cap[0]-o[0];
				if(am>need) {
					int dif=am-need;
					o[0]=cap[0];
					o[2]=dif;
				}
				else {
					o[2]=0;
					o[0]+=am;
				}
			}
			
			cur++;
			if(cur==3)
				cur=0;
		}
		for(int i=0; i<3; i++) {
			out.println(o[i]);
		}
		out.close();
	}

}
