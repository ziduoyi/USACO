import java.io.*;
import java.util.*;
public class prob20 {
	static int[][] ans;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner br = new Scanner(System.in);
		int k = br.nextInt();
		int[] colors = new int[] {4, br.nextInt(), br.nextInt(), br.nextInt()};
		System.out.print(recursion(colors, 0, k, new int[] {0,0,0,0}));
	}
	static long recursion(int[] colors, int pos, int k, int[] fingers) {
		if(pos==4) {
			int cnt =0;
			for(int i=0; i<4; i++)if(fingers[i]>0) cnt++;
			if(cnt==k)return 1;
			return 0;
		}
		long total = 0;
		for(int i=0; i<4; i++) {
			int[] next = colors.clone();
			int[] thing = fingers.clone();
			if(colors[i]>0) {
				next[i]--;
				thing[pos] = i;
				total += recursion(next, pos+1, k, thing);
			}
		}
		return total;
	}
}
