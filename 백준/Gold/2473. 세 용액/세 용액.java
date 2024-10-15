import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		int[] arr = new int[N];

		// 입력 받기
		for (int i = 0; i < N; i++) {
			arr[i] = s.nextInt();
		}

		// 오름차순 정렬
		Arrays.sort(arr);

		long closestSum = Long.MAX_VALUE;
		int result1 = 0, result2 = 0, result3 = 0;

		// 기존 두용액에서, for문으로 모든 케이스에 대해서 두 용액 해준다.
		for (int i = 0; i < N - 2; i++) {
			int start = i + 1;
			int end = N - 1;

			while (start < end) {
				long sum = (long) arr[i] + arr[start] + arr[end]; // 세 용액의 합

				// 가장 0에 가까운 값으로 업데이트
				if (Math.abs(sum) < Math.abs(closestSum)) {
					closestSum = sum;
					result1 = arr[i];
					result2 = arr[start];
					result3 = arr[end];
				}

				if (sum > 0) {
					end--;
				}
				else {
					start++;
				}
			}
		}

		// 결과 출력
		System.out.println(result1 + " " + result2 + " " + result3);
	}
}