import java.io.*;
import java.util.*;
public class hw2 {
	static int n;
	static int m;
	static int[][] stree;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner =new Scanner(new File("sumchange.in"));
		n=scanner.nextInt();
		m=scanner.nextInt();
		int sum =1;
		int old =n;
		while(n>sum)
			sum*=2;
		n=sum;
		stree=new int[n*2][4];
		int[] nums=new int[n];
		for(int i=0;i<old ;i++) {
			nums[i]=scanner.nextInt();
		}
		buildTree(nums);
		for(int i=0;i<m; i++) {
			int a=scanner.nextInt();
			int b=scanner.nextInt();
			update(a-1, b);
			System.out.println(Math.max(0, stree[1][3]));
		}
	}
    public static void buildTree(int[] nums){
        for(int i=0;i<n;i++){
        	stree[i+n][0]=nums[i];
        	stree[i+n][1]=nums[i];
        	stree[i+n][2]=nums[i];
        	stree[i+n][3]=Math.max(nums[i], 0);
        }
        for(int i=n-1;i>0;i--){
        	stree[i][0]=stree[2*i][0] + stree[2*i+1][0];
        	stree[i][1]=Math.max(stree[2*i][1], stree[2*i][0]+stree[2*i+1][1]);
        	stree[i][2]=Math.max(stree[2*i][2]+stree[2*i+1][0], stree[2*i+1][2]);
        	stree[i][3]=Math.max(stree[i*2][3], Math.max(stree[i*2+1][3], stree[i*2][2]+stree[i*2+1][1]));
        }
    }
    public static void update(int i, int val) {
        int x = i + n;
        stree[x][0]=val;
        stree[x][1]=val;
        stree[x][2]=val;
        stree[x][3]=Math.max(val,0);
        while(x>0){
            int left=x;
            int right=x;
            if((x&1)==0)	//x%2==0
                right++;
            else
                left--;
            stree[x>>1][0] = stree[left][0]+stree[right][0];
        	stree[x>>1][1]=Math.max(stree[left][1], stree[left][0]+stree[right][1]);
        	stree[x>>1][2]=Math.max(stree[left][2]+stree[right][0], stree[right][2]);
        	stree[x>>1][3]=Math.max(stree[left][3], Math.max(stree[right][3], stree[left][2]+stree[right][1]));
            x>>=1;
        }
    }

}
