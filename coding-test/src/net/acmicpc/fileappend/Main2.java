package net.acmicpc.fileappend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/11066
public class Main2 {
	private ArrayList<Integer[]> chapterListArr = new ArrayList<>();
	private ArrayList<Integer> resultList = new ArrayList<>();
	private String test = "2\r\n" + 
			"4\r\n" + 
			"40 30 30 50\r\n" + 
			"500\r\n" + 
			"1 21 3 4 5 35 5 4 3 5 98 21 14 17 32 1 21 3 4 5 35 5 4 3 5 98 21 14 17 32 1 21 3 4 5 35 5 4 3 5 98 21 14 17 32 1 21 3 4 5 35 5 4 3 5 98 21 14 17 32 1 21 3 4 5 35 5 4 3 5 98 21 14 17 32 1 21 3 4 5 35 5 4 3 5 98 21 14 17 32 1 21 3 4 5 35 5 4 3 5 98 21 14 17 32 1 21 3 4 5 35 5 4 3 5 98 21 14 17 32 1 21 3 4 5 35 5 4 3 5 98 21 14 17 32 1 21 3 4 5 35 5 4 3 5 98 21 14 17 32 1 21 3 4 5 35 5 4 3 5 98 21 14 17 32 1 21 3 4 5 35 5 4 3 5 98 21 14 17 32";
	
	public void init() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			br.readLine();
			boolean isOdd = true;
			String line = null;
			int len = 0;
			while((line = br.readLine()) != null) {
				if(isOdd) {
					len = Integer.parseInt(line);
					isOdd = false;
				} else {
					chapterListArr.add(this.stringToArray(line, " ", len));
					isOdd = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 		
	}
	
	public void testModeInit() {
		try (BufferedReader br = new BufferedReader(new StringReader(this.test))) {
			br.readLine();
			boolean isOdd = true;
			String line = null;
			int len = 0;
			while((line = br.readLine()) != null) {
				if(isOdd) {
					len = Integer.parseInt(line);
					isOdd = false;
				} else {
					chapterListArr.add(this.stringToArray(line, " ", len));
					isOdd = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 		
	}
	
	public ArrayList<Integer> stringToArrayList(String str, String delim) {
		ArrayList<Integer> list = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(str, delim);
		while(tokenizer.hasMoreTokens()) {
			list.add(Integer.parseInt(tokenizer.nextToken()));
		}
		return list;
	}
	
	public Integer[] stringToArray(String str, String delim, int len) {
		Integer[] arr = new Integer[len];
		StringTokenizer tokenizer = new StringTokenizer(str, delim);
		int idx = 0;
		while(tokenizer.hasMoreTokens()) {
			arr[idx]= Integer.parseInt(tokenizer.nextToken());
			idx++;
		}
		return arr;
	}
	
	private class MergeResult {
		public int sum = 0;
		public int currCost = 0;		
	}
	
	private MergeResult[][] cache; 
	public MergeResult getMinMergeResult(Integer[] arr, int start, int end) {
		if(cache[start][end] != null) {
			return cache[start][end]; 
		}
		
		if(end - start == 1) {
			MergeResult mr = new MergeResult();
			mr.currCost = arr[start];
			cache[start][end] = mr;
			return mr;
		}
		
		MergeResult mr = new MergeResult();
		mr.sum = Integer.MAX_VALUE;
		for(int i=start+1; i<end; i++) {
			MergeResult mr1 = getMinMergeResult(arr, start, i);
			MergeResult mr2 = getMinMergeResult(arr, i, end);
			MergeResult mr3 = new MergeResult();
			mr3.currCost = mr1.currCost + mr2.currCost;
			mr3.sum = mr1.sum + mr2.sum + mr3.currCost;
			if(mr.sum > mr3.sum) {
				mr = mr3;
			}
		}
		cache[start][end] = mr;
		return mr;
	}
	
	
	public void submit() {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int cnt = 0;
		for(int result : this.resultList) {
			try {
				bw.write(String.valueOf(result));
				if(!(cnt == resultList.size()-1)) {
					bw.newLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				cnt++;
			}
		}
		try {
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		//this.init();
		this.testModeInit();
		
		for(Integer[] arr : this.chapterListArr) {
			this.cache = new MergeResult[501][501];

			MergeResult mr = getMinMergeResult(arr, 0, arr.length);
			this.resultList.add(mr.sum);
		}
	}

	
    public static void main(String[] args) throws Exception {
    	//long start = System.currentTimeMillis();
    	Main2 main = new Main2();
    	main.run();
    	main.submit();
    	//long end = System.currentTimeMillis();
    	//System.out.println();
    	//System.out.println(end - start + "ms elapsed.");
    	//arrayList 639ms
    }
}