import java.io.*;
import java.util.*;
public class acquire {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[][] arr = new long[N][3];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Long.parseLong(st.nextToken());
			arr[i][1] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(arr, new Comparator<long[]>() {
			@Override
			public int compare(long[] o1, long[] o2) {
				// TODO Auto-generated method stub
				if(o1[0]!=o2[0]) return (int) (o1[0]-o2[0]);
				return (int) (o1[1]-o2[1]);
			}
		});
		long[] max = new long[N];
		max[N-1] = arr[N-1][1];
		for(int i=N-2; i>-1; i--) 
			max[i] = Math.max(max[i+1], arr[i][1]);
		for(int i=0; i<N; i++) {
			if(arr[i][1]<max[i]) {
				arr[i][2] =1;
			}
		} 
		ArrayList<Long[]> use = new ArrayList<>();
		for(int i=0; i<N; i++) {
			if(arr[i][2]==0) {
				Long[] temp = new Long[] {arr[i][0], arr[i][1]};
				use.add(temp);
			}
		}
		long[] dp = new long[use.size()+1];
		dp[0] =0;
		dp[1] = use.get(0)[0]*use.get(0)[1];
		ArrayList<Integer> al1 = new ArrayList<>();
		ArrayList<Double> al2 = new ArrayList<>();
		al1.add(0);
		al2.add((double) 0);
		for(int i=1; i<use.size(); i++) {
			long l = 0;
			long r = al1.size()-1;
			double save =0;
			while(l<=r) {
				int m = (int) ((l+r)/2);
				double longer = ((double)(dp[i]-dp[m]))/((double)(use.get(al1.get(m))[1]-use.get(i)[1]));
				if(longer<=al2.get(m)) {
					r = m-1;
					save = longer;
				}
				else
					l = m+1;
			}
			if(l==al1.size())
				save = (double)(dp[(int) (i)]-dp[(int) (l-1)])/(double)(use.get(al1.get((int) (l-1)))[1]-use.get((int) (i))[1]);
			else
				save = (double)(dp[i]-dp[(int) l-1])/(double)(use.get(al1.get((int) l-1))[1]-use.get((int) (i))[1]);
			long s = al1.size();
			for(long j=0; j<s-l; j++) {
				al1.remove(al1.size()-1);
				al2.remove(al2.size()-1);
			}
			
			al1.add(i);
			al2.add(save);
			long pos = Collections.binarySearch(al2, (double)use.get(i)[0]);
			if(pos<0) {
				pos++;
				pos*=-1;
				if(pos!=0)
					pos--;
			}
			if(pos==al2.size())
				pos--;
			long need = al1.get((int) pos);
			dp[i+1] = dp[(int) need] + use.get(i)[0] * use.get((int) need)[1];
		}
		System.out.println(dp[use.size()]);

		
	}

}
