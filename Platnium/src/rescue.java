import java.util.*;
public class rescue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		int x1 = scanner.nextInt();
		int y1 = scanner.nextInt();
		int min = Integer.MAX_VALUE;
		int[] ans = new int[2];
		for(int i=0; i<M; i++) {
			int x2 = scanner.nextInt();
			int y2 = scanner.nextInt();
			int curr = 1;
			int s1 = x1;
			int s2 = y1;
			int o1 = x2;
			int o2 = y2;
			if(x2<x1) {
				int save= x1;
				x1 = x2;
				x2 = save;
				save = y1;
				y1 = y2;
				y2 = save;
			}
			int L = y1;
			int R = y1 + 2*(x2-x1);
			if(y2>=L&&y2<=R) {
				curr += 2 * (x2-x1);
				if(y1%2==0&&y2%2==1)
					curr++;
				if(y1%2==1&&y2%2==0)
					curr--;
			}
			else {
				curr +=2*(x2-x1);
				curr+=Math.min(Math.abs(y2-R), Math.abs(L-y2));
			}
			x1 = s1;
			y1 = s2;
			if(curr<min) {
				min = curr;
				ans[0] = o1;
				ans[1] = o2;
			}
			if(curr==min) {
				if(x2<ans[0]) {
					ans[0] = o1;
					ans[1] = o2;
				}
				else if(x2==ans[0] && y2 <ans[1]) {
					ans[0] = o1;
					ans[1] = o2;
				}
			}
		}
		System.out.print(ans[0]+" ");
		System.out.println(ans[1]);
		System.out.println(min);
	}

}
