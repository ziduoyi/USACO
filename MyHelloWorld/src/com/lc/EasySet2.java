package com.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import com.lc.Node;

public class EasySet2 {
	public static void main(String[] args){
		EasySet2 set2 = new EasySet2();
		
		Integer xx = 0 ;
		//set2.testPassing(xx);
		//System.out.println(xx);
		
		int[] nums={5,6,7,3,4,8,1,9};
//		set2.checkSetMismatch(nums);
		
		Node root=set2.buildBST(nums);
//		set2.getAverageForLevel(root);
		
//		set2.findTilt(root);
		
//		set2.reverseWords("Let's take LeetCode contest");
		
//		set2.diameterOfBinaryTree(root);
		
		//set2.getHammingDistance(8, 0);
		
//		int[][] points = new int[][] { { 3, 6 }, { 7, 5 }, { 3, 5 }, { 6, 2 }, { 9, 1 }, { 2, 7 },{ 0, 9 }, { 0, 6 }, { 2, 6 } };
//		set2.numberOfBoomerangs(points);
        
		//set2.findAnagrams("cbaebabacd", "abc");
		
		System.out.println(set2.addStrings("123", "9898"));
	}
	
	void testPassing(Integer n){
		n += 100;
		System.out.println(n);
	}	
	
	Node buildBST(int[] dataArray){
		if(dataArray==null || dataArray.length==0)
			return null;
		Node root = new Node(dataArray[0]);
		for(int i=1;i<dataArray.length;i++){
			Node node = new Node(dataArray[i]);
			root.insert(root, node);
		}
		return root;
	}

	
//#645	Set Mismatch
	int[] checkSetMismatch(int[] nums){
		int[] result = new int[2];
		int[] set = new int[nums.length+1];
		for(int i=0;i<nums.length;i++){
			int k = nums[i];
			if(k<=nums.length)
				if(set[k]!=0)
					result[0]=k;
				else
					set[k]=k;
		}
		for(int i=1;i<=nums.length;i++){
			if(set[i]==0)
				result[1]=i;
		}
		System.out.print("The mismatch results are: " + result[0] + "  "  + result[1]);
		return result;
	}
//#643	Maximum Average Sub array I
	int maxAverageSubArrayK(int[] nums, int k){
		int sum = 0;
		for(int i=0;i<k;i++){
			sum += nums[i];
		}
		int temp = sum;
		for(int i=0;i<nums.length-k;i++){
			temp = sum + nums[i+k-1]-nums[i] ;
			sum=Math.max(sum, temp);
		}
		return sum/k;
	}
//#643	Maximum Average Sub array I
	double maxAverageSubArray(int[] nums, int k){
		int[] sums = new int[nums.length];
		for(int i=1;i<nums.length;i++){
			sums[i]=sums[i-1]+nums[i];
		}
		double max = sums[k-1]/k;
		for(int i=k;i<nums.length;i++){
			for(int j=0;j<i-k;j++){
				int temp = (sums[i]-sums[j])/(i-j);
				max=Math.max(max, temp);
			}
		}
		return max;
	}
//637	Average of Levels in Binary Tree
	Object[] getAverageForLevel(Node root){
		if(root==null)return null;
		Vector<Double> v = new Vector<Double>();
		LinkedList<Node> list = new LinkedList<Node>();
		list.add(root);
		int num=1;
		int nextlevelNum = 0;

		double sum=0.0;

		while(!list.isEmpty()){
			int n=0;
			while(n<num){
				Node t = list.removeLast();
				sum += t.getData();
				n++;
				if(t.getLeft()!=null){
					list.addFirst(t.getLeft());
					nextlevelNum++;
				}
				if(t.getRight()!=null){
					list.addFirst(t.getRight());
					nextlevelNum++;
				}
			}
			v.add(sum/num);
			sum=0.0;
			num=nextlevelNum;
			nextlevelNum=0;
		}
		v.forEach(System.out::println);	//java 8
		v.forEach((data)->System.out.print(data+","));//lambda
		
		Double result = 0.0;
		//v.forEach(e -> result = e);
		ArrayList<Double> res = new ArrayList<Double>();
		v.forEach(e -> res.add(e));

		return v.toArray();
	}
//624	Maximum Distance in ordered Arrays $
	int getMaxDistance(int[][] m){
		if(m==null) return 0;

		int X = m.length;
		int Y = m[0].length;
		int[] max = new int[X];
		int[] min = new int[X];
		
//		for(int i=0;i<X;i++){
//			max[i]=m[i][0];
//			min[i]=m[i][0];
//			for(int j=1;j<Y;j++){
//				if(m[i][j]>max[i])
//					max[i]=m[i][j];
//				if(m[i][j]<min[i])
//					min[i]=m[i][j];
//			}
//		}
		int x=0, y=0;
		for(int i=1;i<X;i++){
			if(m[x][Y-1]<m[i][Y-1])
				x=i;
			if(m[y][0]>m[i][0])
				y=i;
		}
		if(x!=y)
			return m[x][Y-1]-m[y][0];

		//if x==y
		int a=0, b=0;
		if(x==0)
			a=b=1;
		for(int i=0;i<X;i++){
			if(i==x)
				continue;
			if(m[a][Y-1]<m[i][Y-1])
				a=i;
			if(m[b][0]>m[i][0])
				b=i;
		}
		return Math.max(max[x]-min[b], max[a]-min[y]);
	}
	
//563	Binary Tree Tilt
    int tilt=0;
    public int findTilt(Node root) {
        if(root==null)
            return 0;
        sumTree(root);
        System.out.println(this.tilt);
        return this.tilt;
    }
    public int sumTree(Node t){
        if(t==null)
            return 0;
        int ls = sumTree(t.left);
        int rs = sumTree(t.right);
        this.tilt += Math.abs(ls-rs);
        System.out.println(this.tilt);
        return ls+rs+t.data;
    }
//#557	Reverse Words in a String III	    
    String reverseWords(String str){
    	if (str==null) return null;
    	String[] array = str.split(" ");
    	StringBuilder sbd = new StringBuilder();
    	StringBuilder sbd1 = new StringBuilder();
    	for(int i=0;i<array.length;i++){
    		sbd1.setLength(0);
    		sbd1.append(array[i]);
    		sbd.append(sbd1.reverse()).append(" ");
    	}
    	sbd.setLength(sbd.length()-1);
    	System.out.println(sbd.toString());
    	return sbd.toString();
    }
//#543	Diameter of Binary Tree    
    int nums = 0;
    public int diameterOfBinaryTree(Node root) {
    	//String ss= Integer.toString(100,7);
        getDepth(root);
        System.out.println(this.nums);
        return nums;
    }
    int getDepth(Node t){
        if(t==null) return 0;
        int lDepth = getDepth(t.left);
        int rDepth = getDepth(t.right);
        if(lDepth + rDepth>nums)
            this.nums = lDepth+rDepth;
        int d = Math.max(lDepth, rDepth)+1;
        System.out.println(t.getData() + " " + d);
        return d;
    }
//#475	Heaters 
//#461	Hamming Distance 
    int getHammingDistance(int x, int y){
    	int count=0;
    	int n = x ^ y;
    	while(n!=0){
    		count++;
    		n &= n-1;		//calculate how may ones in a integer
    	}
    	System.out.println(count);
    	return count;
    }
    
