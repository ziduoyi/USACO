import java.io.*;
import java.util.*;
public class silverp3 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("mooyomooyo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mooyomooyo.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int high=Integer.parseInt(st.nextToken());
		int need =Integer.parseInt(st.nextToken());
		int[][] arr=new int[high][10];
		for(int i=0; i<high; i++) {
			st=new StringTokenizer(br.readLine());
			String s=st.nextToken().toString();
			for(int j=0; j<10; j++) {
				arr[i][j]=s.charAt(j)-'0';
			}
		}
		boolean move =true;
		int[][] direct = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
		while(move==true) {
			move=false;
			for(int i=0; i<high; i++) {
				for(int j=0; j<10; j++) {
					if(arr[i][j]==0)
						continue;
					int z=arr[i][j];
					int am=1;
					LinkedList<int[]> list=new LinkedList<>();
					LinkedList<int[]> pos=new LinkedList<>();
					int[]s =new int[]{i,j};
					arr[i][j]=0;
					list.add(s);
					pos.add(s);
					while(!list.isEmpty()) {
						int n=list.size();
						for(int k=0; k<n; k++) {
							int[] use=list.removeFirst();
//						
							for(int l=0; l<4; l++) {
								int x=use[0]+direct[l][0];
								int y=use[1]+direct[l][1];
								if(x>-1&&x<high&&y>-1&&y<10) {
									if(arr[x][y]==z) {
										int[] coo=new int[] {x,y};
										arr[x][y]=0;
										list.add(coo);
										pos.add(coo);
										am++;
									}
								}
							}
						}
					}
					if(am<need) {
						while(!pos.isEmpty()) {
							int[]a=pos.removeFirst();
							arr[a[0]][a[1]]=z;
						}
					}else {
						move=true;

					}
				}

			}
			int[] check=new int [10];
			for(int k=high-1; k>-1; k--) {
				for(int l=0; l<10; l++) {
					if(check[l]==1)
						continue;
					if(arr[k][l]!=0)
						continue;
					boolean b=false;
					for(int m=k-1; m>-1; m--) {
						if(arr[m][l]!=0) {
							arr[k][l]=arr[m][l];
							arr[m][l]=0;
							b=true;
							break;
						}
					}
					if(b==false) {
						check[l]=1;
					}
				}
			}
		}
		for(int i=0; i<high; i++) {
			for(int j=0; j<10; j++) {
				out.print(arr[i][j]);
			}
			out.println();
		}
		out.close();
	}

}