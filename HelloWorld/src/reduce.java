import java.io.*;
import java.util.*;
public class reduce {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("reduce.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));
		int num=Integer.parseInt(br.readLine());
			
//		int[][] minx=new int[4][2];
//		int[][] maxx=new int[4][2];
//		int[][] miny=new int[4][2];
//		int[][] maxy=new int[4][2];
		int[][] arr=new int[num][2];
		for(int i=0; i<num; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
		}

		//out.println(area);
		out.close();
		
		
//		for(int i=0;i<4;i++) {
//			for(int j=i+1;j<4;j++) {
//				if(minx[i][0]==miny[j][0] && minx[i][1]==miny[j][1]) {
//					miny[j][0]=-1;
//					miny[j][1]=-1;
			//	}
	//		}
//		}
/*
		for(int i=0;i<4;i++) {
			for(int j=i+1;j<4;j++) {
				if(maxx[i][0]==maxy[j][0] && maxx[i][1]==maxy[j][1]) {
					maxy[j][0]=-1;
					maxy[j][1]=-1;
				}
			}
		}
		int max=Integer.MAX_VALUE;
		
		
		
		
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				for(int k=0; k<4; k++) {
					for(int l=0; l<4;l++) {
						if(i+j+k+l!=6)
							continue;
						if(i!=k||j!=l)
							continue;
						int x=Math.abs(minx[i]-maxx[j]);
						boolean b=false;
						for(int m=0; m<num; m++) {
							if(x==arr[m][0]) {
								if(arr[m][1]>maxy[j]||arr[m][1]<miny[i]) {
									b=true;
								}
								break;
							}
						}
						int y=Math.abs(miny[k]-maxy[l]);
						for(int m=0; m<num; m++) {
							if(y==arr[m][1]) {
								if(arr[m][0]>maxx[j]||arr[m][0]<minx[i]) {
									b=true;
								}
								break;
							}
						}
						if(b==true)
							continue;
						if(x*y>0)
							max=Math.min(max, x*y);
					}
				}
			}
		}
		out.println(max);
		out.close();
	}
*/
/*	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("reduce.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));
		int num=Integer.parseInt(br.readLine());
		int[] minx=new int[4];
		int[] maxx=new int[4];
		int[] miny=new int[4];
		int[] maxy=new int[4];
		int[][] arr=new int[num][2];
		for(int i=0; i<num; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr,(a,b)->a[0]-b[0]);
		for(int i=0; i<4; i++) {
			minx[i]=arr[i][0];
		}
		Arrays.sort(arr,(a,b)->b[0]-a[0]);
		for(int i=0; i<4; i++) {
			maxx[i]=arr[i][0];
		}
		Arrays.sort(arr,(a,b)->a[1]-b[1]);
		for(int i=0; i<4; i++) {
			miny[i]=arr[i][1];
		}
		Arrays.sort(arr,(a,b)->b[1]-a[1]);
		for(int i=0;i<4; i++) {
			maxy[i]=arr[i][1];
		}
		int max=Integer.MAX_VALUE;
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				for(int k=0; k<4; k++) {
					for(int l=0; l<4;l++) {
						if(i+j+k+l!=6)
							continue;
						if(i!=k||j!=l)
							continue;
						int x=Math.abs(minx[i]-maxx[j]);
						boolean b=false;
						for(int m=0; m<num; m++) {
							if(x==arr[m][0]) {
								if(arr[m][1]>maxy[j]||arr[m][1]<miny[i]) {
									b=true;
								}
								break;
							}
						}
						int y=Math.abs(miny[k]-maxy[l]);
						for(int m=0; m<num; m++) {
							if(y==arr[m][1]) {
								if(arr[m][0]>maxx[j]||arr[m][0]<minx[i]) {
									b=true;
								}
								break;
							}
						}
						if(b==true)
							continue;
						if(x*y>0)
							max=Math.min(max, x*y);
					}
				}
			}
		}
		out.println(max);
		out.close();
	}*/
	}
}
