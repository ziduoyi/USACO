package com.lc;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;




public class MediumSetI {
	static public void main(String[] args) throws Exception{
		MediumSetI set1 = new MediumSetI();
		
		//int[][] g={{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};
		//System.out.println(set1.getRedundentConnection(g)==null?false:true);
//		int[][] g={{1,2}, {2,3}, {4,5}, {5,6},{6,7}, {7,4}, {1,5}};
//		System.out.println(set1.findRedundantDirectedConnection(g));
		
//		int[] nums = {8,9,10,220,6,4,5};
//		set1.canPartition(nums);
		
		//set1.reconstructDigits("owofviefuroztneoerowozfviefurotneoer");
		
//		set1.characterReplacement("AABABBA", 1);
//		String[] bank={"AATTCCGG","AACCTGGG","AACCCCGG","AACCTACC"};
//		set1.minMutation("AACCTTGG","AATTCCGG",bank);
		
//		String tenary = "F?1:T?4:5";//"T?T?F:5:3";//"F?T:F?T?1:2:F?3:4";
//        set1.ternaryExpressParse(tenary);
		
//		int[] org={1,2,3};
//		List<List<Integer>> seqs= new ArrayList<List<Integer>>();//Arrays.asList({{1,2},{1,3},{2,3}}));
//		seqs.add(Arrays.asList(1,2));
//		seqs.add(Arrays.asList(1,3));
//		seqs.add(Arrays.asList(2,3));
//		System.out.println(set1.sequenceRecontruct(org, seqs));
		
//        ListNode l1 = set1.contructLinkedList(new int[]{7, 2, 4, 3});
//        ListNode l2 = set1.contructLinkedList(new int[]{5, 6, 4});
//        set1.addTwoNumbers(l1, l2);
		
//		TreeNode root=set1.buildBST(new int[]{5,3,2,1,7,8,9});
//		String data = set1.serialize3(root);
//		System.out.println(data);
//		root = set1.deserialize3(data);
//		System.out.println(set1.serialize3(root));
		
//		set1.canIWin(10, 40);
		
//		System.out.println(set1.findSubstringInWraproundString2("abcxbcd"));
//		System.out.println(set1.findSubstringInWraproundString("abcxbcd"));
		
//		int[] nums=new int[]{1,1,2,2,2};
//		set1.makeSquare(nums);
		
//		Scanner scanner = new Scanner(new File("490_maze.txt"));
//		int m = scanner.nextInt();
//		int n = scanner.nextInt();
//		int[][] maze=new int[m][n];
//		for(int[] row : maze){
//			for(int i=0;i<n;i++){
//				row[i]=scanner.nextInt();
//			}
//		}
//		set1.checkMaze(maze, scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
		
		int[] nums={4, 6, 7, 7};
		set1.findSubsequences(nums);
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

//#684	Redundant Connection	
	int[][] getRedundentConnection(int[][] g){
		if(g==null)
			return null;
		ArrayList<HashSet<Integer>> setlist = new ArrayList<HashSet<Integer>>();
		int rowSize = g.length;
		
		int[][] redundent = new int[1][2];
		boolean found = false;
		
		for(int i=0;i<rowSize;i++){
			int s = g[i][0];
			int t = g[i][1];
			HashSet<Integer> set1 = null;
			HashSet<Integer> set2 = null;

			for(HashSet<Integer> hs : setlist){
				if(set1==null && hs.contains(s))
					set1 = hs;
				if(set2==null && hs.contains(t))
					set2 = hs;
			}
			if(set1!=null && set1==set2){
				if(found)
					return null;
				else{
					found = true;
					redundent[0][0]=s;
					redundent[0][1]=t;
				}
			}else
				if(set1!=null){
					if(set2==null){
						set1.add(t);
					}else{
						set1.addAll(set2);
						setlist.remove(set2);
					}
				}else
					if(set2==null){
						HashSet<Integer> hs = new HashSet<Integer>();
						setlist.add(hs);
						hs.add(s);
						hs.add(t);
					}else
						set2.add(s);
		}
		System.out.println("["+redundent[0][0]+","+redundent[0][1]+"]");
		if(setlist.size()==1)
			return redundent;
		else return null;
	}
	
	//solution 2
    public int[] findRedundantConnection2(int[][] edges) {
        //HashSet<Integer> set = new HashSet<>();
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for(int[] e : edges){
            HashSet<Integer> set0 = map.get(e[0]);
            HashSet<Integer> set1 = map.get(e[1]);
            if(set0 ==null && set1==null){
                HashSet<Integer> set = new HashSet<>();
                set.add(e[0]);
                set.add(e[1]);
                map.put(e[0],set);
                map.put(e[1],set);
            }else if(set0 ==set1)
                return e;
            else if(set0==null){
                set1.add(e[0]);
                map.put(e[0],set1);
            }else if(set1==null){
                set0.add(e[1]);
                map.put(e[1],set0);
            }else{
                set0.addAll(set1);
                for(int i : set1){
                    map.put(i,set0);
                }
            }
        }
        return null;
    }	
	
    //union find
    public int[] findRedundantConnection3(int[][] edges) {
        int[] connected = new int[edges.length+1];
        for(int i=1;i<connected.length;i++)
            connected[i]=i;
        
        for(int[] e : edges){
            int r0 = findRoot(connected, e[0]);
            int r1 = findRoot(connected, e[1]);
            if(r0==r1)
                return e;
            connected[r1]=r0;
        }
        return null;
    }
    
    int findRoot(int[] connected, int n){
        if(connected[n]==n) return n;
        return findRoot(connected,connected[n]);
    }
	//#685	Redundant Connection ||
	
	int[][] getRedundentConnectionII(int[][] g){
		if(g==null)
			return null;
		int[][] results = new int[1][2];
		//check the node with two parents, remove the one lead

		return results;
	}
	
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] can1 = {-1, -1};
        int[] can2 = {-1, -1};
        int[] parent = new int[edges.length + 1];
        for (int i = 0; i < edges.length; i++) {
            if (parent[edges[i][1]] == 0) {
                parent[edges[i][1]] = edges[i][0];
            } else {
                can2 = new int[] {edges[i][0], edges[i][1]};
                can1 = new int[] {parent[edges[i][1]], edges[i][1]};
                edges[i][1] = 0;
            }
        }
        for (int i = 0; i < edges.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][1] == 0) {
                continue;
            }
            int child = edges[i][1], father = edges[i][0];
            if (root(parent, father) == child) {	//circle checking
                if (can1[0] == -1) {
                    return edges[i];//circle found and no node with two parents, return current edge
                }
                return can1;	//circle found with can2 removed, it means can1 is the bad one
            }
            parent[child] = father;
        }
        return can2;
    }
    
    int root(int[] parent, int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }
    
    
    
    
