import java.util.*;
import java.io.*;
public class circlecross {
	static int N;
	static int[] stree;
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("circlecross.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));
		N=2*Integer.parseInt(br.readLine());
		stree=new int[2*N];
		HashMap<Integer, Integer> map=new HashMap<>();
		int pairs=0;
		for(int i=1; i<N+1; i++) {
			int node=Integer.parseInt(br.readLine());
			if(map.get(node)==null) {
				update(i, 1);
				map.put(node, i);
			}
			else {
				int pos=map.get(node);
				pairs+=sumRange(pos+1,i-1);
				update(pos, 0);
			}
		}
		out.println(pairs);
		out.close();
	}
    public static void update(int i, int val) {
        int x = i + N;
        stree[x]=val;
        while(x>0){
            int left=x;
            int right=x;
            if((x&1)==0)	//x%2==0
                right++;
            else
                left--;
            stree[x>>1] = stree[left]+stree[right];
            x>>=1;
        }
    }

    public static int sumRange(int i, int j) {
        int l=i+N, r=j+N;
        int sum=0;
        while(l<=r){
            if((l&1)==1)
                sum += stree[l++];
            if((r&1)==0)
                sum += stree[r--];
            l /= 2;
            r /= 2;
        }
        return sum;
    }
}

