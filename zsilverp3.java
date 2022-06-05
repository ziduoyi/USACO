import java.util.*;
import java.io.*;
public class zsilverp3 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("mountains.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
		int num=Integer.parseInt(br.readLine());
		int[][] arr=new int[num][2];
		boolean[] boo=new boolean[num];
		for(int i=0; i<num; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr,(a,b)->{if(a[1]!=b[1]) 
			return b[1]-a[1];
			else return b[0]-a[0];
			});
		for(int i=0; i<num-1; i++) {
			if(boo[i]==true)
				continue;
			int x=arr[i][0];
			int y=arr[i][1];
			for(int j=i+1; j<num; j++) {
				if(boo[j]==true)
					continue;
				int dif=y-arr[j][1];
				if(dif>=Math.abs(x-arr[j][0]))
					boo[j]=true;
			}
		}
		int ans=0;
		for(int i=0; i<num; i++) {
			if(boo[i]==false)
				ans++;
		}
		out.println(ans);
		out.close();
	}

}
