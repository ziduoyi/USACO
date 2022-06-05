import java.io.*;
import java.util.*;
public class prob09 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner br = new Scanner(System.in);
		Set<String> set = new HashSet<>();
		Map<String, Integer> map = new HashMap<>();
		ArrayList<String> list = new ArrayList<>();
		while(br.hasNext()) {
			String str = br.next();
			list.add(str);
			if(set.contains(str))map.put(str, map.get(str)+1);
			else {
				set.add(str);
				map.put(str, 1);
			}
		}
		for(String num: set) {
			if(map.get(num)==1) {
				for(int i=0; i<list.size(); i++)
					if(list.get(i).equals(num)) {
						System.out.print("#"+(i+1)+" "+num+" you are out of control!");
						return;
					}
			}
		}
	}

}
