import java.io.*;
import java.util.*;
public class zoom {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	int n = Integer.parseInt(br.readLine());
	    	HashMap<String, Integer> map = new HashMap<>();
	    	for(int j=0; j<n; j++) {
	    		String str = br.readLine();
	    		if(map.containsKey(str))
	    			map.put(str, map.get(str)+1);
	    		else
	    			map.put(str, 1);
	    	}
	    	String[] arr = new String[map.size()];
	    	int a = 0;
	    	for(String key: map.keySet()) 
	    		arr[a++] = key;
	    	Arrays.sort(arr, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					// TODO Auto-generated method stub
					if(map.get(o1)!=map.get(o2))return -1*(map.get(o1)- map.get(o2));
					for(int i=0; i<o1.length(); i++) {
						if(i>=o2.length())
							return 1;
						if(o1.charAt(i)<o2.charAt(i))
							return -1;
						else if (o1.charAt(i)!=o2.charAt(i))
							return 1;
					}
					return -1;
				}
	    		
	    	});
	    	for(String s: arr) {
	    		out.write(s+"\n");
	    	}
	    }
	    out.flush();
	    out.close();
	}

}
