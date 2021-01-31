import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class mootube {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num=Integer.parseInt(st.nextToken());
		int q=Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] arr=new ArrayList[num];
		for(int i=0; i<num; i++) {
			arr[i]=new ArrayList<>();
		}
		for(int i=0; i<num-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken());
			arr[a].add(b);
			arr[a].add(c);
			arr[b].add(a);
			arr[b].add(c);
		}
		for(int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			int rel=Integer.parseInt(st.nextToken());
			int com=Integer.parseInt(st.nextToken())-1;
			int count=0;
			LinkedList<Integer> list=new LinkedList<>();
			boolean[] boo=new boolean[num];
			list.add(com);
			boo[com]=true;
			while(!list.isEmpty()) {
				int use=list.removeFirst();
				ArrayList<Integer> al=arr[use];
				for(int j=0; j<al.size(); j+=2) {
					int n=al.get(j);
					int r=al.get(j+1);
					if(r>=rel&&boo[n]==false) {
						boo[n]=true;
						count++;
						list.add(n);
					}
				}
			}
			out.println(count);
		}
		out.close();
	}
}
