import java.io.*;
import java.util.*;
public class shuttle {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		LinkedList<int[]> list = new LinkedList<>();
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.add(new int[] {a,b,i,c,0});
			list.add(new int[] {b,a,i,c,1});
		}
		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if(o1[0] != o2[0])
					return o1[0]-o2[0];
				return o2[4] - o1[4];
			}
		});
		TreeSet<int[]> set = new TreeSet<>(new Comparator<int[]>() { // end pos    # of people    i
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if(o1[0]!=o2[0])
					return o1[0]-o2[0];
				return o1[2]-o2[2];
			}
			
		});
		int ans = 0;
		int curr = 0;
		while(!list.isEmpty()) {
			int[] node = list.removeFirst();
			if(node[4]==0) {
				int pass = curr + node[3] - C;
				if(pass<=0) {
					set.add(new int[] {node[1], node[3], node[2]});
					curr += node[3];
				}
				else {
					curr = C;
					while(pass>0&&!set.isEmpty()) {
						int[] arr = set.pollLast();
						if(arr[0]<=node[1]) {
							set.add(arr);
							break;
						}
						if(pass<arr[1]) {
							set.add(new int[] {arr[0],arr[1]-pass, arr[2]});
							pass = 0;
							break;
						}
						pass-=arr[1];
					}
					if(node[3]-pass>0)
						set.add(new int[] {node[1], node[3]-pass, node[2]});
				}
			}
			else {
				int[] a = new int[] {-1,-1,-1};
				for(int[] num: set) {
					if(num[0]!=node[0])
						break;
					if(num[2]==node[2]) {
						a = num;
						break;
					}
				}
				if(a[0]!=-1) {
					set.remove(a);
					int save = curr;
					curr = Math.max(0, curr-a[1]);
					ans += save - curr;
				}
			}
		}
		System.out.println(ans);
	}

}
