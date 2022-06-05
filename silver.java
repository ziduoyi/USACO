import java.util.*;
import java.io.*;
public class silver {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("reststops.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
	/*	int len=Integer.parseInt(st.nextToken());
		int stop=Integer.parseInt(st.nextToken());
		int fs=Integer.parseInt(st.nextToken());
		int cs=Integer.parseInt(st.nextToken());
		int[][] arr=new int[2][stop];
		for(int i=0; i<2; i++) {
			StringTokenizer st1=new StringTokenizer(br.readLine());
			for(int j=0; j<stop; j++) {
				arr[i][j]=Integer.parseInt(st1.nextToken());
			}
		}
		Arrays.sort(arr,(a,b)->a[0]-b[0]);
		ArrayList<Integer> pos=new ArrayList<>();
		ArrayList<Integer> tas=new ArrayList<>();
		int max=0;
		for(int i=0; i<stop; i++) {
			if(arr[1][i]>=max) {
				max=arr[1][i];
			}
		}
		
		int p1=scanner.nextInt();
		int p2=scanner.nextInt();
		int move =scanner.nextInt();
		int tar=scanner.nextInt();
		if(p1==tar||p2==tar) {
			out.print(0);
			out.close();
			return;
		}
		if(p1+p2==tar&&move>1) {
			out.print(0);
			out.close();
			return;
		}
		int max=999999999;
		int min =Math.min(p1, p2);
		int bigger=Math.max(p1, p2);
		int i=2;
		int a=0;;
		while(min<bigger&&i<move) {
			int dif=Math.abs(tar-a);
			if(dif<max) {
				max=dif;
			}
			a+=min;
			i+=2;
		}
		int max2=999999999;
		i=2;
		while(min<bigger&&i<move) {
			bigger-=min;
			i+=2;
			int dif=Math.abs(tar-bigger);
			if(dif<max2)
				max2=dif;
		}
		int z=Math.min(Math.min(max, Math.abs(tar-p1)),Math.min(Math.abs(tar-p2), Math.abs(tar-p1-p2)));
		z=Math.min(z, max2);
		out.print(z);
		out.close();
		*/
	}

}
