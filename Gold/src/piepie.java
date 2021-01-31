import java.io.*;
import java.util.*;
public class piepie {
/*
			if(bes[i][1]==0) {
				int[] temp=new int[] {1,i,0};
				list.add(temp);
				dist[i][0]=0;
			} 
			if(els[i][1]==0) {
				int[] temp=new int[] {2,i,0};
				list.add(temp);
				dist[i][1]=0;
			}
 */
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		long t1=System.currentTimeMillis();
		BufferedReader br=new BufferedReader(new FileReader("piepie.in"));
		PrintWriter out= new PrintWriter(new BufferedWriter(new FileWriter("piepie.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int D= Integer.parseInt(st.nextToken());
		int[][] bes =new int[N][3];
		int[] bkey=new int[N];
		int[][] els =new int[N][2];
		int[] ekey=new int[N];
		int[][] dist = new int[N][2];
		for(int i=0; i<N; i++) {
			for(int j=0; j<2; j++) {
				dist[i][j]=Integer.MAX_VALUE;
			}
		}
		LinkedList<int[]> list=new LinkedList<>(); // {side,node #,step}
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			bes[i][0]=Integer.parseInt(st.nextToken());
			bes[i][1]=Integer.parseInt(st.nextToken());
			bes[i][2]=i;
		}
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			els[i][0]=Integer.parseInt(st.nextToken());
			els[i][1]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(bes, (a,b)->a[1]-b[1]);
		Arrays.sort(els, (a,b)->a[0]-b[0]);
		for(int i=0; i<N; i++) {
			bkey[i]= bes[i][1];
			if(bes[i][1]==0&&bes[i][0]!=0) {
				int[] temp=new int[] {1,i,0};
				list.add(temp);
				dist[i][0]=0;				
			}
			if(bes[i][1]==0&&bes[i][0]==0) {
				dist[i][0]=0;				
			}
			ekey[i]=els[i][0];
			if(els[i][0]==0&&els[i][1]!=0) {
				int[] temp=new int[] {2,i,0};
				list.add(temp);
				dist[i][1]=0;				
			}
		}
		long t2 =System.currentTimeMillis();
		System.out.println(t2-t1);
		while(!list.isEmpty()) {
			int[] arr=list.removeFirst();
			if(arr[0]==1) {
				int comp = bes[arr[1]][0];
				int s = Arrays.binarySearch(ekey, comp-D);
				int e =Arrays.binarySearch(ekey, comp);
				if(s<0) {
					s++;
					s*=-1;
				}
				if(e<0) {
					e++;
					e*=-1;
				}
				int c=s-1;
				if(c>=0) {
					while(ekey[c]==comp-D) {
						if(dist[c][1]>arr[2]+1) {
							int[] temp=new int[] {2,c,arr[2]+1};
							list.add(temp);
							dist[c][1]=arr[2]+1;
						}
						c--;
						if(c==-1)
							break;
					}
				}
				if(e!=ekey.length) {
					if(ekey[e]>comp)
						e--;
				}
				if(e==ekey.length)
					e--;
				int z=e;
				for(int i=z+1; i< ekey.length; i++) {
					if(ekey[i]==comp)
						e++;
					else
						break;
				}

				for(int i=s; i<=e; i++) {
					if(dist[i][1]>arr[2]+1) {
						int[] temp=new int[] {2,i,arr[2]+1};
						list.add(temp);
						dist[i][1]=arr[2]+1;
					}					
				}
			}
			else {
				int comp = els[arr[1]][1];
				int s = Arrays.binarySearch(bkey, comp-D);
				int e =Arrays.binarySearch(bkey, comp);
				if(s<0) {
					s++;
					s*=-1;
				}
				if(e<0) {
					e++;
					e*=-1;
				}
				int c=s-1;
				if(c>=0) {
					while(bkey[c]==comp-D) {
						if(dist[c][0]>arr[2]+1) {
							int[] temp=new int[] {1,c,arr[2]+1};
							list.add(temp);
							dist[c][0]=arr[2]+1;
						}
						c--;
						if(c==-1)
							break;
					}
				}
				if(e!=bkey.length) {
					if(bkey[e]>comp)
						e--;
				}
				if(e==bkey.length)
					e--;
				int z=e;
				for(int i=z+1; i< bkey.length; i++) {
					if(bkey[i]==comp)
						e++;
					else
						break;
				}
				for(int i=s; i<=e; i++) {
					if(dist[i][0]>arr[2]+1) {
						int[] temp=new int[] {1,i,arr[2]+1};
						list.add(temp);
						dist[i][0]=arr[2]+1;
					}					
				}
			}
		}
		System.out.println(System.currentTimeMillis()-t2);
		int[] ans=new int[N];
		for(int i=0; i<N; i++) {
			if(dist[i][0]==Integer.MAX_VALUE)
				ans[bes[i][2]]=-2;
			else
				ans[bes[i][2]]=dist[i][0];
		}
		for(int i=0; i<N; i++)
			out.println(ans[i]+1);
		out.close();
	}
}
