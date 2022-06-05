import java.io.*;
import java.util.*;
public class class6d4 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		int a=Integer.MAX_VALUE;
		Scanner scanner=new Scanner(new File("lightson.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
		int len=scanner.nextInt();
		int com=scanner.nextInt();
		int[][] square = new int[len][len];
		int[][] light =new int[com][4];
		for(int i=0; i<com; i++) {
			for(int j=0; j<4; j++) {
				light[i][j]=scanner.nextInt()-1;
			}
		}
		for(int i=0; i<com-1; i++) {
			for(int j=i;j<com-i-1; j++ ) {
				if(light[j][0]>light[j+1][0]) {
					int save=light[j][0];
					light[j][0]=light[j+1][0];
					light[j+1][0]=save;
					save=light[j][1];
					light[j][1]=light[j+1][1];
					light[j+1][1]=save;
					save=light[j][2];
					light[j][2]=light[j+1][2];
					light[j+1][2]=save;
					save=light[j][3];
					light[j][3]=light[j+1][3];
					light[j+1][3]=save;
				}
			}
		}
		for(int i=0; i<com; i++) {
			for(int j=i; j<com; j++) {
		//		if(light[i][])
			}
		}
	}

}
