/*
ID: ziduoyi1
LANG: JAVA
TASK: transform
*/
import java.util.*;
import java.io.*;
public class dining {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		PrintWriter out = new PrintWriter(new FileWriter("transform.out"));
		Scanner scanner=new Scanner (new File("transform.in"));
		int num=scanner.nextInt();
		char[][] arr=new char[num][num];
		char[][] arr2=new char[num][num];
		char[][] save=new char[num][num];
		for(int i=0; i<num; i++) {
			String s = scanner.next();
			arr[i]=s.toCharArray();
		}
		for(int i=0; i<num; i++) {
			String s = scanner.next();
			arr2[i]=s.toCharArray();
		}
		save = duplicate(arr, num);
		for(int i=1;i<4;i++) {
			arr=rotate(arr,num);
			if(isEqual(arr,arr2, num)) {
				out.println("" + i);
				out.close();
				return;
			}
		}
		for(int j=0; j<4; j++) {
			reflect(arr, save,num);
			for(int k=0; k<j; k++) {
				arr=rotate(arr, num);
			}
			if(isEqual( arr, arr2, num)) {
				if(j==0) {
					out.println("4");
					out.close();
					return;
				}
				else {
					out.println("5");
					out.close();
					return;
				}
			}
		}
		if(isEqual(save,arr2, num)) {
			out.println("6");
		}else
			out.println("7");
		out.close();
		return;
	}
	static boolean isEqual(char[][] arr,char[][] arr2,int num) {
		for(int i=0; i<num; i++) {
			for(int j=0;j<num; j++) {
				if(arr[i][j]!=arr2[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	static char[][] rotate(char[][] save,int num){
		char[][] arr = new char[num][num];
		for(int i=0; i<num; i++) {
			for(int x=0;x<num;x++) {
				arr[x][num-1-i]=save[i][x];
			}
		}
		return arr;
	}
	static void reflect(char[][] arr,char[][] save,int num){
		for(int i=0; i<num; i++) {
			for(int x=0;x<num;x++) {
				arr[i][num-1-x]=save[i][x];
			}
		}
	}
	static char[][] duplicate(char[][] arr,int num){
		char[][] save = new char[num][num];
		for(int i=0; i<num; i++) {
			for(int x=0;x<num;x++) {
				save[i][x] = arr[i][x];
			}
		}
		return save;
	}
}