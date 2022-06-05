import java.util.*;
class Main{
  public static void main(String[] args){
    System.out.println("\nGrade Average Lab");
    Grade student = new Grade("Bob Jones", "95, 87, 92, 98");
    student.calcAverage();
    student.calcLetterGrade();
    System.out.println(student);
    student = new Grade("Arnold Palmer", "87, 82, 92, 100, 54, 78");
    student.calcAverage();
    student.calcLetterGrade();
    System.out.println(student);
    student = new Grade("Ben Hogan", "97, 92, 75, 82, 34, 78");
    student.calcAverage();
    student.calcLetterGrade();
    System.out.println(student);
  }
}
class Grade{
  private String studentName;
  private double average;
  private char letterGrade;
  private String myGrades;
  public Grade(String myName, String grades){
    // assign myName to studentName, assign grades to myGrades below
    myGrades = grades;
    studentName = myName;
  }
  public void calcAverage(){
    // create local variables for grade, count, and total below
	int size = 0;
    Scanner input = new Scanner(myGrades);
    while(input.hasNext()){
      String stuff = input.next();
      if(input.hasNext())
    	  stuff = stuff.substring(0, stuff.length()-1);
      int next = Integer.parseInt(stuff);
      average += next;
      size++;
    }
    average/=size;
    // create while loop below with hasNext
    // inside while loop use grade = input.nextInt(); to read each grade, count
    //the grades, total the grades together, calculate average (after loop)
  }
  public void calcLetterGrade(){
	  if(average>=90)letterGrade = 'A';
	  if(average>=80&&average<=89)letterGrade='B';
	  if(average>=70&&average<=79)letterGrade='C';
	  if(average>=60&&average<=69)letterGrade='D';
	  if(average<60) letterGrade = 'F';
    // Use the average calculated in calcAverage to determine and assign 'A', 'B',
    //'C', 'D', or 'F' to letterGrade below use if commands or if/else commands
  }
  public String toString(){
    // Create and return a String that has the studentName, average, and letterGrade below
	if(average%1==0)
		return studentName + " has an average of "+ (int)average+" and made an "+letterGrade;
	else {
		if(average%0.1==0)
			return studentName + " has an average of "+ (Math.floor(average*10)/10)+" and made an "+letterGrade;
		return studentName + " has an average of "+ (Math.floor(average*100)/100)+" and made an "+letterGrade;
	}
  }
}