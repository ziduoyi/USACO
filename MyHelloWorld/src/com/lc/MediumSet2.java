package com.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;


public class MediumSet2 {
	static public void main(String[] args) throws Exception{
		MediumSet2 set2 = new MediumSet2();
		
		int n=0;
		Integer m = 0;
		m = m +1;

	
//		int[] nums={4, 6, 7, 7};
//		//set2.findSubsequences(nums);
//		
//		set2.longestPalindromicSubseq2("babbbb");
		
		//set2.countArrangement(4);
		
		//set2.permuteString("marty", "");
		
		
		//set2.updateBoard(new char[][]{{'E','E','E','E','E'},{'E','E','M','E','E'},{'E','E','E','E','E'},{'E','E','E','E','E'}}, new int[]{3,0});
		
		//set2.getBinaryTreeRecursive("4(2(3)(1(11)(21(41)(61))))(6(5)(-10(-5)(8(18)(28))))");//"4(2(3)(1))(6(5))");
//		int[][] M = new int[][]{{1,0,0,0,0,0,0,0,0,1,0,0,0,0,0},{0,1,0,1,0,0,0,0,0,0,0,0,0,1,0},{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},{0,1,0,1,0,0,0,1,0,0,0,1,0,0,0},{0,0,0,0,1,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},{0,0,0,1,0,0,0,1,1,0,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0,0,0},{1,0,0,0,0,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},{0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},{0,0,0,0,1,0,0,0,0,0,0,0,1,0,0},{0,1,0,0,0,0,0,0,0,0,0,0,0,1,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1}};
//		set2.findCircleNum(M);
		
		//set2.checkInclusion("ab","eidbaooo");
		
		//set2.shoppingOffers(Arrays.asList(2,5), Arrays.asList(Arrays.asList(3,0,5),Arrays.asList(1,2,10)), Arrays.asList(3,2));
		
		//set2.predictPartyVictory("RD");
		//set2.pathSum(new int[]{113, 215, 221});
		
		//set2.canPartitionKSubsets(new int[]{4,3,2,3,5,2,1}, 4);
		
		int[][] grid={	{1,1,0,0,1},
						{1,0,1,0,1},
						{0,0,0,0,0},
						{0,0,1,0,1},
						{1,1,0,1,1}
						};
		//set2.getIslandNumber711(grid);
		//set2.numDistinctIslands2(grid);
		
		set2.maxProfit(new int[]{2,1,4,4,2,3,2,5,1,2}, 1);
		
	}

	TreeNode buildBST(int[] dataArray){
		if(dataArray==null || dataArray.length==0)
			return null;
		TreeNode root = new TreeNode(dataArray[0]);
		for(int i=1;i<dataArray.length;i++){
			TreeNode node = new TreeNode(dataArray[i]);
			root.insert(root, node);
		}
		return root;
	}
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
	public ListNode contructLinkedList(int[] nums) {
	    if (nums == null || nums.length == 0) {
	        return null;
	    }
	    ListNode pre = new ListNode(-1);
	    ListNode head = new ListNode(nums[0]);
	    pre.next = head;
	    for (int i = 1; i < nums.length; i++) {
	        head.next = new ListNode(nums[i]);
	        head = head.next;
	    }
	    return pre.next;
	}

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
        void insert(TreeNode root,TreeNode node){
        	while(root!=null){
        		if(root.val>=node.val){
        			if(root.left!=null)
        				root=root.left;
        			else{
        				root.left=node;
        				break;
        			}
        		}else{
        			if(root.right!=null)
        				root=root.right;
        			else{
        				root.right=node;
        				break;
        			}
        		}
        	}
        }
    }
//#5 Palindromic
    public String longestPalindrome(String s) {
	    if(s==null || s.length()<=1) return s;
	    int len=s.length();
	    int[][] dp = new int[len][len];

	    for(int j=0;j<len;j++){
	    	dp[j][j]=1;
	        for(int i=j-1;i>=0;i--){
	            if(j==i+1){
	                if(s.charAt(i)==s.charAt(j))
	                    dp[i][j]=2;
	            }else if(s.charAt(i)==s.charAt(j))
	                dp[i][j]=dp[i+1][j-1]>0? dp[i+1][j-1]+2 : 0;
	        }
	    }
	    int x=0,y=0;
	    int count=0;
	    for(int i=0;i<len;i++){
	        for(int j=i;j<len;j++){
	            if(dp[i][j]>count){
	                count=dp[i][j];
	                x=i;
	                y=j;
	            }
	        }
	    }       
	    return s.substring(x,y+1);
    }  
    