//#402	Remove K Digits
    public String removeKdigits(String num, int k) {
        if(num==null || num.length()==0 || num.length()<=k) return null;
        StringBuilder sbd = new StringBuilder(num);
        int i=0;
        while(i<sbd.length()-1 && k>0){
            int j=i+1;
            if(sbd.charAt(i)<sbd.charAt(j)){
                k--;
                sbd.deleteCharAt(j);
            }else
                i++;
        }
        if(k>0) 
            sbd.setLength(sbd.length()-k);
        while(sbd.charAt(0)=='0'){
        	sbd.deleteCharAt(0);
        }
        return sbd.toString();
    }
//#406	Queue Reconstruction by Height
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>(){
        	public int compare(int[] p1,int[] p2){
        	     //  return -1 if l should be before r	
        	     //  return 1 if r should be before l   
        	     //  return 0 otherwise
//        		if(p1[0]>p2[0]) return -1;
//        		if(p1[0]<p2[0]) return 1;
//        		if(p1[0]==p2[0])
//        			if(p1[1]<p2[1]) return -1;
//        			else return 1;
        		return p1[0]==p2[0]? Integer.compare(p1[1], p2[1]):Integer.compare(p2[0], p1[0]);
        	}
        });
        
        LinkedList<int[]> list = new LinkedList<>();
        for(int[] p : people){
        	list.add(p[1],p);
        }
        return list.toArray(new int[list.size()][2]);
    }
    public int[][] reconstructQueue2(int[][] people) {
    	//Collections.sort(people, (a, b) -> a.name.compareToIgnoreCase(b.name));
        Arrays.sort(people, (p1,p2) -> p1[0]==p2[0]? Integer.compare(p1[1], p2[1]):Integer.compare(p2[0], p1[0]));
        Arrays.sort(people, (p1,p2) -> {if(p1[0]>p2[0]) return -1;else if(p1[0]<p2[0]) return 1;else if(p1[0]==p2[0] && p1[1]<p2[1])
        	return -1;else return 1;});
        
        LinkedList<int[]> list = new LinkedList<>();
        for(int[] p : people){
        	list.add(p[1],p);
        }
        return list.toArray(new int[list.size()][2]);
    }
    
//***** # cow bronzi problems
    int[] cowWeights = {23,112,89,9011,45};
    int maxCow = 0;
    boolean checkCarry(int a, int b){
    	while(a>0 && b>0){
    		if(a%10+b%10>9) return true;
    		a =a/10;
    		b = b/10;
    	}
    	return false;
    }
    public int checkMaxCow(int[] cow){
    	int i=0;
    	int N = 1<<cow.length;
    	int[] flag = new int[cow.length];
    	while(i<N){
    		int sum = 0;
    		for(int j=0;j<cow.length;j++){
    			flag[j] = (i>>j)&1;
    			sum += flag[j];
    		}
    		i++;
    		if(sum<2) continue;
    		int temp=0;
    		boolean carried =false;
    		for(int j=0;j<cow.length;j++){
    			if(flag[j]>0 && !checkCarry(temp,cow[j]))
    				temp += cow[j];
    			else{
    				carried=true;
    				break;
    			}
    		}
    		if(!carried)
    			maxCow = sum;
    	}
    	return maxCow;
    }
//*****#416	Partition Equal Subset Sum
//recursive way:
//suppose the length of nums is N, then each num[i] has a choice to be inlcuded in set or not, marked as 0 or 1, then there 2^N combinations. 
//we can calculate according to its binary representation: 110010010..., or do it recursively as below...
    boolean foundPartition = false;
    public boolean canPartition(int[] nums) {
        if(nums==null || nums.length<2) return false;
        long sum=0;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];
        }
        if(sum %2 !=0) return false;
        searchSum(sum/2,0,nums,0);
        System.out.println("partitiion found : " + this.foundPartition);
        return this.foundPartition;
    }
    
    void searchSum(long sum, long cur, int[] nums, int x){
        if(cur==sum) {
            this.foundPartition =true;
            return;
        }
        if(x>=nums.length || cur>sum)
            return;
        searchSum(sum,cur+nums[x],nums,x+1);
        if(!this.foundPartition)
            searchSum(sum,cur,nums,x+1);
    }
// DP option: 100 elements and each is no more than 100, the max sum 100 * 100, half is 5000
// set a big array, boolean dp[5000],dp[i] means if is a sum of the sub array     
    public boolean canPartitionDP(int[] nums) {
        if(nums==null || nums.length<2) return false;
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];
        }
        if((sum&1) ==0) return false;
        int target = sum/2;
        boolean dp[] = new boolean[target];
        dp[0]=true;
        for(int num:nums){
        	for(int i=0;i<target;i++){
        		if(dp[i])
        			dp[i+num]=true;
        	}
        }
        return dp[target];
    }
