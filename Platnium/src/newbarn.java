import java.io.*;
import java.util.*;
public class newbarn {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arrx = new int[N];
		int[] arry = new int[N];
		int[][] original = new int[N][2];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arrx[i] = Integer.parseInt(st.nextToken());
			arry[i] = Integer.parseInt(st.nextToken());
			original[i][0] = arrx[i];
			original[i][1] = arry[i];
		}
		Arrays.sort(arrx);
		Arrays.sort(arry);
		int[][] data1 = findMin(arrx);
		int[][] data2 = findMin(arry);
		Arrays.sort(data1, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if(o1[0]!=o2[0])return o1[0]-o2[0];
				return o1[1]-o2[1];
			}
		});
		Arrays.sort(data2, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if(o1[0]!=o2[0])return o1[0]-o2[0];
				return o1[1]-o2[1];
			}
		});
		int[] ans = new int[] {Integer.MAX_VALUE, 0};
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				int num = countNum(i, j, data1, data2, original);
				if(N%2==1&&num!=0)
					num++;
				if(num>0) {
					if(ans[0]>data1[i][0]+ data2[j][0]) {
						ans[0] = data1[i][0] + data2[j][0];
						ans[1] = num;
					}
				}
			}
		}
		System.out.println(ans[0]+" "+ans[1]);
	}
	static int countNum(int pos1, int pos2, int[][] data1, int[][] data2, int[][] search) {
		int curr1= pos1;
		while(curr1<data1.length&&data1[curr1][0]== data1[pos1][0]) 
			curr1++;
		int ans = curr1-pos1;
		int curr2 = pos2;
		while(curr2<data2.length&&data2[curr2][0]== data2[pos2][0]) 
			curr2++;
		ans*=(curr2-pos2);
		for(int i=0; i<search.length; i++) {
			if(search[i][0]>=data1[pos1][1]&&search[i][0]<=data1[curr1-1][1]&&search[i][1]<=data2[curr2-1][1]&&search[i][1]>= data2[pos2][1])
				ans--;
		}
		return ans;
	}
	static int[][] findMin(int[] arr) {
		int N = arr.length;
		int[][] dp = new int[20001][2];
		for(int i=-10000; i<=10000; i++)
			dp[i+10000][1] = i;
		int left =0;
		int center =0;
		int right =N;
		int pos =0;
		while(pos<N && arr[pos]==-10000) {
			pos++;
			right--;
			center++;
		}
		for(int i=0; i<N; i++)
			dp[0][0]+=(arr[i]+10000);
		for(int i=-9999; i<=10000; i++) {
			left+=center;
			center=0;
			int c =0;
			while(pos<N && arr[pos]==i) {
				center++;
				c++;
				pos++;
			}
			dp[i+10000][0] = dp[i-1+10000][0] + left- right;
			right-=c;
		}
		return dp;
	}
}
