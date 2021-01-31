import java.io.*;
import java.util.*;
public class silverp1 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("convention.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int num=Integer.parseInt(st.nextToken());
		int bus=Integer.parseInt(st.nextToken());
		int cap=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		int[] arr=new int[num];
		for(int i=0; i<num; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int l=0;
		int r=1000000000;
		int min=Integer.MAX_VALUE;
		while(l+1<r) {
			int mid=(l+r)/2;
			boolean b=true;
			int con=1;
			int tol=0;
			int am=1;
			for(int i=1; i<num; i++) {
				am++;
				tol+=arr[i]-arr[i-1];
				if(am>cap||tol>mid) {
					con++;
					tol=0;
					am=1;
				}
			}
			if(con>bus) {
				b=false;
			}
			if(b==true) {
				min=Math.min(mid, min);
				r=mid;
			}
			else {
				l=mid;
			}
		}
		out.println(min);
		out.close();
	}

}
