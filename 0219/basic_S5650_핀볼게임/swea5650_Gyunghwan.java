package swea;
import java.util.*;
import java.io.*;

public class swea5650_Gyunghwan {
    static int N, MaxScore;
    static int[][] map;
    static List<Point>[] wormholes;
    static int[] dr = {-1, 0, 1, 0}; // 상, 우, 하, 좌 (시계방향)
    static int[] dc = {0, 1, 0, -1};

    // 블록 번호에 따른 방향 전환 (0:상, 1:우, 2:하, 3:좌)
    static int[][] changeDir = {
        {}, 
        {2, 3, 1, 0}, // 1번 블록
        {1, 3, 0, 2}, // 2번 블록
        {3, 2, 0, 1}, // 3번 블록
        {2, 0, 3, 1}, // 4번 블록
        {2, 3, 0, 1}  // 5번 블록
    };

    static class Point {
        int r, c;
        Point(int r, int c) { this.r = r; this.c = c; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            wormholes = new ArrayList[11];
            for (int i = 6; i <= 10; i++) wormholes[i] = new ArrayList<>();
            MaxScore = 0;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] >= 6) {
                        wormholes[map[i][j]].add(new Point(i, j));
                    }
                }
            }

            // 모든 빈 공간(0)에서 4방향으로 출발 시도
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0) {
                        for (int d = 0; d < 4; d++) {
                            MaxScore = Math.max(MaxScore, simulation(i, j, d));
                        }
                    }
                }
            }
            System.out.println("#" + t + " " + MaxScore);
        }
    }

    static int simulation(int startR, int startC, int dir) {
        int score = 0;
        int currR = startR;
        int currC = startC;
        int d = dir;

        while (true) {
            int nr = currR + dr[d];
            int nc = currC + dc[d];

            // 벽에 부딪히는 경우
            if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                score++;
                d = (d + 2) % 4; // 반대 방향
                currR = nr; currC = nc; 
                continue; 
            }

            // 블랙홀이거나 시작 위치인 경우 종료
            if ((nr == startR && nc == startC) || map[nr][nc] == -1) {
                return score;
            }

            if (map[nr][nc] >= 1 && map[nr][nc] <= 5) {
                // 블록을 만난 경우
                score++;
                d = changeDir[map[nr][nc]][d];
            } else if (map[nr][nc] >= 6) {
                // 웜홀을 만난 경우
                int holeIdx = map[nr][nc];
                Point p1 = wormholes[holeIdx].get(0);
                Point p2 = wormholes[holeIdx].get(1);
                if (nr == p1.r && nc == p1.c) {
                    nr = p2.r; nc = p2.c;
                } else {
                    nr = p1.r; nc = p1.c;
                }
            }

            currR = nr;
            currC = nc;
        }
    }
}