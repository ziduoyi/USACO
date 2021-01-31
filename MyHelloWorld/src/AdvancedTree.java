import java.util.*;

public class AdvancedTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AdvancedTree at = new AdvancedTree();
		
		//at.reversePairs(new int[]{1,3,2,3,1});
		

		at.initSegmentTree(new int[]{1,3,5});
		System.out.println(at.sumRange(1, 2));
		at.update(1, 2);
		System.out.println(at.sumRange(1, 2));
		

//		at.NumArray(new int[]{0,1,2,3,4,5,6,7,8,9,10});
//		System.out.println(at.sumRangeBit(0, 2));
//		at.modify(1, 2);
//		System.out.println(at.sumRangeBit(0, 2));
	}

// --------------------------------------------------------
// ---- Begin -----  segment tree
    int n=0;
    int[] stree=null;		// segment tree

    public void initSegmentTree(int[] nums) {
        n=nums.length;
        stree = new int[2*n];    //2^n-1, 0 is not used;
        buildTree(nums);
    }

    public void buildTree(int[] nums){
        for(int i=0;i<n;i++){
        	stree[i+n]=nums[i];
        }
        for(int i=n-1;i>0;i--){
        	stree[i]=stree[2*i] + stree[2*i+1];
        }
    }

    public void update(int i, int val) {
        int x = i + n;
        stree[x]=val;
        while(x>0){
            int left=x;
            int right=x;
            if((x&1)==0)	//x%2==0
                right++;
            else
                left--;
            x>>=1;
            stree[x] = stree[left]+stree[right];
        }
    }

    public int sumRange(int i, int j) {
        int l=i+n, r=j+n;
        int sum=0;
        while(l<=r){
            if((l&1)==1)
                sum += stree[l++];
            if((r&1)==0)
                sum += stree[r--];
            l /= 2;
            r /= 2;
        }
        return sum;
    }
// ---- END --- segment tree
    
    
// ----------------------------------------------------------
// ------------ Begin Binary Index Tree -------
    int[] bitree;
    int[] data;		//have to start from 1

    public void NumArray(int[] nums) {
        n=nums.length;
        data=new int[n+1];
        bitree = new int[n+1];    //2^n-1, 0 is not used;
        buildBITree(nums);
        System.out.println(Arrays.toString(bitree));
    }

    public void buildBITree(int[] nums){
        for(int i=1;i<n;i++)
            modify(i,nums[i]);
    }

//
    public void modify(int j, int delta) {
        for(;j<=n;j+=(-j&j))		//j=j+lowbit(j)
        	bitree[j] += delta;
    }
    public int sum(int j) {
    	int s=0;
        for(;j>0; j-=(-j&j))		//j=j-lowbit(j)
        	s+=bitree[j];
        return s;
    }
//
    
    public int sumRangeBit(int i, int j) {
        return sum(j+1)-sum(i);
    }

// ------------ End BIT ---------    
// ----------------------------------------------------------
    
    
    //493. Reverse Pairs
    public int reversePairs(int[] nums) {
        int res=0;
        int N=nums.length;
        int[] arr = Arrays.copyOf(nums,N);
        Arrays.sort(arr);
        
        int[] bit = new int[N+1];
        
        for(int i=0;i<N;i++){
            long j = 2L*nums[i]+1;
            int x;
            if(j<=Integer.MAX_VALUE) {
                x=Arrays.binarySearch(arr,(int)j);
                if(x<0) x = -1-x;
                res += bitSum(bit,x+1);
            }
            
            x = Arrays.binarySearch(arr,nums[i]);
            modify(bit,x+1,1);
        }
        System.out.println(Arrays.toString(bit));
        return res;
    }
    
    int bitSum(int[] bit, int i){
        int sum=0;
        for(;i<bit.length;i+=(-i&i))
            sum += bit[i];
        return sum;
    }

    void modify(int[] bit, int i, int delta){
        for(;i>0;i-=(-i&i))
            bit[i]++;
    }
}
