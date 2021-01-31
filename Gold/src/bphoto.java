import java.util.*;
import java.io.*;
public class bphoto {
	
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("bphoto.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bphoto.out")));
		int N=Integer.parseInt(br.readLine());
		int[][] arr=new int[N][2];
		for(int i=0; i<N; i++) {
			arr[i][0]=Integer.parseInt(br.readLine());
			arr[i][1]=i;
		}
		Arrays.sort(arr, (a,b)->b[0]-a[0]);
		ArrayList<Integer> list=new ArrayList<>();
		int count =0;
		for(int i=0; i<N; i++) {
			int s=list.size();
			int pos=Collections.binarySearch(list, arr[i][1]);
			pos++;
			pos*=-1;
			list.add(pos, arr[i][1]);
			int a=s-pos;
			int b=pos;
			if(a*2<b||b*2<a)
				count++;
		}
		out.println(count);
		out.close();
	}

}
