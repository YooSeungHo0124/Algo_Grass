import java.util.*;
import java.io.*;

public class Main {
    static int N, M, T;
    static int[][] disc;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        disc = new int[N + 1][M];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                disc[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            spin(x, d, k);
            erase();
        }

        System.out.println(calc());
    }

    static void spin(int x, int d, int k) {
        for (int i = x; i <= N; i += x) {
            int[] temp = new int[M];
            if (d == 0) { // 시계 방향
                for (int j = 0; j < M; j++) {
                    temp[(j + k) % M] = disc[i][j];
                }
            } else { // 반시계 방향
                for (int j = 0; j < M; j++) {
                    temp[(j - k + M) % M] = disc[i][j];
                }
            }
            disc[i] = temp;
        }
    }

    static void erase() {
        boolean[][] toDelete = new boolean[N + 1][M];
        boolean hasDeleted = false;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (disc[i][j] == 0) continue;

                // 상하 확인
                if (i > 1 && disc[i][j] == disc[i - 1][j]) {
                    toDelete[i][j] = toDelete[i - 1][j] = true;
                    hasDeleted = true;
                }
                if (i < N && disc[i][j] == disc[i + 1][j]) {
                    toDelete[i][j] = toDelete[i + 1][j] = true;
                    hasDeleted = true;
                }

                // 좌우 (원형 처리)
                int left = (j - 1 + M) % M;
                if (disc[i][j] == disc[i][left]) {
                    toDelete[i][j] = toDelete[i][left] = true;
                    hasDeleted = true;
                }
                int right = (j + 1) % M;
                if (disc[i][j] == disc[i][right]) {
                    toDelete[i][j] = toDelete[i][right] = true;
                    hasDeleted = true;
                }
            }
        }

        if (hasDeleted) {
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {
                    if (toDelete[i][j]) disc[i][j] = 0;
                }
            }
        } else {
            // 평균 계산 및 조정
            int sum = 0, cnt = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {
                    if (disc[i][j] != 0) {
                        sum += disc[i][j];
                        cnt++;
                    }
                }
            }
            if (cnt == 0) return;
            double avg = (double) sum / cnt;
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {
                    if (disc[i][j] != 0) {
                        if (disc[i][j] > avg) disc[i][j]--;
                        else if (disc[i][j] < avg) disc[i][j]++;
                    }
                }
            }
        }
    }

    static int calc() {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                sum += disc[i][j];
            }
        }
        return sum;
    }
}