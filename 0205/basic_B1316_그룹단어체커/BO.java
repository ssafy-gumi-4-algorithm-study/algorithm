import java.util.*;

public class BO {
	
	public static boolean isGroupWord(String s) {
		
		char[] arr = s.toCharArray();
		boolean[] visited = new boolean[26];
		
		// 문자열의 문자들을 하나씩 순회
		for(int i = 0; i < arr.length; i++) {
			
			// 문자열에서 현재 발견한 문자
			int current = arr[i] - 'a';
			
			// 발견한 문자를 아직 방문하지 않았다면 방문 후 다음 단어 탐색
			if(!visited[current]) {
				visited[current] = true;
			}
			
			//이미 이전에 방문했던 문자라면 단어가 이어지는 중인지 탐색
			else {
				
				/*
				 * !--------------------------------------------------------------------!
				 *  배열의 첫 번째 문자 arr[0]은 반드시 처음 방문하는 문자임이 자명하므로
				 *  arr[i - 1] 접근에 대한 ArrayIndexOutOfBoundsException 예외처리는 필요하지 않다
				 * !--------------------------------------------------------------------!
				 */
				int prev = arr[i - 1] - 'a'; // 바로 이전 문자
				
				
				// 바로 전 문자와도 다르며 이전에 이미 등장한 문자라면 false를 리턴
				if(current != prev) {
					return false;
				}
				
			}
			
		}
		
		// 문자열 내의 모든 문자들이 이어지는 문자인지 검증을 모두 통과했다면 true를 리턴
		return true;
	}
	
    public static void main(String[] args) {
    	
    	Scanner sc = new Scanner(System.in);
    	
    	int n = sc.nextInt();
    	sc.nextLine();
    	
    	int count = 0;
    	
    	// 문자열을 차례로 입력 받으며 그룹 단어라면 카운트를 1 증가
    	for(int i = 0; i < n; i++) {
    		if(isGroupWord(sc.nextLine())) {
    			count++;
    		}
    	}
    	
    	System.out.println(count);
       
    }
}