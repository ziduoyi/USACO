import java.util.*;
import java.io.*;
public class cardgame {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("cardgame.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));
		int num=Integer.parseInt(br.readLine());
		int[] cow=new int[num];
		int[] a1=new int[num/2];
		for(int i=0; i<num/2; i++) {
			a1[i]=Integer.parseInt(br.readLine());
		}
		int[] a2=new int[num/2];
		for(int i=0; i<num/2; i++) {
			a2[i]=Integer.parseInt(br.readLine());
		}
		Arrays.sort(a1);
		Arrays.sort(a2);
		int a=0;
		int b=0;
		int c=0;
		for(int i=1; i<num*2+1; i++) {
			if(a<num/2) {
				if(i==a1[a]) {
					a++;
					continue;
				}
			}
			if(b<num/2) {
				if(i==a2[b]) {
					b++;
					continue;
				}
			}
			if(c+1>num) {
				break;
			}
			cow[c]=i;
			c++;
		}
		for(int i=0; i<num/2; i++) {
			int save=cow[i];
			cow[i]=cow[num-i-1];
			cow[num-i-1]=save;
		}
		int[] cow1=new int[num/2];
		int[] cow2=new int[num/2];
		int z=0;
		for(int i=0; i<num/2; i++) {
			cow1[i]=cow[z];
			z++;
		}
		Arrays.sort(cow1);
		for(int i=0; i<num/2; i++) {
			cow2[i]=cow[z];
			z++;
		}
		Arrays.sort(cow2);
		int pts=0;
		int i=num/2-1;
		int j=num/2-1;
		while(i>-1&&j>-1){
			int v1=a1[i];
			int v2=cow1[j];
			if(v1<v2) {
				pts++;
				i--;
				j--;
			}
			else {
				i--;
			}
		}
		i=0;
		j=0;
		while(i<num/2&&j<num/2){
			int v1=a2[i];
			int v2=cow2[j];
			if(v1>v2) {
				pts++;
				i++;
				j++;
			}
			else {
				i++;
			}
		}
		out.println(pts);
		out.close();
	}

}
