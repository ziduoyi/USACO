import java.util.*;
import java.io.*;
public class milkorder {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner= new Scanner(new File("milkorder.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
		int[] arr=new int[scanner.nextInt()];
		int[] order= new int[scanner.nextInt()];
		int x=scanner.nextInt();
		int pos=0;
		int d=0;
		for(int i=0; i<order.length; i++) {
			order[i]=scanner.nextInt();
		}
		for(int i=0;i<x;i++) {
			int cow=scanner.nextInt();
			arr[scanner.nextInt()-1]=cow;
		}
		boolean flag=false;
		for(int i=0; i<order.length; i++) {
			if(flag==true) {
				break;
			}
			for(int j=0; j<arr.length; j++) {
				if(order[i]==arr[j]) {
					for(int k=arr.length-j-2; k>0; k--) {
						if(arr[k]==0) {
							d++;
							arr[k]=order[i-d];
							break;
						}
					}
					d=0;
					for(int k=arr.length-1; k>arr.length-j-1; k--) {
						if(arr[k]==0) {
							d++;
							arr[k]=order[i+d];
							break;
						}
					}
					flag=true;
					break;
					
				}
			}
		}
		for(int i=0; i< arr.length; i++) {
			if(arr[i]==0) {
				pos=i+1;
				break;
			}
		}
		for(int i=0; i< arr.length; i++) {
			if(arr[i]==1) {
				pos=i+1;
				break;
			}
		}
		out.println(pos);
		out.close();
	}
}
