import java.io.*;
import java.util.*;
public class class9 {

	public static void main(String[] args)throws IOException {
		//USACO 2017 US Open Contest, Silver
		//Problem 1. Paired Up
		BufferedReader br=new BufferedReader(new FileReader("nail.in"));
		int num=Integer.parseInt(br.readLine());
		int[] arr=new int[2001];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0; i<num ;i++) {
			arr[Integer.parseInt(st.nextToken())]++;
		}
		//int[] lengths = new int[4001];
		int[] nums = new int[4001];
		for(int i=1;i<=2000;i++) {
			nums[i+i]=arr[i]/2;
			for(int j=i+1;j<=2000;j++) {
				nums[i+j] += Math.min(arr[i], arr[j]);
			}
		}
		int z=0;
		

	//	System.out.print(maxLen);
		System.out.print(" ");
	//	System.out.print(count);
		//31415925455
		//Scanner scanner=new Scanner(new File(""));
/*		int radius =100000;
		long count=0;
		int add =1;
		for(int i=0; i<(2*radius)+1; i++) {
			count+=add;
			if(i<radius ) {
				add+=2;
			}
			else {
				add-=2;
			}
		}
		System.out.println(count);*/
		
	}

}
