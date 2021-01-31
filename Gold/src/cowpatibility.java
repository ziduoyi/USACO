import java.io.*;
import java.util.*;
public class cowpatibility {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("cowpatibility.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("cowpatibility.out")));
		int N=Integer.parseInt(br.readLine());
		int[][] arr=new int [N][5];
		Map<Integer,BitSet> map = new HashMap<>();
		for(int i=0; i<N; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
				if(map.get(arr[i][j])==null) {
					BitSet b =new BitSet(N);
					b.set(i);
					map.put(arr[i][j], b);
				}
				else {
					BitSet b = map.remove(arr[i][j]);
					b.set(i);
					map.put(arr[i][j], b);
				}
			}
		}
		long tol=0;
		for(int i=0; i<N; i++) {
			BitSet bit = new BitSet(N);
			for(int j=0; j<5; j++) {
				BitSet curr = map.get(arr[i][j]);
				bit.or(curr);
			}
			tol+=bit.cardinality();
		}
		long l = (long)N*(long)N;
		out.println((l-tol)/2);
		out.close();
	}
}
