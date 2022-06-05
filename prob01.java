import java.io.*;
import java.util.*;
public class prob01 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new FileReader("input.txt"));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    String str = br.readLine();
	    out.write("Clap your hands together and give it up for "+str+"!");
	    out.flush();
	    out.close();
	}

}

