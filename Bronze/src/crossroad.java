import java.io.*;
import java.util.*;
public class crossroad {
	static HashMap<Integer, Integer> map;
	static long mod;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // or use Scanner if you prefer
		int Q = Integer.parseInt(br.readLine());
		mod = 1000000007L;
		map = new HashMap<>();
		map.put(-2,-1);
		map.put(0, 0);
		map.put(1, 1);
		map.put(-1,1);
		map.put(2, 1);
		for(int i=0; i<Q; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int f1 = Integer.parseInt(st.nextToken());
			int f2 = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			long first = compute(n-1);
			long second = compute(n);
			System.out.println(((first*f1)%mod + (f2 *second)%mod+2*mod)%mod);
		}
	}
	static long compute(int num) {
		if(map.containsKey(num)) 
			return map.get(num);
		if(num%2==0) {
			map.put(num, (int) (((2*compute(num/2-1)+compute(num/2))*compute(num/2))%mod));
			return map.get(num);
		}
		else {
			map.put(num, (int) ((compute((num+1)/2)*compute((num+1)/2) + (compute((num+1)/2-1)*compute((num+1)/2-1))%mod)));
			return map.get(num);
		}
	}
}
