import java.io.*;
import java.util.*;
public class promise {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		double cnt = 0;
	    for(int i=3; i<10; i++) {
	    	cnt+=(double)1/(i*(i*i*i*i - 5* i*i+4)*(i*i*i*i - 5* i*i+4));
	    }
	    System.out.println(cnt);
	}

}
