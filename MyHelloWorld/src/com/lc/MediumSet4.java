package com.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;



public class MediumSet4 {
	public  static void main(String[] args){
		MediumSet4 set4 = new MediumSet4();
		
		//set4.isNStraightHand(new int[]{1,2,3,6,2,3,4,7,11}, 3);
		
		//set4.loudAndRich(new int[][]{{1,0},{2,1},{3,1},{3,7},{4,3},{5,3},{6,3}},new int[]{3,2,5,4,6,1,7,0});
		
//		ExamRoom er = set4.new ExamRoom(4);
//		er.seat();
//		er.seat();
//		er.seat();
//		er.seat();
//		er.leave(1);
//		er.leave(3);
//		er.seat();
		
		//set4.reorderedPowerOf2(10);
		
//		set4.advantageCount(new int[]{28,47,45,8,2,10,25,35,43,37,33,30,33,20,33,42,43,36,34,3,16,23,15,10,19,42,13,47,0,21,36,38,0,5,3,28,4,20,14,5,
//				19,22,29,17,3,16,35,0,26,0}, 
//							new int[]{44,10,27,4,27,40,46,40,45,0,41,2,44,50,36,30,37,4,44,4,12,13,35,20,19,25,38,42,43,14,2,4,5,38,4,38,0,35,12,32,
//				38,33,3,1,19,46,23,13,24,41});

		//set4.minEatingSpeed(new int[]{3,6,7,11}, 8);
		
		//set4.stoneGame(new int[]{3,7,3,2,5,1,6,3,10,7});
		
		//set4.spiralMatrixIII(1, 4, 0, 0);
		
		//set4.constructFromPrePost(new int[]{2,1,3}, new int[]{3,1,2});
		
//		set4.snakesAndLadders(new int[][]{{-1,-1,-1,46,47,-1,-1,-1},{51,-1,-1,63,-1,31,21,-1},{-1,-1,26,-1,-1,38,-1,-1},{-1,-1,11,-1,14,23,56,57},
//				{11,-1,-1,-1,49,36,-1,48},{-1,-1,-1,33,56,-1,57,21},{-1,-1,-1,-1,-1,-1,2,-1},{-1,-1,-1,8,3,-1,6,56}});
		
		//set4.shortestBridge(new int[][]{{0,0,1,0,1},{0,1,1,0,1},{0,1,0,0,1},{0,0,0,0,0},{0,0,0,0,0}});
		
		set4.removeStones(new int[][]{{3,2},{0,0},{3,3},{2,1},{2,3},{2,2},{0,2}});
	}
	
//#845. Longest Mountain in Array	
    public List<Integer> splitIntoFibonacci(String S) {
        int n = S.length();
        int m = n>>1;
        int l = n*2/3;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=1;i<=m;i++){
            int a = getInt(S,0,i);
            if(a<0)continue;
            //list.clear();
            //list.add(a);
            for(int j=1;j<=m;j++){
                if(i+j>l) continue;
                int b = getInt(S,i,j);
                if(b<0) continue;
                //list.add(b);
                int x = i+j;
                int a0=a;
                while(true){
                    int c= a0 + b;
                    int len = getLength(c);
                    int cs = getInt(S,x,len);
                    if(cs!=c) break;
                    x+=len;
                    //list.add(c);
                    if(x==n) return getList(S,i,j);
                    a0=b;
                    b=c;
                }
            }
        }
        return list;
    }
    
    List<Integer> getList(String S, int i,int j){
        int n = S.length();
        ArrayList<Integer> list = new ArrayList<>();
        int a = getInt(S,0,i);
        int b = getInt(S,i,j);
        list.add(a);
        list.add(b);
        int x = i+j;
                while(true){
                    int c= a + b;
                    int len = getLength(c);
                    int cs = getInt(S,x,len);
                    x+=len;
                    list.add(c);
                    if(x==n) return list;
                    a=b;
                    b=c;
                }
        //return list;
    }
    
    int getInt(String s, int start, int len){
            if(start+len>s.length() || len>1 && s.charAt(start)=='0') return -1;
            int result = 0;
            for(int i=start; i<start+len;i++){
                result = result * 10 + s.charAt(i)-'0';
            }

        return result;
    }
    int getLength(int c){
        if(c==0)return 1;
        int r = 0;
        while(c>0){
            c/=10;
            r++;
        }
        return r;
    }
    
    
    public int longestMountain(int[] A) {
        int len = A.length;
        int i = 0, res = 0;
        while (i+2 < len) {
            while (i < len - 1 && A[i] >= A[i + 1]) i++; 
            int start = i;
            while (i < len - 1 && A[i] < A[i + 1]) i++; 
            int peek = i;
            while (i < len - 1 && A[i] > A[i + 1]) i++; 
            int end = i;
            if (start != peek && peek != end) res = Math.max(res, end - start + 1); 
        }
        return res;
    }
    
    
    
