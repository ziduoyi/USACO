import java.io.*;
import java.util.*;
public class camp {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int t = Integer.parseInt(st.nextToken())-1; //add 1 to ans at end
	    int k = Integer.parseInt(st.nextToken());
	    double curr = ((double)t)/2;
	    double[] arr = new double[t+1];
	    for(int i=0; i<t+1; i++) {
	    	arr[i] = 1;
	    	int cnt = 0;
	    	int start = Math.max(i, t-i);
	    	for(int j=start+1; j<t+1; j++) {
	    		arr[i]*=j;	
	    		arr[i]/=(j-start);    		
	    		while(arr[i]>1) {
	    			arr[i]/=2;
	    			cnt++;
	    		}
	    	}
	    	for(int j=cnt; j<t; j++) arr[i]/=2;
	    }
	    double sum = curr;
	    double prob = 0;
	    int j = 0;
	    for(int i=0; i<k-1; i++) {
	    	while(curr>j) {
	    		prob += arr[j];
	    		sum -= arr[j]*j;
	    		j++;
	    	}
	    	curr = prob*curr + sum;
	    }
	    out.write(curr+1+"\n");
	    out.flush();
	    out.close();
	}

}
