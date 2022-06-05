import java.io.*;
import java.util.*;

public class ac {
	static int[] bitree;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	String str = br.readLine();
	    	int[] arr = new int[n];
	    	for(int j=0; j<n; j++) {
	    		if(str.charAt(j)=='A')arr[j] = 1;
	    		else arr[j] = 0;
	    	}
	    	long ans = 0;
	    	int curr = 0;
	    	for(int j=0; j<n; j++) {
	    		if(arr[j]==1) 
	    			curr++;
	    		else
	    			ans+=curr;
	    	}
	    	out.write(ans+"\n"); 	
	    }
	    out.flush();
	    out.close();
	}
    public void modify(int j, int delta) {
        for(;j<bitree.length;j+=(-j&j))	
        	bitree[j] += delta;
    }
    public int sum(int j) {
    	int s=0;
        for(;j>0; j-=(-j&j))
        	s+=bitree[j];
        return s;
    }
}
