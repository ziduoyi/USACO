package com.lc;

import java.util.*;
public class StrCmp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StrCmp sc = new StrCmp();
		sc.findRepeatedDnaSequences2("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
	}
//Rolling Hash	
    public List<String> findRepeatedDnaSequences(String s) {
        int H = (int)(Math.pow(4,10))-1;
        
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('A',0);map.put('C',1); map.put('G',2);map.put('T',3);
        
        HashSet<String> res = new HashSet<>();
        HashSet<Integer> dict = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        
        int v = 0;
        for(int i=0;i<10;i++){
            v = v * 4 + map.get(s.charAt(i));
        }
        dict.add(v);
        int len=s.length();
        for(int i=0,j=i+10;j<len;i++,j++){
        	//char c1=s.charAt(i),c2=s.charAt(j);
            //v = (v-map.get(s.charAt(i))*H) * 4 + map.get(s.charAt(j));
        	v = (v<<2) & H | map.get(s.charAt(j));
            if(dict.contains(v)){
                res.add(s.substring(i+1,j+1));
            }
            else
                dict.add(v);
            list.add(v);
        }
        
        return new ArrayList<>(res);
    }
    
    public List<String> findRepeatedDnaSequences2(String s) {
        int len=s.length();
        ArrayList<String>list = new ArrayList<>();
        if(len<10)return list;

        int H=0xfffff;

        int[] map = new int[20];
        map['A'-'A']=0;
        map['C'-'A']=1;
        map['G'-'A']=2;
        map['T'-'A']=3;

        HashSet<String> res = new HashSet<>();
        HashSet<Integer> dict = new HashSet<>();

        int v = 0;
        for(int i=0;i<10;i++){
            v = (v << 2) | map[s.charAt(i)-'A'];
        }
        dict.add(v);
        for(int i=0,j=i+10;j<len;i++,j++){
            v = (v<<2) & H | map[s.charAt(j)-'A'];
            if(dict.contains(v)){
                res.add(s.substring(i+1,j+1));
            }
            else
                dict.add(v);
        }
        list.addAll(res);
        return list;
    }
    
    //normal rolling hash -- to check if string p contains string c or not
    public boolean rollingHashCmp(String p, String c){
    	final long b = 100000007;

    	int len = c.length();
    	long t = 1;
    	for(int i=0;i<len;i++)
    		t*=b;
    	
    	long pHash=0,cHash=0;
    	for(int i=0;i<len;i++){
    		pHash = pHash * b + p.charAt(i)-'A';
    		cHash = cHash * b + c.charAt(i)-'A';
    	}
    	
    	for(int i=len;i<p.length();i++){
    		if(pHash == cHash){
    			//match or conflict
    			boolean matched = true;
    			int j=0;
    			for(int k=i-len;k<len;k++){
    				if(p.charAt(k)!=c.charAt(j++)){
    					matched = false;
    					break;
    				}
    			}
    			if(matched) return true;
    		}
    		pHash = pHash*b - t* p.charAt(i-len) + p.charAt(i)-'A';
    	}
    	
    	return false;
    }

    public int bovineGenomicsGold(){
    	int res=0;

    	int N=3, M=8;
    	String[] spotty = new String[]{"AATCCCAT","ACTTGCAA","GGTCGCAA"};
    	String[] plain = new String[]{"ACTCCCAG","ACTCGCAT","ACTTCCAT"};



    	return res;
    }
    
    
}
