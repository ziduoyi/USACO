import java.util.*;
import java.io.*;
public class artgallery3 {
    static int[] bitree;
    static int[] data;	
    static int N;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("artgallery3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("artgallery3.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		data=new int[2*N+1];
        bitree = new int[2*N+1]; 
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++)
			data[i] = Integer.parseInt(st.nextToken());
		int[] summary = new int[2*N];
		for(int i=1; i<=N; i++) 
			summary[i] = summary[i-1] + data[i];
		for(int i=N+1; i<2*N; i++) 
			summary[i] = summary[i-1] + data[i-N];
		int[] order = summary.clone();
		Arrays.sort(order);
		Map<Integer,  Integer> map = new TreeMap<>();
		for(int i=0; i<2*N; i++) {
			if(map.get(order[i])==null) 
				map.put(order[i], i+1);
		}
		int[] dp = new int[2*N-1]; //check whole thing at end
		for(int i=1; i<2*N-1; i++) {
			dp[i] = dp[i-1];
		}
		
		
	}
    public void modify(int j, int delta) {
        for(;j<=2*N;j+=(-j&j))		//j=j+lowbit(j)
        	bitree[j] += delta;
    }
    public int sum(int j) {
    	int s=0;
        for(;j>0; j-=(-j&j))	////j=j-lowbit(j)
        	s+=bitree[j];
        return s;
    }

    public int sumRangeBit(int i, int j) {
        return sum(j+1)-sum(i);
    }

}
