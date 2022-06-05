
import java.util.Arrays;

public class AdvancedTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AdvancedTree at = new AdvancedTree();

		
//		at.NumArray(new int[]{1,1,1,1,1,1,1,1,1});
//		System.out.println(at.sumRangeBit(0, 2));
//		at.modify(1, 2);
//		System.out.println(at.sumRangeBit(0, 2));
		
		
		init(10, new int[] {0,1,2,3,1,2,3,1,2,3});//1,7,8,8,9,5,3,2,3
		buildTreeM(1, 1, 9);
		buildTree(1,1,9);
		rangeUpdate(1,1,3,4);
		System.out.println(queryM(1,3,7));
		System.out.println(query(1,3,7));
//		for(int i=1; i<18; i++)
//			System.out.println(Arrays.toString(seg[i]));
//		System.out.println();
		rangeUpdateM(1,5,2,5);
//		for(int i=1; i<18; i++)
//			System.out.println(Arrays.toString(seg[i]));
//		System.out.println();
//		System.out.println(Arrays.toString(lazy));
		rangeUpdateM(1,2,4,7);
//		for(int i=1; i<18; i++)
//			System.out.println(Arrays.toString(seg[i]));
//		System.out.println();
//		System.out.println(Arrays.toString(lazy));
		
		System.out.println(queryM(1,1,9));
		System.out.println(query(1,3,6));
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
            stree[x>>1] = stree[left]+stree[right];
            x>>=1;
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
// ---------------------------------------------------------
//---------------Begin Lazy Propagation segment tree-----
    static int[][] seg;
    static int[] lazy;
    static int[] nums;
    static int N;
    
    static int[][] segm;
    static int[] lazym;
    static void init(int n, int[] arr) {
    	N=n;
    	//seg=new int[18][3];
    	//lazy=new int[18];
    	seg=new int[4*N][3];
    	lazy=new int[4*N];
    	segm=new int[18][3];
    	lazym=new int[18];
    	nums=arr;
    }
    static void buildTree(int x, int l, int r) {
    	seg[x][1]=l;seg[x][2]=r;
    	if(l==r) 
    		seg[x][0]=nums[l];
    	else {
    		int mid=l+(r-l)/2;
    		int s1=x*2,s2=s1+1;
    		buildTree(s1,l,mid);
    		buildTree(s2,mid+1,r);
    		seg[x][0]=seg[s1][0]+seg[s2][0];	//push up
    	}
    }
    static void rangeUpdate(int x, long delta, int a, int b) {
    	int l = (int) seg[x][1], r=(int) seg[x][2];
    	int s1=x*2,s2=s1+1;
    	if(a<=l && r<=b)
    		lazy[x]+=delta;
    	else {
    		int mid = (l+r)/2;
    		a=Math.max(a,l); b=Math.min(r, b);
    		if(a<=mid) 
    			rangeUpdate(s1,delta,a,Math.min(b,mid));	//l, mid
    		if(mid<b)
    			rangeUpdate(s2,delta,Math.max(a,mid+1),b);
    		seg[x][0] = seg[s1][0] + lazy[s1]*(seg[s1][2]-seg[s1][1]+1) + seg[s2][0] + lazy[s2]*(seg[s2][2]-seg[s2][1]+1);
    	}
    }
    static int query(int x, int a, int b) {
    	int res = 0;
    	int l = seg[x][1], r=seg[x][2];
    	int s1=x*2,s2=s1+1;
    	
    	if(a<=l && r<=b) {
    		return  seg[x][0] + (lazy[x])*(r-l+1) ;//delta * (j-i+1) + lazy[x]*(j-i+1);
    	}else {
    		int mid = (l+r)/2;
    		a=Math.max(a,l); b=Math.min(r, b);
    		res += lazy[x] * (b-a+1);		
    		if(a<=mid)	
    			res += query(s1,a,Math.min(b,mid));	//l, mid
    		if(mid<b)
    			res += query(s2,Math.max(a,mid+1),b);
    	}

    	return res;
    }
