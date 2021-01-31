import java.util.*;
import java.io.*;
public class online2 {

	public static void main(String[] args)throws IOException{
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("pails.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
		int b1=scanner.nextInt();
		int b2=scanner.nextInt();
		boolean[][] dp=new boolean[b1+1][b2+1];
		dp[0][0]=true;
		dp[b1][b2]=true;
		int oper=scanner.nextInt();
		int tar=scanner.nextInt();
		LinkedList<int[]> list=new LinkedList<>();
		int[] a=new int[] {0,0};
		list.add(a);
		int steps=0;
		while(!list.isEmpty()) {
			int s=list.size();
			for(int i=0;i<s;i++) {
			int[] use=list.removeFirst();
			int[] old=use.clone();
				boolean b=false;
				if(dp[b1][use[1]]==false) {
					dp[b1][use[1]]=true;
					int[] temp=new int[] {b1, use[1]};
					list.add(temp);
				}
				if(dp[use[0]][b2]==false) {
					dp[use[0]][b2]=true;
					int[] temp=new int[] {use[0], b2};
					list.add(temp);
				}
				if(dp[0][use[1]]==false) {
					dp[0][use[1]]=true;
					int[] temp=new int[] {0, use[1]};
					list.add(temp);
				}
				if(dp[use[0]][0]==false) {
					dp[use[0]][0]=true;
					int[] temp=new int[] {b1, 0};
					list.add(temp);
				}
				int dif=b2-use[1];
				if(dif>use[0]) {
					use[1]+=use[0];
					use[0]=0;
				}
				else {
					use[0]-=dif;
					use[1]=b2;
				}
				if(dp[use[0]][use[1]]==false) {
					dp[use[0]][use[1]]=true;
					int[] temp=new int[] {use[0], use[1]};
					list.add(temp);
				}
				use=old.clone();
				int dif2=b1-use[0];
				if(dif2>use[1]) {
					use[0]+=use[1];
					use[1]=0;
				}
				else {
					use[1]-=dif2;
					use[0]=b1;
				}
				if(dp[use[0]][use[1]]==false) {
					dp[use[0]][use[1]]=true;
					int[] temp=new int[] {use[0], use[1]};
					list.add(temp);
				}
			}
			steps++;
			if(steps>=oper)
				break;
		}
		int min=Integer.MAX_VALUE;
		for(int i=0; i<b1+1; i++) {
			for(int j=0; j<b2+1; j++) {
				if(dp[i][j]==true)
					min=Math.min(min, Math.abs(i+j-tar));
			}
		}
		out.println(min);
		out.close();
	}
}