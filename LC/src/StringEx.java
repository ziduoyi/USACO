
public class StringEx {

	String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		
		String str1 = "World";
		
		String str2 = "";
		String str4;
		
		
		int x1 =10;
		
		int x2 = Math.max(x1, 100);
		
		
		Card mycard = new Card(1, 2);
		mycard.rank = 10;
		//mycard.getMyCard();
		mycard.getRank();
		Card.printName();
		//str4.trim();
		
		str2.trim();
		
		String str3 = str2.replace("w", "W");
		
		str3.indexOf('o');
		//if(str1.equals(str2)) {
		System.out.println("str1=" + str1 + "  str2=" + str2 + "  str3="+str3 );
		//}
		
//		String str3 = str1.substring(6,10);
//		
//		str3 = str2;
//		
//		if(str2 == str3) {
//			System.out.println("matched");
//		}
//		
//		if(str2.equals(str3)){
//			;//
//		}
		
//		length();
//		parseInt()
//		
//		substring()
//		indexOf()
//		valueOf()
//		equals()
		

	}

	//
	
}
/*
String
1) immutable
2) equals




Encapsulation:





*/