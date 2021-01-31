import java.util.*;
import java.io.*;
public class haircut {
	static int[] arr;
	static int N;
	static int[] bitree;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br= new BufferedReader(new FileReader("haircut.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("haircut.out")));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		bitree = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer>[] count = new ArrayList[N];
		for(int i=0; i<N; i++) 
			count[i] = new ArrayList<>();
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			arr[i] = Math.min(N-1, arr[i]);
			count[arr[i]].add(i);
		}
		long[] ans = new long[N];
		ans[N-1] = mergeCal(0, N-1);
		ArrayList<Integer> al = count[N-1];
		for(int i=0; i<al.size(); i++) 
			modify(al.get(i)+1, 1);
		for(int i=N-2; i>-1; i--) {
			ans[i] = ans[i+1];
			ArrayList<Integer> use = count[i];
			int s= use.size();
			for(int j=0; j<s; j++) 
				ans[i] -=sum(use.get(j));
			for(int j=0; j<s; j++)
				modify(use.get(j)+1, 1);
		}
		for(int i=0; i<N; i++)
			out.println(ans[i]);
		out.close();
	}
    static void modify(int j, int delta) {
        for(;j<=N;j+=(-j&j))		//j=j+lowbit(j)
        	bitree[j] += delta;
    }
    static int sum(int j) {
    	int s=0;
        for(;j>0; j-=(-j&j))	////j=j-lowbit(j)
        	s+=bitree[j];
        return s;
    }
    
    
	static long mergeCal(int start, int end) {
		if(start==end)
			return 0;
		int mid = (start+end)/2;
		long tol = mergeCal(start, mid)+mergeCal(mid+1, end);
		int[] com1 = Arrays.copyOfRange(arr, start, mid+1);
		int[] com2 = Arrays.copyOfRange(arr, mid+1, end+1);
		Arrays.sort(com1);
		Arrays.sort(com2);
		int j=0;
		for(int i=0; i<com1.length; i++) {
			if(j!=com2.length) {
				while(com1[i]>com2[j]) {
					j++;
					if(j==com2.length)
						break;
				}
			}
			tol+=j;
		}
		return tol;
	}

}
