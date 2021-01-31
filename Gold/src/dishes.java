import java.io.*;
import java.util.*;
public class dishes {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("dishes.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dishes.out")));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int min =Integer.MAX_VALUE;
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		int ans=1;
		ArrayList<Integer> a=new ArrayList<Integer>();
		a.add(arr[0]);
		list.add(a);
		for(int i=1; i<N; i++) {
			int num = arr[i];
			int s=list.size();
			if(num<min && min!=Integer.MAX_VALUE)
				break;
			boolean b=false;
			for(int j=0; j<s; j++) {
				ArrayList<Integer> curr = list.get(j);
				if(curr.get(curr.size()-1)>num) {
					if(curr.get(0)>num) {
						curr.add(0, num);
						list.remove(j);
						list.add(j,curr);
					}
					else {
						if(list.get(0).get(0)<min&& min!=Integer.MAX_VALUE) {
							b=true;
							break;
						}
						for(int k=0; k<j; k++) {
							list.remove(0);
						}
						int pos = Collections.binarySearch(curr, num);
						pos++;
						pos*=-1;
						for(int k=0; k<pos; k++) {
							if(k==pos-1)
								min=curr.remove(0);
							else	
								curr.remove(0);
						}
						list.remove(0);
						curr.add(0,num);
						list.add(0, curr);
					}
					break;
				}
				if(curr.get(curr.size()-1)<num && j == s-1) {
					ArrayList<Integer> al= new ArrayList<>();
					al.add(num);
					list.remove(j);
					list.add(curr);
					list.add(al);
					break;
				}
			}
			if(b==true)
				break;
			ans =i+1;
		}
		out.println(ans);
		out.close();
	}

}
