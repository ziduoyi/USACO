import java.io.*;
import java.util.*;

public class helpcross {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("helpcross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int chick=Integer.parseInt(st.nextToken());
		int cow=Integer.parseInt(st.nextToken());
		int[] chtime= new int[chick];
		for(int i=0; i<chick; i++) {
			chtime[i]=Integer.parseInt(br.readLine());
			
		}
		int[][] cotime=new int[cow][2];
		for(int i=0; i<cow; i++) {
			StringTokenizer st1=new StringTokenizer(br.readLine());
			cotime[i][0]=Integer.parseInt(st1.nextToken());
			cotime[i][1]=Integer.parseInt(st1.nextToken());
		}
		Arrays.sort(chtime);
		Arrays.sort(cotime,(a,b)->{if(a[1]!=b[1]) 
									return a[1]-b[1];
									else return a[0]-b[0];
									});
		int i=0;
		int j=0;
		int pair=0;
		while(i<chick&&j<cow) {
			if(chtime[i]>=cotime[j][0]&&chtime[i]<=cotime[j][1]) {
				cotime[j][0]=0;
				cotime[j][1]=0;
				i++;
				j++;
				pair++;
				continue;
			}
			if(chtime[i]<cotime[j][0]) {
				for(int k=j+1; k<cow; k++) {
					if(chtime[i]>=cotime[k][0]&&chtime[i]<=cotime[k][1]) {
						pair++;
						cotime[k][0]=0;
						cotime[k][1]=0;
						break;
					}
				}
				i++;
				continue;
			}
			if(chtime[i]>cotime[j][1]) {
				j++;
				continue;
			}
		}
	
		
		out.println(pair);
		out.close();
	}

}
