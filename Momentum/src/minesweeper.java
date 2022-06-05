import java.io.*;
import java.util.*;
public class minesweeper {
	static boolean[] visit;
	static Map<Integer, Integer> mapx;
	static Map<Integer, Integer> mapy;
	static ArrayList<Integer>[] samex;
	static ArrayList<Integer>[] samey;
	static int[][] arr;
	static int[] min;
	static int[] posx;
	static int[] posy;
	static int n;
	static int k;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	br.readLine();
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	n = Integer.parseInt(st.nextToken());
	    	k = Integer.parseInt(st.nextToken());
	    	arr = new int[n][2];
	    	samex = new ArrayList[n];
	    	samey = new ArrayList[n];
	    	for(int j=0; j<n; j++) {
	    		samex[j] = new ArrayList<>();
	    		samey[j] = new ArrayList<>();
	    	}
	    	mapx = new HashMap<>();
	    	mapy = new HashMap<>();
	    	min = new int[n];
	    	posx = new int[n];
	    	posy = new int[n];
	    	int a = 0;
	    	int b = 0;
	    	LinkedList<int[]> order = new LinkedList<>();
	    	for(int j=0; j<n; j++) {
	    		st = new StringTokenizer(br.readLine());
	    		arr[j][0] = Integer.parseInt(st.nextToken());
	    		arr[j][1] = Integer.parseInt(st.nextToken());
	    		min[j] = Integer.parseInt(st.nextToken());
	    		if(mapx.containsKey(arr[j][0]))
	    			samex[mapx.get(arr[j][0])].add(j);
	    		else {
	    			mapx.put(arr[j][0], a++);
	    			samex[mapx.get(arr[j][0])].add(j);
	    		}
	    		if(mapy.containsKey(arr[j][1]))
	    			samey[mapy.get(arr[j][1])].add(j);
	    		else {
	    			mapy.put(arr[j][1], b++);
	    			samey[mapy.get(arr[j][1])].add(j);
	    		}
	    		order.add(new int[] {min[j],j});
	    	}
	    	Collections.sort(order, new Comparator<int[]>() {
				@Override
				public int compare(int[] arg0, int[] arg1) {
					// TODO Auto-generated method stub
					return arg0[0]-arg1[0];
				}
	    	});
	    	LinkedList<Integer> list = new LinkedList<>();
	    	for(int j=0; j<a; j++)
	    		Collections.sort(samex[j], new Comparator<Integer>() {
					@Override
					public int compare(Integer arg0, Integer arg1) {
						// TODO Auto-generated method stub
						return arr[arg0][1]-arr[arg1][1];
					}	
	    		});
	    	for(int j=0; j<a; j++)
	    		for(int m=0; m<samex[j].size(); m++)
	    			posx[samex[j].get(m)] = m;
	    	for(int j=0; j<b; j++)
	    		Collections.sort(samey[j], new Comparator<Integer>() {
					@Override
					public int compare(Integer arg0, Integer arg1) {
						// TODO Auto-generated method stub
						return arr[arg0][0] - arr[arg1][0];
					}
	    		});
	    	for(int j=0; j<b; j++)
	    		for(int m=0; m<samey[j].size(); m++)
	    			posy[samey[j].get(m)] = m;
	    	visit = new boolean[n];
	    	for(int j=0; j<n; j++) {
	    		int[] pos = order.removeFirst();
	    		if(!visit[pos[1]]) {
			    	visit[pos[1]] = true;
			    	recursion(pos[1]);
			    	list.add(min[pos[1]]);
	    		}
	    	}
	    	Collections.sort(list);
	    	int time = 0;
	    	while(list.size()>0) {
	    		while(list.size()>0&&list.get(0)==time)
	    			list.removeFirst();
	    		if(list.size()>0) list.removeLast();
	    		if(list.size()>0)
	    			time++;
	    	}
	    	out.write(time+"\n");
	    }
	    out.flush();
	    out.close();
	}
	static int recursion(int node) {
		ArrayList<Integer> al = samex[mapx.get(arr[node][0])];
		int s = al.size();
		int start = posx[node];
		for(int m=start+1; m<s; m++) {
			if(arr[al.get(m)][1]-arr[node][1]<=k) {
				if(visit[al.get(m)]) 
					break;
				else {
					visit[al.get(m)] = true;
					min[node] = Math.min(min[node], recursion(al.get(m)));
				}
			}
			else break;
		}
		for(int m=start-1; m>-1; m--) {
			if(arr[node][1]-arr[al.get(m)][1]<=k) {
				if(visit[al.get(m)]) 
					break;
				else {
					visit[al.get(m)] = true;
					min[node] = Math.min(min[node], recursion(al.get(m)));
				}
			}
			else break;
		}
		al = samey[mapy.get(arr[node][1])];
		s = al.size();
		start = posy[node];
		for(int m = start+1; m<s; m++) {
			if(arr[al.get(m)][0]-arr[node][0]<=k) {
				if(visit[al.get(m)]) 
					break;
				else {
					visit[al.get(m)] = true;
					min[node] = Math.min(min[node], recursion(al.get(m)));
				}
			}
			else break;
		}
		for(int m = start-1; m>-1; m--) {
			if(arr[node][0]-arr[al.get(m)][0]<=k) {
				if(visit[al.get(m)]) 
					break;
				else {
					visit[al.get(m)] = true;
					min[node] = Math.min(min[node], recursion(al.get(m)));
				}
			}
			else break;
		}
		return min[node];
	}
}
