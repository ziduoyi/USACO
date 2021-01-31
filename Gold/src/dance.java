import java.io.*;
import java.util.*;
public class dance {

	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int N = Integer.parseInt(st.nextToken());
	    long K = Integer.parseInt(st.nextToken());
	    long M = Integer.parseInt(st.nextToken());
	    TreeSet<int[]>[] edges = new TreeSet[N];
	    for(int i=0; i<N; i++)
	    	edges[i] = new TreeSet<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					return o1[0]-o2[0];
				}
	    		
	    	});
	    for(int i=0; i<K; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	int a = Integer.parseInt(st.nextToken())-1;
	    	int b = Integer.parseInt(st.nextToken())-1;
	    	edges[a].add(new int[] {i,b});
	    	edges[b].add(new int[] {i,a});
	    }
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    for(int i=0; i<N; i++) {
	    	int node = i;
	    	long j=0;
	    	HashSet<Integer> set = new HashSet<>();
	    	while(j<=M) {
	    		TreeSet<int[]> al = edges[node];
	    		if(al.size()==0) {
	    			set.add(node);
	    			break;
	    		}
	    		if(al.ceiling(new int[] {(int) (j%K),1})!=null) {
	    			int[] next = al.ceiling(new int[] {(int) (j%K),1});
	    			set.add(node);
	    			j += next[0]+1-j%K;	
	    			node = next[1];
	    		}
	    		else {
	    			if(set.contains(node))break;
	    			set.add(node);
	    			j+=K-(j%K);
	    			int[] next = al.ceiling(new int[] {(int) (j%K),1});
	    			j += next[0]+1-j%K;	
	    			node = next[1];
	    		}
	    	}
	    	out.write(set.size()+"\n");
	    }
	    out.flush();
	    out.close();
	}

}
