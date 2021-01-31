import java.io.*;
import java.util.*;
public class sort {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("sort.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
		int N=Integer.parseInt(br.readLine());
		int[] orig=new int[N];
		int[] sort=new int[N];
		for(int i=0; i<N; i++)orig[i]=Integer.parseInt(br.readLine());
		sort=orig.clone();
		Arrays.sort(sort);
		int[] count=new int[N];
		ArrayList<Integer> know=new ArrayList<>();
		ArrayList<Integer> see=new ArrayList<>();
		for(int i=0; i<N; i++) {
			if(i!=0)
				count[i]=count[i-1]+1;
			else
				count[i]=1;
			int n1=orig[i];
			int n2=sort[i];
			int old=0;
			if(n1==n2&&i==0)
				count[i]=0;
			if(n1==n2) {
				continue;
			}
			int check=Collections.binarySearch(know, n2);
			if(check>=0) {
				count[i]--;
				know.remove(check);
			}
			else {
				check=Collections.binarySearch(see, n2);
				if(check<0) {
					check++;
					check*=-1;
				}
				old=check;
				see.add(old, n2);
			}
			check=Collections.binarySearch(see, n1);
			if(check>=0) {
				count[i]--;
				see.remove(check);
			}
			else {
				check=Collections.binarySearch(know, n1);
				if(check<0) {
					check++;
					check*=-1;
				}
				know.add(check, n1);
			}
		}
		int max=1;
		for(int i=0 ; i<N; i++)
			max=Math.max(max, count[i]);
		out.println(max);
		out.close();
		
	}

}
