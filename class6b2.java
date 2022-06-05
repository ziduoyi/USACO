import java.util.*;
import java.io.*;
public class class6b2 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("highcard.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));
		int num=scanner.nextInt();
		int[] nums=new int[2*num];
		for(int i=0; i<2*num; i++) {
			nums[i]=i+1;
		}
		int[] bessie =new int[num];
		int[] elsie =new int[num];
		for(int i=0; i<num; i++) {
			elsie[i]=scanner.nextInt(); 
			for(int j=0; j<num*2; j++) {
				if(elsie[i]==nums[j]) {
					nums[j]=0;
					break;
				}
			}
		}
		int a=0;
		for(int i=0; i<num*2; i++) {
			if(nums[i]!=0) {
				bessie[a]=nums[i];
				a++;
			}
		}
		Arrays.sort(elsie);
		int point=0;
		for(int i=0; i<num; i++) {
			if(bessie[i]>elsie[i]) {
				point++;
				continue;
			}
			else {
				for(int j=i+1; j<num; j++) {
					if(bessie[j]==-1) {
						continue;
					}
					if(bessie[j]>elsie[i]) {
						bessie[j]=-1;
						point++;
						break;
					}
				}
			}
		}
		out.print(point);
		out.close();
	}
}
