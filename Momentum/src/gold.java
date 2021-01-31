import java.util.*;
import java.io.*;
public class gold {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
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
        LinkedList<Integer>[] direct= new LinkedList[nodes];
        LinkedList<Integer>[] seg=new LinkedList[nodes];
        for(int i=0; i<nodes; i++) {
        	direct[i]=new LinkedList<>();
        	direct[i].add(0);
        	seg[i]=new LinkedList<>();
        	seg[i].add(0);
        }
        int[] time=new int[nodes];
        for(int i=0; i<nodes; i++){
            time[i]=100000000;
        }
        time[0]=0;
        list.add(0);
        while(!list.isEmpty()){
        	int a=list.removeFirst();
            ArrayList<Integer> al=edge[a];
            LinkedList<Integer> pos=direct[a];
            LinkedList<Integer> use=seg[a];
            int s=al.size();
            for(int i=0; i<s; i+=2){
                int tar=al.get(i);
                int tim=al.get(i+1);
                int old=time[tar];
                int com=time[a]+tim;
            	time[tar]=Math.min(time[tar], com);
                if(old!=time[tar]) {
            		direct[tar].clear();
            		direct[tar].addAll(pos);
            		direct[tar].add(tar);
            		seg[tar].clear();
            		seg[tar].addAll(use);
            		seg[tar].add(tim);
                	list.add(tar);
                }
                if(old==time[tar]&&old==com) {
                	int a1=pos.size()+1;
                	int b1=direct[tar].size();
                	if(a1<b1) {
                		direct[tar].clear();
                		direct[tar].addAll(pos);
                		direct[tar].add(tar);
                		seg[tar].clear();
                		seg[tar].addAll(use);
                		seg[tar].add(tim);
                		list.add(tar);
                	}
                }
            }
        }
        for(int i=0; i<nodes; i++) {
        	time[i]*=amount[i];
        }
        long sub=0;
        for(int i=1; i<nodes; i++) {
        	long curr=0;
        	for(int j=0; j<nodes; j++) {
        		if(direct[j].contains(i)==true) {
        			int sum1=0;
        			int sum2=time[i];
        			int n=direct[j].size();
        			n--;
        			while(direct[j].get(n)!=i) {
        				sum1+=seg[j].get(n);
        				n--;
        			}
        			sum1+=(cut*amount[direct[j].get(n)]);
        			if(sum1<sum2)
        				curr+=(sum2-sum1);
        		}
        	}
        	sub=Math.max(curr, sub);
        }
        out.println(sub);
        out.close();
	}
}
