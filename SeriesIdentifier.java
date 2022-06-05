import java.io.*;
import java.util.*;
public class SeriesIdentifier {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		while((str=br.readLine())!=null) {
			String[] store = str.split(" ");
			int N = store.length;
			double[] arr = new double[N];
			for(int i=0; i<N; i++) {
				long mult = 1;
				String s = store[i];
				boolean neg = false;
				for(int j=s.length()-1; j>-1; j--) {
					if(s.charAt(j)=='-') {
						neg = true;
						continue;
					}
					arr[i] += mult*(s.charAt(j)-'0');
					mult *= 10;
				}
				if(neg)arr[i]*=-1;
			}
			boolean check = false;
			if(N>=3) {
				for(int j=2; j<N; j++) {
					if(arr[j-2]-arr[j]!=(-1*arr[j-1])) {
						check = true;
						break;
					}
				}
				if(!check){
					System.out.println("Fibonacci");
					continue;
				}
			}
			check = false;
			if(N>=2) {
				for(int j=1; j<N; j++) {
					if(arr[j-1]*arr[j-1]!=arr[j]) {
						check = true;
						break;
					}
				}
				if(!check) {
					System.out.println("X^2");
					continue;
				}
			}
			check = false;
			if(N>=2) {
				for(int j=1; j<N; j++) {
					if(arr[j-1]*arr[j-1]*arr[j-1]!=arr[j]) {
						check = true;
						break;
					}
				}
				if(!check) {
					System.out.println("X^3");
					continue;
				}
			}
			check = false;
			if(N>=2) {
				double change = arr[1]/arr[0];
				for(int j=2; j<N; j++) {
					if(arr[j]/arr[j-1]!=change) {
						check = true;
						break;
					}
				}
				if(!check) {
					System.out.println("Geometric");
					continue;
				}
			}
			check = false;
			if(N>=2) {
				ArrayList<Double> dif = new ArrayList<>();
				for(int j=1; j<N; j++)
					dif.add(arr[j]/arr[j-1]);
				int c = dif.size();
				for(int j=0; j<c; j++) {
					for(int k=j+1; k<c; k++) {
						double one = dif.get(j);
						double two = dif.get(k);
						if(Math.ceil(Math.max(one, two)/Math.min(one, two))!=Math.max(one, two)/Math.min(one, two)) {
							check = true;
							break;
						}
					}
				}
				if(!check) {
					System.out.println("Geometric (With Gaps)");
					continue;
				}	
			}
			check = false;
			for(int j=0; j<N; j++) {
				System.out.print((long)arr[j] +" ");
			}
			System.out.println("is an Unknown series");	
		}
	}

}
