package com.lc;

import java.util.*;

public class DynamicEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DynamicEx ex = new DynamicEx();
		//ex.combinationSum_complete(new int[]{2, 4, 5, 7, 8}, 11);
		//ex.combinationSum_01(new int[]{2, 4, 5, 7, 8}, 11);
		//ex.combinationSum(new int[]{1, 2, 3}, 4);
		
		//ArrayList<String> list = new ArrayList<>();
		//ex.subset("abcde", list,0,2,"");
		//System.out.println("");
		
		//ex.knapsackI(5, 50, new int[]{100,150,130,180,120}, new int[]{20,15,20,25,15});
		//ex.knapsackII(6,50, new int[]{100,150,130,180,120,160},new int[]{20,15,20,25,15,25});
		//ex.knapsackIII(7, 50, new int[]{50,100,150,130,180,120,160}, new int[]{10,20,15,20,25,15,25});
		ex.knapsackIV(7,50, new int[]{50,100,150,130,180,120,160}, new int[]{10,20,15,20,25,15,25}, new int[]{-1,1,1,3,1,4,5});
	}

	void subset(String str, ArrayList<String> list, int pos,int len,String com){
		if(com.length()==2*len){
			list.add(com);
			return;
		}
		if(pos==str.length())return;
		subset(str,list,pos+1,len,com+" "+pos);
		subset(str,list,pos+1,len,com);
	}
	
//#377
    public int combinationSum(int[] nums, int target) {
    	/*Arrays.sort(nums);
        int[][] dp = new int[target+1][target+1];       //dp[x][v] = E(dp[x-1][v-nums[i]])
        dp[0][0]=1;
        for(int i=0;i<target;i++){
            for(int j=0;j<nums.length;j++){
                int x=i+nums[j];
                if(x>target || nums[j]>target)break;
               	dp[i+1][x] += dp[i][i];
            }
        }
        int res = 0;
        for(int i=0;i<=target;i++){
        	res+=dp[i][target];
        }
        return res;*/
    	
    	Arrays.sort(nums);
        int[][] dp = new int[target+1][target+1];       //dp[x][v] = E(dp[x-1][v-nums[i]])
        dp[0][0]=1;
        for(int i=0;i<target;i++){
            for(int j=0;j<target;j++){
                int x=i+1,y=j+1;
                //if(x>target || nums[j]>target)break;
                for(int k : nums){
                	if(y-k<0)break;
                	dp[i+1][y] += dp[i][y-k];
                }
                //dp[i+1][y]+=dp[i][y];
            }
        }
        int res = 0;
        for(int i=0;i<=target;i++){
        	res+=dp[i][target];
        }
        return res;
        /*
        int[] dp = new int[target+1];   //dp[x]: the # of ways to make sum as x
        dp[0]=1;
        Arrays.sort(nums);
        for(int i=0;i<=target;i++){
            for(int j=0;j<nums.length;j++){
                int x = i+nums[j];
                if(x>target)break;
                dp[x] += dp[i];
            }
        }
        return dp[target];
        */
    }
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];   //dp[x]: the # of ways to make sum as x
        dp[0]=1;
        for(int i=0;i<=target;i++){
            for(int j=0;j<nums.length;j++){
                int x = i + nums[j];
                if((x>target))break;
                dp[x]+=dp[i];
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[target];
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
	

//322. Coin Change     knapsack	
    public int coinChange(int[] coins, int amount) {
        if(amount==0) return 0;
        if(coins==null || coins.length==0) return -1;
        int len = coins.length;
        int[] dp = new int[amount+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=0;i<len;i++){
            for(int j=coins[i];j<=amount;j++){
                if(dp[j-coins[i]]<Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j],dp[j-coins[i]]+1);
            }
        }
        return dp[amount]<Integer.MAX_VALUE?dp[amount]:-1;
    }
    
	
//63. Unique Paths II	
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m+1][n+1];
        dp[0][1]=1;
        for(int i=1;i<=m;i++){
        	for(int j=1;j<=n;j++){
        		if(grid[i-1][j-1]>0)
        			dp[i][j]=0;
        		else
        			dp[i][j]=dp[i-1][j] + dp[i][j-1];
        	}
        }
        return dp[m][n];
    }
    
