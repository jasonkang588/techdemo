package com.kks.practice;

import java.util.Arrays;
import java.util.Collections;

public class QuickSort {
	private Integer[] data = new Integer[] {6, 7, 2, 0, 4, 3, 1, 5, 8, 9};
	private int dataLen = 100;
	
	public void dataSetup() {
		data = new Integer[this.dataLen];
		for(int i=0; i<this.dataLen; i++) {
			data[i] = (int) (Math.random() * 1000);
		}
		
		Collections.shuffle(Arrays.asList(data));
	}
	
	public void printData() {
		System.out.println(Arrays.toString(data));
	}
	
	public void quickSort(Integer[] array, int start, int end) {
		if(end - start < 1) {
			return;
		}
		
		int pivot = array[start];
		int j = end;
		for(int i=start+1; i<=end; i++) {
			for(; j>=i; j--) {
				if(array[j] >= pivot) {
					continue;
				}
				
				if(array[i] <= pivot) { 
					break;
				}
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				j--;
				break;
			}
			
			if(i >= j) {
				int temp = array[j];
				array[j] = array[start];
				array[start] = temp;
				quickSort(array, start, j-1);
				quickSort(array, j+1, end);
				break;
			}
		}
	}
	
	public void quickSort() {
		this.quickSort(this.data, 0, this.data.length-1);
	}
	
	public static void main(String[] args) {
		QuickSort q = new QuickSort();
		q.dataSetup();
		q.printData();
		q.quickSort();
		q.printData();
	}
}
