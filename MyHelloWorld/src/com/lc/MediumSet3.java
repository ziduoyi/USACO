package com.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeMap;
import com.lc.MediumSet2.TreeNode;

public class MediumSet3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MediumSet3 set3 = new MediumSet3();
	
		//set3.dailyTemperatures(new int[]{73,74,75,71,69,72,76,73});
		
//		set3.deleteAndEarn(new int[]{10,8,4,2,1,3,4,8,2,9,10,4,8,5,9,1,5,1,6,8,1,1,6,7,8,9,1,7,6,8,4,5,4,1,5,9,8,6,10,6,4,3,8,4,10,8,8,10,6,4,4,4,9,6,9,
//				10,7,1,5,3,4,4,8,1,1,2,1,4,1,1,4,9,4,7,1,5,1,10,3,5,10,3,10,2,1,10,4,1,1,4,1,2,10,9,7,10,1,2,7,5});
		
		//set3.networkDelayTime(new int[][]{{2,1,1},{2,3,1},{3,4,1}},4,2);
//		set3.networkDelayTime(new int[][]{{4,2,76},{1,3,79},{3,1,81},{4,3,30},{2,1,47},{1,5,61},{1,4,99},{3,4,68},{3,5,46},{4,1,6},{5,4,7},{5,3,44},
//			{4,5,19},{2,3,13},{3,2,18},{1,2,0},{5,1,25},{2,5,58},{2,4,77},{5,2,74}},5,3);
		
		//set3.openLock(new String[]{"0201","0101","0102","1212","2002"},"0202");
		
		//set3.partitionLabels("caedbdedda");
		
		//set3.orderOfLargestPlusSign(5, new int[][]{{4,2}});
		
		//set3.reorganizeString("vvvlo");
		
		//set3.isBipartite(new int[][]{{},{2,4,6},{1,4,8,9},{7,8},{1,2,8,9},{6,9},{1,5,7,8,9},{3,6,9},{2,3,4,6,9},{2,4,5,6,7,8}});
		
//		set3.numSubarrayBoundedMax(new int[]{876,880,482,260,132,421,732,703,795,420,871,445,400,291,358,589,617,202,755,810,227,813,549,791,418,528,
//				835,401,526,584,873,662,13,314,988,101,299,816,833,224,160,852,179,769,646,558,661,808,651,982,878,918,406,551,467,87,139,387,16,531,
//				307,389,939,551,613,36,528,460,404,314,66,111,458,531,944,461,951,419,82,896,467,353,704,905,705,760,61,422,395,298,127,516,153,299,
//				801,341,668,598,98,241} , 658, 719);
		
		//set3.eventualSafeNodes(new int[][]{{1,2},{2,3},{5},{0},{5},{},{}});
		
		//set3.largestSumOfAverages(new int[]{9,1,2,3,9}, 3);
		
		//set3.ambiguousCoordinates( "(123)");

		//set3.numFactoredBinaryTrees(new int[]{18,3,6,2});
		
		set3.new21Game(3, 2, 3);
	}

	
//#721. Accounts Merge	-- union find	*****5 star*****
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        TreeMap<String,Integer> map = new TreeMap<>();
        int n=accounts.size();
        int[] group = new int[n];
        for(int i=0;i<n;i++){
            group[i]=i;
        }

        for(int i=0;i<n;i++){
            List<String> account = accounts.get(i);
            for(int j=1;j<account.size();j++){
                String mail = account.get(j);
                Integer num = map.get(mail);
                if(num!=null){
                    if(group[i]==i)
                        group[i]=findRoot(group, num);
                    else{
                        int x= findRoot(group, num);
                        group[x]=group[i];
                    }
                }else{
                    map.put(mail,i);
                }
            }
        }
        List<List<String>> res = new ArrayList<>();
        Map<Integer, List<String>> map2 = new HashMap<>();
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            int num = findRoot(group,entry.getValue());
            List<String> list = map2.get(num);
            if(list==null){
                list = new ArrayList<String>();
                list.add(accounts.get(num).get(0)); //add name
                map2.put(num,list);
                res.add(list);
            }
            list.add(entry.getKey());
        }
        return res;

    }
    int findRoot(int[] g, int i){
        while(g[i]!=i){
            i=g[i];
        }
        return i;
    }
    
// 729. My Calendar I		*****5 star*****
//book(int start, int end). Formally, this represents a booking on the half open interval [start, end), 
//the range of real numbers x such that start <= x < end.
    class MyCalendar {
        TreeMap<Integer, Integer> map = null;
         
        public MyCalendar() {
            map = new TreeMap<>();
        }
        
        public boolean book(int start, int end) {
            Map.Entry<Integer,Integer> entry = map.floorEntry(start);
            if(entry!=null && entry.getValue()>start)
                    return false;

            Integer key = map.ceilingKey(start);
            if(key!=null && end>key)
                return false;
            map.put(start,end);
            return true;
        }
    }
//731  My Calendar II
    
    class MyCalendarTwo {
        TreeMap<Integer, Integer> map = null;	//key: date, start or end;  value: num of events.  at start, value+1; at end date, value-1

        public MyCalendarTwo() {
            map = new TreeMap<>();
        }
        
        public boolean book(int start, int end) {
            int prev = start;
            Integer prekey = map.floorKey(start);
            if(prekey!=null) prev = prekey;
            Map<Integer, Integer> submap = map.subMap(prev,end);	//parameter 2: end: exclusive
            for(int v : submap.values()){
                if(v==2) return false;
            }
            for(Map.Entry<Integer,Integer> entry: submap.entrySet()){
                int key = entry.getKey();
                int value = entry.getValue();
                if(key>start)
                    map.put(key,value+1);
            }
            map.put(start,prekey==null?1:map.get(prekey)+1);
            map.putIfAbsent(end,map.floorEntry(end).getValue()-1);	//if end date is the same as other start date, don't update value here
            return true;
        }
    }
    
