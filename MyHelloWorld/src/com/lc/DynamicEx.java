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
//    这道题给了我们N个贴片，每个贴片上有一个小写字母的单词，给了我们一个目标单词target，让我们通过剪下贴片单词上的字母来拼出目标值，每个贴片都有无数个，问我们最少用几个贴片能拼出目标值target，如果不能拼出来的话，就返回-1。这道题博主最开始尝试用贪婪算法，结果发现不行，有网友留言提示说是多重背包问题，然后去论坛上看大神们的解法，果然都是用DP做的，之前曾有网友推荐过一个“背包九讲”的帖子，大概扫过几眼，真是叼到飞起啊，博主希望有时间也能总结一下。先来看这道题吧，既然是用DP来做，那么就需要用dp数组了，我们使用一个一维的dp数组，其中dp[i]表示组成第i个子集合所需要的最少的sticker的个数，注意这里是子集合，而不是子串。长度为n的字符串共有2的n次方个子集合，比如字符串"ab"，就有4个子集合，分别是 "", "a", "b", "ab"。字符串"abc"就有8个子集合，如果我们用0到7来分别对应其子集合，就有：
//
//    复制代码
//         abc   subset 
//    0    000     ""
//    1    001     c
//    2    010     b
//    3    011     bc
//    4    100     a
//    5    101     ac
//    6    110     bb
//    7    111     abc
//    复制代码
//    我们可以看到0到7的二进制数的每一位上的0和1就相当于mask，是1的话就选上该位对应的字母，000就表示都不选，就是空集，111就表示都选，就是abc，那么只要我们将所有子集合的dp值都算出来，最后返回dp数组的最后一个位置上的数字，就是和目标子串相等的子集合啦。我们以下面这个简单的例子来讲解：
//
//    stickers = ["ab", "bc", "ac"], target = "abc"
//
//    之前说了abc的共有8个子集合，所以dp数组的长度为8，除了dp[0]初始化为0之外，其余的都初始化为INT_MAX，然后我们开始逐个更新dp数组的值，我们的目标是从sticker中取出字符，来拼出子集合，所以如果当前遍历到的dp值仍为INT_MAX的话，说明该子集合无法被拼出来，自然我们也无法再其基础上去拼别打子集合了，直接跳过。否则我们就来遍历所有的sticker，让变量cur等于i，说明此时是在第i个子集合的基础上去reach其他的子集合，我们遍历当前sticker的每一个字母，对于sticker的每个字母，我们都扫描一遍target的所有字符，如果target里有这个字符，且该字符未出现在当前cur位置的子集合中，则将该字符加入子集合中。什么意思呢，比如当前我们的cur是3，二进制为011，对应的子集合是"bc"，此时如果我们遍历到的sticker是"ab"，那么遇到"a"时，我们知道target中是有"a"的，然后我们看"bc"中包不包括"a"，判断方法是看 (cur >> k) & 1 是否为0，这可以乍看上去不太好理解，其实不难想，因为我们的子集合是跟二进制对应的，"bc"就对应着011，第一个0就表示"a"缺失，所以我们想看哪个字符，就提取出该字符对应的二进制位，提取方法就是 cur >> k，表示cur向右移动k位，懂位操作Bit Manipulation的童鞋应该不难理解，提出出来的值与上1就知道该位是0还是1了，如果是0，表示缺失，那么把该位变为1，通过 cur |= 1 << k来实现，那么此时我们的cur就变位7，二进制为111，对应的子集合是"abc"，更新此时的dp[cur]为 dp[cur] 和 dp[i] + 1 中的较大值即可，最后循环结束后，如果"abc"对应的dp值还是INT_MAX，就返回-1，否则返回其dp值，参见代码如下：
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
