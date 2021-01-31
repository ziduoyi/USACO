import java.io.*;
import java.util.*;
public class sleepy {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("sleepy.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
		int N = Integer.parseInt(br.readLine());
		int[] arr =new int[N];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		int l=0;
		int r = arr.length;
		while(l<=r) {
			int mid= (l+r)/2;
			ArrayList<Integer> al= new ArrayList<>();
			for(int i = arr.length-mid; i<arr.length; i++) {
				al.add(arr[i]);
			}
			int s =al.size();
			boolean b=false;
			for(int i=0; i<s-1; i++) {
				if(al.get(i)>al.get(i+1)) {
					b=true;
					break;
				}
			}
			if(b==true) {
				r=mid-1;
			}
			else {
				l=mid+1;
			}
		}
		int ans = N -r;
		ArrayList<Integer> list=new ArrayList<>();
		for(int i = ans; i<arr.length; i++) {
			list.add(arr[i]);
		}
		out.println(ans);
		for(int i=0; i<ans; i++) {
			int num = arr[i];
			int pos = Collections.binarySearch(list, num);
			pos++;
			pos*=-1;
			list.add(pos,num);
			out.print(ans-i+pos-1);
			if(i!=ans-1)
				out.print(" ");
		}
		out.close();
	}

}
