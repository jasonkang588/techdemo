package net.acmicpc.cutnamu;

import java.util.*;
import java.io.*;

public class RankOne {
	static BufferedReader in;
	static StringTokenizer st;
	static int n, m, a[], max;

	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		
		init();
		solve();
		
		in.close();

	}
	static void init() throws IOException {
		st=new StringTokenizer(in.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		a = new int[n];
		st=new StringTokenizer(in.readLine());
		for(int i=0; i<n; i++) {
			a[i]=Integer.parseInt(st.nextToken());
			max=Math.max(max, a[i]);
		}
		//System.out.println(Arrays.toString(a));
	}
	static void solve() {
		int min=0;
		int ans=0;
		while(max>=min) {
			int mid=(min+max)/2;
			if (check(mid)) {
				ans=mid;
				min=mid+1;
			} else {
				max=mid-1;
			}
		}
		System.out.println(ans);
		
	}
	static boolean check(long mid) {
		long total=0l;
		for (int i=0; i<n; i++) {
			if (a[i]-mid>0) {
				total+=a[i]-mid;
			} 			
		}
		//System.out.println(mid + " "+max);
		return total>=m;
	}

}
