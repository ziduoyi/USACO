import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class zmapset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Integer> myMap = new HashMap<>();
		myMap.put("dog",2);
		myMap.put("tiger", 3);
		myMap.put("tiger", 5);

		System.out.println(myMap);
		
		Map<String, Integer> map = new TreeMap<>();
		map.put("dog",1);
		map.put("tiger", 5);
		map.put("tiger", 3);

		System.out.println(myMap);
		
		//map.
		
	}
	
	
	void setEx(){
		Set<String> set = new HashSet<>();
		
		set.add("dog");
		set.add("cat");
		set.add("dog");
		set.add("apple");
		
		Set<String> set2 = new HashSet<>();
		set2.add("tiger");
		set2.add("jaguar");
		set2.add("tiger");
		
		set2.addAll(set);
		
		set2.removeAll(set);
		set2.clear();
		
		System.out.println(set);
		System.out.println(set2);
		
		
		Set<String> set3 = new TreeSet<>();
		
		set3.add("dog");
		set3.add("cat");
		set3.add("dog");
		set3.add("apple");

		
		Set<String> set4 = new TreeSet<>();
		set4.add("tiger");
		set4.add("jaguar");
		set4.add("tiger");
		
		set4.addAll(set3);
		
		
		
		System.out.println(set3);
		System.out.println(set4);
		
		for(String s : set4){
			System.out.println(s);
		}
	}

}

