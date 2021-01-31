package com.ex;

public class MountainBike extends Bicycle {

	public int seatHeight;
	

	public MountainBike(int gear, int speed) {
		super(gear, speed);
		// TODO Auto-generated constructor stub
	}
	
	public int seatHeight(int value){
		seatHeight = value;
		return value;
	}
	
	public String toString(){
		return "MountainBike with gear: " + gear + " at speed: " + speed + " at setheight: " +seatHeight;
	}
}
