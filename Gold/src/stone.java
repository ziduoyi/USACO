import java.io.*;
import java.util.*;

public class stone {
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		long ans = 0;
		int max = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}
		if(N==1) {
			System.out.println((int)Math.ceil(arr[0]/(double)2));
			return;
		}
		Arrays.sort(arr);
		int[] store = new int[2000001];
		for(int i=0; i<N; i++) 
			store[arr[i]]++;
		for(int i=1; i<2000001; i++)
			store[i] += store[i-1]; 
		for(int i=max; i>0; i--) {
			int curr = i;
			boolean work = false;
			int pos = store[i-1];
			boolean last = false; //isOdd
			long count = 1;
			int cnt = 0;
			while(pos<N) {
				int comp = curr+i-1;
				int next = store[comp];
				if(work&&(next-pos)%2==1) 
					work = false;
				if(last&&(next-pos)%2==1) {
					count *= next-pos;
					work = true;
				}
				if((next-pos)%2==1) {
					last = true;
					cnt++;
				}
				else
					last = false;
				pos = next;
				curr+=i;
			}
			if(work&&cnt!=2)work = false;
			if(!work&&cnt ==1) {
				if((store[2*i-1]-store[i-1])%2==1) {
					work = true;
					count = (store[2*i-1]-store[i-1]);
				}
			}
			if(work) ans +=count;
		}
		System.out.println(ans);
	}
}
