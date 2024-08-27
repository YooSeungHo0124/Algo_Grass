import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int K = s.nextInt();
        
        System.out.println(binomialCoefficient(N, K));
    }
    
    // 이항 계수 계산
    static int binomialCoefficient(int N, int K) {
        int[][] dp = new int[N + 1][K + 1];
        
        // nC0 = 1, nCn = 1
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
            if (i <= K) {
                dp[i][i] = 1;
            }
        }
        
        // 동적 프로그래밍으로 계산
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K && j < i; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % 10007;
            }
        }
        
        return dp[N][K];
    }
}