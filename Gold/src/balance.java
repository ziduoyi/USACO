import java.io.*;
import java.util.*;
public class balance {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("balance.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("balance.out")));
		int N=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		int[] nums1=new int[N];
		int[] nums2=new int[N];
		for(int i=0; i<N; i++)nums1[i]=Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++)nums2[i]=Integer.parseInt(st.nextToken());
		int[] sum1=new int[N];
		int[] sum2=new int[N];
		long tol1=0;
		long tol2=0;
		for(int i=N-1; i>-1; i--) {
			if(i!=N-1)
				sum1[i]=sum1[i+1];
			if(i!=N-1)
				sum2[i]=sum2[i+1];
			if(nums1[i]==0)
				sum1[i]++;
			if(nums2[i]==0)
				sum2[i]++;
			if(nums1[i]==1)
				tol1+=sum1[i];
			if(nums2[i]==1)
				tol2+=sum2[i];
		}
		int m=N-sum1[0];
		int n=sum2[0];
		long min=Math.abs(tol1-tol2);
		int l=N-1;
		int r=0;
		long count=0;
		int choice=-1;
		int changel=0;
		int changer=0;
		for(int i=1; i<N; i++) { 
			tol1+=changel;
			tol2+=changer;
			if(m>n) {
				if(tol1>tol2&&choice==-1||choice==0&&tol1>tol2) {//0 1
					count+=N-1-l;
					count+=r;
					if(nums1[l]!=0||nums2[r]!=1) {
						boolean b=false;
						while(nums1[l]!=0) {
							if(l==0) {
								b=true;
								break;
							}
							count++;
							l--;
							changel++;
							tol1++;
						}
						while(nums2[r]!=1) {
							if(r==N-1) {
								b=true;
								break;
							}
							count++;
							r++;
							changer++;
							tol2++;
						}
						if(b==true)break;
					}
					tol1-=m;
					tol2-=n;
					count++;
					min=Math.min(count +Math.abs(tol1-tol2), min);
					if(l==0)break;
					if(r==N-1)break;
					l--;
					r++;
					choice =0;
					n++;
					m++;
				}
				else if(choice==1||choice==-1){// 1 0
					count+=N-1-l;
					count+=r;
					if(nums1[l]!=1||nums2[r]!=0) {
						boolean b=false;
						while(nums1[l]!=1) {
							if(l==0) {
								b=true;
								break;
							}
							count++;
							l--;
							tol1--;
							changel--;
						}
						while(nums2[r]!=0) {
							if(r==N-1) {
								b=true;
								break;
							}
							count++;
							r++;
							tol2--;
							changer--;
						}
						if(b==true)break;
					}
					tol1+=m;
					tol2+=n;
					count++;
					min=Math.min(count +Math.abs(tol1-tol2), min);
					if(l==0)break;
					if(r==N-1)break;
					l--;
					r++;
					choice=1;
					n--;
					m--;
				}
			}
			else {
				if(tol1>tol2&&choice==2||tol1>tol2&&choice==-1) {//1 0
					count+=N-1-l;
					count+=r;
					if(nums1[l]!=1||nums2[r]!=0) {
						boolean b=false;
						while(nums1[l]!=1) {
							if(l==0) {
								b=true;
								break;
							}
							count++;
							l--;
							tol1--;
							changel--;
						}
						while(nums2[r]!=0) {
							if(r==N-1) {
								b=true;
								break;
							}
							count++;
							r++;
							tol2--;
							changer--;
						}
						if(b==true)break;
					}
					tol1+=m;
					tol2+=n;
					count++;
					min=Math.min(count +Math.abs(tol1-tol2), min);
					if(l==0)break;
					if(r==N-1)break;
					l--;
					r++;
					choice=2;
					n--;
					m--;
				}
				else if(choice==3||choice==-1){//0 1
					count+=N-1-l;
					count+=r;
					if(nums1[l]!=0||nums2[r]!=1) {
						boolean b=false;
						while(nums1[l]!=0) {
							if(l==0) {
								b=true;
								break;
							}
							count++;
							l--;
							tol1++;
							changel++;
						}
						while(nums2[r]!=1) {
							if(r==N-1) {
								b=true;
								break;
							}
							count++;
							r++;
							tol2++;
							changer++;
						}
						if(b==true)break;
					}
					tol1-=m;
					tol2-=n;
					count++;
					min=Math.min(count +Math.abs(tol1-tol2), min);
					if(l==0)break;
					if(r==N-1)break;
					l--;
					r++;
					choice=3;
					n++;
					m++;
					
				}
			}
		}
		out.println(min);
		out.close();
	}
}
