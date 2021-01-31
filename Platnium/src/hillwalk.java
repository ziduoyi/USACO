import java.util.*;
import java.io.*;
public class hillwalk {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// TODO Auto-generated method stub
		int[][] arr = new int[N][4];
		int[][] end = new int[N][4];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
			arr[i][3] = Integer.parseInt(st.nextToken());
			end[i] = arr[i].clone();
		}
		Arrays.sort(end, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[2] - o2[2];
			}
		});		
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[0] - o2[0];
			}
		});
		TreeSet<int[]> set = new TreeSet<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if(o1[0]>o2[0]) {
					if(o1[2]>o2[2]) {
						if(o1[1]<o2[1]) {
							return -1;
						}
						else if(o1[1]>o2[3]) {
							return 1;
						}
						else {
							long l= -1*ccw(o2[0], o2[1], o1[0], o1[1], o2[2], o2[3]);
							if(l>0)
								return 1;
							if(l<0)
								return -1;
							return 0;
						}
					}
					else if(o1[2]==o2[2]) {
						return o1[3] -o2[3];
					}
					else {
						return o1[1] - o2[1];
					}
				}
				else if(o1[0]==o2[0]){
					return o1[1] - o2[1];
				}
				else {
					if(o1[2]>o2[2]) {
						return o1[1] - o2[1];
					}
					else if(o1[2]==o2[2]) {
						return o1[3] -o2[3];
					}
					else {
						if(o2[1]<o1[1])
							return 1;
						else if(o2[1]>o1[3])
							return -1;
						else {
							long l = ccw(o1[0], o1[1], o2[0], o2[1], o1[2], o1[3]);
							if(l>0)
								return 1;
							if(l<0)
								return -1;
							return 0;
						}
					}
				}
			}
		});
		int total =0;
		boolean fall =false;
		int last =-1;
		int currx =0;
		int curry =0;
		int re =-1;
		while(!fall) {
			for(int i=last+1; i<N; i++) {
				if(currx >= arr[i][0]) {
					last =i;
					if(currx<arr[i][2]) {
						if(curry>=arr[i][1]) {
							set.add(arr[i]); 
						}
					}
				}
				else
					break;
			}
			for(int i=re+1; i<N; i++) {
				if(end[i][2]<=currx) {
					 re = i;
					 if(set.contains(end[i])==true)
						 set.remove(end[i]);
				}
				else
					break;
			}
			if(set.isEmpty())
				break;
			int[] use = set.last();
			total++;
			currx = use[2];
			curry = use[3];
		}
		System.out.println(total);
	}
	static long ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
		return (long)((long)((x2-x1)*(y3-y1))-(long)((y2-y1)*(x3-x1)));
	}

}
