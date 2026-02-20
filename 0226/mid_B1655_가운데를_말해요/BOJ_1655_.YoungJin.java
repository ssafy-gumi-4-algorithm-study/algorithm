import java.io.*;
import java.util.*;

public class Main {
	
	public static class SegmentTree {
		private int n;
		private int[] tree;
		
		public SegmentTree(int n) {
			this.n = n;
			this.tree = new int[n * 4];
		}
		
		
		public int wos(int k) {
			return wos(1, 0, n - 1, k);
		}
		
		private int wos(int node, int start, int end, int k) {
			if(start == end) {
				return start;
			}
			int leftCount = tree[node * 2];
			int mid = (start + end) / 2;
			if(leftCount >= k) {
				return wos(node * 2, start, mid, k);
			} else {
				return wos(node * 2 + 1, mid + 1, end, k - leftCount);
			}
		}
		
		public int update(int index, int value) {
			return update(1, 0, n - 1, index, value);
		}
		
		private int update(int node, int start, int end, int index, int value) {
			if(index < start || end < index) {
				return tree[node];
			}
			if(start == end) {
				return tree[node] += value;
			}
			int mid = (start + end) / 2;
			int leftUpdate = update(node * 2, start, mid, index, value);
			int rightUpdate = update(node * 2 + 1, mid + 1, end, index, value);
			return tree[node] = leftUpdate + rightUpdate;
		}
	}
	
	public static int lowerBound(List<Integer> list, int key) {
		int left = 0;
		int right = list.size();
		while(left < right) {
			int mid = (left + right) / 2;
			if(list.get(mid) >= key) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}
	
	private static final int MAX_VALUE = 100001;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		int[] arr = new int[n];
		List<Integer> list = new ArrayList<>();
		List<Integer> dist = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			list.add(arr[i]);
		}
		Collections.sort(list);
		for(int v : list) {
			if(dist.isEmpty() || dist.get(dist.size() - 1) != v) {
				dist.add(v);
			}
		}
		SegmentTree seg = new SegmentTree(MAX_VALUE);
		for(int i = 0; i < n; i++) {
			int target = lowerBound(dist, arr[i]);
			seg.update(target, 1);
			int midIndex = seg.wos((i + 2) / 2);
			int mid = dist.get(midIndex);
			bw.write(mid + "\n");
		}
		bw.flush();
	}
}