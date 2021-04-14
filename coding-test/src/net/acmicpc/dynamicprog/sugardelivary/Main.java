package net.acmicpc.dynamicprog.sugardelivary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;

//https://www.acmicpc.net/problem/2839
public class Main {
	private int sugarWeight;
	private int bag3kg = 3;
	private int bag5kg = 5;
	private String test = "9";
	private int solution = -1;
	
	public void init() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			sugarWeight = Integer.parseInt(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		} 		
	}
	
	public void testModeInit() {
		try (BufferedReader br = new BufferedReader(new StringReader(this.test))) {
			sugarWeight = Integer.parseInt(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		} 		
	}
	
	public void submit() {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		try {
			bw.write(String.valueOf(solution));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void solve() {
		int bag5kgMax = sugarWeight / bag5kg;
		for(int i=bag5kgMax; i >= 0; i--) {
			int restSugar = sugarWeight - (i * bag5kg);
			if(restSugar % bag3kg != 0) {
				continue;				
			}
			int bag3kgCount = restSugar / bag3kg;
			this.solution = i + bag3kgCount;
			return;
		}
	}
	
	public void run() {
		this.init();
		//this.testModeInit();
		this.solve();
		this.submit();
	}

	
  public static void main(String[] args) throws Exception {
  	//long start = System.currentTimeMillis();
  	Main main = new Main();
  	main.run();
  	//long end = System.currentTimeMillis();
  	//System.out.println(end - start + "ms elapsed.");
  }
}