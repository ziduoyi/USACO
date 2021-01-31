package com.lc2;

import java.util.Random;

public class QuickSelect {
    public int findKthSmallest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }

        int len = nums.length;

        return quickSelect(nums, k, 0, len - 1);
    }

    private int quickSelect(int[] nums, int k, int start, int end) {

        //Choose a pivot randomly
        Random rand = new Random();
        int index = rand.nextInt(end - start + 1) + start;
        int pivot = nums[index];
        swap(nums, index, end);

        int left = start, right = end;

        while(left < right) {
            if (nums[left++] >= pivot) {
                swap(nums, --left, --right);
            }
        }
        //left is now pointing to the first number that is greater then or equal to the pivot
        swap(nums, left, end);

        //m is the number of numbers that is smaller then pivot
        int m = left - start;

        if (m == k - 1) { //in order to find the kth smallest number, their must be k - 1 number smaller then it 
            return pivot;
        }
        else if (k <= m) { //target is in the left subarray
            return quickSelect(nums, k, start, left - 1);
        }
        else { 
            //target is in the right subarray, but need to update k 
            //Since we have discarded m numbers smaller then it which is in the right subarray
            return quickSelect(nums, k - m, left, end);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

