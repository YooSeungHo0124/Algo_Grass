import java.util.*;

public class Main {
	static int N, M;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		N = s.nextInt();
		M = s.nextInt();

		int[] videos = new int[N];

		int left = 0;   
		int right = 0;
		for (int i = 0; i < N; i++) {
			videos[i] = s.nextInt();
			right += videos[i];               // 모든 값 다 더한게 제일 큰 값
			left = Math.max(left, videos[i]); // 합들 중 가장 작게 될 수 있는 값 = 비디오 제일 끝값 하나  
		}
		
		// 이분 탐색 사용
		while (left <= right) {
			int mid = (left + right) / 2;
			if (isPossible(videos, mid)) {  // 함수에서 판별
				right = mid - 1;            // 가능 하다면 -> 더 작은 블루레이에서 실행하도록
			} else { 
				left = mid + 1;             // 불가능 하다면 -> 더 큰 블루레이에서 해보도록
			}
		}

		System.out.println(left);
	}

	private static boolean isPossible(int[] videos, int mid) { // mid = 현재 고려중인 블루레이 크기
		int count = 1;   // 현재 사용중인 블루레이 개수
		int sum = 0;     // 비디오 합

		for (int v : videos) {
			if (sum + v > mid) {  // 블루레이보다 커지면 다음 블루레이에 담을거다 <-> count++ , 
				count++;            
				sum = v;          // 다음 블루레이에 첫 값에 v값 추가
			} else {
				sum += v;         // 비디오 추가할 블루레이 공간 남아있으면 추가
			}

			if (count > M) {      // 담다가 이미 사용가능한 블루레이 수 넘으면 false <-> 블루레이 크기가 너무 작았다는 뜻
				return false;
			}
		}

		return true;             // 모두 비디오가 블루레이수 안에 잘 담겼다면 trye <-> 블루레이 크기가 컸다는 뜻
	}
}