//#508. Most Frequent Subtree Sum
    public int[] findFrequentTreeSum(TreeNode root) {
        HashMap<Integer,Integer> hmap = new HashMap<>();
        postOrder(root,hmap);
        HashSet<Integer> set = new HashSet<>();
        int count=0;
        for(Map.Entry<Integer,Integer> entry : hmap.entrySet()){
            if(entry.getValue()>count){
                count=entry.getValue();
                set.clear();
                set.add(entry.getKey());
            }else if (entry.getValue()==count)
                set.add(entry.getKey());
        }
        int[] res = new int[set.size()];
        int i=0;
        for(int x:set){
            res[i++]=x;
        }
        return res;
    }
    
    int postOrder(TreeNode t, Map<Integer,Integer> map){
        if(t==null) return 0;
        
        int x = postOrder(t.left, map) + postOrder(t.right,map) + t.val;
        map.put(x,map.getOrDefault(x,0)+1);
        return x;
    }
//#515. Find Largest Value in Each Tree Row	--- how to differentiate levels of BFS
    public List<Integer> largestValues(TreeNode root) {
        ArrayList<Integer> resList = new ArrayList<>();
        if(root==null) return resList;
        
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        int num=Integer.MIN_VALUE;
        while(list.size()>0){
            int n=list.size();
            for(int i=0;i<n;i++){
                TreeNode t = list.removeFirst();
                num=Math.max(num,t.val);
                if(t.left!=null) list.add(t.left);
                if(t.right!=null) list.add(t.right);
            }
            resList.add(num);
            num=Integer.MIN_VALUE;
        }
        return resList;
    }
//#516. Longest Palindromic Subsequence    
    public int longestPalindromeSubseq(String s) {
        if(s==null) return 0; 
        if(s.length()<2) return s.length();

        int len = s.length();
        int[][] dp = new int[len][len];

        for(int j=0;j<len;j++){
            dp[j][j]=1;
            for(int i=j-1;i>=0;i--){
                if(s.charAt(i)==s.charAt(j)){
                    dp[i][j]=dp[i+1][j-1]+2;
                }else{
                    dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][len-1];
    }
    public int longestPalindromicSubseq2(String s){
    	int n = s.length();
    	int res = 0;
    	int[] dp = new int[n];
    	for(int i=0;i<n;i++) dp[i]=1;
    	for(int i=n-1;i>=0;--i){
    		int len=0;
    		for(int j=i+1;j<n;++j){
    			int t=dp[j];
    			if(s.charAt(i)==s.charAt(j))
    				dp[j]=len+2;
    			len=Math.max(len,t);
    		}
    	}
    	for	(int num : dp){
    		res=Math.max(res, num);
    	}
    	
    	return res;
    }
//#322. Coin Change
    public int coinChange(int[] coins, int amount) {
        if(amount==0) return 0;
        if(coins==null || coins.length==0) return -1;
        
        int len = coins.length;

        int[] dp = new int[amount+1];
        for(int i=1;i<=amount;i++)
            dp[i]=Integer.MAX_VALUE;
        dp[0]=0;
        for(int i=0;i<len;i++){
            for(int j=coins[i];j<=amount;j++){
                if(dp[j-coins[i]]<Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j],dp[j-coins[i]]+1);
            }
        }
        return dp[amount]<Integer.MAX_VALUE?dp[amount]:-1;
    }
//#518. Coin Change 2
    public int change(int amount, int[] coins) {
        if(amount==0)return 1;
        if(coins==null || coins.length==0) return 0;

        //Arrays.sort(coins);
        int len = coins.length;
        if(coins[len-1]==0 || amount<coins[0]) return 0;
        
        int[][] dp = new int[len][amount+1];

        for(int i=0;i<len;i++){
            dp[i][0]=1;
            for(int j=1;j<=amount;j++){
                if(i==0)
                    dp[i][j]=((j>=coins[i])?(dp[i][j-coins[i]]):0);
                else
                    dp[i][j]=dp[i-1][j] + ((j>=coins[i])?(dp[i][j-coins[i]]):0);
            }
        }
        return dp[len-1][amount];
    }
    //动态规划中二维降一维: dp[i][j] = dp[i-1][j]+ x   ----> dp[j] += x
    //dp[i-1][j] means the previous round of loop the value of dp[i][j]
    public int change2(int amount, int[] coins) {
        if(amount==0)return 1;
        if(coins==null || coins.length==0) return 0;

        int len = coins.length;
        if(coins[len-1]==0 || amount<coins[0]) return 0;
        
        int[] dp = new int[amount+1];
        dp[0]=1;
        for(int i=0;i<len;i++){
            for(int j=coins[i];j<=amount;j++){
                dp[j] += ((j>=coins[i])?(dp[j-coins[i]]):0);
            }
        }
        return dp[amount];
    }
    
//524. Longest Word in Dictionary through Deleting
    public String findLongestWord(String s, List<String> d) {
        if(s==null || s.length()<1) return s;
        if(d==null || d.size()==0) return "";

        Collections.sort(d,(a,b)->a.length()!=b.length()?b.length()-a.length():a.compareTo(b));
        int n=s.length();
        for(String t : d){
            if(t.length()>n) continue;
            
            for(int i=0;i<t.length();i++){
                int j=i;
                while(j<n && t.charAt(i)!=s.charAt(j))
                    j++;
                if((t.length()-i)>n-j)
                    break;
                if(i==t.length()-1 && s.charAt(j)==t.charAt(i))
                    return t;
            }
        }
        return "";
    }

    int count=0;
    public int countArrangement(int N) {
        
        boolean[] flag = new boolean[N+1];
        permute(flag,N,1);
        return count;
    }
    void permute(boolean[] flag, int N, int pos){
        if(pos>N)
            count++;
        for(int i=1;i<=N;i++){
            if(!flag[i] && (pos %i==0 || i%pos==0)){
                flag[i]=true;
                permute(flag,N,pos+1);
                flag[i]=false;
            }
        }
    }
	void swap(int i, int j){
		if(i!=j)
			System.out.print(" "+ i + " <--> " +j +" ");
	}

	void permuteString(String s, String pre){
		if(s==null || s.length()==0){
			System.out.println(pre);
			return;
		}

		for(int i=0;i<s.length();i++){
			String str = pre + s.charAt(i);
			permuteString(s.substring(0,i) + s.substring(i+1), str);
		}
	}

//#529. Minesweeper	
    public char[][] updateBoard(char[][] board, int[] click) {
        int row = board.length;
        int col = board[0].length;
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
        }else if(board[click[0]][click[1]]=='E') {
            int num = getMineNum(board,click[0],click[1]);
            if(num>0)
                board[click[0]][click[1]]=(char)(num+'0');
            else{
                board[click[0]][click[1]]='B';
                for(int i=-1;i<=1;i++){
                    int[] pos = new int[2];
                    for(int j=-1;j<=1;j++){
                        if(i==0 && j==0) continue;
                        pos[0] = click[0]+i;
                        pos[1] = click[1]+j;
                        if(0<=pos[0] && pos[0]<row && 0<=pos[1] && pos[1]<col){
                            updateBoard(board,pos);
                        }
                    }
                }
            }
        }
        return board;
    }

    int getMineNum(char[][]board, int x, int y){
        int row = board.length;
        int col = board[0].length;
        int count = 0;
        for(int i=-1;i<=1;i++){
            int m = x+i;
            for(int j=-1;j<=1;j++){
                int n = y+j;
                if(i==0 && j==0) continue;
                if(0<=m && m<row && 0<=n && n<col){
                    if(board[m][n]=='M')
                        count++;
                }
            }
        }
        return count;
    }
