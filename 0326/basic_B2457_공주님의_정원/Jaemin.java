import java.io.*;
import java.util.*;

public class Jaemin {
	static class Node implements Comparable<Node>{
		int start, end;
		Node(int start, int end){
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Node o) {
			if(this.start != o.start) return Integer.compare(this.start, o.start);
			return Integer.compare(o.end, this.end);
		}
	}
	
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());

		Node[] nodes = new Node[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sm = Integer.parseInt(st.nextToken());
			int sd = Integer.parseInt(st.nextToken());
			int em = Integer.parseInt(st.nextToken());
			int ed = Integer.parseInt(st.nextToken());
			
			int startday = sm*100 + sd;
			int endday = em*100 + ed;
			nodes[i] = new Node(startday, endday);			
		}
		
		Arrays.sort(nodes);
		
		int cnt = 0;
		int tail = 301;
		int temptail = tail;
		int idx = 0;
		while(tail < 1201) {
			boolean found = false;
			while(idx < N && nodes[idx].start <= tail) {
				if(nodes[idx].end > temptail) {
					temptail = nodes[idx].end;
					found = true;
				}
				idx++;
			}
			
			if(found) {
				tail = temptail;
				cnt++;
			} else {
				break;
			}
		}
		
		if(tail <= 1130) {
			cnt = 0;
		}
		
		bw.write(cnt + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
