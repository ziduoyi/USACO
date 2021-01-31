package com.lc;


public class Node {
	public Node left=null;
	public Node right=null;
	private Node parent=null;
	public Integer data;
	private int lpath=0;
	private int rpath=0;
	
	public Node(int data){
		this.data=data;
		this.left=this.right=null;
	}
	public Node getLeft(){
		return this.left;
	}
	public void setLeft(Node node){
		this.left=node;
	}
	public Node getRight(){
		return this.right;
	}
	public void setRight(Node node){
		this.right=node;
	}
	public int getData(){
		return this.data;
	}
	public void setData(int d){
		this.data=d;
	}
	public int getLpath() {
		return lpath;
	}
	public void setLpath(int lpath) {
		this.lpath = lpath;
	}
	public int getRpath() {
		return rpath;
	}
	public void setRpath(int rpath) {
		this.rpath = rpath;
	}
	public void insert(Node root, Node node){
		Node parent = root;
		int v = node.getData();
		while(true){
			if(parent.getData()>=v){
				if(parent.getLeft()==null){
					parent.setLeft(node);
					return;
				}else
					parent=parent.getLeft();
			}else{
				if(parent.getRight()==null){
					parent.setRight(node);
					return;
				}else
					parent=parent.getRight();
			}
		}
	}

}
