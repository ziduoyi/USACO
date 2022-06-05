import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
public class code {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("cowcode.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
		String str=scanner.next();
		long pos=scanner.nextLong();
		long len = str.length();
		while(len<pos) {
			len*=2;
		}
		while(pos>str.length()) {
			len/=2;
			while(len>pos)
				len/=2;
			if(pos==len+1) {
				pos=len;
			}
			else {
				pos=pos-len-1;
			}
		}
		out.println(searchhelp( str, pos));
		out.close();
	}
	static char searchhelp(String str, long N) {
		long len = str.length();
		if(N<=len)
			return str.charAt((int) (N-1));
		
		while(len<N) {
			len*=2;
		}
		len /=2;
		if(N==len+1) {
			searchhelp(str,len);
		}
		else {
			searchhelp(str,N-len-1);
		}
		return ' ';
	}
}