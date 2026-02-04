import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Deque;
import java.util.ArrayDeque;

public class Main {
	static int n,m;
	static int[][] board;
	static int[] dx = {0,0,0,-1,1};
	static int[] dy = {0,1,-1,0,0};
	static Node[] rowQueue = new Node[4];
	static Node[] colQueue = new Node[4];
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][m];
		
		int startX = Integer.parseInt(st.nextToken());
		int startY = Integer.parseInt(st.nextToken());
		int playCnt = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0;j<m;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(bf.readLine());
		Node up = new Node(1);
		Node down = new Node(6);
		
		// 북남 큐
		rowQueue[0] = new Node(2);
		rowQueue[1] = up;
		rowQueue[2] = new Node(5);
		rowQueue[3] = down;
		
		// 동서 큐
		colQueue[0] = new Node(4);
		colQueue[1] = up;
		colQueue[2] = new Node(3);
		colQueue[3] = down;
		
		final int topIdx = 1;
		final int baseIdx = 3;
		while(playCnt-- > 0) {
			int dir = Integer.parseInt(st.nextToken());
			
			int nx = startX + dx[dir];
			int ny = startY + dy[dir];
			
			if(nx<0||ny<0||nx>=n||ny>=m) continue;
			// 남쪽 이동
			if(dir == 4) {
				Node temp = rowQueue[3];
				rowQueue[3] = rowQueue[2];
				rowQueue[2] = rowQueue[1];
				rowQueue[1] = rowQueue[0];
				rowQueue[0] = temp;
				
				colQueue[topIdx] = rowQueue[topIdx];
				colQueue[baseIdx] = rowQueue[baseIdx];
				
				board[nx][ny] = rowQueue[baseIdx].change(board[nx][ny]);
			}else if(dir == 3){
				// 북쪽 이동
				Node temp = rowQueue[0];
				rowQueue[0] = rowQueue[1];
				rowQueue[1] = rowQueue[2];
				rowQueue[2] = rowQueue[3];
				rowQueue[3] = temp;
				
				colQueue[topIdx] = rowQueue[topIdx];
				colQueue[baseIdx] = rowQueue[baseIdx];
				
				board[nx][ny] = rowQueue[baseIdx].change(board[nx][ny]);
			}else if(dir == 2) {
				// 서쪽 이동
				Node temp = colQueue[0];
				colQueue[0] = colQueue[1];
				colQueue[1] = colQueue[2];
				colQueue[2] = colQueue[3];
				colQueue[3] = temp;
				
				rowQueue[topIdx] = colQueue[topIdx];
				rowQueue[baseIdx] = colQueue[baseIdx];
				
				board[nx][ny] = colQueue[baseIdx].change(board[nx][ny]);
			}else if(dir == 1) {
				// 동쪽 이동
				Node temp = colQueue[3];
				colQueue[3] = colQueue[2];
				colQueue[2] = colQueue[1];
				colQueue[1] = colQueue[0];
				colQueue[0] = temp;
				
				rowQueue[topIdx] = colQueue[topIdx];
				rowQueue[baseIdx] = colQueue[baseIdx];
				
				board[nx][ny] = colQueue[baseIdx].change(board[nx][ny]);
			}
			startX = nx;
			startY = ny;
			System.out.println(rowQueue[topIdx].value);
		}
	}
	
	static class Node{
		int num;
		int value = 0;
		
		Node(int num){
			this.num = num;
		}
		
		public int change(int boardNum) {
			if(boardNum == 0) {
				return this.value;
			}else {
				this.value = boardNum;
				return 0;
			}
		}
	}
}
