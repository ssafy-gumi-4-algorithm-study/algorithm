import java.io.*;
import java.util.*;

public class Main {
	
	public static class Flower implements Comparable<Flower>{
		public int startTime;
		public int endTime;

		public Flower(int startTime, int endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
		}

		@Override
		public int compareTo(Flower o) {
			if(this.startTime == o.startTime) {
				return o.endTime - this.endTime;
			}
			return this.startTime - o.startTime;
		}

		@Override
		public String toString() {
			return "Flower [startTime=" + startTime + ", endTime=" + endTime + "]";
		}
	}

	public static void main(String args[]) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		
		int[] cal = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		for(int i = 1; i < cal.length; i++) {
			cal[i] += cal[i - 1];
		}
		
		int n = sc.nextInt();
		Flower[] arr = new Flower[n];
		for(int i = 0; i < n; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			int d = sc.nextInt();
			
			arr[i] = new Flower(cal[a - 1] + b, cal[c - 1] + d);
		}
		
		Arrays.sort(arr);
		
		int count = 0;
		int current = 60;
		int maxEndTime = 0;
		int idx = 0;
		
		while(current <= 334) {
			boolean found = false;
			
			while(idx < n && arr[idx].startTime <= current) {
				maxEndTime = Math.max(maxEndTime, arr[idx].endTime);
				idx++;
				found = true;
			}
			
			if(!found) {
				break;
			}
			
			current = maxEndTime;
			count++;
		}
		
		if(current <= 334) {
			System.out.println(0);
		} else {
			System.out.println(count);
		}
	}
}