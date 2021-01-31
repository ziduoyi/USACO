import java.io.*;
import java.util.*;
public class csort {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int min = Integer.MAX_VALUE;
		HashMap<Integer, Integer> map1 = new HashMap<>();
		HashMap<Integer, Integer> map2 = new HashMap<>();
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			min = Math.min(min, arr[i]);
			map1.put(arr[i], i);
		}
		int[] comp = arr.clone();
		Arrays.sort(comp);
		for(int i=0; i<N; i++)
			map2.put(comp[i], i);
		int[] next = new int[N];
		for(int i=0; i<N; i++) 
			next[map1.get(comp[i])] = i;
		long ans =0; 
		boolean[] visited = new boolean[N];
		for(int i=0; i<N; i++) {
			if(visited[i]==true)
				continue;
			if(arr[i]==comp[i]) {
				visited[i] = true;
				continue;
			}
			int pos = i;
			long sum =0;
			int count =0;
			int curr = Integer.MAX_VALUE;
			while(next[pos] != i&& visited[pos]==false) {
				visited[pos] = true;
				count++;
				curr = Math.min(curr, comp[pos]);
				sum += comp[pos];
				pos = next[pos];
			}
			ans += Math.min(count*curr+sum-curr, sum+curr+(count+2)*min);
		}
		System.out.println(ans);
	}

}
