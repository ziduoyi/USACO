import java.io.*;
import java.util.*;
public class prob05 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new FileReader("input.txt"));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		double number = Double.parseDouble(br.readLine());
		int cnt = 0;
		while((number%(double)1)>0.0001) {
			number *=10;
			cnt++;
		}
		number = Math.round(number);
		if((number%10)==7)number += 0.02*Math.pow(10, cnt);
		else if((((number%10)%2)==1)) number -= 0.09 * Math.pow(10, cnt);
		else if((number%10)>7) number -= 4*Math.pow(10, cnt);
		else if((number%10)<4) number += 6.78*Math.pow(10, cnt);
		number/=Math.pow(10, cnt);
		System.out.printf("%.2f", number);
		out.flush();
		out.close();
	}

}
