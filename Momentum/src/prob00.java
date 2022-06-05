import java.io.*;
import java.util.*;
public class prob00 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
	    HashSet<Integer>set =new HashSet<>();
	    for(int i=1; i<1000000; i++)
	    	set.add(1000000/i);
	    System.out.print(set.size());
	}

}
