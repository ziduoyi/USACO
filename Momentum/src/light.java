import java.util.*;
import java.io.*;
public class light {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("lightson.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
		double d= System.currentTimeMillis();
		int n=scanner.nextInt();
		int m=scanner.nextInt();
		int[][] arr=new int[n+1][n+1];
		arr[1][1]=11;
		LinkedList<room> list=new LinkedList<>();
		room pos=new room(1,1);
		list.add(pos);
		int rooms=1;
		HashMap<room,ArrayList<room>> lightMap = new HashMap<>();
		for(int i=0; i<m; i++) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			room key = new room(x,y);
			x = scanner.nextInt();
			y = scanner.nextInt();
			room value = new room(x,y);
			ArrayList<room> lightables = lightMap.get(key);
			if(lightables==null) {
				lightables = new ArrayList<>();
				lightMap.put(key, lightables);
			}
			lightables.add(value);
		}
		int[][] ways= new int[][] {{-1,0,},{0,1},{1, 0},{0, -1}};
		while(!list.isEmpty()) {
				room node=list.removeFirst();
				ArrayList<room> lr = lightMap.get(node);
				if(lr!=null)
					for(room rm : lr) {
						if(arr[rm.row][rm.col]==1) {
							arr[rm.row][rm.col]=11;
							rooms++;
							list.add(rm);
						}
						if(arr[rm.row][rm.col]==0) {
							arr[rm.row][rm.col]=10;
							//rooms++;			//----need to add this
						}
					}
				
				for(int j=0; j<4; j++) {
					int x=node.row+ways[j][0];
					int y=node.col+ways[j][1];
					if(x>0 && y>0&& x<=n && y<=n) {
						if(arr[x][y]==10) {
							arr[x][y]=11;
							rooms++;
							list.add(new room(x,y));
						}
						if(arr[x][y]==0)
							arr[x][y]=1;
					}
				}
		}
		rooms=0;
		for(int[] lr : arr) {
			for(int r : lr)
				if(r>1)
					rooms++;
		}
		out.println(rooms);
		out.close();
		double d2= System.currentTimeMillis();
		System.out.println((d2-d)/1000);
		return;
	}
	static class room{
		public int row;
		public int col;
		room(int x, int y){
			row=x;
			col=y;
		}
		@Override
		public boolean equals(Object obj) {
			room other = (room) obj;
			if(this.row==other.row && this.col==other.col)
				return true;
			return false;
		}
		@Override
		public int hashCode() {
			return (this.row + " "+ this.col).hashCode();
		}
	}
}
