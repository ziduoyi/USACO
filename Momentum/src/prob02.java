import java.io.*;
import java.util.*;
public class prob02 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new FileReader("input.txt"));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    ArrayList<Integer> list = new ArrayList<>();
	    int a = -1;
	    int odd = 0;
	    while(a!=0) {
	    	a = Integer.parseInt(br.readLine());
	    	if(a!=0) {
	    		list.add(a);
	    		if(a%2==1)odd++;
	    	}
	    }
	    
	    if(odd==0) {
	    	out.write("NO LIST PROBLEMS FOUND");
		    out.flush();
		    out.close();
	    	return;
	    }
	    if(odd==list.size()) {
	    	out.write("NO LIST PROBLEMS FOUND");
		    out.flush();
		    out.close();

	    	return;
	    }
	    if(odd==1) {
	    	int ans = 0;
	    	for(int i=0; i<list.size(); i++)
	    		if(list.get(i)%2==1) {
	    			ans = list.get(i);
	    			break;
	    		}
	    	out.write(ans + " is not even");
	    }
	    else {
	    	int ans = 0;
	    	for(int i=0; i<list.size(); i++)
	    		if(list.get(i)%2==0) {
	    			ans = list.get(i);
	    			break;
	    		}
	    	out.write(ans + " is not odd");
	    }
	    out.flush();
	    out.close();
	}

}