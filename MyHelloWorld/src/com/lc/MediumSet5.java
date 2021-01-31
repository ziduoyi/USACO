package com.lc;

import java.util.*;

public class MediumSet5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WordDictionary dict = new WordDictionary();
		dict.addWord("a");
		dict.addWord("ab");
		boolean rs=dict.search("a");
		System.out.println(rs);
				
		//compareVersion("1.0","0.1");
		
		//twoSum(new int[] {2, 7, 11, 15}, 9);
		//myAtoi("-+1");
		
		//kClosest(new int[][]{{1,0},{0,1}},2);
		
		//System.out.println(findKthLargest(new int[] {-1,2,0},2));
		
		int[][] A = new int[][]{
		{1, 8, 2, 3, 6, 4},
		{8, 5, 1, 2, 9, 9},
		{6, 4, 3, 9, 0, 0},
		{0, 0, 0, 7, 8, 9},
		{2, 4, 5, 9, 5, 9},
		{2, 5, 5, 9, 5, 9},
		{6, 5, 4, 3, 2, 1},
		{8, 1, 2, 4, 3, 6},
		{9, 2, 8, 6, 7, 0},
		{0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 1},
		{2, 3, 5, 9, 5, 9},
		{4, 4, 4, 5, 9, 9},
		{0, 0, 0, 9, 9, 9},
		{0, 0, 0, 0, 9, 9},
		{9, 9, 9, 9, 9, 9}};
		for(int[] a : A)
			getTime(a);
	}

//#1080 Insufficient Nodes in Root to Leaf Paths 
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        return postOrder(root, limit,0);
    }

    TreeNode postOrder(TreeNode t, int limit, int sum){
        if(t==null) return null;
        sum += t.val;
        boolean hasChild = false;
        if(t.left==null && t.right==null){
            if(sum<limit) return null;
        }else
            hasChild = true;

        t.left = postOrder(t.left, limit, sum);
        t.right=postOrder(t.right, limit, sum);
        
        if(hasChild && t.left==null && t.right==null)
            return null;
        return t;
    }	
//#1090. Largest Values From Labels
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        int len = values.length;
        Integer[] idx = new Integer[len];
        for(int i=0;i<len;i++)
            idx[i]=i;
        if(len>1)
            Arrays.sort(idx,(a,b)->values[b]-values[a]);
        
        int[] num = new int[20001];
        int sum=0,i=0;
        while(num_wanted>0 && i<len){
            int x = idx[i++];
            if(num[labels[x]]<use_limit){
                sum+=values[x];
                num[labels[x]]++;
                num_wanted--;
            }
        }
        return sum;
    }    
    
//#165. Compare Version Numbers    
    public static int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        
        System.out.println(Arrays.toString(v1));
        System.out.println(Arrays.toString(v2));
        
        int i=0;
        while(i<v1.length || i<v2.length){
            int x=0,y=0;
            if(i<v1.length)
                x=Integer.parseInt(v1[i]);
            if(i<v2.length)
                y=Integer.parseInt(v2[i]);
            System.out.println("x="+x + "; y="+y);
            if(x>y)
                return 1;
            else if(x<y)
                return -1;
            else
                i++;
        }
        return 0;
    }
    
    
    static int[] twoSum(int[] nums, int target) {
        int[] res =new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
        	long y=0l + target - nums[i];		//overflow???  long y=0l + target - nums[i];
            if(Integer.MIN_VALUE<y && y<Integer.MAX_VALUE && map.containsKey((int)y)){
                res[0]=map.get((int)y);
                res[1]=i;
                return res;
            }
            map.put(nums[i],i);
        }
        /*
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                long x = 0L+ nums[i]+nums[j];
                if(Integer.MIN_VALUE<x && x<Integer.MAX_VALUE && x==target){
                    res[0]=i;
                    res[1]=j;
                    return res;
                }
            }
        }*/
        return res;
    }
    
    static int myAtoi(String str) {
        if(str==null || str.length()<1) return 0;
        String s = str.trim();
        if(s.length()<1 || "-".equals(s))return 0;
        if('0'<=s.charAt(0) && s.charAt(0)<='9' || s.charAt(0)=='-'){
            for(int i=1;i<s.length();i++){
                char c = s.charAt(i);
                if(c<'0' || c>'9'){
                    s=s.substring(0,i);
                    break;
                }
            }
            long res = Long.parseLong(s);
            if(res>Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            else if( res<Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            else
                return (int)res;
        }else
            return 0;
    }
    
    static int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][2];
        
        int[] dist = new int[points.length];
        TreeSet<Integer> set = new TreeSet<>((a,b)->(dist[a]-dist[b]));
        
        for(int i=0;i<points.length;i++){
            dist[i]=points[i][0]*points[i][0] + points[i][1]*points[i][1];
            set.add(i);
        }
        
        int i=0;
        for(int x : set){
            res[i][0]=points[x][0];
            res[i][1]=points[x][1];
            if(++i==K) break;
        }
        return res;
    }
    
    
