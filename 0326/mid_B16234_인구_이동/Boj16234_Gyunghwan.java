
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16234_Gyunghwan {
	static boolean isvisited[][];
	static int map[][];
	static int L, R, N;
	static int dr[] = { 0, 1, 0, -1 };
	static int dc[] = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;		
		while (true) {
			isvisited = new boolean[N][N];
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!isvisited[i][j]) {
						bfs(i, j);
						cnt++;
					}
				}
			}
			
			if(cnt==N*N) {
				break;
			}
			result++;
			
		}
		System.out.println(result);


		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}

	}

	static void bfs(int r, int c) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { r, c });
		List<int[]> list = new ArrayList<>();
		int count = 1;
		int sum = map[r][c];
		isvisited[r][c] = true;
		list.add(new int[] { r, c });
		while (!queue.isEmpty()) {
			int qval[] = queue.poll();
			int curr = qval[0];
			int curc = qval[1];
			for (int i = 0; i < 4; i++) {
				int nextr = curr + dr[i];
				int nextc = curc + dc[i];

				if (nextr < N && 0 <= nextr && nextc < N && 0 <= nextc && !isvisited[nextr][nextc]) {
					int difval = Math.abs(map[curr][curc] - map[nextr][nextc]);
					if (L <= difval && difval <= R) {
						isvisited[nextr][nextc] = true;
						sum += map[nextr][nextc];
						queue.offer(new int[] { nextr, nextc });
						list.add(new int[] { nextr, nextc });
						count++;
					}
				}
			}
		}

		for (int i = 0; i < list.size(); i++) {
			int listval[] = list.get(i);
			map[listval[0]][listval[1]] = sum / count;
		}
	}
}
