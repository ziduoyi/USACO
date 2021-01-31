import java.util.*;

public class graph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//-------------Bellman Ford
		int N=2;
		int K=2;
		int[][] times=new int[][] {{1,2,1},
									{2,1,3}};
		
        int[] time=new int[N];
        for(int i=0; i<N; i++){
            time[i]=1000000;
        }
        time[K-1]=0;
        for(int i=0;i<N; i++){
            for(int j=0;j<times.length; j++){
                int u=times[j][0]-1;
                int v=times[j][1]-1;
                int w=times[j][2];
                if(time[v]>time[u]+w){
                    time[v]=time[u]+w;
                }
            }
        }
        int ans=0;
        for(int i=0; i<N;i++) {
        	ans=Math.max(ans, time[i]);
        }
        if(ans==1000000)
        	System.out.println(-1);
        else {
        	System.out.println(ans);
        }
	}
	//Union Find
	
	public int[] findRedundantConnection2(int[][] edges) {
        int[] root = new int[edges.length+1];
        for(int i=1;i<root.length;i++)
        	root[i]=i;
        
        for(int[] e : edges){
            int r0 = findRoot(root, e[0]);
            int r1 = findRoot(root, e[1]);
            if(r1!=r0)
            	root[r1]=r0;	//union
            if(r0==r1)	
                return e;
        }
        return null;
    }
    
    static int findRoot(int[] connected, int n){
    	int x=n;
    	while(connected[x]!=x)
    		x=connected[x];
    	connected[n]=x;
        return x;
    }
    
	//Dijkstra
	// positive and negative (slow)
    public int networkDelayTime(int[][] times, int N, int K) {
    	
        LinkedList<Integer> list=new LinkedList<>();
        int[] time=new int[N];
        for(int i=0; i<N; i++){
            time[i]=Integer.MAX_VALUE;
        }
        time[K-1]=0;
        ArrayList<Integer>[] arr=new ArrayList[N];
        for(int i=0; i<N;i++) {
        	arr[i]=new ArrayList<>();
        }
        for(int i=0; i<times.length; i++){
        	int z=times[i][0]-1;
        	int y=times[i][1]-1;
        	int x=times[i][2];
            arr[z].add(y);
            arr[z].add(x);
        }
        boolean[] boo=new boolean[N];
        boo[K-1]=true;
        list.add(K-1);
        while(!list.isEmpty()){
        	int a=list.removeFirst();
        	boo[a]=true;
            ArrayList<Integer> al=arr[a];
            int s=al.size();
            for(int i=0; i<s; i+=2){
                int tar=al.get(i);
                int tim=al.get(i+1);
                int old=time[tar];
                int com=time[a]+tim;
            	time[tar]=Math.min(time[tar], com);
                if(boo[tar]==false||old!=time[tar]) {
                	list.add(tar);
                }
            }
        }
        int ans=0;
        for(int i=0; i<N;i++) {
        	ans=Math.max(ans, time[i]);
        }
        if(ans==Integer.MAX_VALUE)
        	return -1;
        else {
        	return ans;
        }
    }
    //positive only ,faster by a lot
    static public int betterDijkstra(ArrayList<Integer>[] edges, int N, int find) {
    	int[] time=new int[N];
    	PriorityQueue<Integer> list=new PriorityQueue<>((a,b)->time[a]-time[b]);
    	Arrays.fill(time, Integer.MAX_VALUE);
    	time[0]=0;
    	list.add(0);
    	while(!list.isEmpty()) {
    		int use=list.poll();
    		ArrayList<Integer> al=edges[use];
    		int s=al.size();
    		for(int i=0; i<s; i+=2) {
                int tar=al.get(i);
                int dist=al.get(i+1);    	
                if(time[use]+dist<time[tar]) {
                	time[tar]=time[use]+dist;
                	list.add(tar);
                }
    		}
    	}
    	if(time[find]==Integer.MAX_VALUE)
    		return -1;
    	else
    		return time[find];
    	
    }
    static public void minSpanTreePrim(int nums, int[][] cost) {
		//minimum spanning tree -- prim's algorithm
		int[] dist = new int[nums];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean[] visited = new boolean[nums];
		dist[0]=0;
		for(int i=0;i<nums;i++) {
			//find next vertex
			int w=Integer.MAX_VALUE;
			int p=-1;
			for(int j=0;j<nums;j++) {
				if(!visited[j] && w>dist[j]) {
					p=j;
					w=dist[j];
				}
			}
			if(p<0) {	//unreachable
				System.out.println(-1);
				return;
			}
			visited[p]=true;
			
			//edge relaxation
			for(int j=0;j<nums;j++) {
				if(!visited[j] && dist[j]>cost[p][j])
					dist[j]=cost[p][j];
			}
		}
		long res=0;
		for(int x:dist) 
			res += x;
		System.out.println(res);
    }
}
