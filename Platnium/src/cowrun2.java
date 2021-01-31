import java.io.*;
import java.util.*;
public class cowrun2 {
	static int[][] cards;
	static String comp;
	static int[] stuff;
	static int N;
	static int M;
	static int K;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		String idk =br.readLine();
		stuff = new int[N];
		for(int i=0; i<N; i++) {
			if(idk.charAt(i)=='B')
				stuff[i] = 2;
		}
		cards = new int[N][8];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<8; j++) 
				cards[i][j] = Integer.parseInt(st.nextToken());
		}
		String ans = dfs(0, new String());
		if(ans!=null) {
			for(int i =0; i<ans.length(); i++) {
				if(ans.charAt(i) == '1')
					System.out.print('B');
				else
					System.out.print('T');
			}
		}
		else {
			ans = dfs2(0, new String());
			if(ans!=null) {
				for(int i =0; i<ans.length(); i++) {
					if(ans.charAt(i) == '1')
						System.out.print('B');
					else
						System.out.print('T');
				}
			}	
		}
		System.out.println();
	}
	static String dfs2(int node, String curr) {// 1 = B, 0 = T
		if(node == N) {
			comp = curr;
			if(check2(0, new String(), 0)==true)
				return curr;
			return null;
		}
		else
			for(int i=1; i>-1; i--) {
				String str = dfs2(node+1, curr+i);
				if(str!=null)
					return str;
			}
		return null;
	}
	static boolean check2(int node, String curr, long dist) {
		if(node==N) {
			if(dist<=K||dist>=M-K)
				return true;
			return false;
		}
		else {
			long a = dist*cards[node][(comp.charAt(node)-'0')*4 + stuff[node]];
			long b = cards[node][(comp.charAt(node)-'0')*4 + stuff[node]+1];
			long c = dist;
			if(check2(node+1, curr+stuff[node], (c+a+b)%(long)M)==false)
				return false;
		}
		return true;
	}
	static String dfs(int node, String curr) {// 1 = B, 0 = T
		if(node == N) {
			comp = curr;
			if(check(0, new String(), 0)==true)
				return curr;
			return null;
		}
		else
			for(int i=1; i>-1; i--) {
				String str = dfs(node+1, curr+i);
				if(str!=null)
					return str;
			}
		return null;
	}
	static boolean check(int node, String curr, long dist) {
		if(node==N) {
			if(dist<=K||dist>=M-K)
				return true;
			return false;
		}
		else 
			for(int i=0; i<4; i+=2) {
				long a = dist*cards[node][(comp.charAt(node)-'0')*4 + i];
				long b = cards[node][(comp.charAt(node)-'0')*4 + i+1];
				long c = dist;
				if(check(node+1, curr+i, (c+a+b)%(long)M)==false)
					return false;
			}
		return true;
	}
}
