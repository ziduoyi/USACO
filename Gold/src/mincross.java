import java.io.*;
import java.util.*;
public class mincross {
	static int[] bitree;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("mincross.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("mincross.out")));   
		int N = Integer.parseInt(br.readLine());
		int[][] store = new int[N][2];
		for(int i=0; i<N; i++)
			store[i][0] = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++)
			store[i][1] = Integer.parseInt(br.readLine());
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<N; i++) 
			map.put(store[i][0], i+1);
		
		int[] arr = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = map.get(store[i][1]);
		bitree = new int[N+1];
		long ans = 0;
		for(int i=0; i<N; i++) {
			ans += i-compute(arr[i]);
			update(arr[i],1);
		}
		long curr = ans;
		for(int i=N-1; i>-1; i--) {
			curr-= (N-compute(arr[i]));
			curr+= compute(arr[i]-1);
			ans = Math.min(ans, curr);
		}
		out.println(ans);
		out.close();
	}
	static void update(int pos, int val) {
		for(int i=pos; i<bitree.length; i+=(-i&i))
			bitree[i]+=val;
	}
	static int compute(int num) {
		int sum = 0;
		for(int j=num; j>0; j-=(-j&j))
			sum += bitree[j];
		return sum;
	}
}
