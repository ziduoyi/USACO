import java.util.*;
public class practice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1="geeksforgeeks";
		String s2="fourtrees";
		int x=s1.length();
		int y=s2.length();
		int[][] arr=new int[y][x];
		for(int i=0; i<y; i++) {
			for(int j=0; j<x; j++) {
				if((i==0||j==0)&&s1.charAt(j)==s2.charAt(i)) {
					arr[i][j]++;
					continue;
				}
				if(i==0||j==0) {
					if(j!=0) {
						arr[i][j]=arr[i][j-1];
					}
					continue;
				}
				int add=0;
				if(s1.charAt(j)==s2.charAt(i))
					add++;
				arr[i][j]=Math.max(arr[i-1][j], arr[i][j-1]+add);
			}
		}
		System.out.println(arr[y-1][x-1]);
	}

}
