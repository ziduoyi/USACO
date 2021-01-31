import java.io.*;
import java.util.*;
public class goldp3 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("boards.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("boards.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int[][] arr = new int[P][4];
		int[] comp1 = new int[P];
		int[] comp2 = new int[P];
		for(int i=0; i<P; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][2] = Integer.parseInt(st.nextToken());
			arr[i][3] = Integer.parseInt(st.nextToken());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr,(a,b)->{if(a[0]!=b[0]) 
			return a[0]-b[0];
			else return a[1]-b[1];
			});
		for(int i=0; i<P; i++) {
			comp1[i] = arr[i][0];
			comp2[i] = arr[i][1];
		}
		int[] dist = new int[P+2];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] =0;
		LinkedList<Integer> list = new LinkedList<>();
		list.add(0);
		while(!list.isEmpty()) {
			int num = list.removeFirst();
			int x1 = 0;
			int y1 =0;
			if(num!=0) {
				x1 =arr[num-1][0];
				y1 = arr[num-1][1];
			}
			dist[P+1] = Math.min(dist[P+1], N-x1+N-y1+dist[num]);
			int pos = bsearch(comp1,comp2, x1,y1);
			for(int i=pos; i<P; i++) {
				if(x1<=arr[i][2]&&y1<=arr[i][3]&&i!=num-1) {
					if(dist[num]+arr[i][2]-x1+arr[i][3]-y1<dist[i+1]) {
						dist[i+1] = dist[num]+arr[i][2]-x1+arr[i][3]-y1;
						list.add(i+1);
					}
				}
			}
		}
		out.println(dist[P+1]);
		out.close();
	}
	static int bsearch(int[] arr1, int[] arr2, int X, int Y) {
		int l=0, r=arr1.length-1;
		while(l<=r) {
			int m=l+(r-l)/2;
			if(arr1[m]==X&&arr2[m]>Y)
				return m;
			if(arr1[m]>X||arr1[m]==X&&arr2[m] >Y)
				r=m-1;
			else
				l=m+1;
		}
		return l;
	}
}
