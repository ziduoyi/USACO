import java.util.*;
import java.io.*;
class Class3a1{
  public static void main( String[] args )throws IOException{
	  int size=3;
	  for(int i=0; i<=size; i++)
	  {
	     for(int x=0; x<=i; x++)
	        System.out.print('$');
	     System.out.println();
	  }
  }
  public static int go( int x, int y)
  {
     int cnt = 0;
     for(int n = x; n < y; n = n + 1)
        if(n % 2 != 0 )
           cnt++;
     return cnt;
  }
  
}
