 import java.io.*;
import java.util.*;
public class cpattern {
	static long mod;
	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		mod = 1000000007;
		int[]data = new int[N];
		int[][] input = new int[N][S];
		for(int i=0; i<N; i++) {
			data[i] = Integer.parseInt(br.readLine())-1;
			input[i][data[i]] = 1;
		}
		int[][] stuff = new int[K][S];
		int[] keep = new int[K];
		TreeSet<Integer> check = new TreeSet<>();
		for(int i=0; i<K; i++) {
			keep[i] = Integer.parseInt(br.readLine())-1;
			check.add(keep[i]);
		}
		Map<Integer, Integer> map = new HashMap<>();
		int z =0;
		for(int num: check) 
			map.put(num, z++);
		for(int i=0; i<K; i++)
			keep[i] = map.get(keep[i]);  
		for(int i=0; i<K; i++)
			stuff[i][keep[i]] = 1; 
		int[] times = new int[K+1];
		times[0]=1;
		for(int i=1; i<K+1; i++) {
			times[i] = times[i-1]*2;
			times[i]%=mod;
		}
		int[][] arr = new int[N][S];
		int[][] search = new int[K][S];
		for(int i=0; i<S; i++) {
			arr[0][i]=input[0][i];
			for(int j =1; j<K; j++) {
				arr[j][i] = arr[j-1][i] + times[j]*input[j][i];
				arr[j][i] %= mod;
			}
			for(int j = K; j<N; j++) {
				long thing = arr[j-1][i]-input[j-K][i];
				thing += times[K]*input[j][i];
				if(thing%2==1)
					thing+=mod;
				thing/=2;
				arr[j][i] = (int) thing;
				arr[j][i] %= mod;
			}
		}
		for(int i=0; i<S; i++) {
			search[0][i]=stuff[0][i];
			for(int j =1; j<K; j++) {
				search[j][i] = search[j-1][i] + times[j]*stuff[j][i];
				search[j][i] %= mod;
			}
		}
		ArrayList<Integer> ans = new ArrayList<>();
		
		HashMap<Integer, Integer> count = new HashMap<>();
		TreeSet<Integer> set = new TreeSet<>();
		for(int i=0; i<=N-K; i++) {
			boolean b = false;
			if(i==0) {
				for(int j=0; j<K; j++) {
					if(set.contains(data[j]))
						count.put(data[j], count.get(data[j])+1);
					else {
						set.add(data[j]);
						count.put(data[j], 1);
					}
				}
			}
			else {
				if(count.get(data[i-1])>1)
					count.put(data[i-1], count.get(data[i-1])-1);
				else {
					count.remove(data[i-1]);
					set.remove(data[i-1]);
				}
				if(set.contains(data[i+K-1]))
					count.put(data[i+K-1], count.get(data[i+K-1])+1);
				else {
					set.add(data[i+K-1]);
					count.put(data[i+K-1], 1);
				}
			}
			int j =0;
			for(int num: set) {
				if(search[K-1][j] != arr[i+K-1][num]) {
					b =true;
					break;
				}
				j++;
			}
			if(b==false)
				ans.add(i);
		}
		System.out.println(ans.size());
		for(int i=0; i<ans.size(); i++)
			System.out.println(ans.get(i)+1);
	}
}
