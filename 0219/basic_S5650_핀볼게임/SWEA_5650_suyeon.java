import java.io.*;
import java.util.*;
import java.awt.*;

public class SWEA_5650_suyeon {
	public static int T, N, board[][], cnt;
	public static Point wormHole[][];
	public static int[][] meetBlock = {{0, 0, 0, 0}, {2, 0, 3, 1}, {2, 3, 1, 0}, {1, 3, 0, 2}, {3, 2, 0, 1}, {2, 3, 0, 1}};
	public static int[] oppositeDir = {2, 3, 0, 1};
	public static int[] dy = {0, 1, 0, -1};
	public static int[] dx = {1, 0, -1, 0};		// 우 하 좌 상
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine().trim());
			
			board = new int[N][N];
			wormHole = new Point[5][2];
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					
					// 웜홀 페어 정보 저장하기
					if(board[i][j] >= 6) {
						if(wormHole[board[i][j]-6][0] == null)
							wormHole[board[i][j]-6][0] = new Point(j, i);
						else wormHole[board[i][j]-6][1] = new Point(j, i);
					}
				}
			}
			cnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(board[i][j] != 0) continue;
					for(int dir = 0; dir < 4; dir++)
						cnt = Math.max(cnt, game(i, j, dir));
				}
			}
			bw.write("#" + test_case + " " + cnt + "\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static int game(int y, int x, int dir) {
		int score = 0;
		int curY = y, curX = x;
		
		while(true) {
			int ny = curY + dy[dir];
			int nx = curX + dx[dir];
			
			// 벽에 부딪힘
			if(ny < 0 || ny >= N || nx < 0 || nx >= N) {
				score++;						// 점수 증가
				dir = oppositeDir[dir];			// 방향 반대로
				curY = ny; curX = nx;
				continue;
			}
			
			// 시작 지점에 도달 || 블랙홀과 만남
			if((ny == y && nx == x) || board[ny][nx] == -1)
				return score;
			
			if(board[ny][nx] >= 1 && board[ny][nx] <= 5) {
				score++;
				dir = meetBlock[board[ny][nx]][dir];
				
			} else if(board[ny][nx] >= 6 && board[ny][nx] <= 10) {
				int holeIdx = board[ny][nx] - 6;
				Point h1 = wormHole[holeIdx][0];
				Point h2 = wormHole[holeIdx][1];

				if(ny == h1.y && nx == h1.x) {
					ny = h2.y; nx = h2.x;
				}
				else {
					ny = h1.y; nx = h1.x;
				}
			}
			curY = ny; curX = nx;
		}
	}
}
