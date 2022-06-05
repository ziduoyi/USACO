import java.util.*;
import java.io.*;
public class cow {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		double time= System.currentTimeMillis();
		Scanner scanner=new Scanner(new File("countcross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("countcross.out")));
		int n=scanner.nextInt();
		int k=scanner.nextInt();
		int r=scanner.nextInt();
		int[][] arr=new int[n*2-1][];
		int[][] save=new int[n*2-1][n*2-1];
		for(int i=0; i<r; i++) {
			int a=scanner.nextInt()*2-2;
			int b=scanner.nextInt()*2-2;
			int c=scanner.nextInt()*2-2;
			int d=scanner.nextInt()*2-2;
			if(c-a==0) {
				if(d-b>0)
					save[a][b+1]=1;
				else 
					save[a][b-1]=1;
			}
			else {
				if(c-a>0)
					save[a+1][b]=1;
				else 
					save[a-1][b]=1;
			}
		}
		
		int[][] cows=new int[k][2];
		for(int i=0; i<k; i++) {
			for(int j=0; j<2; j++) {
				cows[i][j]=scanner.nextInt()*2-2;
			}
		}
		//System.out.println((System.currentTimeMillis()-time)/1000);
		int[][] ways= new int[][] {{-2,0,},{0,2},{2, 0},{0, -2}};
		int dis=0;
		int pair=0;
		for(int i=k-1; i>0; i--) {
			for(int j=i-1; j>-1; j--) {
				for(int l=0; l<n*2-1; l++)
					arr[l]=save[l].clone();

				pair++;
				LinkedList<int[]> list=new LinkedList<>();
				int s1=cows[i][0];
				int s2=cows[i][1];
				int e1=cows[j][0];
				int e2=cows[j][1];
				int[] s=new int[] {s1,s2};
				list.add(s);
				arr[s1][s2]=2;
				while(!list.isEmpty()) {
					boolean b=false;
					int l=list.size();
					for(int m=0; m<l; m++) {
						int[] coo=list.removeFirst();
						if(coo[0]==e1&&coo[1]==e2) {
							b=true;
							dis++;
							break;
						}
						for(int o=0; o<4; o++) {
							int x = coo[0]+ways[o][0], y=coo[1]+ways[o][1];
							if(x>-1  && y>-1 && x<n*2-1 && y<n*2-1) {
								if(arr[x][y]!=2) {
									if(arr[coo[0]+(ways[o][0]/2)][coo[1]+(ways[o][1]/2)]==0) {
										int[] temp=new int[] {x,y};
										list.add(temp);
										arr[x][y]=2;
									}
								}
							}
						}
					}
					if(b)break;
				}
			}
		}
		out.println(pair-dis);
		out.close();
		System.out.println((System.currentTimeMillis()-time)/1000);
	}
}