//846. Hand of Straights   
    public boolean isNStraightHand(int[] hand, int W) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for(int i:hand){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        while(!map.isEmpty()){
            int i=map.firstKey();
            for(int j=0;j<W;j++){
                Integer v = map.get(i+j);
                if(v==null) return false;
                if(v>1)
                    map.put(i+j,--v);
                else
                    map.remove(i+j);
            }
        }
        return true;
    }

    public boolean isNStraightHand2(int[] hand, int W) {
        int n=hand.length;
        Arrays.sort(hand);
        
        int i=-1;
        while(i<n){
            while(++i<n && hand[i]<0);
            if(i>=n)return true;
            int j=i+1;
            int count=W-1;
            int x=hand[i];
            hand[i]=-1;
            while(count>0 && j<n){
                if(hand[j]==x+1){
                    x++;
                    count--;
                    hand[j++]=-1;
                }else if(hand[j]==x || hand[j]==-1){
                    j++;
                }else
                    return false;
            }
            if(count>0) return false;
        }
        return true;

    }
    
 //848. Shifting Letters
    public String shiftingLetters(String S, int[] shifts) {
        int n=S.length();
        int[] nums = new int[n+1];
        char[] res = S.toCharArray();
        for(int i=n-1;i>=0;i--){
            nums[i] = (nums[i+1]+shifts[i])%26;
            res[i]= (char)('a' + (res[i] - 'a' + nums[i])%26);
        }
        return new String(res);
    }  
//#851. Loud and Rich    
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        int[] res = new int[n];
        Arrays.fill(res,-1);

        ArrayList<HashSet<Integer>> list = new ArrayList<>(n);
        for(int i=0;i<n;i++)
            list.add(new HashSet<Integer>());

        for(int i=0;i<richer.length;i++){
            list.get(richer[i][1]).add(richer[i][0]);
        }

        for(int i=0;i<n;i++){
            if(res[i]<0)
                res[i] = dfs(list, quiet, i, res);
        }
        return res;
    }
    
    int dfs(ArrayList<HashSet<Integer>> list, int[] quiet, int i, int[] res){
        if(res[i]<0){
	        res[i]=i;
	        for(int x : list.get(i)){
	        	int v = dfs(list,quiet,x,res);
	            if(quiet[v]<quiet[res[i]])
	                res[i]=x;
	        }
        }
        return res[i];
    }
    
//853. Car Fleet    
    public int carFleet(int target, int[] position, int[] speed) {
        int n = speed.length;
        int[][] ps = new int[n][2];
        for(int i=0;i<n;i++){
            ps[i][0]=target-position[i];
            ps[i][1]=speed[i];
        }
        Arrays.sort(ps,(a,b)->a[0]-b[0]);

        int res=0;
        double arrive = -1;
        
        for(int i=0;i<n;i++){
            double d = (ps[i][0]+0.0) / ps[i][1];
            if(d>arrive){
                res++;
                arrive = d;
            }
        }
        return res;
    }
    //it is fast to use tree map here
    public int carFleetMap(int target, int[] position, int[] speed) {
        int n = speed.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i=0;i<n;i++){
            map.put(target-position[i],speed[i]);
        }
        int res=0;
        double arrive = -1;
        
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            double d = (entry.getKey()*1.0) / entry.getValue();
            if(d>arrive){
                res++;
                arrive = d;
            }
        }
        return res;
    }
    
//855. Exam Room    
    class ExamRoom {
        int maxSeats=0;
        TreeSet<Integer> students=null;
        public ExamRoom(int N) {
            this.maxSeats=N;
            students = new TreeSet<>();
        }
        
        public int seat() {
            if(students.isEmpty()){
                students.add(0);
                return 0;
            }
            if(students.size()==maxSeats)
                return -1;
            int distance = students.first();
            int prev=distance;
            int pos=0;
            for(int i: students){
                int temp=(i-prev)/2;
                if(temp>distance){
                    distance=temp;
                    pos=prev+distance;
                }
                prev=i;
            }
            if(maxSeats-1-students.last()>distance){
                pos=maxSeats-1;
            }
            students.add(pos);
            return pos;
        }
        
        public void leave(int p) {
            students.remove(p);
        }
    } 
//#856. Score of Parentheses
    public int scoreOfParentheses(String S) {
        return helper(S,0,S.length()-1);
        /*
        Stack<String> stack = new Stack<>();
        for(char c : S.toCharArray()){
            if(c=='(')
                stack.push(String.valueOf(c));
            else if( c==')'){
                String s = stack.pop();
                if(s.equals("(")){
                    stack.push("1");
                }else{
                    int x = Integer.parseInt(s);
                    String t = stack.pop();
                    while(!t.equals("(")){
                        x += Integer.parseInt(t);
                        t=stack.pop();
                    }
                    x = x<<1;
                    stack.push(String.valueOf(x));
                }
            }
        }
        int score=0;
        while(!stack.isEmpty()){
            score += Integer.parseInt(stack.pop());
        }
        return score;*/
    }
    int helper(String s, int i, int j){
        if(i+1==j) return 1;
        int count=0;
        for(int k=i;k<=j;k++){
            if(s.charAt(k)=='(')
                count++;
            else
                count--;
            if(count==0){
                if(k==j){
                    return 2*helper(s,i+1,j-1);
                }else{
                    return helper(s,i,k) + helper(s,k+1,j);
                }
            }
        }
        return 0;
    }    
//#863. All Nodes Distance K in Binary Tree    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        TreeNode[] parents = new TreeNode[501];
        boolean[] visited = new boolean[501];
        //arrays.fill(parents, -1);
        
        setParents(root, parents);
        
        ArrayList<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(target);
        while(K>=0){
            int n = list.size();
            for(int i=0;i<n;i++){
                TreeNode node = list.removeFirst();
                visited[node.val]=true;
                if(K==0){
                    res.add(node.val);
                }else{
                    if(parents[node.val]!=null && !visited[parents[node.val].val] ){
                        list.add(parents[node.val]);
                    }
                    if(node.left!=null && !visited[node.left.val]){
                        list.add(node.left);
                    }
                    if(node.right!=null && !visited[node.right.val]){
                        list.add(node.right);
                    }
                }
            }
            K--;
        }
        return res;
    }
    void setParents(TreeNode root, TreeNode[] parents){
        if(root==null) return;
        
        if(root.left!=null){
            parents[root.left.val]=root;
            setParents(root.left,parents);
        }
        if(root.right!=null){
            parents[root.right.val]=root;
            setParents(root.right,parents);
        }
    }
    
