import java.util.*;
import java.io.*;
public class class6c3 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		//long startTime = System.nanoTime();
		
		Scanner scanner=new Scanner(new File("bcount.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));
		int N = scanner.nextInt();
		int Q =scanner.nextInt();
	//	int[] arr= new int[N];
		int[][] nums= new int[Q][2];
		int[][] ans= new int[N+1][3];
		int b1=0;
		int b2=0;
		int b3=0;
		for(int i=1; i<N+1; i++) {
			int b=scanner.nextInt();
			if(b==1) {
				b1++;
			}
			if(b==2) {
				b2++;
			}
			if(b==3) {
				b3++;
			}
			ans[i][0]=b1;
			ans[i][1]=b2;
			ans[i][2]=b3;
			
		}
		for(int i=0; i<Q; i++) {
			for(int j=0; j<2; j++) {
				nums[i][j]=scanner.nextInt();
			}
		}

		for(int i=0; i<Q; i++) {
			int num=0;
			for(int j=0; j<3; j++) {
				num=ans[nums[i][1]][j]-ans[nums[i][0]-1][j];
				out.print(num);
				if(j<2)
					out.print(" ");
			}
			out.println();
		}
		out.close();
		
//		long endTime   = System.nanoTime();
//		long totalTime = endTime - startTime;
//		System.out.println(totalTime/1000000000);
	}
}