//#531 531	    Lonely Pixel I
    int getLonelyPixels(char[][] p){
    	int count=0;
    	int row = p.length, col=p[0].length;
    	int[] rp = new int[row];
    	int[] cp = new int[col];
    	
    	for(int i=0;i<row;i++){
    		for(int j=0;j<col;j++){
    			if(p[i][j]=='B'){
    				rp[i]++;
    				cp[j]++;
    			}
    		}
    	}
    	for(int i=0;i<row;i++){
    		if(rp[i]==1){
    			for(int j=0;j<col;j++){
	    			if(p[i][j]=='B' && cp[j]==1)
	    				count++;
	    		}
    		}
    	}

    	return count;
    }
    
//#532    Lonely Pixel II
    int getLonelyPixel2(char[][] p, int N){
    	int count=0;
    	int row = p.length, col=p[0].length;
    	int[] rp = new int[row];
    	int[] cp = new int[col];
    	
    	for(int i=0;i<row;i++){
    		for(int j=0;j<col;j++){
    			if(p[i][j]=='B'){
    				rp[i]++;
    				cp[j]++;
    			}
    		}
    	}
    	for(int i=0;i<row;i++){
    		if(rp[i]==N){
    			for(int j=0;j<col;j++){
	    			if(p[i][j]=='B' && cp[j]==N)
	    			//	if()
	    				count++;
	    		}
    		}
    	}

    	return count;
    }
//#536	Construct Binary Tree from String $
    TreeNode getBinaryTree(String str){
    	if(str==null || str.length()==0)return null;
    	Stack<String> stack  = new Stack<>();
    	int len = str.length();
    	int i=0;
    	while(i<len && !stack.isEmpty()){
    		StringBuilder sbd = new StringBuilder();
    		char c = str.charAt(i);
    		if(c=='('){
    			stack.push(""+c);
    		}else if(c==')'){
    			int value = Integer.parseInt(stack.pop());
    			TreeNode node = new TreeNode(value);
    			stack.pop(); //"("
    			
    		}
    	}
    	
		return null;
    }
    
    TreeNode getBinaryTreeRecursive(String str){
    	if(str==null || str.length()==0)return null;
   	
    	TreeNode t = recursiveHelper(str);
    	return t;
    }
    int btpos = 0;
    TreeNode recursiveHelper(String str){
    	if(btpos>=str.length()) return null;

    	while(btpos<str.length() && (str.charAt(btpos)=='(' || str.charAt(btpos)==')'))
    		btpos++;
    	int i = str.indexOf('(',btpos);
    	int j = str.indexOf(')',btpos);
    	if(i>=0 && i<j){
    		int value = Integer.parseInt(str.substring(btpos, i));
    		TreeNode node = new TreeNode(value);
    		btpos=i+1;
    		node.left = recursiveHelper(str);
    		
    		if(btpos<str.length() && str.charAt(btpos)=='('){
    			node.right = recursiveHelper(str);
    			btpos++;
    		}
    		return node;
    	}else if(j>=0 && (j<i||i<0)){
    		int value = Integer.parseInt(str.substring(btpos, j));
    		TreeNode node = new TreeNode(value);
    		btpos=j+1;
    		//node.left = recursiveHelper(str);
    		return node;
    	}

    	return null;
    }
