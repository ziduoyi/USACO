import java.io.*;
import java.util.*;

public class circular {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	char[] arr = br.readLine().toCharArray();
	    	int cnt = 0;
	    	for(int j=0; j<n; j++)
	    		if(arr[j]=='1')
	    			cnt++;
	    	if(cnt%2==1||cnt==0) {
	    		out.write("NO\n");
	    		continue;
	    	}
	    	out.write("YES\n");
	    	ArrayList<int[]> list = new ArrayList<>();
	    	for(int j=0; j<n; j++) {
	    		if(arr[j]=='1') {
	    			int pos = j;
	    			while(arr[(pos+1)%n]=='0') {
	    				int tar = (pos+1)%n;
	    				out.write((pos+1)+" "+(tar+1)+"\n");
	    				pos = tar;
	    			}
	    			list.add(new int[] {j,pos});
	    		}
	    	}
	    	for(int j=1; j<list.size(); j++)out.write(((list.get(0)[1]+1)+" "+(list.get(j)[1]+1)+"\n"));
	    }
	    out.flush();
	    out.close();
	}

}
