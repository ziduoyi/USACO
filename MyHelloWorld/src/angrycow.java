import java.io.*;
import java.util.*;

public class angrycow {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("angry.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N =Integer.parseInt(st.nextToken());
		int K =Integer.parseInt(st.nextToken());
		
		int[] x = new int[N];
		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			x[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(x);
		int l=0,r=x[N-1];
		int R = 0;
		if(!isWorkable(x,N,K,0)){
			while(l<r){
				int m = (l+r)/2;
				if(isWorkable(x,N,K,m))
					r=m-1;
				else
					l=m+1;
			}
			if(isWorkable(x,N,K,l)){
				R=l;
				if(isWorkable(x,N,K,l-1))
					R=l-1;
			}
			if(R%2>0)
				R=R/2+1;
			else
				R=R/2;
		}
		
		out.println(R);
		out.close();
	}

	static boolean isWorkable(int[] x, int N, int K, int D){
		int min=x[0];

		for(int i=1;i<N;i++){
			if(x[i]-min<=D)
				continue;
			min=x[i];
			if(--K<0)
				return false;
		}
		return true;
	}
}