//bridge passing:
//  During a dark night, n cows need to pass a long bridge. They only have 1 flash light and they always need the flash light to walk through the bridge.
//  cow i need t[i] time to pass the bridge, the bridge is strong enough to support at most two cows at the same time. Please calculate the least time they need to pass the bridge
//
//     				1 3 6 9                                                    //     				1 3 4 9 
//     				--- 1 3 --->        dp[i-2]                                //     				--- 1 3 --->		dp[i-2]
//     				<---  1  ---                                               //     				<---  1  ---
//     --- 6 9 --->				--- 1 6 --->                                   //     --- 4 9 --->				--- 1 4 --->	dp[i-1]
//     <---  3  ---				<---  1  ---                                   //     <---  3  ---				<---  1  ---
//     --- 1 3 --->    			----1 9 --->                                   //     --- 1 3 --->    			----1 9 --->
//	   --- answer---																							--- answer---
//	   dp[i] = dp[i-2] + t[1] + t[i] + t[2] + t[2]							   //	dp[i] = dp[i-1] + t[1] + t[i]
//
//     so, dp[i] = Min(dp[i-2] + t[1] + t[i] + t[2] + t[2], dp[i] = dp[i-1] + t[1] + t[i])

    public int passBridge(int[] t){
    	Arrays.sort(t);
    	int[] dp = new int[t.length];
    	dp[0] = t[0]; dp[1]=t[1];
    	
    	for(int i=2;i<t.length;i++){
    		dp[i]=Math.min(dp[i-2] + t[2] + t[2], dp[i-1] );
    		dp[i] +=  t[1] + t[i];
    	}
    	return dp[t.length-1];
    }
    
    
// 1) Smart thief  I
//    A smart thief is sneaking into a store and he finds some(N) valuable items. The #i item has the value v(i) and it weighs w(i). He cannot carry weights over W lbs. N, W, v(i), w(i) are all integers. please help him determine the maximum valuable items he can take during this time.
//    For example, N=5, W=50, v={100,150,130,180,120}, w={20,15,20,25,15}

    public int knapsackI(int N, int W, int[] v, int[] w){
    	int[] dp = new int[W+1];

    	for(int i=0;i<N;i++){
    		for(int j=W;j>=w[i];j--){
    			dp[j] = Math.max(dp[j], dp[j-w[i]]+v[i]);
    		}
    	}

    	int res=0;
    	for(int x : dp){
    		res=Math.max(res, x);
    	}

    	return res;
    }

//
//  2) Smart thief  II
//    A smart thief is sneaking into a store and he finds some(N) valuable items. The #i item has the value v(i) and volume w(i). The capacity of his backpack is W. N, W, v(i), w(i) are all integers. He has to fully fill up his pack otherwise the items would be broken during running. please help him determine the maximum valuable items he can take during this time.
//    For example, N=6, W=50, v={100,150,130,180,120,160}, w={20,15,20,25,15,25}

    public int knapsackII(int N, int W, int[] v, int[] w){
    	int[] dp = new int[W+1];
    	Arrays.fill(dp, -1);
    	dp[0]=0;

    	for(int i=0;i<N;i++){
    		for(int j=W;j>=w[i];j--){
    			if(dp[j-w[i]]>=0)
    				dp[j] = Math.max(dp[j], dp[j-w[i]]+v[i]);
    		}
    	}

    	return dp[W];
    }
    
