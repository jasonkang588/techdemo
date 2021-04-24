package net.acmicpc.segmenttree.plantnamu;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SegmentTree {
//	Integer
//	
//	public SegmentTree(E[] array) {
//		this.array = array;
//		
//		int size = (int) Math.ceil(this.log(array.length, 2)) * 2;
//		this.tree =  (E[]) Array.newInstance(array[0].getClass(), size);
//
//	}
//
//	private double log(double x, double base) {
//		return Math.log(x) / Math.log(base);
//	}
//	
//	private E init(int start, int end, int node) {
//		if(start == end) { /* 리프노드이거나 자식노드들이 구간합이 모두구해졌을 경우 */
//			return tree[node] = array[start]; /* 구간합 트리에 넣어준다 */
//		}
//		/* 반씩 나눠서  재귀적으로 자식노드들의 구간합을 구해준다 */
//		int mid = (start+end)/2;
//		return tree[node] = init(start, mid, node*2).add(init(mid+1, end, node*2+1));
//	}
//	
//	public static void main(String[] args) {
//		Integer[] arr = new Integer[] {0,1,2,3,4,5};
//	}
	
	public SegmentTree(Object[] array) {
		if(array[0] instanceof Integer) {
			Integer[] arr = (Integer[]) array;
			System.out.println(Arrays.toString(arr));
		} else if(array[0] instanceof Double) {
			Double[] arr = (Double[]) array;
			System.out.println(Arrays.toString(arr));
		}
	}
	
    
	public static void main(String[] args) {         
		//Integer[] arr = new Integer[] {0,1,2,3,4,5}; 
		Double[] arr = new Double[] {0d,1d,2d,3d,4d,5d};
		SegmentTree tree = new SegmentTree(arr);
	}                                                
	    
	
}
