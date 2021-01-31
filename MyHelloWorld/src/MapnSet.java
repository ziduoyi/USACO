import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MapnSet {

	static class Point{
		int x,y;
		Point(int i, int j){
			this.x=i;
			this.y=j;
		}
		@Override
		public String toString(){
			return this.x + "," + this.y;
		}
		
		@Override
		public boolean equals(Object obj){
			if(this.x==((Point)obj).x && this.y==((Point)obj).x)
				return true;
			return false;
		}
		
		@Override
		public int hashCode(){
			String s = x +" "+ y;
			return s.hashCode();
		}
	}

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
		
		Map<Point, Integer> map2 = new HashMap<>();
		map2.put(new Point(1,2), 11);
		map2.put(new Point(3,3), 13);
		map2.put(new Point(2,1), 12);
		
		Point p = new Point(2,1);
		Point p2 = new Point(2,5);
		System.out.println(map2);
		System.out.println(map2.containsKey(p));
		System.out.println(map2.containsKey(p2));
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