//423	Reconstruct Original Digits from English
    HashMap<Character, Integer> countLetters(String str){
    	if(str==null) return null;
    	HashMap<Character, Integer> hmap = new HashMap<>();
    	for(int i=0;i<str.length();i++){
    		char c = str.charAt(i);
    		if(hmap.containsKey(c))
    			hmap.put(c,hmap.get(c)+1);
    		else
    			hmap.put(c,1);
    	}
    	return hmap;
    }
    void removeMapData(HashMap<Character, Integer> hmap, HashMap<Character, Integer>map,int num){
    	if(hmap==null || map==null) return;
    	for(Character c :map.keySet()){
    		if(hmap.containsKey(c)){
    			if(hmap.get(c)>map.get(c)*num)
    				hmap.put(c,hmap.get(c)-map.get(c)*num);
    			else
    				hmap.remove(c);
    		}
    	}
    }
    public String reconstructDigits(String s){
    	String[] digits={"zeor","one","two","three","four","five","six","sever","eight","nine"};
    	StringBuilder sbd = new StringBuilder();
    	for(int i=0;i<digits.length;i++){
    		sbd.append(digits[i]);
    	}
    	HashMap<Character, Integer> hmap = countLetters(sbd.toString());
    	HashMap<Character, Integer> letterdigitmap = new HashMap<Character, Integer>();
    	ArrayList<Map<Character,HashMap<Character,Integer>>> plist = new ArrayList<Map<Character,HashMap<Character,Integer>>>();
    	while(hmap.containsValue(1)){
    		Character ch = null;
    		for(Character c : hmap.keySet()){
    			if(hmap.get(c)==1){
    				ch = c;
    				break;
    			}
    		}
    		int i=0;
    		for(;i<digits.length;i++){
    			if(digits[i].indexOf(ch)>=0)
    				break;
    		}
    		HashMap<Character, Integer> map = countLetters(digits[i]);
    		Map<Character,HashMap<Character,Integer>> wordmap = new TreeMap<Character,HashMap<Character,Integer>>();
    		wordmap.put(ch, map);
    		plist.add(wordmap);
    		digits[i]="";
    		letterdigitmap.put(ch, i);
    		removeMapData(hmap,map,1);
    	}
    	
    	sbd.setLength(0);
    	if(hmap.size()>0) 
    		System.out.println("error, number init error");

    	hmap=countLetters(s);
    	for(int i=0;i<plist.size();i++){
    		if(hmap.isEmpty()) break;
    		Map<Character,HashMap<Character,Integer>> wmap = plist.get(i);
    		Character key = wmap.keySet().iterator().next();
    		if(hmap.containsKey(key)){
    			int count = hmap.get(key);
    			removeMapData(hmap,wmap.get(key),count);
    			for(int j=0;j<count;j++)
    				sbd.append(letterdigitmap.get(key));
    		}
    	}
    	char[] res = sbd.toString().toCharArray();
    	Arrays.sort(res);
    	System.out.println(res);
    	return new String(res);
    }
//#424	Longest Repeating Character Replacement
//	sliding window    
    public int characterReplacement(String str, int k) {
    	if(str==null) return 0;
        if(str.length()<=k+1)
            return str.length();
        
        HashMap<Character,Integer> hmap = new HashMap<>();
        int res = 0;
        int maxcount = 0; 
        int start=0;
        
        for(int i=0;i<str.length();i++) {
            char c = str.charAt(i);
        	Integer v= hmap.getOrDefault(c,0)+1;
            hmap.put(c,v);
            maxcount=Math.max(maxcount,v);
   
           while(i-start+1-maxcount>k){
               char cs = str.charAt(start++);
               if(hmap.get(cs)==1)
                   hmap.remove(cs);
               else
                   hmap.put(cs,hmap.get(cs)-1);
               //update maxcount of a character
               maxcount = 0;
               for(Integer x:hmap.values()){
               	if(x>maxcount) maxcount=x;
               }
           }
            res=Math.max(res, i-start+1);
        }

    	System.out.println(res);
    	return res;
    }
    
    public int longestRepeatingChars(String str, int k){
    	if(str==null) return 0;
    	int nums = 1;
    	int lnum=1, rnum = 1;
    	int rep=k;
    	char c='0';
    	
    	for(int i=1;i<str.length();i++) {
    		if(str.charAt(i)==str.charAt(i-1) || str.charAt(i)==c){
    			lnum++;
    			if(lnum>nums) nums = lnum;
    		}else if(rep==0){
    			rep=k;
    			lnum=1;
    			c='0';
    		}else{
    			c=str.charAt(i-1);
    			rep--;
    			lnum++;
    			if(lnum>nums) nums = lnum;
    		}
    	}
    	for(int i=str.length()-2;i>=0;i--) {
    		if(str.charAt(i)==str.charAt(i+1)){
    			rnum++;
    			if(rnum>nums) nums = rnum;
    		}else if(rep>0){
    			rep=k;
    			rnum=1;
    			c='0';
    		}else{
    			c=str.charAt(i+1);
    			rep--;
    			rnum++;
    			if(rnum>nums) nums = rnum;
    		}
    	}
    	System.out.println(nums);
    	return nums;
    }
    
