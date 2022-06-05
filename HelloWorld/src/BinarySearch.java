//https://www.geeksforgeeks.org/find-first-and-last-positions-of-an-element-in-a-sorted-array/ custom occurene sorts
public class BinarySearch {

	public static void main(String[] args) {
		int[] data = new int[] {4,5,8,9,10,10,15,20};
		System.out.println(bsearch(data,0));
	}
	static int bsearch(int[] arr, int X) {
		int l=0, r=arr.length-1;
		while(l<=r) {
			int m=l+(r-l)/2;
			if(arr[m]>=X)
				r=m-1;
			else
				l=m+1;
		}
		return l ;
	}
	/*
	static int bsearch(int[] arr, int X) {
		int l=0, r=arr.length-1;
		if(X>arr[r])  return -1;
		while(l<=r) {
			int m=l+(r-l)/2;
			if(arr[m]==X)
				return X;
			else if(arr[m]>X)
				r=m-1;
			else
				l=m+1;
		}
		return arr[l];
	}
*/
}
