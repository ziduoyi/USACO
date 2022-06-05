import java.io.*;
import java.util.*;
public class linguistics {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int[] need = new int[4];
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	for(int j=0; j<4; j++)need[j] = Integer.parseInt(st.nextToken());
	    	char[] arr = br.readLine().toCharArray();
	    	int n =arr.length;
	    	int cnt = 0;
	    	for(int j=0; j<n; j++) if(arr[j] == 'A')cnt++;
	    	if(cnt!=need[0]+need[2]+need[3]) {
	    		out.write("NO\n");
	    		out.flush();
	    	}
	    	int bonus = 0;
	    	LinkedList<Integer> type1 = new LinkedList<>(); //AB
	    	LinkedList<Integer> type2 = new LinkedList<>(); //BA
	    	int last = 0;
	    	for(int j=0; j<n-1; j++) {
	    		//if()
	    	}
	    }
	}

}
