package com.lc;

public class TreeEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		bstFromPreorder(new int[] {1});
		
	}


    static TreeNode bstFromPreorder(int[] data) {
        
        return recursivePre(data,Integer.MIN_VALUE,Integer.MAX_VALUE);
        /*
        TreeNode node = new TreeNode(data[0]);
        for(int i=1;i<data.length;i++){
            insert(node,data[i]);
        }
        return node;
        */
    }
    
    static TreeNode recursivePre(int[] data, int l, int r) {
    	if(pos>=data.length) return null;
    	
    	if(data[pos]<l || data[pos]>r) return null;
    	
    	TreeNode t = new TreeNode(data[pos++]);
    	t.left = recursivePre(data,l,t.val);
    	t.right =recursivePre(data,t.val,r);
    		
    	return t;
    }
    
    static TreeNode preOrder(int[] data, int parentVal){
        int len=data.length;
        if(pos>=len) return null;
        int x=pos;
        TreeNode node = new TreeNode(data[x++]);
        pos=x;
        if(x<len && data[x]<node.val){
            //pos=x;
            node.left=preOrder(data,node.val);
        }
        if(x<len && data[pos]<parentVal){
            //pos=x;
            node.right=preOrder(data,node.val);
        }
        return node;
    }
 
    
    void insert(TreeNode t, int x){
        TreeNode node = new TreeNode(x);
        if(x<t.val){
            if(t.left==null){
                t.left=node;
            }else
                insert(t.left,x);
        }else{
            if(t.right==null){
                t.right=node;
            }else
                insert(t.right,x);
        }
    }	
	
	
//#1028. Recover a Tree From Preorder Traversal	
    static int pos=0;
    static int len=0;
    static TreeNode recoverFromPreorder(String S) {
        len = S.length();
        return preOrder(0,S,0);
    }
    
    static TreeNode preOrder(int depth, String S, int x){
        if(x>=len)
            return null;
        
        int dep = 0;
        while(x<len && S.charAt(x)=='-'){   //get depth
            x++;
            dep++;
        }
        if(dep==depth){
            int y = S.indexOf('-',x);
            if(y<0) y=len;
            int v = Integer.parseInt(S.substring(x,y));
            pos=y;
            TreeNode t = new TreeNode(v);
            t.left=preOrder(depth+1, S, y);
            t.right=preOrder(depth+1,S,pos);
            return t;
        }else
            return null;
    }
}
