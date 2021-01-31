package com.ex;



public class Ex {

	
public boolean find132pattern(int[] nums) {
		for(int i = 0; i< nums.length-2;i++) {
			if(nums[i]<nums[i+1] && nums[i]<nums[i+2]) {
				for(int x =i+1; x<nums.length-1; i++) {
					for(int j=x+1; j<nums.length-1; i++) {
						if(nums[x]>nums[i] && nums[x]>nums[j] && nums[j]> nums[i]) {
							return true;
						}
						else {
							continue;
						}
					}		
				}			
			}
			else {
				continue;
			}
		}
		System.out.println("lol");
		return false;
	}
}
