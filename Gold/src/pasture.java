import java.io.*;
import java.util.*;
public class pasture {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int N = Integer.parseInt(br.readLine());
	    int[][] arrx = new int[N][2];
	    int[][] arry = new int[N][2];
	    for(int i=0; i<N; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int x = Integer.parseInt(st.nextToken());
	    	int y = Integer.parseInt(st.nextToken());
	    	arrx[i][0] = x;
	    	arrx[i][1] = y;
	    	arry[i][0] = x;
	    	arry[i][1] = y;
	    }
	    
	    Arrays.sort(arrx, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				if(arg0[0]!=arg1[0])
					return arg0[0] - arg1[0];
				return arg0[1] - arg1[1];
			}
	    });
	    Arrays.sort(arry, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				if(arg0[1]!=arg1[1])
					return arg0[1] - arg1[1];
				return arg0[0] - arg1[0];
			}
	    });
	    int ans = N+1;
	    for(int i=0; i<N; i++) {
	    	for(int j=i+1; j<N; j++) {
    			int len = arrx[j][0] - arrx[i][0];
    			int wid = Math.abs(arrx[j][1]-arrx[i][1]);
	    		if(len>=wid) {
	    			TreeSet<Integer> valy = new TreeSet<>();
	    			for(int k=i; k<=j; k++) 
	    				valy.add(arrx[k][1]);
	    			int curr = valy.pollFirst(); 
	    			valy.add(curr);
	    			while(curr+(len-wid)<Math.min(arrx[i][1], arrx[j][1])) {
	    				valy.remove(curr);
	    				curr = valy.ceiling(curr);
	    			}
	    			curr = valy.pollLast();
	    			valy.add(curr);
	    			while(curr-(len-wid)>Math.max(arrx[i][1], arrx[j][1])) {
	    				valy.remove(curr);
	    				curr = valy.floor(curr);
	    			}
	    			int p1 = valy.pollFirst();
	    			int p2 = p1 + len;
	    			valy.add(p1);
	    			while(p1!=arrx[i][1]&&p1!=arrx[j][1]) {
	    				int dif1 = valy.ceiling(p1+1)-p1;
	    				int dif2 = 2000000000;
	    				if(valy.ceiling(p2+1)!=null)
	    					dif2 = valy.ceiling(p2+1)-p2;
	    				ans++;
	    				if(dif1>=dif2) {
	    					p1+=dif2;
	    					p2+=dif2;
	    				}
	    				else {
	    					p1+=dif1;
	    					p2+=dif2;
	    				}
	    			}
	    			ans++;
	    		}
	    		else {
	    			int save = wid;
	    			wid = len;
	    			len = save;
	    			int i2 = -1;
	    			int j2 = -1;
	    			for(int k=0; k<N; k++) {
	    				if(arrx[i][0] == arry[k][0]) {
	    					i2 = k;
	    					break;
	    				}
	    			}
	    			for(int k=0; k<N; k++) {
	    				if(arrx[j][0] == arry[k][0]) {
	    					j2 = k;
	    					break;
	    				}
	    			}
	    			if(i2>j2) {
	    				save = i2;
	    				i2 = j2;
	    				j2 = save;
	    			}
	    			TreeSet<Integer> valx = new TreeSet<>();
	    			for(int k=i2; k<=j2; k++) 
	    				valx.add(arry[k][0]);
	    			int curr = valx.pollFirst(); 
	    			valx.add(curr);
	    			while(curr+(len-wid)<Math.min(arry[i2][0], arry[j2][0])) {
	    				valx.remove(curr);
	    				curr = valx.ceiling(curr);
	    			}
	    			curr = valx.pollLast();
	    			valx.add(curr);
	    			while(curr-(len-wid)>Math.max(arry[i2][0], arry[j2][0])) {
	    				valx.remove(curr);
	    				curr = valx.floor(curr);
	    			}
	    			int p1 = valx.pollFirst();
	    			int p2 = p1 + len;
	    			valx.add(p1);
	    			while(p1!=arry[i2][0]&&p1!=arry[j2][0]) {
	    				int dif1 = valx.ceiling(p1+1)-p1;
	    				int dif2 = 2000000000;
	    				if(valx.ceiling(p2+1)!=null)
	    					dif2 = valx.ceiling(p2+1)-p2;
	    				ans++;
	    				if(dif1>=dif2) {
	    					p1+=dif2;
	    					p2+=dif2;
	    				}
	    				else {
	    					p1+=dif1;
	    					p2+=dif2;
	    				}
	    			}
	    			ans++;
	    		}
	    	}
	    }
	    System.out.println(ans);
	}

}