//
//  3) Smart thief  III
//    A smart thief is sneaking into a store and he finds N kinds of valuable items. Each item has enough quantity to fill up his backpack. The #i item has the value v(i) and volume w(i). The capacity of his backpack is W. N, W, v(i), w(i) are all integers. He has to fully fill up his pack otherwise the items would be broken during running. please help him determine the maximum valuable items he can take during this time.
//    For example, N=7, W=50, v={50,100,150,130,180,120,160}, w={10,20,15,20,25,15,25}
    public int knapsackIII(int N, int W, int[] v, int[] w){
    	int[] dp = new int[W+1];
    	Arrays.fill(dp, -1);
    	dp[0]=0;

    	for(int i=0;i<N;i++){
    		for(int j=0;j<=W-w[i];j++){
    			if(dp[j]>=0)
    				dp[j+w[i]] = Math.max(dp[j+w[i]], dp[j]+v[i]);
    		}
    	}

    	return dp[W];
    }
//
//
//  4) Smart thief IV
//  	A smart thief is sneaking into a store and he finds N kinds of valuable items. The #i item has the value v(i), volume w(i) and quantity q(i). Note: if q(i)==-1, it means #i item has infinite quantity. The capacity of his backpack is W. N, W, v(i), w(i) are all integers. He has to fully fill up his pack otherwise the items would be broken during running. please help him determine the maximum valuable items he can take during this time.
//  	For example, N=7, W=50, v={50,100,150,130,180,120,160}, w={10,20,15,20,25,15,25}, q={-1,1,1,3,1,4,5}
//    Leetcode #691    
    public int knapsackIV(int N, int W, int[] v, int[] w, int[] q){
    	int[] dp = new int[W+1];
    	Arrays.fill(dp, -1);
    	dp[0]=0;

    	for(int i=0;i<N;i++){
    		if(q[i]<0)
	    		for(int j=0;j<=W-w[i];j++){
	    			if(dp[j]>=0)
	    				dp[j+w[i]] = Math.max(dp[j+w[i]], dp[j]+v[i]);
	    		}
    		else if(q[i]>0){
    			for(int k=1;k<=q[i];k++){
    				int wk=k * w[i], vk=k*v[i];
    				for(int j=W;j>=wk;j--){
    					if(dp[j-wk]>=0)
    						dp[j] = Math.max(dp[j], dp[j-wk]+vk);
    				}
    			}
    		}
    	}

    	return dp[W];
    }
    
