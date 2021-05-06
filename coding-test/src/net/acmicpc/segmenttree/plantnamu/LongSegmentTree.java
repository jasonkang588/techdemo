package net.acmicpc.segmenttree.plantnamu;

import java.util.Arrays;

public class LongSegmentTree implements ISementTree<Long>{
	private Long[] array;
	private Long[] tree;
	
	public LongSegmentTree(Long[] array) {
		this.array = array;
		this.tree = new Long[this.array.length*4];
	}
	
	public LongSegmentTree(Integer[] array) {
		this.convertToLongArray(array);
		this.tree = new Long[this.array.length*4];
	}
	
	private void convertToLongArray(Integer[] array) {
		this.array = new Long[array.length];
		for(int i=0; i<array.length; i++) {
			this.array[i] = array[i].longValue();
		}
	}
	
	@Override
	public Long init(int start, int end, int node) {
		if(start == end) {
			return tree[node] = this.array[start].longValue();
		}
		
		int mid = (start + end) / 2;
		return tree[node] = this.init(start, mid, node*2+1) + this.init(mid+1, end, node*2+2);
	}

	@Override
	public Long getSum(int start, int end, int node, int left, int right) {
		if(right < start || left > end) {
			return 0L;
		}
		
		if(start >= left && end <= right) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		return this.getSum(start, mid, node*2+1, left, right) + this.getSum(mid+1, end, node*2+2, left, right);
	}

	@Override
	public void update(int start, int end, int node, int index, Long diff) {
		if(index < start || index > end) {
			return;
		}
		
		tree[node] = tree[node] + diff;
		
		if(start == end) {
			return;
		}
		
		int mid = (start + end) / 2;
		update(start, mid, node*2+1, index, diff);
		update(mid+1, end, node*2+2, index, diff);
	}
	
	public void setValue(int index, Long value) {
		Long oldVal = this.array[index];
		Long diff = value - oldVal;
		this.array[index] = value;
		System.out.println(Arrays.toString(this.array));
		this.update(0, this.array.length-1, 0, index, diff);
		System.out.println(Arrays.toString(this.tree));
	}

}
