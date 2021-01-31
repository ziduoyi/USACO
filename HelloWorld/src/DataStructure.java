import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

// Simple/basic data types
// Class Objects: Integer, Double, String,
// Arrays
//		default value, init, fill(), sort(), copyOf(), 

// Collections: sort()
// Linked List: 
//			<>: template
//			add(3,"Value"), remove(), get(), set(), remvoeFirst(), addLast(), etc

// Recursion: 
//	1) function call itself
//	2) Parameter diminish 
//	3) Exit criteria


public class DataStructure {

	public class ListNode{
		public ListNode next;
		public Integer val;
		public ListNode(int x){
			val = x;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DataStructure ds = new DataStructure();
/*		
		String str = "hello world";
    	char[] cs = str.toCharArray();
    	cs[0]--;
    	int x = 'c'-'a';
		
    	System.out.println(x);
    	System.out.println(11);
    	//System.out.printf("%2d", x);
    	
    	int[][] m = new int[4][];
    	m[0] = new int[]{10,8,4,2,1,3,4,8,2,9,10,4};
    	m[1] = Arrays.copyOf(m[0], 5);
    	m[2] = Arrays.copyOfRange(m[1], 3, 5);
    	m[3] = new int[100];
    	Arrays.fill(m[3], 10);
    	//System.out.println(m[0]);
    	Arrays.sort(m[0]);
    	
    	LinkedList<Integer> list = new LinkedList<>();
    	list.add(0,8);
    	list.add(0,5);
    	list.add(2,3);
    	list.set(1, 100);
    	//list.remove(1);
    	
    	//loop a linked list
    	for(int i=0;i<list.size();i++){
    		System.out.print(" " + list.get(i));
    		//list.remove(0);
    	}
    	list.sort(null);
    	Collections.sort(list,(a,b)->b-a);	//reverse order
    	int v = list.get(2);
    	
    	System.out.println();
    	
    	System.out.print("10! = " +ds.factorial(10));
		
    	
    	ds.reverse(m[0], 0, m[0].length-1);
    	System.out.println();
    	*/
		//ds.stringPermutation("blue");
		
		//System.out.print(ds.convert("1234", 4));
		
//		ListNode list = ds.buildList();
//	//	System.out.print(ds.count(list,0));
//		int[]arr =new int[5];
//		arr[0]=5;
//		arr[3]=4;
//		arr[1]=-1;
//		int[] num=new int[2];
//		System.out.println(ds.value(arr,num, 0, Integer.MAX_VALUE,0)[0]);
//		System.out.println(ds.value(arr,num,  0, Integer.MAX_VALUE,0)[1]);
		
		//int n=4;
		//ds.hanoi(n,'A','B','C');
//		boolean flag=false;
//		if(ds.palindrome("racecar", 0,6,flag)==true)System.out.print("false");
//		else System.out.print("true");
		
		
		int[][] maze = new int[][] {	{1,1,1,1,1,1,1,1,1,1},
										{1,0,0,1,0,1,0,0,0,1},
										{1,0,0,1,0,0,0,0,0,1},
										{1,0,0,0,0,0,0,0,0,1},
										{1,0,0,1,1,1,1,1,1,0},
										{1,0,1,1,1,1,1,1,1,1},
										{1,0,0,1,1,0,0,0,0,1},
										{1,1,0,0,0,0,1,1,1,1},
										{1,1,1,1,1,0,0,0,0,1},
										{1,1,1,1,1,0,1,1,1,1}};
		System.out.println("the steps are "+ ds.findShortestPath(maze, 8, 8));

	}
	
