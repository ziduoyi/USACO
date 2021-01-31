package com.lc;

import java.util.*;
public class MyInterfaceEx implements Movable{
	public static int pos = 0;
	
	public static void main(String[] arr) {
		
		MyInterfaceEx ex = new MyInterfaceEx();
		ex.move(ex);
		System.out.println(pos);
		
		Movable.move1mile(ex);
		System.out.println(pos);
	}

	int getPos() {return pos;}
	void setPos(int x) {this.pos = x;}
	
	

}


interface Movable{
	int step = 1;
	
	static void move1mile(MyInterfaceEx ex) {
		ex.setPos(ex.getPos()+100);
		//step += 1;
		//return step;
	}
	default void move(MyInterfaceEx ex) {
		ex.setPos(ex.getPos() + this.step);
	}
}