//#433	Minimum Genetic Mutation
    public int minMutation(String start, String end, String[] bank) {
        LinkedList<Integer> list = new LinkedList<>();
        HashSet<Integer> passedSet = new HashSet<>();

        if(bank==null || bank.length==0) return -1;
        
        for(int i=0;i<bank.length;i++){
            if(getDistance(start,bank[i])==1){
                if(end.equals(bank[i]))
                    return 1;
                list.add(i);
                passedSet.add(i);
            }
        }
        list.add(-1);
        int level = 1;
        while(!list.isEmpty()){
            int x=list.removeFirst();
            if(x<0){
            	if(!list.isEmpty()){
	                level++;
	                list.add(-1);
            	}
                continue;
            }
            for(int i=0;i<bank.length;i++){
                if(passedSet.contains(i))
                    continue;
                if(getDistance(bank[x],bank[i])==1){
                    if(end.equals(bank[i]))
                        return level+1;
                    list.add(i);
                    passedSet.add(i);
                }
            }
        }
        return -1;
    }

    int getDistance(String s1, String s2){
        if(s1==null || s2==null || s1.length()!=8 || s2.length()!=8)
            return -1;
        int count=0;
        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i)!=s2.charAt(i))
                count++;
        }
        return count;
    }
 //#435	Non-overlapping Intervals 
    class Interval{
    	int start=0,end=0;
    }
    public int eraseOverlapIntervals(Interval[] intervals) {
        int res = 0;
        
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
                return Integer.compare(a.start,b.start);
            }
        });
        //Arrays.sort(intervals,(a,b)->Integer.compare(a.start,b.start));

        int i=0,j=0;
        while(++j<intervals.length){
            if(intervals[j].start < intervals[i].end){
                res++;  //overlapped
                if(intervals[i].end <= intervals[j].end){
                    ;//j++;//remove j
                }else{
                    i=j;//remmove i
                }
            }else
                i=j;
        }
        System.out.println(res);
        return res;
    }
//#436	Find Right Interval 
    public int[] findRightInterval(Interval[] intervals) {
        int[] res = new int[intervals.length];
        
        for(int i=0;i<intervals.length;i++){
            int min = -1;
            for(int j=0;j<intervals.length;j++){
                if(i==j) continue;
                if(intervals[i].end<=intervals[j].start){
                    if(min==-1)
                        min=j;
                    else if(intervals[min].start>intervals[j].start)
                        min=j;
                }
            }
            res[i]=min;
        }
        return res;
    }
//#437	Path Sum III
    //two level recursive, 
    //level 1: tree traversal or loop every node, for each do level 2
    //level 2: make the node as starting, traverse all its sub nodes to calculate the path sum
    
    int pathNum = 0;
    int getPathSum(Node t, int sum){
    	if(t==null) return 0;
    	pathSumHelper(t, 0, sum);
    	getPathSum(t.left,sum);
    	getPathSum(t.right,sum);
    	
    	return pathNum;
    }
    
    void pathSumHelper(Node t, int cur, int sum){
    	if(t==null) return;
    	int newCur = t.getData() + cur;
    	if(newCur==sum){
    		pathNum++;
    	}
    	pathSumHelper(t.left,newCur,sum);
    	pathSumHelper(t.right,newCur,sum);
    }

//#439	Ternary Expression Parser $
    String ternaryExpressParse(String tenary){
    	if(tenary==null) return null;
    	while(tenary.length()>1){
    		System.out.println(tenary);
    		if(tenary.charAt(1)=='?'){
    			int x=1;
    			int num=1;
    			while(num>0){
    				x += 2;
    				if(tenary.charAt(x)=='?')
    					num++;
    				else if(tenary.charAt(x)==':')
    					num--;
    			}
    			if(tenary.charAt(0)=='T')
    				tenary=tenary.substring(2,x);
    			else
    				tenary=tenary.substring(x+1);
    		}
    	}
    	System.out.println(tenary);
    	return tenary;
    }

//#444	Sequence Reconstruction $
    public boolean sequenceRecontruct(int[] org, List<List<Integer>> loList){
    	if(org==null || loList==null) return false;

    	int[] rank = new int[org.length+1];
    	int[] flag = new int[org.length+1];

    	for(int i=0;i<org.length;i++){
    		rank[org[i]]=i;
    	}
    	
    	for(List<Integer>list : loList){
    		if(list==null || list.size()<2) continue;
    		int pre=0,cur=list.get(0);
    		if(cur<1 || cur>org.length)return false;
    		for(int i=1;i<list.size();i++){
    			pre=cur;
    			cur=list.get(i);
    			if(cur>org.length)return false;
    			if(rank[pre]>=rank[cur])
    				return false;
    			if(flag[cur]==0 && rank[pre]+1==rank[cur])
    				flag[cur]=1;
    		}
    	}
    	for(int i=2;i<=org.length;i++)
    		if(flag[i]==0)
    			return false;
    	return true;
    }
//#445	Add Two Numbers II
    
     // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
     

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1==null) return l2;
        LinkedList<ListNode> list1 = new LinkedList<ListNode>();
        LinkedList<ListNode> list2 = new LinkedList<ListNode>();
        
        ListNode t = l1;
        while(t!=null){
            list1.addFirst(t);
            t=t.next;
        }
        t = l2;
        while(t!=null){
            list2.addFirst(t);
            t=t.next;
        }
        int carry = 0;
        t=null;
        while(!list1.isEmpty() || !list2.isEmpty() || carry>0){
            ListNode node = new ListNode(0);
            l1=list1.isEmpty()?node:list1.removeFirst();
            l2=list2.isEmpty()?node:list2.removeFirst();
            int i = l1.val+l2.val+carry;
            carry=i>9?1:0;
            node.val=i%10;
            if(t!=null)
                node.next=t;
            t=node;
        }
        return t;
    }
