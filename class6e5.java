import java.io.*;
import java.util.*;
public class class6e5 {
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		/*
		Scanner scanner=new Scanner(new File("homework.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));
		int num=scanner.nextInt();
		int[] arr =new int[num];
		int ave=0;
		int a=0;
		arr[0]=scanner.nextInt();
		for(int i=1; i<num; i++) {
			arr[i]=scanner.nextInt();
		}
		ave+=arr[arr.length-1];
		ave+=arr[arr.length-2];
		ave/=2;
		int pos=arr.length-3;
		int[] mins=new int[arr.length-3];
		for(int i=arr.length-3; i>0; i--) {
			if(arr[i]<=ave) {
				mins[a]=arr[i];
				a++;
				continue;
			}
			int ave2=0;
			for(int j=0; j<a; j++) {
				ave2+=mins[j];
			}
			ave2+=arr[i];
			ave2/=(a+1);
			if(ave2>ave) {
				int n=0;
				for(int j=arr.length-1; j>i; j--) {
					ave+=arr[j];
					n++;
				}
				ave/=n;
				pos=i;
				for(int j=0; j<a; j++) {
					mins[j]=0;
				}
				a=0;
			}
			else {
				mins[a]=arr[i];
				a++;
			}
		}
	}
		ave-=min;
		ave/=(num-1);
		int a=0;
		for(int i=1; i<num-2;i++) {
			if(arr[i]>ave) {
				ave=arr[i];
				a=i-1;
			}
		}
		out.println(pos);
		out.close();
		*/

	}
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack1=new Stack<>();
        Stack<Integer> stack2=new Stack<>();
        int[] arr=new int[nums1.length];
        for(int i=nums1.length-1; i>-1; i--){
            stack1.push(nums1[i]);
        }
        for(int i=nums2.length-1; i>-1; i--){
            stack2.push(nums2[i]);
        }
        for(int i=0; i<nums1.length; i++){
            int num= stack1.pop();
            for(int j=0; j<nums2.length; j++){
                int com=stack2.peek();
                if(num>com){
                    arr[i]=num;
                    break;
                }
            }
        }
        return arr;
    }
}
