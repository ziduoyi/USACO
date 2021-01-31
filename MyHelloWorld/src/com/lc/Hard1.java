package com.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

interface Problem{
	String type="math";
	default String getType() {
		return type;
	}
	default void setType(String t) {
		//type=t;
	}
}


public class Hard1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		ArrayList<Integer> list = new ArrayList(10);
//		Integer[] arr = new Integer[list.size()];
//		list.toArray(arr);
		
//		String s="abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb";
//		String p="**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb";
//		int[][] match = new int[s.length()][p.length()];
//		System.out.println(isMatch(s.toCharArray(),0,p.toCharArray(),0,match));
		
		//System.out.println(isMatch("ho","**ho"));
		
		alienOrder(new String[] {"bsusz","rhn","gfbrwec","kuw","qvpxbexnhx","gnp","laxutz","qzxccww"});
		
	}
//#	wild char
    static boolean isMatch(String s, String p) {
        int len1 = s.length();
        int len2 = p.length();
        if(len1>0 && len2==0)
            return false;
        if(len1==0 && len2==0)
            return true;
        
        int[][] dp = new int[len1+1][len2+1];
        for(int i=0;i<=len1;i++)
            Arrays.fill(dp[i],-1);

        dp[0][0]=0;
        for(int i=0;i<len2;i++)
        	if(p.charAt(i)=='*')
        		dp[0][i+1]=0;
        	else
        		break;
        for(int i=0;i<len1;i++){
            for(int j=0;j<len2;j++){
                if(s.charAt(i)==p.charAt(j) || p.charAt(j)=='?'){
                    if(dp[i][j]>=0)
                        dp[i+1][j+1] = i + 1;
                }else if(p.charAt(j)=='*'){
                    int v = Math.max(Math.max(dp[i][j],dp[i][j+1]),dp[i+1][j]);
                    if(v>=0)
                        dp[i+1][j+1] = i + 1;
                }
            }
        }
        return dp[len1][len2]==len1;
    }
	
    static boolean isMatch(char[] s, int a, char[] p, int b,int[][] match){
        int len1=s.length, len2=p.length;
        
        int x=a, y=b;
        if(a<len1 && b<len2) {
	        if(match[a][b]>0) return true;
	        if(match[a][b]<0) return false;
        }
        while(x<len1 && y<len2){
            if(s[x]==p[y] ||p[y]=='?'){
                x++;
                y++;
            }else if(p[y]=='*'){
                int num = 0;
                int i=y+1;
                while(i<len2){
                    if(p[i]=='*') 
                       i++;
                    else if(p[i]=='?'){
                        i++;
                        num++;
                    }else
                        break;
                }
                if(i==len2){
                    if(len1-x>=num)
                        return true;
                    else {
                    	match[a][b]=-1;
                        return false;
                    }
                }
                int j=x+num;
                while(j<len1){
                    while(j<len1 && s[j]!=p[i]){
                        j++;
                    }
                    if(isMatch(s,j,p,i,match))
                        return true;
                    j++;
                }
                match[a][b]=-1;
                return false;
            }else {
            	match[a][b]=-1;
                return false;
            }
        }
        if(x==len1){
            for(int i=y;i<len2;i++)
                if(p[i]!='*') {
                	return false;
                }
            return true;
        }else {
        	match[a][b]=-1;
            return false;
        }
    }
	
	
	
//#123. Best Time to Buy and Sell Stock III	
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int res = getMaxProfit(prices,0,n-1);
        if(n<4) return res;
        
        for(int i=1;i<n-1;i++){
            if(prices[i-1]>=prices[i] && prices[i]<=prices[i+1])
                res = Math.max(res, getMaxProfit(prices,0,i-1)+getMaxProfit(prices,i,n-1));
        }
        return res;
    }
    
    int getMaxProfit(int[] p, int x, int y){
        int res=0;
        int low=Integer.MAX_VALUE, high=0;
        for(int i=x;i<=y;i++){
            if(p[i]>high) high=p[i];
            if(low<high && high>p[i] || i==y){
                if(high-low>res) res=high-low;
            }
            if(low>p[i]){
                low=p[i];
                high=low;
            }
        }
        return res;
    }
    