//#449  449	Serialize and Deserialize BST 
    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) { val = x; }
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
    public String serialize(TreeNode root) {
        if(root==null) return null;
        StringBuilder sbd = new StringBuilder();
        serializeBst(root,sbd);
        return sbd.toString();
    }
    void serializeBst(TreeNode t, StringBuilder sbd){
        if(t==null) return;
        sbd.append(t.val).append(" ");
        serializeBst(t.left,sbd);
        serializeBst(t.right,sbd);
    }
    

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data==null || data.length()<1) return null;
        String[] temp = data.split(" ");
        System.out.println(data);
        int[] vals=new int[temp.length];
        for(int i=0;i<temp.length;i++){
            vals[i]=Integer.parseInt(temp[i]);
        }
        return deserializeBst(vals,0, vals.length);
    }
    TreeNode deserializeBst(int[] vals, int start, int end){
        if(start<end){
            TreeNode t =new TreeNode(vals[start]);
            int r=0;
            for(int i=start;i<end;i++){
                if(t.val<vals[i]){
                    r=i;
                    break;
                }
            }
            if(r>0){
                t.left=deserializeBst(vals,start+1,r);
                t.right=deserializeBst(vals,r,end);
            }else
                t.left=deserializeBst(vals,start+1,end);

            return t;
        }else
            return null;
    }
    int cur_pos=1;
    TreeNode deserializeBst2(TreeNode root, int[] vals, int maxvalue){
        if(cur_pos<vals.length && vals[cur_pos]<=maxvalue){
            TreeNode t =new TreeNode(vals[cur_pos]);
            if(root.val>=vals[cur_pos]){
                root.left=t;
                //deserializeBst2(t,vals,start+1);
            }else{
            	root.right=t;
            }
            ++cur_pos;
            deserializeBst2(t,vals,t.val);
            return t;
        }else
            return null;
    }
    
    public String serialize3(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        if (root == null) {
            return stringBuilder.toString();
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr == null) {
                    stringBuilder.append("# ");
                } else {
                    stringBuilder.append(curr.val + " ");
                    queue.offer(curr.left);
                    queue.offer(curr.right);
                }
            }
        }
        return stringBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize3(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] nodes = data.split(" ");
        TreeNode root = new TreeNode(Integer.valueOf(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for (int i = 1; i < nodes.length; i++) {
            TreeNode curr = queue.poll();
            if (!nodes[i].equals("#")) {
                curr.left = new TreeNode(Integer.valueOf(nodes[i]));
                queue.offer(curr.left);
            }
            if (!nodes[++i].equals("#")) {
                curr.right = new TreeNode(Integer.valueOf(nodes[i]));
                queue.offer(curr.right);
            }
        }
        return root;
    }
    
 //#450
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode t = root;
        TreeNode p=null;
        while(t!=null){
            if(t.val==key)
                break;
            p=t;
            if(key<t.val)
                t=t.left;
            else
                t=t.right;
        }
        if(t==null) return root;
        //if(p==null) return null;
        
        //find right node of left children of t
        TreeNode rl = t.left;
        TreeNode rlp = t;
        while(rl!=null && rl.right!=null){
            rlp=rl;
            rl=rl.right;
        }
        if(root==t){
            if(rl==null)  {
                t=root.right;
                root.left=root.right=null;
                return t;
            }else{
                rl.right=root.right;
                rlp.right=rl.left;
                if(root.left!=rl)
                    rl.left=root.left;
                return rl;
            }
        }else{
            if(rl==null){
                if(p.left==t)
                    p.left=t.right;
                else
                    p.right=t.right;
            }else{
                t.val=rl.val;
                if(rlp.left==rl)
                    rlp.left=rl.left;
                else
                    rlp.right=rl.left;
            }
            return root;
        }
    }
    
    public String frequencySort(String s) {
        HashMap<Character,Integer> hmap = new HashMap<>();
        if(s==null || s.length()==0) return s;
        for(char c: s.toCharArray()){
            hmap.put(c, hmap.getOrDefault(c,0)+1);
        }
        StringBuilder sbd = new StringBuilder();
        //Collections.sort(hmap,(a,b)->Integer.compare(b.value(),a.value()));   //--never directly sort for a hash map
        
        //Options 1: --- lambda
        hmap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .forEach(a->{for(int i=0;i<a.getValue();i++) sbd.append(a.getKey());});
        
        //List<Map.Entry<Character, Integer>> list = new ArrayList<>(hmap.entrySet());
        
//        Collections.sort(list,Map.Entry.comparingByValue());						// sort by ascending 
//        Collections.sort(list,(a,b)->Integer.compare(a.getValue(),b.getValue())); //sort by ascending
//		  Collections.sort(list,Map.Entry.comparingByValue(Comparator.reverseOrder()));

//        for(int i=list.size()-1;i>=0;i--){
//            Map.Entry entry = list.get(i);
//            for(int j=0;j<(int)(entry.getValue());j++){
//                sbd.append(entry.getKey());
//            }
//        }

        
        return sbd.toString();                
    }
//#452	Minimum Number of Arrows to Burst Balloons 
    public int findMinArrowShots(int[][] points) {
        int res = 0;
        if(points==null || points.length<1) return 0;
        
        Arrays.sort(points,(a,b)->(a[0]-b[0]==0)?(a[1]-b[1]):(a[0]-b[0]));
        int[] flags = new int[points.length];
        for(int i=0;i<points.length-1;i++){
            if(flags[i]<0) continue;
            for(int j=i+1;j<points.length;j++){
                if(flags[j]<0 || points[i][1] < points[j][0])
                    continue;
                points[i][0]=points[j][0];
                points[i][1]=Math.min(points[i][1],points[j][1]);
                flags[j]=-1;
            }
        }
        for(int i=0;i<points.length;i++)
            if(flags[i]==0) res++;
        return res;
    }
    public int findMinArrowShots2(int[][] points) {
        if(points==null || points.length<1) return 0;
        int count = 1;
        Arrays.sort(points,(a,b)->(a[0]-b[0]==0)?(a[1]-b[1]):(a[0]-b[0]));
        int curEnd = points[0][1];
        for(int i=1;i<points.length;i++){
            if(points[i][0]>curEnd){
                count++;
                curEnd = points[i][1];
            }else
                curEnd = Math.min(curEnd,points[i][1]);
        }

        return count;
    }

