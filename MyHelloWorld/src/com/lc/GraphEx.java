package com.lc;

import java.util.*;

import com.lc.MediumSet3.Edge;

public class GraphEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphEx ex = new GraphEx();
		
		//ex.findItinerary(new String[][]{{"EZE","AXA"},{"TIA","ANU"},{"ANU","JFK"},{"JFK","ANU"},{"ANU","EZE"},{"TIA","ANU"},{"AXA","TIA"},{"TIA","JFK"},{"ANU","TIA"},{"JFK","TIA"}});
		//ex.calcEquation(new String[][]{ {"a", "b"}, {"b", "c"} , {"d", "e"}, {"e", "f"} }, new double[]{2.0, 4.0, 5.0, 8.0,10.0}, new String[][]{ {"a", "d"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"} });
		ex.calcEquation(new String[][]{ {"a", "b"}, {"e", "f"}, {"b", "e"} }, new double[]{3.4, 1.4, 2.3}, new String[][]{{"b","a"},{"a","f"},{"f","f"},{"e","e"},{"c","c"},{"a","c"},{"f","e"}});
	}

//310. Minimum Height Trees	
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        ArrayList<Integer> res = new ArrayList<>();
        HashSet<Integer>[] nodes = new HashSet[n];
        for(int i=0;i<n;i++){
            nodes[i]=new HashSet<Integer>();
        }
        for(int i=0;i<n-1;i++){
            nodes[edges[i][0]].add(edges[i][1]);
            nodes[edges[i][1]].add(edges[i][0]);
        }
        LinkedList<Integer> list = new LinkedList<>();
        boolean[] visited = new boolean[n];
        int m=n;
        while(m>2){
            for(int i=0;i<n;i++){
                if(nodes[i].size()==1){
                    list.add(i);
                    visited[i]=true;
                    m--;
                }
            }
            int num=list.size();
            for(int i=0;i<num;i++){
                int v = list.removeFirst();
                for(int u: nodes[v]){
                    nodes[u].remove(v);
                }
                nodes[v].clear();
            }
        }
        for(int i=0;i<n;i++)
            if(!visited[i])
                res.add(i);
        return res;
    }
//solution 2:
    HashSet<Integer>[] nodes = null;
    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        ArrayList<Integer> res = new ArrayList<>();
        nodes = new HashSet[n];
        for(int i=0;i<n;i++){
            nodes[i]=new HashSet<Integer>();
        }
        for(int i=0;i<n-1;i++){
            nodes[edges[i][0]].add(edges[i][1]);
            nodes[edges[i][1]].add(edges[i][0]);
        }
        boolean[] visited = new boolean[n];
        dfs(0,1,visited,"");
        int pos = longestPath.lastIndexOf(',');
        int start = Integer.parseInt(longestPath.substring(pos+1));
        maxLength=0;
        longestPath=null;
        Arrays.fill(visited, false);
        dfs(start,1,visited,"");
        String[] path = longestPath.split(",");
        int num = path.length;
        res.add(Integer.parseInt(path[num/2]));
        if(num%2==0)
        	res.add(Integer.parseInt(path[num/2-1]));
        
        return res;
    }
    
	int maxLength = 0;
	String longestPath=null;
    void dfs(int s, int len, boolean[] visited, String path){
    	visited[s]=true;
        if(len==1)
            path=""+s;
        else
    	    path += ","+s;
    	if(len>maxLength){
    		maxLength=len;
    		longestPath = path;
    	}

    	for(int u : nodes[s]){
    		if(!visited[u]){
    			dfs(u,len+1,visited,path);
    		}
    	}
    }
