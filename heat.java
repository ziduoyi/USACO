import java.io.*;
import java.util.*;
public class heat {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = Integer.parseInt(br.readLine());
	    for(int i=0; i<t; i++) {
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	String type = st.nextToken();
	    	HashMap<String, Double> map = new HashMap<>();
	    	for(int j=0; j<n; j++) {
	    		st = new StringTokenizer(br.readLine()); 
	    		String city = st.nextToken();
	    		double dist = Double.parseDouble(st.nextToken());
	    		map.put(city, dist);
	    	}
	    	String[] arr = new String[map.size()];
	    	int a = 0;
	    	for(String key: map.keySet()) 
	    		arr[a++] = key;
	    	if(type.equals("W")) {
		    	Arrays.sort(arr, new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						// TODO Auto-generated method stub
						if(map.get(o1)<map.get(o2))return -1;
						if(map.get(o1)>map.get(o2))return 1;
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
	    	}
	    	else {
		    	Arrays.sort(arr, new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						// TODO Auto-generated method stub
						if(map.get(o1)<map.get(o2))return 1;
						if(map.get(o1)>map.get(o2))return -1;
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
	    	}
	    	for(int j=0; j<map.size(); j++) 
	    		out.write(arr[j]+"\n");
	    	out.write("\n");
	    }
	    out.flush();
	    out.close();
	}

}