//option 2: Percolate Distance    
    List<Integer> ans;
    TreeNode target;
    int K;
    public List<Integer> distanceKpd(TreeNode root, TreeNode target, int K) {
        ans = new LinkedList();
        this.target = target;
        this.K = K;
        dfs(root);
        return ans;
    }

    // Return distance from node to target if exists, else -1
    public int dfs(TreeNode node) {
        if (node == null)
            return -1;
        else if (node == target) {
            subtree_add(node, 0);
            return 1;
        } else {
            int L = dfs(node.left), R = dfs(node.right);
            if (L != -1) {
                if (L == K) ans.add(node.val);
                subtree_add(node.right, L + 1);
                return L + 1;
            } else if (R != -1) {
                if (R == K) ans.add(node.val);
                subtree_add(node.left, R + 1);
                return R + 1;
            } else {
                return -1;
            }
        }
    }

    // Add all nodes 'K - dist' from the node to answer.
    public void subtree_add(TreeNode node, int dist) {
        if (node == null) return;
        if (dist == K)
            ans.add(node.val);
        else {
            subtree_add(node.left, dist + 1);
            subtree_add(node.right, dist + 1);
        }
    } 
    
//865. Smallest Subtree with all the Deepest Nodes
    TreeNode resnode=null;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        int[] depth = new int[2];   //[0]: max depth; [1]: # of node with the max depth
        postOrder(root, 1, depth);
        return resnode;
    }
    
    int postOrder(TreeNode t, int level, int[] depth){
        if(t.left==null && t.right==null){
            if(depth[0]<level){
                depth[0]=level;
                depth[1]=1;
                resnode = t;
                return 1;
            }else if(depth[0]==level){
                depth[1]++;
                return 1;
            }
            return 0;
        }
        int l=0,r=0;
        int ld=0,rd=0;
        if(t.left!=null){
            l=postOrder(t.left,level+1,depth);
            ld=depth[0];
        }
        if(t.right!=null){
            r=postOrder(t.right,level+1,depth);
            rd=depth[0];
        }
        if(ld==rd && ld==depth[0] && l>0 && r>0)
        	resnode = t;
        return l+r;
    }
    
// --- option 2
    int deepest=0; TreeNode resNode=null;
    public TreeNode subtreeWithAllDeepest2(TreeNode root) {
   if(root==null) return null;
        Helper(root,0);
        return resNode;
    }
    private int Helper(TreeNode root,int level){
        if(root==null) return level;
        int leftlevel=Helper(root.left,level+1);
        int rightlevel=Helper(root.right,level+1);
        int curlevel=Math.max(leftlevel,rightlevel);
        deepest=Math.max(deepest,curlevel);
        if(leftlevel==deepest&&rightlevel==deepest){
        	resNode=root;
        }
        return curlevel;
    }

//#869. Reordered Power of 2    
    public boolean reorderedPowerOf2(int N) {
        long[] powers = new long[30];
        int[] arr = new int[10];
        
        int x=1;
        for(int i=0;i<30;i++){
            int y=x;
            x*=2;
            while(y>0){
                arr[y%10]++;
                y/=10;
            }
            powers[i]=encodeInt(arr);
            Arrays.fill(arr,0);
        }

        while(N>0){
            arr[N%10]++;
            N/=10;
        }
        long n=encodeInt(arr);
        for(int i=0;i<30;i++){
            if(powers[i]==n)
                return true;
        }
        return false;
    }
    long encodeInt(int[] p){
        long res=0;
        for(int i:p){
            res = res*10 + i;
        }
        return res;
    }    

//#870. Advantage Shuffle    						*****five start*****
    public int[] advantageCount(int[] A, int[] B) {
        int len = A.length;
        Integer[] BI = new Integer[len];
        for(int i=0;i<len;i++){
            BI[i]=i;
        }

        Arrays.sort(BI,(a,b)->B[a]-B[b]);	//#1: sort on index, need <Integer> data type for 1 dimension array
        Arrays.sort(A);

        int[] res = new int[len];
        int r = len-1;
        for(int i=len-1;i>=0;i--){
            int key = A[i];
            int x = binarySearch(B,BI, 0, r, key);
            if(x>=0){
            	res[BI[x]]=key;
            	r=x-1;
            }else{
                r=i;
                break;
            }
        }
        for(int i=0;i<len;i++){
            if(res[i]==0)
                res[i]=A[r--];
                
        }
        return res;
    }
    int binarySearch(int[] B, Integer[] ind, int l, int r, int val){
        if(r>=0 && B[ind[r]]<val)return r;
        if(B[ind[l]]>=val)return -1;
        while(l<=r){
            int m = (l+r)/2;
            if(B[ind[m]]<val && (r<=m || val<=B[ind[m+1]]))
                return m;
            if(B[ind[m]]<val)
                l=m+1;
            else
                r=m-1;
        }
        return -1;
    }
    
    public int[] advantageCount2(int[] A, int[] B) {
        int len = A.length;
        int[] res = new int[len];
        for(int i=0;i<len;i++){
        	int t=-1;
            for(int j=0;j<len;j++){
            	if(B[j]<A[i]){
            		if(t<0)
            			t=j;
            		else if(B[t]<B[j])
            			t=j;
            	}
            }
            if(t>=0){
            	res[t]=A[i];
            	A[i]=0;
            	B[t]=1000000000;
            }
        }
        int x=-1,y=-1;
        while(x<len){
        	while(++x<len && A[x]==0);
        	while(++y<len && res[y]>0);
        	if(x<len && y<len){
        		res[y]=A[x];
        	}
        }
        return res;
    }

