import java.io.*;
import java.util.*;

public class Main {
	
	public static class Set {
		private int[] set;

		public Set(int n) {
			set = new int[n];
			for(int i = 0; i < n; i++) {
				set[i] = i;
			}
		}
		
		public void uinon(int y, int x) {
			set[find(y)] = find(x);
		}
		
		public int find(int x) {
			if(set[x] != x) {
				set[x] = find(set[x]);
			}
			return set[x];
		}
		
	}
	
	public static class Edge {
		public int from;
		public int to;
		public int len;

		public Edge(int from, int to, int len) {
			this.from = from;
			this.to = to;
			this.len = len;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", len=" + len + "]";
		}
		
	}
	
	public static class Node {
		public int y;
		public int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	public static int n, m;
	public static int[][] graph;
	
	public static int[] dy = { 0, 1, 0, -1 };
	public static int[] dx = { 1, 0, -1, 0 };

	public static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.len - e2.len);
	
	public static void ff(Node start, int number, Queue<Node> target) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(start);
		boolean[][] visited = new boolean[n][m];
		visited[start.y][start.x] = true;
		
		while(!q.isEmpty()) {
			
			Node now = q.poll();
			target.add(now);
			graph[now.y][now.x] = number;
			
			for(int i = 0; i < 4; i++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];
				try {
					if(!visited[ny][nx] && graph[ny][nx] == 1) {
						visited[ny][nx] = true;
						q.add(new Node(ny, nx));
					}	
				} catch(ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}
		}
	}
	
	public static void bfs(Queue<Node> q, int number) {
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			for(int i = 0; i < 4; i++) {
				try {
					int ny = now.y + dy[i];
					int nx = now.x + dx[i];
					int len = 0;
					while(graph[ny][nx] != number) {
						if(graph[ny][nx] > 0 ) {
							if(len > 1) {
								pq.add(new Edge(number, graph[ny][nx], len));	
							}
							break;
						}
						ny += dy[i];
						nx += dx[i];
						len++;
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}
		}
	}
    
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		graph = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				graph[i][j] = sc.nextInt();
			}
		}
		
		List<Queue<Node>> list = new ArrayList<>();
		int number = 2;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(graph[i][j] == 1) {
					Queue<Node> q = new ArrayDeque<>();
					ff(new Node(i, j), number++, q);
					list.add(q);
				}
			}
		}
				
		int som = list.size();
		number = 2;
		for(Queue<Node> q : list) {
			bfs(q, number++);
		}
		
		Set set = new Set(10);
		int count = 0;
		int sum = 0;

		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			if(set.find(now.from) == set.find(now.to)) {
				continue;
			}
			set.uinon(now.from, now.to);
			sum += now.len;
			count++;
			if(count >= som - 1) {
				break;
			}
		}
		if(count < som - 1) {
			System.out.println(-1);
		} else {
			System.out.println(sum);
		}		
	}
}