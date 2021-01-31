import java.io.*;
import java.util.*;

public class milk {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scanner= new Scanner(new File("milkorder.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		int K=scanner.nextInt();
		int[] arr = new int[N+1];
		int[] order= new int[M];

		for(int i=0; i<M; i++) {
			order[i]=scanner.nextInt();
		}
		for(int i=0;i<K;i++) {
			int cow=scanner.nextInt();
			int pos = scanner.nextInt();
			arr[pos] = cow;
			if(cow==1) {
				out.println(pos);
				out.close();
				return;
			}
		}
		int pos1=-1;
		for(int i=0; i<M; i++) {
			if(order[i]==1) {
				pos1=i;
				break;
			}
		}
		
		if(pos1>=0) {
			for(int i=pos1-1;i>=0;i--) {
				for(int j=1;j<N+1;j++) {
					if(order[i]==arr[j]) {
						if(pos1<i) {
							int j1=0;
							for(int i1=0;i1<=pos1;i1++) {
								while(arr[++j1]!=0) ;
							}
							out.println(j1);
							out.close();
							return;
						}else {
							int j1=j;
							for(int i1=i+1;i1<=pos1;i1++) {
								while(arr[++j1]!=0) ;
							}
							out.println(j1);
							out.close();
							return;
						}
					}
				}
			}
			int j1=0;
			for(int i1=0;i1<=pos1;i1++) {
				while(arr[++j1]!=0) ;
			}
			out.println(j1);
			out.close();
			return;
		}else {
			int lasti=0;
			for(int i=0;i<M;i++) {
				for(int j=1;j<N+1;j++) {
					if(order[i]==arr[j]) {
						
						int i1=i-1;
						for(int j1=j-1;j1>=1;j1--) {
							if(arr[j1]==0) {
								if(i1<lasti) {
									for(int y=1;y<N+1;y++) {
										if(arr[y]==0) {
											out.println(y);
											out.close();
											return;
										}
									}
								}
								arr[j1]=order[i1--];
							}
						}
						lasti=i+1;
					}
				}
			}
			for(int j=1;j<N+1;j++) {
				if(arr[j]==0) {
					out.println(j);
					out.close();
					return;
				}
			}
		}
	}
}
