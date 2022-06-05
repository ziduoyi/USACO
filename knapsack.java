
public class knapsack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] use=new int[]{7,13,6,4,11};
		int cap=99999999;
		//---------------code starts here
		boolean[] arr=new boolean[cap+1];
		arr[0]=true;
		int max=0;
		for(int i=0; i<cap+1; i++) {
			if(arr[i]==false)
				continue;	
			for(int j=0; j<use.length; j++) {
				if(i+use[j]<=cap) {
					arr[i+use[j]]=true;
					max=Math.max(max, i+use[j]);
				}
			}
		}
		System.out.println(max);
	}

}
