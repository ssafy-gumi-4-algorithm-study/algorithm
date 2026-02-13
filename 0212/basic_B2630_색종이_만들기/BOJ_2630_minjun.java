import java.io.*;
import java.util.*;

public class BOJ_2630_minjun {
	static int blue;
	static int white;
	static boolean[][] paper;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		paper = new boolean[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				paper[i][j] = "1".equals(st.nextToken());
			}
		}
		
		cutPaper(1, 1, N);
		
		sb.append(white).append("\n").append(blue);
		System.out.print(sb);
		
	}
	
	public static void cutPaper(int row, int col, int size) { // (r, c, size) 시작좌표, 현재크기로 재귀 돌리기
		if(isOnePiece(row, col, size)) { // 한조각이면
			if (paper[row][col]) blue++;
			else white++;
		} else {
			int nextSize = size/2;
			cutPaper(row, col, nextSize);
			cutPaper(row, col + nextSize, nextSize);
			cutPaper(row+nextSize, col, nextSize);
			cutPaper(row+nextSize, col+nextSize, nextSize);
		}
	}
	
	public static boolean isOnePiece(int row, int col, int size) {
		boolean isOnePiece = true;
		boolean crtColor = paper[row][col];
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if(crtColor != paper[i][j]) return false;
			}
		}
		return isOnePiece;
	}
	
	
}