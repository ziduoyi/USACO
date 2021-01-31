import java.io.*;
import java.util.*;
public class silverp2 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader("convention2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));
		int num=Integer.parseInt(br.readLine());
		int[][] arr=new int[num][3];
		for(int i=0; i<num; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			arr[i][1]=Integer.parseInt(st.nextToken());
			arr[i][2]=Integer.parseInt(st.nextToken());
			arr[i][0]=i;
		}
		Arrays.sort(arr,(a,b)->{if(a[1]!=b[1]) 
				return (int) (a[1]-b[1]);
				else return (int) (a[0]-b[0]);
		});
		TreeSet<int[]> set=new TreeSet<>((a,b)->(a[0]-b[0]));
		int j =1;
		int curr=arr[0][1]+arr[0][2];
		int wait=0;
		while(j<num) {
			for(int i=j; i<num; i++) {
				if(curr<arr[i][1]) {
					break;
				}
				else {
					set.add(arr[i]);
				}
				j++;
			}
			if(set.isEmpty()) {
				set.add(arr[j]);
				curr=arr[j][1];
				j++;
			}
			int[] temp=set.pollFirst();
			wait=Math.max(wait, curr-temp[1]);
			curr+=temp[2];
		}
		
		out.println(wait);
		out.close();
	}
}
