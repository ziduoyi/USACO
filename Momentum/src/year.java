import java.io.*;
import java.util.*;
public class year {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int N = Integer.parseInt(st.nextToken());
	    int K = Integer.parseInt(st.nextToken());
	    int[] start = new int[N]; // start[index] stores the first multiple of 12 greater than arr[index]
	    int[] arr = new int[N]; //stores the values of the input
	    for(int i=0; i<N; i++) {
	    	arr[i] = Integer.parseInt(br.readLine());
	    	start[i] = ((arr[i]+11)/12)*12;
	    }
	    Arrays.sort(arr); //makes the input increasing in value
	    Arrays.sort(start);
	    int ans = start[N-1]; //stores the answer
	    int[] gaps = new int [N];
	    for(int i=1; i<N; i++) {
	    	if(start[i-1] == start[i]) //makes it so that only distinct and adjacent
	    		continue;			   //intervals can be time traveled
	    	gaps[i-1] = start[i]-start[i-1]-12; //adds a potential time travel
	    }
	    gaps[N-1] = start[0]-12; //consider the time travel back to the present
	    Arrays.sort(gaps); //finds the k-1 greatest time saves and uses them
	    for(int i=0; i<Math.min(K-1, N); i++)
	    	ans -= gaps[N-i-1];
	    out.write(ans+"\n");
	    out.flush();
	    out.close();
	}

}
