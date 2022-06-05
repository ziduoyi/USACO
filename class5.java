import java.util.*;
import java.io.*;
public class class5 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		int t= Integer.parseInt(br.readLine());
		for(int i=0; i<t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			Set<Integer> left = new HashSet<>();
			Map<Integer, Integer> countl = new HashMap<>();
			Set<Integer> right = new HashSet<>();
			Map<Integer, Integer> countr = new HashMap<>();
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<l; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(left.contains(num))
					countl.put(num, countl.get(num)+1);
				else {
					left.add(num);
					countl.put(num, 1);
				}
			}
			for(int j=0; j<r; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(right.contains(num))
					countr.put(num, countr.get(num)+1);
				else {
					right.add(num);
					countr.put(num, 1);
				}
			}
			int ans = 0;
			LinkedList<Integer> rev = new LinkedList<>();
			for(int num: left) {
				if(right.contains(num)) {
					int min = Math.min(countl.get(num), countr.get(num));
					if(min==countl.get(num)) {
						countl.remove(num);
						rev.add(num);
						countr.put(num, countr.get(num)-min);
					}else {
						countr.remove(num);
						right.remove(num);
						countl.put(num, countl.get(num)-min);
					}
				}
			}
			for(int num: rev)left.remove(num);
			int size1 = 0;
			int size2 = 0;
			for(int num: left)
				size1+=countl.get(num);
			for(int num: right)
				size2+=countr.get(num);
			if(size1==size2) {
				ans += size1;
				out.write(ans+"\n");
				continue;
			}
			if(size1>size2) {
				int sub = size1-size2;
				for(int num: left) {
					int val = countl.get(num);
					if(val>1) {
						if(sub>=((val))) {
							sub-=(val/2)*2;
							ans+=(val/2);
						}
						else {
							ans+= (sub/2);
							sub = 0;
						}
					}
					if(sub==0)break;
				}
				ans+= size2+(sub);
			}
			else {
				int sub = size2-size1;
				for(int num: right) {
					int val = countr.get(num);
					if(val>1) {
						if(sub>=(val)) {
							sub-=(val/2)*2;
							ans+=(val/2);
						}
						else {
							ans+= (sub/2);
							sub = 0;
						}
					}
					if(sub==0)break;
				}
				ans+= size1+(sub);
			}
			out.write(ans+"\n");
		}
		out.flush();
		out.close();
	}
}

