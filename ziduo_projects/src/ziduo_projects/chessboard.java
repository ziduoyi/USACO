package ziduo_projects;
import java.io.*;
import java.util.*;
public class chessboard { //0 is white, 1 is black
	static char[][] chessBoard;
	static int[][] playerBoard;
	static int[][] knightMoves;
	static Map<Character, String[]>[][] pieceConvert;//square color, then piece color
	static String[][] numbersLeft;
	static boolean[][] canCastle;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		knightMoves = new int[][] {{2,1},{1,2},{-2,1},{1,-2},{2,-1},{-1,2},{-1,-2},{-2,-1}};
		
		numbersLeft = new String[9][5];
		numbersLeft[1] = new String[]{"        |","    /|  |","     |  |","        |","        |"};
		numbersLeft[2] = new String[]{"    _   |","     )  |","    /_  |","        |","        |"};
		numbersLeft[3] = new String[]{"    _   |","    _)  |","    _)  |","        |","        |"};
		numbersLeft[4] = new String[]{"     .  |","    /|  |","   '-|  |","        |","        |"};
		numbersLeft[5] = new String[]{"    _   |","   |_   |","    _)  |","        |","        |"};
		numbersLeft[6] = new String[]{"        |","    /   |","   (_)  |","        |","        |"};
		numbersLeft[7] = new String[]{"   __   |","    /   |","   /    |","        |","        |"};
		numbersLeft[8] = new String[]{"    _   |","   (_)  |","   (_)  |","        |","        |"};
		
		pieceConvert = new HashMap[2][2];
		pieceConvert[0][0] = new HashMap<>();
		pieceConvert[0][1] = new HashMap<>();
		pieceConvert[1][0] = new HashMap<>();
		pieceConvert[1][1] = new HashMap<>();
		
		pieceConvert[0][0].put('K', new String[]{"   _+_   ","   ) (   ","   | |   ","   | |   ","  /  \\   "});
		pieceConvert[0][0].put('Q', new String[]{"   www   ","   ) (   ","   | |   ","   | |   ","  /  \\   "});
		pieceConvert[0][0].put('R', new String[]{"         ","  |_|_|  ","   | |   ","   | |   ","  /  \\   "});
		pieceConvert[0][0].put('N', new String[]{"         ","   _,,   ","  \"- \\~  ","   | |   ","  /  \\   "});
		pieceConvert[0][0].put('B', new String[]{"         ","   (/)   ","   | |   ","   | |   ","  /  \\   "});
		pieceConvert[0][0].put('P', new String[]{"         ","    ()   ","    )(   ","   /  \\  ","         "});
		
		pieceConvert[0][1].put('K', new String[]{"   _+_   ","   )@(   ","   |@|   ","   |@|   ","  /@@\\   "});
		pieceConvert[0][1].put('Q', new String[]{"   www   ","   )@(   ","   |@|   ","   |@|   ","  /@@\\   "});
		pieceConvert[0][1].put('R', new String[]{"         ","  |_|_|  ","   |@|   ","   |@|   ","  /@@\\   "});
		pieceConvert[0][1].put('N', new String[]{"         ","   _,,   ","  \"- \\~  ","   |@|   ","  /@@\\   "});
		pieceConvert[0][1].put('B', new String[]{"         ","   (/)   ","   |@|   ","   |@|   ","  /@@\\   "});
		pieceConvert[0][1].put('P', new String[]{"         ","    ()   ","    )(   ","   /@@\\  ","         "});
		
