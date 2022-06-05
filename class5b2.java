import java.util.*;
import java.io.*;
public class class5b2 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner =new Scanner(new File("lifeguards.in"));
		PrintWriter out = new PrintWriter(new FileWriter("lifeguards.out"));
		int num=scanner.nextInt();
		int[][] arr= new int[num][2];
		for(int i=0; i<num; i++) {
			for(int j=0; j<2; j++) {
				arr[i][j]=scanner.nextInt();
			}
		}
		for(int i=0; i<num-1; i++) {
			for(int j=i+1; j<num; j++) {
				if(arr[j][0]<arr[i][0]) {
					int save=0;
					save=arr[j][0];
					arr[j][0]=arr[i][0];
					arr[i][0]=save;
					save=arr[j][1];
					arr[j][1]=arr[i][1];
					arr[i][1]=save;
				}
			}
		}
		int[] time= new int[1000];
		for(int i=0; i<num; i++) {
			for(int j=arr[i][0]; j<arr[i][1]; j++) {
				time[j]++;
			}
		}
		int max=0; 
		for(int i=0; i<num; i++) {
			int t=0;
			for(int j=arr[i][0]; j<arr[i][1]; j++) {
				time[j]--;
			}
			for(int j=0; j<1000; j++) {
				if(time[j]!=0) {
					t++;
				}
			}
			for(int j=arr[i][0]; j<arr[i][1]; j++) {
				time[j]++;
			}
			if(t>max) {
				max=t;			}
		}
		out.println(max);
		out.close();
	}
		
}
