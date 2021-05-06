package net.acmicpc.cutnamu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        //inout
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringBuilder input = new StringBuilder();
    	for(String line; (line = br.readLine()) != null; input.append(line+System.lineSeparator())) {
    	}
    	br.close();
    	
    	bw.write(String.valueOf(getCuttersHeight(input.toString())));
    	bw.flush();
    }
    
	public static int getCuttersHeight(String input) throws Exception {
		int treeCount = 0;
		int myTreeLen = 0;
		int[] treeLenArr = null;
		{
			String[] splitArr = input.split(System.lineSeparator());
			String line1 = splitArr[0];
			String line2 = splitArr[1];
			
			splitArr = line1.split(" ");
			treeCount = Integer.parseInt(splitArr[0]);
			myTreeLen = Integer.parseInt(splitArr[1]);
			
//			for(int i=0; i<100000; i++) {
//				int num = (int)(Math.random() * 100000);
//				System.out.println(i+"::"+num);
//				line2 += num + " ";	
//			}
			
//			for(int i=0; i<1000; i++) {
//				int num = (int)(Math.random() * 146);
//				System.out.println(i+"::"+num);
//				line2 += " " + num;	
//			}
			
			String[] line2Split = line2.split(" ");

			{
				//long start = System.currentTimeMillis();
				int[] arr2 = new int[line2Split.length];
				int idx = 0;
				for(String s : line2Split) {
					arr2[idx] = Integer.parseInt(s);
					idx++;
				}				
				//long end = System.currentTimeMillis();
				//System.out.println(end - start + "ms");
				treeLenArr = arr2;
			}
			
//			{
//				long start = System.currentTimeMillis();
//				treeLenArr = Arrays.stream(line2Split).parallel()
//						.mapToInt(Integer::parseInt).toArray();
//				long end = System.currentTimeMillis();
//				System.out.println(end - start + "ms");	
//			}
//			
//			
//			{
//				long start = System.currentTimeMillis();
//				treeLenArr = Arrays.stream(line2Split)
//						//.parallel()
//						.mapToInt(Integer::parseInt).toArray();
//				long end = System.currentTimeMillis();
//				System.out.println(end - start + "ms");
//			}
			

		}
		
		//System.out.println("treeCount="+treeCount);
		//System.out.println("myTreeLen="+myTreeLen);
		//System.out.println("treeLenArr=" + Arrays.toString(treeLenArr));
		
//		{
//			long start = System.currentTimeMillis();
//			Arrays.sort(treeLenArr);	
//			long end = System.currentTimeMillis();
//			System.out.println(end - start + "ms sort");
//		}
//		
//		System.out.println("sortedTreeLenArr=" + Arrays.toString(treeLenArr));
		
		{
			//long start = System.currentTimeMillis();
			Arrays.parallelSort(treeLenArr);	
			//long end = System.currentTimeMillis();
			//System.out.println(end - start + "ms p-sort");
		}
		//System.out.println("sortedTreeLenArr=" + Arrays.toString(treeLenArr));

		int cutsum = 0;
		int index = treeLenArr.length-1;
		int cnt = 0;
		int cutlen = 0;
		for(; index>=0; index--) {
//			int num1 = treeLenArr[index];
//			int num2 = treeLenArr[index-1];

			if(myTreeLen <= cutsum) {
				break;
			}
			
			cnt++;
			//calc cutlen
			int diff = myTreeLen - cutsum;
			int cutNeed = (int) Math.ceil((double)diff/cnt);
			int maxcutlen = treeLenArr[index] - (index == 0 ? 0 : treeLenArr[index-1]);
			
			if(cutNeed > maxcutlen) {
				//문제 가정상 일어날수 없으므로 성능을 위해 주석처리함.
//				if(index == 0) {
//					throw new Exception("Not enough tree total length");
//				}
				cutNeed = maxcutlen;
			}
			
			cutsum += cutNeed*cnt;
			cutlen += cutNeed;
		}
		//System.out.println("cutsum=" + cutsum);
		
//		int testsum = 0;
//		for(int i=1; i<treeLenArr.length; i++) {
//			testsum += treeLenArr[i];
//		}
//		System.out.println("testsum=" + testsum);
		
		return treeLenArr[treeLenArr.length-1] - cutlen;
	}
}