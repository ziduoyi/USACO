import java.io.*;
import java.util.*;
public class alehouse {
	static HashMap<Integer, Integer> map;
	static int[] bitree;
	static int[] over;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		map = new HashMap<>();
		TreeSet<Integer> set = new TreeSet<>();
		int[][] arr = new int[n][2];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			set.add(arr[i][0]);
			set.add(arr[i][1]);
		}
		set.add(-1);
		int a = 1;
		for(int num: set)
			map.put(num, a++);
		map.put(-1, 0);
		bitree = new int[a];
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				return arg0[0]-arg1[0];
			}	
		});
		for(int i=0; i<n; i++)
			modify(map.get(arr[i][0]),1);
		for(int i=0; i<n; i++)
			modify(map.get(arr[i][1]),1);
		int ans = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<2; j++) {
				int x = set.floor(arr[i][j]+k);
				int y = set.lower(arr[i][j]);
				int m = map.get(x);
				int l = map.get(y);
				int com1 = sum(m);
				int com2 = sum(l);
				ans = Math.max(ans, com1-com2);
			}
		}
		out.write(ans+"\n");
		out.flush();
		out.close();
	}
    static void modify(int j, int delta) {
        for(;j<bitree.length;j+=(-j&j))
        	bitree[j] += delta;
        
    }
    static int sum(int j) {
    	int s=0;
        for(;j>0; j-=(-j&j))	////j=j-lowbit(j)
        	s+=bitree[j];
        return s;
    }
}
