import java.io.*;
import java.util.*;
public class sleepy {
	static int[] bitree;
	
	public static void main(String[] args)throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader("sleepy.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));		// TODO Auto-generated method stub
	    int N = Integer.parseInt(br.readLine());
	    int[] arr = new int[N];
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    for(int i=0; i<N; i++) {
	    	arr[i] = Integer.parseInt(st.nextToken());
	    }
	    int last = N-1;
	    for(int i=N-2; i>-1; i--) {
	    	if(arr[i]<arr[i+1])
	    		last = i;
	    	else
	    		break;
	    }
	    bitree = new int[N+1];
	    for(int i=last; i<N; i++) {
	    	modify(arr[i], 1);
	    }
	    out.println(last);
	    for(int i=0; i<last; i++) {
	    	out.print(last-i-1+sum(arr[i]));
	    	if(i!=last-1)out.print(" ");
	    	modify(arr[i], 1);
	    }
	    out.println();
	    out.close();
	}
    static void modify(int j, int delta) {
        for(;j<=bitree.length;j+=(-j&j))	
        	bitree[j] += delta;
    }
    static int sum(int j) {
    	int s=0;
        for(;j>0; j-=(-j&j))
        	s+=bitree[j];
        return s;
    }
}
