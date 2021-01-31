import java.io.*;
import java.util.*;
public class dream {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("dream.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dream.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int len=Integer.parseInt(st.nextToken());
		int wid=Integer.parseInt(st.nextToken());
		int[][] arr=new int[len][wid];
		for(int i=0;i<len; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<wid; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		LinkedList<int[]> list=new LinkedList<>();
	}

}
