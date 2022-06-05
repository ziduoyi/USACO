import java.io.*;
import java.util.*;
public class Circle {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		long a=System.currentTimeMillis();
		BufferedReader br=new BufferedReader(new FileReader("cbarn.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
		int num=Integer.parseInt(br.readLine());
		int[] arr=new int[num];
		for(int i=0; i<num; i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		int[] summary=new int[num];
		summary[num-1]=arr[num-1];
		for(int i=num-2; i>-1; i--) {
			summary[i]=summary[i+1]+arr[i];
		}
		int count =0;
		int first=-1;
		for(int i=0; i<num; i++) {
			if(summary[i]-(num-i)>first) {
				first=summary[i]-(num-i);
				count =i;
			}
		}
		count=num-count;
		int[] save=new int[num];
		if(first!=-1) {
			int j=num-1-count;
			for(int i=num-1;i>=0;i--) {
				save[i]=arr[j];
				j--;
				if(j==-1)
					j=num-1;
			}
			arr=save.clone();
		}
		
		/*
		boolean b=true;
		int[] save=new int[num];
		while(b==true) {
			int zeros =0;
			b=false;
			int in=0;
			for(int i=arr.length-1; i>-1; i--) {
				zeros++;
				zeros-=arr[i];
				if(zeros<0) {
					b=true;
					in=arr.length-i;
					break;
				}
			}
			if(b==true) {
				int j=num-1-in;
				for(int i=num-1;i>=0;11i--) {
					save[i]=arr[j];
					j--;
					if(j==-1)
						j=num-1;
				}
				int[] temp=arr;
				arr=save;
				save=arr;
			}
		}
		*/
		TreeSet<Integer> set=new TreeSet<>();
		for(int i=0; i<arr.length; i++) {
			if(arr[i]==0) 
				set.add(i);
		}
		long sum=0;
		while(!set.isEmpty()) {
			int dis=1;
			int pos=set.pollLast();
			int find=pos-1;
			while(arr[find]==0) {
				find--;
				dis++;
			}
			arr[pos]++;
			arr[find]--;
			if(arr[find]==0) {
				set.add(find);
			}
			sum+=dis*dis;
		}
		out.println(sum);
		out.close();
		System.out.println(System.currentTimeMillis()-a);
	}

}
