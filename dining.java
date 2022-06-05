import java.io.*;
import java.util.*;
public class dining {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("dining.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dining.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int tol=Integer.parseInt(st.nextToken());
		int num=Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] edge=new ArrayList[tol];
		int[] bale=new int[tol];
		int hay=Integer.parseInt(st.nextToken());
		for(int i=0; i<tol; i++) {
			edge[i]=new ArrayList<>();
		}
		for(int i=0; i<num; i++) {
			 st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken())-1;
			int y=Integer.parseInt(st.nextToken())-1;
			int z=Integer.parseInt(st.nextToken());
			edge[x].add(y);
			edge[x].add(z);
			edge[y].add(x);
			edge[y].add(z);
		}

		for(int i=0; i<hay; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken())-1;
			int b=Integer.parseInt(st.nextToken());
			bale[a]=Math.max(bale[a], b);
		}
        int[] time=new int[tol];
        for(int i=0; i<tol; i++){
            time[i]=1000000;
        }
        time[tol-1]=0;
		LinkedList<Integer> list=new LinkedList<>();
        list.add(tol-1);
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
                if(old!=time[tar]) {
                	list.add(tar);
                }
            }
        }
        while(!list.isEmpty()) {
     	   list.removeFirst();
        }
        int[] time2=new int[tol];
        for(int i=0; i<tol; i++) {
        	if(bale[i]==0)
        		time2[i]=100000;
        	else {
        		time2[i]-=bale[i];
        		time2[i]+=time[i];
        		list.add(i);
        	}
        }
        while(!list.isEmpty()) {
        	int n=list.removeFirst();
        	ArrayList<Integer> al=edge[n];
        	int s=al.size();
        	for(int i=0; i<s; i+=2) {
        		int end=al.get(i);
        		int t=al.get(i+1);
        		int old=time2[end];
        		time2[end]=Math.min(time2[end], time2[n]+t);
                if(old!=time2[end]) {
                	list.add(end);
                }
        	}
        }
        for(int i=0; i<tol-1; i++) {
        	if(time[i]>=time2[i])
        		out.println(1);
        	else
        		out.println(0);
        }
        out.close();
	}
}
