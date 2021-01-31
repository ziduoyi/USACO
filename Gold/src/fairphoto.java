import java.io.*;
import java.util.*;
public class fairphoto {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("fairphoto.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fairphoto.out")));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			if(st.nextToken().equals("W"))
				arr[i][1] = 1;
			else
				arr[i][1] = -1;
		}
		Arrays.sort(arr, (a,b)->a[0]-b[0]);
		int[] summary = new int[N];
		summary[0] = arr[0][1];
		for(int i=1; i<N; i++) 
			summary[i] = summary[i-1] + arr[i][1];
		int[] dp = new int[N];
		HashMap<Integer, Integer> map1 = new HashMap<>();
		TreeSet<Integer> set1 = new TreeSet<>();
		set1.add(summary[0]);
		map1.put(summary[0], 0);
		HashMap<Integer, Integer> map2 = new HashMap<>();
		TreeSet<Integer> set2 = new TreeSet<>();
		for(int i=1; i<N; i++) {
			int need = summary[i];
			if(i%2==0) {
				if(set2.floor(need)!=null) 
					dp[i] = arr[i][0] - arr[map2.get(set2.floor(need))][0];
				if(set1.floor(summary[i])==null) {
					set1.add(summary[i]);
					map1.put(summary[i], i);
				}
			}
			else {
				if(set1.floor(need)!=null) 
					dp[i] = arr[i][0] - arr[map1.get(set1.floor(need))][0];
				if(set2.floor(summary[i])==null) {
					set2.add(summary[i]);
					map2.put(summary[i], i);
				}
			}
		}
		int ans =0;
		for(int i=0; i<N; i++)
			ans = Math.max(ans, dp[i]);
		out.println(ans);
		out.close();
	}

}
