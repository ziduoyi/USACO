import java.io.*;
import java.util.*;
public class gates {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("gates.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gates.out")));
		int len=Integer.parseInt(br.readLine());
		String str=br.readLine();

		int[][] arr=new int[len*2+1][len*2+1];
		int[] pos=new int[] {len,len};
		arr[len][len]=1;
		for(int i=0; i<len; i++) {
			char s=str.charAt(i);
			if(s=='N') {
				pos[0]--;
				arr[pos[0]][pos[1]]=1;
				continue;
			}
			if(s=='S') {
				pos[0]++;
				arr[pos[0]][pos[1]]=1;
				continue;
			}
			if(s=='E') {
				pos[1]++;
				arr[pos[0]][pos[1]]=1;
				continue;
			}
			if(s=='W') {
				pos[1]--;
				arr[pos[0]][pos[1]]=1;
				continue;
			}
			
		}
		LinkedList<int[]>list=new LinkedList<>();
		int[][] ways= new int[][] {{-1,0,},{0,1},{1, 0},{0, -1}};
		int[] s=new int[] {0,0};
		list.add(s);
		while(!list.isEmpty()) {
			int n=list.size();
			for(int i=0; i<n; i++) {
				int[] coo=list.removeFirst();
					for(int j=0; j<4; j++) {
						int x=coo[0]+ways[j][0];
						int y=coo[1]+ways[j][1];
						if(x>-1&&x<len*2+1&&y>-1&&y<len*2+1) {
							if(arr[x][y]==0&&arr[x][y]!=2) {
								int[] b=new int[] {x,y};
								arr[x][y]=2;
								list.add(b);
						}
					}
				}
			}
		}
		int count =0;
		int ter=0;

		boolean o=false;
		for(int i=0; i<len*2+1; i++) {
			for(int j=0; j<len*2+1; j++) {
				if(arr[i][j]!=1&&o==true) {
					o=false;
					if(ter%2==1)
						count+=ter/2+1;
					else
						count+=ter/2;
					ter=0;
				}
				if(arr[i][j]==2)
					continue;
				if(arr[i][j]==1) {
					if(arr[i+1][j]==1&&arr[i][j+1]==1&&arr[i+1][j+1]==1) {
						count++;
						//o=true;
					}
					continue;
				}
				if(arr[i][j]==0) {
					LinkedList<int[]>lis=new LinkedList<>();
					int[] a=new int[]{i,j};
					lis.add(a);
					count++;
					while(!lis.isEmpty()) {
						int n=lis.size();
						for(int k=0; k<n; k++) {
							int[] coo=lis.removeFirst();
								for(int l=0; l<4; l++) {
									int x=coo[0]+ways[l][0];
									int y=coo[1]+ways[l][1];
									if(arr[x][y]==0) {
										int[] b=new int[] {x,y};
										arr[x][y]=2;
										list.add(b);
									}
							}
						}
					}
				}
				
			}
		}

		out.println(count);
		out.close();
	}

}
