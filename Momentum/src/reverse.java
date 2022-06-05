import java.io.*;
import java.util.*;
public class reverse {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long x = Long.parseLong(st.nextToken());
		long y = Long.parseLong(st.nextToken());
		LinkedList<Integer> arr1 = binary(x);
		int size1 = arr1.removeLast();
		LinkedList<Integer> arr2 = binary(y);
		int size2 = arr2.removeLast();
		if(arr1.size()!=arr2.size()) {
			out.write("YES\n");
			out.flush();
			out.close();
			return;
		}
		
		
	}
	static LinkedList<Integer> binary(long num) {
		long mult = 1;
		while(mult<=num)
			mult*=2;
		LinkedList<Integer> list = new LinkedList<>();
		mult/=2;
		int i=0;
		while(num>0) {
			if(num>mult) 
				num-=mult;
			else
				list.add(i);
			mult/=2;	
			i++;
		}
		//list.add(i)
		return list;
	}
}