//#537. Complex Number Multiplication
    public String complexNumberMultiply(String a, String b) {
        if(a==null || b==null) return "";
        try{
            String[] as = a.split("\\+");
            int reala = Integer.parseInt(as[0]);
            int coma = Integer.parseInt(as[1].substring(0,as[1].length()-1));
            
            String[] bs = b.split("\\+");
            int realb = Integer.parseInt(bs[0]);
            int comb = Integer.parseInt(bs[1].substring(0,bs[1].length()-1));
            
            int c = reala * realb - coma * comb;
            int comc = reala * comb + realb * coma;
            StringBuilder sbd = new StringBuilder();
            sbd.append(c).append('+').append(comc).append('i');
            return sbd.toString();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

//#539. Minimum Time Difference
    public int findMinDifference(List<String> timePoints) {
        int n=timePoints.size();
        if(n>1440) return 0;
        boolean[] times = new boolean[1440];
        
        for(int i=0;i<n;i++){
            String s = timePoints.get(i);
            int t = Integer.parseInt(s.substring(0,2)) * 60 + Integer.parseInt(s.substring(3));
            if(times[t]) return 0;
            times[t] = true;
        }
        
        int min = 1440;
        int pre=-1,first=0;
        for(int i=0;i<1440;i++){
            if(times[i]){
                if(pre<0){
                    pre=i;
                    first=i;
                } else{
                    min = Math.min(min, i-pre);
                    pre=i;
                }
            }
            min = Math.min(min, first-pre+1440);
        }
        
        return min;
    }
//#542. 01 Matrix    
    public int[][] updateMatrix(int[][] matrix) {
	    int row=matrix.length;
	    int col=matrix[0].length;
	    int[][] p = new int[row][col];
	    for(int i=0;i<row;i++){
	        for(int j=0;j<col;j++){
	            if(matrix[i][j]==0)
	                p[i][j]=0;
	            else
	                p[i][j]=200;
	        }
	    }
	    for(int i=0;i<row;i++){
	        for(int j=0;j<col;j++){
	            int m=i-1;
	            if(m>=0)
	                p[i][j]=Math.min(p[i][j],p[m][j]+1);
	            m=j-1;
	            if(m>=0)
	                p[i][j]=Math.min(p[i][j],p[i][m]+1);
	        }
	    }
	    for(int i=row-1;i>=0;i--){
	        for(int j=col-1;j>=0;j--){
	            int m=i+1;
	            if(m<row)
	                p[i][j]=Math.min(p[i][j],p[m][j]+1);
	            m=j+1;
	            if(m<col)
	                p[i][j]=Math.min(p[i][j],p[i][m]+1);
	        }
	    }
	    return p;
    }
//#545	Boundary of Binary Tree
    public List<Integer> getBinTreeBoundary(TreeNode root){
    	List<Integer> res = new ArrayList<>();
    	List<Integer> res2 = new ArrayList<>();
    	LinkedList<TreeNode> list = new LinkedList<>();
    	list.add(root);
    	res.add(root.val);
    	while(!list.isEmpty()){
    		int n = list.size();
    		for(int i=0;i<n;i++){
    			TreeNode t = list.removeFirst();
    			if(i==0 && root.left !=null && t!=root)
    				res.add(t.val);
    			else if(i==n-1 && root.right!=null && t!=root)
    				res2.add(t.val);
    			else if(t.left==null && t.right==null)
    				res.add(t.val);
    			if(t.left!=null)
    				list.add(t.left);
    			if(t.right!=null)
    				list.add(t.right);
    		}
    	}
    	for(int i=res.size()-1;i>=0;i--)
    		res.add(res2.get(i));
    	return res;
    }

    
//#547. Friend Circles
    public int findCircleNum(int[][] M) {
        int N = M.length;
        int count=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(M[i][j]==1){
                    count++;
                    markAll(M,i,j,count);
                }
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
            	System.out.print(" " + M[i][j]);
            }
            System.out.println();
        }
        return count;
    }
    void markAll(int[][] M, int x, int y,int count){
        M[x][y]=count+1;
        int N=M.length;
        for(int i=0;i<N;i++){
            if(M[x][i]==1)
                markAll(M,x,i,count);
        }
        for(int i=0;i<N;i++){
            if(M[i][y]==1)
                markAll(M,i,y,count);
        }
    }
    
    public int findCircleNumUnionFind(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) {
            return 0;
        }
        int m = M.length;//number of rows in this matrix
        UnionFind unionFind = new UnionFind(m);
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (M[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.count;
    }

    class UnionFind {
        int count;
        int[] root;

        public UnionFind(int m) {
            root = new int[m];
            for (int i = 0; i < m; i++) {
                root[i] = i;
            }
            count = m;
        }

        public void union(int i, int j) {
            int x = find(root, i);
            int y = find(root, j);
            if (x != y) {
                count--;
                root[x] = y;//path compression
            }
        }

        public int find(int[] ids, int i) {
            if (ids[i] == i) {
                return i;
            }
            return find(ids, ids[i]);
        }
    }

//#549	Binary Tree Longest Consecutive Sequence II
    public int getLongestConsecutiveSeq(TreeNode root){
    	int count=0;
    	
    	return count;
    }
    void helper549(TreeNode root){
    	
    }

//#554. Brick Wall    
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer,Integer> sumMap = new HashMap<>();
        for(List<Integer> list : wall){
            int sum=0;
            for(int i=0;i<list.size()-1;i++){
                sum += list.get(i);
                sumMap.put(sum,sumMap.getOrDefault(sum,0)+1);
            }
        }
        int max=0;
        for(int i : sumMap.values()){
            max=Math.max(max,i);
        }
        return wall.size()-max;
    }    