		pieceConvert[1][0].put('K', new String[]{":::_+_:::",":::) (:::",":::| |:::",":::| |:::","::/  \\:::"});
		pieceConvert[1][0].put('Q', new String[]{":::www:::",":::) (:::",":::| |:::",":::| |:::","::/  \\:::"});
		pieceConvert[1][0].put('R', new String[]{":::::::::","::|_|_|::",":::| |:::",":::| |:::","::/  \\:::"});
		pieceConvert[1][0].put('N', new String[]{":::::::::",":::_,,:::","::\"- \\~::",":::| |:::","::/  \\:::"});
		pieceConvert[1][0].put('B', new String[]{":::::::::",":::(/):::",":::| |:::",":::| |:::","::/  \\:::"});
		pieceConvert[1][0].put('P', new String[]{":::::::::","::::():::","::::)(:::",":::/  \\::",":::::::::"});

		pieceConvert[1][1].put('K', new String[]{":::_+_:::",":::)@(:::",":::|@|:::",":::|@|:::","::/@@\\:::"});
		pieceConvert[1][1].put('Q', new String[]{":::www:::",":::)@(:::",":::|@|:::",":::|@|:::","::/@@\\:::"});
		pieceConvert[1][1].put('R', new String[]{":::::::::","::|_|_|::",":::|@|:::",":::|@|:::","::/@@\\:::"});
		pieceConvert[1][1].put('N', new String[]{":::::::::",":::_,,:::","::\"- \\~::",":::|@|:::","::/@@\\:::"});
		pieceConvert[1][1].put('B', new String[]{":::::::::",":::(/):::",":::|@|:::",":::|@|:::","::/@@\\:::"});
		pieceConvert[1][1].put('P', new String[]{":::::::::","::::():::","::::)(:::",":::/@@\\::",":::::::::"});
		setUp();
		int turn = 0;
		printBoardWhite();
		while(true) {
			System.out.print("    What is your next move?       ");
			String str = sc.next();
			char[] thing = str.toCharArray();
			boolean work = false;
			if(str.equals("O-O-O"))
				work = castleLong(turn);
			else if(str.equals("O-O"))
				work = castleShort(turn);
			else {
				if(thing.length==2) 
					work = deciper(thing[0]-'a'+1,'P',9-((int)(thing[1]-'0')),thing[0]-'a'+1,turn);
				if(thing.length==3&&thing[0]!='P')
					work = deciper(-1, thing[0], 9-((int)(thing[2]-'0')),thing[1]-'a'+1,turn);
				if(thing.length==4) {
					if((thing[0]-'a')>0&&(thing[0]-'a')<9) 
						work = deciper(thing[0]-'a'+1,'P',9-((int)(thing[3]-'0')),thing[2]-'a'+1,turn);
					if(thing[0]!='P'&&pieceConvert[0][0].containsKey(thing[0])) work = deciper(-1,thing[0],9-((int)(thing[3]-'0')),thing[2]-'a'+1,turn);
				}
				if(thing.length==5)
					work = classify(thing[0],9-((int)(thing[2]-'0')),thing[1]-'a'+1,9-((int)(thing[4]-'0')),thing[3]-'a'+1,turn);
			}
			if(!work){
				System.out.println("invalid move\n");
				continue;
			}
			if(thing[0]=='K') {
				canCastle[turn][0] = false;
				canCastle[turn][1] = false;
			}
			turn = 1-turn;
		}
	}
	
	static void setUp() {
		playerBoard = new int[9][9];
		canCastle = new boolean[2][2];
		Arrays.fill(canCastle[0], true);
		Arrays.fill(canCastle[1], true);
		for(int i=0; i<9; i++) Arrays.fill(playerBoard[i], -1);
		chessBoard = new char[][] {
			{' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ','R','N','B','Q','K','B','N','R'},
			{' ','P','P','P','P','P','P','P','P'},
			{' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ','P','P','P','P','P','P','P','P'},
			{' ','R','N','B','Q','K','B','N','R'}
		};
		Arrays.fill(playerBoard[7], 0);
		Arrays.fill(playerBoard[8], 0);
		Arrays.fill(playerBoard[1], 1);
		Arrays.fill(playerBoard[2], 1);
	}
	static void printBoardBlack() {
		String[] fillSquare= new String[] {"         ",":::::::::"};
		for(int i=8; i>0; i--) {		
			for(int k=0; k<5; k++) {
				System.out.print(numbersLeft[9-i][k]);
				for(int j=1; j<9; j++) {
					int type = 0;
					if((i+j)%2==1) type = 1;
					int color = playerBoard[i][j];
					char shape = chessBoard[i][j];
					if(shape!=' ')
						System.out.print(pieceConvert[type][color].get(shape)[k]);
					else System.out.print(fillSquare[type]);
				}
				System.out.println();
			}
		}
		System.out.println("         ________________________________________________________________________\n                      _        _        _        __       _        _           \n"+"            /\\       |_)      /        | \\      |_       |_       /        |_|\r\n"
				+ "           /--\\      |_)      \\_       |_/      |__      |        \\_?      | | \n"
				);
	}
	static void printBoardWhite() {
		String[] fillSquare= new String[] {"         ",":::::::::"};
		for(int i=1; i<9; i++) {		
			for(int k=0; k<5; k++) {
				System.out.print(numbersLeft[9-i][k]);
				for(int j=1; j<9; j++) {
					int type = 0;
					if((i+j)%2==1) type = 1;
					int color = playerBoard[i][j];
					char shape = chessBoard[i][j];
					if(shape!=' ')
						System.out.print(pieceConvert[type][color].get(shape)[k]);
					else System.out.print(fillSquare[type]);
				}
				System.out.println();
			}
		}
		System.out.println("         ________________________________________________________________________\n                      _        _        _        __       _        _           \n"+"            /\\       |_)      /        | \\      |_       |_       /        |_|\r\n"
				+ "           /--\\      |_)      \\_       |_/      |__      |        \\_?      | | \n"
				);
	}
	static boolean deciper(int col, char c, int endx, int endy, int side) {
		if(endx>8||endx<1||endy>8||endy<1)return false;
		int[] arr= new int[2];
		if(c=='P'&&col>=1&&col<=8) arr = pawnFind(col, endx, endy, side).clone();
		if(c=='N') arr = knightFind(endx, endy, side);
		if(c=='K') arr = kingFind(endx, endy, side);
		if(c=='R') arr = rookFind(endx, endy, side);
		if(c=='Q') arr = queenFind(endx, endy, side);
		if(c=='B') arr = bishopFind(endx, endy, side);
		if(arr[0]==-1)return false;
		
		return classify(c, arr[0],arr[1],endx, endy, side);
	}
	static boolean classify(char c, int startx, int starty, int endx, int endy, int side) {
		if(startx==endx&&starty==endy)return false;
		if(startx<1||startx>8||starty<1||starty>8||endx<1||endx>8||endy<1||endy>8) return false;
		if(!pieceConvert[0][0].containsKey(c)) return false;
		boolean work= false;
		if(c=='N')work = knightMove(startx, starty, endx, endy, side);
		if(c=='K')work = kingMove(startx, starty, endx, endy, side);
		if(c=='P')work = pawnMove(startx, starty, endx, endy, side);
		if(c=='B')work = bishopMove(startx, starty, endx, endy, side);
		if(c=='R') {
			work = rookMove(startx, starty, endx, endy, side);
			if(work) {
				if(starty==1)canCastle[side][0] = false;
				if(starty==8)canCastle[side][1] = false;
			}
		}
		if(c=='Q')work = queenMove(startx, starty, endx, endy, side);
		if(!work)return false;
		autoQueen();
		if(1-side==0) printBoardWhite();
		else printBoardBlack();
		if(inCheck(1000000000,0,0,0,1-side)) {
			if(1-side==0)System.out.println("\n White is in check \n");
			else System.out.println("\n Black is in check \n");
		}
		return true;
	}
	static void movePiece(int startx, int starty, int endx, int endy) {
		playerBoard[endx][endy] = playerBoard[startx][starty];
		chessBoard[endx][endy] = chessBoard[startx][starty];
		playerBoard[startx][starty] =-1;
		chessBoard[startx][starty] = ' ';
	}
	static void autoQueen() {
		for(int i=1; i<=8; i+=7)
			for(int j=1; j<=8; j++)
				if(chessBoard[i][j] == 'P')
					chessBoard[i][j] = 'Q';
	}
	static boolean inCheck(int startx, int starty, int endx, int endy, int side) {
		int posx = 0;
		int posy = 0;
		char[][] chessBoard1 = new char[9][9];
		int[][] playerBoard1 = new int[9][9];
		for(int i=1; i<9; i++) {
			chessBoard1[i] = chessBoard[i].clone();
			playerBoard1[i] = playerBoard[i].clone();
		}
		if(startx!=1000000000) {
			chessBoard1[endx][endy] = chessBoard1[startx][starty];
			playerBoard1[endx][endy] = playerBoard1[startx][starty];
			chessBoard1[startx][starty] = ' ';
			playerBoard1[startx][starty] = -1;
		}
		for(int i=1; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(chessBoard1[i][j]=='K'&&playerBoard1[i][j]==side) {
					posx = i;
					posy = j;
					break;
				}
			}
		}
		//check knight
		for(int i=0; i<8; i++) {
			int x = posx + knightMoves[i][0];
			int y = posy + knightMoves[i][1];
			if(x>0&&x<9&&y>0&&y<9) {
				if(chessBoard1[x][y]=='N'&&playerBoard1[x][y]==1-side)
					return true;
			}
		}
		//check pawn
		int l = posy-1;
		int r = posy+1;
		if(side==0) {
			if(posx>1) {
				if(l>0&&chessBoard1[posx-1][l]=='P'&&playerBoard1[posx-1][l]==1-side)
					return true;
				if(r<9&&chessBoard1[posx-1][r]=='P'&&playerBoard1[posx-1][r]==1-side)
					return true;
			}
		}
		else {
			if(posx<8) {
				if(l>0&&chessBoard1[posx+1][l]=='P'&&playerBoard1[posx+1][l]==1-side)
					return true;
				if(r<9&&chessBoard1[posx+1][r]=='P'&&playerBoard1[posx+1][r]==1-side)
					return true;
			}
		}
		//check king
		for(int i=-1; i<=1; i++)
			for(int j=-1; j<=1; j++)
				if(posx+i>=1&&posx+i<=8&&posy+j>=1&&posy+j<=8)
					if((!(i==0&&j==0))&&chessBoard1[posx+i][posy+j]=='K')
						return true;
		//check bishop + queen
		for(int i=-1; i<=1; i+=2) {
			for(int j=-1; j<=1; j+=2) {
				for(int k=1; k<=8; k++) {
					int x = posx+i*k;
					int y = posy+j*k;
					if(x<1||x>8||y<1||y>8)break;
					if(chessBoard1[x][y]!=' ') {
						if(chessBoard1[x][y]=='B'||chessBoard1[x][y]=='Q')
							if(playerBoard1[x][y]==1-side)
								return true;
						break;
					}
				}
			}
		}
		//check rook + queen
		for(int i=-1; i<=1; i+=2) {
			for(int k=1; k<=8; k++) {
				int x = posx + i*k;
				int y = posy;
				if(x<1||x>8)break;
				if(chessBoard1[x][y]!=' ') {
					if(chessBoard1[x][y]=='R'||chessBoard1[x][y]=='Q')
						if(playerBoard1[x][y]==1-side)
							return true;
					break;
				}
			}
		}
		for(int i=-1; i<=1; i+=2) {
			for(int k=1; k<=8; k++) {
				int x = posx;
				int y = posy+i*k;
				if(y<1||y>8)break;
				if(chessBoard1[x][y]!=' ') {
					if(chessBoard1[x][y]=='R'||chessBoard1[x][y]=='Q')
						if(playerBoard1[x][y]==1-side)
							return true;
					break;
				}
			}
		}
		return false;
	}
	static boolean pawnMove(int startx, int starty, int endx, int endy, int side) {
		if(chessBoard[startx][starty]!='P'||playerBoard[startx][starty]!=side)return false;
		if(playerBoard[endx][endy]==side) return false;
		if(inCheck(startx, starty, endx, endy, side)) return false;
		boolean found = false;
		if(side==0) {
			if(endy!=starty) {
				if(Math.abs(endy-starty)==1&&startx-endx==1) 
					if(chessBoard[endx][endy]!=' ') 
						if(playerBoard[endx][endy]!=side) 
							found = true;
			}
			else {
				if(startx-endx==2)
					if(startx==7)
						if(chessBoard[endx][endy]==' ')
							if(chessBoard[endx+1][endy]==' ')
								found = true;
				if(startx-endx==1) {
					if(chessBoard[endx][endy]==' ')
						found = true;
				}
			}
		}
		else {
			if(endy!=starty) {
				if(Math.abs(endy-starty)==1&&startx-endx==-1) 
					if(chessBoard[endx][endy]!=' ') 
						if(playerBoard[endx][endy]!=side) 
							found = true;
			}
			else {
				if(startx-endx==-2) {
					if(startx==2)
						if(chessBoard[endx][endy]==' ')
							if(chessBoard[endx-1][endy]==' ')
								found = true;
				}
				if(startx-endx==-1) {
					if(chessBoard[endx][endy]==' ')
						found = true;
				}
			}			
		}
		if(!found)return false;
		movePiece(startx, starty, endx, endy);
		return true;
	}
	static boolean knightMove(int startx, int starty, int endx, int endy, int side) {
		if(chessBoard[startx][starty]!='N'||playerBoard[startx][starty]!=side)return false;
		if(playerBoard[endx][endy]==side) return false;
		if(inCheck(startx, starty, endx, endy, side)) return false;		
		if((Math.abs(startx-endx)==2&&Math.abs(starty-endy)==1)||(Math.abs(startx-endx)==1&&Math.abs(starty-endy)==2)) {
			movePiece(startx, starty, endx, endy);
			return true;
		}
		return false;
	}
	static boolean kingMove(int startx, int starty, int endx, int endy, int side) {
		if(chessBoard[startx][starty]!='K'||playerBoard[startx][starty]!=side)return false;
		if(playerBoard[endx][endy]==side) return false;
		if(inCheck(startx, starty, endx, endy, side)) return false;		
		if(Math.abs(startx-endx)>1||Math.abs(starty-endy)>1)return false;
		movePiece(startx, starty, endx, endy);
		return true;
	}
	static boolean rookMove(int startx, int starty, int endx, int endy, int side) {
		if(chessBoard[startx][starty]!='R'||playerBoard[startx][starty]!=side)return false;
		if(playerBoard[endx][endy]==side) return false;
		if(inCheck(startx, starty, endx, endy, side)) return false;		
		if(startx-endx!=0&&starty-endy!=0)return false;
		if(startx-endx==0) 
			for(int i=Math.min(starty, endy)+1; i<=Math.max(starty, endy)-1; i++) {
				if(chessBoard[startx][i]!=' ')return false;
			}
		else
			for(int i=Math.min(startx, endx)+1; i<=Math.max(startx, endx)-1; i++) {
				if(chessBoard[i][starty]!=' ')return false;	
			}
		movePiece(startx, starty, endx, endy);
		return true;
	}
	static boolean bishopMove(int startx, int starty, int endx, int endy, int side) {
		if(chessBoard[startx][starty]!='B'||playerBoard[startx][starty]!=side)return false;
		if(playerBoard[endx][endy]==side) return false;
		if(inCheck(startx, starty, endx, endy, side)) return false;
		if(Math.abs(startx-endx)!=Math.abs(starty-endy))return false;
		int incx = (endx-startx)/Math.abs(startx-endx);
		int incy = (endy-starty)/Math.abs(starty-endy);
		for(int i=1; i<=Math.abs(startx-endx)-1; i++)
			if(chessBoard[startx+i*incx][starty+i*incy]!=' ')
				return false;
		movePiece(startx, starty, endx, endy);
		return true;
	}
	static boolean queenMove(int startx, int starty, int endx, int endy, int side) {
		if(chessBoard[startx][starty]!='Q'||playerBoard[startx][starty]!=side)return false;
		if(playerBoard[endx][endy]==side) return false;
		if(inCheck(startx, starty, endx, endy, side)) return false;	
		chessBoard[startx][starty] = 'B';
		if(bishopMove(startx, starty, endx, endy,side)) {
			chessBoard[endx][endy] = 'Q';
			return true;
		}
		chessBoard[startx][starty] = 'R';
		if(rookMove(startx, starty, endx, endy,side)){
			chessBoard[endx][endy] = 'Q';
			return true;
		}chessBoard[startx][starty] = 'Q';
		return false;
	}
	static int[] knightFind(int endx, int endy, int side) {
		for(int i=0; i<8; i++) {
			int x = endx+knightMoves[i][0];
			int y = endy+knightMoves[i][1];
			if(x<1||x>8||y<1||y>8)continue;
			if(chessBoard[x][y]=='N'&&playerBoard[x][y]==side)
				return new int[] {x, y};
		}
		return new int[] {-1,-1};
	}
	static int[] rookFind(int endx, int endy, int side) {
		for(int i=endx+1; i<=8; i++) {
			if(chessBoard[i][endy]!=' ') {
				if(chessBoard[i][endy]=='R'&&playerBoard[i][endy]==side) 
					return new int[] {i, endy};				
				break;
			}
		}
		for(int i=endx-1; i>=1; i--) {
			if(chessBoard[i][endy]!=' ') {
				if(chessBoard[i][endy]=='R'&&playerBoard[i][endy]==side) 
					return new int[] {i, endy};				
				break;
			}
		}
		for(int i=endy+1; i<=8; i++) {
			if(chessBoard[endx][i]!=' ') {
				if(chessBoard[endx][i]=='R'&&playerBoard[endx][i]==side) 
					return new int[] {endx, i};				
				break;
			}
		}
		for(int i=endy-1; i>=1; i--) {
			if(chessBoard[endx][i]!=' ') {
				if(chessBoard[endx][i]=='R'&&playerBoard[endx][i]==side) 
					return new int[] {endx, i};				
				break;
			}
		}
		return new int[] {-1,-1};
	}
	static int[] bishopFind(int endx, int endy, int side) {
		for(int i=-1; i<=1; i+=2) {
			for(int j=-1; j<=1; j+=2) {
				for(int k=1; k<=8; k++) {
					int x = endx + i*k;
					int y = endy + j*k;
					if(x<1||x>8||y<1||y>8)continue;
					if(chessBoard[x][y] !=' ') {
						if(chessBoard[x][y] == 'B'&&playerBoard[x][y]==side)
							return new int[] {x,y};
						break;
					}
				}
			}
		}
		return new int[] {-1,-1};
	}
	static int[] queenFind(int endx, int endy, int side) {
		for(int i=endx+1; i<=8; i++) {
			if(chessBoard[i][endy]!=' ') {
				if(chessBoard[i][endy]=='Q'&&playerBoard[i][endy]==side) 
					return new int[] {i, endy};				
				break;
			}
		}
		for(int i=endx-1; i>=1; i--) {
			if(chessBoard[i][endy]!=' ') {
				if(chessBoard[i][endy]=='Q'&&playerBoard[i][endy]==side) 
					return new int[] {i, endy};				
				break;
			}
		}
		for(int i=endy+1; i<=8; i++) {
			if(chessBoard[endx][i]!=' ') {
				if(chessBoard[endx][i]=='Q'&&playerBoard[endx][i]==side) 
					return new int[] {endx, i};				
				break;
			}
		}
		for(int i=endy-1; i>=1; i--) {
			if(chessBoard[endx][i]!=' ') {
				if(chessBoard[endx][i]=='Q'&&playerBoard[endx][i]==side) 
					return new int[] {endx, i};				
				break;
			}
		}
		for(int i=-1; i<=1; i+=2) {
			for(int j=-1; j<=1; j+=2) {
				for(int k=1; k<=8; k++) {
					int x = endx + i*k;
					int y = endy + j*k;
					if(x<1||x>8||y<1||y>8)continue;
					if(chessBoard[x][y] !=' ') {
						if(chessBoard[x][y] == 'Q'&&playerBoard[x][y]==side)
							return new int[] {x,y};
						break;
					}
				}
			}
		}
		return new int[] {-1,-1};
	}
	static int[] kingFind(int endx, int endy, int side) {
		for(int i=-1; i<=1; i++) {
			for(int j=-1; j<=1; j++) {
				if(i==0&&j==0)continue;
				int x = endx+i;
				int y = endy+j;
				if(chessBoard[x][y]=='K'&&playerBoard[x][y]==side)
					return new int[] {x,y};
			}
		}
		return new int[] {-1,-1};
	}
	static int[] pawnFind(int col, int endx, int endy, int side) {
		if(col==endy) {
			if(side==0) {
				for(int i=1; i<=2; i++) {
					int x = endx + i;
					if(x>8)break;
					if(chessBoard[x][endy]=='P'&&playerBoard[x][endy]==side)
						return new int[] {x, endy};
				}
				return new int[] {-1,-1};
			}
			else {
				for(int i=1; i<=2; i++) {
					int x = endx - i;
					if(x<1)break;
					if(chessBoard[x][endy]=='P'&&playerBoard[x][endy]==side)
						return new int[] {x, endy};
				}
				return new int[] {-1,-1};
			}
		}
		if(side==0) {
			int x = endx+1;
			if(x<=8&&chessBoard[x][col]=='P'&&playerBoard[x][col]==side)
				return new int[] {x,col};
			return new int[] {-1,-1};
		}
		int x = endx - 1;
		if(x>=1&&chessBoard[x][col]=='P'&&playerBoard[x][col]==side)
			return new int[] {x, col};
		return new int[] {-1,-1};
	}
	static boolean castleShort(int side) {
		if(canCastle[side][1]==false)return false;
		int x = 1;
		if(side==0) x=8;
		boolean work = true;
		for(int i=6; i<8; i++) {
			if(chessBoard[x][i]!=' '||inCheck(x,5,x,i,side))
				work = false;
		}
		if(!work) return false;
		canCastle[side][1] = false;
		canCastle[side][0] = false;
		movePiece(x,5,x,7);
		movePiece(x,8,x,6);
		if(1-side==0) printBoardWhite();
		else printBoardBlack();
		if(inCheck(1000000000,0,0,0,1-side)) {
			if(1-side==0)System.out.println("\n White is in check \n");
			else System.out.println("\n Black is in check \n");
		}
		return true;
	}
	static boolean castleLong(int side) {
		if(canCastle[side][0]==false)return false;
		int x = 1;
		if(side==0) x=8;
		boolean work = true;
		for(int i=2; i<5; i++) {
			if(chessBoard[x][i]!=' '||inCheck(x,5,x,i,side))
				work = false;
		}
		if(!work) return false;
		canCastle[side][0] = false;
		canCastle[side][1] = false;
		movePiece(x,5,x,3);
		movePiece(x,1,x,4);
		if(1-side==0) printBoardWhite();
		else printBoardBlack();
		if(inCheck(1000000000,0,0,0,1-side)) {
			if(1-side==0)System.out.println("\n White is in check \n");
			else System.out.println("\n Black is in check \n");
		}
		return true;
	}
}