//#873. Length of Longest Fibonacci Subsequence
    public int lenLongestFibSubseq(int[] A) {
        int len = A.length;
        int[][] p = new int[len][len];
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for(int i=0;i<len;i++)
            map.put(A[i],i);
        int res=0;
        for(int i=0;i<len-1;i++){
            for(int j=i+1;j<len;j++){
                p[i][j]=2;
                int v= A[j]-A[i];
                Integer k = map.get(v);
                if(k!=null && k<i){
                    p[i][j]=p[k][i]+1;
                    res=Math.max(res,p[i][j]);
                }
            }
        }
        return res;
    }
    
//#875. Koko Eating Bananas    
    class Pile{
        public int bananas;
        public Double val;
        public int num=1;
        public int hours=1;
        public Pile(int bn){
        	bananas = bn;
            val=(double) bn;
        }
    }
    public int minEatingSpeed2(int[] piles, int H) {
        int len = piles.length;
        int n = H-len;
        
        TreeSet<Pile> set = new TreeSet<Pile>((a,b)->a.val.compareTo(b.val));			//comparator for TreeSet or TreeMap

        return 0;
    }    
    
    
    public int minEatingSpeed(int[] piles, int H) {
        int len = piles.length;
        Arrays.sort(piles);
        int r = piles[len-1], l=1;
        int n = H - len;
        int m=0;
        while(l<=r){
            m = l+(r-l)/2;
            if(isEatup(piles,m,n))
                r=m-1;
            else
                l=m+1;
        }
        while(!isEatup(piles,m,n))
            m++;

        return m;
    }
    
    boolean isEatup(int[] piles, int m, int x){
        for(int i=piles.length-1;i>=0;i--){
            if(piles[i]>m){
                int hours = (int)Math.ceil((piles[i] - m + 0.0) / m);
                if(x<hours)return false;
                x -= hours;
            }
        }
        return true;
    }

//#877. Stone Game    -- recursive -- remember result
    HashMap<String, Integer> map = new HashMap<>();
    public boolean stoneGameI(int[] piles) {
        return play(piles, 0,piles.length-1)>0;
    }

    int play(int[] p, int l, int r){
        String key = (new StringBuilder()).append(l).append(" ").append(r).toString();
        if(map.get(key)!=null)
            return map.get(key);
        if(l==r)
            return p[l];

        int score = Math.max(p[l]-play(p,l+1,r),p[r]-play(p,l,r-1));
        map.put(key,score);
        return score;
    }

    HashMap<String, Boolean> mymap = new HashMap<>();
    public boolean stoneGame(int[] piles) {
        int target = 0;
        for(int i=0;i<piles.length;i++){
            target += piles[i];
        }
        target = target/2+1;
        int[] sum = new int[2];
        sum[0]=sum[1]=target;
        return play(piles, 0,piles.length-1,sum,0);
    }

    boolean play(int[] p, int l, int r, int[] sum, int index){
        String key = (new StringBuilder()).append(l).append(" ").append(r).toString();
        if(mymap.containsKey(key))return mymap.get(key);
        if(sum[index]<=p[l] || sum[index]<=p[r]){
        	mymap.put(key,true);
            return true;
        }
        int temp = sum[index];
        sum[index]-=p[l];
        boolean res = play(p,l+1,r,sum,index^1);
        if(res){
            sum[index]=temp-p[r];
            res = play(p,l,r-1,sum,index^1);
        }
        sum[index]=temp;
        mymap.put(key,res);
        return res;
    }
    //https://blog.csdn.net/qq874455953/article/details/82696196
    boolean stoneGameDP(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n+1][n+1];
        for(int i = 0; i < n; i++) {
            dp[i][i] = piles[i]; //初始化只有i一个石头堆的情形
        }
        for(int dis = 1; dis < n; dis++) {//依次计算相邻2个石头堆到n个石头堆的情形
            for(int i = 0; i < n - dis; i++) {
                dp[i][i+dis] = Math.max(piles[i]-dp[i+1][i+dis], piles[i+dis]-dp[i][i+dis-1]);
            }
        }
        return dp[0][n-1] > 0;
    }
    
//#880. Decoded String at Index    					***** 5 star *****
    public String decodeAtIndex(String S, int K) {
        long len=0;
        for(char c : S.toCharArray()){
            if('1'<c && c<='9'){
                int num = c-'0';
                long n=len;
                len *= num;
                if(len>=K){
                    K %= n;
                    if(K==0)K=(int)n;
                    return decodeAtIndex(S,K);
                }
                continue;
            }else
                len++;
            if(len==K){
                System.out.print(len);
                return ""+c;
            }
        }
        return "";
    }
    
    public String decodeAtIndexNR(String S, int K) {
         long size = 0;
         int N = S.length();
         int i = 0;
         for (; i < N; ++i) {
             char c = S.charAt(i);
             if (Character.isDigit(c))
                 size *= c - '0';
             else
                 size++;
             if(size>=K)
            	 break;
         }

         for (int j = i; j >= 0; --j) {
             char c = S.charAt(j);
             K %= size;
             if (K == 0 && Character.isLetter(c))
                 return Character.toString(c);

             if (Character.isDigit(c))
                 size /= c - '0';
             else
                 size--;
         }

         throw null; 
     }
    
