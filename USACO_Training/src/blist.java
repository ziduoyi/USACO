import java.util.*;
import java.io.*;
public class blist {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("blist.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("blist.out")));
		int num=scanner.nextInt();
		int[][] arr=new int[num][3];
		for(int i=0; i<num; i++) {
			for(int j=0; j<3; j++) {
				arr[i][j]=scanner.nextInt();
			}
		}
		Arrays.sort(arr,(a,b)->a[0]-b[0]);
		int max=0;
		for(int i=0; i<num; i++) {
			int tol=arr[i][2];
			for(int j=i-1; j>-1; j--) {
				if(arr[i][0]<arr[j][1]) {
					tol+=arr[j][2];
				}
			}
			max=Math.max(tol, max);
		}
		out.println(max);
		out.close();
	}

}