	int findShortestPath(int[][] maze,int x, int y) {
		int result = 0;
		LinkedList<int[]> list = new LinkedList<>();
		int[] start = new int[]{x,y};
		list.add(start);
		maze[x][y]=2;
		
		int m=maze.length;
		int n=maze[0].length;
		
		while(!list.isEmpty()) {
			int num = list.size();
			for(int i=0;i<num;i++) {
				int[] p = list.removeFirst();
				
				if((maze[p[0]][p[1]]==0 || maze[p[0]][p[1]]==2) && (p[0]==0 || p[0]==m-1 || p[1]==0 || p[1]==n-1))
					return result;
				;
				int j=p[0];
				int k=p[1];
				if(j-1>=0 && maze[j-1][k]==0) {
					int[] upper = new int[] {j-1,k};
					list.add(upper);
					maze[j-1][k]=2;
				}
				if(j+1<m && maze[j+1][k]==0) {
					int[] down = new int[] {j+1,k};
					list.add(down);
					maze[j+1][k]=2;
				}
				if(k+1<n && maze[j][k+1]==0) {
					int[] right = new int[] {j,k+1};
					list.add(right);
					maze[j][k+1]=2;
				}
				if(k-1<n && maze[j][k-1]==0) {
					int[] left = new int[] {j,k-1};
					list.add(left);
					maze[j][k-1]=2;
				}
			}
			result++;
		}
		return -1;
	}
	
	void hanoi(int n, char A, char B, char C) {
		if(n<=1) {
			System.out.println("move " + n + " from " + A + " to " + B);
			return;
		}
		
		hanoi(n-1, A, C, B);
		System.out.println("move " + n + " from " + A + " to " + B);
		hanoi(n-1, C, B, A);
	}
	
	
	ListNode buildList() {
		ListNode head = new ListNode(5);
		ListNode t = head;
		for(int i=0;i<5;i++) {
			t.next=new ListNode(i+4);
			t=t.next;
		}
		return head;
	}
	boolean palindrome(String arr, int f, int e, boolean flag) {
		if(f>e)return flag;
		if(arr.charAt(f)!=arr.charAt(e))flag=true;
		return palindrome(arr, f+1, e-1, flag);
	}
		int[] value(int[] arr,int[] num, int max, int min, int n) {
			if(n>arr.length-1)return num;
			if(arr[n]>max)num[0] =arr[n];
			else if(arr[n]<min)num[1]=arr[n];
		return value(arr,num, num[0], num[1], n+1);
		}
	int count(ListNode list, int n) {
		ListNode l =list.next;
		if(l==null)return n;
		return count(l,n+1);
	}
	
	void reverse(int[] arr, int f, int l){
		if(f>l)return;
		int save=arr[f];
		arr[f]=arr[l];
		arr[l]=save;
		reverse(arr, f+1, l-1);
	}
	
	
	
	int factorial(int n){
		if(n==0)return 1;
		return n*factorial(n-1);
	}
	int convert(String s, int n) {
		if(n<1)return 0 ;
		char c=s.charAt(n-1);
		return c-'0'+convert(s,n-1)*10;
	}
	
	int fibonacci(int n){
		if(n<2)return n;
		return fibonacci(n-2)+fibonacci(n-1);
	}

	void stringPermutation(String str){
		spHelper(str,"");
	}
	void spHelper(String str, String pre){
		if(str.length()<1)	
			System.out.println(pre);

		for(int i=0;i<str.length();i++){
			char c = str.charAt(i);
			String newpre = pre+ c;
			spHelper(str.substring(0, i) + str.substring(i+1),newpre);
		}
	}

	
	
	void test(){
    	ListNode node1 = new ListNode(5);
    	ListNode node2 = new ListNode(6);
    	node1.next=node2;
    	ListNode node3 = new ListNode(8);
    	node2.next=node3;
    	
    	
    	ListNode node = node1;
    	while(node!=null){
    		System.out.print(node.val + " ");
    		node = node.next;
    	}
    	System.out.println();
    	ListNode t = node1;
    	while(t.val!=6){
    		t=t.next;
    	}
    	ListNode n = new ListNode(7);
    	n.next = t.next;
    	t.next = n;
 
    	node = node1;
    	while(node!=null){
    		System.out.print(node.val + " ");
    		node = node.next;
    	}
	}



}
