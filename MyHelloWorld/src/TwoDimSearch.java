import java.util.LinkedList;

public class TwoDimSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] m = new int[][]{{1,1,1,1,1,1,1,1,1,1},
								{1,0,0,0,0,0,1,1,1,1},
								{1,1,1,1,0,1,1,1,1,1},
								{1,0,0,0,0,0,1,1,1,1},
								{1,0,1,1,1,0,1,1,1,1},
								{1,0,1,1,1,0,0,0,0,1},
								{0,0,1,1,1,0,0,0,0,0},
								{1,0,1,1,1,1,1,1,1,1},
								{1,1,1,1,1,1,1,1,1,1},
								{1,1,1,1,1,1,1,1,1,1}};

		int[] start= new int[]{1,1};
		TwoDimSearch tds = new TwoDimSearch();
		//System.out.println(tds.bfs(m,start));
		tds.dfs(m,start[0],start[1],1);
		System.out.println(tds.res);//(tds.res==9999?-1:tds.res);
	}
	
	int bfs(int[][] m, int[] start){
		int path = 0;
		int R = m.length-1, C=m[0].length-1;
		
		int[][] direct = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};	//4 directions
		
		LinkedList<int[]> list = new LinkedList<>();
		
		list.add(start);
		
		while(!list.isEmpty()){
			int n = list.size();
			path++;
			for(int i=0;i<n;i++){
				int[] arr = list.removeFirst();
				m[arr[0]][arr[1]]=2;	//// -----------------------------set flag to prevent going back
				if(arr[0]==0 || arr[0]==R || arr[1]==0 || arr[1]==C)
					return path;
				
				for(int d=0;d<4;d++){							//find possible next 4 moves
					int x = arr[0] + direct[d][0];
					int y = arr[1] + direct[d][1];
					//if(x>=0 && x<=R && y>=0 && y<=C){
					if(m[x][y]==0){		////-------------------------------check flag for next move
						int[] temp = new int[]{x,y};
						list.add(temp);
					}
				}
			}
		}
		
		return -1;
	}
	
	int res = 9999;
	int dfs(int[][] m, int i, int j, int step){
		int R = m.length-1, C=m[0].length-1;

		if(i==0 || i==R || j==0 || j==C){
			res = Math.min(res, step);
			return res;
		}
		m[i][j]=2;		//// -----------------------------set flag to prevent going back
		int[][] direct = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};	//4 directions
		for(int d=0;d<4;d++){							//find possible next 4 moves
			int x = i + direct[d][0];
			int y = j + direct[d][1];
			//if(x>=0 && x<=R && y>=0 && y<=C){
			if(m[x][y]==0){		////-------------------------------check flag for next move
				dfs(m,x,y,step+1);
			}
		}

		return -1;
	}

}
