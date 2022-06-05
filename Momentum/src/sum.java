import java.io.*;
import java.util.*;
public class sum {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    long a = 172896201 ;
	    long b = 172896201 ;
	    long ans = 0;
	    for(long j=1; j<=Math.round(Math.sqrt(b)); j++) {
	    	if(a*j/gcd(a,j)==b) {
	    		ans += ((b*b)/(j*j));
	    	}
	    	j = b/j;
	    	if(j==b/j)break;
	    	if(a*j/gcd(a,j)==b) {
	    		ans += ((b*b)/(j*j));
	    	}
	    	j = b/j;
	    }
	    System.out.println(ans);
	    ans = 0;
	    for(long j=1; j<=b; j++) {
	    	if(a*j/gcd(a,j)==b) {
	    		ans += ((b*b)/(j*j));
	    	}
	    }
	    System.out.println(ans);
	}
	static long gcd(long a, long b) {
		if(a==0||b==0)return Math.max(a, b);
		return gcd(b, a-a/b*b);
	}
}
