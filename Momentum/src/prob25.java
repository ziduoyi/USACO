import java.io.*;
import java.util.*;
public class prob25 {
	static int[] com;
	static char namec;
	static double[] posc;
	static boolean signc;
	
	static int[][] arr;
	static char[] name;
	static double[][] pos;
	static boolean[] sign;
	static double[] ans;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		com = new int[3];
		namec = ' ';
		posc = new double[2];
		signc = false;
		
		arr = new int[3][3];
		name = new char[3];
		pos = new double[3][2];
		sign = new boolean[3];
		for(int i=0; i<2; i++) {
			String str = br.readLine();
			if(str.equals("DONKEY")) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				char[] c = st.nextToken().toCharArray();
				namec = c[0];
				for(int j=0; j<3; j++)
					com[j] = Integer.parseInt(st.nextToken());
				st = new StringTokenizer(br.readLine());
				posc[0] = Double.parseDouble(st.nextToken());
				posc[1] = Double.parseDouble(st.nextToken());
				char[] d = st.nextToken().toCharArray();
				if(d[0]=='+')
					signc = true;
			}
			else {
				for(int k=0; k<3; k++) {
					StringTokenizer st = new StringTokenizer(br.readLine());
					char[] c = st.nextToken().toCharArray();
					name[k] = c[0];
					for(int j=0; j<3; j++)
						arr[k][j] = Integer.parseInt(st.nextToken());
					st = new StringTokenizer(br.readLine());
					pos[k][0] = Double.parseDouble(st.nextToken());
					pos[k][1] = Double.parseDouble(st.nextToken());
					char[] d = st.nextToken().toCharArray();
					if(d[0]=='+')
						sign[k] = true;
				}
			}
		}
		int pos = -1;
		double val = 1000000000;
		for(int i=0; i<3; i++) {
			ans = new double[2];
			double d = compare(i);
			if(d>=0) {
				System.out.print(name[i] + " (");
				System.out.printf("%.3f", ans[0]);
				System.out.print(",");
				System.out.printf("%.3f", ans[1]);
				System.out.println(")");
				if(d <val) {
					val = d;
					pos = i;
				}
			}
			else
				System.out.println(name[i]+" MISS!");
		}
		if(pos!=-1) {
			System.out.print(name[pos] +" CATCHES DONKEY ");
			System.out.printf("%.3f", val);
			System.out.println(" METERS AWAY");
		}
		else
			System.out.println("DONKEY GETS AWAY!");
		
	}
	static double compare(int node) {
		int a = arr[node][0] - com[0];
		int b = arr[node][1] - com[1];
		int c = arr[node][2] - com[2];
		if(a==0&&b==0) {
			if(c!=0)
				return -1;
			else {
				ans[0] = pos[node][0];
				ans[1] = pos[node][1];
				return 0;
			}
		}
		if(a==0) {
			double sol = -c/b;
			double y = com[0]*sol*sol+com[1]*sol+com[2];
			double dist = Math.sqrt((pos[node][0]-sol)*(pos[node][0]-sol)+(pos[node][1]-y)*(pos[node][1]-y));
			ans[0] = sol;
			ans[1] = y;
			if(signc==true&&sign[node]==true)
				if(pos[node][0]<=sol&&posc[0]<=sol)
					return dist;
			if(signc==true&&sign[node]==false)
				if(pos[node][0]>=sol&&posc[0]<=sol)
					return dist;
			if(signc==false&&sign[node]==true)
				if(pos[node][0]<=sol&&posc[0]>=sol)
					return dist;
			if(signc==false&&sign[node]==false)
				if(pos[node][0]>=sol&&posc[0]>=sol)
					return dist;
			return -1;
		}
		double sol1 = (-b+Math.sqrt(b*b-4*a*c))/(2*a);
		double sol2 = (-b-Math.sqrt(b*b-4*a*c))/(2*a);
		double y1 = com[0]*sol1*sol1+com[1]*sol1+com[2];
		double dist1 = Math.sqrt((pos[node][0]-sol1)*(pos[node][0]-sol1)+(pos[node][1]-y1)*(pos[node][1]-y1));
		double y2 = com[0]*sol2*sol2+com[1]*sol2+com[2];
		double dist2 = Math.sqrt((pos[node][0]-sol2)*(pos[node][0]-sol2)+(pos[node][1]-y2)*(pos[node][1]-y2));
		boolean[] pas1 = new boolean[2];
		boolean[] pas2 = new boolean[2];
		if(sign[node]==true&&sol1>=pos[node][0])
			pas1[0] = true; 
		if(sign[node]==true&&sol2>=pos[node][0])
			pas1[1] = true; 
		if(signc==true&&sol1>=pos[node][0])
			pas2[0] = true; 
		if(signc==true&&sol2>=pos[node][0])
			pas2[1] = true; 
		if(sign[node]==false&&sol1<=pos[node][0])
			pas1[0] = true; 
		if(sign[node]==false&&sol2<=pos[node][0])
			pas1[1] = true; 
		if(signc==false&&sol1<=pos[node][0])
			pas2[0] = true; 
		if(signc==false&&sol2<=pos[node][0])
			pas2[1] = true; 
		if(pas1[0]&&pas1[1]&&pas2[0]&&pas2[1]) {
			if(dist1<dist2) {
				ans[0] = sol1;
				ans[1] = y1;
			}
			else {
				ans[0] = sol2;
				ans[1] = y2;
			}
			return Math.min(dist1, dist2);
		}
		if(pas1[0]&&pas2[0]) {
			ans[0] = sol1;
			ans[1] = y1;
			return dist1;
		}
		if(pas1[1]&&pas2[1]) {
			ans[0] = sol2;
			ans[1] = y2;
			return dist2;
		}
		return -1;
	}
}
