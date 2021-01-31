import java.io.*;
import java.util.*;
public class patterns {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		int l =1;
		int r = N;
		int max =0;
		while(l<=r) {
			int m = (l+r)/2;
			Map<Long, Integer> map = new HashMap<>();
			Long add = (long) 0;
			long stuff =1;
			boolean b =false;
			for(int i=0; i<m; i++) {
				add*=10;
				add+=arr[i];
				add%=1000000007;
				stuff*=10;
				if(i!=m-1)
					stuff%=1000000007;
			}
			map.put(add, 1);
			stuff/=10;
			for(int i=m; i<N; i++) {
				add-=arr[i-m]*(stuff);
				add+=((long)1000000007*(long)1000000007);
				add%=1000000007;
				add*=10;
				add+=arr[i];
				add%=1000000007;
				if(map.get(add)!=null) {
					map.put(add, map.get(add)+1);
					if(map.get(add)==K) {
						b = true;
						break;
					}
				}
				else
					map.put(add, 1);
			}
			if(b==true) {
				l = m+1;
				max = Math.max(max, m);
			}
			else {
				r = m-1;
			}
		}
		System.out.println(max);

	}

}
