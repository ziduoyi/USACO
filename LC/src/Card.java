
public class Card {

	int rank;
	String suit;
	int pointValue;
	
	int count=0;
	
	public Card() {
		
	}
	
	public Card(int rk) {
		rank = rk;
	}
	
	public Card(int pv, int rk) {
		pointValue = pv;
	}
	
	public Card(String st) {
		suit  = st;
		rank=19;
		if(count()>10) {
			int rank =10;
			System.out.println("ooh, too many cards: " + rank);
		}
		System.out.println("ooh, too many cards: " + rank);
		
	}
	
	//public, private
	public static void printName() {
		System.out.println("static");
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	private int count() {
		count++;
		return count;
	}
	
	
	
}
