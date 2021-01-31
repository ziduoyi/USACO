package com.lc;

public class BinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] data = new int[] {1,3,5,10,10,20,30,50};
		findRightMost2(data,3);
/*		
		假定你的三个指针是low mid high

		简单三句话

		1 终止条件决定了 high 如何操作，一般来说,while(low<high) 则high＝mid；while
		（low<=high） high＝mid－1；
		2.要根据实际情况，确定是否要排除mid 然后确定终止条件
		3.low 永远是 low ＝ mid＋1；
*/		
		
		for(int i=0;i<100;i++) {
			int r1=findLeftMost(data,i);
			int r2=findLeftMost2(data,i);
			//int r1= findRightMost(data,i);
			//int r2=findRightMost2(data,i);
			if(r1!=r2)
				System.out.println("differences found: " + i + "; r1=" +r1+ ";  r2="+ r2);
		}
	}
	static int findLeftMost(int[] data, int x) {	//if match found, return match, else return the left closest
		int l=0, r=data.length-1;
		while(l<=r) {
			int m = l+ (r-l)/2;
			if(data[m]==x)
				return data[m];
			else if(data[m]>x)
				r=m-1;
			else
				l=m+1;
		}
		if(r<0)return -1;
		return data[r];
	}

	static int findLeftMost2(int[] data, int x) {	//if match found, return match, else return the left closest
		int l=0, r=data.length-1;
		while(l<r) {
			int m = l+ (r-l)/2;
			if(data[m]==x)
				return data[m];
			else if(data[m]>x)
				r=m;
			else
				l=m+1;
		}
		if(l<=0)return -1;
		return data[l-1];
	}
	
	static int findRightMost(int[] data, int x) {	//if match found, return match, else return the left closest
		int l=0, r=data.length-1;
		while(l<=r) {
			int m = l+ (r-l)/2;
			if(data[m]==x)
				return data[m];
			else if(data[m]>x)
				r=m-1;
			else
				l=m+1;
		}
		if(l>=data.length)return -1;
		return data[l];
	}

	static int findRightMost2(int[] data, int x) {	//if match found, return match, else return the left closest
		int l=0, r=data.length-1;
		while(l<r) {
			int m = l+ (r-l)/2;
			if(data[m]==x)
				return data[m];
			else if(data[m]>x)
				r=m;
			else
				l=m+1;
		}
		if(l==0 || l>data.length)return -1;
		return data[l];
	}
	
}