//# 380. Insert Delete GetRandom O(1)
    class RandomizedSet {
        HashMap<Integer,Integer> map = null;
        ArrayList<Integer> list = null;
        
        /** Initialize your data structure here. */
        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList();
        }
        
        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(map.containsKey(val)) return false;
            map.put(val,list.size());
            list.add(val);
            return true;
        }
        
        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(map.containsKey(val)){
                int x = map.remove(val);
                if(x==list.size()-1)
                    list.remove(x);
                else{
                    int p = list.get(list.size()-1);
                    list.set(x,p);
                    map.put(p,x);
                    list.remove(list.size()-1);
                }
                return true;
            }
            return false;
        }
        
        /** Get a random element from the set. */
        public int getRandom() {
            int rand = (int)(Math.random()*10);
            int x = rand%list.size();
            if(x<0)x=-x;
            return list.get(x);
        }
    }

//#253. Meeting Rooms II
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->a[0]==b[0]?a[1]-b[1]:a[0]-b[0]);
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for(int[] m : intervals){
            if(!queue.isEmpty() && queue.peek()<=m[0])
                queue.poll();
            queue.add(m[1]);
        }
        return queue.size();
    }
    
//#277. Find the Celebrity
    boolean knows(int i, int j) {return false;}	//just for compile here
    public int findCelebrity(int n) {
        int res = 0;

        for(int i=0;i<n;i++){
            if(res!=i && knows(res,i))
                res = i;
        }

        for(int i=0;i<res;i++){
            if(knows(res,i))
               return -1;
        }
        
        for(int i=0;i<n;i++){
            if(i!=res && !knows(i,res))
               return -1;
        }
        return res;
    }
    
//215. Kth Largest Element in an Array
    static Random rand = null;
    public static int findKthLargest(int[] nums, int k) {
        rand = new Random();
        return helper(nums, k, 0, nums.length-1);
    }
    
    static int helper(int[] nums, int k, int x, int y){
        if(x==y) return nums[x];
        //if(y-x<2) return k>1?Math.min(nums[y],nums[x]):Math.max(nums[x],nums[y]);
        
        //int r = rand.nextInt(y-x)+x; 
        int r=(x+y)/2;
        swap(nums,r,y);    //swap
        int i=x, j=y-1;
        while(i<=j){
            if(nums[i]<=nums[y]){ 
                i++;
                continue;
            }
            if(nums[j]>=nums[y]){
                j--;
                continue;
            }
            swap(nums,i,j);
        }
        if(i<y)
            swap(nums,i,y);
        
        int m=y-i+1;
        if(m>k)
            return helper(nums,k,i+1,y);		// !!! must be i-1, shift at least one position
        else if(m<k)
            return helper(nums,k-m,x,i-1);		// !!! must be i+1, shift at least one position
        else 
            return nums[i];
    }
    
    static void swap(int[] nums, int i, int j){
        int t=nums[i];
        nums[i]=nums[j];
        nums[j]=t;
    }
 
    
//#979. Distribute Coins in Binary Tree
/*    Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.

In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)

Return the number of moves required to make every node have exactly one coin.    */
    
    static int distributeCoins(TreeNode root) {
    	if(root==null) return 0;
    	int l = distributeCoins(root.left);
    	int r = distributeCoins(root.right);
    	
    	return root.val + Math.abs(l) + Math.abs(r) - 1;
    }
    
