import java.io.*;
import java.util.*;

public class Minjun {
	
	static class Flower implements Comparable<Flower> {
		int start;
		int end;
		public Flower(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Flower o) {
			return start - o.start;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// N개의 꽃
		// 같은 해에 피고 같은 해에 짐
		// 각 꽃마다 피는 날과 지는 날이 정해져 있음
		// 6월 13일 지는 꽃은 13일부터 볼 수 없음
		// N개의 꽃들 중 두 조건 만족하는 꽃들을 선택
		// 1. 3월 11일 ~ 11월 30일까지 매일 꽃 한개 이상이 피어있도록
		// 2. 심는 꽃들의 수는 가능한 적게
		// 선택한 꽃들의 최소 개수를 출력
		
		int flowerNum = Integer.parseInt(br.readLine());
		
		ArrayList<Flower> flowers = new ArrayList<>(flowerNum);
		
		for (int i = 0; i < flowerNum; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sM = Integer.parseInt(st.nextToken());
			int sD = Integer.parseInt(st.nextToken());
			int start = 100 * sM + sD;
			
			int eM = Integer.parseInt(st.nextToken());
			int eD = Integer.parseInt(st.nextToken());
			int end = 100 * eM + eD;
			
			flowers.add(new Flower(start, end));
		}
		
		flowers.sort(null);
		
		
		
		// 각 꽃마다 시작과 끝이 있음
		// 날짜 사이에 꽃이 하나라도 있어야함
		// 그리디, 정렬
		// 날짜 비교 단순 숫자로 
		// 3월 1일 이하의 날짜들 중에서 끝나는 날이 가장 먼 날짜가 그리디한 선택
		// 꽃 클래스 만들기 (시작, 끝)
		// 시작을 기준으로 정렬
		// 시작이 3월 1일 이하인지 확인
		// 꽃 선택이 불가능하다면 0을 출력
		// 현재 끝을 해당 꽃으로 갱신
		// 현재 끝이 1130을 초과할 때 까지 반복
		
		// 현재 끝을 기록 301
		int target = 301;
		int idx = 0;
		int cnt = 0;
		int maxEnd = 0;
		boolean isFound = false;
		
		// 시작이 301 이하이면서 끝나는 날이 가장 큰 꽃 고르기
		while(target <= 1130) {
			isFound = false;
			
			// 현재 target 이하로 피는 꽃들을 전부 스캔하면서 가장 늦게 지는 날(maxEnd) 찾기
			while (idx < flowerNum && flowers.get(idx).start <= target) {
				if (flowers.get(idx).end > maxEnd) {
					maxEnd = flowers.get(idx).end;
					isFound = true;
					if(maxEnd > 1130) break;
				}
				idx++;
			}
			
			// 2. 만약 isFound가 false라면? 
			// -> 현재 target을 이어받을 꽃이 아예 없다는 뜻! 중간에 날짜 빵꾸가 났으니 0 출력하고 탈출!
			if (isFound == false) {
				break;
			} else { // 3. 무사히 찾았다면?
				target = maxEnd; // -> 이제서야 비로소 target = maxEnd 로 바통 터치! 그리고 cnt++
				cnt++;
			}
		}
		System.out.println(isFound ? cnt : 0);
	}
}