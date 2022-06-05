import java.util.*;
import java.io.*;
public class class5a1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner= new Scanner(new File("measurement.in"));
		PrintWriter out = new PrintWriter(new FileWriter("measurement.out"));
		int cows=scanner.nextInt();
		int[] arr=new int[cows];
		String[] arr2=new String[cows];
		int[] arr3=new int[cows];
		int[] nums=new int[cows];
		int days=0;
		String[] names= new String[cows];
		int d=0;
		for(int i=0; i<cows; i++) {
			arr[i]=scanner.nextInt();
			String s=scanner.next();
			arr2[i]=s;
			String c =scanner.next();
			if(c.charAt(0)=='+') {
				arr3[i]=c.charAt(1)-'0';
			}
			else {
				arr3[i]=c.charAt(1)-'0';
				arr3[i]*=-1;
			}
			int flag2=0;
			for(int j=0; j<names.length; j++) {
				if(s.equals(names[j])) {
					flag2++;
				}
			}
			if(flag2==0) {
				names[d]=s;
				nums[d]=1;
				d++;
			}
		}
		int len=cows;
		for(int i=0; i<cows; i++) {
			if(nums[i]!=1) {
				nums[i]=-1;
				len--;
			}
		}
		for(int i=0; i<cows-1; i++) {
			for(int j=i+1; j<cows; j++) {
				if(arr[i]>arr[j]) {
					int save=0;
					save=arr[i];
					arr[i]=arr[j];
					arr[j]=save;
					String s ="  ";
					save=arr3[i];
					arr3[i]=arr3[j];
					arr3[j]=save;
					s=arr2[i];
					arr2[i]=arr2[j];
					arr2[j]=s;
				}		
			}
		}
		int win=len;
		int lead=0;
		int leadp=0;
		int maxp=0;
		for(int i=0; i<cows; i++) {
			String cow=arr2[i];
			int pos=0;
			for(int j=0; j<len; j++) {
				if(cow.equals(names[j])) {
					pos=j;
					break;
				}
			}
			leadp=0;
			nums[pos]+=arr3[i];
			int val=0;
			for(int j=0; j<len; j++) {
				if(nums[j]>val) {
					val=nums[j];
					lead=1;
					leadp=j;
				}
				else if(nums[j]==val) {
					lead++;
				}
			}
			
			if(win!=lead) {
				maxp=leadp;
				win=lead;
				days++;
				continue;
			}
			if(leadp!=maxp) {
				maxp=leadp;
				days++;
				continue;
			}
			
		}
		out.println(days);
		out.close();
	}
}