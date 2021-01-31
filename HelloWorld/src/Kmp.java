import java.util.Arrays;
import java.util.Comparator;

public class Kmp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(kmpFind("ababababca", "abababca"));
		System.out.println(sunday("ababababca", "abababca",0));
	}
	
	static int kmpFind(String txt, String s) {
		int[] next = new int[s.length()];
		kmpNext(s, next);
		
		int i=0,j=-1;
		while(i<txt.length() && j<s.length()) {
			if(j==-1 || txt.charAt(i)==s.charAt(j)) {
				i++;
				j++;
			}else {
				j=next[j];
			}
		}
		if(j==s.length())
			return i-j;
		
		return -1;
	}
	
	static void kmpNext(String s, int[] next) {
		next[0]=-1;
		int i=0, j=-1;

		while(i<s.length()-1) {
			if(j==-1 || s.charAt(i)==s.charAt(j)) {
				i++;
				j++;
				next[i]=j;
			}else {
				j=next[j];
			}
		}
	}
	

	static int sunday(String txt, String s, int from) {
		if( s.length() + from > txt.length())
			return -1;

		int[] shift = new int[256];
		
		int m=s.length(), n=txt.length();
		Arrays.fill(shift, m+1);
		for(int i=0;i<s.length();i++) {
			shift[s.charAt(i)] = m-i; 
		}

		while (from <= n - m) {
			int j = 0;
			while (txt.charAt(from + j) == s.charAt(j)) {
				j++;
				if (j >= m)
					return from;
			}

			from += shift[txt.charAt(from + m)];
		}
		
		return -1;
	}
	//-----------------------------------------------------------
    // Suffix Array Implementation
	static int[] c;//c[sort key]=the number of the strings which have this key(bucket)
	static int[] x;//x[string's NUM]=the rank with the sort key now;  Rank
	static int[] y;//y[the rank of the second key]=string's NUM;
	static char[] s; //= new char[10010];//s[the adress in the string]=the char;
	static int[] sa;//sa[the rank of the string]=num;
	static int[] height;//height[rank]=len of lcp
	static int n;	//n:the len of the string,	m:the diction_num
	static int m;
	static int[] temp;
    public static void suffixArray()  {
    	s = "hgfedcba".toCharArray();		//"aabaaaab"
		n = s.length;
		m = 26;		
		c = new int[n];//c[sort key]=the number of the strings which have this key(bucket)
		x = new int[2*n];//x[string's NUM]=the rank with the sort key now;
		y = new int[2*n];//y[the rank of the second key]=string's NUM;
		sa = new int[n];//sa[the rank of the string]=num;
		height = new int[n];//height[rank]=len of lcp
	    int i,j,k;
	    for(i=0;i<n;i++)	//make x when the sort key is only one char
	    	x[i]=s[i]-'a';
	    for(i=0;i<n;i++)	//sort;
	    	c[x[i]]++;
	    for(i=1;i<m;i++)	 //Prefix sum to find rank;
	    	c[i]+=c[i-1];
	    for(i=n-1;i>=0;i--)		//make sa with the first char in each string; 	and sort with the first char; if same letter, the former first
	    	sa[--c[x[i]]]=i;	    
	    for(k=1;k<2*n;k*=2){
	        int num=0;
	        for(i=n-k;i<n;i++)	//the strings whose length <= k;
	        	y[num++]=i;	        
	        for(i=0;i<n;i++)
	        	if(sa[i]>=k)	//use the stringB which has the head char that is the No.k char in the stringA to sort stringA;
	        		y[num++]=sa[i]-k;	//sort with the second key;	        
	        Arrays.fill(c, 0);
	        for(i=0;i<n;i++)c[x[i]]++;	//sort with the last total sort key which could be the first key now;	        
	        for(i=1;i<m;i++)	//Prefix sum to find rank;
	        	c[i]+=c[i-1];
	        for(i=n-1;i>=0;i--) {
	        	int p = --c[x[y[i]]];
	        	sa[p] = y[i];
	        	//sa[--c[x[y[i]]]]=y[i];	//make sa with first K char;   //sort with the first key and as it finish the work, sorting with first K char, is finished;
	        }	        
	        Arrays.fill(y, 0);
	        temp=x;x=y;y=temp;//swap(x,y);
	        num=0;x[sa[0]]=num;
	        for(i=1;i<n;i++)
	            if(y[sa[i]]!=y[sa[i-1]] || y[sa[i]+k]!=y[sa[i-1]+k])	//if the string with sa[i] has the content which isn't the same as the content of the string with sa[i-1];
	                x[sa[i]]=++num;										//x[sa[i]] is diferent from x[sa[i-1]];
	            else
	                x[sa[i]]=num;	       	          
	        if(++num>=n)break;	//it means the work is finished that the key has the same number as the string;
	        m=num;		//expand the ton;
	        //make the x with the total key now as the first key in the next step;

	    }
    } 
    //----------------------------------------------------------------------
    //Trie implentation
    class Trienode {
    	boolean isword = false;
    	Trienode[] nodes = new Trienode[26];
    	void add(String str, int i) {
    		if(i>=str.length()) {
    			isword= true;
    			return;
    		}
    		int x = str.charAt(i) - 'a';
    		if(nodes[x]==null)
    			nodes[x] = new Trienode();
    		nodes[x].add(str, i+1);
    	}
    	
    	void add(String str, Trienode root) {
    		for(int i=0; i<str.length(); i++) {
    			int x = str.charAt(i)-'a';
    			if(root.nodes[x]==null)
    				root.nodes[x] = new Trienode();
    			root = root.nodes[x];
    		}
    		root.isword=true;
    	}
    	
    	boolean search(String str, Trienode root) {
    		for(int i=0; i<str.length(); i++) {
    			int x = str.charAt(i)-'a';
    			if(root.nodes[x]==null)
    				return false;
    			root = root.nodes[x];
    		}
    		return root.isword;
    	}
    }
}
