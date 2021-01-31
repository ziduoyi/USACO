import java.io.*;
import java.util.*;
public class rect1 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		N++;
		int[][] arr = new int[N][5];
		arr[0][0] = 0;
		arr[0][1] = 0;
		arr[0][2] = A;
		arr[0][3] = B;
		arr[0][4] = 1;
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
			arr[i][3] = Integer.parseInt(st.nextToken());
			arr[i][4] = Integer.parseInt(st.nextToken());
		}
		PriorityQueue<int[]> que = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return arr[o1[0]][o1[1]] - arr[o2[0]][o2[1]];
			}
		});
		for(int i=0; i<N; i++) {
			que.add(new int[] {i, 1,}); // 1 = start , 3 = end
			que.add(new int[] {i, 3,});
		}
		/*
		PriorityQueue<int[]> stuff = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[0] - o2[0];
			}
		});
		*/
		ArrayList<int[]> stuff = new ArrayList<>();
		int[] ans = new int[2501];
		int last =-1;
		while(!que.isEmpty()) {
			TreeSet<Integer> kinds = new TreeSet<>();
			int back =-1;
			int[] use = que.poll();
			for(int i=0; i<stuff.size(); i++) {
				int[] next = stuff.get(i);
				if(back!=-1&&!kinds.isEmpty()) {
					int save = kinds.pollLast();
					kinds.add(save);
					ans[arr[save][4]]+=(arr[next[0]][next[1]]-back)*(arr[use[0]][use[1]]-last);
				}
				if(next[1]==0) 
					kinds.add(next[0]);
				else 
					kinds.remove(next[0]);
				back = arr[next[0]][next[1]];
				if(i!=stuff.size()-1) 
					if(arr[stuff.get(i+1)[0]][stuff.get(i+1)[1]]==arr[next[0]][next[1]])
						continue;
			}
			last = arr[use[0]][use[1]];
			if(use[1]==1) { //0 = add, 2 =remove
				stuff.add(new int[] {use[0],0});
				stuff.add(new int[] {use[0],2});
			}
			else {
				for(int i=0; i<stuff.size(); i++) {
					if(stuff.get(i)[0]==use[0]) {
						stuff.remove(i);
						i--;
					}
				}
			}
			if(!que.isEmpty()) 
				if(arr[use[0]][use[1]]==arr[que.peek()[0]][que.peek()[1]]) 
					continue;
			Collections.sort(stuff, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					return arr[o1[0]][o1[1]] - arr[o2[0]][o2[1]];
				}
			});
		}
		for(int i=0; i<2501; i++) {
			if(ans[i]>0) {
				System.out.print(i+" ");
				System.out.println(ans[i]);
			}
		}
	}

}
