import java.util.LinkedList;

public class TreeNode {
	public TreeNode left;
	public TreeNode right;
	public int val;
	public TreeNode(int x){val=x;}
	
	public void preOrder(TreeNode root){
		if(root==null) return;
		
		System.out.println(root.val);
		preOrder(root.left);
		preOrder(root.right);
	}

	public void inOrder(TreeNode root){
		if(root==null) return;
		
		inOrder(root.left);
		System.out.println(root.val);
		inOrder(root.right);
	}
	
	public void postOrder(TreeNode root){
		if(root==null) return;
		
		postOrder(root.left);
		postOrder(root.right);
		System.out.println(root.val);
	}
	
	public void bfs(TreeNode root){
		LinkedList<TreeNode> list = new LinkedList<>();
		list.add(root);
		
		while(!list.isEmpty()){
			int n = list.size();
			for(int i=0;i<n;i++){
				TreeNode node = list.removeFirst();
				if(node.left!=null)
					list.add(node.left);
				if(node.right!=null)
					list.add(node.right);
				System.out.print(node.val + " ");
			}
			System.out.println();
		}
	}
}