//#881. Boats to Save People
    public int numRescueBoats(int[] people, int limit) {
        int res=0;
        Arrays.sort(people);
        int i=0,j=people.length-1;
        while(i<=j){
            if(limit>=people[i]+people[j--]){
                i++;
            }
            res++;
        }
        return res;
    }    

//885. Spiral Matrix III
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        int[][] res = new int[R*C][2];
        int x=r0,y=c0;
        int k=0;
        int d=-1;
        int moves = 0;
        res[k][0]=r0;
        res[k++][1]=c0;
        if(R==1 && C==1)return res;
        while(true){
            ++moves;
            for(int j=0;j<2;j++){
                if(++d==4)d=0;
                for(int m=0;m<moves;m++){
                    x+=dir[d][0];
                    y+=dir[d][1];
                    if(0<=x && x<R && 0<=y && y<C){
                        res[k][0]=x;
                        res[k++][1]=y;
                        if(k>=R*C) return res;
                    }
                }
            }
        }
    }

//#886. Possible Bipartition
    public boolean possibleBipartition(int N, int[][] dislikes) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(N+1);
        for(int i=0;i<=N;i++)
            graph.add(new ArrayList<Integer>());

        for(int i=0;i<dislikes.length;i++){
            int a=dislikes[i][0];
            int b=dislikes[i][1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        int[] colors = new int[N+1];
        for(int i=1;i<N+1;i++){
            if(colors[i]==0)
                if(!dfs(graph,i,1,colors))
                    return false;
        }
        
        return true;
    }
    
    boolean dfs(ArrayList<ArrayList<Integer>> graph, int x, int v, int[] colors){
    	colors[x]=v;
    	for(int i : graph.get(x)){
   			if(colors[i]==v)
    			return false;
    		else if(colors[i]==0)
    			if(!dfs(graph,i,-v,colors)) return false;
    	}
        return true;
    }
    
//#889. Construct Binary Tree from Preorder and Postorder Traversal    
    int[] pos1;
    int[] pos2;
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        int len = pre.length;
        pos1 = new int[len+1];
        pos2 = new int[len+1];
        
        for(int i=0;i<len;i++){
            pos1[pre[i]]=i;
            pos2[post[i]]=i;
        }
        return helper(pre,0,len-1,post,0,len-1);
    }
    TreeNode helper(int[] pre, int x0, int y0, int[] post, int x1, int y1){
        TreeNode root = new TreeNode(pre[x0]);
        
        x0++;y1--;
        if(x0<=y0){
            int new_y1=pos2[pre[x0]];
            int new_y0=pos1[post[y1]]-1;
            if(x0<=new_y0)
                root.left=helper(pre,x0,new_y0,post,x1,new_y1);
            if(new_y0<y0)
                root.right=helper(pre,new_y0+1,y0,post,new_y1+1,y1);
        }
        return root;
    }    

//#890. Find and Replace Pattern
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        int n = pattern.length();
        String match = getPattern(pattern,n);
        ArrayList<String> list = new ArrayList<>();
        for(String s:words){
            if(match.equals(getPattern(s,n)))
                list.add(s);
        }
        return list;
    }
    String getPattern(String s,int n){
        StringBuilder sbd = new StringBuilder();
        char[] arr = s.toCharArray();
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(arr[j] == arr[i]){
                    sbd.append(j).append('=').append(i).append(';');
                    break;
                }
            }
        }
        return sbd.toString();
    }
//#894. All Possible Full Binary Trees
    public List<TreeNode> allPossibleFBT(int N) {
        ArrayList<TreeNode> list = new ArrayList<>();
        if(N%2==0) return list;
        if(N==1){
            TreeNode root = new TreeNode(0);
            list.add(root);
            return list;
        }
        for(int i=1;i<N;i+=2){
            for(TreeNode l : allPossibleFBT(i)){
                for(TreeNode r : allPossibleFBT(N-1-i)){
                    TreeNode root = new TreeNode(0);
                    root.left=l;
                    root.right=r;
                    list.add(root);
                }
            }
        }
        return list;
    }

//#898	    Bitwise ORs of Subarrays    
    public int subarrayBitwiseORs(int[] A) {
        Set<Integer> ans = new HashSet<>();
        Set<Integer> cur = new HashSet<>();
        for (int a : A) {
          Set<Integer> nxt = new HashSet<>();
          nxt.add(a);
          for (int b : cur)
            nxt.add(a | b);
          ans.addAll(nxt);
          cur = nxt;
        }
        return ans.size();
      }
    
//#900. RLE Iterator
    class RLEIterator {
        int[] A;
        int pos=0;
        public RLEIterator(int[] A) {
            this.A=A;
        }
        
        public int rle_next(int n) {
            while(n>0){
                for(int i=pos;i<A.length;i+=2){
                    pos=i;
                    if(A[i]>=n){
                        A[i]-=n;
                        return A[i+1];
                    }else{
                        n-=A[i];
                        A[i]=0;
                    }
                }
                return -1;
            }
            return -1;
        }
    }    
    
