import java.io.*;
import java.util.*;
public class lonesome2 {

	static int x;
	static int y;
	
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		Map<int[], Integer> com = new HashMap<>();
		Map<Integer, int[]> back = new HashMap<>();
		x = Integer.MAX_VALUE;
		y = Integer.MAX_VALUE;
		ArrayList<Integer> hull = new ArrayList<>();
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(
			br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			com.put(arr[i], i);
			back.put(i, arr[i]);
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
		for(int i=2; i<N; i++) {
			list.add(i);
			boolean see =true;
			while(see&&list.size()>2) {
				see = false;
				int a = list.get(list.size()-3);
				int b = list.get(list.size()-2);
				int c = list.get(list.size()-1);
				if(arr[b][1]>=arr[a][1]&&arr[b][1]<=arr[c][1]||arr[b][1]<=arr[a][1]&&arr[b][1]>=arr[c][1]) {
					if(ccw(arr[a][0], arr[a][1], arr[b][0], arr[b][1], arr[c][0], arr[c][1])>0) {
						list.remove(list.get(list.size()-2));
						see =true;
					}
				}
				else if(arr[b][1]<arr[a][1]&&arr[b][1]<arr[c][1]) {
					list.remove(list.get(list.size()-2));
					see = true;
				}
			}
		}
		for(int i=0; i<list.size(); i++) 
			hull.add(com.get(arr[list.get(i)]));
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
		for(int i=2; i<N; i++) {
			list.add(i);
			boolean see =true;
			while(see&&list.size()>2) {
				see = false;
				int a = list.get(list.size()-3);
				int b = list.get(list.size()-2);
				int c = list.get(list.size()-1);
				if(arr[b][1]>=arr[a][1]&&arr[b][1]<=arr[c][1]||arr[b][1]<=arr[a][1]&&arr[b][1]>=arr[c][1]) {
					if(ccw(arr[a][0], arr[a][1], arr[b][0], arr[b][1], arr[c][0], arr[c][1])>0) {
						list.remove(list.get(list.size()-2));
						see =true;
					}
				}
				else if(arr[b][1]>arr[a][1]&&arr[b][1]>arr[c][1]) {
					list.remove(list.get(list.size()-2));
					see = true;
				}
			}
		}
		for(int i=1; i<list.size()-1; i++) 
			hull.add(com.get(arr[list.get(i)]));
		
		double fir = 0;
		double sec =0;
		int pos1 =0;
		int pos2 = 0;
		int a =Integer.MAX_VALUE;
		double b = 0;
		for(int i=0; i<hull.size(); i++) {
			if(back.get(hull.get(i))[0]<a) {
				a = back.get(hull.get(i))[0];
				pos1 =i;
			}
			if(back.get(hull.get(i))[0]>b) {
				b = back.get(hull.get(i))[0];
				pos2 =i;
			}
		}
		double max = dist(back.get(pos1),back.get(pos2));
		int[] ans = new int[] {pos1, pos2};
		while(fir<=360||sec<=360) {
			int next1 = pos1+1;
			if(next1==hull.size()-1) 
				next1 =0;
			int next2 = pos2+1;
			if(next2==hull.size()-1) 
				next2 =0;
			double x =((double)180/Math.PI)*Math.asin(((double)Math.abs(back.get(hull.get(next1))[1]-back.get(hull.get(pos1))[1]))/((double)dist(back.get(hull.get(pos1)), back.get(hull.get(next1)))));
			double y =((double)180/Math.PI)* Math.asin(((double)Math.abs(back.get(hull.get(next2))[1]-back.get(hull.get(pos2))[1]))/((double)dist(back.get(hull.get(pos2)), back.get(hull.get(next2)))));
			if(fir+x<sec+y||(fir+x==sec+y&&fir<sec)) {
				int next = pos1+1;
				if(next==hull.size()-1) 
					next =0;
				fir+=x;
				pos1 = next;
			}
			else {
				int next = pos2+1;
				if(next==hull.size()-1) 
					next =0;
				sec+=y;
				pos2 = next;				
			}
			if(dist(back.get(hull.get(pos1)), back.get(hull.get(pos2)))>max) {
				max = dist(back.get(hull.get(pos1)), back.get(hull.get(pos2)));
				ans[0] = Math.min(hull.get(pos1),hull.get(pos2));
				ans[1] = Math.max(hull.get(pos1),hull.get(pos2));
			}
			else if(dist(back.get(hull.get(pos1)), back.get(hull.get(pos2)))==max) {
				if(Math.min(hull.get(pos1),hull.get(pos2))<ans[0]) {
					ans[0] = Math.min(hull.get(pos1),hull.get(pos2));
					ans[1] = Math.max(hull.get(pos1),hull.get(pos2));					
				}
				else if(Math.min(hull.get(pos1),hull.get(pos2))==ans[0]&&Math.max(hull.get(pos1),hull.get(pos2))<ans[1]) {
					ans[0] = Math.min(hull.get(pos1),hull.get(pos2));
					ans[1] = Math.max(hull.get(pos1),hull.get(pos2));
				}
			}
		}
		
		for(int i=0; i<hull.size(); i++) {
			if(i==ans[1])continue;
			if(dist(back.get(ans[1]), back.get(hull.get(i)))>max){
				max = dist(back.get(ans[1]), back.get(hull.get(i)));
				ans[0] = hull.get(i);
			}
		}
		
		System.out.println((ans[0]+1)+" "+(ans[1]+1));
	}
	static double dist (int[] a, int[]b ) {
		return Math.sqrt((a[0]-b[0])*(a[0]-b[0])+(a[1]-b[1])*(a[1]-b[1]));
	}
	static long ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
		return (long)((long)((x2-x1)*(y3-y1))-(long)((y2-y1)*(x3-x1)));
	}

}
