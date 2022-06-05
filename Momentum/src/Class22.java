import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Class22
{
  public static void main( String[] args ) throws FileNotFoundException
  {
    //add in all of the provided test cases from the lab handout
	  int[] array = new int[] {100000,4,6,5,7};
	   int z=0;
	   for(int value : array )
	   {
		   if( value % 2 != 0)
			   z++;
	   }
	   System.out.println(z);
  
  }
} 




