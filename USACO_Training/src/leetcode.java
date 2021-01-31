import java.io.*;
import java.util.*;
public class leetcode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String S="z";
		int[] shifts=new int[] {52};
		char[] arr=S.toCharArray();
		int[] val=new int[arr.length];
		for(int i=0; i<arr.length;i++) {
			val[i]=arr[i];
		}
		for(int i=shifts.length-2; i>-1; i--) {
			shifts[i]+=shifts[i+1];
		}
		for(int i=0; i<arr.length; i++) {
			val[i]+=shifts[i];
			if(val[i]>122) {
				val[i]-=96;
				val[i]%=26;
				val[i]+=96;
				arr[i]=(char) val[i];
			}
			else {
				arr[i]=(char) val[i];
			}
		}
		
		System.out.println(new String(arr));
		//return new String(arr);
	}

}
