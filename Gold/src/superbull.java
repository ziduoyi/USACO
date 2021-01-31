import java.io.*;
import java.util.*;
public class superbull {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("superbull.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("superbull.out")));
		int num=Integer.parseInt(br.readLine());
		int[] arr=new int[num];
		for(int i=0; i<num; i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}	
		int[][] edges = new int[num][num];
		for(int i=0;i<num-1;i++) {
			for(int j=i+1;j<num;j++) {
				edges[i][j]=arr[i]^arr[j];
				edges[j][i]=edges[i][j];
			}
		}
		boolean[] visited=new boolean[num];
		int[] dist = new int[num];
		dist[0]=Integer.MAX_VALUE;
		for(int i=0; i<num-1; i++) {
			int w=0;
			int p=-1;
			for(int j=0;j<num;j++) {
				if(!visited[j] && w<dist[j]) {
					p=j;
					w=dist[j];
				}
			}
			visited[p]=true;
			
			//edge relaxation
			for(int j=0;j<num;j++) {
				if(!visited[j] && dist[j]<edges[p][j])
					dist[j]=edges[p][j];
			}
		}
		long ans=0;
		for(int i=1; i<num; i++) {
			ans+=dist[i];
		}
		out.println(ans);
		out.close();
	}

}
