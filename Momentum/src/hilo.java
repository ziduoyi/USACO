import java.util.*;
import java.io.*;

public class hilo {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int N = Integer.parseInt(br.readLine());
	    int[] pos = new int[N+1];
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int[] prev = new int[N+1];
	    for(int i=0; i<N; i++)
	    	pos[Integer.parseInt(st.nextToken())] = i+1;
	    Stack<Integer> stack = new Stack<>();
	    stack.push(0);
	    for(int i=N; i>0; i--) {
	    	while(stack.peek()>pos[i]) stack.pop();
	    	prev[pos[i]] = stack.peek();
	    	stack.push(pos[i]);
	    }
	    while(stack.size()!=1)stack.pop();
	    int[] hilo = new int[N+1];
	    int cnt = 0;
	    for(int i=1; i<=N; i++) {
	    	while(stack.peek()>pos[i]) {
	    		cnt-=hilo[stack.peek()];
	    		stack.pop();
	    	}
	    	
	    }
	    out.flush();
	    out.close();
	    
	}

}
