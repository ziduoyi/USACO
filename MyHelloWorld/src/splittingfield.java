import java.io.*;
import java.util.*;
public class splittingfield {

	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("split.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("split.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());

		int[][] arr=new int[N][2];

		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
		}
		long area1=0;
		int[] bound = new int[4];
		getBoundary(bound,arr,0,N);
		area1 = (bound[1]-bound[0]) * (bound[3]-bound[2]);
		
		long res=0;
		Arrays.sort(arr, (a,b)->(a[0]-b[0]));
		for(int i=1;i<N;i++){
			getBoundary(bound,arr,0,i);
			long areap1 = (bound[1]-bound[0]) * (bound[3]-bound[2]);
			getBoundary(bound,arr,i,N);
			long areap2 = (bound[1]-bound[0]) * (bound[3]-bound[2]);
			res=Math.max(res, area1 - areap1-areap2);
		}
		
//		Arrays.sort(arr, (a,b)->(a[1]-b[1]));
//		for(int i=1;i<N;i++){
//			getBoundary(bound,arr,0,i);
//			long areap1 = (bound[1]-bound[0]) * (bound[3]-bound[2]);
//			getBoundary(bound,arr,i,N);
//			long areap2 = (bound[1]-bound[0]) * (bound[3]-bound[2]);
//			res=Math.max(res, area1 - areap1-areap2);
//		}
		
		out.println(res);			
		out.close();
	}
//129671255810
//5711838522
	
	static void getBoundary(int[] bound, int[][] arr, int x, int y){
		bound[0]=bound[1]=arr[x][0];
		bound[2]=bound[3]=arr[x][1];
		for(int i=x+1;i<y;i++){
			bound[0]=Math.min(bound[0], arr[i][0]);
			bound[1]=Math.max(bound[1], arr[i][0]);
			bound[2]=Math.min(bound[2], arr[i][1]);
			bound[3]=Math.max(bound[3], arr[i][1]);
		}
		
	}
}