//#555	Split Concatenated Strings $
    String res = null;
    public String getLexaString(String[] sa){
    	
    	StringBuilder sbd = new StringBuilder();
    	
    	
    	return res;
    }
    void glsHelper(String[] sa, int pos){
    	//for
    }
    
//#567. Permutation in String
    public boolean checkInclusion(String s1, String s2) {
        if(s2.length()<s1.length())return false;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        Map<Character, Integer> map2 = new HashMap<Character, Integer>();
        for(char c: s1.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        for(int i=0;i<s1.length();i++){
            char c = s2.charAt(i);
            if(map.containsKey(c)){
                if(map.get(c)>1)
                    map.put(c,map.get(c)-1);
                else
                    map.remove(c);
            }else
                map2.put(c,map2.getOrDefault(c,0)+1);
        }
        int i=0,j=s1.length();
        while(j<s2.length() && (!map.isEmpty() || !map2.isEmpty())){
            char c1=s2.charAt(i++);
            char c2=s2.charAt(j++);
            if(c1==c2) continue;
            if(map2.containsKey(c1)){
                if(map2.get(c1)>1)
                    map2.put(c1,map2.get(c1)-1);
                else
                    map2.remove(c1);
            }else{
                map.put(c1,map.getOrDefault(c1,0)+1);
            }
            if(map.containsKey(c2)){
                if(map.get(c2)>1)
                    map.put(c2,map.get(c2)-1);
                else
                    map.remove(c2);
            }else{
                map2.put(c2,map2.getOrDefault(c2,0)+1);
            }
        }
        return map.isEmpty() && map2.isEmpty();
    }
//the solution with simple hash map    
    public boolean checkInclusionSimpleHash(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 > len2) {
            return false;
        }

        int[] count = new int[26];
        for (int i = 0; i < len1; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }

        if (allZeroes(count)) {
            return true;
        }

        for (int i = len1; i < len2; i++) {
            count[s2.charAt(i) - 'a']--;
            count[s2.charAt(i - len1) - 'a']++;
            if (allZeroes(count)) {
                return true;
            }
        }

        return false;
    }

    private boolean allZeroes(int[] count) {
        for (int i : count) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
//#573 Squirrel Simulation
    
    public int getSquirrelSteps(int height, int width, int[] tree, int[] squirrel, int[][] nuts){
    	int res = height+width;
    	int t0 = 0;
    	for(int i=0;i<nuts.length;i++){
    		int n = Math.abs(tree[0]-nuts[i][0]) + Math.abs(tree[1]-nuts[i][1]);
    		int s = Math.abs(squirrel[0]-nuts[i][0]) + Math.abs(squirrel[1]-nuts[i][1]);
    		if(s-n<res){
    			res = s-n;
    			t0 = i;
    		}
    	}
    	for(int i=0;i<nuts.length;i++){
    		if(i!=t0){
    			res += 2 * (Math.abs(tree[0]-nuts[i][0]) + Math.abs(tree[1]-nuts[i][1]));
    		}
    	}

    	return res;
    }
    
//#576 
    public int findPaths(int m, int n, int N, int i, int j) {
        int[][][] temp = new int[m][n][N+1];
        for(int[][] a2: temp){
            for(int[] a1 : a2){
                Arrays.fill(a1,-1);
            }
        }
        return fpHelper(m,n,N,i,j,temp);
    }
    
    int fpHelper(int m, int n, int N, int i, int j, int[][][] temp){
        if(i<0 || j<0 || i>=m || j>=n)
            return 1;
        if(N<=0) return 0;
        if(temp[i][j][N]>=0)
            return temp[i][j][N];
        int[][] direct = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        int res=0;
        for(int x=0;x<4;x++){
            res = (res+findPaths(m,n,N-1,i+direct[x][0],j+direct[x][1]))%1000000007;
        }
        temp[i][j][N]=res;
        return res;
    }
//#582 Kill Process
    List<Integer> killProcess(int[] pid, int[] ppid, int id){
    	List<Integer> list = new ArrayList<Integer>();
    	list.add(id);
    	kpHelper(pid, ppid, id, list);
    	return list;
    }
    void kpHelper(int[] pid, int[] ppid, int id, List<Integer> list){
    	for(int i=0;i<pid.length;i++){
    		if(ppid[i]==id){
    			list.add(pid[i]);
    			kpHelper(pid, ppid, pid[i], list);
    		}
    	}
    }
    
//583. Delete Operation for Two Strings -- longest common sub sequences    
    public int minDistance(String word1, String word2) {
	    if(word1==null && word2==null) return 0;
	    if(word1==null || word1.length()==0) return word2.length();
	    if(word2==null || word2.length()==0) return word1.length();
	    int m=word1.length();
	    int n=word2.length();
	    int[][] dp = new int[m+1][n+1];
	    
	    for(int i=1;i<=m;i++){
	        for(int j=1;j<=n;j++){
	            if(word1.charAt(i-1)==word2.charAt(j-1))
	                dp[i][j]=dp[i-1][j-1]+1;
	            else
	                dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
	        }
	    }
	    return m+n-2*dp[m][n];
    }    

//616	Add Bold Tag in String
    //Solution 1: check each string in dictionary if in the target string or not, if yes, log the start and end as intervals 
    //then merge all intervals
    
    //Solution 2: boolean[] bold = new boolean[str.length()];
    
//625	Minimum Factorization   
    int getMinFactors(int n){
    	int count=1;
    	long res=0;
    	while(n>1){
    		boolean divisible = false;
    		for(int i=9;i>1;i--){
    			if(n%i==0){
    				res += i*count;
    				n/=i;
    				divisible=true;
    				count*=10;
    				break;
    			}
    		}
    		if(!divisible)
    			return -1;
    	}

    	return res>Integer.MAX_VALUE?0:(int)res;
    }

//#638. Shopping Offers
    int result = 0;
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        for(int i=0;i<needs.size();i++){
            result += needs.get(i) * price.get(i);
        }
        //System.out.println(result);
        offerHelper(price, special, needs, result);
        return result;
    }
    
    void offerHelper(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int total){
        for(List<Integer> list : special){
            if(!isUsable(list,needs,total)) continue;
            //int t = total + list.get(list.size()-1);
            int t = getDeltaPrice(price,list);
            System.out.print(t);
            if(t<0)   continue;
            
            for(int i=0;i<needs.size();i++){
                needs.set(i,needs.get(i)-list.get(i));
            }
            result=Math.min(result,total-t);
            offerHelper(price, special, needs, total-t);
            for(int i=0;i<needs.size();i++){
                needs.set(i,needs.get(i)+list.get(i));
            }
        }
    }
    boolean isUsable(List<Integer> special, List<Integer> needs, int total){
        //if(special.get(special.size()-1)+total >= result)
        //    return false;
        for(int i=0;i<needs.size();i++){
            if(special.get(i)>needs.get(i))
                return false;
        }
        return true;
    }
    int getDeltaPrice(List<Integer> price, List<Integer> special){
        int t = 0;
        for(int i=0;i<special.size()-1;i++){
            t += price.get(i) * special.get(i);
        }
        return t-special.get(special.size()-1);
    }
    
 //#646. Maximum Length of Pair Chain
    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        Arrays.sort(pairs,(a,b)->a[1]-b[1]);

        int res=1;
        int cur=0;
        for(int i=1;i<n;i++){
            if(pairs[cur][1] < pairs[i][0]){
                res++;
                cur = i;
            }
        }
        return res;
    }
