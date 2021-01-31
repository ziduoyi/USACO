import java.util.*;
import java.io.*;
class Class3a1{
  public static void main( String[] args )throws IOException{
      /*
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      double L = Double.parseDouble(st.nextToken());
      double S = Double.parseDouble(st.nextToken());
      char[][] maze = new char[N][N];
      int startx = 0;
      int starty = 0;
      for(int i=0; i<N; i++){
          String str = br.readLine();
          for(int j=0; j<N; j++){
              maze[i][j] = str.charAt(j);
              if(str.charAt(j)=='S') {
            	  startx = i;
            	  starty = j;
              }
          }
      }
      */
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      boolean a1 = false;
      boolean a2 = false;
      boolean b1 = false;
      boolean b2 = false;
      for(int i=0; i<N; i++) {
    		  StringTokenizer st = new StringTokenizer(br.readLine());
    		  while(st.hasMoreTokens()) {
    			  String str = st.nextToken();
    			  if(str.equals("*winks*")) {
    				  if(i%2==0) 
    					  a1=true;
    				  else
    					  b1=true;
    			  }
    			  if(str.equals("*checks")&&st.hasMoreTokens()&&st.nextToken().equals("watch*")) {
    				  if(i%2==0) 
    					  a2=true;
    				  else
    					  b2=true;
    			  }
    		  }
    		  if(a1&&a2) {
    			  System.out.println("JINGLE IS A SPY!");
    			  return;
    		  }
    		  if(b1&&b2) {
    			  System.out.println("GUMDROP IS A SPY!");
    			  return;
    		  }
      }
      System.out.println("NO SPIES ARE PRESENT");
  }
  
}
