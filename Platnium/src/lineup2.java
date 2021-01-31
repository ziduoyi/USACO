import java.io.*;
import java.util.*;

public class lineup2 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] summary= new int[N+1][K];
		for(int i=1; i<=N; i++) {
			int num = Integer.parseInt(br.readLine());
			int pos =0;
			int sub =1;
			while(num!=0) {
				if(num%(2*sub)!=0) {
					summary[i][pos] =1;
					num-= sub;
				}
				pos++;
				sub*=2;
			}
		}
		for(int i=0; i<K; i++) {
			for(int j=1; j<=N; j++)
				summary[j][i] += summary[j-1][i]; 
		}
		int ans =0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<=N; i++) {
			long hash = 0;
			long mult = 1;
			int[] use = summary[i].clone();
			int min =Integer.MAX_VALUE;
			for(int j=0; j<K; j++) 
				min = Math.min(min, use[j]);
			for(int j=0; j<K; j++) 
				use[j] -= min;
			for(int j=0; j<K; j++) {
				hash += mult*use[j];
				hash%=1000000007;
				mult*= (N+1);
				mult%=1000000007;
			}
			if(map.get((int)hash)==null) 
				map.put((int) hash, i);
			else 
				ans = Math.max(i-map.get((int)hash), ans);
		}
		System.out.println(ans);
	}

}