//////////////////    
    static void buildTreeM(int x, int l, int r) {
    	segm[x][1]=l;segm[x][2]=r;
    	if(l==r) 
    		segm[x][0]=nums[l];
    	else {
    		int mid=l+(r-l)/2;
    		int s1=x*2,s2=s1+1;
    		buildTreeM(s1,l,mid);
    		buildTreeM(s2,mid+1,r);
    		segm[x][0]=Math.min(segm[s1][0],segm[s2][0]);	//push up
    	}
    }
    static void rangeUpdateM(int x, long delta, int a, int b) {//idk if correct
    	int l = (int) segm[x][1], r=(int) segm[x][2];
    	int s1=x*2,s2=s1+1;
    	if(a<=l && r<=b) {
    		lazym[x]+=delta;
    	}else {
    		int mid = (int) ((l+r)/2);
    		a=Math.max(a,l); b=Math.min(r, b);
    		if(a<=mid)
    			rangeUpdateM(s1,delta,a,Math.min(b,mid));
    			//min = Math.min(rangeUpdateM(s1,delta,a,Math.min(b,mid)),min);	//l, mid
    		if(mid<b)
    			rangeUpdateM(s2,delta,Math.max(a,mid+1),b);
    			//min = Math.min(rangeUpdateM(s2,delta,Math.max(a,mid+1),b),min);
    		segm[x][0] = Math.min(segm[s1][0] + lazym[s1], segm[s2][0] + lazym[s2]);	//push up
    		seg[s1][0] = lazy[s1]*(seg[s1][2]-seg[s1][1]+1) + seg[s2][0] + lazy[s2]*(seg[s2][2]-seg[s2][1]+1);
    	}
    }
    
    static long queryM(int x, int a, int b) {//idk of correct
    	long res = Integer.MAX_VALUE;
    	int l = (int) segm[x][1], r=(int) segm[x][2];
    	int s1=x*2,s2=s1+1;
    	if(a<=l && r<=b) {
    		return  segm[x][0] +lazym[x] ;//segm[x][0] + (del+lazym[x])*(r-l+1)
    	}else {
    		int mid = (int) ((l+r)/2);
    		a=Math.max(a,l); b=Math.min(r, b);
    		if(a<=mid)
    			res =Math.min( res,queryM(s1,a,Math.min(b,mid)));	//l, mid
    		if(mid<b)
    			res = Math.min(res,queryM(s2,Math.max(a,mid+1),b));
    		res += lazym[x];
    	}
    	return res;
    }    
    
    
    
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
        for(int i=0;i<n;i++)
            modify(i+1,nums[i]);
    }

    public void modify(int j, int delta) {
        for(;j<=n;j+=(-j&j))		//j=j+lowbit(j)
        	bitree[j] += delta;
    }
    public int sum(int j) {
    	int s=0;
        for(;j>0; j-=(-j&j))	////j=j-lowbit(j)
        	s+=bitree[j];
        return s;
    }

    public int sumRangeBit(int i, int j) {
        return sum(j+1)-sum(i);
    }

// ------------ End BIT ---------    
// ----------------------------------------------------------
}
/* MO's algorithm
int inter = (int)Math.ceil(Math.sqrt(n));
int[] freq = new int[map.size()];
Arrays.sort(queries, new Comparator<int[]>() {
	@Override
	public int compare(int[] o1, int[] o2) {
		// TODO Auto-generated method stub
		if(o1[0]/inter != o2[0]/inter) return o1[0]/inter - o2[0]/inter;
		return o1[1]-o2[1];
	}	    		
});
long[] ans = new long[q];
int stop = inter-1;
int curr = 0;
int pos = 0;
for(int j=1; j<=(int)Math.ceil((double)n/inter); j++) {
	if(pos<q&&queries[pos][0]<j*inter) {
		int left = queries[pos][0];
		int right = queries[pos][0]-1;
		Arrays.fill(freq, 0);
		while(pos<q&&queries[pos][0]<j*inter) {
			for(int k=right+1; k<=queries[pos][1]; k++) {
				freq[map.get(arr[k])]++;
			}
			right = queries[pos][1];
			if(queries[pos][0]>=left)
				for(int k=left; k<queries[pos][0]; k++) {
					freq[map.get(arr[k])]--;
				}
			else
				for(int k=queries[pos][0]; k<left; k++) {
					freq[map.get(arr[k])]++;
				}
			left = queries[pos][0];
			if(map.containsKey(queries[pos][2]))
				ans[queries[pos][3]] = freq[map.get(queries[pos][2])];
			pos++;
		}
	}
	
}
for(int j=0; j<q; j++)
	out.write(ans[j]+"\n");
	*/