//////////////////////////////////    
//332. Reconstruct Itinerary

    public List<String> findItinerary(String[][] tickets) {
        ArrayList<String>res=new ArrayList<>();

        HashMap<String, TreeSet<String>> routes = new HashMap<>();	//cannot use tree set here because duplicate routes could exist: {"TIA","ANU"},{"TIA","ANU"}
        for(String[] ticket : tickets){
            TreeSet<String> set = routes.get(ticket[0]);
            if(set==null){
            	set = new TreeSet<String>();
            	routes.put(ticket[0], set);
            }
            set.add(ticket[1]);
        }
        dfs332(routes,tickets.length,"JFK",res);
        Collections.reverse(res);
        return res;
    }
    
    boolean dfs332(HashMap<String, TreeSet<String>> routes, int N, String start, List<String> list){
        if(N==0){
            list.add(start);
            return true;
        }
        TreeSet<String> set = routes.get(start);
        if(set==null || set.isEmpty())
            return false;
        String[] dest = set.toArray(new String[set.size()]);
        for(String to : dest){
            set.remove(to);
            if(dfs332(routes,N-1,to,list)){
                list.add(start);
                return true;
            }
            set.add(to);
        }
        return false;
    }

 //solution 2:   
    public List<String> findItinerary_2(String[][] tickets) {
        int N=tickets.length;
        ArrayList<String>res=new ArrayList<>();
        Arrays.sort(tickets,(a,b)->{if(a[0].equals(b[0]))return a[1].compareTo(b[1]); else return a[0].compareTo(b[0]);});
        boolean[] used = new boolean[N];
        dfs332(tickets,N,"JFK",res,used);
        Collections.reverse(res);
        return res;
    }
    
    boolean dfs332(String[][] tickets, int N, String start, List<String> list,boolean[] used){
        if(N==0){
            list.add(start);
            return true;
        }
        for(int i=0;i<tickets.length;i++){
            if(!used[i] && start.equals(tickets[i][0])){
                used[i]=true;
                if(dfs332(tickets,N-1,tickets[i][1],list,used)){
                    list.add(start);
                    return true;
                }
                used[i]=false;
            }
        }
        return false;
    }

    
////207. Course Schedule
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashSet<Integer>[] pres = new HashSet[numCourses];
        HashSet<Integer>[] post = new HashSet[numCourses];
        for(int i=0;i<numCourses;i++){
            pres[i] = new HashSet<>();
            post[i] = new HashSet<>();
        }
        for(int[] courses : prerequisites){
            int p = courses[0], q = courses[1];
            pres[q].add(p);
            post[p].add(q);
        }
        boolean[] canTake = new boolean[numCourses];
        LinkedList<Integer> list = new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(pres[i].isEmpty())
                list.add(i);
        }
        while(!list.isEmpty()){
            int x = list.removeFirst();
            canTake[x]=true;
            for(int i : post[x]){
                if(!canTake[i]){
                    pres[i].remove(x);
                    if(pres[i].isEmpty())
                        list.add(i);
                }
            }
        }
        for(boolean b : canTake){
            if(!b) return false;
        }
        return true;
    }

//210. Course Schedule II    Topological sort
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        HashSet<Integer>[] pres = new HashSet[numCourses];
        HashSet<Integer>[] post = new HashSet[numCourses];
        for(int i=0;i<numCourses;i++){
            pres[i]=new HashSet<>();
            post[i]=new HashSet<>();
        }
        for(int[] c : prerequisites){
            pres[c[0]].add(c[1]);
            post[c[1]].add(c[0]);
        }
        boolean[] canTake = new boolean[numCourses];
        int[] res=new int[numCourses];
        LinkedList<Integer> list = new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(pres[i].isEmpty())
                list.add(i);
        }
        int x = 0;
        while(!list.isEmpty()){
            int i = list.removeFirst();
            canTake[i]=true;
            res[x++]=i;
            for(int j : post[i]){
                if(!canTake[j]){
                    pres[j].remove(i);
                    if(pres[j].isEmpty())
                        list.add(j);
                }
            }
        }
        return x==numCourses?res:new int[0];
    }    