//#901. Online Stock Span
    class StockSpanner {
        int[] p = null;
        int[] nums = null;
        int pos=0;
        public StockSpanner() {
            p = new int[150000];
            nums = new int[150000];
        }
        
        public int ss_next(int price) {
            p[pos]=price;
            int i=pos-1;
            int res=1;
            while(i>=0){
                if(p[i]==price){
                    res+=nums[i];
                    break;
                }else if(p[i]>price)
                    break;
                else{
                    res+=nums[i];
                    i-=nums[i];
                }
            }

            nums[pos++]=res;
            return res;
        }
    }
//#904. Fruit Into Baskets
    public int totalFruit(int[] tree) {
        int len=tree.length;
        if(len<3)
            return len;
        int num1=1,num2=1;
        int res=0;
        int i=1;
        int a=tree[0];
        while(i<len && tree[i]==a){
            i++;
            num1++;
        }
        if(i==len) return len;
        int b=tree[i];
        int j=i+1;
        i=0;
        while(j<len){
            if(tree[j]==a)
                num1++;
            else if(tree[j]==b)
                num2++;
            else{
                res=Math.max(res,num1+num2);
                while(num1>0 && num2>0){
                    if(tree[i]==a)
                        num1--;
                    else
                        num2--;
                    i++;
                }
                if(num1==0){
                    a=tree[j];
                    num1=1;
                }else{
                    b=tree[j];
                    num2=1;
                }
            }
            j++;
        }
        return Math.max(res,num1+num2);
    }
    
//907. Sum of Subarray Minimums
    public int sumSubarrayMins(int[] A) {
        int n=A.length;
        int mod = 1000000007;
        long res = 0;
        /*
        for(int i=n;i>=0;i--){
            for(int j=0;j<i;j++){
                res+=A[j];
            }
            res %= mod;
            for(int j=1;j<i;j++){
                if(A[j-1]>A[j])
                    A[j-1]=A[j];
            }
        }
        return (int)res;
        */
        
        Stack<Integer> nums = new Stack<>();
        Stack<Integer> lens = new Stack<>();
        int[] left = new int[n];
        for(int i=0;i<n;i++){
            int len = 1;
            while(!nums.isEmpty() && nums.peek()>=A[i]){
                nums.pop();
                len += lens.pop();
            }
            nums.push(A[i]);
            lens.push(len);
            left[i]=len;
        }
        
        nums.clear();lens.clear();
        int[] right = new int[n];
        for(int i=n-1;i>=0;i--){
            int len = 1;
            while(!nums.isEmpty() && nums.peek()>A[i]){
                nums.pop();
                len += lens.pop();
            }
            nums.push(A[i]);
            lens.push(len);
            right[i]=len;
        }
        
        for(int i=0;i<n;i++){
            res += A[i] * left[i]  * right[i] % mod;
            res %= mod;
        }
        return (int)res;
    }

//909. Snakes and Ladders						Solution: BFS + visited
    public int snakesAndLadders(int[][] board) {
        int N = board.length;
        int target = N * N ;
        int res = 1;
        int[] bd = new int[target+1];
        boolean[] visited = new boolean[target+1];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                bd[getIndex(N,i,j)]=board[i][j];
            }
        }
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        
        while(!list.isEmpty()){
            int n = list.size();
            for(int i=0;i<n;i++){
                int x = list.removeFirst();
                visited[x]=true;
                for(int k=1;k<7;k++){
                    if(x+k==target || bd[x+k]==target)
                        return res;
                    if(bd[x+k]>0 && !visited[bd[x+k]])
                        list.add(bd[x+k]);
                }
                int m=6;
                while(m>0 && bd[x+m]>0)
                    m--;
                if(m>0 && !visited[x+m])
                    list.add(x+m);
            }
            res++;
        }
        return -1;
    }
    int getIndex(int n, int i, int j){
        if((n-1-i)%2==0)
            return ((n-1-i)*n+1+j);
        else
            return ((n-1-i)*n+n-j);
    }

//911. Online Election
    int[] times;
    int[] won=null;
    public void TopVotedCandidate(int[] persons, int[] times) {
        this.times=times;
        int n=0;
        for(int i=0;i<persons.length;i++)
            n=Math.max(n,persons[i]);
        won = new int[times.length];
        HashMap<Integer, Integer> votes = new HashMap<>();

        votes.put(persons[0],1);
        won[0]=persons[0];
        int w = 1;
        for(int j=1;j<persons.length;j++){
            int p = persons[j];
            int c = votes.getOrDefault(p,0)+1;
            votes.put(p,c);
            if(w<=c){
                won[j]=p;
                w=c;
            }else
                won[j]=won[j-1];
        }

    }
    
    public int q(int t) {
        int k = Arrays.binarySearch(times,t);
        if(k<0)
            k=-(k+2);
        return won[k];
    }

//#915. Partition Array into Disjoint Intervals
    public int partitionDisjoint(int[] A) {
        int n = A.length;
        Integer[] index = new Integer[n];
        for(int i=0;i<n;i++)
            index[i]=i;
        Arrays.sort(index,(a,b)->A[a]-A[b]);
        
        int max_ind=0;
        for(int i=0;i<n;i++){
            max_ind = Math.max(max_ind,index[i]);
            if(max_ind==i)
                return i+1;
        }
        return -1;
    }
    
    public int partitionDisjointSpeed(int[] A) {
        return helper915(A,0,1);
    }
    
    int helper915(int[] A, int lmax, int len){
        for(int i=A.length-1;i>=len;i--){
            if(A[i]<A[lmax]){
                for(int j=len-1;j<i;j++){
                    if(A[j]>A[lmax])
                        lmax=j;
                }
                return helper915(A,lmax,i+1);
            }
        }
        return len;
    }
