/*
ID: ziduoyi1
LANG: JAVA
TASK: milk2
*/
import java.io.*;
import java.util.*;
public class milk2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = new PrintWriter(new FileWriter("milk2.out"));
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(new File("milk2"+ ".in"));
		int num=scanner.nextInt();
		int save=0;
		int maxt=0;
		int maxe=0;
		int pos=0;
		int[][] arr=new int[num][2];
		for(int i=0;i<num;i++) {
			for(int j=0;j<2;j++) {
				arr[i][j]=scanner.nextInt();
			}
		}
		for(int i=0; i<num-1;i++) {
			int flag=0;
			for(int j=0;j<num-i-1;j++) {
				if(arr[j+1][0]<arr[j][0]) {
					flag++;
					save=arr[j][0];
					arr[j][0]=arr[j+1][0];
					arr[j+1][0]=save;
					save=arr[j][1];
					arr[j][1]=arr[j+1][1];
					arr[j+1][1]=save;
				}
			}
			if(flag==0) {
				break;
			}
		}
		maxt=arr[0][1]-arr[0][0];
		
		for(int i=0; i<num-1; i++) {
			int start=arr[pos][0];
			int end=arr[pos][1];
			int start2=arr[i+1][0];
			int end2=arr[i+1][1];
			if(end>=start2) {
				arr[pos][1]=Math.max(end, end2);
				maxt = Math.max(maxt, arr[pos][1]-start);
			}
			if(end<start2) {
				pos=i+1;
				maxe=Math.max(maxe, start2-end);
			}
		}
		out.print(maxt);
		out.print(" ");
		out.print(maxe);
		out.println();
		out.close();
	}
}
