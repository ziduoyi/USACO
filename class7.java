import java.io.*;
import java.util.*;
public class class7 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		String s= scanner.nextLine();
		s=s.toLowerCase();
		int c=0;
		int v=0;
		int space=0;
		int word=1;
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)-'a'<0) {
				space++;
				word++;
				continue;
				
			}
			if(s.charAt(i)=='a'||s.charAt(i)=='e'||s.charAt(i)=='i'||s.charAt(i)=='o'||s.charAt(i)=='u') {
				c++;
			}
			else 
				v++;
			
		}
		System.out.println(s.length()-space);
		System.out.println(c);
		System.out.println(v);
		System.out.println(word);
		
	}

}
