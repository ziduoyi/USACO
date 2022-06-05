import java.io.*;
import java.util.*;

public class bracketDeletion {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	String str = br.readLine();
	    	char[] arr = str.toCharArray();
	    	int cnt = 0;
	    	int num = n;
	    	int j = 0;
	    	while(j<n) {
	    		boolean isRegular = true;
	    		int sum = 0;
	    		int start = j;
	    		for(; j<n; j++) {
	    			boolean b = true;
	    			if(j-start>0) {
	    				for(int k=start; k<=j; k++) {
	    					if(arr[k]!=arr[j-(k-start)]) {
	    						b =  false;
	    	    				break;
	    					}
	    				}
	    				if(b)break;
	    			}
	    			if(arr[j]=='(') {
	    				sum++;
	    			}
	    			else {
	    				sum--;
	    				if(sum<0) isRegular = false;
	    				if(sum==0&&isRegular)break;
	    			}
	    		}
	    		if(j==n)break;
	    		cnt++;
	    		num-=(j-start+1);
	    		j++;
	    	}
	    	out.write(cnt+" " + num +"\n");
	    }
	    out.flush();
	    out.close();
	}

}
