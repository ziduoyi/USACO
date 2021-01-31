import java.util.*;
import java.io.*;
public class maxcross {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("maxcross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
		int num=scanner.nextInt();
		int con=scanner.nextInt();
		int b=scanner.nextInt();
		int[] arr=new int[num];
		for(int i=0; i<b; i++) {
			int a=scanner.nextInt();
			arr[a-1]=1;
		}
		ArrayList<Integer> list=new ArrayList<>();
		int c=0;
		for(int i=0; i<num; i++) {
			if(arr[i]==0)
				c++;
			else {
				list.add(c);
				c=0;
			}
		}
		list.add(c);
		int min=Integer.MAX_VALUE;
		int i=0;
		int sum=0;
		int size=0;
		int use=0;
		while(i<list.size()) {
				sum+=list.get(i);
				if(size>0) {
					sum++;
					use++;
					size++;
				}
				else {
					size++;
				}
				i++;
					if(sum>=con) {
						if(sum==con) {
							min=Math.min(min, use);
							i++;
							sum=0;
							size=0;
							use=0;
						}
						sum-=list.get(i-size);
						size--;
						sum--;
						use--;
						if(sum<con) {
							min=Math.min(min, use+1);
							i++;
							sum=0;
							size=0;
							use=0;
						}
						if(sum==con) {
							min=Math.min(min, use);
							sum=0;
							size=0;
							use=0;
							i++;
						}
						else {
							sum-=list.get(i-size);
							size--;
							sum--;
							use--;
						}
					}
	
				continue;
			
		}
		out.println(min);
		out.close();
	}

}