// Solution II: reuse my Calendar I code.
    public class MyCalendarII {

        public MyCalendarII() {
        	books = new ArrayList<>();
        }
        
        private List<int[]> books = null;

        public boolean book(int s, int e) {
            MyCalendar overlaps = new MyCalendar();
            for (int[] b : books)
                if (Math.max(b[0], s) < Math.min(b[1], e)) // overlap exist 第一次不怕，要是和以前的overlap重合就不行了，overlaps是记录overlap的
                    if (!overlaps.book(Math.max(b[0], s), Math.min(b[1], e))) return false; // overlaps overlapped
            books.add(new int[]{ s, e });
            return true;
        }
    }
    
//732. My Calendar III
    class MyCalendarThree {
        TreeMap<Integer,Integer> map = null;
        public MyCalendarThree() {
            map = new TreeMap<>();
        }

        public int book(int start, int end) {
            int count=1;
            Map<Integer,Integer> subMap = map.subMap(start,end);
            for(Map.Entry<Integer, Integer> entry : subMap.entrySet()){
                int key = entry.getKey();
                int value = entry.getValue();
                if(key>start){
                    map.put(key,value+1);
                }
            }
            Integer pre = map.floorKey(start);
            map.put(start, pre==null?1:map.get(pre)+1);
            map.putIfAbsent(end,map.floorEntry(end).getValue()-1);
            for(int v : map.values()){
                count = Math.max(count,v);
            }

            return count;
        }
    }
//#735. Asteroid Collision
    public int[] asteroidCollision(int[] ast) {
        Stack<Integer> stack = new Stack<>();
        int n = ast.length;
        int i=0;
        while(i<n){
            if(ast[i]>0)
                stack.push(i);
            if(ast[i]<0){
                if(!stack.isEmpty()){
                    int j=stack.peek();
                    int s = ast[i]+ast[j];
                    if(s==0){
                        ast[i]=0;
                        ast[j]=0;
                        stack.pop();
                    }else if(s>0){
                        ast[i]=0;
                    }else{
                        stack.pop();
                        ast[j]=0;
                        i--;
                    }
                }
            }
            i++;
        }

        int k=0;
        for(i=0;i<n;i++)
            if(ast[i]!=0)
                k++;
        int[] res = new int[k];
        k=0;
        for(i=0;i<n;i++)
            if(ast[i]!=0)
                res[k++]=ast[i];

        return res;
    }
    
// #737	Sentence Similarity II $    
    
    
//#739	    Daily Temperatures    
    public int[] dailyTemperatures(int[] T) {
        int n=T.length;
        int[] res = new int[n];
        res[n-1]=0;
        int j=0;
        for(int i=n-2;i>=0;i--){
            j=i+1;
            if(T[i]<T[j]){
                res[i]=1;
                continue;
            }
            while(T[i]>=T[j] && res[j]>0){
                j+=res[j];
            }
            if(T[i] < T[j])
                res[i]=j-i;
            else
                res[i]=0;
        }
        return res;
    }
    
//#740. Delete and Earn		--- one dimensional Dynamic programming---
    public int deleteAndEarn(int[] nums) {
        if(nums.length==0) return 0;
        int[] sums=new int[10001];
        int min=nums[0],max=nums[0];
        for(int i=0;i<nums.length;i++){
            int x = nums[i];
            sums[x] += x;
            min=Math.min(min,x);
            max=Math.max(max,x);
        }
        int i=min;
        while(++i<=max){
           sums[i]=Math.max(sums[i-2]+sums[i],sums[i-1]);
        }
        return sums[max];
    }
    public int deleteAndEarn2(int[] nums) {
        if(nums.length==0) return 0;
        int[] sums=new int[10001];
        int min=nums[0],max=nums[0];
        for(int i=0;i<nums.length;i++){
            int x = nums[i];
            sums[x] += x;
            min=Math.min(min,x);
            max=Math.max(max,x);
        }
        int i=min;
        int take=0,skip=0;
        int takepre=sums[i],skippre=0;
        while(++i<=max){
           take = skippre + sums[i];
           skip = Math.max(takepre, skippre);
           takepre = take;
           skippre = skip;
        }
        return Math.max(take, skip);
    }
    
 //213. House Robber II
    
    public int rob(int[] nums) {
        int n = nums.length;
        if(n==0)return 0;
        if(n==1)return nums[0];
        if(n==2)return Math.max(nums[0],nums[1]);

        //int res1 = getAmount(nums);
        int nums0 = nums[0];
        nums[0]=0;
        int res2 = getAmount(nums);
        //if(res1==res2) return res2;
        nums[0]=nums0;
        nums[n-1]=0;
        int res3 = getAmount(nums);
        //if(res1==res3) return res3;
        return Math.max(res2,res3);
        
    }
    int getAmount(int[] nums){
        int take=nums[0],skip=0;
        int takepre=nums[0],skippre=0;
        for(int i=1;i<nums.length;i++){
            take=skippre + nums[i];
            skip=Math.max(skippre,takepre);
            skippre = skip;
            takepre = take;
        }
        return Math.max(take,skip);
    }