/*  323 Number of Connected Components in an Undirected Graph 
 *  
    Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

    Example 1:

         0          3

         |          |

         1 --- 2    4

    Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

    Example 2:

         0           4

         |           |

         1 --- 2 --- 3

    Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

     Note:

    You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.    
*/
    
//#399. Evaluate Division
    /**
    1. Thoughts
        - check if we have enough info to get the result
        - if yes, calculate; if not, return -1.0
        - Method: union find
            - a/b = 2.0 --> b is the root of a; the distance from a to b is 1/2.0
            - if two nums have the same root, we can get the result; a/b=2.0, b/c=3.0
            index   a   b   c
            root    b   c   c 
            dist    2   3   1
            - if we want to know a/c = ?: a = 2 * b = 2 * 3 * c => a/c = 6.0
    2. Corner case
        - if any input is null, return null
        - no enough info, return -1.0
    3. Steps
        - go through equations to union elements with the same root and update root map and distance map
        - go through each query: check if has the same root; find relative dist
*/
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        int n = values.length;
        Map<String,String> root = new HashMap<>();
        HashMap<String,Double> div = new HashMap<>();
        
        for(int i=0;i<n;i++){
            String r0 = findRoot(root,div,equations[i][0]);
            String r1 = findRoot(root,div,equations[i][1]);
            double d0 = cal(root,div,equations[i][0]);
            double d1 = cal(root,div,equations[i][1]);
            root.put(r0,r1);
            div.put(r0,values[i] * d1 /d0);
        }
        double[] res=new double[queries.length];
        for(int i=0;i<queries.length;i++){
            if(!root.containsKey(queries[i][0]) || !root.containsKey(queries[i][1])){
                res[i]=-1;
                continue;
            }
            String r0 = findRoot(root, div, queries[i][0]);
            String r1 = findRoot(root, div, queries[i][1]);
            if(r0.equals(r1)){
                res[i] = cal(root,div,queries[i][0]) / cal(root,div,queries[i][1]);
            }else
                res[i]=-1.0;
        }
        return res;
    }
    String findRoot(Map<String,String> root, HashMap<String,Double> div, String str){
        if(!root.containsKey(str)){
            div.put(str,1.0);
            root.put(str,str);
            return str;
        }
        while(!str.equals(root.get(str))){
            str = root.get(str);
        }
        return str;
    }
    double cal(Map<String,String> root, HashMap<String,Double> div, String str){
        double res = div.get(str);
        while(!str.equals(root.get(str))){
            str = root.get(str);
            res *= div.get(str);
        }
        return res;
    }
    
    public double[] calcEquation2(String[][] e, double[] values, String[][] q) {
        double[] res = new double[q.length];
        Map<String, String> root = new HashMap<>();
        Map<String, Double> dist = new HashMap<>();
        for (int i = 0; i < e.length; i++) {
            String r1 = find(root, dist, e[i][0]);
            String r2 = find(root, dist, e[i][1]);
            root.put(r1, r2);
            dist.put(r1, dist.get(e[i][1]) * values[i] / dist.get(e[i][0]));
        }
        for (int i = 0; i < q.length; i++) {
            if (!root.containsKey(q[i][0]) || !root.containsKey(q[i][1])) {
                res[i] = -1.0;
                continue;
            }
            String r1 = find(root, dist, q[i][0]);
            String r2 = find(root, dist, q[i][1]);
            if (!r1.equals(r2)) {
                res[i] = -1.0;
                continue;
            }
            res[i] = (double) dist.get(q[i][0]) / dist.get(q[i][1]);
        }
        return res;
    }
    
    private String find(Map<String, String> root, Map<String, Double> dist, String s) {
        if (!root.containsKey(s)) {
            root.put(s, s);
            dist.put(s, 1.0);
            return s;
        }
        if (root.get(s).equals(s)) return s;
        String lastP = root.get(s);
        String p = find(root, dist, lastP);
        root.put(s, p);
        dist.put(s, dist.get(s) * dist.get(lastP));
        return p;
    }    

