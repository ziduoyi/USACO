import java.util.*;
import java.io.*;
public class build {
	static int x;
	static int m;
	static int p;
	static int lower;
	static int upper;
	static int[][] banned;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int n = Integer.parseInt(br.readLine());
	    ArrayList<Integer>[] list = new ArrayList[n];
	    ArrayList<int[]> possible = new ArrayList<>();
	    for(int i=0; i<n; i++)list[i] = new ArrayList<>();
	    for(int i=0; i<n; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int s = Integer.parseInt(st.nextToken());
	    	int sub = Integer.parseInt(st.nextToken());
	    	for(int j=0; j<s-1; j++) {
	    		int add = Integer.parseInt(st.nextToken());
	    		list[i].add(add-sub);
	    		sub = add;
	    	}
	    }
	    Queue<int[]> que = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				return arg0[n]-arg1[n];
			}
	    });
	    m = Integer.parseInt(br.readLine());
	    if(m==0) {
		    for(int i=0; i<n; i++)
		    	out.write(list[i].size()+1+ " ");	    	
		    out.flush();
		    out.close();
		    return;
	    }
	    banned = new int[m][n];
	    for(int i=0; i<m; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	for(int j=0; j<n; j++)
	    		banned[i][j] = Integer.parseInt(st.nextToken());
	    }
	    Arrays.sort(banned, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				for(int j=0; j<n; j++)
					if(arg0[j]!=arg1[j])
						return arg0[j]-arg1[j];
				return 0;
			} 	
	    });
	    int[] curr = new int[n];
	    for(int i=0; i<(1<<n); i++) {
	    	int[] here = new int[n+1];
	    	for(int j=0; j<n; j++) {
	    		if((i&(1<<j))==(1<<j)) {
	    			here[j] = 1;
	    			here[n]+=list[j].get(list[j].size()-1);
	    		}
	    	}
	    	que.add(here);
	    }
	    for(int i=0; i<m+2; i++) {
	    	curr = que.poll();
	    	int l = 0;
	    	int r = m-1;
	    	boolean ban = true;
	    	for(int j=0; j<n; j++) {
	    		lower = l;
	    		upper = r;
	    		p = j;
	    		x = list[j].size()-curr[j]+1;
	    		l = findFirst(l, r);
	    		lower = l;
	    		if(l!=-1)
	    			r = findLast(l,r);
	    		if(l==-1) {
	    			ban = false;
	    			break;
	    		}
	    	}
	    	if(!ban) 
	    		break;
	    	if(!que.isEmpty()) {
	    		for(int j=0; j<n; j++) {
	    			if(curr[j]>0&&list[j].size()!=curr[j]) {
	    				int[] temp = curr.clone();
	    				temp[j]+=1; 
	    				temp[n] += list[j].get(list[j].size()-temp[j]);
	    				que.add(temp);
	    			}
	    		}
	    	}
	    }
	    for(int i=0; i<n; i++)
	    	out.write(list[i].size()+1-curr[i] + " ");
	    out.write("\n");
	    out.flush();
	    out.close();
	}
	static int findFirst(int l, int r) {
		if(r>=l) {
			int mid = (l + r)/2;
			if((mid==lower||x>banned[mid-1][p])&&banned[mid][p] ==x) 
				return mid;
			else if (x > banned[mid][p])
				return findFirst(mid+1, r);
			else return findFirst(l, mid-1);
		}
		return -1;
	}
	static int findLast(int l, int r) {
		if(r>=l) {
			int mid = (l + r)/2;
			if((mid==upper||x<banned[mid+1][p])&&banned[mid][p] ==x)
				return mid;
			else if (x < banned[mid][p])
				return findLast(l, mid-1);
			else return findLast(mid+1, r);
		}
		return -1;
	}
}
