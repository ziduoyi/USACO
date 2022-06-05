import java.io.*;
import java.util.*;
public class tanks {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		int sum = 0;
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum+=arr[i];
		}
		boolean[][] dp = new boolean[n][k];
		dp[0][0] = true;
		dp[0][arr[0]%k] = true;
		for(int i=0; i<n-1; i++) {
			for(int j=0; j<k; j++) {
				if(dp[i][j]) {
					dp[i+1][j] = true;
					dp[i+1][(j+arr[i+1])%k] = true;				
				}
			}
		}
		int pos = -1;
		for(int i=0; i<n; i++)
			if(dp[i][v%k]) {
				pos = i;
				break;
			}
		if(pos==-1||sum<v) {
			out.write("NO\n");
			out.flush();
			out.close();
			return;
		}
		out.write("YES\n");
		Set<Integer> set = new HashSet<>();
		int curr = v%k;
		int val = 0;
		for(int j=0; j<n; j++)set.add(j);
		set.remove(pos);
		for(int i=pos; i>0; i--) {
			if(dp[i-1][curr])continue;
			set.remove(i);
			val+=arr[i];
			curr = (curr-arr[i]+k*k)%k;
			if(i!=pos)
				out.write((int) Math.ceil((double)arr[i]/k)+" "+(i+1)+" "+(pos+1)+"\n");
		}
		if(curr!=0) {
			set.remove(0);
			val+=arr[0];
			curr = (curr-arr[0]+k*k)%k;
			if(0!=pos)
				out.write((int) Math.ceil((double)arr[0]/k)+" "+(1)+" "+(pos+1)+"\n");
		}
		if(v%k==0) {
			int put = pos-1;
			if(put==-1) put = pos+1;
			if(arr[pos]!=0)
				out.write((int)Math.ceil((double)arr[pos]/k)+" "+(pos+1)+" "+(put+1)+"\n");
		}
		if(val>=v) {
			int put = 0;
			if(pos==0) put = 1;
			if((val-v)/k>0)
				out.write((val-v)/k+" "+(pos+1)+" "+(put+1)+"\n");
		}
		else {
			int put = 0;
			if(pos==0) put = 1;
			for(int i: set) {
				if(i==put)continue;
				if(arr[i]==0) continue;
				out.write((int) Math.ceil((double)arr[i]/k)+" "+(i+1)+" "+(put+1)+"\n");
			}
			out.write((v-val)/k+" "+(put+1)+" "+(pos+1)+"\n");
		}
		out.flush();
		out.close();
	}

}