//#337 House Robber III
    public int rob(TreeNode root) {
        return postOrder337(root)[0];
    }
    int[] postOrder337(TreeNode t){
        int[] res = new int[2];
        if(t==null) return res;

        int[] res1 = postOrder337(t.left);
        int[] res2 = postOrder337(t.right);
        
        res[1]= Math.max(res1[0]+res2[0],res1[1]+res2[1]);
        res[0]= Math.max(res1[0]+res2[0],res1[1]+res2[1]+t.val);
        return res;
    }    
//#742 Closest Leaf in a Binary Tree 二叉树中最近的叶结点
    int getClosestLeaf(TreeNode root, int k){
    	HashMap<Integer,HashSet<Integer>> connections = new HashMap<>();
    	dfs742(root,connections);
    	
    	if(!connections.containsKey(k)) return 0;
    	
    	LinkedList<Integer> list = new LinkedList<>();
    	HashSet<Integer> visited = new HashSet<>();
    	list.add(k);
    	visited.add(k);
    	while(!list.isEmpty()){
    		int x = list.removeFirst();
    		HashSet<Integer> cs = connections.get(x);
    		if(x!=root.val && cs.size()==1) return x;
    		for(int c : cs){
    			if(!visited.contains(c)){
    				list.add(c);
    				visited.add(c);
    			}
    		}
    	}
    	return -1;
    }
    void dfs742(TreeNode t, HashMap<Integer,HashSet<Integer>> connections){
    	if(t==null) return;
    	
    	HashSet<Integer> conn = connections.get(t.val);
    	if(conn==null){
    		conn = new HashSet<>();
    		connections.put(t.val,conn);
    	}
    	if(t.left!=null){
    		conn.add(t.left.val);
    		HashSet<Integer> cl = connections.get(t.val);
        	if(cl==null){
        		cl = new HashSet<>();
        		connections.put(t.left.val,cl);
        	}
    		cl.add(t.val);
    	}
    	if(t.right!=null){
    		conn.add(t.right.val);
    		HashSet<Integer> cr = connections.get(t.val);
        	if(cr==null){
        		cr = new HashSet<>();
        		connections.put(t.right.val,cr);
        	}
    		cr.add(t.val);
    	}
    	dfs742(t.left,connections);
    	dfs742(t.right,connections);
    	
    }

//#743. Network Delay Time
//graph Dijkstra    
    public int networkDelayTime(int[][] times, int N, int K) {
	    int m=times.length;
	    //int n=times[0].length;
	    
	    LinkedList<Integer> list = new LinkedList<>();
	    HashSet<Integer> visited = new HashSet<>();
	    int[] dist=new int[N+1];
	    Arrays.fill(dist,Integer.MAX_VALUE);
	    dist[K]=0;
	    list.add(K);
	    visited.add(K);
	
	    while(!list.isEmpty()){
	    	for(int x=list.size();x>0;x--){
		        int u = list.removeFirst();
		        for(int i=0;i<m;i++){
		            if(times[i][0]!=u) continue;
		            int v = times[i][1];
		            int s = dist[u] + times[i][2];
		            if(dist[v]>s)
		                dist[v]=s;
		            if(!visited.contains(v)){
		                visited.add(v);
		                list.add(v);
		            }
		        }
	    	}
	    }
	    
	    int res = 0;
	    for(int i=1;i<=N;i++){
	        res = Math.max(res,dist[i]);
	    }
	    if(res==Integer.MAX_VALUE)
	        return -1;
	    else
	        return res;
    }   
    
//solution 2
    class Edge{
        int node;
        int time;
        public Edge(int n,int t){this.node=n;this.time=t;}
    }
    public int networkDelayTime2(int[][] times, int N, int K) {
        int[] dist = new int[N+1];
        Arrays.fill(dist,600000);
        dist[0]=0;
        dist[K]=0;
        
        ArrayList<Edge>[] edges = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            edges[i]=new ArrayList<Edge>();
        }
        for(int[] e : times){
            edges[e[0]].add(new Edge(e[1],e[2]));
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(K);
        boolean[] visited = new boolean[N+1];
        visited[K]=true;
        while(true){
            int time=600000;
            Edge edge = null;
            for(int x : list){
                for(Edge e : edges[x]){
                    if(!visited[e.node] && time>dist[x]+e.time){
                        time=dist[x]+e.time;
                        edge=e;
                    }
                }
            }
            if(edge==null) break;
            dist[edge.node]=time;
            list.add(edge.node);
            visited[edge.node]=true;
        }
        int max=0;
        for(int x : dist){
            max=Math.max(max,x);
        }
        return max==600000?-1:max;
    }

    
//Dijkstra's algorithm
//    Time complexity: O(Nlog(N) + E), Space complexity: O(N + E)    
    public int networkDelayTime_Dijkstra(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge: times) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        boolean[] visited = new boolean[N + 1];
        int[] minDis = new int[N + 1];
        Arrays.fill(minDis, Integer.MAX_VALUE);
        minDis[K] = 0;
        pq.offer(new int[]{0, K});
        int max = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currNode = curr[1];
            if (visited[currNode]) continue;
            visited[currNode] = true;
            int currDis = curr[0];
            max = currDis;
            N--;
            if (!graph.containsKey(currNode)) continue;
            for (int[] next : graph.get(currNode)) {
                if (!visited[next[0]] && currDis + next[1] < minDis[next[0]])
                    pq.offer(new int[]{currDis + next[1], next[0]});
            }
        }
        return N == 0 ? max : -1;
    }
    
