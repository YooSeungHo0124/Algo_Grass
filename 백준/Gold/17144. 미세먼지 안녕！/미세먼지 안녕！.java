import java.util.*;
import java.io.*;

public class Main {

    static int R, C, T;
    static int[][] map;
    static int[] filterTop, filterBottom;
    // 동 북 서 남
    static int[] drTop = {0, -1, 0, 1};
    static int[] dcTop = {1, 0, -1, 0};
    // 동 남 서 북
    static int[] drBottom = {0, 1, 0, -1};
    static int[] dcBottom = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        boolean isFilterFound = false;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    if (!isFilterFound) {
                        filterTop = new int[]{i, j};
                        isFilterFound = true;
                    } else {
                        filterBottom = new int[]{i, j};
                    }
                }
            }
        }

        while (T-- > 0) {
            diffusion();
            rotation();
        }

        int result = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != -1) {
                    result += map[i][j];
                }
            }
        }
        System.out.println(result);
    }

    static void diffusion() {
        int[][] newMap = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    int dust = map[i][j];
                    int spreadAmount = dust / 5;
                    int spreadCount = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + drTop[d];
                        int ny = j + dcTop[d];
                        if (nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] != -1) {
                            newMap[nx][ny] += spreadAmount;
                            spreadCount++;
                        }
                    }
                    newMap[i][j] += dust - spreadAmount * spreadCount;
                }
            }
        }
        // 공기청정기 위치 복원
        newMap[filterTop[0]][filterTop[1]] = -1;
        newMap[filterBottom[0]][filterBottom[1]] = -1;
        map = newMap;
    }

    static void rotation() {
        int topRow = filterTop[0];
        int bottomRow = filterBottom[0];

        // 위쪽 반시계방향 회전
        // 위쪽 경로 (아래에서 위로)
        for (int i = topRow - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        // 오른쪽 경로 (왼쪽에서 오른쪽으로)
        for (int j = 0; j < C - 1; j++) {
            map[0][j] = map[0][j + 1];
        }
        // 아래쪽 경로 (위에서 아래로)
        for (int i = 0; i < topRow; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }
        // 왼쪽 경로 (오른쪽에서 왼쪽으로)
        for (int j = C - 1; j > 1; j--) {
            map[topRow][j] = map[topRow][j - 1];
        }
        map[topRow][1] = 0;

        // 아래쪽 시계방향 회전
        // 아래쪽 경로 (위에서 아래로)
        for (int i = bottomRow + 1; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        // 오른쪽 경로 (왼쪽에서 오른쪽으로)
        for (int j = 0; j < C - 1; j++) {
            map[R - 1][j] = map[R - 1][j + 1];
        }
        // 위쪽 경로 (아래에서 위로)
        for (int i = R - 1; i > bottomRow; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }
        // 왼쪽 경로 (오른쪽에서 왼쪽으로)
        for (int j = C - 1; j > 1; j--) {
            map[bottomRow][j] = map[bottomRow][j - 1];
        }
        map[bottomRow][1] = 0;
    }
}