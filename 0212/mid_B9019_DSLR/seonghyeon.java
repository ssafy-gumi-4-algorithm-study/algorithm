package samsung01;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
	static int n, target;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        for(int testCase = 0;testCase<t;testCase++) {
        	st = new StringTokenizer(br.readLine());
        	String str = st.nextToken();
        	n = Integer.parseInt(str);
        	target = Integer.parseInt(st.nextToken());
        	bfs(n, target);
        }
        
    }
    
    public static void bfs(int start, int target) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[10000];
        String[] path = new String[10000];
        
        q.add(start);
        visited[start] = true;
        path[start] = "";

        while (!q.isEmpty()) {
            int curr = q.poll();

            if (curr == target) {
                System.out.println(path[curr]);
                return;
            }

            int D = (curr * 2) % 10000;
            if (!visited[D]) {
                update(q, visited, path, curr, D, "D");
            }

            int S = (curr == 0) ? 9999 : curr - 1;
            if (!visited[S]) {
                update(q, visited, path, curr, S,"S");
            }
            
            int L = (curr % 1000) * 10 + (curr / 1000);
            if (!visited[L]) {
            	update(q, visited, path, curr, L,"L");
            }

            int R = (curr % 10) * 1000 + (curr / 10);
            if (!visited[R]) {
                update(q,visited, path, curr, R, "R");
            }
        }
    }

	private static void update(Queue<Integer> q, boolean[] visited, String[] path, int curr, int value, String order) {
		q.add(value);
		visited[value] = true;
		path[value] = path[curr] + order;
	}
   
}