// #1092. Shortest Common Supersequence
    /*
    Input: str1 = "abac", str2 = "cab"
    		Output: "cabac"
    		Explanation: 
    		str1 = "abac" is a substring of "cabac" because we can delete the first "c".
    		str2 = "cab" is a substring of "cabac" because we can delete the last "ac".
    		The answer provided is any one of the shortest such string that satisfies these properties.    */
    
    public String shortestCommonSupersequence(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();
        
        int[][] dp = new int[len1+1][len2+1];
        
        //find longest common sub sequence
        for(int i=0;i<len1;i++){
            for(int j=0;j<len2;j++){
                if(str1.charAt(i)==str2.charAt(j))
                    dp[i+1][j+1]=dp[i][j]+1;
                else
                    dp[i+1][j+1]=Math.max(dp[i+1][j],dp[i][j+1]);
            }
        }
        
        //get the lcs string
        int x=dp[len1][len2];
        char[] lcs = new char[x];
        int m=len1,n=len2;
        while(m>0 && n>0){
            if(dp[m][n]==dp[m-1][n])
                m--;
            else if(dp[m][n]==dp[m][n-1])
                n--;
            else{
                lcs[--x] = str1.charAt(--m);
                --n;
            }
        }
        System.out.println(new String(lcs));
        
        int len = len1+len2-dp[len1][len2];
        char[] res = new char[len];
        m=0;n=0;x=0;
        for(int i=0;i<len;i++){
            if(x<lcs.length){
                if(m<len1 && str1.charAt(m)!=lcs[x])
                    res[i]=str1.charAt(m++);
                else if(n<len2 && str2.charAt(n)!=lcs[x])
                    res[i]=str2.charAt(n++);
                else{
                    res[i]=lcs[x++];
                    m++;n++;
                }
            }else{
                if(m<len1)
                    res[i]=str1.charAt(m++);
                else if(n<len2)
                    res[i]=str2.charAt(n++);
            }
        }
        return new String(res);
    }
    
//#269. Alien Dictionary
    public static String alienOrder(String[] words) {
        Map<Character,HashSet<Character>> pre = new HashMap<>();
        Map<Character,HashSet<Character>> post = new HashMap<>();
        StringBuilder sbd = new StringBuilder();
        
        HashSet<Character> all = new HashSet<>();
        
        for(int i=0;i<words.length-1;i++){
            String w1 =words[i], w2=words[i+1];
            for(char c : w1.toCharArray())
                all.add(c);
                
            int j=0;
            while(j<w1.length() && j<w2.length()){
                char c1=w1.charAt(j), c2=w2.charAt(j++);
                if(c1!=c2){
                    HashSet<Character> set = post.get(c1);
                    if(set==null){
                        set = new HashSet<Character>();
                        post.put(c1,set);
                    }
                    set.add(c2);
                    
                    set = pre.get(c2);
                    if(set==null){
                        set = new HashSet<Character>();
                        pre.put(c2,set);
                    }
                    set.add(c1);
                    break;
                }
            }
        }
        for(char c: words[words.length-1].toCharArray())
            all.add(c);
        LinkedList<Character> list = new LinkedList<>();
        for(char c : all){
            if(!pre.containsKey(c) || pre.get(c).size()==0)
                list.add(c);
        }
        while(list.size()>0){
            char c = list.removeFirst();
            sbd.append(c);
            HashSet<Character> postset = post.get(c);
            if(postset!=null){
                for(char pc: postset){
                    HashSet<Character> preset = pre.get(pc);
                    if(preset!=null) preset.remove(c);
                    if(preset==null || preset.isEmpty()){
                        list.add(pc);
                        pre.remove(pc);
                    }
                }
            }
        }
        for(char c : pre.keySet()){
            if(pre.get(c)!=null && pre.get(c).size()>0)
                return "";					//if conflicts, cannot remove all pre sets
        }
        return sbd.toString();
    }

    
//#428. Serialize and Deserialize N-ary Tree    
 // Definition for a Node.
    class Node428 {
        public int val;
        public List<Node428> children;

        public Node428() {}

        public Node428(int _val,List<Node428> _children) {
            val = _val;
            children = _children;
        }
    };

    class Codec {

        // Encodes a tree to a single string.
        public String serialize(Node428 root) {
            StringBuilder sbd = new StringBuilder();
            shelper(root,sbd);
            return sbd.toString();
        }
        void shelper(Node428 t, StringBuilder sbd){
            if(t==null){
                sbd.append("n,");
                return;
            }
            sbd.append(t.val).append(",").append(t.children.size()).append(",");
            for(Node428 node : t.children){
                shelper(node,sbd);
            }
        }

        // Decodes your encoded data to tree.
        int p=0;
        public Node428 deserialize(String data) {
            p=0;
            return dhelper(data);
        }
        
        Node428 dhelper(String data){
            if(p==data.length()) return null;
            
            int x = data.indexOf(',',p);
            String s = data.substring(p,x);
            p=x+1;
            if("n".equals(s)) return null;
            
            int v = Integer.parseInt(s);
            List<Node428> children = new ArrayList<>();
            Node428 t= new Node428(v,children);
            
            x=data.indexOf(',',p);
            int num = Integer.parseInt(data.substring(p,x));
            p=x+1;
            for(int i=0;i<num;i++){
                t.children.add(dhelper(data));
            }
            return t;
        }
    }
    
//to Ziduo
    //#877: game, and interval DP
    
}