//#648. Replace Words  -- trie tree usage    
    public String replaceWords(List<String> dict, String sentence) {

        TrieNode trie = new TrieNode();
        for(String s:dict){
            trie.insert(s);
        }
        
        String[] words = sentence.split(" ");
        
        for(int i=0;i<words.length;i++){
            int p = trie.searchRoot(words[i]);
            if(p>=0 && p<words[i].length()-1)
                words[i]=words[i].substring(0,p+1);
        }
        StringBuilder sbd = new StringBuilder();

        for(int i=0;i<words.length-1;i++)
            sbd.append(words[i]).append(" ");
        sbd.append(words[words.length-1]);
        return sbd.toString();
    }
    class TrieNode{
        boolean end=false;
        TrieNode[] children;
        TrieNode(){
            children = new TrieNode[26];
        }
        void insert(String word){
            TrieNode node = this;
            for(int i=0;i<word.length();i++){
                int ind = word.charAt(i) -'a';
                if(node.children[ind]==null)
                    node.children[ind]=new TrieNode();
                node=node.children[ind];
            }
            node.end=true;
        }
        int searchRoot(String word){
            TrieNode node = this;
            for(int i=0;i<word.length();i++){
                int ind = word.charAt(i) -'a';
                node = node.children[ind];
                if(node==null) return -1;
                if(node.end) return i;
            }
            return -1;
        }
    }
    
 //#649. Dota2 Senate
    public String predictPartyVictory(String senate) {
        int n=senate.length()+1;
        int[] r = new int[n];
        int[] d = new int[n];
        int r1=0,r2=0;
        int d1=0,d2=0;
        for(int i=0;i<n-1;i++){
            if(senate.charAt(i)=='R')
                r[++r2]=i;
            else
                d[++d2]=i;
        }
        while(r1!=r2 && d1!=d2){
            r1 = ++r1%n;
            d1 = ++d1%n;
            if(r[r1]<d[d1]){
                r2 = ++r2 % n;
                r[r2]=r[r1]+n;
            }else{
                d2 = ++d2 % n;
                d[d2]=d[d1]+n;
            }
        }
        if(r1==r2)
            return "Dire";
        else
            return "Radiant";
    }