//    Bellman-Ford algorithm
//    Time complexity: O(N*E), Space complexity: O(N)
    public int networkDelayTime_BF(int[][] times, int N, int K) {
        double[] disTo = new double[N];
        Arrays.fill(disTo, Double.POSITIVE_INFINITY);
        disTo[K - 1] = 0;
        for (int i = 1; i < N; i++) {
            for (int[] edge : times) {
                int u = edge[0] - 1, v = edge[1] - 1, w = edge[2];
                disTo[v] = Math.min(disTo[v], disTo[u] + w);
            }
        }
        double res = Double.MIN_VALUE;
        for (double i: disTo) {
            res = Math.max(i, res);
        }
        return res == Double.POSITIVE_INFINITY ? -1 : (int) res;
    }
    
    public int networkDelayTime_Bellman(int[][] times, int N, int K) {
        int[] disTo = new int[N];
        Arrays.fill(disTo, 600000);
        disTo[K - 1] = 0;
        for (int i = 1; i < N; i++) {
            for (int[] edge : times) {
                int u = edge[0] - 1, v = edge[1] - 1, w = edge[2];
                disTo[v] = Math.min(disTo[v], disTo[u] + w);
            }
        }
        int res = 0;
        for (int i: disTo) {
            res = Math.max(i, res);
        }
        return res == 600000 ? -1 : (int) res;
    } 
    
//Floyd–Warshall algorithm
//Time complexity: O(N^3), Space complexity: O(N^2)
    public int networkDelayTime_FW(int[][] times, int N, int K) {
        double[][] disTo = new double[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(disTo[i], Double.POSITIVE_INFINITY);
        }
        for (int i = 0; i < N; i++) {
            disTo[i][i] = 0;
        }
        for (int[] edge: times) {
            disTo[edge[0] - 1][edge[1] - 1] = edge[2];
        }
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (disTo[i][j] > disTo[i][k] + disTo[k][j])
                        disTo[i][j] = disTo[i][k] + disTo[k][j];
                }
            }
        }
        double max = Double.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (disTo[K - 1][i] == Double.POSITIVE_INFINITY) return -1;
            max = Math.max(max, disTo[K - 1][i]);
        }
        return (int) max;
    }    
/////////////////////////////////////////////////////////////////////////////////    

//#748	Shortest Completing Word 
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] letters = new int[26];
        
        int num=0;
        for(char c : licensePlate.toCharArray()){
            if('A'<=c && c<='Z'){
                letters[c-'A']++;
                num++;
            }else if('a'<=c && c<='z'){
                letters[c-'a']++;
                num++;
            }
        }
        
        int min=16;
        String res = null;
        for(String word : words){
            int diff = getDifference(Arrays.copyOf(letters,letters.length),word,num);
            if(diff==0) return word;
            if(diff<min){
                min=diff;
                res=word;
            }
        }
        return res;
    }
    int getDifference(int[] letters,String word,int len){
        if(word.length()<len) return 16;
        int res=0;
        for(char c : word.toCharArray()){
            if(letters[c-'a']>0){
                letters[c-'a']--;
                if(len>0)
                    len--;
                else
                    res++;
            }else
                res++;
        }
        for(int i=0;i<26;i++){
            if(letters[i]>0)
                return 16;
        }
        return res;
    }
    
//#750	Number Of Corner Rectangles
    int getNumOfRectangles(int[][] p){
    	int res=0;
    	int m=p.length;
    	int n=p[0].length;
    	for(int i=0;i<m-1;i++)
    		for(int j=0;j<n-1;j++)
    			for(int k=i+1;k<m;k++)
    				for(int l=j+1;l<n;l++)
    					if(p[i][j]==1 && p[k][j]==1 && p[k][l]==1 && p[i][l]==1)
    						res++;
    	return res;
    }
    
//#752. Open the Lock    
    static final String initail = "0000";
    public int openLock(String[] deadends, String target) {
        
        LinkedList<String> list = new LinkedList<>();
        HashSet<String> deadset = new HashSet<>();
        Collections.addAll(deadset,deadends);
        if(deadset.contains(initail)) return -1;

        list.add(initail);
        int res=0;
        HashSet<String> visited = new HashSet<>();
        visited.add(initail);
        while(!list.isEmpty()){
            int n=list.size();
            for(int i=0;i<n;i++){
                String str = list.removeFirst();
                if(str.equals(target)) return res;

                char[] cur = str.toCharArray();
                for(int j=0;j<4;j++){
                    char c=str.charAt(j);
                    cur[j]=c=='9'?'0':(char)(c+1);
                    String next1 = new String(cur);
                    if(!visited.contains(next1) && !deadset.contains(next1)){
                        list.add(next1);
                        visited.add(next1);
                    }
                    cur[j]=c=='0'?'9':(char)(c-1);
                    String next2 = new String(cur);
                    if(!visited.contains(next2) && !deadset.contains(next2)){
                        list.add(next2);
                        visited.add(next2);
                    }
                    cur[j]=c;
                }
            }
            res++;
        }
        return -1;
    }
//#	755	Pour Water
    public int[] pourWater(int[] heights, int V, int K) {
        for(int i=V;i>0;i--){
            boolean found = false;
            int low = K;
            for(int l=K-1;l>=0;l--){
                if(heights[l]>heights[low])
                    break;
                if(heights[l]<heights[low]){
                    found=true;
                    low=l;
                }
            }
            if(found){
                heights[low]++;
                continue;
            }
            for(int r=K+1;r<heights.length;r++){
                if(heights[r]>heights[low])
                    break;
                if(heights[r]<heights[low]){
                    found=true;
                    low=r;
                } 
            }
            if(found){
                heights[low]++;
            }
            if(!found)
                heights[K]++;
        }
        return heights;
    }

    
