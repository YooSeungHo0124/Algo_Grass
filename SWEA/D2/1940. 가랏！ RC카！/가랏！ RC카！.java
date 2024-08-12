import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int test_case = s.nextInt();
		int result_cnt = 1;

		while (test_case > 0) {
			int N = s.nextInt(); // 개수
			int cnt = 0; // 결과 카운팅
			int speed = 0; // 현재 속력

			for (int i = 0; i < N; i++) {
				int order = s.nextInt(); // 1:가속, 2:감속, 0:현재 속도 유지
				int magnitude =0;
				if (order != 0) {
					magnitude = s.nextInt(); // 크기   // 속도 유지가 아닐때 값 받기
				}

				if (order == 1) {
					speed += magnitude;
				} else if (order == 0) {
					// 할일없음
				} else {
					speed -= magnitude;
					if (speed < 0) {
						speed = 0;
					}
				}
				cnt += speed;
			}
			System.out.println("#" + result_cnt + " " + cnt);

			test_case--;
			result_cnt++;
		}

	}
}
