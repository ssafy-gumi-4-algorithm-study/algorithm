import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Set;
import java.util.HashSet;

public class seng {
	static int n,m;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		
		Deque<String> deque;
		Set<String> groupWord;
		int cnt = 0;
		for(int i=0;i<n;i++) {
			String[] word = bf.readLine().split("");
			deque = new ArrayDeque<String>();
			groupWord = new HashSet<String>();
			deque.add(word[0]);
			boolean isGroup = true;
			for(int j=1;j<word.length;j++) {
				String str = word[j];
				
				if(groupWord.contains(str)) {
					isGroup = false;
					break;
				}
				boolean isEqual = deque.peek().equals(str);
				if(!isEqual) {
					groupWord.add(deque.pop());
					deque.add(str);
				}
			}
			if(isGroup) cnt++;
		}
		System.out.println(cnt);
	}
}
