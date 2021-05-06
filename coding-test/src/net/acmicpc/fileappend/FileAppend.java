package net.acmicpc.fileappend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/11066
public class FileAppend {
	private ArrayList<LinkedList<Integer>> chapterListArr = new ArrayList<>();
	private ArrayList<Integer> resultList = new ArrayList<>();
	
	public void init() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			br.readLine();
			boolean isOdd = true;
			String line = null;
			while((line = br.readLine()) != null) {
				if(isOdd) {
					isOdd = false;
				} else {
					chapterListArr.add(this.stringToLinkedList(line, " "));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 		
	}
	
	public void addToSortedList(LinkedList<Integer> list, int item) {
		int index = 0;
		for(int n : list) {
			if(item <= n) {
				list.add(index, item);
				break;
			}
			index++;
		}
	}
	
	public LinkedList<Integer> stringToLinkedList(String str, String delim) {
		LinkedList<Integer> list = new LinkedList<>();
		StringTokenizer tokenizer = new StringTokenizer(str, delim);
		while(tokenizer.hasMoreTokens()) {
			list.add(Integer.parseInt(tokenizer.nextToken()));
		}
		return list;
	}
	
	public void run() {
		this.init();
		
		for(LinkedList<Integer> chapterList : this.chapterListArr) {
			Collections.sort(chapterList);
			int totalCost = 0;
			while(chapterList.size() > 1) {
				int n1 = chapterList.removeFirst();
				int n2 = chapterList.removeFirst();
				int sum = n1 + n2;
				totalCost += sum;
				addToSortedList(chapterList, sum);
			}
			this.resultList.add(totalCost);
		}
		
		this.submit();
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
}