//#756. Pyramid Transition Matrix    
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        return helper756(bottom,"",allowed);
    }

    boolean helper756(String bottom, String upper, List<String> allowed){
        if(bottom.length()<2){
            if(upper.length()<2) return true;
            return helper756(upper, "",allowed);
        }
        String pre = bottom.substring(0,2);
        for(String s : allowed){
            if(s.startsWith(pre)){
                if(helper756(bottom.substring(1),upper+s.substring(2),allowed))
                    return true;
            }
        }
        return false;
    }
    
//#763. Partition Labels    
    public List<Integer> partitionLabels(String s) {
        int[][] pos = new int[26][2];
        for(int i=0;i<26;i++)
        Arrays.fill(pos[i],-1);
        
        for(int i=0;i<s.length();i++){
            int x = s.charAt(i)-'a';
            if(pos[x][0]<0)
                pos[x][0]=i;
            else
                pos[x][1]=i;
        }
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=0;i<25;i++){
            if(pos[i][0]<0) continue;
            for(int j=i+1;j<26;j++){
                if(pos[j][0]<0) continue;

                if(pos[j][0] < pos[i][0] && pos[i][0] < pos[j][1]){
                    pos[i][0]=-1;
                    if(pos[i][1]>pos[j][1]) 
                        pos[j][1] = pos[i][1];
                    break;
                }
                if(pos[i][0] < pos[j][0] && pos[j][0] < pos[i][1]){
                    if(pos[i][1]>pos[j][1]) 
                        pos[j][0] = -1;
                    else{
                        pos[j][0] = pos[i][0];
                        pos[i][0]=-1;
                        break;
                    }
                }
            }
        }
        Arrays.sort(pos,(a,b)->a[0]-b[0]);
        for(int[] x : pos){
            if(x[0]>-1){
                if(x[1]<0)
                    res.add(1);
                else
                    res.add(x[1]-x[0]+1);
            }
        }
        return res;
    }
    
//#764. Largest Plus Sign
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int[][] p = new int[N][N];
        for(int i=0;i<N;i++)
            Arrays.fill(p[i],N);

        for(int i=0;i<mines.length;i++){
            p[mines[i][0]][mines[i][1]]=0;
        }

        for(int i=0;i<N;i++){
            int l=0,r=0,u=0,d=0;
            for(int j=0,k=N-1;j<N;j++,k--){     // l = (dp[i][j] ? l + 1 : 0)
                l=p[i][j]>0?l+1:0;
                p[i][j]=Math.min(p[i][j],l);
                r=p[i][k]>0?r+1:0;
                p[i][k]=Math.min(p[i][k],r);
                u=p[j][i]>0?u+1:0;
                p[j][i]=Math.min(p[j][i],u);
                d=p[k][i]>0?d+1:0;
                p[k][i]=Math.min(p[k][i],d);
            }
        }
        int res=0;
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++)
                res=Math.max(res,p[i][j]);
        return res;
    }

//#767. Reorganize String
    public String reorganizeString(String S) {
        int[][] letters = new int[26][2];
        int max = 0;
        int n = S.length();
        char[] res = S.toCharArray();
        for(char c : res){
            int i=c-'a';
            letters[i][0]=i;
            letters[i][1]++;
            max=Math.max(max,letters[i][1]);
        }
        if(max>(n+1)/2) return "";

        Arrays.sort(letters,(a,b)->(b[1]-a[1]));
        int i=0;
        for(int j=0;j<n;j+=2){
            if(letters[i][1]==0) 
                i++;
            letters[i][1]--;
            res[j] = (char)('a'+letters[i][0]);
        }
        for(int j=1;j<n;j+=2){
            if(letters[i][1]==0) 
                i++;
            letters[i][1]--;
            res[j] = (char)('a'+letters[i][0]);
        }
        return new String(res);
    }
    
//$776 Split BST    
    public TreeNode[] splitBST(TreeNode root,int v){
    	if(root==null) return null;
    	TreeNode[] nodes = new TreeNode[2];
    	if(root.val>=v){
    		TreeNode[] res = splitBST(root.left,v);
    		nodes[0]=res[0];
    		nodes[1]=root;
    		root.left=res[1];
    	}else{
    		TreeNode[] res = splitBST(root.right,v);
    		nodes[0]=root;
    		nodes[1]=res[1];
    		root.left = res[0];
    	}
		return nodes;
    }

    
//#785. Is Graph Bipartite
    public boolean isBipartite(int[][] graph) {
        int[] bi=new int[graph.length];
        
        for(int i=0;i<bi.length;i++){
            if(bi[i]==0){
                //bi[i]=1;
                if(!helper785(graph,bi,i,1))
                    return false;
            }
        }
        return true;
    }
    
    boolean helper785(int[][] graph, int[] bi, int x, int v){
        //if(bi[x]!=0)
        //    return helper785(graph,bi,x+1);
        
        bi[x]=v;
        for(int i=0;i<graph[x].length;i++){
            int k = graph[x][i];
            if(bi[k]==v) return false;
            if(bi[k]==0)
                if(!helper785(graph,bi,k,-v))
                    return false;
        }
        return true;
    }
    