//#650 650. 2 Keys Keyboard
    public int minSteps2Keys(int n) {
        if(n<=1) return 0;
        int res=n;
        for(int i=2;i<=n/2;i++){
            if(n%i==0){
                res=Math.min(res,minSteps2Keys(i)+ n/i);
            }
        }
        return res;
    }
//#651	4 Keys Keyboard    
    public int maxPrints4Keys(int n) {
        if(n<=6) return n;
        int res=n;
        for(int i=6;i<n-2;i++){
        	res=Math.max(res,maxPrints4Keys(i)*(n-i-2));
        }
        return res;
    }
    
//#652. Find Duplicate Subtrees   ***** five star *****
    //serialization should reply on pre or post. in-order could create ambiguity
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        postOrder652(root, new HashMap<String,Integer>(), res);
        return res;
    }
    String postOrder652(TreeNode t, HashMap<String,Integer> map, List<TreeNode>res){
        if(t==null)
            return "#";

        StringBuilder sbd = new StringBuilder();
        String s = sbd.append(t.val).append(" ").append(postOrder652(t.left,map,res)).append(postOrder652(t.right,map,res)).toString();
        if(map.getOrDefault(s,0)==1){
            res.add(t);
        }
        map.put(s,map.getOrDefault(s,0)+1);

        return s;
    }
    
//#663 Equal Tree Partition $
    int num0 = 0;
    public boolean equalTreePartition(TreeNode root){
    	HashSet<Integer> hset = new HashSet<Integer>();
    	int sum = postOrder663(root, hset);
    	if(sum==0) return num0>1;
    	return sum %2==0 && hset.contains(sum/2);
    }
    int postOrder663(TreeNode t, HashSet<Integer> hset){
    	if(t==null) return 0;
    	int sum = t.val + postOrder663(t.left, hset) + postOrder663(t.right,hset);
    	if(sum==0)num0++;
    	if(!hset.contains(sum)) 
    		hset.add(sum);
    	return sum;
    }

//#666 Path Sum IV  
    int pathSum(int[] nums){
    	int sum=0;
    	int[] tree = new int[50];
    	Arrays.fill(tree, -1);
    	for(int i : nums){
    		tree[i/10]=i%10;
    	}
    	for(int i=11;i<49;i++){
    		if(tree[i]<0) continue;
    		if(i>40){
    			sum+= getPathSum(tree,i);
    		}else{
    			int d = i/10 +1;
    			int dl = d*10 + i%10*2-1;
    			int dr = d*10 + (i%10)*2;
    			if(tree[dl]<0 && tree[dr]<0)
    				sum+=getPathSum(tree,i);
    		}
    	}
    	return sum;
    }

    int getPathSum(int[] tree, int i){
    	int d = i/10;
    	int sum=0;
    	while(d>0){
    		sum+=tree[i];
    		d--;
    		i=d*10+(i%10+1)/2;
    	}
    	return sum;
    }

//#692. Top K Frequent Words    ***** 5 star *****
    public List<String> topKFrequent(String[] words, int k) {
        List<String> list = new ArrayList();
        HashMap<String, Integer> hmap = new HashMap<>();
        for(String word : words){
            hmap.put(word, hmap.getOrDefault(word,0) + 1);
        }
        TreeSet<Map.Entry<String,Integer>> sortedSet = new TreeSet<>((a,b)->{
            if(a.getValue()==b.getValue())
                return a.getKey().compareTo(b.getKey());
            else
                return b.getValue()-a.getValue();
        });
        
        sortedSet.addAll(hmap.entrySet());
        
        for(Map.Entry<String,Integer> entry: sortedSet){
            list.add(entry.getKey());
            if(--k==0)
                return list;
        }
        return list;
    }  

