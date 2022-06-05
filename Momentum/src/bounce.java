import java.io.*;
import java.util.*;
public class bounce {

	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    Map<String, Integer> dict = new HashMap<>();
	    dict.put("N",0);
	    dict.put("S",1);
	    dict.put("E", 2);
	    dict.put("W",3);
	    Map<Integer, String> back = new HashMap<>();
	    back.put(0,"N");
	    back.put(1,"S");
	    back.put(2,"E");
	    back.put(3,"W");
	    Map<String, String> opp = new HashMap<>();
	    opp.put("S","N");
	    opp.put("N","S");
	    opp.put("W","E");
	    opp.put("E","W");
	    int[][] sub = new int[][] {{0,1},{0,-1},{1,0},{0,1}};
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int[] starP = new int[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
	    	String starD = st.nextToken();
	    	int n = Integer.parseInt(br.readLine());
	    	int[][] nodes  = new int[n][3];
	    	Map<Integer, Integer> mapx = new HashMap<>();
	    	int a = 0;
	    	Map<Integer, Integer> mapy = new HashMap<>();
	    	int b = 0;
	    	for(int j=0; j<n; j++) {
	    		st = new StringTokenizer(br.readLine());
	    		int x = Integer.parseInt(st.nextToken());
	    		int y = Integer.parseInt(st.nextToken());
	    		nodes[j][0] = x;
	    		nodes[j][1] = y;
	    		nodes[j][2] = dict.get(st.nextToken());
	    	}
	    	TreeSet<Integer>[] valx = new TreeSet[a];
	    	TreeSet<Integer>[] valy = new TreeSet[b];
	    	Map<Integer, Integer>[] pointx = new HashMap[a];
	    	Map<Integer, Integer>[] pointy = new HashMap[b];
	    	for(int j=0; j<a; j++)valx[j] = new TreeSet<>();
	    	for(int j=0; j<b; j++)valy[j] = new TreeSet<>();
	    	int[] edges = new int[4*n];
	    	for(int j=0; j<n; j++) {
	    		valx[mapx.get(nodes[j][0])].add(nodes[j][1]);
	    		pointx[mapx.get(nodes[j][0])].put(nodes[j][1], j);
	    		valy[mapy.get(nodes[j][1])].add(nodes[j][0]);
	    		pointy[mapy.get(nodes[j][1])].put(nodes[j][1],j);
	    	}
	    	/*
	    	for(int j=0; j<n; j++) {
	    		int pos = Collections.binarySearch(valx[mapx.get(nodes[j][0])],j, new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						// TODO Auto-generated method stub
						return nodes[o1][0]-nodes[o2][0];
					}
		    	});
	    		if(pos<valx[mapx.get(nodes[j][0])].size()-1) 
	    			edges[j][0] = valx[mapx.get(nodes[j][0])].get(pos+1);
	    		else
	    			edges[j][0] = -1;
	    		if(pos>0) 
	    			edges[j][1] = valx[mapx.get(nodes[j][0])].get(pos-1);
	    		else
	    			edges[j][1] = -1;
	    		pos = Collections.binarySearch(valy[mapy.get(nodes[j][1])],j,new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						// TODO Auto-generated method stub
						return nodes[o1][0]-nodes[o2][0];
					}
		    	});
	    		if(pos<valy[mapy.get(nodes[j][1])].size()-1) 
	    			edges[j][2] = valy[mapy.get(nodes[j][1])].get(pos+1);
	    		else
	    			edges[j][2] = -1;
	    		if(pos>0) 
	    			edges[j][3] = valy[mapy.get(nodes[j][1])].get(pos-1);
	    		else
	    			edges[j][3] = -1;
	    	}
	    	int pos = -1;
	    	if(starD.equals("N")) {
	    		if(!mapx.containsKey(starP[0])) {
	    			out.write(starD+" "+starP[0]+" "+starP[1]+"\n");
	    			continue;
	    		}
	    		int s = valx[mapx.get(starP[0])].size();
	    		for(int j=0; j<s; j++) {
	    			if(nodes[valx[mapx.get(starP[0])].get(j)][1]>starP[1]) {
	    				pos = valx[mapx.get(starP[0])].get(j);
	    				break;
	    			}
	    		}
	    	}
	    	if(starD.equals("S")) {
	    		if(!mapx.containsKey(starP[0])) {
	    			out.write(starD+" "+starP[0]+" "+starP[1]+"\n");
	    			continue;
	    		}
	    		int s = valx[mapx.get(starP[0])].size();
	    		for(int j=s-1; j>-1; j--) {
	    			if(nodes[valx[mapx.get(starP[0])].get(j)][1]<starP[1]) {
	    				pos = valx[mapx.get(starP[0])].get(j);
	    				break;
	    			}
	    		}
	    	}
	    	if(starD.equals("E")) {
	    		if(!mapy.containsKey(starP[1])) {
	    			out.write(starD+" "+starP[0]+" "+starP[1]+"\n");
	    			continue;
	    		}
	    		int s = valy[mapy.get(starP[1])].size();
	    		for(int j=0; j<s; j++) {
	    			if(nodes[valy[mapy.get(starP[1])].get(j)][0]>starP[0]) {
	    				pos = valy[mapy.get(starP[1])].get(j);
	    				break;
	    			}
	    		}
	    	}
	    	if(starD.equals("W")) {
	    		if(!mapy.containsKey(starP[1])) {
	    			out.write(starD+" "+starP[0]+" "+starP[1]+"\n");
	    			continue;
	    		}
	    		int s = valy[mapy.get(starP[1])].size();
	    		for(int j=s; j>-1; j--) {
	    			if(nodes[valy[mapy.get(starP[1])].get(j)][0]<starP[0]) {
	    				pos = valy[mapy.get(starP[1])].get(j);
	    				break;
	    			}
	    		}
	    	}
	    	
    		if(pos==-1) {
    			out.write(starD+" "+starP[0]+" "+starP[1]+"\n");
    			continue;
    		}
    		
	    	boolean[][] visit = new boolean[4*n][4];
	    	boolean win = false;
	    	int x = starP[0];
	    	int y = starP[1];
	    	String dir = starD;
	    	int curr = -1;
	    	while(curr==-1||!visit[curr][dict.get(dir)]) {
	    		if(curr!=-1)
	    			visit[curr][dict.get(dir)] = true;
	    		if(dir=="N") {
		    		int pos = Collections.binarySearch(valx[mapx.get(x)],y, new Comparator<int[]>() {
						@Override
						public int compare(int[] o1, int[] o2) {
							// TODO Auto-generated method stub
							return o1[0]-o2[0];
						}
			    	});
	    		}
	    		int last = pos;
	    		pos = edges[pos][dict.get(opp.get(dir))];
	    		if(pos==-1) {
	    			out.write(dir+" "+(nodes[last][0]-sub[dict.get(lastdir)][0])+" "+(nodes[last][1]-sub[dict.get(lastdir)][1])+"\n");
	    			win = true;
	    			break;
	    		}
	    		dir = opp.get(dir);
	    		lastdir = dir;
	    	}
	    	if(!win) {
	    		out.write(-1+"\n");
	    	}
	    	*/
	    }
	    out.flush();
	    out.close();
	    
	}

}
