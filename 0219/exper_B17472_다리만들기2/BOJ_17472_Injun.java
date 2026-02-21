import java.io.*;
import java.util.*;

/*
 * 첫 생각
 * 1. 섬마다 가능한 다리를 저장해야 함
 * 2. 다리는 edge니까 다익스트라를 사용하면 가능할 것 같다. 
 * 3. MST를 만드니까 섬의 개수 -1보다 가능한 다리가 적으면 무조건 불가능하다. 
 * 4. 섬의 개수는 dfs로 찾을 수 있을 것 같음
 * 
 * 문제를 푼 흐름
 * 1. dfs를 사용해서 섬을 찾고 id를 부여
 * 2. 섬의 가장자리에서 직선으로 이동하며 가능한 다리를 모두 계산
 * 3. int형 배열에 저장된 다리를 Edge가 들어있는 list로 변형
 * 4. 다리 list에 다익스트라 적용
 * 5. 이후 find를 사용해서 모든 점들이 서로 연결되었는지 확인 (이 부분을 해결 못했음..)
 * 
 * */
public class P17472_2 {
	static int[][] board;
	static boolean[][] visited;
	static int n, m;
	static int count = 1;
	
	static int[][] bridge;
	static int[] parent;
	
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		int cost;
		
		public Edge (int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}

	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new int[n][m];
		visited = new boolean[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬의 개수를 세면서 섬에 id 부여 + 섬의 가장자리 찾기
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(!visited[i][j] && board[i][j] != 0) {
					dfs(i, j, count);
					count++;
				}
			}
		}
		count--;
		
		// 섬의 가장자리에서 갈 수 있는 섬의 다리 찾기
		bridge = new int[count+1][count+1];
		for(int i = 0; i <= count; i++) {
			Arrays.fill(bridge[i], Integer.MAX_VALUE);
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(board[i][j] > 0) {
					canBridge(i, j, board[i][j]);
				}
			}
		}
		
		List<Edge> mst = new ArrayList<>();
		for(int i = 1; i <= count; i++) {
			for(int j = i+1; j <= count; j++) {
				if(bridge[i][j] < Integer.MAX_VALUE) {
					mst.add(new Edge(i, j, bridge[i][j]));
				}
			}
		}
		
		// MST가 만들어질 수 없으면 불가능
		if(mst.size() < count-1) {
			System.out.println(-1);
			System.exit(0);
		}
		mst.sort(null);
		
		// 다리들을 사용해서 다익스트라
		int edgeCount = 0;
		int totalCost = 0;
		parent = new int[count+1];
		for(int i = 1; i <= count; i++) {
			parent[i] = i;
		}
		
		for(Edge curEdge : mst) {
			if(union(curEdge.start, curEdge.end)) {
				totalCost += curEdge.cost;
				edgeCount++;
			}
			
			if(edgeCount == count - 1) {
				break;
			}
		}
		
		// 모두 처리 후 find를 사용해서 모든 점들이 연결 되었는지 확인
		boolean isValid = true;
		int root = find(1);
		for(int i = 2; i <= count; i++) {
			if(root != find(i)) {
				isValid = false;
				break;
			}
		}
		
		if(isValid) {
			System.out.println(totalCost);
		} else {
			System.out.println(-1);
		}
	}

	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA != rootB) {
			parent[rootB] = rootA;
			return true;
		}
		return false;
	}

	private static int find(int a) {
		if(parent[a] == a) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}

	private static void canBridge(int ni, int nj, int start) {
		for(int i = 0; i < 4; i++) {
			int cost = 0;
			int nexti = ni + di[i];
			int nextj = nj + dj[i];
			
			if(nexti >= 0 && nexti < n && nextj >= 0 && nextj < m) {
				if(board[nexti][nextj] != 0) {
					continue;
				}
			}
			
			while(nexti >= 0 && nexti < n && nextj >= 0 && nextj < m) {
				if(board[nexti][nextj] > 0) {
					int end = board[nexti][nextj];
					if(cost > 1) {
						bridge[start][end] = Math.min(bridge[start][end], cost);
					}
					
					break;
				}
				
				nexti = nexti + di[i];
				nextj = nextj + dj[i];
				cost++;
			}
			
		}
		
	}

	private static void dfs(int ni, int nj, int count) {
		visited[ni][nj] = true;
		
		for(int i = 0; i < 4; i++) {
			int nexti = ni + di[i];
			int nextj = nj + dj[i];
			
			if(nexti >= 0 && nexti < n && nextj >= 0 && nextj < m) {
				if(board[nexti][nextj] != 0 && !visited[nexti][nextj]) {
					dfs(nexti, nextj, count);
				}
			}
		}
		
		board[ni][nj] = count;
	}
}
