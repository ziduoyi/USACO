import java.util.*;
public class island {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int H = scanner.nextInt();
		int W = scanner.nextInt();
		int[][] arr = new int[H][W];//-1: X    0: .   1: A
		for(int i=0; i<H; i++) {
			String str = scanner.next();
			for(int j=0; j<W; j++) {
				if(str.charAt(j)=='x') 
					arr[i][j] = -1;
				else if(str.charAt(j)=='A')
					arr[i][j] =1;
			}
		}
		boolean[][] visit = new boolean[H][W];
		LinkedList<int[]> list = new LinkedList<>(); // h , w
		int[][] direct = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
		int[][] check = new int[][] {{1,0},{1,1},{0,1}, {-1,0},{0,-1},{-1,-1}, {-1,1},{1,-1}};
		for(int i=1; i<H-1; i++) {
			for(int j =1; j<W-1; j++) {
				if(arr[i][j] == 0) {
					int count =0;
					boolean b = false;
					if(arr[i+direct[0][0]][j+direct[0][1]] ==1&&arr[i+direct[3][0]][j+direct[3][1]] ==1)
						b= true;
					for(int k =0; k<4; k++) {
						if(arr[i+direct[k][0]][j+direct[k][1]] ==1) {
							if(count ==1)
								b = true;
							count=1;
						}
						else {
							count =0;
						}
					}
					for(int k =0; k<8; k++) {
						if(arr[i+check[k][0]][j+check[k][1]] ==-1)
							count = -10;
					}
					if(b==true&&count!=-10) {
						visit[i][j] =true;
						arr[i][j] = 1;
						for(int k =0; k<4; k++) {
							if(arr[i+direct[k][0]][j+direct[k][1]] == 0) {
								int[] temp = new int[] {i+direct[k][0], j+direct[k][1]};
								list.add(temp);
								visit[i+direct[k][0]][j+direct[k][1]]= true;
							}
						}
					}
				}
				else
					visit[i][j] = true;
			}
		}
		while(!list.isEmpty()) {
			int[] use = list.removeFirst();
			if(use[0]==0||use[1]==0||use[0] == H-1||use[1]==W-1)
				continue;
			int count =0;
			boolean b = false;
			if(arr[use[0]+direct[0][0]][use[1]+direct[0][1]] ==1&&arr[use[0]+direct[3][0]][use[1]+direct[3][1]] ==1)
				b= true;
			for(int k =0; k<4; k++) {
				if(arr[use[0]+direct[k][0]][use[1]+direct[k][1]] ==1) {
					if(count ==1)
						b = true;
					count=1;
				}
				else {
					count =0;
				}
			}
			for(int k =0; k<8; k++) {
				if(arr[use[0]+check[k][0]][use[1]+check[k][1]] ==-1)
					count = -10;
			}
			if(b==true&&count!=-10) {
				visit[use[0]][use[1]] =true;
				arr[use[0]][use[1]] = 1;
				for(int i =0; i<4; i++) {
					if(arr[use[0]+direct[i][0]][use[1]+direct[i][1]] == 0) {
						int[] temp = new int[] {use[0]+direct[i][0], use[1]+direct[i][1]};
						list.add(temp);
						visit[use[0]+direct[i][0]][use[1]+direct[i][1]]= true;
					}
				}
			}
		}

		
	}

}
