import java.io.*;
import java.util.*;
public class trader {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int[] arr1 = new int[N];
		int[] arr2 = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr1[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++)
			arr2[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		Queue<int[]> que = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				return arg0[0]-arg1[0];
			}
		});
		
	}

}
