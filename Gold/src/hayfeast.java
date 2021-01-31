import java.io.*;
import java.util.*;
public class hayfeast {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("hayfeast.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		long M=Long.parseLong(st.nextToken());
		int[][] arr=new int[N][2];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			int[] temp=new int[2];
			temp[0]=Integer.parseInt(st.nextToken());
			temp[1]=Integer.parseInt(st.nextToken());
			arr[i]=temp.clone();
		}
		int i=0;
		int j=0;
		int min=Integer.MAX_VALUE;
		long sum=0;
		LinkedList<int[]> list=new LinkedList<>();
		ArrayList<Integer> sss=new ArrayList<>();
		while(i<N && j<N) {
			if(sum<M) {
				int add=arr[j][0];
				int spice=arr[j][1];
				sum+=add;
				int pos=Collections.binarySearch(sss, spice);
				if(pos<0) {
					pos++;
					pos*=-1;
				}
				sss.add(pos, spice);
				j++;
				if(sum>=M)
					min=Math.min(min, sss.get(sss.size()-1));
			}
			else {
				int sub=arr[i][0];
				int spice=arr[i][1];
				sum-=sub;
				int pos=Collections.binarySearch(sss, spice);
				sss.remove(pos);
				i++;
				if(sum>=M)
					min=Math.min(min, sss.get(sss.size()-1));
			}
		}
		out.println(min);
		out.close();
	}
}
