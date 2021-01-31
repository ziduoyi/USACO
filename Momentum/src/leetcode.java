import java.io.*;
import java.util.*;
public class leetcode {
	class StockSpanner {
		Stack<Integer> data;
	    Stack<Integer> num;
	    public StockSpanner() {
	    	data =new Stack<>();
	        num =new Stack<>();
	    }
	    
	    public int next(int price) {
	    	int sum=0;
	    	while(data.size()!=0&&price>data.peek()) {
	    		data.pop();
	    		sum+=num.pop();
	    	}
	    	data.push(sum);
	    	data.push(price);
	    	
			return price;
	        
	    }
	}

	public static void main(String[] args)throws IOException {
		leetcode lt=new leetcode();
		StockSpanner obj = lt.new StockSpanner();
		 int param_1 = obj.next(100);
		 
		
		// TODO Auto-generated method stub
	/*b	long K=1;
		
		String S="a2345678999999999999999";

		long len=0;


		for(int i=0; i<S.length(); i++) {
			if(S.charAt(i)>='2'&& S.charAt(i)<='9') {
				len*=(S.charAt(i)-'0');
			}
			else {
				len++;
				
			}
				
		}
		for(int i=S.length()-1; i>-1; i--) {
			char c=S.charAt(i);
			K%=len;
			if(K==0&&c>='a'&& c<='z') {
				String s="";
				System.out.println(s+c);
				return;
			}
			
			if(c>='2'&& c<='9') {
				len/= (c-'0');
			}
			else {
				len--;
			}
		}

		
		
		*/
	}

}
