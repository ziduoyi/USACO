import java.io.*;
import java.util.*;
public class groups {
	static int[] root;
	public static void main(String[] args)throws IOException  {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	LinkedList<int[]> list = new LinkedList<>();//pos, type, side, #
	    	int[] end = new int[n];
	    	for(int j=0; j<n; j++) {
	    		StringTokenizer st = new StringTokenizer(br.readLine());
	    		int a = Integer.parseInt(st.nextToken());
	    		int b = Integer.parseInt(st.nextToken());
	    		int c = Integer.parseInt(st.nextToken());
	    		end[j] =c;
	    		list.add(new int[] {b,0,a,j});
	    		list.add(new int[] {c,1,a,j});
	    	}
	    	Collections.sort(list, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					if(o1[0]!=o2[0])return o1[0]-o2[0];
					return o1[1]-o2[1];
				}
	    	});
	    	root = new int[n];
	    	for(int j=0; j<n; j++)root[j]=j;
	    	Set<Integer>[] set = new HashSet[2];
	    	for(int j=0; j<2; j++) set[j] = new HashSet<>();
	    	while(!list.isEmpty()) {
	    		int[] arr = list.removeFirst();
	    		if(arr[1]==0) {
	    			int m = -1;
	    			int p = 0;
	    			for(int num: set[1-arr[2]]) {
	    				if(end[num]>m) {
	    					m = end[num];
	    					p = num;
	    				}
    					int r1 = getRoot(arr[3]);
    					int r2 = getRoot(num);
    					if(r1!=r2) 
    						root[r2]= r1;
	    			}
	    			set[1-arr[2]].clear();
	    			if(m!=-1)
	    				set[1-arr[2]].add(p);
	    			set[arr[2]].add(arr[3]);
	    		}
	    		else {
	    			if(set[arr[2]].contains(arr[3]))
	    				set[arr[2]].remove(arr[3]);
	    		}
	    	}
	    	Set<Integer> ans = new HashSet<>();
	    	for(int j=0; j<n; j++)ans.add(getRoot(j));
	    	out.write(ans.size()+"\n");
	    }
	    out.flush();
	    out.close();
	}
	static int getRoot(int pos) {
		if(root[pos]==pos)return pos;
		return root[pos]=getRoot(root[pos]);
	}
}
