import java.util.*;
import java.io.*;
public class angry {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("angry.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num =Integer.parseInt(st.nextToken());
		int cow =Integer.parseInt(st.nextToken());
		int[] arr =new int[num];
		for(int i =0; i <num; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		int l =0;
		int r =500000000;
		int min=Integer.MAX_VALUE;
		while(l+1!=r) {
			int choose=(l+r)/2;
			int use=0;
			int tol=0;
			boolean b=true;
			for(int i=0; i<num; i++) {
				if(i==0) {
					use++;
				}
				else {
					int a=arr[i]-arr[i-1];
					if(tol+a>choose*2) {
						use++;
						tol=0;
					}
					else {
						tol+=a;
					}
					
				}
			}
			if(use<=cow) {
				r=choose;
				min=Math.min(min, choose);
			}
			else {
				l=choose;
			}
		}
		out.println(min);
		out.close();
	}

}
