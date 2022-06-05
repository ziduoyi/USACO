import java.io.*;
import java.util.*;
public class Game {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    String strLine = in.readLine();
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    out.write(strLine, 0, strLine.length());
	    out.flush();
	    in.close();
	    out.close();
	}

}