//#456	132 Pattern 
    public boolean find132pattern(int[] nums) {
        if(nums==null || nums.length<3) return false;
        int i=0,j=0,k=0;
        int n=nums.length;
        while(i<n-2){
            while(i<n-2 && nums[i]>=nums[i+1])i++;
            j=i+1;
            while(j<n-1 && nums[j]<=nums[j+1])j++;
            k=j+1;
            while(k<n){
                if(nums[i]<nums[k] && nums[j]>nums[k]) return true;
                k++;
            }
            i=j+1;
        }
        return false;  
    }


//#464 can I win
    public boolean canIWin(int maxValue, int total) {
        boolean[] used = new boolean[maxValue];
        HashMap<Integer,Boolean> hmap = new HashMap<>();

        int sum = maxValue * (maxValue+1) /2;
        if(sum<total) return false;
        return helper1(total,hmap,used);
    }
    
    boolean helper1(int desiredTotal, HashMap<Integer,Boolean> hmap, boolean[] used){ //helper1 is for player 1
        if(getMax(used)>=desiredTotal) return true;

        int key = getKey(used);
        if(hmap.containsKey(key))
            return hmap.get(key);
        
        for(int i=0;i<used.length;i++){
            if(!used[i]){
                used[i]=true;
                if(!helper2(desiredTotal-i-1,hmap,used)){
                    hmap.put(key,true);
                    used[i]=false;
                    return true;
                }else{
                    used[i]=false;
                }
            }
        }
        hmap.put(key,false);
        return false;
    }
    
    boolean helper2(int desiredTotal, HashMap<Integer,Boolean> hmap, boolean[] used){ //helper2 is for player 2
        if(getMax(used)>=desiredTotal) return true;

        int key = getKey(used);
        if(hmap.containsKey(key))
            return hmap.get(key);   //if player 1 can win for that case, player 2 can also win for his turn
        
        for(int i=0;i<used.length;i++){
            if(!used[i]){
                used[i]=true;
                if(!helper1(desiredTotal-i-1,hmap,used)){
                    hmap.put(key,true);
                    used[i]=false;
                    return true;
                }else{
                    used[i]=false;
                }
            }
        }
        hmap.put(key,false);
        return false;
    }

    int getMax(boolean[] used){
        for(int i=used.length-1;i>=0;i--)
            if(!used[i]) return (i+1);
        return 0;
    }

    int getKey(boolean[] used){
        int res = 0;
        for(int i=used.length-1;i>=0;i--){
            res = (res<<1) ;
            res += used[i]?0:1;
        }
        return res;
    }

//#467 
    public int findSubstringInWraproundString(String p) {
        if(p==null || p.length()==0)return 0;

        int res=0;
        int[] counts=new int[26];
        int i=0;
        int n=p.length();
        while(i<n){
            int j=i+1;
            while(j<n && isContinuous(p.charAt(j-1),p.charAt(j))){
                j++;
            }

            int x = j-i;
            for(int k=0;k<x;k++){
            int index = p.charAt(i+k)-'a';
            if(counts[index]<x-k) 
                counts[index]=x-k;
            }

            i=j;
        }
        
        for(i=0;i<26;i++){
            res += counts[i];
        }
        return res;
    }
    
    boolean isContinuous(char a, char b){
        if(a+1 == b) return true;
        if(a=='z' && b=='a') return true;
        return false;
    }
    public  int findSubstringInWraproundString2(String p) {
        if (p == null || p.isEmpty()) {
            return 0;
        }
        if (p.length() < 2) {
            return p.length();
        }
        int count = 0;
        int[] dp = new int[26];
        dp[p.charAt(0) - 'a'] = 1;
        int len = 1;
        for (int i = 1; i < p.length(); i++) {
            if (p.charAt(i) - 1 == p.charAt(i - 1) || (p.charAt(i) == 'a' && p.charAt(i - 1) == 'z')) {
                len++;
            } else {
                len = 1;
            }
            dp[p.charAt(i) - 'a'] = Math.max(len, dp[p.charAt(i) - 'a']);	//even the count of each ca
        }

        for (int i : dp) {
            count += i;
        }
        return count;
    }
    
//#468 
    public String validIPAddress(String IP) {
        if(IP==null || IP.length()<1) return "Neither";
        
        if(IP.indexOf(".")>0){
            int i=0,j=0;
            while(i>=0){
                i=IP.indexOf('.',i+1);
                if(i>=0)j++;
            }
            if(j!=3) return "Neither";
            String[] ipv4 = IP.split("\\.");
            //for(String s : ipv4){System.out.println(s);}
            if(ipv4.length!=4) return "Neither";
            for(String part:ipv4){
                try{
                    //System.out.println(part);
                    if(part.length()>1 && part.charAt(0)=='0' || part.charAt(0)=='-')return "Neither";
                    int v = Integer.parseInt(part);
                    if(v<0 || v>255) return "Neither";
                }catch(Exception ex){
                    return "Neither";
                }
            }
            return "IPv4";
        }else{
            int i=0,j=0;
            while(i>=0){
                i=IP.indexOf(":",i+1);
                if(i>=0)j++;
            }
            if(j!=7) return "Neither";
            String[] ipv6 = IP.split(":");
            if(ipv6.length!=8) return "Neither";
            for(String part:ipv6){
                if(part==null || part.length()>4 || part.length()==0) return "Neither";
                for(char c : part.toCharArray()){
                    boolean valid=false;
                    if(c-'0'>=0 && c-'0'<=9){
                        valid = true;
                    } 

                    if(!valid && (c-'a'>=0 && c-'a'<6 || c-'A'>=0 && c-'A'<6)) 
                       valid = true;
                    if(!valid) return "Neither";
                }
            }
            return "IPv6";
        }
    }

