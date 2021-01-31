import java.util.*;
import java.io.*;
public class lighton {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("lightson.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
		double d= System.currentTimeMillis();
		int n=scanner.nextInt();
		int m=scanner.nextInt();
		int[][] arr=new int[n][n];
		arr[0][0]=11;
		LinkedList<int[] > list=new LinkedList<>();
		int[] pos=new int[] {0,0};
		list.add(pos);
		int rooms=1;
		int[][] com=new int[m][4];
		for(int i=0; i<m; i++) {
			for(int j=0; j<4; j++) {
				com[i][j]=scanner.nextInt()-1;
			}
		}
		int[][] ways= new int[][] {{-1,0,},{0,1},{1, 0},{0, -1}};
		while(!list.isEmpty()) {
			int s=list.size();
			for(int i=0; i<s; i++) {
				int[] num=list.removeFirst();

	
				for(int j=0; j<4; j++) {
					if(num[0]+ways[j][0]>-1 &&num[1]+ways[j][1]>-1&&num[0]+ways[j][0]<n&&num[1]+ways[j][1]<n) {
						if(arr[num[0]+ways[j][0]][num[1]+ways[j][1]]==0) {
							int[] save= new int[] {num[0]+ways[j][0],num[1]+ways[j][1]};
							arr[num[0]+ways[j][0]][num[1]+ways[j][1]]=1;
						}
						if(arr[num[0]+ways[j][0]][num[1]+ways[j][1]]==10) {
							int[] save= new int[] {num[0]+ways[j][0],num[1]+ways[j][1]};
							arr[num[0]+ways[j][0]][num[1]+ways[j][1]]=11;
							list.add(save);
						}
					}
				}
				for(int j=0; j<m; j++) {
					if(com[j][0]==num[0]&&com[j][1]==num[1]) {
						if(arr[com[j][2]][com[j][3]]!=11) {
							if(arr[com[j][2]][com[j][3]]==1) {
								arr[com[j][2]][com[j][3]]=11;
								int[] save=new int[] {com[j][2],com[j][3]};
								rooms++;
								list.add(save);
							}
							else if(arr[com[j][2]][com[j][3]]==0){
								arr[com[j][2]][com[j][3]]=10;
								rooms++;
							}
						}
					}
				}
			}
		}
		out.println(rooms);
		out.close();
		double d2= System.currentTimeMillis();
		System.out.println((d2-d)/1000);
		return;
	}

}
