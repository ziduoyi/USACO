import java.util.*;
public class Knapsack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//smartp1(5, 50,new int[] {100,150,130,180,120},new int[] {20,15,20,25,15} );
		//smartp2(6, 50,new int[] {100,150,130,180,120,160},new int[] {20,15,20,25,15,25} );
		//smartp3(7,50,new int[] {50,100,150,130,180,120,160},new int[] {10,20,15,20,25,15,25} );
		smartp4(7,50, new int[]{90,100,150,130,180,120,160}, new int[]{10,20,15,20,25,15,25}, new int[]{-1,1,2,3,1,4,5});
	}
	
//	1) Smart thief I		---  0/1 knapsack
//		A smart thief is sneaking into a store and he finds some(N) valuable items. The #i item has the value v(i) and it weighs w(i). He cannot carry weights over W lbs. N, W, v(i), w(i) are all integers. please help him determine the maximum valuable items he can take during this time.
//		For example, N=5, W=50, v={100,150,130,180,120}, w={20,15,20,25,15}	
	
	public static void smartp1(int N, int W, int[] v, int[] w) {
		int[] dp=new int[W+1];

		for(int i=0;i<N;i++){
            for(int j=W;j>=w[i];j--){
            	dp[j] = Math.max(dp[j], dp[j-w[i]]+v[i]);
            }
        }
		System.out.println(dp[W]);
	}

//	2) Smart thief  II 	-- 0/1 knapsack, fill up
//	A smart thief is sneaking into a store and he finds some(N) valuable items. The #i item has the value v(i) and volume w(i). The capacity of his backpack is W. N, W, v(i), w(i) are all integers. He has to fully fill up his pack otherwise the items would be broken during running. please help him determine the maximum valuable items he can take during this time.
//
//	For example, N=6, W=50, v={100,150,130,180,120,160}, w={20,15,20,25,15,25}
	public static void smartp2(int N, int W,int[] v, int[] w) {
		int[] arr=new int[W+1];
		Arrays.fill(arr, -1);
		arr[0]=0;
		for(int i=0;i<N;i++){
            for(int j=W-w[i];j>=0;j--){
            	if(arr[j]!=-1) {
            		int x=j+w[i];
           			arr[x]=Math.max(arr[j]+v[i],arr[x]);
            	}
            }
        }
		System.out.println(arr[W]);		
	}

//3) Smart thief  III	--- complete knapsack
//A smart thief is sneaking into a store and he finds N kinds of valuable items. Each item has enough quantity to fill up his backpack. The #i item has the value v(i) and volume w(i). The capacity of his backpack is W. N, W, v(i), w(i) are all integers. He has to fully fill up his pack otherwise the items would be broken during running. please help him determine the maximum valuable items he can take during this time.
//
//For example, N=7, W=50, v={50,100,150,130,180,120,160}, w={10,20,15,20,25,15,25}	
	public static void smartp3(int N, int W,int[] v, int[] w) {
		int[] arr=new int[W+1];
		Arrays.fill(arr, -1);
		arr[0]=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<=W-w[i];j++){
            	if(arr[j]!=-1) {
            		int x=j+w[i];
            		arr[x]=Math.max(arr[j]+v[i],arr[x]);         		
            	}
            }
        }
        System.out.println(arr[W]);
	}

//4) Smart thief IV	-- multi knapsack
//A smart thief is sneaking into a store and he finds N kinds of valuable items. The #i item has the value v(i), volume w(i) and quantity q(i). Note: if q(i)==-1, it means #i item has infinite quantity. The capacity of his backpack is W. And N, W, v(i), w(i), q(i) are all integers. He has to fully fill up his pack otherwise the items would be broken during running. please help him determine the maximum valuable items he can take during this time.
//For example, N=7, W=50, v={50,100,150,130,180,120,160}, w={10,20,15,20,25,15,25}, q={-1,1,1,3,1,4,5}
//Leetcode #691
	public static void smartp4(int N, int W,int[] v, int[] w, int[] q) {
		int[] arr=new int[W+1];
		Arrays.fill(arr, -1);
		arr[0]=0;
		 for(int i=0;i<N;i++){
			 if(q[i]<0) {	// infinite
			     for(int j=0;j<=W-w[i];j++){
			     	if(arr[j]!=-1) {
			     		int x=j+w[i];
			     		arr[x]=Math.max(arr[j]+v[i],arr[x]);         		
			     	}
			     }
			 }else if(q[i]>0) {
				 for(int k=1;k<=q[i];k++) {//to prevent tle for big quantity #, apply q(i): log N --
					 int vk = v[i]*k, wk=w[i]*k;
					 if(wk>W) break;				//performance
			         for(int j=W-wk;j>=0;j--){
			         	if(arr[j]!=-1) {
			         		int x=j+wk;
			         		arr[x]=Math.max(arr[j]+vk,arr[x]);
			         	}
			         }
				 }
			 }
		 }

	    System.out.println(arr[W]);
	}
}
