//non recursive solution:
    public boolean isBipartiteNR(int[][] graph) {
        int[] bi=new int[graph.length];

        LinkedList<Integer> list = new LinkedList<>();
        for(int i=0;i<bi.length;i++){
            if(bi[i]!=0) continue;
            list.add(i);
            bi[i]=1;
            while(!list.isEmpty()){
                int x=list.removeFirst();
                for(int j=0;j<graph[x].length;j++){
                    int k = graph[x][j];
                    if(bi[k]==bi[x]) return false;
                    if(bi[k]==0){
                       bi[k]=-bi[x];
                        list.add(k); 
                    }
                }
            }
        }
        return true;
    }
    
    
//#787. Cheapest Flights Within K Stops
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        LinkedList<Integer> list = new LinkedList<>();
        prices[src]=0;
        list.add(src);
        for(int x=0;x<=K;x++){
            int num = list.size();
            for(int z=0;z<num;z++){
                int from = list.removeFirst();
                for(int i=0;i<flights.length;i++){
                    int[] f = flights[i];
                    int u = f[0],v=f[1],price=f[2];
                    if(u==from){
                        if(x==K && v!=dst) continue;				//skip one case
                        if(price+prices[u]<prices[v]){
                            prices[v]=price+prices[u];
                            list.add(v);
                        }
                        //list.add(v);		//move this action inside the if check to cut useless path.
                    }
                }
            }
        }
        if(prices[dst]==Integer.MAX_VALUE)
            return -1;
        return prices[dst];
    }
//#791. Custom Sort String
    public String customSortString(String S, String T) {
        if(S==null || S.length()<2 || T==null || T.length()<2) return T;
        
        int[] count = new int[26];
        for(int i=0;i<T.length();i++){
            count[T.charAt(i)-'a']++;
        }
        char[] res = new char[T.length()];
        int x=0;
        for(int i=0;i<S.length();i++){
            char c = S.charAt(i);
            for(int j=0;j<count[c-'a'];j++){
                res[x++]= c;
            }
            count[c-'a']=0;
        }
        for(int i=0;i<count.length;i++){
            for(int j=0;j<count[i];j++){
                res[x++]=(char)(i+'a');
            }
        }
        return new String(res);
    }
    
//#792. Number of Matching Subsequences
    public int numMatchingSubseq(String S, String[] words) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>(26);
        for(int i=0;i<26;i++)
            list.add(null);

        for(int i=0;i<S.length();i++){
            int x = S.charAt(i)-'a';
            ArrayList<Integer> set = list.get(x);
            if(set==null){
                set=new ArrayList<Integer>();
                list.set(x,set);
            }
            set.add(i);
        }
        int res=0;
        for(String word : words){
            boolean matched = true;
            int start = -1;
            for(char c : word.toCharArray()){
                ArrayList<Integer> set = list.get(c-'a');
                if(set==null){
                    matched=false;
                    break;
                }
                boolean found=false;
                for(int x : set){
                    if(x>start){
                        start = x;
                        found=true;
                        break;
                    }
                }
                if(!found){
                    matched=false;
                    break;
                }
            }
            if(matched)
                res++;
        }
        return res;
    }

//#795. Number of Subarrays with Bounded Maximum
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int n = A.length;
        int[] nums = new int[n+1];
        
        if(L<=A[0] && A[0]<=R)
        	nums[0]=1;
        
        for(int i=1;i<n;i++){
            if(A[i]>R)
                nums[i]=0;
            else if(A[i]<L)
                nums[i]=nums[i-1];
            else{
                int j=i;
                while(--j>=0 && A[j]<=R);
                nums[i]=i-j;
            }
        }
        int res=0;
        for(int i=0;i<n;i++){
            res+=nums[i];
        }
        return res;
    }
    
//#797 All Paths From Source to Target   
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        
        List<Integer> path = new ArrayList<Integer>();
        path.add(0);
        helper797(res,graph,0,path);

        return res;
    }
    
    void helper797(List<List<Integer>> res, int[][] g, int s, List<Integer> path){
        if(s==g.length-1){
            res.add(path);
            return;
        }

        for(int node : g[s]){
            List<Integer> newpath = new ArrayList<Integer>(path);
            newpath.add(node);
            helper797(res,g,node,newpath);
        }
    }

//#799. Champagne Tower    
    public double champagneTower(int poured, int row, int glass) {
        double[][] d = new double[101][101];
        d[0][0]=poured;
        for(int i=0;i<=row;i++){
            for(int j=0;j<=i;j++){
                if(d[i][j]>1){
                    double delta = (d[i][j]-1)/2;
                    d[i+1][j] += delta;
                    d[i+1][j+1] += delta;
                    d[i][j] = 1;
                }
            }
        }
        return d[row][glass];
    }

    public double champagneTowerSpaceSaver(int poured, int row, int glass) {
        double[] d = new double[101];
        d[0]=poured;
        for(int i=0;i<row;i++){
            for(int j=i;j>=0;j--){
                if(d[j]>1){
                    double delta = (d[j]-1)/2;
                    d[j+1] += delta;
                    d[j] = delta;
                }else
                    d[j]=0;
            }
        }
        return d[glass]>1?1:d[glass];
    }

