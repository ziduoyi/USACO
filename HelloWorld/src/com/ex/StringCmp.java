package com.ex;
import java.util.*;
public class StringCmp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringCmp sc = new StringCmp();
		sc.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
	}
//Rolling Hash + with bit operations + customized Hash map array
//leetcode #187. Repeated DNA Sequences
    public List<String> findRepeatedDnaSequences(String s) {
        LinkedList<String> list=new LinkedList<>();
        if(s.length()<10)return list;
        int[] hmap = new int[26];
        hmap['A'-'A']=0;
        hmap['C'-'A']=1;
        hmap['G'-'A']=2;
        hmap['T'-'A']=3;
        
        int mask = 0xfffff;
        
//        HashMap<Character,Integer> cov=new HashMap<>();
//        cov.put('A',0);
//        cov.put('C',1);
//        cov.put('G',2);
//        cov.put('T',3);
        Set<Integer> set=new HashSet<>();
        Set<String> ans=new HashSet<>();
        
        int h=0;
        for(int i=0;i<10;i++)
        	h = (h<<2) + hmap[s.charAt(i)-'A'];
        set.add(h);
        
        for(int i=0,j=i+10; j<s.length(); i++,j++){
        	h = (h<<2) & mask | hmap[s.charAt(j)-'A'];
        	
        	if(set.contains(h)) {
        		ans.add(s.substring(i+1,j+1));
        	}else
        		set.add(h);
        }
        list.addAll(ans);
        return list;
    }
}
