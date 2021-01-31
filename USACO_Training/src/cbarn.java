import java.io.*;
import java.util.*;
public class cbarn {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("cbarn.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
		int n=Integer.parseInt(br.readLine());
		int[] save=new int[n];
		int[] arr=new int[n];
		for(int i=0; i<n; i++) {
			save[i]=Integer.parseInt(br.readLine());
		}
		arr=save.clone();
		int s=0;
		for(int i=0; i<n; i++) {
			if(i==0) {
				if(arr[i]<2&&arr[arr.length-1]!=0) {
					s=i;
					break;
				}
				continue;
			}
			else {
				if(arr[i]<2&&arr[i-1]!=0) {
					s=i;
					break;
				}
			}
		}
		int b=s;
		LinkedList<Integer> list=new LinkedList<>();
		for(int i=0; i<n; i++) {
			arr[i]=save[b];
			if(arr[i]==0)
				list.add(i);
			b++;
			if(b==n)
				b=0;
		}
		int ene=0;
		int i=1;
		while(!list.isEmpty()) {
			int num=list.removeFirst();
			while(arr[i]==0) {
				i++;
			}
			arr[num]++;
			arr[i]--;
			if(arr[i]==0) {
				list.add(i);
				Collections.sort(list);
			}
			ene+=Math.pow(i-num, 2);
		}
		out.println(ene);
		out.close();
	}

}