//#1102. Path With Maximum Minimum Value    
    int[][] dir = new int[][]{{-1,0},{0,-1},{1,0},{0,1}};
    int[][] M = null;
    int R=0,C=0;
    public int maximumMinimumPath(int[][] A) {
        M = A;
        R = M.length;
        C = M[0].length;
        
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(i==0 && j==0 || i==R-1 && j==C-1) continue;
                A[i][j]=getMin(i,j);
            }
        }
        int res = A[R-1][C-1];
        return Math.max(Math.min(res,A[R-2][C-1]),Math.min(res,A[R-1][C-2]));
    }
    int getMin(int i, int j){
        int x1=Integer.MIN_VALUE, x2=Integer.MIN_VALUE; //= M[i][j];
        int count=0;
        for(int a=0;a<4;a++){
            int x=i+dir[a][0];
            int y=j+dir[a][1];
            if(x>=0 && x<R && y>=0 && y<C){
                if(M[x][y]>x1){
                    x2=x1;
                    x1=M[x][y];
                }else if(M[x][y]>x2)
                    x2=M[x][y];
            }
        }
        return Math.min(x2,M[i][j]);
    }
    
//# {8, 1, 2, 4, 3, 6}, return earliest possible time (14:26:38), if not possible, return invalid    
    static void getTime(int[] A) {
    	System.out.println("");
    	System.out.println(Arrays.toString(A));
    	Arrays.parallelSort(A);
    	if(A[3]>5) {
    		int t = A[3];
    		A[3] = A[2];
    		A[2] = A[1];
    		A[1] = t;
    	}
    	if(A[4]>5)
    		A[3]=A[4] ^ A[3] ^ (A[4]=A[3]);
    	
    	if(A[0]>2 || A[0]==2 && A[1]>3 || A[2]>5 || A[4]>5) {
    		System.out.println("invalid");
    		return;
    	}else
    		System.out.println(A[0] +"" + A[1] +":"+A[2]+A[3]+":"+A[4]+A[5]);
    	return;
    }
    
 //#1101. The Earliest Moment When Everyone Become Friends
    public int earliestAcq(int[][] logs, int N) {
        int[] persons = new int[N];
        for(int i=0;i<N;i++)
            persons[i]=i;
        
        Arrays.sort(logs, (a,b)->a[0]-b[0]);
        for(int[] log : logs){
            int x=find(persons, log[1]);
            int y=find(persons, log[2]);
            if(x!=y){
                persons[x]=y;
                if(--N==1) return log[0];
            }
        }
        return -1;
    }
    
    int find(int[] p, int x){
        int i=x;
        while(p[i]!=i){
            i=p[i];
        }
        p[x]=i;
        return i;
    }
 
//#1. find Kth in 2 sorted array. should be (logn+ logm): edge case     
    public int getKthElement(int[] A, int[] B, int k) {
    	int res=0;
    	
    	return res;
    }
    
//#1073. Adding Two Negabinary Numbers
    /*Given two numbers arr1 and arr2 in base -2, return the result of adding them together.

    		Each number is given in array format:  as an array of 0s and 1s, from most significant bit to least significant bit.  For example, arr = [1,1,0,1] represents the number (-2)^3 + (-2)^2 + (-2)^0 = -3.  A number arr in array format is also guaranteed to have no leading zeros: either arr == [0] or arr[0] == 1.

    		Return the result of adding arr1 and arr2 in the same format: as an array of 0s and 1s with no leading zeros.

    		Example 1:

    		Input: arr1 = [1,1,1,1,1], arr2 = [1,0,1]
    		Output: [1,0,0,0,0]
    		Explanation: arr1 represents 11, arr2 represents 5, the output represents 16.

    		Note:

    		1 <= arr1.length <= 1000
    		1 <= arr2.length <= 1000
    		arr1 and arr2 have no leading zeros
    		arr1[i] is 0 or 1
    		arr2[i] is 0 or 1    */
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int maxsize = Math.max(arr1.length, arr2.length);
        int[] ar1 = new int[maxsize];
        for(int i=0;i<arr1.length;i++)
            ar1[i] = arr1[arr1.length-1-i];
        
        int[] ar2 = new int[maxsize];
        for(int i=0;i<arr2.length;i++)
            ar2[i] = arr2[arr2.length-1-i];
        
        int[] r = new int[maxsize+3];
        
        for(int i=0;i<maxsize;i++){
            r[i]=ar1[i]+ar2[i];
        }
        
        for(int i=0;i<maxsize+2;i++){
            if(r[i]>1){
                r[i]-=2;
                if(r[i+1]>=1)
                    r[i+1]--;
                else{
                    r[i+1]++;
                    r[i+2]++;
                }
            }
        }
        int x=maxsize+2;
        while(x>0 && r[x]==0) x--;
        int[] ans = new int[x+1];
        for(int i=0;i<=x;i++)
            ans[i]=r[x-i];
        
        return ans;
    }/*  
题意：给两个以-2为基数的数字，求两个数字之和。

思路：对于大整数加法，第一步是翻转字符串，使得低位在前面，高位在后面。
第二步是高位补零。
第三步是相加。由于-2基数的特殊性，这里我们暂时不管进位问题。
第四步是处理进位逻辑。
第五步去前导零。
第六步翻转结果，即得到答案。

这里最关键的一步是如何处理进位。
如果当前位的值是0和1，那就不需要处理。
如果当前位大于等于2，且下一位大于0，那么可以抵消，即2*(-2)^k + (-2)^(k+1) = 0。
如果当前位是2且下一位为0，则可以转化为后面两位的运算结果，即2 * (-2)^k = (-2)^(k+1) + (-2)^(k+2)。

证明：
当k是偶数时，最终结果是2^(k+1)，那公式展开-2^(k+1) + 2^(k+2) = 2^(k+1)。
当k是奇数时，最终结果是-2^(k+1)，那公式展开2^(k+1) - 2^(k+2) = -2^(k+1)。

由此，则可以完美的处理所有的进位了。    
*/    

    
//#33. Search in Rotated Sorted Array
    public int search(int[] nums, int target) {
        int l=0, r=nums.length-1;
        if(r<0)return -1;
        if(nums[l]==target) return l;
        if(nums[r]==target) return r;
        while(l<r){
            if(nums[l]==target) return l;
            if(nums[r]==target) return r;
            int m=l+(r-l)/2;
            if(nums[m]==target) return m;
            if(nums[m]>nums[r]){
                if(target>nums[l] && target<nums[m])
                    r=m-1;
                else
                    l=m+1;
            }else{
                if(target>nums[m] && target<nums[r])
                    l=m+1;
                else
                    r=m-1;
            }
        }
        return -1;
    }
    
