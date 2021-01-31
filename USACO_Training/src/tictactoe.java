import java.util.*;
import java.io.*;
public class tictactoe {

	public static void main(String[] args) throws IOException{
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tttt.out")));
		Scanner scanner= new Scanner(new File("tttt.in"));
		char[][] arr=new char[3][3];
		for(int i=0; i<3; i++) {
			String s=scanner.next();
			arr[i]=s.toCharArray();	
		}
		int team=0;
		int solo=0;
		int flag=0;
		for(int i=0; i<3; i++) {
			flag=0;
			if(arr[i][0]==arr[i][1])
				flag++;
			if(arr[i][0]==arr[i][2])
				flag++;
			if(arr[i][1]==arr[i][2])
				flag++;
			if(flag==1)
				team++;
			if(flag==3) 
				solo++;
		}
		
		for(int i=0; i<3; i++) {
			flag=0;
			if(arr[0][i]==arr[1][i])
				flag++;
			if(arr[0][i]==arr[2][i])
				flag++;
			if(arr[1][i]==arr[2][i])
				flag++;
			if(flag==1)
				team++;
			if(flag==3) 
				solo++;
		}
		flag=0;
		for(int i=0; i<2; i++) {
			if(arr[i][i]==arr[i+1][i+1])
				flag++;
		}
		if(arr[0][0]==arr[2][2])
			flag++;
		if(flag==1)
			team++;
		if(flag==3) 
			solo++;
		flag=0;
		if(arr[0][2]==arr[1][1])
			flag++;
		if(arr[0][2]==arr[2][0])
			flag++;
		if(arr[1][1]==arr[2][0])
			flag++;
		if(flag==1)
			team++;
		if(flag==3) 
			solo++;
		out.println(solo);
		out.println(team);
		out.close();
	}
}