import java.util.*;
import java.io.*;
public class zsilverp1 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("planting.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
		int fields=Integer.parseInt(br.readLine());
		ArrayList<Integer>[] edge=new ArrayList[fields];
		for(int i=0; i<fields; i++) {
			edge[i]=new ArrayList<>();
		}
		int[] arr=new int[fields];
		for(int i=0; i<fields-1; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken())-1;
			arr[a]++;
			arr[b]++;
			edge[a].add(b);
			edge[b].add(a);
		}
		/*int start=0;
		for(int i=0; i<fields; i++) {
			if(arr[i]==1) {
				start=i;
				break;
			}
		}
		int pos=0;
		int[] count=new  int[fields+1];
 		LinkedList<Integer> list=new LinkedList<>();
		list.add(start);
		count[0]=1;
		boolean[] visit=new boolean[fields];
		
		visit[start]=true;
		while(!list.isEmpty()) {
			int a=list.size();
			int nodes=0;
			for(int j=0; j<a; j++) {
				int n=list.removeFirst();
				ArrayList<Integer> al=edge[n];
				int s=al.size();
				for(int i=0; i<s; i++) {
					if(visit[al.get(i)]==true) {
						continue;
					}
					list.add(al.get(i));
					visit[al.get(i)]=true;
					nodes++;
				}
			}
			pos++;
			count[pos]=nodes+count[pos-1];
		}
		int ans=count[fields-1]-count[fields-4];
		if(count[fields]==0){
			for(int i=fields; i>-1; i--) {
				if(count[i]>0) {
					if(i-3<0) {
						ans=count[i];
					}
					else {
						ans=count[i]-count[i-3];
					}
				}
			}
		}
		*/
		int ans=0;
		for(int i=0; i<fields;i++) {
			ans=Math.max(ans, edge[i].size());
		}
		out.println(ans+1);
		out.close();
	}

}
