import java.util.*;
import java.io.*;
public class backforth {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("backforth.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("backforth.out")));
		LinkedList<Integer> list=new LinkedList<>();
		int[] b1=new int[12];
		int[] b2=new int[12];
		int[] b1s=new int[12];
		int[] b2s=new int[12];
		for(int i=0; i<10; i++) {
			b1[i]=scanner.nextInt();
		}
		for(int i=0; i<10; i++) {
			b2[i]=scanner.nextInt();
		}
		b1s=b1.clone();
		b2s=b2.clone();
		for(int i=0; i<10; i++) {
			 for(int j=0; j<11; j++) {
				 for(int k=0; k<11; k++) {
					 for(int l=0; l<12; l++) {
						b1=b1s.clone();
						b2=b2s.clone();
						 b2[10]=b1[i];
						 int tol=(-1*b1[i]);
						 if(b1[i]==0)
							 continue;
						 b1[i]=0;
						 b1[10]=b2[j];
						 tol+=b2[j];
						 if(b2[j]==0)
							 continue;
						 b2[j]=0;
						 b2[10]=b1[k];
						 tol-=b1[k];
						 if(b1[k]==0)
							 continue;
						 b1[k]=0;
						 b1[10]=b2[l];
						 tol+=b2[l];
						 if(b2[l]==0)
							 continue;
						 list.add(tol);		 
					 }
				 }
			 }
		}
		Collections.sort(list);
		int ans=0;
		int old=-975393884;
		while(!list.isEmpty()) {
			int n=list.removeFirst();
			if(n!=old)
				ans++;
			old=n;
		}
		out.println(ans);
		out.close();
	}

}
