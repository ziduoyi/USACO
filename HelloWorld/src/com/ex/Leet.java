package com.ex;

public class Leet {
	
	public void testStringCharType(){
		System.out.println(" --- test String Char");
		
		System.out.print("Y" + "O");
		System.out.print('L' + 'O');		// char converted to int for +/-...
		System.out.print('L');
		System.out.print('O');

		System.out.println("");
		System.out.println(" --- end");
	}
	
	public String getPalindromicSubstring(String source){
		String psub = null;
	    String input = "forgeeksskeegfor";
	    //Scanner s = new Scanner(input).useDelimiter("\\s*fish\\s*");
	    
	    int len = input.length();
		if(input.length()<2)
			return input;

		int start = 0;
		int maxLength = 1;

		for (int i=1; i<len; i++){
			//odd length
			int x=i-1;
			int y=i+1;
			while(x>=0 && y <= len-1 && input.charAt(x) == input.charAt(y)){
				if(y-x+1>maxLength){
					maxLength = y-x+1;
					start = x;
				}
				--x;
				++y;
			}
			
			//even length
			x=i;
			y=i+1;
			while(x>=0 && y<=len-1 && input.charAt(x)==input.charAt(y)){
				if(y-x+1>maxLength){
					maxLength = y-x+1;
					start=x;
				}
				--x;
				++y;
			}
		}
		psub = input.substring(start,start+maxLength);
		System.out.println(psub);

		return psub;
	}
}
