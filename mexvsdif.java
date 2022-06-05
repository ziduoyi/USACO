import java.lang.*;
import java.util.*;
import java.io.*;
import java.math.*;
 
public class mexvsdif {
    public static void deal(int n,int k,int[] a) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int v:a) {
            hm.put(v,hm.getOrDefault(v, 0)+1);
        }
        int t = 0;
        int cnt = 0;
        while(cnt<=k && hm.size()>0) {
            if(hm.containsKey(t)) {
                hm.remove(t);
            } else {
                cnt++;
            }
            t++;
        }
        if(hm.size() == 0) {
            out.println(0);
            return;
        } 
        ArrayList<Integer> arr = new ArrayList<>(hm.values());
        arr.sort((o1,o2)->Integer.compare(o1, o2));
        int index = 0;
        cnt = 0;
        while(cnt<=k && index<arr.size()) {
            cnt+=arr.get(index);
            index++;
        }
        if(cnt <= k) {
            out.println(0);
        } else {
            out.println(arr.size()-index+1);
        }
    }
 
    
	public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        int t = sc.nextInt();
        for(int i=0;i<t;i++) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] a = new int[n];
            for(int j=0;j<n;j++) {
                a[j] = sc.nextInt();
            }
            deal(n,k,a);
        }
        out.close();
    }
    
    //-----------PrintWriter for faster output---------------------------------
    public static PrintWriter out;
    
    //-----------MyScanner class for faster input----------
    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;
        
        public MyScanner() {
                br = new BufferedReader(new InputStreamReader(System.in));
        }
        
        String next() {
                while (st == null || !st.hasMoreElements()) {
                        try {
                                st = new StringTokenizer(br.readLine());
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
                return st.nextToken();
        }
        
        int nextInt() {
                return Integer.parseInt(next());
        }
        
        long nextLong() {
                return Long.parseLong(next());
        }
        
        double nextDouble() {
                return Double.parseDouble(next());
        }
        
        String nextLine(){
                String str = "";
    try {
            str = br.readLine();
    } catch (IOException e) {
            e.printStackTrace();
    }
    return str;
        }
        
    }
}