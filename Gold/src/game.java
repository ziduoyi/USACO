import java.io.*;
import java.util.*;
public class game {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] values = new int[2000001];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)values[Integer.parseInt(st.nextToken())]++;
		for(int i=1; i<=2000000; i++) values[i] += values[i-1];
		long ans =0;
		for(int i=1; i<=1000000; i++) {
			int last = i-1;
			int curr = 2*i-1;
			int cnt = 0;
			long add = 0;
			boolean odd = false;
			boolean work = false;
			while(last<=1000000) {
				int num = values[curr]-values[last];
				if(num%2==0) 
					odd = false;
				else {
					cnt++;
					if(odd) {
						work = true;
						add = num;
					}
					odd = true;
				}
				last+=i;
				curr+=i;
			}
			if(cnt>2)work = false;
			if(cnt==1&&(values[2*i-1]-values[i-1])%2==1) {
				work = true;
				add = values[2*i-1]-values[i-1];
			}
			if(work) 
				ans += add;
			
		}
		out.write(ans+"\n");
		out.flush();
		out.close();
		
	}

}
