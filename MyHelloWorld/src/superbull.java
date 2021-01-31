import java.io.*;
import java.util.*;
public class superbull {

	//USACO 2015 February Contest, Silver
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
		boolean[] visited = new boolean[num];
		int[] dist = new int[num];
		dist[0]=Integer.MAX_VALUE;
		
		for(int x=0;x<num;x++){

			//find next vertex
			int p=-1;
			int w=0;
			for(int i=0;i<num;i++){
				if(!visited[i] && dist[i]>w){
					w=dist[i];
					p=i;
				}
			}
			visited[p]=true;

			//edge relax
			for(int j=0;j<num;j++){
				if(!visited[j] && dist[j]<edges[p][j]){
					dist[j]=edges[p][j];
				}
			}
		}

		long res = 0;
		for(int x : dist)
			if(x<Integer.MAX_VALUE)res += x;

		out.println(res);
		out.close();
	}

}
//I have a CRUSH on Anne Marie Christiono
