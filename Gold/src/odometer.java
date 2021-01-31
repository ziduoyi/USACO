import java.io.*;
import java.util.*;
public class odometer {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(new File("odometer.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("odometer.out")));
		long start = scanner.nextLong();
		long end = scanner.nextLong();
		out.println(compute(end)-compute(start-1));
		out.close();
	}
	static int compute(long num) {
		if(num==0)
			return 0;
		int sum =0;
		int[] prefix = new int[10];
		int len = (int) (Math.log10(num)+1);
		int[] digits = new int[len];
		long[] powers = new long[len];
		powers[0]=1;
		for(int i=1; i<len; i++)
			powers[i] = powers[i-1]*9;
		for(int i=len-1; i>-1; i--) {
			digits[i] = (int) (num%10);
			num/=10;
		}
		long[] factorials = new long[len+1];
		factorials[0]=1;
		for(int i=1; i<=len; i++)
			factorials[i] = factorials[i-1]*(i);
		long[] mult = new long[len+1];
		mult[0] =1;
		for(int i=1; i<=len; i++)
			mult[i] = mult[i-1] * 9;
		len--;
		for(int i=1; i<=len; i++) {
			for(int j=1; j<10; j++) {
				for(int k =(int) (Math.ceil((double)len/2))-1; k<=len; k++) {
				//	sum+= factorials[len-k] 
				}
 			}
		}
		for(int i=2; i<=len; i+=2) 
			sum-= 10*(factorials[i]/(2*factorials[i/2]*factorials[i/2]))*9;
		len++;
		for(int i=0; i< len; i++) {
			int s =0;
			if(i==0)
				s=1;
			for(int j=s; j< digits[i]; j++) {
				prefix[j]++;
				for(int k=0; k<10; k++) {
					int need = (int)(((double)len/2)+0.5) - prefix[k];
					if(need<= len-i-1) {
						for(int l=need; l<=len-i-1; l++) {
							if(l<0)continue;
							sum+= factorials[len-i-1]/(factorials[l]*factorials[len-i-1-l])*powers[len-i-1-l];
						}
					}
				}
				/*
				if(len%2==0) {
					int dif =0;
					int one =-1;
					int two =-1;
					for(int l=0; l<10; l++) {
						if(prefix[l]>0) {
							dif++;
							if(dif>2) {
								one= -1;
								two = -1;
								break;
							}
							if(one==-1)
								one= prefix[l];
							if(one>0&&two==-1)
								two = prefix[l];
						}
					}
					if(one>0&&two>0) {
						if(one==two) {
							sum-=1;
						}
					}
				}
				*/
				prefix[j]--;
			}
			prefix[digits[i]] ++;
		}
		for(int i=0; i<10; i++) {
			if(prefix[i]>=(int)(((double)len/2)+0.5)) {
				sum++;
				break;
			}
		}
		return sum;
	}
}
