import java.io.*;
import java.util.*;
public class art2 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("art2.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("art2.out")));
		int N=Integer.parseInt(br.readLine());
		int[][] arr=new int[N][2];
		for(int i=0; i<N; i++) {
			Arrays.fill(arr[i], -1);
		}
		ArrayList<Integer> zero=new ArrayList<>();
		Map<Integer, Integer> map=new HashMap<>();
		int a=0;
		for(int i=0; i<N; i++) {
			int n=Integer.parseInt(br.readLine());
			if(n==0) {
				zero.add(i);
				continue;
			}
			if(!map.containsKey(n)) {
				map.put(n, a);
				arr[a][0]=i;
				arr[a][1]=i;
				a++;
			}
			else {
				int pos=map.get(n);
				arr[pos][1]=i;
			}
		}
		boolean[] visit=new boolean[a];
		int max=0;
		int c=0;
		for(int i=0; i<a; i++) {
			if(arr[i][0]<zero.get(c)&&arr[i][1]>zero.get(c)) {
				out.println(-1);
				out.close();
			}
			else {
				if(arr[i][0]>c) {
					if(c==zero.size()-1)
						break;
					else {
						c++;
					}
				}
			}
		}
		for(int i=0; i<a; i++) {
			if(visit[i]==true)
				continue;
			visit[i]=true;
			int dep=1;
			LinkedList<Integer> list=new LinkedList<>();
			list.add(i);
			list.add(arr[i][1]);
			while(!list.isEmpty()) {
				int z=list.size();
				for(int k=0; k<z;  k+=2) {
					int s=list.removeFirst();
					int j=s;
					int l=list.removeFirst();
					while(j<l&&s<a) {
						if(visit[s]!=true) {
							list.add(arr[s][0]);
							list.add(arr[s][1]);
							visit[s]=true;
						}
						s++;
						j=arr[s][0];
					}
				}
				dep++;
			}
			max=Math.max(dep, max);
		}
		out.println(max);
		out.close();
	}

}
