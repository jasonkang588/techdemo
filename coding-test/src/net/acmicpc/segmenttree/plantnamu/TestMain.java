package net.acmicpc.segmenttree.plantnamu;

import java.util.Arrays;

public class TestMain {
	public static void main(String[] args) {
		int arrSize = 4000;
		Integer[] arr = new Integer[arrSize];
		for(int i=0; i<arr.length; i++) {
			arr[i] = i+1;
		}
		LongSegmentTree tree = new LongSegmentTree(arr);
		tree.init(0, arr.length-1, 0);
		
		Long sum = tree.getSum(0, arr.length-1, 0, 2, 3);
		
		System.out.println("expected val=3+4=7, val=" + sum);
		
		Long sum2 = tree.getSum(0, arr.length-1, 0, 1, 2);
		
		System.out.println("expected val=2+3=5, val=" + sum2);
		
		System.out.println("update 2 -> 10 then [1, 10, 3, 4]");
		tree.setValue(1, 10L);
		
		sum = tree.getSum(0, arr.length-1, 0, 2, 3);  
		
		System.out.println("expected val=3+4=7, val=" + sum);
		
		sum2 = tree.getSum(0, arr.length-1, 0, 1, 2);
		
		System.out.println("expected val=10+3=13, val=" + sum2);
		
	}
}
