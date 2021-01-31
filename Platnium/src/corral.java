import java.util.*;
public class corral {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int C = scanner.nextInt();
		int N = scanner.nextInt();
		int[][] arr = new int[N][2];
		for(int i=0; i<N; i++) {
			arr[i][0] = scanner.nextInt();
			arr[i][1] = scanner.nextInt();
		}
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
			return o1[0] - o2[0];
			}
		});
		int l =0;
		int r = 10001;
		int ans = Integer.MAX_VALUE;
		while(l<=r) {
			int m = (l+r)/2;
			boolean b = false;
			for(int i =0; i<N; i++) {
				ArrayList<Integer> al = new ArrayList<>();
				int find = arr[i][0] + m;
				for(int j=i; j<N; j++) {
					if(find>=arr[j][0])
						al.add(arr[j][1]);
					else
						break;
				}
				Collections.sort(al);
				int s = al.size();
				int last = -1;
				for(int j=0; j<s; j++) {
					int pos =-1;
					for(int k = last+1; k<s; k++) {
						if(al.get(j)+m>=al.get(k)) {
							pos = k;
						}
						else
							break;
					}
					if(pos-j+1>=C) {
						b = true;
						break;
					}
					last = pos;
				}
				if(b==true)
					break;
			}
			if(b==true) {
				r = m-1;
				ans = Math.min(ans, m);
			}
			else
				l = m+1;
		}
		System.out.println(ans+1);
	}

}
