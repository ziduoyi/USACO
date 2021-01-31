import java.util.*;
import java.io.*;

public class Class1 {

	public static void main(String[] args)throws IOException {
	    System.out.println("\n\t\t Speeding Ticket \n");
	    // do not change this main method

	    Traffic ticket = new Traffic(35, 35, 'N');
	    ticket.calculateTicket();
	    System.out.println(ticket);

	    ticket = new Traffic(60, 45, 'N');
	    ticket.calculateTicket();
	    System.out.println(ticket);

	    ticket = new Traffic(40, 20, 'Y');
	    ticket.calculateTicket();
	    System.out.println(ticket);

	    ticket = new Traffic(100, 60, 'N');
	    ticket.calculateTicket();
	    System.out.println(ticket);

	    ticket = new Traffic(100, 60, 'Y');
	    ticket.calculateTicket();
	    System.out.println(ticket);
	}
}
class Traffic {
	  private int speed;
	  private int limit;
	  private char zone;
	  private double cost;

	  public Traffic(int mySpeed, int myLimit, char myZone) {
	     speed = mySpeed;
	     limit = myLimit;
	     zone = myZone;
	     cost = 0;
	  }

	  public void calculateTicket() {

	    if (speed > limit) {
	      cost += 30;

	      if (speed > limit + 30) {
	        cost += 100;

	        if (zone == 'Y') {
	          cost += 6 * (speed - limit);

	          if (zone == 'X') {
	            cost += 3 * (speed - limit);
	          }
	        }
	      }
	    }

	  }

	  public String toString() {

	    return "" + cost;
	  }
	}
	
		
	
