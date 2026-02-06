import java.io.*;

public class suyeon {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int cnt = 0;
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			char[] word = str.toCharArray();
			int[] alp = new int[28];
			char prev = 'a';
			boolean isGroup = true;
			
			for(char c : word) {
				if(prev != c && alp[c-97] == 1) {
					isGroup = false;
					break;
				}
				alp[c-97] = 1;
				prev = c;
			}
			
			if(isGroup == true)
				cnt++;
		}
		System.out.println(cnt);
	}
}