///////////////////////////
    
//#918. Maximum Sum Circular Subarray    
    public int maxSubarraySumCircular(int[] A) {
        int n = A.length;
        int[] sum = new int[n];
        int temp=0;
        
        int res=A[0];
        for(int i=0;i<n;i++){
            if(temp<=0)
                sum[i]=A[i];
            else
                sum[i]=temp+A[i];
            temp=sum[i];
            res=Math.max(res,sum[i]);
        }
        int min=0;
        temp=0;
        for(int i=0;i<n;i++){
            if(temp>=0)
                sum[i]=A[i];
            else
                sum[i]=temp+A[i];
            temp=sum[i];
            if(sum[min]>temp)
                min=i;
        }
        min = (min+1)%n;
        temp=0;
        for(int i=min;i<n+min;i++){
            int x = i%n;
            if(temp<=0)
                sum[x]=A[x];
            else
                sum[x]=temp+A[x];
            temp=sum[x];
            res=Math.max(res,sum[x]);
        }
        return res;
    }    
    
//#919. Complete Binary Tree Inserter
    class CBTInserter {
        TreeNode root = null;
        LinkedList<TreeNode> list = null;
        public CBTInserter(TreeNode root) {
            this.root = root;
            list = new LinkedList<>();
            list.add(this.root);
            int i=0;
            while(i<list.size()){
                TreeNode node = list.getFirst();
                if(node.left!=null)
                    list.add(node.left);
                if(node.right!=null)
                    list.add(node.right);
                if(node.left != null && node.right!=null){
                    list.removeFirst();
                    i--;
                }
                if(node.left==null || node.right==null)
                    break;
                i++;
            }
        }
        
        public int insert(int v) {
            TreeNode node = list.getFirst();
            TreeNode newNode = new TreeNode(v);
            if(node.left==null){
                node.left=newNode;
            }else{
                node.right=newNode;
                list.removeFirst();
            }
            list.add(newNode);
            return node.val;
        }
        
        public TreeNode get_root() {
            return this.root;
        }
    }

//#921. Minimum Add to Make Parentheses Valid    
    public int minAddToMakeValid(String S) {
        int left=0;
        int right=0;
        
        for(char c : S.toCharArray()){
            if(c==')'){
                if(right>0) 
                    right--;
                else
                    left++;
            }else if(c=='('){
                right++;
            }
        }
        return left+right;
    }
    
//#923. 3Sum With Multiplicity    
    public int threeSumMulti(int[] A, int target) {
        long[] nums = new long[101];
        long count=0;
        for(int i : A)
            nums[i]++;

        for(int i=0;i<nums.length-2;i++){
            if(nums[i]==0)  continue;
            for(int j=i;j<nums.length-1;j++){
                if(nums[j]>0){
                    int k=target - i - j;
                    if(k>=j && k<=100 && nums[k]>0){
                        if(i<j && j<k)
                            count += (long)nums[i]*nums[j]*nums[k] %1000000007 ;
                        else if(i==j && j==k){
                            if(nums[i]>=3)
                                count += (long)nums[i] * (nums[i]-1) * (nums[i]-2) / 6 %1000000007;
                            System.out.print(count);
                        }
                        else if(i==j && nums[i]>=2)
                            count += (long) nums[i] * (nums[i]-1) * nums[k]/2 %1000000007;
                        else if(j==k && nums[j]>=2)
                            count += (long) nums[i] * (nums[j]-1) * nums[j]/2 %1000000007;
                        count %= 1000000007;
                    }
                }
            }
        }
        return (int) (count%1000000007);
    }

//#926. Flip String to Monotone Increasing
    public int minFlipsMonoIncr(String S) {
        int keep0 = 0;
        int keep1 = 0;
        
        for(int i=0;i<S.length();i++){
            if(S.charAt(i)=='0'){
                keep1 = Math.min(keep0,keep1)+1;
            }else{
                keep1 = Math.min(keep0,keep1);
                keep0++;
            }
        }
        return Math.min(keep0,keep1);
    }

//#930. Binary Subarrays With Sum    
    public int numSubarraysWithSum(int[] A, int S) {
        int n=A.length;
        int[] sum = new int[n+1];
        sum[0]=1;
        int t = 0;
        int count=0;
        for(int i : A){
            t += i;
            if(t-S>=0)
                count += sum[t-S];
            sum[t]++;
        }
        return count;
    }    
//#931.   931. Minimum Falling Path Sum  
    public int minFallingPathSum(int[][] A) {
	    int n = A.length;
	    int[][] dp = new int[n][n];
	    dp[0] = A[0];
	    for(int i=1;i<n;i++){
	        for(int j=0;j<n;j++){
	            int x = dp[i-1][j];
	            if(j>0)x=Math.min(x,dp[i-1][j-1]);
	            if(j<n-1)x=Math.min(x,dp[i-1][j+1]);
	            dp[i][j]=A[i][j]+x;
	        }
	    }
	    int res = Integer.MAX_VALUE;
	    for(int x : dp[n-1]){
	        res=Math.min(res,x);
	    }
	    return res;
    }
    
