import java.io.*;
import java.util.*;

class IEdge{
	int src, dst, w;
	public IEdge(int s, int d, int w) {
		src=s; dst=d; this.w=w;
	}
}

//USACO 2014 March Contest, Silver
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
		int[][] cost = new int[nums][nums];
		for(int i=0;i<nums-1;i++){
			for(int j=i+1;j<nums;j++){
				int a1=arr[i][0],a2=arr[j][0];
				int b1=arr[i][1],b2=arr[j][1];
				int sum = (a1-a2)*(a1-a2)+(b1-b2)*(b1-b2);
				if(sum<min) sum=Integer.MAX_VALUE;
				cost[i][j]=sum;
				cost[j][i]=sum;
			}
		}
		
		boolean[] visited = new boolean[nums];
		int[] dist = new int[nums];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0]=0;
		for(int x=0;x<nums;x++){
			int w = Integer.MAX_VALUE;
			int p=-1;
			//find next vertex
			for(int i=0;i<nums;i++){
				if(!visited[i] && dist[i]<w){
					p=i;
					w=dist[i];
				}
			}
			if(p<0){	// some vertex unreachable
				out.println(-1);
				out.close();
				return;
			}
			visited[p]=true;
			
			//edge relaxation
			for(int j=0;j<nums;j++){
				if(!visited[j])
					dist[j]=Math.min(dist[j], cost[p][j]);
			}
		}
		long res = 0;
		for(int x : dist)
			res+=x;

		out.println(res);
		out.close();
		
	}
    static int findRoot(int[] root, int n){
    	int x=n;
    	while(root[x]!=x)
    		x=root[x];
    	root[n]=x;
    	return x;
    }
    

}
