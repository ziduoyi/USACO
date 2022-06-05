import java.io.*;
import java.util.*;
public class calculations {

	public static void main(String[] args) {
		double sum = 1;
		for(int i=1; i<=47; i++) {
			sum*= Math.sqrt(3/i + 1);
		}
		
		System.out.println(sum);
	}
	

	
}