//  Leetcode #691
//    ������������N����Ƭ��ÿ����Ƭ����һ��Сд��ĸ�ĵ��ʣ���������һ��Ŀ�굥��target��������ͨ��������Ƭ�����ϵ���ĸ��ƴ��Ŀ��ֵ��ÿ����Ƭ�����������������������ü�����Ƭ��ƴ��Ŀ��ֵtarget���������ƴ�����Ļ����ͷ���-1������ⲩ���ʼ������̰���㷨��������ֲ��У�������������ʾ˵�Ƕ��ر������⣬Ȼ��ȥ��̳�Ͽ������ǵĽⷨ����Ȼ������DP���ģ�֮ǰ���������Ƽ���һ���������Ž��������ӣ����ɨ�����ۣ����ǵ𵽷��𰡣�����ϣ����ʱ��Ҳ���ܽ�һ�¡������������ɣ���Ȼ����DP��������ô����Ҫ��dp�����ˣ�����ʹ��һ��һά��dp���飬����dp[i]��ʾ��ɵ�i���Ӽ�������Ҫ�����ٵ�sticker�ĸ�����ע���������Ӽ��ϣ��������Ӵ�������Ϊn���ַ�������2��n�η����Ӽ��ϣ������ַ���"ab"������4���Ӽ��ϣ��ֱ��� "", "a", "b", "ab"���ַ���"abc"����8���Ӽ��ϣ����������0��7���ֱ��Ӧ���Ӽ��ϣ����У�
//
//    ���ƴ���
//         abc   subset 
//    0    000     ""
//    1    001     c
//    2    010     b
//    3    011     bc
//    4    100     a
//    5    101     ac
//    6    110     bb
//    7    111     abc
//    ���ƴ���
//    ���ǿ��Կ���0��7�Ķ���������ÿһλ�ϵ�0��1���൱��mask����1�Ļ���ѡ�ϸ�λ��Ӧ����ĸ��000�ͱ�ʾ����ѡ�����ǿռ���111�ͱ�ʾ��ѡ������abc����ôֻҪ���ǽ������Ӽ��ϵ�dpֵ�����������󷵻�dp��������һ��λ���ϵ����֣����Ǻ�Ŀ���Ӵ���ȵ��Ӽ���������������������򵥵����������⣺
//
//    stickers = ["ab", "bc", "ac"], target = "abc"
//
//    ֮ǰ˵��abc�Ĺ���8���Ӽ��ϣ�����dp����ĳ���Ϊ8������dp[0]��ʼ��Ϊ0֮�⣬����Ķ���ʼ��ΪINT_MAX��Ȼ�����ǿ�ʼ�������dp�����ֵ�����ǵ�Ŀ���Ǵ�sticker��ȡ���ַ�����ƴ���Ӽ��ϣ����������ǰ��������dpֵ��ΪINT_MAX�Ļ���˵�����Ӽ����޷���ƴ��������Ȼ����Ҳ�޷����������ȥƴ����Ӽ����ˣ�ֱ���������������Ǿ����������е�sticker���ñ���cur����i��˵����ʱ���ڵ�i���Ӽ��ϵĻ�����ȥreach�������Ӽ��ϣ����Ǳ�����ǰsticker��ÿһ����ĸ������sticker��ÿ����ĸ�����Ƕ�ɨ��һ��target�������ַ������target��������ַ����Ҹ��ַ�δ�����ڵ�ǰcurλ�õ��Ӽ����У��򽫸��ַ������Ӽ����С�ʲô��˼�أ����統ǰ���ǵ�cur��3��������Ϊ011����Ӧ���Ӽ�����"bc"����ʱ������Ǳ�������sticker��"ab"����ô����"a"ʱ������֪��target������"a"�ģ�Ȼ�����ǿ�"bc"�а�������"a"���жϷ����ǿ� (cur >> k) & 1 �Ƿ�Ϊ0�������է����ȥ��̫����⣬��ʵ�����룬��Ϊ���ǵ��Ӽ����Ǹ������ƶ�Ӧ�ģ�"bc"�Ͷ�Ӧ��011����һ��0�ͱ�ʾ"a"ȱʧ�����������뿴�ĸ��ַ�������ȡ�����ַ���Ӧ�Ķ�����λ����ȡ�������� cur >> k����ʾcur�����ƶ�kλ����λ����Bit Manipulation��ͯЬӦ�ò�����⣬���������ֵ����1��֪����λ��0����1�ˣ������0����ʾȱʧ����ô�Ѹ�λ��Ϊ1��ͨ�� cur |= 1 << k��ʵ�֣���ô��ʱ���ǵ�cur�ͱ�λ7��������Ϊ111����Ӧ���Ӽ�����"abc"�����´�ʱ��dp[cur]Ϊ dp[cur] �� dp[i] + 1 �еĽϴ�ֵ���ɣ����ѭ�����������"abc"��Ӧ��dpֵ����INT_MAX���ͷ���-1�����򷵻���dpֵ���μ��������£�
//    
    public int minStickers(String[] stickers, String target) {
        int n=target.length();
        int N = 1<<n;
        int[] dp = new int[N];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for(int state=0;state<N;state++){
            if(dp[state]==Integer.MAX_VALUE) continue;
            for(String s : stickers){
                int now=state;
                for(char c : s.toCharArray()){
                    for(int i=0;i<n;i++){
                        if(((now>>i) & 1)==0){
                            char ch = target.charAt(i);
                            if(c==ch){
                                now = now | (1<<i);
                                break;
                            }
                        }
                    }
                }
                if(now>state)
                    dp[now]=Math.min(dp[now],dp[state]+1);
            }
        }
        return dp[N-1]==Integer.MAX_VALUE?-1:dp[N-1];
    }
}
