/*
ID: ziduoyi1
LANG: JAVA
TASK: gift1
*/
import java.util.*;
import java.io.*;

public class gift1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scanner= new Scanner(new File("gift1.in"));
		int gift=0;
		int peep=0;
		int sub=0;
		int nump=scanner.nextInt();
		int index=0;
		String name=" ";
		int[] nums= new int[nump];
		for(int i=0;i<nump; i++) {
			nums[i]=0;
		}
		String[] names=new String[nump];
		for(int i=0; i<nump; i++) {
			names[i]=scanner.next();
		}
		while(scanner.hasNext()) {
			name=scanner.next();
			index=getIndex(name, names);
			sub=scanner.nextInt();
			peep=scanner.nextInt();
			nums[index]-=sub;
			if(peep!=0) {
				gift=sub/peep;
				nums[index]+= sub-gift*peep;
				for(int i=0; i<peep; i++) {
					name=scanner.next();
					index=getIndex(name, names);
					nums[index]+=gift;
					
				}
			}
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
		for(int i=0; i<nump;i++) {
			out.print(names[i]);
			out.print(" ");
			out.println(nums[i]);
		}
		out.close();
	}
	static int getIndex(String name,String[] names) {
		for(int i=0; i<names.length; i++) {
			if(names[i].equals(name)) {
				return i;
			}
		}
		return -1;
	}
}

