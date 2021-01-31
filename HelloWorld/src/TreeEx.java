import java.util.*;
public class TreeEx {
	
	
	public class TreeNode{
		TreeNode left;
		TreeNode right;
		int val=0;
		public TreeNode(int v) {val=v;}
	}
	
	public static void main(String[] args) {
		TreeEx ex = new TreeEx();
		TreeNode root = ex.buildTree();
		preOrder( root);
		//System.out.println();
		//inOrder(root);
		//System.out.println();
		//summary(root);
		//BFS(root);
		
		
	}

	static int summary(TreeNode root) {
		if(root==null) {
			return 0;
		}
		int ls=summary(root.left);

		int rs=summary(root.right);
		if(ls+rs==root.val) {
			System.out.print(ls+rs+" ");
		}
		//System.out.print(ls+rs+root.val+" ");
		return ls+rs+root.val;

	}
	static void preOrder(TreeNode root) {
		if(root==null) {
			return;
		}
		System.out.print(root.val);
		System.out.print(" ");
		preOrder(root.left);
		preOrder(root.right);

	}
	static void inOrder(TreeNode root) {
		if(root==null) {
			return;
		}
		inOrder(root.left);
		System.out.print(root.val);
		System.out.print(" ");
		inOrder(root.right);

	}
	static void postOrder(TreeNode root) {
		if(root==null) {
			return;
		}
		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.val);
		System.out.print(" ");

	}
	static void BFS(TreeNode root) {
		if(root==null) {
			return;
		}
		LinkedList<TreeNode> list =new LinkedList<>();
		list.add(root);
		while(list.size()!=0) {
			int n=list.size();
			for(int i=0; i<n; i++) {
				TreeNode node=list.removeFirst();
				if(node.left!=null) {
					list.add(node.left);
				}
				if(node.right!=null) {
					list.add(node.right);
				}
				System.out.print(node.val);
				System.out.print(" ");
			}
			System.out.println();
		}	
		
	}
	
	
	public TreeNode buildTree() {
		TreeNode root = new TreeNode(20);
		root.left = new TreeNode(10);
		root.right = new TreeNode(30);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.left.left.left = new TreeNode(1);
		root.left.left.right = new TreeNode(2);
		root.right.right = new TreeNode(31);
		return root;
	}
	
	
}
