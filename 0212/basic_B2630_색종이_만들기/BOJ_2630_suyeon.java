import java.io.*;
import java.util.*;

public class BOJ_2630_suyeon {
	public static int N, white = 0, blue = 0;
	public static int[][] paper;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		paper = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
				paper[i][j] = Integer.parseInt(st.nextToken());		
		}		
		cut(0, 0, N);
		System.out.println(white);
		System.out.println(blue);
	}
	
	public static void cut(int y, int x, int size) {
		if(check(y, x, size)) {
			if(paper[y][x] == 0)
				white++;
			else if(paper[y][x] == 1)
				blue++;
			return;
		}
		
		int halfSize = size / 2;
		cut(y, x, halfSize);
		cut(y, x + halfSize, halfSize);
		cut(y + halfSize, x, halfSize);
		cut(y + halfSize, x + halfSize, halfSize);
	}
	
	public static boolean check(int y, int x, int size) {
		int prev = paper[y][x];
		for(int i = y; i < y+size; i++) {
			for(int j = x; j < x+size; j++) {
				if(paper[i][j] != prev)
					return false;
				prev = paper[i][j];
			}
		}
		return true;
	}
}