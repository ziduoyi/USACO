import java.util.*;
import java.io.*;
public class countcross {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("countcross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("countcross.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int len=Integer.parseInt(st.nextToken());
		int cow=Integer.parseInt(st.nextToken());
		int road=Integer.parseInt(st.nextToken());
		int[][] c=new int[cow][2];
		int[][] arr=new int[len][len];
		for(int i=0; i<road; i++) {
			StringTokenizer st1=new StringTokenizer(br.readLine());
			int y= Integer.parseInt(st1.nextToken())-1;
			int x=Integer.parseInt(st1.nextToken())-1;
			arr[y][x]=1;
			y= Integer.parseInt(st1.nextToken())-1;
			x=Integer.parseInt(st1.nextToken())-1;
			arr[y][x]=1;
		}
		for(int i=0; i<cow; i++) {
			StringTokenizer st1=new StringTokenizer(br.readLine());
			c[i][0]= Integer.parseInt(st1.nextToken())-1;
			c[i][1]=Integer.parseInt(st1.nextToken())-1;
		}
		int dis=0;
		for(int i=0; i<cow; i++) {
			int a=c[i][0];
			int v=c[i][1];
			
			int b=0;
			if(arr[a][v]==1) {
				b++;
			}
			if(a==0&&v==0||a==0&&v==cow-1||a==cow-1&&v==0||a==cow-1&&v==cow-1)
				b+=2;

			for(int j=0; j<cow; j++) {
				if(c[j][0]==c[i][0]) {
					b++;
				}
			}
			
			if(b==5) {
				dis++;
			}
		}
		out.print(dis);
		out.close();
	}

}
