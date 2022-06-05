import java.io.*;
import java.util.*;
public class classl {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("power.out")));
		BufferedReader br=new BufferedReader(new FileReader("power.in"));
		long sum=0;
		int n=Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			long c=Integer.parseInt(st.nextToken());
			long d=Integer.parseInt(st.nextToken());
			long e=Integer.parseInt(st.nextToken());
			long s=1;
			long dif=0;
			long pw=1;
			while(d>1) {
				while(d>pw) {
					pw*=2;
				}
				while(pw>d) {
					pw/=2;
				}
				long old=pw;
				long parts=1;
				while(pw>e) {
					parts*=2;
					pw/=2;
				}
				long l=(long) Math.pow(c, pw);
				l%=d;
				while(parts!=1) {
					parts/=2;
					l*=l;
					l%=e;
				}
				dif+=l;
				dif%=d;
				d=d-old;
			}
			sum+=dif;
		}
		out.println(sum);
		out.close();
		
	}
}
