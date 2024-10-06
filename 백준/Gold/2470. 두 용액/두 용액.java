import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = s.nextInt();
		}

		Arrays.sort(arr);

		// 시작점, 끝점 
		int start = 0;
		int end = N - 1;

	
		int closestSum = Integer.MAX_VALUE;
		int result1 = 0, result2 = 0;

		while (start < end) {
			int sum = arr[start] + arr[end];   // 현재 값

			// 계속 업데이트
			if (Math.abs(sum) < Math.abs(closestSum)) {
				closestSum = sum;
				result1 = arr[start];
				result2 = arr[end];
			}

			
			if (sum > 0) { // 양수면 오른쪽에서 줄어들고
				end--;
			} else {
				start++;   // 음수면 왼쪽에서 줄어은다
			}
		}

		System.out.println(result1 + " " + result2);
	}
}
