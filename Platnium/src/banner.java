import java.util.*;
import java.io.*;
public class banner {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		long count1 = 0;
		long count2 = 0;
		for(int i=1; i<=M; i++) {
			int l =1;
			int r = N;
			while(l<=r) {
				int m = (l+r)/2;
				double comp = Math.sqrt((double) i * i + (double)m *m);
				if(comp>H) 
					r = m-1;
				else 
					l = m+1;
				
			}
			if(Math.sqrt((double) i * i + (double)l *l)>H)
				l --;
			if(l!=0)
				count1 += 2*(long)(M+1-i) * ((long)(N)*(N+1)/2 - (long)(N-l)*(N+1-l)/2)%B;
			count1 %= B;
		}
		for(int i=1; i<=M; i++) {
			int l =1;
			int r = N;
			while(l<=r) {
				int m = (l+r)/2;
				double comp = Math.sqrt((double) i * i + (double)m *m);
				if(comp>=L) 
					r = m-1;
				else 
					l = m+1;
			}
			if(Math.sqrt((double) i * i + (double)l *l)>=L)
				l --;
			if(l!=0)
				count2 += 2*(long)(M+1-i) * ((long)(N)*(N+1)/2 - (long)(N-l)*(N+1-l)/2)%B;
			count2 %= B;
		}
		long ans = count1 - count2;
		if(L==1)
			ans += (long)(N+1)*(M) + (long)(M+1)*(N);
		if(ans<0)
			ans+=(long)B*B;
		ans%=B;
		int[] counts = new int[M+1];
		int[][] divisors = new int[M+1][6];
		for(int i=2; i<=M; i++) {
			if(counts[i]==0) {
				for(int j=i; j<=M; j+= i) 
					divisors[j][counts[j]++] = i;
			}
		}
		for(int i =2; i<= M; i++) {
			if(i>=H)break;
			int l =1;
			int r = N;
			while(l<=r) {
				int m = (l+r)/2;
				double comp = Math.sqrt((double) i * i + (double)m *m);
				if(comp>H) 
					r = m-1;
				else 
					l = m+1;
			}
			if(Math.sqrt((double) i * i + (double)l *l)>H)
				l --;
			int right = l;
			l =1;
			r = N;
			while(l<=r) {
				int m = (l+r)/2;
				double comp = Math.sqrt((double) i * i + (double)m *m);
				if(comp>L) 
					r = m-1;
				else 
					l = m+1;
			}
			if((double)l*l+(double)i*i<L)
				l++;
			int left = l;
			long sub = 0;
			int[] store = divisors[i].clone();
			int size = 0;
			for(int j=0; j<6; j++) {
				if(store[j]==0)break;
				size++;
			}
			int[] use = new int[size];
			for(int j=0; j<size; j++)
				use[j] = store[j];
			if(left<right) {
				for(int j=0; j<6; j++) {
					if(store[j]==0)break;
					if(j%2==0)
						sub += recursion(use, j+1, 0, 0, left, right, 1, B, N);
					else
						sub -= recursion(use, j+1, 0, 0, left, right, 1, B, N);
					if(sub<0)
						sub += (long)B*B;
					sub %= B;
				}
			}
			ans-=2*(long)(M+1-i) * sub;
			ans+=(long)B*B;
			ans%= B;
		}
		System.out.println(ans);
	}
	static long recursion(int[] arr, int size, int curr, int pos, int l, int r, int product, int B, int N) {
		if(curr==size) {
			int start = (int) Math.ceil((double)l/product);
			int end = (int) Math.floor((double)r/product);
			if(start<=end)
				return (((long)((N+1-start*(long)product)+(N+1-end*(long)product))*((end-start)+1))/2)%B;
			else
				return 0;
		}
		long sum =0;
		for(int i=pos; i<arr.length-(size- curr)+1; i++) {
			sum+= recursion(arr, size, curr+1, i+1, l, r, product*arr[i], B, N);
			sum%= B;
		}
		return sum;
	}
}
