import java.io.*;
import java.util.*;
public class cowemb {
	static int[] bitree;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		ArrayList<double[]> arr = new ArrayList<>();// 0= start      1 = end
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(b==0) {
				double val = (double)R*R - ((double)c*c)/((double)a*a);
				if((double)R*R-(c*c)/(a*a)>0) {
					arr.add(new double[] {-1*c/a, val, i+1});
					arr.add(new double[] {-1*c/a, -1*val, i+1});
				}
				continue;
			}
			double div = (long)a*a + (long)b*b;
			div = Math.sqrt(div);
			double curr = (Math.sqrt((long)R*div +(long)Math.abs(c)))*(Math.sqrt((long)R*div -(long)Math.abs(c)));
			div*=div;
			if(curr>0) {
				curr/=div;
				curr*=b;
				double start = -1*a*c/div;
				arr.add(new double[] {start+curr, -1*((a*(start+curr)/b)+(c/b)), i+1});
				arr.add(new double[] {start-curr, -1*((a*(start-curr)/b)+(c/b)), i+1});
			}
		}
		Collections.sort(arr, new Comparator<double[]>(){
			@Override
			public int compare(double[] o1, double[] o2) {
				// TODO Auto-generated method stub
				if(o1[0]<o2[0])
					return -1;
				if(o1[0]==o2[0])
					return 0;
				return 1;
			}
		});
		double[][] order =  new double[arr.size()][3];
		bitree = new int[arr.size()+1];
		int z =0;
		Set<Integer> set = new HashSet<>();
		for(int i=0; i<arr.size(); i++) {
			if(arr.get(i)[1]>=0) {
				order[z][0] = arr.get(i)[0];
				order[z][1] = arr.get(i)[1];
				order[z++][2] = arr.get(i)[2];
			}
		}
		for(int i= arr.size()-1; i>-1; i--) {
			if(arr.get(i)[1]<0) {
				order[z][0] = arr.get(i)[0];
				order[z][1] = arr.get(i)[1];
				order[z++][2] = arr.get(i)[2];
			}
		}
		int ans =0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<order.length; i++) {
			if(set.contains((int)order[i][2])) {
				ans += sum(i+1)-sum(map.get((int)order[i][2]));
				modify(map.get((int)order[i][2]), -1); 
			}
			else {
				set.add((int)order[i][2]);
				modify(i+1, 1);
				map.put((int)order[i][2], i+1);
			}
		}
		System.out.println(ans);
	}
    static void modify(int j, int delta) {
        for(;j<=bitree.length-1;j+=(-j&j))		//j=j+lowbit(j)
        	bitree[j] += delta;
    }
    static int sum(int j) {
    	int s=0;
        for(;j>0; j-=(-j&j))	////j=j-lowbit(j)
        	s+=bitree[j];
        return s;
    }
	
}
