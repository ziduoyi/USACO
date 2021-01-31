import java.io.*;
import java.util.*;
public class gourmet {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] cows = new int[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i][0] = Integer.parseInt(st.nextToken());
			cows[i][1]= Integer.parseInt(st.nextToken());
		}
		int[][] grass = new int[M][2];
		for(int i=0; i<M; i++) {
			st =new StringTokenizer(br.readLine());
			grass[i][0] = Integer.parseInt(st.nextToken());
			grass[i][1]= Integer.parseInt(st.nextToken());			
		}
		TreeSet<Integer> values = new TreeSet<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		long ans =0;
		Arrays.sort(cows, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if(o1[1]!=o2[1]) return o2[1]-o1[1];
				return o2[0]-o1[0];
			}
		});		
		Arrays.sort(grass, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if(o1[1]!=o2[1]) return o2[1]-o1[1];
				return o2[0]-o1[0];
			}
		});	
		int pos =0;
		for(int i=0; i<N; i++) {
			int y = cows[i][1];
			if(pos!=M) {
				while(grass[pos][1]>=y) {
					values.add(grass[pos][0]);
					if(map.get(grass[pos][0])==null) 
						map.put(grass[pos][0], 1);
					else 
						map.put(grass[pos][0], map.get(grass[pos][0])+1);
					pos++;
					if(pos==M)
						break;
				}
			}
			if(values.ceiling(cows[i][0])==null) {
				ans=-1;
				break;
			}
			int a = values.ceiling(cows[i][0]);
			ans +=a;
			if(map.get(a)==1) {
				map.remove(a);
				values.remove(a);
			}
			else 
				map.put(a, map.get(a)-1);
			
		}
		System.out.println(ans);
	}

}