//# 801. Minimum Swaps To Make Sequences Increasing
    public int minSwap(int[] A, int[] B) {
        int n=A.length;
        int[] swap = new int[n];
        int[] noSwap = new int[n];
        swap[0]=1;
        noSwap[0]=0;
        for(int i=1;i<n;i++){
            noSwap[i]=Math.max(noSwap[i-1],swap[i-1]);
            swap[i]=Math.max(noSwap[i-1]+1,swap[i-1]+1);
            if(A[i-1]<A[i] && B[i-1]<B[i]){
                noSwap[i]=Math.min(noSwap[i],noSwap[i-1]);
                swap[i]=Math.min(swap[i],swap[i-1]+1);
            }
            if(A[i-1]<B[i] && B[i-1]<A[i]){
                noSwap[i]=Math.min(noSwap[i],swap[i-1]);
                swap[i]=Math.min(swap[i],noSwap[i-1]+1);
            }
        }
        return Math.min(noSwap[n-1],swap[n-1]);
    }

    
//#802. Find Eventual Safe States    
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n= graph.length;
        int[] nodes = new int[n];   //-1: cycle, 0: unvisited, 1:visited
        
        List<Integer> res = new ArrayList<Integer>();
        for(int i=0;i<n;i++){
            if(nodes[i]==0)
                dfs802(graph,i, nodes);
        }

        for(int i=0;i<n;i++){
            if(nodes[i]>1)
                res.add(i);
        }
        return res;
    }
    boolean dfs802(int[][] graph, int i, int[] nodes){
        if(nodes[i]==1)
            return false;
        else if(nodes[i]==0){
            nodes[i]=1;
            for(int x : graph[i]){
                if(!dfs802(graph,x,nodes))
                    return false;
            }
        }
        nodes[i]=2;
        return true;
    }   

//#813. Largest Sum of Averages
    public double largestSumOfAverages(int[] A, int K) {
        int n = A.length;
        double[] sums = new double[n+1];
        
        for(int i=0;i<n;i++)
            sums[i+1] = sums[i]+A[i];
        double[][] dp = new double[K][n];
        for(int i=0;i<n;i++){
            dp[0][i]=sums[i+1]/(i+1);
        }
        
        for(int k=1;k<K;k++){
            for(int i=k-1;i<n;i++){
                for(int j=k-1;j<i;j++){
                    dp[k][i]=Math.max(dp[k][i], dp[k-1][j]+(sums[i+1]-sums[j+1])/(i-j));
                }
            }
        }
        return dp[K-1][n-1];
    }

//#814. Binary Tree Pruning    
    public TreeNode pruneTree(TreeNode root) {
        if(root==null) return root;
        if(!helper814(root))
            return null;
        return root;
    }
    
    boolean helper814(TreeNode t){
        if(t==null)
            return false;
        boolean l = helper814(t.left);
        boolean r = helper814(t.right);
        if(!l)
            t.left=null;
        if(!r)
            t.right=null;
        if(l||r||t.val>0)
            return true;
        return false;
    }    

//#816. Ambiguous Coordinates
    public List<String> ambiguousCoordinates(String S) {
        ArrayList<String> res = new ArrayList<String>();
        
        String s = S.substring(1,S.length()-1);
        StringBuilder sbd = new StringBuilder();
    	for(int i=1;i<s.length();i++){
    		String s1 = s.substring(0,i);
    		String s2 = s.substring(i);
    		String[] set1 = helper816(s1);
    		String[] set2 = helper816(s2);
    		for(String a : set1){
    			for(String b : set2){
    				if(a!=null && b!=null){
    					sbd.setLength(0);
    					sbd.append("(").append(a).append(",").append(b).append(")");
    					res.add(sbd.toString());
    				}
    			}
    		}
    	}
        
        return res;
    }


    String[] helper816(String s){
    	String[] res = new String[12];
    	int x=0;
    	if(isValid816(s)) res[x++]=s;
        for(int i=1;i<s.length();i++){
            String temp = s.substring(0,i) + (".") + s.substring(i);
            if(isValid816(temp))
                res[x++]=temp;
        }
        return res;
    }
    boolean isValid816(String s){
        if(s.length()>1){
            int dot = s.indexOf(".");
            if(s.charAt(0)=='0' && dot!=1) return false;
            if(dot>=0 && s.charAt(s.length()-1)=='0') return false;
        }
        return true;
    }
    boolean isValid816(String[] arr){
        for(String s : arr){
            if(!isValid816(s))
                return false;
        }
        return true;
    }

//820. Short Encoding of Words
    public int minimumLengthEncoding(String[] words) {
        //String[] data = Arrays.copyOf(words,words.length());
        Arrays.sort(words,(a,b)->b.length()-a.length());
        StringBuilder sbd = new StringBuilder();
        for(String s : words){
            if(sbd.indexOf(s+"#")<0)
                sbd.append(s).append("#");
            else
            	System.out.println(s);
        }
        return sbd.length();
    }
//#823. Binary Trees With Factors
    public static final int Modulo = 1000000007;
    public int numFactoredBinaryTrees(int[] A) {
        int n = A.length;
        Arrays.sort(A);
        long count = 0;
        HashMap<Integer,Long> map = new HashMap<>();
        for(int i=0;i<n;i++)
            count += helper823(A, i,  map);
        return (int)(count % Modulo);
    }
    
    long helper823(int[] A, int x, HashMap<Integer,Long> map){
        long count=1;

        for(int i=0;i<x;i++){
            if(A[x]%A[i]==0){
                int d = A[x] / A[i];
                if(map.containsKey(d)){
                    long m = map.get(d);
                    long l = map.get(A[i]);
                    count += m*l;
                }
            }
        }
        map.put(A[x],count);
        return count;
    }

