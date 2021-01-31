import java.io.*;
import java.util.*;
public class standingout {
	public static class Suffix { 
	    int pos; 
	    int rank; 
	    int next;
	    public Suffix(int ind, int r, int nr)  { 
	        pos = ind; 
	        rank = r; 
	        next = nr; 
	    } 
	}
    public static int[] suffixArray(String s)  { 
        int N = s.length(); 
        Suffix[] suffix = new Suffix[N];  
        for (int i = 0; i < N; i++) 
            suffix[i] = new Suffix(i, s.charAt(i) - '$'-1, 0);
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
        int[] save = new int[N];      
        for (int length = 4; length < 2 * N; length *=2)  {
            int rank = 0, prev = suffix[0].rank; 
            suffix[0].rank = rank; 
            save[suffix[0].pos] = 0; 
            for (int i = 1; i < N; i++){ 
                if (suffix[i].rank == prev && suffix[i].next == suffix[i - 1].next) { 
                    prev = suffix[i].rank; 
                    suffix[i].rank = rank; 
                }  
                else {  
                    prev = suffix[i].rank; 
                    suffix[i].rank = ++rank; 
                } 
                save[suffix[i].pos] = i; 
            } 
            for (int i = 0; i < N; i++){ 
                int nextP = suffix[i].pos + length / 2; 
                if(nextP<N) 
                	suffix[i].next = suffix[save[nextP]].rank;
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
            suf[i] = suffix[i].pos; 
        return suf; 
    } 
    static long[] hashes;
    static String str;
    static TreeSet<Integer> ends;
    static long[] times;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		str = "";
		ends = new TreeSet<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<N; i++) {
			str += br.readLine()+"`";
			ends.add(str.length()-1);
			map.put(str.length()-1, i);
		}
		int[] suffix = suffixArray(str);
		int[] lcp = new int[suffix.length];
		hashes = new long[suffix.length];
		int[] len = new int[suffix.length];
		for(int i=0; i<suffix.length; i++) //can be improved to be faster
			len[i] = (N-suffix[i]) - (N-ends.ceiling(suffix[i]));
		long curr = 0;
		times = new long[str.length()];
		times[0] = 1;
		for(int i=0; i<str.length(); i++) {
			curr+=(long)times[i]*str.charAt(i)-'`';
			curr%=1000000007;
			if(i!=str.length()-1) {
				times[i+1] = times[i]*27;
				times[i+1]%= 1000000007;
			}
			hashes[i] = curr;
		}
		char c = '`';
		for(int i=1; i<suffix.length; i++) {
			if(str.charAt(suffix[i])==c)continue;
			int left = i-1;
			int right = i;
			c = str.charAt(suffix[i]);
			while(right<suffix.length) {
				if(str.charAt(suffix[right])==c)continue;
				right++;
			}
			
		}
		
	}
	static int findLca(int start1, int start2) {
		int l = 0;
		int r = Math.min(ends.ceiling(start1)-start1, ends.ceiling(start2)-start2)-1;
		long div1 = times[start1];
		long div2 = times[start2];
		long extra1 = 1000000007%div1;
		long extra2 = 1000000007%div2;
		while(l<=r) {
			 int mid = l+ (r-l)/2;
			 long hash1 = hashes[start1+mid];
			 long hash2 = hashes[start2+mid];
			 if(start1!=0)
				 hash1-=hashes[start1-1];
			 if(start2!=0)
				 hash2-=hashes[start2-1];
			 if(hash1<0)
				 hash1+=1000000007;
			 if(hash2<0)
				 hash2+=1000000007;
			 
		}
		return 0;
	}
}
