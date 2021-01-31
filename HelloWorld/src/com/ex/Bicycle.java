package com.ex;

public class Bicycle {
	public int speed;
	public int gear;	// 1

	public Bicycle(int gear, int speed){
		this.speed = speed;
		this.gear = gear;
	}
	
	public void speedUp(int up){
		speed += up;
	}
	
	public void brake(int down){
		speed -= down;
	}

	public int getGear() {
		return gear;
	}

	public void setGear(int gear) {
		this.gear = gear;
	}
	
	public String toString(){
		return "Bicycel with gear: " + gear + " at speed: " + speed;
	}

}
