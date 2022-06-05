import java.io.*;
import java.util.*;
public class caregiver {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		long curr = 1;
	    for(int i=2; i<1000000000; i++) {
	    	curr += i*i;
	    	double comp = (double)curr/i;
	    	if(Math.sqrt(comp)==(long)Math.sqrt(comp)) {
	    		System.out.println(i);
	    		break;
	    	}
	    }
	}

}
