import java.io.*;
import java.util.*;
class Edge{
	int src, dst, w;
	public Edge(int s, int d, int w) {
		src=s; dst=d; this.w=w;
	}
}


public class irrigation {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("irrigation.out")));
		BufferedReader br=new BufferedReader(new FileReader("irrigation.in"));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int nums =Integer.parseInt(st.nextToken());
		int min=Integer.parseInt(st.nextToken());
		int[][] arr=new int[nums][2];
		for(int i=0; i<nums; i++) {
			st=new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
		}
		
		//TreeSet<Edge> edges = new TreeSet<>((a,b)->a.w==b.w?a.dst-b.dst:a.w-b.w);
		ArrayList<Edge> edges = new ArrayList<>();
		for(int i=0; i<nums-1; i++) {
			for(int j=i+1; j<nums; j++) {
				int a1=arr[i][0];
				int a2=arr[j][0];
				int b1=arr[i][1];
				int b2=arr[j][1];
				int sum=((a1-a2)*(a1-a2))+((b1-b2)*(b1-b2));
				if(sum>=min) {
					edges.add(new Edge(i,j,sum));
				}
			}
		}
		Collections.sort(edges,(a,b)->a.w-b.w);
		int[] root = new int[nums];
		for(int i=0;i<nums;i++)
			root[i]=i;
		long total=0;
		int count=0;
		boolean b=true;
		for(Edge e : edges) {
			int r1 = findRoot(root,e.src);
			int r2 = findRoot(root,e.dst);
			if(r1!=r2) {
				root[r2]=r1;
				root[e.src]=r1;
				root[e.dst]=r1;
				total+=e.w;
				if(++count>nums-2) {
					b=false;
					break;
				}
			}
		}
        if(b)
        	out.println(-1);
        else
        	out.println(total);
        out.close();
	}
    static int findRoot(int[] root, int n){
    	while(root[n]!=n)
    		n=root[n];
    	return n;
    }
    

}
