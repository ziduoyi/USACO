import java.util.*;
import java.io.*;
public class radio {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("radio.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("radio.out")));
		int fn=scanner.nextInt();
		int bn=scanner.nextInt();
		int[] fpos=new int[2];
		fpos[0]=scanner.nextInt();
		fpos[1]=scanner.nextInt();
		int[] bpos=new int[2];
		bpos[0]=scanner.nextInt();
		bpos[1]=scanner.nextInt();
		int[] old=bpos.clone();//only need for bessie
		int[][] dp=new int[fn+1][bn+1];
		char[] fpath=scanner.next().toCharArray();
		char[] bpath=scanner.next().toCharArray();
		for(int i=0; i<fn+1; i++) {
			int[] fadd=new int[2];
			if(i!=0) {
				fadd=findPath(fpath[i-1]).clone();
			}
			fpos[0]+=fadd[0];
			fpos[1]+=fadd[1];
			for(int j=0; j<bn+1; j++) {
				if(i==0&&j==0)continue;
				int[] badd= new int[2];
				if(j!=0)
					badd=findPath(bpath[j-1]).clone();
				bpos[0]+=badd[0];
				bpos[1]+=badd[1];
				dp[i][j]=((fpos[0]-bpos[0])*(fpos[0]-bpos[0]))+((fpos[1]-bpos[1])*(fpos[1]-bpos[1]));
			}
			bpos=old.clone();
		}
		int[][] fast=new int[fn+1][bn+1];
		for(int i=0; i<fn+1; i++) {
			Arrays.fill(fast[i], Integer.MAX_VALUE);
		}
		fast[0][0]=0;
		int[][] direct=new int[][]{{1,0},{0,1},{1,1}};
		LinkedList<int[]> list=new LinkedList<>();
		int[] t=new int[] {0,0};
		list.add(t);
		while(!list.isEmpty()) {
			int[] use=list.removeFirst();
			for(int i=0; i<3; i++) {
				int x=use[0]+direct[i][0];
				int y=use[1]+direct[i][1];
				if(x>-1&&x<fn+1&&y>-1&&y<bn+1) {
					if(fast[use[0]][use[1]]+dp[x][y]<fast[x][y]) {
						fast[x][y]=fast[use[0]][use[1]]+dp[x][y];
						int[] temp=new int[] {x,y};
						list.add(temp);
					}
				}
			}
		}
		int min1=Integer.MAX_VALUE;
		int min2=Integer.MAX_VALUE;
		for(int i=0; i<fn; i++) {
			min1=Math.min(min1, fast[i][bn]);
		}
		for(int i=0; i<bn; i++) {
			min2=Math.min(min2, fast[fn][i]);
		}
		out.println(Math.min(min1+min2, fast[fn][bn]));
		out.close();
	}
	static int[] findPath(char c) {
		if(c=='N') {
			int[] temp=new int[] {0,1};
			return temp;
		}
		if(c=='S') {
			int[] temp=new int[] {0,-1};
			return temp;
		}
		if(c=='E') {
			int[] temp=new int[] {1,0};
			return temp;
		}
		if(c=='W') {
			int[] temp=new int[] {-1,0};
			return temp;
		}
		return null;
	}
}