//#743. Network Delay Time -- directed graph.
  //graph Dijkstra    
      public int networkDelayTime(int[][] times, int N, int K) {
  	    int m=times.length;
  	    //int n=times[0].length;
  	    
  	    LinkedList<Integer> list = new LinkedList<>();
  	    HashSet<Integer> visited = new HashSet<>();
  	    //boolean[] visited = new boolean[N+1];
  	    int[] dist=new int[N+1];
  	    Arrays.fill(dist,Integer.MAX_VALUE);
  	    dist[K]=0;
  	    list.add(K);
  	    visited.add(K);
  	
  	    //spreading like bfs
  	    while(!list.isEmpty()){
  	    	for(int x=list.size();x>0;x--){
  		        int u = list.removeFirst();
  		        for(int i=0;i<m;i++){
  		            if(times[i][0]==u){	// directed: find all edges starts from u // if for undirected graph: if(times[i][0]==u || times[i][1]==u)
	  		            int v = times[i][1];
	  		            int s = dist[u] + times[i][2];
	  		            if(dist[v]>s)
	  		                dist[v]=s;
	  		            if(!visited.contains(v)){
	  		                visited.add(v);
	  		                list.add(v);
	  		            }
  		            }
  		        }
  	    	}
  	    }
  	    
  	    int res = 0;
  	    for(int i=1;i<=N;i++){
  	        res = Math.max(res,dist[i]);
  	    }
  	    if(res==Integer.MAX_VALUE)
  	        return -1;
  	    else
  	        return res;
      }   
      
  //solution 2: Dijkstra with adjacent list
      class Edge{
          int node;	//target node
          int time;
          public Edge(int n,int t){this.node=n;this.time=t;}
      }
      public int networkDelayTime2(int[][] times, int N, int K) {
          int[] dist = new int[N+1];
          Arrays.fill(dist,600000);
          dist[0]=0;
          dist[K]=0;
          
          ArrayList<Edge>[] edges = new ArrayList[N+1];
          for(int i=1;i<=N;i++){
              edges[i]=new ArrayList<Edge>();
          }
          for(int[] e : times){
              edges[e[0]].add(new Edge(e[1],e[2]));
          }
          ArrayList<Integer> list = new ArrayList<>();
          list.add(K);
          boolean[] visited = new boolean[N+1];
          visited[K]=true;
          while(true){
              int time=600000;
              Edge edge = null;
              for(int x : list){
                  for(Edge e : edges[x]){
                      if(!visited[e.node] && time>dist[x]+e.time){
                          time=dist[x]+e.time;
                          edge=e;
                      }
                  }
              }
              if(edge==null) break;
              dist[edge.node]=time;
              list.add(edge.node);
              visited[edge.node]=true;
          }
          int max=0;
          for(int x : dist){
              max=Math.max(max,x);
          }
          return max==600000?-1:max;
      }

      
  //Dijkstra's algorithm
//      Time complexity: O(Nlog(N) + E), Space complexity: O(N + E)    
      public int networkDelayTime_Dijkstra(int[][] times, int N, int K) {
          Map<Integer, List<int[]>> graph = new HashMap<>();
          for (int[] edge: times) {
              graph.putIfAbsent(edge[0], new ArrayList<>());
              graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
          }
          PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
          boolean[] visited = new boolean[N + 1];
          int[] minDis = new int[N + 1];
          Arrays.fill(minDis, Integer.MAX_VALUE);
          minDis[K] = 0;
          pq.offer(new int[]{0, K});
          int max = 0;
          while (!pq.isEmpty()) {
              int[] curr = pq.poll();
              int currNode = curr[1];
              if (visited[currNode]) continue;
              visited[currNode] = true;
              int currDis = curr[0];
              max = currDis;
              N--;
              if (!graph.containsKey(currNode)) continue;
              for (int[] next : graph.get(currNode)) {
                  if (!visited[next[0]] && currDis + next[1] < minDis[next[0]])
                      pq.offer(new int[]{currDis + next[1], next[0]});
              }
          }
          return N == 0 ? max : -1;
      }
      
