package net.acmicpc.segmenttree.plantnamu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

//https://www.acmicpc.net/problem/1280
public class Main {
	private String testData = "5\r\n" + 
			"3\r\n" + 
			"4\r\n" + 
			"5\r\n" + 
			"6\r\n" + 
			"7";

	private long num = 1000000007;
	
	private int[] treeCoordArr;
	
	public void init() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			treeCoordArr = new int[Integer.parseInt(br.readLine())];
			for(int i=0; i<treeCoordArr.length; i++) {
				treeCoordArr[i] = Integer.parseInt(br.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 		
	}
	
	public void testModeInit() {
		try (BufferedReader br = new BufferedReader(new StringReader(this.testData))) {
			treeCoordArr = new int[Integer.parseInt(br.readLine())];
			for(int i=0; i<treeCoordArr.length; i++) {
				treeCoordArr[i] = Integer.parseInt(br.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 		
	}
	
	public long getPlantCost(int[] treeCoordArr, int treeIdx) {
		int treeCoordX = treeCoordArr[treeIdx];
		long cost = 0;
		for(int i=0; i<treeIdx; i++) {
			cost += Math.abs(treeCoordX - treeCoordArr[i]);
		}
		return cost;
	}
	
	public long getPlantCostMultiplyEach(int[] treeCoordArr) {
		long result = 1;
		for(int i=1; i<treeCoordArr.length; i++) {
			result *= this.getPlantCost(treeCoordArr, i);
		}
		return result;
	}
	
	public void run() {
		this.init();
		//this.testModeInit();
		long result = this.getPlantCostMultiplyEach(this.treeCoordArr);		
		System.out.println(String.valueOf(result % num));
	}

	
  public static void main(String[] args) throws Exception {
	  Main main = new Main();
	  main.run();
  }
}