import java.util.*;
import java.io.*;
public class class8b2 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("hps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		int num=scanner.nextInt();
		String[] arr=new String[num];
		int[][] move=new int[num][3];
		int a=0;
		int b=0;
		int c=0;
		for(int i=0; i<num; i++) {
			arr[i]=scanner.next();
			if(arr[i].equals("H")) {
				move[i][0]=++a;
				move[i][1]=b;
				move[i][2]=c;
				continue;
			}
			if(arr[i].equals("P")) {
				move[i][1]=++b;
				move[i][0]=a;
				move[i][2]=c;
				continue;
			}
			if(arr[i].equals("S")) {
				move[i][2]=++c;
				move[i][1]=b;
				move[i][0]=a;
				continue;
			}
		}
		int max=0;
		for(int i=0; i<num; i++) {
			int f = move[num-1][0]-move[i][0];
			int m = move[num-1][1]-move[i][1];
			int e = move[num-1][2]-move[i][2];
			int z=Math.max(f, m);
			int n=Math.max(z, e);
			int win=0;
			win+=n;
			z=Math.max(move[i][0], move[i][1]);
			n=Math.max(z, move[i][2]);
			win+=n;
			if(win>max) {
				max=win;
			}
		}
		out.println(max);
		out.close();
	}

}
