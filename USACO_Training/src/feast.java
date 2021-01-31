import java.io.*;
import java.util.*;
public class feast {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("feast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("feast.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int cap=Integer.parseInt(st.nextToken());
		int a=Integer.parseInt(st.nextToken());
		int b=Integer.parseInt(st.nextToken());
		int num=cap+1;
		boolean[][] arr=new boolean[2][num];
		arr[0][0]=true;
		for(int i=0; i<2; i++) {
			for(int j=0; j<num; j++) {
				if(arr[i][j]==false)
					continue;
				if(j+a<=cap) {
					arr[i][j+a]=true;
				}
				if(j+b<=cap) {
					arr[i][j+b]=true;
				}
				if(i==0) {
					arr[1][j/2]=true;
				}
			}
		}
		int max=0;
		for(int i=0; i<num; i++) {
			if(arr[1][i]==true) 
				max=Math.max(max, i);
		}
		out.println(max);
		out.close();
	}

}
