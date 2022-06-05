import java.util.*;
import java.io.*;
public class twofoureight {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("248.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("248.out")));
		LinkedList<Integer> list=new LinkedList<>();
		int num=Integer.parseInt(br.readLine());
		int min=Integer.MAX_VALUE;
		boolean b=false;
		int old=0;
		for(int i=0; i<num; i++) {
			if(i!=0)
				 old=min;
			list.add(Integer.parseInt(br.readLine()));
			min=list.getLast();
			if(i!=0) {
				if(min!=old)
					b=true;
			}
		}
		if(b==false) {
			int s=(int) Math.sqrt(num);
			min+=s;
		}
		else
			min=Integer.MAX_VALUE;
		for(int i=0; i<num; i+=2) {
			int f=list.removeFirst();
			int l=list.removeFirst();
			if(f==l) {
				list.add(f+1);
			}
			else {
				list.add(Math.max(f, l));
			}
		}
		while(list.size()>2) {
			int n=list.size();
			for(int i=0; i<n-2; n--) {
				int f=list.removeFirst();
				int l=list.get(i+2);
				if(f==l)
					list.add(f+1);
				else {
					list.add(Math.max(f, l));
				}
			}
			list.removeFirst();
			list.removeFirst();
		}
		int ans=0;
		if(list.get(0)==list.get(1))
			ans=list.getFirst()+1;
		else 
			ans=Math.max(list.get(0), list.get(1));
		out.println(Math.min(ans, min));
		out.close();
	}

}
