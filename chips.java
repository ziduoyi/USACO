import java.io.*;
import java.util.*;
public class chips {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int m = Integer.parseInt(st.nextToken());
	    	long[][] arr = new long[n][6];
	    	for(int j=0; j<n; j++) {
	    		st = new StringTokenizer(br.readLine());
	    		for(int k=0; k<6; k++)
	    			arr[j][k] = Long.parseLong(st.nextToken());
	    	}
	    	double[] area = new double[n];
	    	for(int j=0; j<n; j++) {
	    		area[j] = Math.abs((arr[j][0]*arr[j][3])+(arr[j][2]*arr[j][5])+(arr[j][4]*arr[j][1])-(arr[j][1]*arr[j][2])-
	    				(arr[j][3]*arr[j][4])-(arr[j][5]*arr[j][0]))/(double)2;
	    	}
	    	for(int j=0; j<m; j++) {
	    		st = new StringTokenizer(br.readLine());
	    		int x = Integer.parseInt(st.nextToken());
	    		int y = Integer.parseInt(st.nextToken());
	    		int r = Integer.parseInt(st.nextToken());
	    		int k = Integer.parseInt(st.nextToken());
	    		ArrayList<Double> list = new ArrayList<>();
	    		for(int l=0; l<n; l++) {
	    			boolean b = true;
	    			for(int o = 0; o<3; o++) {
	    				double dist = Math.sqrt((arr[l][o*2]-x)*(arr[l][o*2]-x)+(arr[l][o*2+1]-y)*(arr[l][o*2+1]-y));
	    				if(dist>r) {
	    					b = false;
	    					break;
	    				}
	    			}
	    			if(b)
	    				list.add(area[l]);
	    		}
	    		Collections.sort(list);
	    		double ans = 0;
	    		for(int l=list.size()-1; l>Math.max(-1, list.size()-1-k); l--) {
	    			ans+=list.get(l);
	    		}
	    		System.out.printf("%.2f", ans);
	    		System.out.println();
	    	}
	    }
	}

}
