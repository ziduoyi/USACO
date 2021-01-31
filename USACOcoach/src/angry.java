import java.util.*;
import java.io.*;
public class angry {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("angry.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine())*2;
		}
		Arrays.sort(arr);
		int[] left = new int[N];
		int[] right = new int[N];
		selfSummary(N,left,arr);
		reverse(arr);
		whatever(N,right,arr);
		reverse(right);
		reverse(arr);
		int[] mins = new int[N];
		int min = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			mins[i] = Math.max(left[i], right[i]);
			if(mins[i]<min) {
				min= mins[i];
			}
		}
		int pos1=-1;
		int pos2=-1;
		for(int i=0; i<N; i++) {
			if(mins[i]==min&&pos1==-1) {
				pos1 =i;
			}
			else if(mins[i]==min) {
				pos2=i;
				break;
			}
		}
		if(pos2==-1) {
			out.println((double)min/(double)2);
			out.close();
			return;
		}
		int l =0;
		int r =1000000000;
		double ans =Integer.MAX_VALUE;
		while(l<=r) {
			int power = (l+r)/2;
			int a = arr[pos1];
			int b = arr[pos2];
			boolean see= false;
			while(a<=b) {
				if(power>min) {
					see = true;
					break;
				}
				int pos = a+(b-a)/2;
				int check = isWork(arr, power, pos);
				if(check == 2) {
					see = true;
					break;
				}
				if(check ==0) {
					a = pos+1;
				}
				else {
					b = pos-1;
				}
			}
			
			if(see==true) {
				r = power-1;
				ans = power;
			}
			else {
				l = power +1;
			}
		}
		out.printf("%.1f\n",ans/(double)2);
		out.close();
	}
	static int isWork(int[] arr, int pow, int pos) {
		int Anne = Arrays.binarySearch(arr, pos);
		int s =-1;
		int e =-1;
		if(Anne>=0) {
			s = Anne -1;
			e = Anne +1;
		}
		else {
			Anne ++;
			Anne *=-1;
			s = Anne-1;
			e = Anne;
		}
		long curr = pos;
		int save = pow;
		long z = curr;
		for(int i=e; i<arr.length; i++) {
			curr += pow;
			pow-=2;
			if(curr>= arr[i]) {
				int a=i;
				for(int j=i+1; j<arr.length; j++) {
					if(curr>=arr[j]) {
						a=j;
					}
					else {
						curr = arr[j-1];
						break;
					}
				}
				i=a;
			}
			else
				return 0;
		}
		pow=save;
		curr =z;
		for(int i=s; i>-1; i--) {
			curr -= pow;
			pow-=2;
			if(curr<= arr[i]) {
				int a=i;
				for(int j=i-1; j>-1; j--) {
					if(curr<=arr[j]) {
						a=j;
					}
					else {
						curr = arr[j+1];
						break;
					}
				}
				i=a;
			}
			else
				return 1;
		}
		return 2;
	}
	static void reverse(int[] arr) {
		for(int i=0; i<arr.length/2; i++) {
			int save = arr[i];
			arr[i] = arr[arr.length-i-1];
			arr[arr.length-i-1] = save;
		}
	}
	static void whatever (int N, int[] right, int[] arr) {
		for(int i=1; i<N; i++) {
			int find = arr[i-1] - right[i-1] - 2;
			if(find>arr[i]) {
				int dif = find-arr[i];
				right[i] = right[i-1] + dif + 2;
			}
			else {
				right[i] = right[i-1] + 2;
				int a=i;
				for(int j=i+1; j<N; j++) {
					if(find<=arr[j]) {
						a=j;
						right[j] = right[i];
					}
					else
						break;
				}
				i=a;
			}
		}
	}
	static void selfSummary (int N, int[] left, int[] arr) {
		for(int i=1; i<N; i++) {
			int find = arr[i-1] + left[i-1] + 2;
			if(find<arr[i]) {
				int dif = arr[i] - find;
				left[i] = left[i-1] + dif + 2;
			}
			else {
				left[i] = left[i-1] + 2;
				int a=i;
				for(int j=i+1; j<N; j++) {
					if(find>=arr[j]) {
						a=j;
						left[j] = left[i];
					}
					else
						break;
				}
				i=a;
			}
		}
	}
}
