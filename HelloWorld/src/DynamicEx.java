

import java.util.*;

public class DynamicEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DynamicEx ex = new DynamicEx();
		//ex.combinationSum_complete(new int[]{1,2,3}, 4);
		System.out.println(ex.passBridge(new int[] {1,3,4,9,10}));
		//ex.combinationSum_complete(new int[]{2, 4, 5, 7, 8}, 11);
	}
	//dynamic programming: restricted knapsack
	public int combinationSum_restricted(int[] coins, int[] count, int T) {
		T++;
		int N = coins.length;
		int[] dp = new int[T];
		int V = 0;
		for(int i=0; i<N; i++)
			V = Math.max(V, coins[i]);
		Arrays.fill(dp, 10000000);
		dp[0]=0;
		TreeSet<Integer>[] que = new TreeSet[V];
		Queue<Integer>[] stuff = new Queue[V];
		Map<Integer, Integer>[] set = new HashMap[V];
		for(int j=0; j<V; j++) {
			que[j] = new TreeSet<>();
			set[j] = new HashMap<>();
			stuff[j] = new LinkedList<>();
		}
		int[] size = new int[V];
		for(int i=0; i<N; i++) {
			int val = coins[i];
			que[0].add(0);
			set[0].put(0, 1);
			stuff[0].add(0);
			size[0] = 1;
			int add = 0;
			for(int j=1; j<T; j++) {
				int pos = j%val;
				if(pos==0)
					add++;
				if(dp[j]!=10000000) {
					if(que[pos].contains(dp[j]-add)) {
						set[pos].put(dp[j]-add, set[pos].get(dp[j]-add)+1);
						stuff[pos].add(dp[j]-add);
					}
					else {
						que[pos].add(dp[j]-add);
						set[pos].put(dp[j]-add, 1);
						stuff[pos].add(dp[j]-add);
					}
				}
				if(que[pos].size()!=0) {
					int min = que[pos].pollFirst();
					que[pos].add(min);
					int comp  = min +add;
					dp[j] = Math.min(dp[j], comp);
				}
				if(size[pos]>count[i]&&stuff[pos].size()!=0) {
					int rev =  stuff[pos].poll();
					if(set[pos].get(rev)==1) {
						set[pos].remove(rev);
						que[pos].remove(rev);
					}
					else 
						set[pos].put(rev, set[pos].get(rev)-1);
				}
				size[j%val] ++;
			}
			for(int j=0; j<val; j++) {
				que[j].clear();
				set[j].clear();
				stuff[j].clear();
				size[j] = 0;
			}
		}
		return dp[T-1];
	}
	//dynamic programming: 0/1 knapsack	
    public int combinationSum_01(int[] nums, int target) {
        int[] dp = new int[target+1];   //dp[x]: the min amount of numbers used to make x
        Arrays.fill(dp,Integer.MAX_VALUE);
        Arrays.sort(nums);
		dp[0]=0;
        for(int i=0;i<nums.length;i++){
            for(int j=target-nums[i];j>=0;j--){
            	if(dp[j]<Integer.MAX_VALUE){
	                int x = j + nums[i];
	                if(x<=target)
	                	dp[x] = Math.min(dp[x],dp[j]+1); 
            	}
	        }
            System.out.println(Arrays.toString(dp));
        }
        return dp[target];
    }
    
    //dynamic programming: complete knapsack
    public int combinationSum_complete(int[] nums, int target) {
        int[] dp = new int[target+1];   //dp[x]: the min amount of numbers used to make x
        Arrays.fill(dp,Integer.MAX_VALUE);
        Arrays.sort(nums);
		dp[0]=0;
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<=target;j++){
            	if(dp[j]<Integer.MAX_VALUE){
	                int x = j + nums[i];
	                if(x<=target)
	                	dp[x] = Math.min(dp[x],dp[j]+1); 
            	}
	        }
            System.out.println(Arrays.toString(dp));
        }
        return dp[target];
    }
    
    
//bridge passing:
//  During a dark night, n cows need to pass a long bridge. They only have 1 flash light and they always need the flash light to walk through the bridge.
//  cow i need t[i] time to pass the bridge, the bridge is strong enough to support at most two cows at the same time. Please calculate the least time they need to pass the bridge
//
//     				1 3 6 9                                                    //     				1 3 4 9 
//     				--- 1 3 --->                                               //     				--- 1 3 --->
//     				<---  1  ---                                               //     				<---  1  ---
//     --- 6 9 --->				--- 1 6 --->                                   //     --- 4 9 --->				--- 1 4 --->
//     <---  3  ---				<---  1  ---                                   //     <---  3  ---				<---  1  ---
//     --- 1 3 --->    			----1 9 --->                                   //     --- 1 3 --->    			----1 9 --->
//	   --- answer---																							--- answer---
//	   dp[i] = dp[i-2] + t[0] + t[i] + t[1] + t[1]							   //	dp[i] = dp[i-1] + t[0] + t[i]
//
//     so, dp[i] = Min(dp[i-2] + t[0] + t[i] + t[1] + t[1], dp[i] = dp[i-1] + t[0] + t[i])

    public int passBridge(int[] t){
    	Arrays.sort(t);
    	int[] dp = new int[t.length];
    	dp[0] = t[0]; dp[1]=t[1];
    	
    	for(int i=2;i<t.length;i++){
    		dp[i]=Math.min(dp[i-2] + t[1] + t[1], dp[i] = dp[i-1] );
    		dp[i] +=  t[0] + t[i];
    	}
    	
    	return dp[t.length-1];
    }
}
