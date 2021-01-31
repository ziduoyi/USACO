import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DataStructure ds = new DataStructure();
		
		TreeNode root =new TreeNode(5);
		root.left = new TreeNode(3);
		root.left.left=new TreeNode(1);
		root.left.right = new TreeNode(4);
		root.right=new TreeNode(8);
		root.right.left=new TreeNode(6);
		root.right.right = new TreeNode(9);
		
		//root.preOrder(root);
		//root.inOrder(root);
		root.bfs(root);
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
		//ds.stringPermutation("blue","");
//		ds.stringPermutation("blue");
//		System.out.println("-----------");
//		ds.stringPermutationNR("blue");
		
		Stack<String> stack = new Stack<>();
		stack.push("Blue");
		String v = stack.pop();
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
	
	int fibonacci(int n){
		if(n<2)return n;
		return fibonacci(n-2)+fibonacci(n-1);
	}

///////// string permutation -- recursive	
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
// non-recursive
	void stringPermutationNR(String str){
		LinkedList<String[]> list = new LinkedList<>();
		String[] sa = new String[2];
		sa[0]=str;
		sa[1]="";
		list.add(sa);
		
		while(!list.isEmpty()){
			String[] arr = list.removeFirst();
			if(arr[0].length()<1){
				System.out.println(arr[1]);
				continue;
			}
			for(int i=0;i<arr[0].length();i++){
				char c= arr[0].charAt(i);
				String[] newarr = new String[2];
				newarr[1]=arr[1] + c;
				newarr[0]=arr[0].substring(0, i) + arr[0].substring(i+1);
				list.add(newarr);
			}
		}
	}
	
	
	
/////////////////////	
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

	public class ListNode{
		public ListNode next;
		public Integer val;
		ListNode(Integer x){
			val = x;
		}
	}

}