//934. Shortest Bridge
    public int shortestBridge(int[][] A) {
        int n=A.length;
        LinkedList<Point> list = new LinkedList<>();
        for(int i=0;i<n;i++){
            boolean done = false;
            for(int j=0;j<n;j++){
                if(A[i][j]==1){
                    floodFill(A, i, j, list);
                    done=true;
                    break;
                }
            }
            if(done) break;
        }
        int count = 0;
        int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        while(!list.isEmpty()){
            int m = list.size();
            for(int x=0;x<m;x++){
                Point p = list.removeFirst();
                for(int k=0;k<4;k++){
                    int i = p.x + dir[k][0];
                    int j = p.y + dir[k][1];
                    if(i>=0 && i<A.length && j>=0 && j<A.length){
                        if(A[i][j]==1) return count;
                        if(A[i][j]==0){
                            list.add(new Point(i,j));
                            A[i][j]=2;
                        }
                    }
                }
            }
            count++;
        }
        return count;
    }
    void floodFill(int[][] A, int x, int y, LinkedList<Point> list){
        int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        A[x][y]=2;
        boolean isSide = false;
        for(int k=0;k<4;k++){
            int i = x + dir[k][0];
            int j = y + dir[k][1];
            if(i>=0 && i<A.length && j>=0 && j<A.length){
                if(A[i][j]==0)
                    isSide = true;
                else if(A[i][j]==1)
                    floodFill(A,i,j,list);
            }
        }
        if(isSide)
            list.add(new Point(x,y));
    }
    static class Point{
        int x,y;
        Point(int x, int y){
            this.x=x;
            this.y=y;
        }
    }

//935. Knight Dialer    
    public int knightDialer(int N) {
        if(N==1) return 10;
        long[] d = new long[10];
        Arrays.fill(d,1);
        d[5]=0;

        while(--N>0){
            long t=d[0];
            d[0]=(d[4]+d[6])% 1000000007;
            d[1]=(d[6]+d[8])% 1000000007;
            d[2]=d[8]=(d[7]+d[9])% 1000000007;
            d[4]=d[6]=(d[3]+d[9] + t)% 1000000007;
            d[3]=d[7]=d[9]=d[1];

        }
        long res = 0;
        for(int i=0;i<10;i++)
            res = (res+d[i]) % 1000000007;
        return (int)res;
    }


//939. Minimum Area Rectangle
    public int minAreaRect(int[][] points) {
        int n=points.length;
        Arrays.sort(points, (a,b)->{if(a[0]==b[0]) 
                                        return a[1]-b[1];
                                    else 
                                        return a[0]-b[0];});
        int res = Integer.MAX_VALUE;
        for(int i=0;i<n-1;i++){
            int x0=points[i][0];
            int y0=points[i][1];
            for(int j=i+1;j<n;j++){
                if(points[j][0]!=x0) break;
                //found 
                int y1=points[j][1];
                for(int k=j+1;k<n;k++){
                    if(points[k][1]==y0){
                        int x1=points[k][0];
                        for(int l=k+1;l<n;l++){
                            if(points[l][0]!=x1) break;
                            if(points[l][1]==y1){
                                res=Math.min(res,(x1-x0)*(y1-y0));
                            }
                        }
                    }
                }
            }
        }
        
        return res<Integer.MAX_VALUE?res:0;
    }
    
 //945. Minimum Increment to Make Array Unique
    public int minIncrementForUnique(int[] A) {
        if(A.length==0) return 0;
        int res = 0;
        int[] B = new int[80000];
        int max=0,min=A[0];
        for(int i=0;i<A.length;i++){
            max=Math.max(max,A[i]);
            min=Math.min(min,A[i]);
            B[A[i]]++;
        }
        int j=min+1;
        for(int i=min;i<=max;i++){
            while(B[i]>1){
                j=Math.max(j,i+1);
                while(B[j]>0)j++;
                res += j-i;
                B[i]--;
                B[j]++;
            }
        }
        return res;
    }


//947. Most Stones Removed with Same Row or Column
    public int removeStones(int[][] stones) {
        int n = stones.length;
        int count = 0;
        int[] rootr = new int[10000];
        int[] rootc = new int[10000];
        Arrays.fill(rootr,-1);
        Arrays.fill(rootc,-1);
        for(int i=0;i<n;i++){
            int r=findRootr(stones,rootr,stones[i][0]);
            int c=findRootc(stones,rootc,stones[i][1]);
            if(r<0 && c<0){
                rootr[stones[i][0]]=i;
                rootc[stones[i][1]]=i;
                count++;
            }else if(r>=0 && c<0)
                rootc[stones[i][1]]=r;
            else if(r<0 && c>=0)
                rootr[stones[i][0]]=c;
            else if(r!=c){
                rootr[stones[c][0]]=r;
                rootc[stones[c][1]]=r;
                count--;
            }
        }
        return n-count;
    }
    int findRootr(int[][] stones, int[] root, int x){
        while(root[x]>=0 && stones[root[x]][0]!=x)
            x=stones[root[x]][0];
        return root[x];
    }
        
    int findRootc(int[][] stones, int[] root, int x){
        while(root[x]>=0 && stones[root[x]][1]!=x)
            x=stones[root[x]][1];
        return root[x];
    }

//#948. Bag of Tokens    
    public int bagOfTokensScore(int[] tokens, int P) {
        int n = tokens.length;
        if(n==0) return 0;

        Arrays.sort(tokens);
        int score = 0;
        int maxScore=0;
        int i=0,j=n-1;
        while(i<=j){
            while(i<n && P>=tokens[i]){
                score++;
                maxScore=Math.max(maxScore,score);
                P-=tokens[i++];
            }
            if(i<j && score>0){
                score--;
                P+=tokens[j--];
            }else
                break;
        }
        return maxScore;
    }    
    
}
