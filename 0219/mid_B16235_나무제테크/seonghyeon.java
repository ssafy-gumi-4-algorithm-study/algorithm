import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static int n, m, k;
    static int[][] ground;
    static int[][] energy; 
    static Deque<Integer>[][] treeMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        ground = new int[n][n];
        energy = new int[n][n];
        treeMap = new LinkedList[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                energy[i][j] = Integer.parseInt(st.nextToken());
                ground[i][j] = 5; // 초기 양분은 5
                treeMap[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            
            treeMap[x][y].add(age);
        }

        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!treeMap[i][j].isEmpty()) {
                    List<Integer> temp = (List<Integer>) treeMap[i][j];
                    Collections.sort(temp);
                }
            }
        }

        while (k-- > 0) {
            springAndSummer();
            autumn();
            winter();
        }

        
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                count += treeMap[i][j].size();
            }
        }
        System.out.println(count);
    }

    public static void springAndSummer() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (treeMap[i][j].isEmpty()) continue;

                Deque<Integer> nextTrees = new LinkedList<>();
                int deadEnergy = 0;
                
                
                while (!treeMap[i][j].isEmpty()) {
                    int age = treeMap[i][j].pollFirst();
                    if (ground[i][j] >= age) {
                        ground[i][j] -= age;
                        nextTrees.addLast(age + 1);
                    } else {
                        deadEnergy += (age / 2);
                    }
                }
                treeMap[i][j] = nextTrees;
                ground[i][j] += deadEnergy;
            }
        }
    }

    public static void autumn() {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        List<int[]> newTrees = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int age : treeMap[i][j]) {
                    if (age % 5 == 0) {
                        for (int d = 0; d < 8; d++) {
                            int nx = i + dx[d];
                            int ny = j + dy[d];
                            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                                newTrees.add(new int[]{nx, ny});
                            }
                        }
                    }
                }
            }
        }
     
        for (int[] pos : newTrees) {
            treeMap[pos[0]][pos[1]].addFirst(1);
        }
    }

    public static void winter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ground[i][j] += energy[i][j];
            }
        }
    }
}