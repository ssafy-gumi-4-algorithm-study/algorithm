package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_1316_Gyunghwan {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int count = 0;
		for (int n = 0; n < N; n++) {
			String st = bf.readLine();
			boolean isgroup = true;

			ArrayDeque<Character> queue = new ArrayDeque<>();

			ArrayDeque<Character> queueCompare = new ArrayDeque<>();

			for (int i = 0; i < st.length(); i++) {
				queue.offer(st.charAt(i));

			} // 단어들 queue에 저장

			for (int i = 0; i < st.length(); i++) {

				char C = queue.poll(); // 단어 하나꺼내고

				if (queue.isEmpty() || queue.peek() != C) { // 비어져있거나 다음것이 같지 않으면

					if (queueCompare.contains(C)) {  //queuecompare에 C가있으면
						isgroup = false;
						break;
					} else {
						queueCompare.offer(C); // 아니면 queuecompare에 저장
					}

				}

			}

			if (isgroup) {
				++count;
			}

		}
		System.out.println(count);
	}

}