//#473 Matchsticks to Square
    int target = 0;
    public boolean makeSquare(int[] nums) {
        if(nums==null || nums.length<4) return false;
        //Arrays.sort(nums,(a,b)->b-a); -- needed for Integer[]
        Arrays.sort(nums);
        
        int sum = 0, n=nums.length;
        int temp;
        for(int i=0;i<n/2;i++){
        	temp= nums[i];
        	nums[i]=nums[n-i-1];
        	nums[n-i-1]=temp;
        }
        
        for(int x: nums)
            sum += x;
            
        if(sum%4!=0 || nums[0]>sum/4) return false;
        target = sum/4;
        
        boolean[] flag=new boolean[nums.length];
        return helper(nums,flag,sum/2,0,0);
    }
    
    boolean helper(int[] nums, boolean[] used, int total, int curTotal, int pos){
        if(pos>=nums.length) return false;
        if(used[pos])
            return helper(nums,used,total,curTotal,pos+1);
        if(curTotal + nums[pos]==total){
            if(total==target) 
                return true;
            else{
                used[pos]=true;
                boolean[] used1 = new boolean[nums.length];
                for(int i=0;i<used.length;i++){
                    used1[i] = !used[i];
                }
                if(helper(nums,used1,target,0,0)){
                    for(int i=0;i<used.length;i++)
                        used1[i] = used[i];
                    used[pos]=false;
                    return helper(nums,used1,target,0,0);
                }else{
                    used[pos]=false;
                    return false;
                }
            }
        }else if(curTotal + nums[pos]<total){
            used[pos]=true;
            if(helper(nums,used,total,curTotal+nums[pos],pos+1))
                return true;
        }
        used[pos]=false;
        return helper(nums,used,total,curTotal,pos+1);
    }

//# 474 Ones and Zeroes
    public int findMaxForm(String[] strs, int m, int n) {
        if(strs==null || strs.length<1 || m<0 || n<0 || m+n<1) 
            return 0;

        int len = strs.length;
        int[][] dp = new int[m+1][n+1];
        int[][] nums = new int[len][2];
        
        int emptys = 0;
        for(int i=0;i<len;i++){
            if(strs[i]==null || strs[i].length()<1) {
                emptys++;
                continue;
            }
            for(char c : strs[i].toCharArray()){
                if (c=='0')
                    nums[i][0]++;
                else
                    nums[i][1]++;
            }
        }

        for(int i=0;i<len;i++){
            int n0 = nums[i][0];
            int n1 = nums[i][1];
            if(n0+n1==0 || n0>m || n1>n) continue;
            for(int x=m-n0;x>=0;x--){
                for(int y=n-n1;y>=0;y--){
                    if(dp[x][y]>0){
                        dp[x+n0][y+n1] = Math.max(dp[x+n0][y+n1],dp[x][y] + 1);
                    }
                }
            }
            if(dp[n0][n1]==0) 
                dp[n0][n1]=1;
        }
        
        int max = 0;
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                max = Math.max(max,dp[i][j]);
            }
        }

        return max+emptys;
        
    }
    
    public int findMaxFormDP2(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int[] count = count(str);
            for (int i = m; i >= count[0]; i--) {
                for (int j = n; j >= count[1]; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - count[0]][j - count[1]] + 1);
                }
            }
        }
        return dp[m][n];
    }

    private int[] count(String str) {
        int[] count = new int[]{};
        for (char c : str.toCharArray()) {
            count[c - '0']++;
        }
        return count;
    }

//#484	Find Permutation  
    public int[] findPermutation(String s){
    	if(s==null || s.length()<1) return null;
    	int[] res=new int[s.length()+1];
    	int pos=0;
    	int begin = 0;
    	int last = s.lastIndexOf('I');
    	while(pos>=0 && pos<last){
    		pos = s.indexOf("I",begin);
    		for(int i=pos;i<=begin;i--){
    			res[i]=begin+pos-i+1;
    		}
    		res[pos]=begin+1;
    	}
    	if(last<0)
    		for(int i=0;i<res.length;i++)
    			res[i]=res.length-i;
    	if(last==0){
    		res[0]=1;
    		for(int i=1;i<res.length;i++)
    			res[i]=res.length+1-i;
    	}
    	
    	
    	return res;
    }
//#486. Predict the Winner    
    int desired1 = 0,desired2=0;
    public boolean PredictTheWinner(int[] nums) {
        int sum = 0;
        for(int i : nums){
            sum+=i;
        }
        desired2 = sum/2 + 1;
        if((sum & 1) == 1){
            desired1=desired2;
        }else
            desired1=desired2-1;
        
        return pwHelper(nums,0,nums.length-1,0,0,true);
    }
    boolean pwHelper(int[] nums, int i, int j, int score1, int score2, boolean isPlayer1){
        if(isPlayer1 ){
            if(desired1-score1<=nums[i] || desired1-score1<=nums[j])
                return true;
            if(!pwHelper(nums,i+1,j,score1+nums[i],score2,false))
                if(!pwHelper(nums,i,j-1,score1+nums[j],score2,false))
                    return false;
            return true;
            
        }else{
            if(desired2-score2<=nums[i] || desired2-score2<=nums[j])
                return false;
            if(pwHelper(nums,i+1,j,score1,score2+nums[i],true))
                if(pwHelper(nums,i,j-1,score1,score2+nums[j],true))
                    return true;
            return false;
        }
    }
    //refer to 486    
    private int helper486(int[] nums, int start, int end, Integer[][] mem) {
        if (mem[start][end] == null) {
            mem[start][end] = start == end ? nums[end] :
                    Math.max(nums[end] - helper486(nums, start, end - 1, mem),
                            nums[start] - helper486(nums, start + 1, end, mem));
        }
        return mem[start][end];
    }
    
