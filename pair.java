import java.io.*;
import java.util.*;
public class pair {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("pairup.in"));
		int num=Integer.parseInt(br.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));
		int[][] milk=new int[num][2];
		for(int i=0; i<num; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			milk[i][1]= Integer.parseInt(st.nextToken());
			milk[i][0]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(milk,(a,b)->a[0]-b[0]);
		int time=0;
		int l=0;
		int r=num-1;
		while(l<=r) {
			int sum=milk[l][0]+milk[r][0];
			if(sum>time)
				time=sum;
			if(milk[l][1]-milk[r][1]==0) {
				l++;
				r--;
				continue;
			}
			if(milk[l][1]>milk[r][1]) {
				milk[l][1]-=milk[r][1];
				r--;
			}
			else {
				milk[r][1]-=milk[l][1];
				l++;
			}
		}
		/*int r=milk.size()-1;
		while(l<r) {
			int sum=milk.get(l)+milk.get(r);
			if(sum>time) {
				time=sum;
			}
			l++;
			r--;
		}*/
		out.println(time);
		out.close();
	}

}
