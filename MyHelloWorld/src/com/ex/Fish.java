package com.ex;

public class Fish extends Pet {
	public boolean eat(int num){
		if(num<5)
			return false;
		if(num>5 && num < 10){
			height += 1;
			weight += 1;
			return true;
		}
		if(num >= 10)
			return false;
		return false;
	} 
	
	public void sleep(int hour){
		if(hour == 5){
			height +=1;
			weight -=1;
		}
	}
}
