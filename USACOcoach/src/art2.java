import java.io.*;
import java.util.*;
public class art2 {
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("art2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("art2.out")));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) 
			arr[i] = Integer.parseInt(br.readLine());
		ArrayList<Integer> list = new ArrayList<>();
		int[][] inter = new int[N+1][2];
		for(int i=0; i<N; i++) {
			int pos = Collections.binarySearch(list, arr[i]);
			if(pos>=0) {
				inter[arr[i]][1] = i;
			}
			else {
				pos++;
				pos*=-1;
				list.add(pos, arr[i]);
				inter[arr[i]][0]=i;
			}
		}
		out.println();
		out.close();
	}

}
