
public class CowCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="COW";
		long pos=10;
		long len=str.length();
		while(len<pos) {
			len*=2;
		}
		int shift =0;
		while(len>str.length()) {
			while(len>pos) {
				len/=2;
			}
			pos-=len;
			shift++;
		}
		if(shift>=26) {
			shift%=26;
		}
		char c=str.charAt((int) (pos-1));
		if(c+shift>90) {
			int a=90-c;
			shift-=a;
			System.out.println((char)('A'+shift));
			
		}
		else {
			System.out.println((char)(c+shift));
		}
	}

	//static char generateNthLetter(String s, long N) {
		
	//}
}
