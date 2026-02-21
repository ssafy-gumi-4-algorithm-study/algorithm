import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<int[]> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        list = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            list.add(new int[] {m - r, m + r});
        }

        // 왼쪽 끝은 오름차순, 오른쪽 끝은 내림차순으로 정렬
        list.sort((o1, o2) -> {
            int result = o1[0] - o2[0];
            if(result == 0) {
                result = o2[1] - o1[1];
            }

            return result;
        });

        // stack에 넣으면서 지름을 모두 덮는지 계산
        int count = 1;
        Deque<int[]> stack = new ArrayDeque<>();
        int[] c = list.get(0);
        stack.addLast(new int[]{c[0], c[1], 0});
        for(int i = 1; i < n; i++) {
            c = list.get(i);

            // 다음 위치의 원이 들어오면 stack에서 그 전에 있던 원을 빼서 처리
            while(!stack.isEmpty() && stack.peekLast()[1] <= c[0]) {
                int[] finished = stack.removeLast();
                // 지름을 모두 덮었다면 위, 아래 2가지 영역이 생긴다. 
                if(finished[1] - finished[0] == finished[2]) {
                    count += 2;
                } else {
                    count += 1;
                }
            }

            // 자기 바로 위의 원에 지름을 더해준다. 
            if(!stack.isEmpty()) {
                stack.peekLast()[2] += (c[1] - c[0]);
            }

            stack.addLast(new int[]{c[0], c[1], 0});
        }

        // 스택에 남아있는 원 처리
        while(!stack.isEmpty()) {
            int[] finished = stack.removeLast();
            if(finished[1] - finished[0] == finished[2]) {
                count += 2;
            } else {
                count += 1;
            }
        }

        System.out.println(count);
    }
}