//      Bellman-Ford algorithm
//      Time complexity: O(N*E), Space complexity: O(N)
      public int networkDelayTime_BF(int[][] times, int N, int K) {
          double[] disTo = new double[N];
          Arrays.fill(disTo, Double.POSITIVE_INFINITY);
          disTo[K - 1] = 0;
          for (int i = 1; i < N; i++) {
              for (int[] edge : times) {
                  int u = edge[0] - 1, v = edge[1] - 1, w = edge[2];
                  disTo[v] = Math.min(disTo[v], disTo[u] + w);
              }
          }
          double res = Double.MIN_VALUE;
          for (double i: disTo) {
              res = Math.max(i, res);
          }
          return res == Double.POSITIVE_INFINITY ? -1 : (int) res;
      }
      
      public int networkDelayTime_Bellman(int[][] times, int N, int K) {
          int[] disTo = new int[N];
          Arrays.fill(disTo, 600000);
          disTo[K - 1] = 0;
          for (int i = 1; i < N; i++) {
              for (int[] edge : times) {
                  int u = edge[0] - 1, v = edge[1] - 1, w = edge[2];
                  disTo[v] = Math.min(disTo[v], disTo[u] + w);
              }
          }
          int res = 0;
          for (int i: disTo) {
              res = Math.max(i, res);
          }
          return res == 600000 ? -1 : (int) res;
      } 
      
  //Floyd¨CWarshall algorithm
  //Time complexity: O(N^3), Space complexity: O(N^2)
      public int networkDelayTime_FW(int[][] times, int N, int K) {
          double[][] disTo = new double[N][N];
          for (int i = 0; i < N; i++) {
              Arrays.fill(disTo[i], Double.POSITIVE_INFINITY);
          }
          for (int i = 0; i < N; i++) {
              disTo[i][i] = 0;
          }
          for (int[] edge: times) {
              disTo[edge[0] - 1][edge[1] - 1] = edge[2];
          }
          for (int k = 0; k < N; k++) {
              for (int i = 0; i < N; i++) {
                  for (int j = 0; j < N; j++) {
                      if (disTo[i][j] > disTo[i][k] + disTo[k][j])
                          disTo[i][j] = disTo[i][k] + disTo[k][j];
                  }
              }
          }
          double max = Double.MIN_VALUE;
          for (int i = 0; i < N; i++) {
              if (disTo[K - 1][i] == Double.POSITIVE_INFINITY) return -1;
              max = Math.max(max, disTo[K - 1][i]);
          }
          return (int) max;
      }   
//// End #743
      
//MST: minimum spanning tree, prim's algorithm
      public int mstPrim(int[][]times, int N){	//adjacent matrix
    	  int[] dist = new int[N];

    	  Arrays.fill(dist, Integer.MAX_VALUE);
    	  dist[0]=0;
    	  boolean[] visited = new boolean[N];

    	  for(int x=0;x<N;x++){
    		  //find next vertex
    		  int time=Integer.MAX_VALUE;
    		  int p=-1;	//next vertex
    		  for(int i=0;i<N;i++){
    			  if(!visited[i] && dist[i]<time){
    				  p=i;
    				  time=dist[i];
    			  }
    		  }
    		  visited[p]=true;
    		  
    		  //edge relaxation
    		  for(int j=0;j<N;j++){
    			  if(visited[j]) 
    				  dist[j]=Math.min(dist[j], times[p][j]);
    		  }
    	  }

    	  int res = 0;
    	  for(int x:dist)
    		  res += x;
    	  return res;
      }
      
      
}

