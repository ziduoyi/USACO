import java.io.*;
import java.util.*;
public class network {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int n = Integer.parseInt(br.readLine());
	    long[][] arr = new long[n][2];
	    for(int i=0; i<n; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	arr[i][0] = Long.parseLong(st.nextToken());
	    	arr[i][1] = Long.parseLong(st.nextToken());
	    }
	    Arrays.sort(arr, new Comparator<long[]>() {
			@Override
			public int compare(long[] arg0, long[] arg1) {
				// TODO Auto-generated method stub
				return (int)arg0[0]-(int)arg1[0];
			}	    	
	    });
	    boolean[] visit = new boolean[n];
	    PriorityQueue<long[]> que = new PriorityQueue<>(new Comparator<long[]>() {
			@Override
			public int compare(long[] arg0, long[] arg1) {
				// TODO Auto-generated method stub
				if(arg0[0]<arg1[0])return -1;
				return 1;
			}	    	
	    });
	    long ans = 0;
	    que.add(new long[] {0,0});
	    while(!que.isEmpty()) {
	    	long[] use = que.poll();
	    	long cost = use[0];
	    	int tar = (int)use[1];
	    	if(!visit[tar]) {
	    		visit[tar] = true;
	    		ans += cost;
	    		int i = tar+1;
	    		while((i<n)&&(arr[tar][0]+50>=arr[i][0])) {
	    			if(!visit[i])
	    				que.add(new long[] {(arr[i][0]-arr[tar][0])*(arr[i][0]-arr[tar][0])+(arr[i][1]-arr[tar][1])*(arr[i][1]-arr[tar][1]), i});
	    			i++;
	    		}
	    		if((i<n)) {
	    			long comp = arr[i][0];
	    			while((i<n)&&(comp==arr[i][0])) {
	    				if(!visit[i])
	    					que.add(new long[] {(arr[i][0]-arr[tar][0])*(arr[i][0]-arr[tar][0])+(arr[i][1]-arr[tar][1])*(arr[i][1]-arr[tar][1]), i});
	    				i++;
	    			}
	    		}
	    		
	    		i = tar-1;
	    		while((i>-1)&&(arr[tar][0]-50<=arr[i][0])) {
	    			if(!visit[i])
	    				que.add(new long[] {(arr[i][0]-arr[tar][0])*(arr[i][0]-arr[tar][0])+(arr[i][1]-arr[tar][1])*(arr[i][1]-arr[tar][1]), i});
	    			i--;
	    		}
	    		if((i>-1)) {
	    			long comp = arr[i][0];
	    			while((i>-1)&&(comp==arr[i][0])) {
	    				if(!visit[i])
	    					que.add(new long[] {(arr[i][0]-arr[tar][0])*(arr[i][0]-arr[tar][0])+(arr[i][1]-arr[tar][1])*(arr[i][1]-arr[tar][1]), i});
	    				i--;
	    			}
	    		}
	    		
	    	}
	    }
	    out.write(ans+"\n");
	    out.flush();
	    out.close();
	}

}
