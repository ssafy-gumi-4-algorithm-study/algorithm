package boj;

import java.io.*;
import java.util.*;

public class Boj1316 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int cnt = 0;
		boolean[] alpa = new boolean[26];
		for (int i = 0; i < n; i++) {
			String word = br.readLine();
			
			Arrays.fill(alpa, false);
			int pre = -1;
			boolean isGroup = true;
			for (int j = 0; j < word.length(); j++) {
				int cur = word.charAt(j) - 'a';
				if (pre == cur) {
					continue;
				}
				if (alpa[cur]) {
					isGroup = false;
					break;
				}
				alpa[cur] = true;
				pre = cur;
			}
			
			if (isGroup) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
