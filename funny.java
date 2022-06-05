import java.util.*;
import java.io.*;
public class funny {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	    Scanner br = new Scanner(System.in);
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int t = br.nextInt();
	    
	    for(int i=0; i<t; i++) {
	    	String str = "";
	    	int n = br.nextInt();
	    	Map<String, String[]> map = new HashMap<>();
	    	Map<String, Long> val = new HashMap<>();
	    	for(int j=0; j<n; j++) {
	    		String str1 = br.next();
	    		str = str1;
	    		if(br.next().equals(":=")) {
	    			String str2 = br.next();
	    			String[] temp = new String[6];
	    			Arrays.fill(temp, "");
	    			for(int k=0; k<Math.min(3, str2.length()); k++)
	    				temp[k] = Character.toString(str2.charAt(k));
	    			for(int k=5; k>Math.max(2, 5-str2.length()); k--)
	    				temp[k] = Character.toString(str2.charAt(str2.length()-6+k));
	    			for(int k=0; k<2; k++)
	    				if(temp[k].equals(""))
	    					for(int m=k+1; m<3; m++) 
	    						if(!temp[m].equals("")) {
	    							temp[k] = temp[m];
	    							temp[m] = "";
	    							break;
	    						}
	    			for(int k=5; k>3; k--)
	    				if(temp[k].equals(""))
	    					for(int m=k-1; m>2; m--) 
	    						if(!temp[m].equals("")) {
	    							temp[k] = temp[m];
	    							temp[m] = "";
	    							break;
	    						}
	    			map.put(str1, temp);
	    			int cnt = 0;
	    			for(int k=3; k<str2.length(); k++)
	    				if(str2.charAt(k-3)=='h'&&str2.charAt(k-2)=='a'&&str2.charAt(k-1)=='h'&&str2.charAt(k)=='a')
	    					cnt++;
	    			val.put(str1, (long) cnt);
	    		}
	    		else {
	    			String str2 = br.next();
	    			br.next();
	    			String str3 = br.next();
	    			long curr = val.get(str2)+val.get(str3);
	    			String[] side1 = map.get(str2);
	    			String[] side2 = map.get(str3);
	    			String[] temp = new String[] {side1[3],side1[4],side1[5],side2[0],side2[1],side2[2]};
	    			for(int k=0; k<5; k++) {
	    				if(temp[k].equals(""))
	    					for(int m=k+1; m<6; m++) 
	    						if(!temp[m].equals("")) {
	    							temp[k] = temp[m];
	    							temp[m] = "";
	    							break;
	    						}
	    					
	    			}
	    			int cnt = 0;
	    			for(int k=3; k<6; k++)
	    				if((temp[k-3]+temp[k-2]+temp[k-1]+temp[k]).equals("haha"))
	    					cnt++;
	    			val.put(str1, curr+cnt);
	    			String[] next = new String[] {side1[0],side1[1],side1[2],side2[3],side2[4],side2[5]};
	    			int pos = 0;
	    			for(int k=0; k<2; k++)
	    				if(next[k].equals(""))
	    					next[k] = side2[pos++];
	    			pos = 5;
	    			for(int k=5; k>3; k--)
	    				if(next[k].equals(""))
	    					next[k] = side1[pos--];
	    			map.put(str1, next);
	    		}
	    	}
	    	out.write(val.get(str)+"\n");	    	
	    }
	    out.flush();
	    out.close();	 
	}

}
