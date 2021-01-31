import java.io.*;
import java.util.*;
public class expand {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<int[]> sidex = new ArrayList<>();
		ArrayList<int[]> sidey = new ArrayList<>();
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			sidex.add(new int[] {a,b,d,i});
			sidex.add(new int[] {c,b,d,i});
			sidey.add(new int[] {b,a,c,i});
			sidey.add(new int[] {d,a,c,i});
		}
		boolean[] ispossible = new boolean[N];
		Arrays.fill(ispossible, true);
		Collections.sort(sidex, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if(o1[0]!=o2[0])return o1[0]-o2[0];
				if(o1[1]!=o2[1])return o1[1]-o2[1];
				return o1[2]-o2[2];
			}
		});
		int i=0;
		while(i<sidex.size()) {
			int[] use = sidex.get(i++);
			int comp = use[0];
			int max = use[2];
			while(i<sidex.size()&&sidex.get(i)[0]==comp) {
				if(sidex.get(i)[1]<=max) {
					ispossible[sidex.get(i)[3]] = false;
					ispossible[sidex.get(i-1)[3]] = false;
				}
				i++;
			}
			
		}
		Collections.sort(sidey, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if(o1[0]!=o2[0])return o1[0]-o2[0];
				if(o1[1]!=o2[1])return o1[1]-o2[1];
				return o1[2]-o2[2];
			}
		});
		i=0;
		while(i<sidey.size()) {
			int[] use = sidey.get(i++);
			int comp = use[0];
			int max = use[2];
			while(i<sidey.size()&&sidey.get(i)[0]==comp) {
				if(sidey.get(i)[1]<=max) {
					ispossible[sidey.get(i)[3]] = false;
					ispossible[sidey.get(i-1)[3]] = false;
				}
				i++;
			}
		}
		int ans =0;
		for(i=0; i<N; i++) {
			if(ispossible[i]==true)
				ans++;
		}
		System.out.println(ans);
	}

}
