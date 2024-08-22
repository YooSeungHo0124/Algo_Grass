import java.util.*;

public class Main {

    static int R, C, K;
    static char[][] map;
    static boolean[][] visit;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int result = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        R = s.nextInt();
        C = s.nextInt();
        K = s.nextInt();

        map = new char[R][C];
        visit = new boolean[R][C];

        for (int r = 0; r < R; r++) {
            String str = s.next();
            for (int c = 0; c < C; c++) {
                map[r][c] = str.charAt(c);
            }
        }

        // DFS를 (R-1, 0)에서 시작하며 초기 이동 거리는 1로 시작
        visit[R-1][0] = true;
        dfs(R - 1, 0, 1);
        System.out.println(result);
    }

    static void dfs(int x, int y, int depth) {
        // 목적지에 도착하고, 이동 거리가 정확히 K일 경우
        if (x == 0 && y == C - 1) {
            if (depth == K) {
                result++;
            }
            return;
        }

        for (int k = 0; k < 4; k++) {
            int nr = x + dr[k];
            int nc = y + dc[k];

            if (0 <= nr && nr < R && 0 <= nc && nc < C && map[nr][nc] != 'T' && !visit[nr][nc]) {
                visit[nr][nc] = true;
                dfs(nr, nc, depth + 1);
                visit[nr][nc] = false; // 백트래킹
            }
        }
    }
}
