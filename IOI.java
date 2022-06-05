import java.util.*;
public class IOI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		travel_plan(5,7,new int[][]{ {0, 2}, {0, 3}, {3, 2}, {2, 1}, {0, 1}, {0, 4}, {3, 4} },new int[]{4, 3, 2, 10, 100, 7, 9},2,new int[]{1, 3});

	}
	static int travel_plan(int N, int M, int[][] R, int[] L, int K, int[] P) {
		//set up process for shortest path D
		int ans = 1000000000;
		int[] time=new int[N];
		Arrays.fill(time, Integer.MAX_VALUE);
		time[0]=0;
		ArrayList<Integer>[] edges = new ArrayList[N];
		for(int i=0; i<N; i++)
			edges[i]=new ArrayList<>();
		for(int i=0; i<M; i++) {
			edges[R[i][0]].add(R[i][1]);
			edges[R[i][0]].add(L[i]);
			edges[R[i][1]].add(R[i][0]);
			edges[R[i][1]].add(L[i]);
		}
	    PriorityQueue<int[]> list=new PriorityQueue<>((a,b)->a[1]-b[1]);
	    int[] z=new int[] {0,0};
	    list.add(z);
	    //shortest path D from start
	    while(!list.isEmpty()) {
	    	int[] use = list.poll();
	    	ArrayList<Integer> al = edges[use[0]];
	    	int s=al.size();
	    	for(int i=0; i<s; i+=2) {
	    		int next=al.get(i);
	    		int dist=al.get(i+1);
	    		if(use[1]+dist<time[next]&&use[1]+dist>0) {
	    			time[next]=use[1]+dist;
	    			int[] temp=new int[]{next,time[next]};
	    			list.add(temp);
	    		}
	    	}
	    }
	    //2nd shortest path D from all exit
	    int[][] time2=new int[N][2];
	    int[][] prev2=new int[N][2];
	    for(int i=0; i<N; i++) {
	    	prev2[i][0]=-1;
	    	prev2[i][1]=-1;
	    }
 	    second_short(time2,prev2,edges,K,P,N);
	    
	    return -1;
 	}
	static void second_short(int[][] time, int[][] prev,ArrayList<Integer>[] edges, int K, int[] P, int N) {
		for(int i=0; i<N; i++)
			Arrays.fill(time[i],Integer.MAX_VALUE);
		PriorityQueue<int[]> list=new PriorityQueue<>((a,b)->a[1]-b[1]);
		int[] z=new int[] {0,0};
		for(int i=0; i<K; i++) {
			prev[P[i]][0]=P[i];
			prev[P[i]][1]=P[i];
			z[0]=P[i];
			int[] x=new int[] {z[0],z[1]};
			time[P[i]][0]=0;
			time[P[i]][1]=0;
			list.add(x);
		}
		while(!list.isEmpty()) {
			int[] use=list.poll();
			ArrayList<Integer> al=edges[use[0]];
			int s=al.size();
			for(int i=0; i<s; i+=2) {
				int next=al.get(i);
				int dist=al.get(i+1);
				if(use[1]+dist<time[next][1]&&use[1]+dist>0) {
					if(use[1]+dist>=time[next][0]&&prev[next][0]!=prev[next][1]) {
						time[next][1]=use[1]+dist;
		    			int[] temp=new int[]{next,time[next][1]};
		    			prev[next][1]=use[0];
		    			list.add(temp);
					}
					else if(use[1]+dist<time[next][0]){
						time[next][1]=time[next][0];
						prev[next][1]=prev[next][0];
						time[next][0]=use[1]+dist;
						prev[next][0]=use[0];
						int[] temp=new int[] {next, time[next][0]};
						list.add(temp);
					}
				}
			}
		}
 	}
}
   