//#490 The Maze
    public boolean checkMaze(int[][] mz, int sx,int sy, int ex,int ey){
    	int[][] data=new int[mz.length+2][mz[0].length+2];
    	for(int i=0;i<data.length;i++)
    		for(int j=0;j<data[0].length;j++){
    			if(i==0 || i==data.length-1 || j==0 || j==data[0].length-1)
    				data[i][j]=1;
    			else
    				data[i][j]=mz[i-1][j-1];
    		}

//    	for(int[] row:data){
//    		for(int e:row)
//    			System.out.print(e + " ");
//    		System.out.println();
//    	}
    	
    	if(mazeHelper(data,sx+1,sy+1,ex+1,ey+1)){
    		System.out.println("Maze --- true");
    		return true;
    	}else{
    		System.out.println("Maze --- false");
    		return false;
    	}
    }
    
    boolean mazeHelper(int[][] mz, int sx,int sy, int ex,int ey){
    	int[][] directions={{-1,0},{0,1},{1,0},{0,-1}};
    	
    	if(sx==ex && sy==ey) return true;
    	
    	for(int[] d : directions){
    		int x=sx;
    		int y=sy;
    		while(mz[x+d[0]][y+d[1]]!=1){
    			x+=d[0];
    			y+=d[1];
    		}
    		if((x!=sx || y!=sy) && mz[x][y]!=-1){
    			mz[x][y]=-1;
    			if(mazeHelper(mz,x,y,ex,ey)) return true;
    		}
    	}
    	return false;
    }

//#491	    Increasing Subsequences  
    public List<List<Integer>> findSubsequences(int[] nums) {
        if(nums==null || nums.length<2) return null;
        HashSet<String> set = new HashSet<>();

        StringBuilder sbd = new StringBuilder();
        seqHelper(nums,sbd,0,set);

        ArrayList<List<Integer>> list = new ArrayList<>();

        HashSet<String> set2 = new HashSet<>();
        for(String s:set){
            sbd.setLength(0);
            for(char c : s.toCharArray()){
                sbd.append(nums[c]).append('_');
            }
            set2.add(sbd.toString());
        }
        
        for(String s:set){
            List<Integer> seq = new ArrayList<>();
            String[] arr = s.split("_");
            for(String ss : arr){
                if(ss!=null || ss.length()>0)
                    seq.add(Integer.parseInt(ss));
            }
            list.add(seq);
        }
        return list;
    }
    void seqHelper(int[] nums, StringBuilder sbd, int pos, HashSet<String> set ){
        int len=sbd.length();
        if(len>1 && !set.contains(sbd.toString()))
           set.add(sbd.toString());
        if(pos>=nums.length)
            return;        
        if(len==0 || nums[sbd.charAt(len-1)] <= nums[pos]){
            sbd.append((char) pos);
            seqHelper(nums,sbd,pos+1,set);
            sbd.setLength(sbd.length()-1);
        }
        seqHelper(nums,sbd,pos+1,set);
    }
      

    public List<List<Integer>> findSubsequences2(int[] nums) {
        if(nums==null || nums.length<2) return null;
        HashSet<String> set = new HashSet<>();

        StringBuilder sbd = new StringBuilder();
        backTrack(nums,sbd,0,set);

        ArrayList<List<Integer>> list = new ArrayList<>();
        
        for(String s:set){
            List<Integer> seq = new ArrayList<>();
            for(char c : s.toCharArray()){
                seq.add(nums[c]);
            }
            list.add(seq);
        }
        return list;
    }
    void backTrack(int[] nums, StringBuilder sbd, int pos, HashSet<String> set ){
        int len=sbd.length();
        if(len>1 && !set.contains(sbd.toString()))
           set.add(sbd.toString());
        if(pos>=nums.length)
            return;        
        if(len==0 || nums[sbd.charAt(len-1)] <= nums[pos]){
            sbd.append((char) pos);
            backTrack(nums,sbd,pos+1,set);
            sbd.setLength(sbd.length()-1);	//back track
        }
        backTrack(nums,sbd,pos+1,set);
    }
    
//494 target sum
    int res = 0;
    public int findTargetSumWays(int[] nums, int S) {
        Arrays.sort(nums);
        int[] sum = new int[nums.length];

        sum[0]=nums[0];
        for(int i=1;i<nums.length;i++){
            sum[i] = nums[i] + sum[i-1];
        }
        if(sum[nums.length-1]<Math.abs(S)) return 0;
        //if(sum[nums.length-1]==Math.abs(S) && S!=0) return 1;
        tswHelper(nums,nums.length-1,0,S,sum);
        return res;
    }
    
    void tswHelper(int[] nums,int pos,int cur,int ts,int[] sum){
        if(pos<0){
            if(ts==cur) 
                res++;
            return;
        }
        if(cur-sum[pos]<=ts && ts<=cur+sum[pos] ){
            tswHelper(nums,pos-1,cur+nums[pos],ts,sum);
            tswHelper(nums,pos-1,cur-nums[pos],ts,sum);
        }
    }
 /*   
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];
        }
        if(sum<S) return 0;
        tswHelper(nums,0,0,S);
        return res;
    }
    
    void tswHelper(int[] nums,int pos,int cur,int ts){
        if(pos>=nums.length){
            if(ts==cur) 
                res++;
            return;
        }
        tswHelper(nums,pos+1,cur+nums[pos],ts);
        tswHelper(nums,pos+1,cur-nums[pos],ts);
    }*/
    
//#503	Next Greater Element II
    public int[] nextGreaterElements(int[] nums) {
        if(nums==null || nums.length==0) return nums;
        int len = nums.length;
        int[] res=new int[len];
        int[] stack = new int[len<<1];
        int pos = len-1;
        for(int i=0;i<len;i++){
            stack[i]=nums[len-1-i];
        }
        for(int i=len-1;i>=0;i--){
            res[i]=-1;
            while(pos>=0 && stack[pos]<=nums[i])
                pos--;
            if(pos>=0)
                res[i]=stack[pos];
            stack[++pos]=nums[i];
        }
        
        return res;
    }
}
