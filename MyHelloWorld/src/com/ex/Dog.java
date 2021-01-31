package com.ex;

public class Dog extends Pet {
	
	public boolean eat(int num){
		if(num<50)
			return false;
		if(num>50 && num < 100){
			height += 5;
			weight += 5;
			return true;
		}
		if(num >= 100)
			return false;
		return false;
	} 
	
	public void sleep(int hour){
		if(hour == 10){
			height +=1;
			weight -=1;
		}
	}
}
