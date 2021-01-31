import java.io.*;
import java.util.*;


public class outofsort {

	static int N;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("sort.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		int[][] A=new int[N+1][2];
		for(int i=1; i<=N; i++) {
			st=new StringTokenizer(br.readLine());
			A[i][0]=Integer.parseInt(st.nextToken());
			A[i][1]=i;
		}
		int moo = 1;
		Arrays.sort(A,(a,b)->a[0]-b[0]);
		
//		int[] sum = new int[N+1];
//		for(int i=1;i<N;i++){
//			for(int j=i;j<A[i][1];j++){
//				sum[j]++;
//			}
//		}
//		for(int i=1;i<N;i++)
//			moo=Math.max(moo,sum[i]);
		
// --- Binary Index Tree		
		B=new int[N+1];
		for(int i=1;i<N;i++){
			if(i<A[i][1]){
				edit(i,1);
				edit(A[i][1],-1);
			}
		}
//		
		for(int i=1;i<N;i++){
			moo=Math.max(moo, prefix_sum(i));
		}

//---BIT 2		
//		B=new int[N+1];
//		for(int i=1;i<N;i++){
//			modify(A[i][1]);
//			int s=prefix_sum(i);
//			moo=Math.max(moo, i-prefix_sum(i));
//		}

//--- Segment Tree
/*		
		B=new int[2*N+1];
		for(int i=1;i<N;i++){
			if(i<A[i][1]){
				modifyST(i,1);
				modifyST(A[i][1],-1);
			}
		}
		for(int i=1;i<N;i++){
			moo=Math.max(moo, sumST(i));
		}
*/
		
		out.println(moo);
		out.close();
	}
//---BIT	
	static int[] B;;
	// Concise implementation of a binary indexed tree
	static void modify(int j) {
		for (; j<=N; j+=(j&-j))		//j+lowbit(j)
			B[j]++; 
	}
	static int  prefix_sum(int j) { 
		int sum = 0; 
		for (; j>0; j-=(j&-j)) 		//j-lowbit(j)
			sum += B[j]; 
		return sum; 
	}
	static void edit(int j, int delta) {
		for (; j<=N; j+=(j&-j))		//j+lowbit(j)
			B[j]+=delta; 
	}
//---BIT
	
//---Segment
	/*
	static void modifyST(int i, int delta){
		i += N;
		for(;i>0;i/=2)
			B[i] += delta;
	}
	static int sumST(int i){
		int l=N,r=N+i;
		int sum=0;
		while(l<=r){
			if((l&1)>0)
				sum += B[l++];
			if((r&1)==0)
				sum += B[r--];
			l/=2;
			r/=2;
		}
		return sum;
	}*/

	
}
