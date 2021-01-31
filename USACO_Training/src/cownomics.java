import java.util.*;
import java.io.*;
public class cownomics {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("cownomics.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int num=Integer.parseInt(st.nextToken());
		int len=Integer.parseInt(st.nextToken());
		String[]arr=new String[num*2];
		for(int i=0; i<num*2; i++) {
			arr[i]=br.readLine();
		}

		int f=0;
		int l=len;
		int min=Integer.MAX_VALUE;
		while(f+1<l) {
			int mid=(f+l)/2;
			boolean b=false;
			for(int i=0; i<len-mid+1; i++) {
				Set<String> genome1=new HashSet<>();
				Set<String> genome2=new HashSet<>();
				for(int j=0; j<num; j++) {
					String str=arr[j].substring(i, i+mid);
					genome1.add(str);
				}
				for(int j=num; j<num*2; j++) {
					String str=arr[j].substring(i, i+mid);
					genome2.add(str);
				}
				int tol=genome1.size()+genome2.size();
				genome1.addAll(genome2);
				
				if(tol==genome1.size()) {
					b=true;
					break;
				}
			}
			if(b==true) {
				l=mid;
				min=Math.min(min, mid);
			}
			else {
				f=mid;
			}
		}
		out.println(min);
		out.close();
	}

	/*
		int work=0;
		for(int i=0; i<len; i++) {
			for(int j=i+1; j<len; j++) {
				for(int k=j+1; k<len; k++) {
					Set<String> set=new HashSet<>();
					for(int l=0; l<num; l++) {
						String s=""+arr[l][i]+arr[l][j]+arr[l][k];
						set.add(s);
					}
					int a=set.size();
					Set<String> set2=new HashSet<>();
					for(int l=num; l<num*2; l++) {
						String s=""+arr[l][i]+arr[l][j]+arr[l][k];
						set2.add(s);
					}
					int b=set2.size();
					set.addAll(set2);
					if(set.size()==a+b)
						work++;
				}
			}
		}
		out.println(work);
		out.close();
		
	*/

}
