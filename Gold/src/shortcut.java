import java.util.*;
import java.io.*;
public class shortcut {

	public static void main(String[] args)throws IOException {
		long t1 = System.currentTimeMillis();
		BufferedReader br=new BufferedReader(new FileReader("shortcut.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int nodes=Integer.parseInt(st.nextToken());
		int paths=Integer.parseInt(st.nextToken());
		int cut=Integer.parseInt(st.nextToken());

		ArrayList<Integer>[] edge=new ArrayList[nodes];
		int[] amount=new int[nodes];
		st=new StringTokenizer(br.readLine());
		for(int i=0; i<nodes; i++) {
			amount[i]=Integer.parseInt(st.nextToken());
			edge[i]=new ArrayList<>();
		}
		for(int i=0; i<paths; i++) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken())-1;
			int y=Integer.parseInt(st.nextToken())-1;
			int z=Integer.parseInt(st.nextToken());
			edge[x].add(y);
			edge[x].add(z);
			edge[y].add(x);
			edge[y].add(z);
		}
        LinkedList<Integer> list=new LinkedList<>();
        int[] time=new int[nodes];
        for(int i=0; i<nodes; i++){
            time[i]=1000000000;
        }
        time[0]=0;
        list.add(0);
        int[] prev=new int[nodes];
        for(int i=0; i<nodes; i++)
        	prev[i]=i;

        while(!list.isEmpty()){
        	int a=list.removeFirst();
            ArrayList<Integer> al=edge[a];
            int s=al.size();
            for(int i=0; i<s; i+=2){
                int tar=al.get(i);
                int tim=al.get(i+1);
                int old=time[tar];
                int com=time[a]+tim;
            	time[tar]=Math.min(time[tar], com);
                if(old!=time[tar]  || com==time[tar] && prev[tar]>a) {	// tie break and remember path
                	prev[tar]=a;
                	list.add(tar);
                }
            }
        }
        //find leaf node
//        boolean[] isLeaf = new boolean[nodes];
//        for(int i=0;i<nodes;i++)
//        	isLeaf[prev[i]]=false;
        
        int[] cows=new int[nodes];
        for(int i=1; i<nodes; i++) {
        	int x=i;
        	while(x!=prev[x]) {
        		cows[x]+=amount[i];
        		x=prev[x];
        		if(x>10000) {
        			System.out.println(i);
        			x=i+1;
        		}
        	}
        }
        long tol=0;
        for(int i=1; i<nodes; i++) {
        	if(cut>=time[i])
        		continue;
        	int dif=time[i]-cut;
        	long sum=(long)cows[i]*dif;
        	tol=Math.max(tol, sum);
        }
        out.println(tol);
        out.close();
	}
}