    public int numberOfBoomerangs(int[][] points) {
        int count = 0;
        int len = points.length;
        HashMap<String,HashMap<Integer,Integer>> ptsMap = new HashMap<>();
        for(int i=0;i<len-1;i++){
            String key = Integer.toString(points[i][0]) + "_" + Integer.toString(points[i][1]);
            HashMap<Integer,Integer> disMap = ptsMap.get(key);
            if(disMap==null){
                disMap = new HashMap<Integer,Integer>();
                ptsMap.put(key,disMap);
            }
            for(int j=i+1;j<len;j++){
                int distance = (points[i][0]-points[j][0])*(points[i][0]-points[j][0]) + (points[i][1]-points[j][1])*(points[i][1]-points[j][1]);
                if(disMap.containsKey(distance)){
                    disMap.put(distance,disMap.get(distance)+1);
                }else{
                    disMap.put(distance,1);
                }
                String key2 = Integer.toString(points[j][0])+"_"+Integer.toString(points[j][1]);
                HashMap<Integer,Integer> disMap2 = ptsMap.get(key2);
                if(disMap2==null){
                    disMap2 = new HashMap<Integer,Integer>();
                    ptsMap.put(key2,disMap2);
                }
                if(disMap2.containsKey(distance)){
                    disMap2.put(distance,disMap2.get(distance)+1);
                }else{
                    disMap2.put(distance,1);
                }
            }
//            for(Integer d: disMap.keySet()){
//                count += disMap.get(d) * (disMap.get(d)-1);
//            }
            for(Integer val : disMap.values()){
            	count += val * (val-1);
            }
        }
        System.out.println(count);
        return count;
    }
//#438	Find All Anagrams in a String

    public List<Integer> findAnagrams(String s, String p) {
        if(s==null || p==null || p.length()>s.length()) return null;
        HashMap<Character,Integer> pmap = new HashMap<>();
        for(char c:p.toCharArray()){
            if(pmap.containsKey(c)){
                pmap.put(c,pmap.get(c)+1);
            }else
                pmap.put(c,1);
        }
        for(int i=0;i<p.length();i++){
            char c=s.charAt(i);
            if(pmap.containsKey(c)){
                if(pmap.get(c)==1)
                    pmap.remove(c);
                else
                    pmap.put(c,pmap.get(c)-1);
            }else
                pmap.put(c,-1);
        }
        int i=0;
        int m=s.length()-p.length();
        ArrayList<Integer> list = new ArrayList<>();
        if(pmap.size()==0)
            list.add(0);
        while(i<m){
            char prev = s.charAt(i);
            char next = s.charAt(i+p.length());
            if(prev!=next){
                if(pmap.containsKey(prev))
                	if(pmap.get(prev)==-1)
                		pmap.remove(prev);
                	else
                		pmap.put(prev,pmap.get(prev)+1);
                else
                    pmap.put(prev,1);
                if(pmap.containsKey(next)){
                    if(pmap.get(next)==1)
                        pmap.remove(next);
                    else
                        pmap.put(next,pmap.get(next)-1);
                }else
                    pmap.put(next,-1);
            }
            i++;
            if(pmap.size()==0)
                list.add(i);
        }

        list.forEach((e)->System.out.print(e+","));
        return list;
    }


//#415	Add Strings 
    public String addStrings(String num1, String num2) {
        if(num1==null || num1.length()==0) return num2;
        if(num2==null || num2.length()==0) return num1;
        StringBuilder sbd = new StringBuilder();
        int i = num1.length()-1;
        int j = num2.length()-1;
        int carry = 0;
        
        while (i>=0 || j>=0){
            int a = i>=0?(num1.charAt(i)-'0'):0;
            int b = j>=0?(num2.charAt(j)-'0'):0;
            int c = a + b + carry;
            if(c>9) {
                c -= 10;
                carry=1;
            }else
                carry=0;
            sbd.append(c);
            i--;j--;
        }
        if(carry==1)
            sbd.append("1");
        return sbd.reverse().toString();
        
    }
}