//#826. Most Profit Assigning Work    
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i=0;i<profit.length;i++){
            int k=difficulty[i];
            int v=profit[i];
            Map.Entry<Integer,Integer> entry = map.floorEntry(k);
            if(entry!=null && entry.getValue() >= v)
                continue;
            while(true){
                entry = map.ceilingEntry(k);
                if(entry!=null && entry.getValue() <= v)
                    map.remove(entry.getKey());
                else
                    break;
            }
            map.put(k,v);
        }
        int result = 0;
        for(int i=0;i<worker.length;i++){
            Map.Entry<Integer,Integer> entry = map.floorEntry(worker[i]);
            if(entry!=null)
                result+=entry.getValue();
        }
        return result;
    }

//#833. Find And Replace in String
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        int n=S.length();
        int[] x= new int[n];
        Arrays.fill(x,-1);
        for(int i=0;i<indexes.length;i++){
            x[indexes[i]]=i;
        }
        char[] cs = S.toCharArray();
        StringBuilder sbd = new StringBuilder();
        for(int i=0;i<n;){
            int k=x[i];
            boolean matched = false;
            if(k>=0){
                matched = true;
                for(int j=0;j<sources[k].length();j++){
                    if(cs[i+j]!=sources[k].charAt(j)){
                        matched=false;
                        break;
                    }
                }
                if(matched){
                    sbd.append(targets[k]);
                    i+=sources[k].length();
                }
            }
            if(!matched)
                sbd.append(cs[i++]);
        }
        return sbd.toString();
    }

//#837. New 21 Game
// 解题思路：这个题目有点像爬楼梯问题，只不过楼梯问题要求的计算多少种爬的方式，但是本题是计算概率。因为点数超过或者等于K后就不允许再增加新的点数了，
//   因此我们可以确定最终Alice拥有的点数的区间是[K,K-1+W]，下限等于K很好理解，Alice最后一次抽取点数前可能拥有的点数最大值是K-1，最后一次抽取的点数最大值是W,因此上限就是K-1+W。
//和爬楼梯类似，恰好获得点数n的概率dp[n] = sum(dp[n-w]/w + dp[n-w+1]/w + .... dp[n-1]/w)。因为获取任意一个点数的概率都是1/W，所以上面的公式中每个dp都要除以W。
//但是题目约定了一个K值，在n > k + 1的情况下，dp[n]是无法通过dp[n-1]得到，需要修正公式： dp[n] = sum(dp[n-w]/w + dp[n-w+1]/w + .... dp[K-1]/w)。
// 最后，点数小于或者等于N的概率就是 sum(dp[K:N + 1])。
// dp(n) - dp(n-1) = 1/w * (dp(n-1)-dp(n-1-w))		-> dp(n) = dp(n-1) + 1/w * * (dp(n-1)-dp(n-1-w))

    public double new21Game0(int N, int K, int W) {
    	if(N==0|| K==0 || N>=K+W)return 1;

        double[] dp = new double[K+W];
        //dp[0]=1.0;
        Arrays.fill(dp,1,W+1,1.0/W);
        for(int i=1;i<=K;i++){
        	for(int j=Math.max(0, i-W);j<i;j++){
        			dp[i]+=dp[j]/W;
        	}
        }
        for(int i=K+1;i<=N;i++){
            for(int j=Math.max(1,i-W);j<K;j++){
                    dp[i] += dp[j]/W;
            }
        }
        double res = 0;
        for(int i=K;i<=N;i++){
            res += dp[i];
        }
        return res;
    }
//
    public double new21Game2(int N, int K, int W) {
        if(N==0|| K==0 || N>=K+W)return 1;

        double[] dp = new double[K+W];
        Arrays.fill(dp,1,W+1,1.0/W);
        for(int i=2;i<=N;i++){
            int m = Math.min(i,K);
        	for(int j=Math.max(1, i-W);j<m;j++){
        			dp[i]+=dp[j]/W;
        	}
        }
        double res = 0;
        for(int i=K;i<=N;i++){
            res += dp[i];
        }
        return res;
    }
    
    public double new21Game3(int N, int K, int W) {
        if(N==0|| K==0 || N>=K+W)return 1;
        double[] dp = new double[K+W];
        //Arrays.fill(dp,1,W+1,1.0/W);
        dp[0]=1.0;
        dp[1]=0.1;
        for(int i=2;i<=K;i++){
        	double last =0.0;
        	if(i>=W+1) last = dp[i-1-W];
        	dp[i]=dp[i-1]+(dp[i-1]-last)/W;
        	//	dp[i]=dp[i-1]+(dp[i-1]-dp[i-1-W])/W;
        }
        for(int i=K+1;i<=N;i++){
            for(int j=Math.max(1,i-W);j<K;j++){
                    dp[i] += dp[j]/W;
            }
        }
        double res = 0;
        for(int i=K;i<=N;i++){
            res += dp[i];
        }
        return res;
    }
    
    public double new21Game(int N, int K, int W) {
        if(N==0|| K==0 || N>=K+W)return 1;
        double[] dp = new double[K+W];

        dp[0]=1.0;
        double last = 0.0;
        for(int i=1;i<=K;i++){
        	last += dp[i-1];
        	if(i>=W+1) last = last - dp[i-1-W];
        	dp[i]=last/W;
        	//	dp[i]=dp[i-1]+(dp[i-1]-dp[i-1-W])/W;
        }
        for(int i=K+1;i<=N;i++){
            for(int j=Math.max(0,i-W);j<K;j++){
            	dp[i] += dp[j]/W;
            }
        }
        double res = 0;
        for(int i=K;i<=N;i++){
            res += dp[i];
        }
        return res;
    }  
//

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
    
    
}
