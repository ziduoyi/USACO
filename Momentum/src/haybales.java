import java.io.*;
import java.util.*;
public class haybales {

	
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("haybales.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
		int num=scanner.nextInt();
		int intervels=scanner.nextInt();
		int[] nums=new int[num+1];
		nums[0]=0;
		for(int i=0; i<num; i++) {
			nums[i]=scanner.nextInt();
		}
		Arrays.sort(nums);
		for(int i=0; i<intervels; i++) {
			int a =scanner.nextInt();
			int b=scanner.nextInt();
			int c=bisearch(nums,a, true);
			int d=bisearch(nums,b, false);
			out.println(d-c);
				
		}
		out.close();
		
	}
		static int bisearch(int[] nums, int val, boolean check) {
			if(val<=nums[0])
				return 0;
			if(val>=nums[nums.length-1])
				return nums.length-1;
			int l=0;
			int r=nums.length-1;
			while(l<=r) {
				int mid=(l+r)/2;
				if(nums[mid]<=val&&nums[mid+1]>val) {
					if(nums[mid]==val&&check==true) {
						return mid-1;
					}

					return mid;
				}
				else if(val>nums[mid]) {
					l=mid+1;
					
				}
				else {
					r=mid-1;
				}
				
			}
			return -1;
		}
	}
