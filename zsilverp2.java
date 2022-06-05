import java.util.*;
import java.io.*;
public class zsilverp2 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("perimeter.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
		int side=Integer.parseInt(br.readLine());
		int[][] arr=new int[side][side];
		for(int i=0; i<side; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			char[] c=st.nextToken().toCharArray();
			for(int j =0; j<side; j++) {
				if(c[j]!='.')
					arr[i][j]=1;
			}
		}
		int[][] direct = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
		int area=0;
		int perimeter=Integer.MAX_VALUE;
		for(int i=0; i<side; i++) {
			for(int j=0; j<side; j++) {
				if(arr[i][j]==1) {
					LinkedList<int[]> list=new LinkedList<>();
					int[]e=new int[] {i,j};
					arr[i][j]=2;
					list.add(e);
					int curra=1;
					int currp=0;
					while(!list.isEmpty()) {
						int num=list.size();
						for(int k=0; k<num; k++) {
							int[] use=list.removeFirst();
							for(int l=0; l<4; l++) {
								int r=use[0]+direct[l][0];
								int c=use[1]+direct[l][1];
								if(r<0||r>=side||c<0||c>=side) {
									currp++;
									continue;
								}
								if(arr[r][c]==0) {
									currp++;
									continue;
								}
								if(arr[r][c]==1) {
									arr[r][c]=2;
									curra++;
									int[] temp=new int[] {r,c};
									list.add(temp);
								}
							}
						}
					}
					if(area<=curra) {
						if(area==curra) {
							perimeter=Math.min(perimeter, currp);
						}
						else {
							area=curra;
							perimeter=currp;
						}
					}
				}
			}
		}
		out.print(area+" ");
		out.println(perimeter);
		out.close();
	}

}