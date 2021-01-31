import java.io.*;
import java.util.*;
public class sightsee {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int[] values = new int[L];
		for(int i=0; i<L; i++)
			values[i] = Integer.parseInt(br.readLine());
		int[][] edges = new int[P][3];
		for(int i=0; i<P; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i][0] = Integer.parseInt(st.nextToken())-1;
			edges[i][1] = Integer.parseInt(st.nextToken())-1;
			edges[i][2] = Integer.parseInt(st.nextToken());
		}
		float l = 0;
		float r = 1000000;
		while(l<=r) {
			float m = (l+r)/2;
			if(m==l|| m==r)
				break;
			float[][] paths = new float[P][3];
			for(int i=0; i<P; i++) {
				paths[i][0] = edges[i][0];
				paths[i][1] = edges[i][1];
				paths[i][2] = m*edges[i][2] - values[edges[i][1]];
			}
			float[] dist = new float[L];
			for(int i=0; i<L; i++) {
				for(int j = 0; j<P; j++) {
					int start = (int) paths[j][0];
					int tar = (int) paths[j][1];
					float cost = paths[j][2];
					if(dist[start] + cost < dist[tar])
						dist[tar] = dist[start] + cost;
				}
			}
			boolean isnegative = false;
			for(int i=0; i<P; i++) {
				int start = (int) paths[i][0];
				int tar = (int) paths[i][1];
				float cost = paths[i][2];
				if(dist[start] + cost < dist[tar]) {
					isnegative = true;
					break;
				}
			}
			if(isnegative) 
				l= m;
			else
				r = m;
		}
		System.out.printf("%.2f", l);
	}

}
