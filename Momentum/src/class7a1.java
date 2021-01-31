import java.io.*;
import java.util.*;
public class class7a1 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("notlast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("notlast.out")));
		int entries=scanner.nextInt();
		String[] names=new String[entries];
		int[] nums=new int[entries];
		String[] cow=new String[entries];
		int[] values=new int[entries];
		boolean same=false;
		int a=0;
		for(int i=0; i<entries; i++) {
			cow[i]=scanner.next();
			for(int j=0; j<names.length; j++) {
				if(cow[i]==names[j])
					same=true;
					break;
			}
			if(same==false) {
				names[a]=cow[i];
				a++;
			}
			values[i]=scanner.nextInt();
		}
		for(int i=0; i<entries; i++) {
			int pos=0;
			for(int j=0; j<names.length; j++) {
				if(names[j]==cow[i]) {
					pos=j;
					break;
				}
			}
			nums[pos]+=values[i];
		}
		for(int i=0; i<nums.length-1; i++) {
			for(int j=0; i<nums.length-1-i; j++) {
				if(nums[j+1]<nums[j]) {
					int save=nums[j];
					nums[j]=nums[j+1];
					nums[j+1]=save;
					String s=names[j];
					names[j]=names[j+1];
					names[j+1]=s;
				}
			}
		}
		int min=0;
		for(int i=0; i<nums.length; i++) {
			if(nums[i]==0)
				continue;
			min=nums[i];
			if(i+1<nums.length) {
				if(nums[i]==nums[i+1]) {
					out.println("tied");
					out.close();
					return;
				}
				else {
					out.println(names[i+1]);
				}
			}
		}

	}

}
