import java.io.*;
import java.util.*;

class Node{
	int r, c;
	Node(int r, int c){
		this.r = r;
		this.c = c;
	}
}

public class Jaemin {
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static final StringBuilder sb = new StringBuilder();
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};
	
	static int N, L, R, map[][], answer;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int days = 0;
		
		boolean isMoved;
		do {
			isMoved = false;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visited[i][j])continue;
					if(tryMove(i, j)) isMoved=true;
				}
			}
			if(isMoved)days++;
		}while(isMoved);
		
		sb.append(days).append("\n");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
		
	static boolean tryMove(int r, int c) {
		int sum = 0;
		
		Deque<Node> dq = new ArrayDeque<>();
		Deque<Node> union = new ArrayDeque<>();
		
		dq.add(new Node(r, c));
		visited[r][c]= true;
		
		while(!dq.isEmpty()) {
			Node node = dq.pollFirst();
			int cr = node.r;
			int cc = node.c;
			
			sum+= map[cr][cc];
			union.addFirst(new Node(cr , cc));
			
			for(int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
					int diff = Math.abs(map[cr][cc] - map[nr][nc]);
					
					if(diff >= L && diff <= R) {
						dq.add(new Node(nr, nc));
						visited[nr][nc] = true;
					}
				}
			}
		}
		
		if(union.size() > 1) {
			int size = union.size();
			int div = sum / size;
			while(!union.isEmpty()) {
				Node node = union.pollFirst();
				map[node.r][node.c] = div;
			}
			return true;
		}
		return false;
	}
	
}
