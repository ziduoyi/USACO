import java.io.*;
import java.util.*;
public class plank {
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> que = new PriorityQueue<>();
		for(int i=0; i<N; i++)
			que.add(Integer.parseInt(br.readLine()));
		long ans =0;
		while(que.size()!=1) {
			int add = que.poll()+que.poll();
			ans+=add;
			que.add(add);
		}
		System.out.println(ans);
	}
}