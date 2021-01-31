import java.io.*;
import java.util.*;
public class class8a1 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("hps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		int num=scanner.nextInt();
		int[][] arr=new int[num][2];
		for(int i=0; i<num;i++) {
			for(int j=0; j<2; j++) {
				arr[i][j]=scanner.nextInt();
			}
		}
		int win1=0;
		int draw=0;
		int win2=0;
		for(int i=0; i<num; i++) {
			if(arr[i][0]==arr[i][1]) {
				continue;
			}
			if(arr[i][0]==1&&arr[i][1]==2) {
				win2++;
				continue;
			}
			if(arr[i][0]==1&&arr[i][1]==3) {
				win1++;
				continue;
			}
			if(arr[i][0]==2&&arr[i][1]==3) {
				win2++;
				continue;
			}
			if(arr[i][0]==2&&arr[i][1]==1) {
				win1++;
				continue;
			}
			if(arr[i][0]==3&&arr[i][1]==1) {
				win2++;
				continue;
			}
			if(arr[i][0]==3&&arr[i][1]==2) {
				win1++;
				continue;
			}
		}
		if(win1>win2) {
			out.println(win1);
			out.close();
		}
		else {
			out.println(win2);
			out.close();
		}
	}

}
