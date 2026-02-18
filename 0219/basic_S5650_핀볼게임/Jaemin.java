import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;


public class Jaemin {
    static int n, map[][];
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    static class Point {
        int r;
        int c;
        
        Point(int r, int c){
            this.r = r;
            this.c = c;
        }     
    }

    static List<Point>[] wormHoles;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());        
        StringBuilder sb = new StringBuilder();
        for(int t = 1; t <= T; t++){
            sb.append("#").append(t).append(" ");

            String str = br.readLine().trim();
            // isBlank => Java11
            if(str.trim().isEmpty()) return;
            n = Integer.parseInt(str);
            map = new int[n][n];
            wormHoles = new ArrayList[11];

            for(int i = 6; i <= 10; i++){
                wormHoles[i] = new ArrayList<>();
            }


            for(int i = 0; i < n; i++){
                str =br.readLine();
                while (Objects.nonNull(str) && str.trim().isEmpty()) {
                    str = br.readLine();
                }
                if(Objects.isNull(str)) break;
                StringTokenizer st = new StringTokenizer(str);
                for(int j = 0; j < n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken().trim());

                    if(map[i][j] >= 6){
                        int number = map[i][j];
                        wormHoles[number].add(new Point(i, j));
                    }
                }
            }

            int answer = 0;
            for (int i = 0; i < n; i++) {
                for(int j = 0; j <n; j++){
                    if(map[i][j] == 0){
                        for(int d = 0; d < 4; d++){
                            answer = Math.max(answer, solve(i, j, d));
                        }
                    }
                }
            }
            sb.append(answer).append("\n");

        }
        System.out.println(sb);
    }
    static int solve(int r, int c, int d){
        int currentR = r;
        int currentC = c;
        int score = 0;

        int[][] changeD = {
            {},
            {1, 3, 0 ,2}, // 1
            {3, 0, 1, 2}, // 2
            {2, 0, 3, 1}, // 3
            {1, 2, 3, 0}, // 4
            {1, 0, 3, 2} // 5
        };

        while (true) {
            currentR += dr[d];
            currentC += dc[d];

            if(r == currentR && c == currentC ||
                (catchOutOfIndex(currentR, currentC)) && map[currentR][currentC] == -1){
                return score;
            }

            if(!catchOutOfIndex(currentR, currentC)){
                score++;
                d = (d %2 == 0) ? d + 1 : d -1;
                continue;
            }

            if(map[currentR][currentC] >= 1 && map[currentR][currentC] <= 5){
                score++;
                d = changeD[map[currentR][currentC]][d];
                continue;
            }

            if(map[currentR][currentC] >=6 && map[currentR][currentC] <= 10){
                Point pair = findPairHole(currentR, currentC, map[currentR][currentC]);
                currentR = pair.r;
                currentC = pair.c;
            }
        }

    }

    static boolean catchOutOfIndex(int r, int c){
        return r>= 0 && r < n && c >= 0 && c < n;
    }

    static Point findPairHole(int r, int c, int number){
        List<Point> pair = wormHoles[number];

        Point point = pair.get(0);
        return (point.r == r && point.c == c) ? pair.get(1) : pair.get(0);
    }
}
