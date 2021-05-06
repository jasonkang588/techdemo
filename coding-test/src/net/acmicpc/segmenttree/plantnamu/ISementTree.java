package net.acmicpc.segmenttree.plantnamu;

public interface ISementTree<E> {
	
	/**
	 * 재귀함수
	 * 리프노드까지 타고내려간 뒤 상위노드끼리의 합을 구해서 트리를 구성함
	 * @param start 합계 시작인덱스
	 * @param end 합계 끝인덱스
	 * @param node 합계값이 저장될 노드 1~n
	 * @return
	 */
	public E init(int start, int end, int node);
	
	/**
	 * 재귀함수
	 * 시작인덱스와 끝인덱스 구간이 완전히 left right 사이에 포함되는경우의 노드값들의 합계를 구함
	 * @param start 시작인덱스
	 * @param end 끝인덱스
	 * @param node 노드 1~n
	 * @param left 구간합 시작 fixed
	 * @param right 구간합 끝 fixed
	 * @return
	 */
	public E getSum(int start, int end, int node, int left, int right);
	
	/**
	 * 재귀함수
	 * 인덱스가 노드합계에 포함된 요소 인덱스인경우 노드값 update
	 * @param start 시작인덱스
	 * @param end 끝인덱스
	 * @param node 노드 1~n
	 * @param index 수정된배열 인덱스
	 * @param diff 수정된 값
	 */
	public void update(int start, int end, int node, int index, E diff);
	
}
