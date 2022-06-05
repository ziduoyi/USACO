import java.io.*;
import java.util.*;
public class prob08 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new FileReader("input.txt"));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int n = Integer.parseInt(br.readLine());
	    int total = 0;
	    for(int i=0; i<n; i++) {
	    	String str = br.readLine();
	    	String[] a = str.split(":");
	    	total += Integer.parseInt(a[0]) * 60 + Integer.parseInt(a[1]);
	    }
	    System.out.print((total/60)+":");
	    if((total%60)<10)System.out.print("0");
	    System.out.print(total%60);
	}

}