//#153. Find Minimum in Rotated Sorted Array    
    public int findMin(int[] nums) {
        int l=0,r=nums.length-1;
        while(l<r){
            int m = l + (r-l)/2;
            if(nums[m]<nums[r])
                r=m;
            else if(nums[m]>nums[r])
                l=m+1;
        }
        return nums[l];
    }

//#297. Serialize and Deserialize Binary Tree    
    // Encodes a tree to a single string.
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sbd = new StringBuilder();
        doSerialization(root,sbd);
        //System.out.println(sbd.toString());
        return sbd.toString();
    }
    
    void doSerialization(TreeNode t, StringBuilder sbd){
        if(t==null) {
            sbd.append("n,");
            return;
        }
        sbd.append(t.val).append(',');
        doSerialization(t.left,sbd);
        doSerialization(t.right,sbd);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        p=0;
        return doDeserialization(data);
    }
    int p=0;
    TreeNode doDeserialization(String data){
        if(p==data.length()) return null;
        
        int x = data.indexOf(',',p);
        String s = data.substring(p,x);
        p=x+1;
        if("n".equals(s)) return null;
        
        int v = Integer.parseInt(s);

        TreeNode t = new TreeNode(v);
        t.left = doDeserialization(data);
        t.right = doDeserialization(data);
        return t;
    } 
    
}

//#211. Add and Search Word - Data structure design
class WordDictionary {
    private WordDictionary[] trie = null;
    private boolean isEnd = false;
    /** Initialize your data structure here. */
    public WordDictionary() {
        trie = new WordDictionary[26];
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if(word== null || word.length()==0)
            return;
        addWord(word,0);
    }
    
    void addWord(String word, int x){
        if(x==word.length()) {
            isEnd=true;
            return;
        }
        int i = word.charAt(x)-'a';
        if(trie[i]==null)
        	trie[i]=new WordDictionary();
        trie[i].addWord(word,x+1);
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if(word==null || word.length()==0) return false;
        return search(word,0);
        /*
        LinkedList<WordDictionary> list = new LinkedList<>();
        list.add(this);
        for(char c : word.toCharArray()){
            if(c=='.'){
                int n=list.size();
                for(int i=0;i<n;i++){
                    
                }
            }else{
                
            }
        }
        
        return true;
        */
    }
    
    boolean search(String word, int x){
        if(x==word.length()){
            if(isEnd) return true;
            return false;
        }
        char c = word.charAt(x);
        if(c=='.'){
            for(WordDictionary dict : trie){
                if(dict!=null && dict.search(word,x+1))
                    return true;
            }
            return false;
        }else if(trie[c-'a']==null)
            return false;
        else
            return trie[c-'a'].search(word,x+1);
    }
}
