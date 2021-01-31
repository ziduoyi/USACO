import java.io.*;
import java.util.*; 
public class letter {
	public static class Suffix { 
	    int index; 
	    int rank; 
	    int next;
	    public Suffix(int ind, int r, int nr)  { 
	        index = ind; 
	        rank = r; 
	        next = nr; 
	    } 
	}
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String string1 = "";
		String string2 = "";
		int tol1=0;
		int tol2=0;
		while(tol1!=N) {
			String s =br.readLine();
			string1+=s;
			tol1+=s.length();
		}
		while(tol2!=M) {
			String s =br.readLine();
			string2+=s;
			tol2+=s.length();
		}
		int[] suffix = suffixArray(string1);
		int start =0;
		int end = N-1;
		int dep =0;
		int ans =1;
		for(int i=0; i<M; i++) {
			int l=start;
			int r = end;
			while(l<=r) {
				int m = (l+r)/2;
				if(suffix[m]+dep<N && string1.charAt(suffix[m]+dep)>=string2.charAt(i))
					r =m-1;
				else
					l = m+1;
			}
			int s1 = l;
			l = start;
			r = end;
			while(l<=r) {
				int m = (l+r)/2;
				if(suffix[m]+dep<N && string1.charAt(suffix[m]+dep)>string2.charAt(i))
					r=m-1;
				else
					l = m+1;
			}
			int s2 = r;
			start = s1;
			end = s2;
			if(start>end||string1.charAt(suffix[start]+dep)!=string2.charAt(i)||string1.charAt(suffix[end]+dep)!=string2.charAt(i)) {
				ans++;
				dep=0;
				start =0;
				end = N-1;
				i--;
			}
			else
				dep++;
		}
		System.out.println(ans);
	}
    public static int[] suffixArray(String s)  { 
        int N = s.length(); 
        Suffix[] suffix = new Suffix[N];  
        for (int i = 0; i < N; i++) 
            suffix[i] = new Suffix(i, s.charAt(i) - '$', 0); 
        for (int i = 0; i < N-1; i++)  
            suffix[i].next = (suffix[i + 1].rank);
        suffix[N-1].next = -1;
        Arrays.sort(suffix, new Comparator<Suffix>() {
			@Override
			public int compare(Suffix arg0, Suffix arg1) {
				// TODO Auto-generated method stub
				if(arg0.rank!=arg1.rank)return arg0.rank-arg1.rank;
				return arg0.next-arg1.next;
			}	
        }); 
        int[] ind = new int[N];      
        for (int length = 4; length < 2 * N; length *=2)  {
            int rank = 0, prev = suffix[0].rank; 
            suffix[0].rank = rank; 
            ind[suffix[0].index] = 0; 
            for (int i = 1; i < N; i++){ 
                if (suffix[i].rank == prev && suffix[i].next == suffix[i - 1].next) { 
                    prev = suffix[i].rank; 
                    suffix[i].rank = rank; 
                }  
                else {  
                    prev = suffix[i].rank; 
                    suffix[i].rank = ++rank; 
                } 
                ind[suffix[i].index] = i; 
            } 
            for (int i = 0; i < N; i++){ 
                int nextP = suffix[i].index + length / 2; 
                if(nextP<N) 
                	suffix[i].next = suffix[ind[nextP]].rank;
                else
                	suffix[i].next = -1;
            } 
            Arrays.sort(suffix, new Comparator<Suffix>() {
    			@Override
    			public int compare(Suffix arg0, Suffix arg1) {
    				// TODO Auto-generated method stub
    				if(arg0.rank!=arg1.rank)return arg0.rank-arg1.rank;
    				return arg0.next-arg1.next;
    			}	
            });
        } 
        int[] suf = new int[N];  
        for (int i = 0; i < N; i++)  
            suf[i] = suffix[i].index; 
        return suf; 
    } 
}