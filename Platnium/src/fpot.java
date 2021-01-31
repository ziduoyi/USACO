import java.io.*;
import java.util.*;
public class fpot {
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int[][] drops = new int[N][2];
		int[] starts = new int[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			drops[i][0] = Integer.parseInt(st.nextToken());
			drops[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(drops, new Comparator<int[]>() {//check
			@Override
			public int compare(int[] o1, int[] o2) {
			if(o1[0]>o2[0])
				return 1;
			if(o1[0]==o2[0])
				return 0;
			return -1;
			}
		});
		for(int i=0; i<N; i++)
			starts[i] = drops[i][0];
		int ans = 1000001;
		Map<Integer, Integer> map = new HashMap<>();
		TreeSet<Integer> set = new TreeSet<Integer>();
		int last =-1;
		for(int i=0; i<N; i++) {
			int com = starts[i]+ans;
			if(i!=0) {
				if(map.get(drops[i-1][1])==1) {
					set.remove(drops[i-1][1]);
					map.remove(drops[i-1][1]);
				}
				else {
					map.put(drops[i-1][1], map.get(drops[i-1][1])-1);
				}
			}
			int pos = last;
			for(int j = last+1; j<N; j++) {
				if(set.size()>0) {
					if(set.last()-set.first()>=D) {
						ans = Math.min(ans, starts[j-1]-starts[i]);
						break;
					}
				}
				if(com>=starts[j]) {
					if(map.get(drops[j][1])==null) {
						map.put(drops[j][1],1);
						set.add(drops[j][1]);
					}
					else {
						map.put(drops[j][1], map.get(drops[j][1])+1);
					}					
					pos =j;
				}
				else
					break;
			}
			if(set.last()-set.first()>=D)
					ans = Math.min(ans, starts[N-1]-starts[i]);
			last = pos;
			
		}
		if(ans ==1000001)
			System.out.println(-1);
		else
			System.out.println(ans);
	}

}
