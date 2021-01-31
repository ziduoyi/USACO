package com.ex;

import java.util.Scanner;

public class Str2Int {

	public void main(String[] args){
		Scanner keyboard  = new Scanner(System.in);
		
		String rawStr = keyboard.next();
		int sign = 1;
		boolean firstFound = false;
		String digitStr = "";
		StringBuffer strbuf = new StringBuffer();
		for(int i=0;i<rawStr.length();i++){
			char c = rawStr.charAt(i);
			if(!firstFound){
				if(c=='+')
					firstFound = true;
				if(c=='-'){
					firstFound = true;
					sign = -1;
				}
				if (c>'0' && c<='9'){
					firstFound = true;
					strbuf.append(c);
				}
			}
			
			if(c=='+' || c=='-' || c>='0' && c<='9'){
				
			}
		}
		
		
	}
	
}
 