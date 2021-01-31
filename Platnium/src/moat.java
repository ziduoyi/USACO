import java.util.*;
import java.io.*;
public class moat {
	static int x;
	static int y;
	
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		x = Integer.MAX_VALUE;
		y = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(
			br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			if(x>arr[i][0]) {
				x = arr[i][0];
				y = arr[i][1];
			}
		}
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0]!=o2[0]) return o1[0]-o2[0];
				return o2[1]-o1[1];
			}
		});
		ArrayList<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(1);
		double total = dist(arr[0], arr[1]);
		for(int i=2; i<N; i++) {
			total += dist(arr[i], arr[list.get(list.size()-1)]);
			list.add(i);
			boolean see =true;
			while(see&&list.size()>2) {
				see = false;
				int a = list.get(list.size()-3);
				int b = list.get(list.size()-2);
				int c = list.get(list.size()-1);
				if(arr[b][1]>=arr[a][1]&&arr[b][1]<=arr[c][1]||arr[b][1]<=arr[a][1]&&arr[b][1]>=arr[c][1]) {
					if(ccw(arr[a][0], arr[a][1], arr[b][0], arr[b][1], arr[c][0], arr[c][1])>0) {
						total -= dist(arr[a], arr[b]);
						total -= dist(arr[b], arr[c]);
						total +=dist(arr[a], arr[c]);
						list.remove(list.get(list.size()-2));
						see =true;
					}
				}
				else if(arr[b][1]<arr[a][1]&&arr[b][1]<arr[c][1]) {
					total -= dist(arr[a], arr[b]);
					total -= dist(arr[b], arr[c]);
					total +=dist(arr[a], arr[c]);
					list.remove(list.get(list.size()-2));
					see = true;
				}
			}
		}
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0]!=o2[0]) return o2[0]-o1[0];
				return o1[1]-o2[1];
			}
		});		
		list.clear();
		list.add(0);
		list.add(1);
		total += dist(arr[0], arr[1]);
		for(int i=2; i<N; i++) {
			total += dist(arr[i], arr[list.get(list.size()-1)]);
			list.add(i);
			boolean see =true;
			while(see&&list.size()>2) {
				see = false;
				int a = list.get(list.size()-3);
				int b = list.get(list.size()-2);
				int c = list.get(list.size()-1);
				if(arr[b][1]>=arr[a][1]&&arr[b][1]<=arr[c][1]||arr[b][1]<=arr[a][1]&&arr[b][1]>=arr[c][1]) {
					if(ccw(arr[a][0], arr[a][1], arr[b][0], arr[b][1], arr[c][0], arr[c][1])>0) {
						total -= dist(arr[a], arr[b]);
						total -= dist(arr[b], arr[c]);
						total +=dist(arr[a], arr[c]);
						list.remove(list.get(list.size()-2));
						see =true;
					}
				}
				else if(arr[b][1]>arr[a][1]&&arr[b][1]>arr[c][1]) {
					total -= dist(arr[a], arr[b]);
					total -= dist(arr[b], arr[c]);
					total +=dist(arr[a], arr[c]);
					list.remove(list.get(list.size()-2));
					see = true;
				}
			}
		}
		System.out.printf("%.2f", total);
	}
	static double dist (int[] a, int[]b ) {
		return Math.sqrt((a[0]-b[0])*(a[0]-b[0])+(a[1]-b[1])*(a[1]-b[1]));
	}
	static long ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
		return (long)((long)((x2-x1)*(y3-y1))-(long)((y2-y1)*(x3-x1)));
	}
}
