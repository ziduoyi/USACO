import java.util.*;
import java.io.*;
public class reststops {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("reststops.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int len=Integer.parseInt(st.nextToken());
		int stops=Integer.parseInt(st.nextToken());
		int fs=Integer.parseInt(st.nextToken());
		int cs=Integer.parseInt(st.nextToken());
		int[][] arr= new int[stops][2];
		for(int i=0; i<stops; i++) {
			st=new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr,(a,b)->{if(a[0]!=b[0]) 
				return a[0]-b[0];
				else return a[1]-b[1];
				});
		LinkedList<int[]> list=new LinkedList<>();
		for(int i=0; i<stops; i++) {
			while(!list.isEmpty()) {
				int[] a=list.removeLast();
				if(arr[i][1]>=a[1]) {
				}
				else {
					list.add(a);
					break;
				}
			}
			list.add(arr[i]);
		}
		long taste =0;
		long last=0;
		while(!list.isEmpty()) {
			int[] u=list.removeFirst();
			long ft=fs*(u[0]-last);
			long bt=cs*(u[0]-last);
			long time=ft-bt;
			last=u[0];
			taste+=(time*u[1]);
		}
		out.println(taste);
		out.close();
	}

}
