import java.io.*;
import java.util.*;
public class simplify {
	static int[] root;
	static ArrayList<Integer> store;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		TreeSet<Integer> set = new TreeSet<>();
		Map<Integer, ArrayList<Integer>> map = new HashMap<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			if(a>b) {
				int save = a;
				a = b;
				b = save;
			}
			set.add(c);
			if(map.get(c)!=null) {
				ArrayList<Integer> al = map.get(c);
				al.add(a);
				al.add(b);
			}
			else {
				ArrayList<Integer> al = new ArrayList<>();
				al.add(a);
				al.add(b);
				map.put(c, al);
			}
		}
		root = new int[N];
		for(int i=0; i<N; i++)
			root[i] = i;
		long ans = 1;
		long min = 0;
		int count = N;
		while(!set.isEmpty()) {
			if(count ==1)
				break;
			int cost = set.pollFirst();
			ArrayList<Integer> al = map.get(cost);
			int s = al.size();
			for(int i=s-2; i>-1; i-=2) {
				int a = getRoot(al.get(i));
				int b= getRoot(al.get(i+1));
				al.remove(i);
				al.remove(i);
				if(a!=b) {
					if(a>b) {
						int save = a;
						a = b;
						b = save;
					}
					al.add(i, a);
					al.add(i+1,b);
				}
			}
			s = al.size();
			if(s==0)
				continue;
			if(s==2) {
				count--;
				int r1 = getRoot(al.get(0));
				int r2 = getRoot(al.get(1));
				root[r2] = r1;
				min += cost;
				continue;
			}
			if(s==4) {
				if(getRoot(al.get(0))==getRoot(al.get(2))&&getRoot(al.get(1))==getRoot(al.get(3))) {
					ans*=2;
					min -= cost;
					count++;
				}
				min += 2*cost;
				int r1 = getRoot(al.get(0));
				int r2 = getRoot(al.get(1));
				root[r2] = r1;
				r1 = getRoot(al.get(2));
				r2 = getRoot(al.get(3));
				root[r2] = r1;
				count -=2;
				continue;
			}
			boolean f = false;
			int mult = 1;
			if(getRoot(al.get(0))==getRoot(al.get(2))&&getRoot(al.get(1))==getRoot(al.get(3))) {
				mult++;
				min -= cost;
				count++;
				f = true;
			}
			if(getRoot(al.get(0))==getRoot(al.get(2))&&getRoot(al.get(3))==getRoot(al.get(5))) {
				mult++;
				min -= cost;
				count++;
				f = true;
			}
			if(getRoot(al.get(2))==getRoot(al.get(4))&&getRoot(al.get(3))==getRoot(al.get(5))) {
				mult++;
				min -= cost;
				count++;
				f = true;
			}
			if(mult==4) {
				mult--;
				min += cost;
				count--;
			}
			count -=3;
			min+=3*cost;
			ans *= mult;
			boolean c = false;
			for(int i=0; i<6; i++) {
				boolean b = false;
				for(int j=0; j<6; j++) {
					if(i==j)
						continue;
					if(getRoot(al.get(i))==getRoot(al.get(j))){
						b = true;
						break;
					}
				}
				if(b == false) {
					c = true;
					break;
				}
			}
			if(c==false&&f == false) {
				min -= cost;
				count++;
				ans *= 3;
			}
			root[getRoot(al.get(0))] = getRoot(al.get(1));
			root[getRoot(al.get(2))] = getRoot(al.get(3));
			root[getRoot(al.get(4))] = getRoot(al.get(5));
		}
		System.out.print(min%1000000007+" ");
		System.out.println(ans%1000000007);
	}
	static void combi(int len, int curr,int size, int pos, int[] arr){
		if(curr==len) {
			for(int i=0; i<len; i++)
				store.add(arr[i]);
			return;
		}
		if(pos==size)
			return;
		for(int i=pos; i<size; i++) {
			arr[curr] = i;
			combi(len, curr+1,size, i+1, arr);
		}
	}
	static int getRoot(int curr) {
		int pos = curr;
		while(pos!=root[pos])
			pos = root[pos];
		root[curr] = pos; 
		return pos;
	}
}
