package net.acmicpc.fileappend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/11066
public class Main {
	private ArrayList<ArrayList<Integer>> chapterListArr = new ArrayList<>();
	private ArrayList<Integer> resultList = new ArrayList<>();
	private String test = "2\r\n" + 
			"4\r\n" + 
			"40 30 30 50\r\n" + 
			"15\r\n" + 
			"1 21 3 4 5 35 5 4 3 5 98 21 14 17 32";
	
	public void init() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			br.readLine();
			boolean isOdd = true;
			String line = null;
			while((line = br.readLine()) != null) {
				if(isOdd) {
					isOdd = false;
				} else {
					chapterListArr.add(this.stringToArrayList(line, " "));
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
			while((line = br.readLine()) != null) {
				if(isOdd) {
					isOdd = false;
				} else {
					chapterListArr.add(this.stringToArrayList(line, " "));
					isOdd = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 		
	}
	
	public void addToSortedList(LinkedList<Integer> list, int item) {
		boolean hasAdded = false;
		int index = 0;
		for(int n : list) {
			if(item <= n) {
				list.add(index, item);
				hasAdded = true;
				break;
			}
			index++;
		}
		
		if(!hasAdded) {
			list.addLast(item);
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
	
	private class MergeResult implements Comparable<MergeResult>{
		public int sum = 0;
		public int currCost = 0;
		@Override
		public int compareTo(MergeResult o) {
			return this.sum - o.sum;
		}
	}
	
	private MergeResult[][] cache; 
	public MergeResult getMinMergeResult(List<Integer> list, int start, int end) {
		if(cache[start][end] != null) {
			return cache[start][end]; 
		}
		if(list.size() > 1) {
			MergeResult mr = new MergeResult();
			mr.sum = Integer.MAX_VALUE;
			for(int i=1; i<list.size(); i++) {
				MergeResult mr1 = getMinMergeResult(list.subList(0, i),  start + 0, start + i);
				MergeResult mr2 = getMinMergeResult(list.subList(i, list.size()), start + i, start + list.size());
				MergeResult mr3 = new MergeResult();
				mr3.currCost = mr1.currCost + mr2.currCost;
				mr3.sum = mr1.sum + mr2.sum + mr3.currCost;
				if(mr.sum > mr3.sum) {
					mr = mr3;
				}
			}
			cache[start][end] = mr;
			return mr;
		} else if(list.size() == 1) {
			MergeResult mr3 = new MergeResult();
			mr3.currCost = list.get(0);
			cache[start][end] = mr3;
			return mr3;
		}
		
		return null;
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
		
		for(ArrayList<Integer> chapterList : this.chapterListArr) {
			this.cache = new MergeResult[500][500];
			for(int i=0; i<5; i++) {
				chapterList.addAll(chapterList);
			}
			MergeResult mr = getMinMergeResult(chapterList, 0, chapterList.size());
			this.resultList.add(mr.sum);
		}
		
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