package com.lc;


public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }
    void insert(TreeNode root,TreeNode node){
    	while(root!=null){
    		if(root.val>=node.val){
    			if(root.left!=null)
    				root=root.left;
    			else{
    				root.left=node;
    				break;
    			}
    		}else{
    			if(root.right!=null)
    				root=root.right;
    			else{
    				root.right=node;
    				break;
    			}
    		}
    	}
    }
}
