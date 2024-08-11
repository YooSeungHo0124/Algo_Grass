import java.util.*;

public class Solution {
	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int test_case = s.nextInt();
		int result_cnt = 1;
		while (test_case > 0) {

			int N = s.nextInt();
			int M = s.nextInt();
			char[][] map = new char[N][M];
			for (int i = 0; i < N; i++) {
				String str = s.next();
				for (int j = 0; j < str.length(); j++) {
					map[i][j] = str.charAt(j);
				}
			}
//			System.out.println(Arrays.deepToString(map));

//			int s_e_cnt = 0; // 처음, 끝 바뀌는 수 카운팅
//			// 첫번째 줄 -> to W
//			for (int i = 0; i < M; i++) {
//				if (map[0][i] != 'W') {
//					s_e_cnt++;
//				}
//			}
//			// 마지막 줄 -> to R
//			for (int i = 0; i < M; i++) {
//				if (map[N - 1][i] != 'R') {
//					s_e_cnt++;
//				}
//			}

			// 중간 줄 -> 색깔당 카운팅
			int[] toW_arr = new int[N];
			int[] toR_arr = new int[N];
			int[] toB_arr = new int[N];
			for (int i = 0; i < N; i++) {
				int toW = 0;
				int toR = 0;
				int toB = 0;
				for (int j = 0; j < M; j++) {
					if (map[i][j] != 'W') {
						toW++;
					}
					if (map[i][j] != 'B') {
						toB++;
					}
					if (map[i][j] != 'R') {
						toR++;
					}
				}
				toW_arr[i] = toW;
				toR_arr[i] = toR;
				toB_arr[i] = toB;
			}

			int min = Integer.MAX_VALUE;
			for (int w = 0; w < N - 2; w++) {
				for (int b = 1; b < N - 1; b++) {
					int toW_sum = 0;
					int toB_sum = 0;
					int toR_sum = 0;
					for (int k = 1; k <= w; k++) {
						toW_sum += toW_arr[k];
					}
					for (int k = w + 1; k <= b; k++) {
						toB_sum += toB_arr[k];
					}
					for (int k = b + 1; k < N - 1; k++) {
						toR_sum += toR_arr[k];
					}
					min = Math.min(toW_sum + toB_sum + toR_sum, min);
				}
			}

			System.out.println("#" + result_cnt + " " + (min + toW_arr[0] + toR_arr[N - 1]));

			test_case--;
			result_cnt++;
		}

	}
}
