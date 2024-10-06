import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 입력받는 첫 번째 줄
        int[] arr = new int[N + 1];
        String[] inputs = br.readLine().split(" "); // 두 번째 줄의 배열 입력
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(inputs[i - 1]); // 값 받기
        }

        int[][] dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i][i] = 1; // 길이 1: 팰린드롬
            if (i < N && arr[i] == arr[i + 1]) {
                dp[i][i + 1] = 1; // 길이 2: 두 개의 값이 같으면 팰린드롬
            }
        }

        // 길이 3 이상: 양 끝이 같은 값이고 가운데가 팰린드롬이면
        for (int length = 3; length <= N; length++) {
            for (int i = 1; i <= N - length + 1; i++) {
                int j = i + length - 1; // j는 i로부터 length만큼 떨어진 인덱스
                if (arr[i] == arr[j] && dp[i + 1][j - 1] == 1) {
                    dp[i][j] = 1; // 양 끝이 같고 가운데가 팰린드롬이면 팰린드롬
                }
            }
        }

        int M = Integer.parseInt(br.readLine()); // M 입력
        StringBuilder sb = new StringBuilder(); // 결과 출력을 위한 StringBuilder
        for (int i = 0; i < M; i++) {
            String[] query = br.readLine().split(" ");
            int x = Integer.parseInt(query[0]);
            int y = Integer.parseInt(query[1]);
            sb.append(dp[x][y]).append("\n"); // 결과 저장
        }
        System.out.print(sb); // 결과 출력
    }
}
