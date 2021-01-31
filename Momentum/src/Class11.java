import java.util.*;
import java.io.*;
public class Class11 {
	
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    StarsAndStripes var = new StarsAndStripes();
	    var.printASmallBox();
	    var.printTwoBlankLines();
	    var.printABigBox();
	}
}
class StarsAndStripes{
	   public StarsAndStripes(){
	      System.out.println("StarsAndStripes");
	      printTwoBlankLines();
	   }

	   public void printTwentyStars(){
	     System.out.println("********************");
	   }

	   public void printTwentyDashes(){
	     System.out.println("--------------------");
	   }

	   public void printTwoBlankLines(){
	     System.out.println("\n");
	   }
	   
	   public void printASmallBox(){	
	     printTwentyDashes();
	     printTwentyStars();
	     printTwentyDashes();
	     printTwentyStars();
	     printTwentyDashes();
	     printTwentyStars();
	     printTwentyDashes();
	   }
	 
	   public void printABigBox(){
	     printASmallBox();
	     printASmallBox();
	   }
	}