//#694	Number of Distinct Islands $    
    int getIslandNumber(int[][] grid){
    	Set<String> set = new HashSet<>();

    	StringBuilder sbd = new StringBuilder();
    	for(int i=0;i<grid.length;i++){
    		for(int j=0;j<grid[0].length;j++){
    			if(grid[i][j]==1){
    				floodfill_694(grid,i,j,0,0,sbd);
    				set.add(sbd.toString());
    				sbd.setLength(0);
    			}
    		}
    	}
    	return set.size();
    }
    
    void floodfill_694(int[][] grid,int x, int y, int xr, int yr,StringBuilder sbd){
    	int m=grid.length;
    	int n=grid[0].length;

    	sbd.append(" ").append(xr).append("_").append(yr);
    	grid[x][y]=2;
    	int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    	for(int d=0;d<4;d++){
    		int i=x+directions[d][0];
    		int j=y+directions[d][1];
    		if(i>=0 && i<m && j>=0 && j<n && grid[i][j]==1)
    			floodfill_694(grid, i,j,directions[d][0],directions[d][1],sbd);
    	}
    }
    
//#711	Number of Distinct Islands $
    class Point {
        public int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    } 
    int getIslandNumber711(int[][] grid){
    	Set<String> set = new HashSet<>();
    	for(int i=0;i<grid.length;i++){
    		for(int j=0;j<grid[0].length;j++){
    			if(grid[i][j]==1){
    				List<List<Point>> list = new ArrayList<>();
    				set.add(floodfill_711(grid,i,j,list));
    			}
    		}
    	}
    	return set.size();
    }
    String floodfill_711(int[][] grid,int x, int y, List<List<Point>> list){
    	int m=grid.length;
    	int n=grid[0].length;
    	
    	int[][] conv=new int[][]{{1,1},{1,-1},{-1,1},{-1,-1}};
    	boolean first = false;
    	if(list.size()==0){
    		first=true;
    		for(int i=0;i<8;i++)
    			list.add(new ArrayList<Point>());
    	}
    	for(int i=0;i<4;i++){
    		list.get(i).add(new Point(conv[i][0]*x,conv[i][1]*y));
    		list.get(4+i).add( new Point(conv[i][0]*y,conv[i][1]*x));
    	}
    	grid[x][y]=2;
    	int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    	for(int d=0;d<4;d++){
    		int i=x+directions[d][0];
    		int j=y+directions[d][1];
    		if(i>=0 && i<m && j>=0 && j<n && grid[i][j]==1)
    			floodfill_711(grid, i,j,list);
    	}
    	if(first){
    		String res="Z";
    		for(List<Point> l : list){
    			String s = getString(l);
    			if(s.compareTo(res)<0)
    				res=s;
    		}
    		return res;
    	}
    	return null;
    }
    
    String getString(List<Point> list){
    	Collections.sort(list, (a,b)->a.x!=b.x?a.x-b.x:a.y-b.y);
    	StringBuilder sbd=new StringBuilder();
    	Point p = list.get(0);
    	int x=p.x;
    	int y=p.y;
    	for(Point a : list){
    		a.x -= x;
    		a.y -= y;
    		sbd.append('(').append(a.x).append(',').append(a.y).append(')');
    	}
    	return sbd.toString();
    }
    
//#698. Partition to K Equal Sum Subsets
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int[] sums = new int[k];

        int total = 0;
        for(int i=0;i<nums.length;i++)
            total += nums[i];

        if(total % k !=0) return false;
        
        return helper698(nums,sums,total/k,0,k);
    }
    
    boolean helper698(int[] nums, int[] sums, int total, int pos,int k){
        if(pos>=nums.length){
            for(int i:sums){
                if(i!=total)
                    return false;
            }
            return true;
        }
        for(int i=0;i<k;i++){
            if(sums[i]+nums[pos]<=total){
                sums[i]+=nums[pos];
                if(helper698(nums,sums,total,pos+1, k))	//if no match for one case, does not return false
                    return true;	// only return if found
                sums[i]-=nums[pos];
            }
        }
        return false;
    }

//#714. Best Time to Buy and Sell Stock with Transaction Fee
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] d = new int[n][n];
        int res=0;
        for(int j=0;j<n;j++){
            d[j][j]=0;
            for(int i=j-1;i>=0;i--){
                int profit = prices[j]-prices[i]-fee;
                if(profit<0) profit = 0;
                for(int k=i+1;k<j;k++){
                    profit = Math.max(profit, d[i][k] + d[k+1][j]);
                }
                d[i][j]=profit;
                res = Math.max(res,profit);
            }
        }
        return res;
    }
    public int maxProfit_greedy(int[] prices, int fee) {
        int n = prices.length;
        int profit = 0;
        int low=500000, high=0;
        for(int i=0;i<n;i++){
            high=Math.max(high,prices[i]);
            if( high-low>fee && (high > prices[i] + fee || i==n-1)){
                profit += high-low-fee;
                low=500000;
                high=0;
            }
            if(low>prices[i]){
                low = prices[i];
                high = prices[i];
            }
        }
        return profit;
    }

    
    
}
