import java.util.*;
import java.io.*;
public class closing {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("closing.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int barns=Integer.parseInt(st.nextToken());
		int paths=Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] arr=new ArrayList[barns];
		for(int i=0;i<barns; i++) {
			arr[i]=new ArrayList<>();
		}
		for(int i=0;i<paths; i++) {
			st = new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken())-1;
			arr[a].add(b);
			arr[b].add(a);
		}
		int[] prev=new int[barns];
		for(int i=0;i <barns; i++) {
			prev[i]=i;
		}
		int[] order=new int[barns];
		for(int i=0; i<barns; i++) {
			order[i]=Integer.parseInt(br.readLine())-1;
		}
		for(int i=0; i<barns/2; i++) {
			int save=order[i];
			order[i]=order[barns-i-1];
			order[barns-i-1]=save;
		}
		boolean[] ans=new boolean[barns];
		boolean[] visit=new boolean[barns];
		int parts=0;
		for(int i=0; i<barns; i++) {
			parts++;
			int pos=order[i];
			visit[pos]=true;
			ArrayList<Integer> al=arr[pos];
			int n = al.size();
			for(int j =0; j <n; j++) {
				int node = al.get(j);
				if(visit[node]==false)continue;
				int p = findRoot(prev, node);
				if(prev[p]!=pos) {
					parts--;
				}
				prev[p]=pos;
				prev[node] = pos;
			}
			if(parts==1)
				ans[i]=true;
		}
		
		for(int i=barns-1; i>-1; i--) {
			if(ans[i])
				out.println("YES");
			else
				out.println("NO");
		}
		out.close();
	}
    static int findRoot(int[] connected, int n){
    	int x=n;
    	while(connected[x]!=x)
    		x=connected[x];
    	connected[n]=x;
        return x;
    }
    
    
}
