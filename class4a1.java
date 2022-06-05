import java.util.*;
import java.io.*;
public class class4a1 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		int a = 0;
	}
	
    public static int minMoves(int[] nums, int limit) {
    	int N  = nums.length;
        TreeSet<Integer> set = new TreeSet<>();
        LinkedList<int[]> list = new LinkedList<>();
        for(int i=0; i <N/2; i++)
        	set.add(nums[i]+nums[N-i-1]);
        int[][] stuff = new int[N/2][3];
        for(int i=0; i<N/2; i++) {
        	stuff[i][1] = nums[i]+nums[N-i-1];
        	stuff[i][0] = Math.max(nums[i], nums[N-i-1]) - limit-1;
        	stuff[i][2] = Math.min(nums[i], nums[N-i-1]);
        	for(int j=0; j<3; j++) {
        		list.add(new int[] {stuff[i][0], i});
        		list.add(new int[] {stuff[i][0], i});
        		list.add(new int[] {stuff[i][0], i});
        	}
        }
        Collections.sort(list, (a,b)->a[0]-b[0]);
        return 0;
    }
    
}
