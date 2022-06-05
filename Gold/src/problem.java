import java.io.*;
import java.util.*;

public class problem {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		int t = Integer.parseInt(br.readLine());
		for(int i=0; i<t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int max = (N-1)/2;
			if(M>max) {
				out.write(-1+" ");
				out.write("\n");
			}
			else {
				int[] arr = new int[N];
				for(int j=0; j<N; j++)arr[j] = j+1;
				int j = 1;
				while(M!=0) {
					int save = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = save;
					j+=2;
					M--;
				}
				for(j=0; j<N; j++)
					out.write(arr[j]+" ");
				out.write("\n");
			}
		}
		out.flush();
		out.close();
	}

}
