import java.util.*;
import java.io.*;
public class angry {

	/*
	static int[] calPower(int[] p, int[] h, int num) {
		p[1]=h[1]-h[0];
		int next = p[1]-1;
		int amount =next;
		for(int i=2;i<num;i++) {
			int com=h[i]-h[i-1];
			if(com<=next) {
				p[i]=p[i-1];
				next--;
				continue;
			}else {
				if(com==next+1) {
					p[i]=p[i-1]+1;
					next++;
				}
				else {
					p[i]=com;
					next=com-1;
				}
			}
		}
		return p;
	}*/
	static void calPower(int[][] p, int[] h, int num) {
		for(int i=1;i<num;i++) {
			int j=i;
			while(p[j-1][1]>=h[i])
				j--;
			if(h[i]<=p[j][1])
				p[i][0]=p[j][0]+1;
			else if(h[i]-h[i-1]<p[i-1][0])
				p[i][0]=p[i-1][0]+1;
			else
				p[i][0]=h[i]-h[i-1];
			p[i][1]=p[i][0]+h[i];
		}
	}
	
	static void calPowerR(int[][] p, int[] h, int num) {
		p[num-1][1] = Integer.MAX_VALUE;
		for(int i=num-2;i>=0;i--) {
			int j=i;
			p[i][1] = Integer.MAX_VALUE;
			while(p[j+1][1]<=h[i])
				j++;
			if(h[i]>=p[j][1])
				p[i][0]=p[j][0]+1;
			else if(h[i+1]-h[i]<p[i+1][0])
				p[i][0]=p[i+1][0]+1;
			else
				p[i][0]=h[i+1]-h[i];
			p[i][1]=p[i][0]+h[i];
		}
	}
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("angry.in"));
		PrintWriter out =new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		int num=Integer.parseInt(br.readLine());
		int[] bale=new int[num];
		for(int i=0; i<num; i++) {
			bale[i]=2*Integer.parseInt(br.readLine());
		}
		Arrays.sort(bale);
		int[][] left=new int[num][2];
		int[][] right=new int[num][2];
		calPower(left, bale, num);

//		int[] bale2 = new int[num];
//		for(int i=0;i<num;i++)
//			right[num-1-i]=bale[i];
		calPowerR(right, bale, num);
//		for(int i=0;i<num;i++)
//			right[i]=bale2[num-1-i];
		int[] last=new int[num];
		int min=Integer.MAX_VALUE;
		int pos=-1;
		for(int i=0; i<num; i++) {
			last[i]=Math.max(left[i][0], right[i][0]);
			if(min>last[i]) {
				min=last[i];
				pos=i;
			}
		}

		double res=0;
		for(int i=0;i<=pos;i++) 
			if(left[i][0]>res)//res=Math.max(res,left[i][0]);
				res=left[i][0];

		for(int i=pos+1;i<num;i++)
			if(right[i][0]>res)
				res=right[i][0];
			//res=Math.max(res, right[i][0]);
		if(last[pos]==last[pos+1]) {
			int x=left[pos][1];
			int y=-right[pos+1][1];
			if(x>=y)
				res=Math.max(left[pos][0]+1,res);
		}
		res= Math.min(res,bale[pos+1]-bale[pos]);
		res=res/2.0;

		out.printf("%.1f",Math.min(min,res));
		System.out.printf("%.1f",Math.min(min,res));
		out.close();
	}
	
	/*
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("angry.in"));
		PrintWriter out =new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		int num=Integer.parseInt(br.readLine());
		int[] bale=new int[num];
		for(int i=0; i<num; i++) {
			bale[i]=Integer.parseInt(br.readLine());
		}
		Arrays.sort(bale);
		int[] left=new int[num];
		int[] right=new int[num];
		calPower(left, bale, num);
		
		for(int i=1; i<num; i++) {
			if(left[i]>0)continue;
			int dif=bale[i]-bale[i-1];
			if(dif>left[i-1]) {
				left[i]=dif;
				int on=bale[i]+dif-left[i-1];
				for(int j=i+1; j<num; j++) {
					on-=bale[j];
					if(on>0)
						left[j]=dif;
					else {
						break;
					}
				}
			}
			else {
				if(dif==left[i-1])
					left[i]=dif+1;
				else 
					left[i]=left[i-1]+1;
			}
		}
		for(int i=num-2; i>-1; i--) {
			if(right[i]>0)continue;
			int dif=Math.abs(bale[i]-bale[i+1]);
			if(dif>right[i+1]) {
				right[i]=dif;
				int on=bale[i]+dif-right[i+1];
				for(int j=i-1; j>-1; j--) {
					on-=bale[j];
					if(on>0)
						right[j]=dif;
					else
						break;
				}
			}
			else {
				if(dif==right[i+1])
					right[i]=dif+1;
				else 
					right[i]=right[i+1]+1;
			}
		}
		int[] last=new int[num];
		int min=Integer.MAX_VALUE;
		int pos=-1;
		for(int i=0; i<num; i++) {
			last[i]=Math.max(left[i], right[i]);
			if(min>last[i]) {
				min=last[i];
				pos=i;
			}
		}
		int other=Math.min(last[pos-1], last[pos+1]);
		int use=-1; 
		if(other==last[pos-1])
			use=pos-1;
		else
			use=pos+1;
		int search=bale[pos]+bale[use];
		if(other!=min) {
			out.println((double)min);
			out.close();
		}
		else {
			double ans=0;
			for(int i=0; i<num; i++) {
				bale[i]*=2;
			}
			int now=0;
			int p=-1;
			for(int i=1; i<num; i++) {
				if(bale[i]>search) {
					p=i-1;
					break;
				}
				int dif=bale[i]-bale[i-1];
				if(dif>now+1) {
					now=dif;
					continue;
				}
				if(dif==now||dif>now) {
					now=now+2;
					continue;
				}
				if(dif<now) {
					now=now+2;
					int add=0;
					int ov=bale[i]+now-dif;
					for(int j=i+1; j<num; j++) {
						if(bale[j]>search) {
							p=j-1;
							break;
						}
						ov-=bale[j];
						if(ov>=0)
							add++;
						else
							break;
					}
					i+=add;
					continue;
				}
				
			}
			int a=search-bale[p];
			if(a>now+1) {
				now=a;
			}
			else {
				if(a>=now)
					now+=2;
			}
			int now1=0;
			int p1=-1;
			for(int i=num-2; i>-1; i--) {
				if(bale[i]<search) {
					p1=i+1;
					break;
				}
				int dif=Math.abs(bale[i]-bale[i+1]);
				if(dif>now1+1) {
					now=dif;
					continue;
				}
				if(dif==now1||dif>now1) {
					now1=now1+2;
					continue;
				}
				if(dif<now1) {
					now1=now1+2;
					int add=0;
					int ov=bale[i]+now1-dif;
					for(int j=i+1; j>-1; j--) {
						if(bale[j]<search) {
							p1=j+1;
							break;
						}
						ov-=bale[j];
						if(ov>=0)
							add++;
						else
							break;
					}
					i-=add;
					continue;
				}
			}
			int a1=search-bale[p1];
			if(a1>now1+1) {
				now1=a1;
			}
			else {
				if(a1>=now1)
					now1+=2;
			}
			out.println(Math.min((double)now,(double) now1)/2);
		}
	}*/

}
