import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int test_case = s.nextInt(); 
		while (test_case-- > 0) {
			int K = s.nextInt(); // 파일의 개수
			int[] p = new int[K];
			for (int i = 0; i < K; i++) {
				p[i] = s.nextInt(); 
			}
			
			// DP 테이블 초기화
			int[][] dp = new int[K][K];
			int[][] sum = new int[K][K];

			// sum 테이블 계산 - 구간의 합
			for (int i = 0; i < K; i++) {
				sum[i][i] = p[i];
				for (int j = i + 1; j < K; j++) {
					sum[i][j] = sum[i][j - 1] + p[j]; // 구간 [i, j]의 합
				}
			}

			// DP 계산
			for (int len = 2; len <= K; len++) { // 파일의 개수 len
				for (int i = 0; i <= K - len; i++) {
					int j = i + len - 1;
					dp[i][j] = Integer.MAX_VALUE;
					// 파일을 i에서 j까지 합치는 경우의 최소 비용
					for (int k = i; k < j; k++) {
						dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + sum[i][j]);
					}
				}
			}

			// 결과 출력
			System.out.println(dp[0][K - 1]);
			
////			 과정 출력 
//			for (int i=0; i<K; i++) {
//				for (int j=0; j<K; j++) {
//					System.out.printf("%5d",sum[i][j]);
//				}
//				System.out.println();
//			}
//			 System.out.println("========================");
//			// 과정 출력 
//			for (int i=0; i<K; i++) {
//				for (int j=0; j<K; j++) {
//					System.out.printf("%5d",dp[i][j]);
//				}
//				System.out.println();
//			}
		}
	}
}
